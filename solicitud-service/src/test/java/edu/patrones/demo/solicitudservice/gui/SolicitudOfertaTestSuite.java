package edu.patrones.demo.solicitudservice.gui;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SelectClasses({SolicitudAprobadaTest.class, SolicitudRechazadaRNECTest.class, SolicitudRechazadaCentralesTest.class, SolicitudRechazadaRNECScreenplayTest.class})
@SuiteDisplayName("Solicitud Oferta Suit Test")
public class SolicitudOfertaTestSuite {
}
