package edu.patrones.demo.solicitudservice.config;

import edu.patrones.demo.dto.ClienteDto;
import edu.patrones.demo.dto.EmailDto;
import edu.patrones.demo.dto.SolicitudDto;
import edu.patrones.demo.event.aportes.AportesLineaStatus;
import edu.patrones.demo.event.centrales.CentralesStatus;
import edu.patrones.demo.event.estudio.EstudioStatus;
import edu.patrones.demo.event.rnec.RNECStatus;
import edu.patrones.demo.event.solicitud.SolicitudStatus;
import edu.patrones.demo.solicitudservice.model.Solicitud;
import edu.patrones.demo.solicitudservice.repository.SolicitudRepository;
import edu.patrones.demo.solicitudservice.service.SolicitudStatusPublisher;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;
import java.util.function.Consumer;

@Service
@Slf4j
public class SolicitudStatusUpdateEventHandler {

    @Autowired
    private SolicitudRepository repository;

    @Autowired
    private SolicitudStatusPublisher publisher;

    @Autowired
    private ProducerTemplate producerTemplate;

    @Transactional
    public void updateSolicitud(final String id, Consumer<Solicitud> consumer){
        this.repository
                .findById(id)
                .ifPresent(consumer.andThen(this::updateSolicitud));

    }

    private void updateSolicitud(Solicitud solicitud){
        log.warn("UPDATE SOLICITUD {} - {} - {} - {}",solicitud.getCentralesStatus(), solicitud.getRnecStatus(), solicitud.getAportesLineaStatus(), solicitud.getEstudioStatus());
        if(Objects.isNull(solicitud.getCentralesStatus())
                || Objects.isNull(solicitud.getRnecStatus())
                || Objects.isNull(solicitud.getAportesLineaStatus())
                || Objects.isNull(solicitud.getEstudioStatus()))
            return;

        var isComplete = RNECStatus.RNEC_COMPLETADO.equals(solicitud.getRnecStatus())
                && CentralesStatus.CENTRALES_COMPLETADO.equals(solicitud.getCentralesStatus())
                && AportesLineaStatus.APORTES_LINEA_VALIDADO.equals(solicitud.getAportesLineaStatus());

        var isTerminada = isComplete && !EstudioStatus.ESTUDIO_PENDIENTE.equals(solicitud.getEstudioStatus());

        var solicitudStatus = isComplete ? SolicitudStatus.SOLICITUD_COMPLETA : SolicitudStatus.SOLICITUD_RECHAZADA;
        solicitud.setSolicitudStatus(solicitudStatus);

        if(isTerminada || SolicitudStatus.SOLICITUD_RECHAZADA.equals(solicitud.getSolicitudStatus())){
            var terminadaStatus = EstudioStatus.ESTUDIO_APROBADO.equals(solicitud.getEstudioStatus()) ?
                    SolicitudStatus.SOLICITUD_APROBADA : SolicitudStatus.SOLICITUD_RECHAZADA;
            solicitud.setSolicitudStatus(terminadaStatus);

            mensajeEmail(solicitud);
            log.warn("SOLICITUD TERMINADA");
        } else {
            this.publisher.publishSolicitudEvent(convertEntityToDto(solicitud), solicitudStatus);
            log.warn("REQUEST ESTUDIO SOLICITUD");
        }
    }

    private SolicitudDto convertEntityToDto(Solicitud solicitud) {
        ClienteDto clienteDto = new ClienteDto();
        SolicitudDto solicitudDto = new SolicitudDto();

        clienteDto.setNumeroDocumento(solicitud.getCliente().getClienteId().getNumeroDocumento());
        clienteDto.setTipoDocumento(solicitud.getCliente().getClienteId().getTipoDocumento());
        clienteDto.setFechaExpedicion(solicitud.getCliente().getFechaExpedicion());
        clienteDto.setFechaNacimiento(solicitud.getCliente().getFechaNacimiento());
        clienteDto.setSalarioMensual(solicitud.getCliente().getSalarioMensual());
        clienteDto.setGastos(solicitud.getCliente().getGastos());

        solicitudDto.setClienteDto(clienteDto);
        solicitudDto.setNumeroSolicitud(solicitud.getNumeroSolicitud());
        solicitudDto.setReportado(solicitud.getReportado());
        solicitudDto.setPromedioAportes(solicitud.getPromedioAportes());
        solicitudDto.setValorSolicitado(solicitud.getValorSolicitado());
        solicitudDto.setPlazo(solicitud.getPlazo());

        return solicitudDto;
    }

    public void mensajeEmail(Solicitud solicitud){
        EmailDto emailDto = new EmailDto();
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        try {
            if(SolicitudStatus.SOLICITUD_RECHAZADA.equals(solicitud.getSolicitudStatus())){
                emailDto.setTo(solicitud.getCliente().getCorreoElectronico());
                emailDto.setSubject("Respuesta Solicitud Crédito");
                emailDto.setText("Apreciado cliente su Solicitud de preoferta de Crédito fue : " + solicitud.getSolicitudStatus());

            }else {
                if (solicitud.getValorAprobado() == 1500000){
                    emailDto.setTo(solicitud.getCliente().getCorreoElectronico());
                    emailDto.setSubject("Respuesta Solicitud Crédito");
                    emailDto.setText("Apreciado cliente su preoferta de Crédito fue : " + solicitud.getSolicitudStatus() + " por un valor de: " +
                            nf.format(solicitud.getValorAprobado()) + " y con una cuota mensual aproximada de: " + nf.format(solicitud.getCuotaCalculada()) +
                            "\n\nDentro de poco será contactado por uno de nuestros asesores."  );

                }else{
                    emailDto.setTo(solicitud.getCliente().getCorreoElectronico());
                    emailDto.setSubject("Respuesta Solicitud Crédito");
                    emailDto.setText("Apreciado cliente tiene una preoferta de Crédito : " + solicitud.getSolicitudStatus() + " por un valor de: " +
                                  nf.format(solicitud.getValorAprobado()) + " y con una cuota mensual aproximada de: " + nf.format(solicitud.getCuotaCalculada()) +
                                  "\n\nDentro de poco será contactado por uno de nuestros asesores."  );
                }
            }
            producerTemplate.start();
            producerTemplate.requestBody("direct:correo", emailDto, InputStream.class);
            producerTemplate.stop();
            log.warn("SOLICITUD TERMINADA");
        }catch (Exception e){
            log.warn("Error en envio de correo electronico ");
        }
    }
}
