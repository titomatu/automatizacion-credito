package edu.patrones.demo.aporteslineaservice.unit;

import edu.patrones.demo.aporteslineaservice.model.InformacionAportes;
import edu.patrones.demo.aporteslineaservice.model.InformacionAportesId;
import edu.patrones.demo.aporteslineaservice.repository.AportesLineaRepository;
import edu.patrones.demo.aporteslineaservice.service.AportesLineaService;
import edu.patrones.demo.dto.ClienteDto;
import edu.patrones.demo.dto.SolicitudDto;
import edu.patrones.demo.event.aportes.AportesLineaEvent;
import edu.patrones.demo.event.aportes.AportesLineaStatus;
import edu.patrones.demo.event.solicitud.SolicitudEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AportesLineaServiceTest {

    @InjectMocks
    private AportesLineaService aportesLineaService;

    @Mock
    private AportesLineaRepository aportesLineaRepository;

    @Test
    public void promedioClienteCeros(){
        SolicitudEvent solicitudEvent = new SolicitudEvent();
        SolicitudDto solicitudDto = new SolicitudDto();
        ClienteDto clienteDto = new ClienteDto();

        clienteDto.setTipoDocumento("CC");
        clienteDto.setNumeroDocumento(99999999999999L);

        solicitudDto.setClienteDto(clienteDto);
        solicitudDto.setNumeroSolicitud(UUID.randomUUID().toString());
        solicitudEvent.setSolicitudDto(solicitudDto);

        when(aportesLineaRepository.findByAportesId_TipoDocumentoAndAportesId_NumeroDocumento(any(String.class), any(Long.class)))
                .thenReturn(new ArrayList<>());

        AportesLineaEvent aportesLineaEvent = aportesLineaService.nuevaSolicitud(solicitudEvent);

        assertThat(aportesLineaEvent.getAportesLineaStatus()).isEqualTo(AportesLineaStatus.APORTES_LINEA_VALIDADO);
        assertThat(aportesLineaEvent.getAportesLineaDto().getPromedioAportes()).isEqualTo(0D);
    }

    @Test
    public void promedioClienteMayorCeros() throws ParseException {
        DateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");

        SolicitudEvent solicitudEvent = new SolicitudEvent();
        SolicitudDto solicitudDto = new SolicitudDto();
        ClienteDto clienteDto = new ClienteDto();

        clienteDto.setTipoDocumento("CC");
        clienteDto.setNumeroDocumento(99999999999999L);

        solicitudDto.setClienteDto(clienteDto);
        solicitudDto.setNumeroSolicitud(UUID.randomUUID().toString());
        solicitudEvent.setSolicitudDto(solicitudDto);

        InformacionAportes info1 = new InformacionAportes();

        info1.setAportesId(
                new InformacionAportesId(1,
                        clienteDto.getTipoDocumento(), clienteDto.getNumeroDocumento(), formatter.parse("30-11-2020"))
        );
        info1.setPagoRealizado(7500000D);

        InformacionAportes info2 = new InformacionAportes();

        info2.setAportesId(
                new InformacionAportesId(1,
                        clienteDto.getTipoDocumento(), clienteDto.getNumeroDocumento(), formatter.parse("30-12-2020"))
        );
        info2.setPagoRealizado(8500000D);

        when(aportesLineaRepository.findByAportesId_TipoDocumentoAndAportesId_NumeroDocumento(any(String.class), any(Long.class)))
                .thenReturn(Arrays.asList(info1, info2));

        AportesLineaEvent aportesLineaEvent = aportesLineaService.nuevaSolicitud(solicitudEvent);

        assertThat(aportesLineaEvent.getAportesLineaStatus()).isEqualTo(AportesLineaStatus.APORTES_LINEA_VALIDADO);
        assertThat(aportesLineaEvent.getAportesLineaDto().getPromedioAportes()).isGreaterThan(0D);
    }
}
