package edu.patrones.demo.aporteslineaservice.unit;

import edu.patrones.demo.aporteslineaservice.model.InformacionAportes;
import edu.patrones.demo.aporteslineaservice.model.InformacionAportesId;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.assertj.core.api.Assertions.assertThat;

public class AportesLineaModelTest {

    @Test
    void testInformacionAportesIdModel() throws ParseException {
        DateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");
        InformacionAportesId aportesId = new InformacionAportesId();

        aportesId.setId(1);
        aportesId.setTipoDocumento("CC");
        aportesId.setNumeroDocumento(123456789L);
        aportesId.setFecha_pago(formatter.parse("30-12-2020"));

        InformacionAportesId aportesId1 = new InformacionAportesId(1, "CC", 123456789L, formatter.parse("30-12-2020"));

        assertThat(aportesId.equals(aportesId1)).isTrue();
        assertThat(aportesId.hashCode()).isEqualTo(aportesId1.hashCode());
        assertThat(aportesId.toString()).isEqualTo(aportesId1.toString());
    }

    @Test
    void testInformacionAportesModel() throws ParseException {
        DateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");

        InformacionAportesId informacionAportesId =
                new InformacionAportesId(1, "CC", 123456789L, formatter.parse("30-11-2020"));

        InformacionAportes informacionAportes = new InformacionAportes(informacionAportesId, 10500000D);
        InformacionAportes informacionAportes1 = new InformacionAportes(informacionAportesId, 10500000D);

        assertThat(informacionAportes.equals(informacionAportes1)).isTrue();
        assertThat(informacionAportes.hashCode()).isEqualTo(informacionAportes1.hashCode());
        assertThat(informacionAportes.toString()).isEqualTo(informacionAportes1.toString());
    }
}
