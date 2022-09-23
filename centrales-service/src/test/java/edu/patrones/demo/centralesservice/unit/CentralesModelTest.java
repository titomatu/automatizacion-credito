package edu.patrones.demo.centralesservice.unit;

import edu.patrones.demo.centralesservice.model.Usuario;
import edu.patrones.demo.centralesservice.model.UsuarioId;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CentralesModelTest {

    @Test
    void testUsuarioId(){
        UsuarioId usuarioId = new UsuarioId();
        UsuarioId usuarioId1 = new UsuarioId();

        usuarioId.setTipoDocumento("CC");
        usuarioId.setNumeroDocumento(123456789L);

        usuarioId1.setTipoDocumento("CC");
        usuarioId1.setNumeroDocumento(123456789L);

        assertThat(usuarioId.equals(usuarioId1)).isTrue();
        assertThat(usuarioId.hashCode()).isEqualTo(usuarioId1.hashCode());
        assertThat(usuarioId.toString()).isEqualTo(usuarioId1.toString());
    }

    @Test
    void  testUsuario(){
        Usuario usuario = new Usuario();
        Usuario usuario1 = new Usuario();

        UsuarioId usuarioId = new UsuarioId("CC", 123456789L);

        usuario.setUsuarioId(usuarioId);
        usuario.setReportado("N");

        usuario1.setUsuarioId(usuarioId);
        usuario1.setReportado("N");

        assertThat(usuario.equals(usuario1)).isTrue();
        assertThat(usuario.hashCode()).isEqualTo(usuario1.hashCode());
        assertThat(usuario.toString()).isEqualTo(usuario1.toString());
    }
}
