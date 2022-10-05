package edu.patrones.demo.registraduriaservice.unit;

import edu.patrones.demo.registraduriaservice.model.RNEC;
import edu.patrones.demo.registraduriaservice.model.RNECId;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.assertj.core.api.Assertions.assertThat;

public class RNECModelTest {

    @Test
    void testRNECId(){
        RNECId rnecId = new RNECId();
        RNECId rnecId1 = new RNECId();

        rnecId.setTipoDocumento("CC");
        rnecId.setNumeroDocumento(123456789L);

        rnecId1.setTipoDocumento("CC");
        rnecId1.setNumeroDocumento(123456789L);

        assertThat(rnecId.equals(rnecId1)).isTrue();
        assertThat(rnecId.hashCode()).isEqualTo(rnecId1.hashCode());
        assertThat(rnecId.toString()).isEqualTo(rnecId1.toString());
    }

    @Test
    void testRNEC() throws ParseException {
        DateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");

        RNECId rnecId = new RNECId("CC", 123456789L);

        RNEC rnec = new RNEC(rnecId, formatter.parse("27-12-2020"));
        RNEC rnec1 = new RNEC(rnecId, formatter.parse("27-12-2020"));

        assertThat(rnec.equals(rnec1)).isTrue();
        assertThat(rnec.hashCode()).isEqualTo(rnec1.hashCode());
        assertThat(rnec.toString()).isEqualTo(rnec1.toString());
    }
}
