package edu.patrones.demo.solicitudservice.gui.screenplay;

import edu.patrones.demo.event.aportes.AportesLineaStatus;
import edu.patrones.demo.event.centrales.CentralesStatus;
import edu.patrones.demo.event.estudio.EstudioStatus;
import edu.patrones.demo.event.rnec.RNECStatus;
import edu.patrones.demo.event.solicitud.SolicitudStatus;
import edu.patrones.demo.solicitudservice.model.Solicitud;
import net.serenitybdd.screenplay.Question;

public class SolicitudOverviewData {

    private Solicitud solicitud;

    public SolicitudOverviewData(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    public Question<SolicitudStatus> estadoSolicitud(){
        return actor -> solicitud.getSolicitudStatus();
    }

    public Question<RNECStatus> estadoValidacionRNEC(){
        return actor -> solicitud.getRnecStatus();
    }

    public Question<AportesLineaStatus> estadoAportesLinea(){
        return actor -> solicitud.getAportesLineaStatus();
    }

    public Question<CentralesStatus> estadoVerificacionCentrales(){
        return actor -> solicitud.getCentralesStatus();
    }

    public Question<EstudioStatus> estadoEstudioSolicitud(){
        return actor -> solicitud.getEstudioStatus();
    }

    public Question<Long> valorAprobado(){
        return actor -> solicitud.getValorAprobado();
    }
}
