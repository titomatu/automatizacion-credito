package edu.patrones.demo.aporteslineaservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Aportes_Linea")
public class InformacionAportes {

    @EmbeddedId
    @Column(name = "id")
    private InformacionAportesId aportesId;

    @Column(name = "pago_realizado")
    private Double pagoRealizado;
}
