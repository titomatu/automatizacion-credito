package edu.patrones.demo.registraduriaservice.unit;

import edu.patrones.demo.dto.ClienteDto;
import edu.patrones.demo.dto.SolicitudDto;
import edu.patrones.demo.event.rnec.RNECEvent;
import edu.patrones.demo.event.rnec.RNECStatus;
import edu.patrones.demo.event.solicitud.SolicitudEvent;
import edu.patrones.demo.registraduriaservice.model.RNEC;
import edu.patrones.demo.registraduriaservice.model.RNECId;
import edu.patrones.demo.registraduriaservice.repository.RNECRepository;
import edu.patrones.demo.registraduriaservice.service.RNECService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RNECServiceTest {

    @InjectMocks
    private RNECService rnecService;

    @Mock
    private RNECRepository rnecRepository;

    @Test
    public void clienteExisteRNEC() throws ParseException {
        SolicitudEvent solicitudEvent = new SolicitudEvent();
        SolicitudDto solicitudDto = new SolicitudDto();
        ClienteDto clienteDto = new ClienteDto();

        clienteDto.setTipoDocumento("CC");
        clienteDto.setNumeroDocumento(99999999999999L);

        solicitudDto.setClienteDto(clienteDto);
        solicitudDto.setNumeroSolicitud(UUID.randomUUID().toString());
        solicitudEvent.setSolicitudDto(solicitudDto);

        SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy", Locale.ENGLISH);

        RNECId rnecId = new RNECId();
        rnecId.setTipoDocumento(solicitudDto.getClienteDto().getTipoDocumento());
        rnecId.setNumeroDocumento(solicitudDto.getClienteDto().getNumeroDocumento());

        RNEC rnec = new RNEC();
        rnec.setRnecId(rnecId);
        rnec.setFechaExpedicion(formatter.parse("01-02-2000"));

        when(rnecRepository.findById(any(RNECId.class)))
                .thenReturn(Optional.of(rnec));

        RNECEvent rnecEvent = rnecService.nuevaSolicitud(solicitudEvent);

        assertThat(rnecEvent.getRnecStatus()).isEqualTo(RNECStatus.RNEC_COMPLETADO);
        assertThat(rnecEvent.getRnecRequestDto().getNumeroSolicitud()).isEqualTo(solicitudDto.getNumeroSolicitud());
    }



    @Test
    public void clienteNoExisteRNEC() throws ParseException {
        SolicitudEvent solicitudEvent = new SolicitudEvent();
        SolicitudDto solicitudDto = new SolicitudDto();
        ClienteDto clienteDto = new ClienteDto();

        clienteDto.setTipoDocumento("CC");
        clienteDto.setNumeroDocumento(99999999999999L);

        solicitudDto.setClienteDto(clienteDto);
        solicitudDto.setNumeroSolicitud(UUID.randomUUID().toString());
        solicitudEvent.setSolicitudDto(solicitudDto);

        RNECEvent rnecEvent = rnecService.nuevaSolicitud(solicitudEvent);

        assertThat(rnecEvent.getRnecStatus()).isEqualTo(RNECStatus.RNEC_NO_EXITOSO);
    }
}
