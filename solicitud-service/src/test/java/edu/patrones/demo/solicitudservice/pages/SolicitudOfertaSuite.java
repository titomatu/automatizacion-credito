package edu.patrones.demo.solicitudservice.pages;

import edu.patrones.demo.solicitudservice.SolicitudAprobadaTest;
import edu.patrones.demo.solicitudservice.SolicitudRechazadaCentralesTest;
import edu.patrones.demo.solicitudservice.SolicitudRechazadaRNECTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SelectClasses({SolicitudAprobadaTest.class, SolicitudRechazadaRNECTest.class, SolicitudRechazadaCentralesTest.class})
@SuiteDisplayName("Solicitud Oferta Suit Test")
public class SolicitudOfertaSuite {
}
