package edu.patrones.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudDto {
    ClienteDto clienteDto;

    private String numeroSolicitud;
    private Double valorSolicitado;
    private Double valorAprobado;
    private Double promedioAportes;
    private int plazo;
    private String reportado;
    private String mensaje;
}
