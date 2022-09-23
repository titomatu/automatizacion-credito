package edu.patrones.demo.centralesservice.unit;

import edu.patrones.demo.centralesservice.model.Usuario;
import edu.patrones.demo.centralesservice.model.UsuarioId;
import edu.patrones.demo.centralesservice.repository.UsuarioRepository;
import edu.patrones.demo.centralesservice.service.CentralesService;
import edu.patrones.demo.dto.ClienteDto;
import edu.patrones.demo.dto.SolicitudDto;
import edu.patrones.demo.event.centrales.CentralesEvent;
import edu.patrones.demo.event.solicitud.SolicitudEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CentralesServiceTest {

    @InjectMocks
    CentralesService centralesService;

    @Mock
    UsuarioRepository usuarioRepository;

    @BeforeEach
    public void initService(){
        usuarioRepository = mock(UsuarioRepository.class);
        centralesService = new CentralesService(usuarioRepository);
    }

    @Test
    public void usuarioSinInformacion(){
        SolicitudEvent solicitudEvent = new SolicitudEvent();
        SolicitudDto solicitudDto = new SolicitudDto();
        ClienteDto clienteDto = new ClienteDto();

        clienteDto.setTipoDocumento("CC");
        clienteDto.setNumeroDocumento(99999999999999L);

        solicitudDto.setClienteDto(clienteDto);
        solicitudDto.setNumeroSolicitud("a4845166-668b-40fe-8428-3173e429de08");
        solicitudEvent.setSolicitudDto(solicitudDto);

        CentralesEvent centralesEvent = centralesService.nuevaSolicitud(solicitudEvent);

        assertThat(centralesEvent.getCentralesRequestDto().getReportado()).isEqualTo("X");
    }

    @Test
    public void usuarioConInformacion(){
        SolicitudEvent solicitudEvent = new SolicitudEvent();
        SolicitudDto solicitudDto = new SolicitudDto();
        ClienteDto clienteDto = new ClienteDto();

        clienteDto.setTipoDocumento("CC");
        clienteDto.setNumeroDocumento(99999999999999L);

        solicitudDto.setClienteDto(clienteDto);
        solicitudDto.setNumeroSolicitud("a4845166-668b-40fe-8428-3173e429de08");
        solicitudEvent.setSolicitudDto(solicitudDto);

        Usuario usuario = new Usuario(new UsuarioId("CC", 999999999L), "S");

        when(usuarioRepository.findById(any(UsuarioId.class)))
                .thenReturn(Optional.of(usuario));

        CentralesEvent centralesEvent = centralesService.nuevaSolicitud(solicitudEvent);

        assertThat(centralesEvent.getCentralesRequestDto().getReportado()).isEqualTo("S");
    }

}
