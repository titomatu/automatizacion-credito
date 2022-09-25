package edu.patrones.demo.motorreglasservice.controller;

import edu.patrones.demo.dto.MotorReglaRequestDto;
import edu.patrones.demo.dto.MotorReglaResponseDto;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
@RestController
@RequestMapping("/api")
@Slf4j
public class ReglasNegocioController {
    private final KieContainer kieContainer;

    public ReglasNegocioController(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }


    @PostMapping("/motor")
    public MotorReglaResponseDto getDiscountPercent(@RequestBody MotorReglaRequestDto orderRequest) {
        double totalINgresosFinal;
        double totcuotacalculada;
        double tasa = 22.75 / 100;
        double tasames = (1 + (tasa / 12));
        log.warn("Plazo {}", orderRequest.getPlazo());
        log.warn("Valor Solicitado {}", orderRequest.getValorSolicitado());
        /*INICIO CALCULO CUOTA MES*/
        totcuotacalculada = calcularCuota(orderRequest.getValorSolicitado(),orderRequest.getPlazo(),tasa,tasames);
        /*FIN CALCULO CUOTA MES*/
        /*Se validan el total de ingresos - gastos y factor de ajuste*/
        totalINgresosFinal = calculaTotalIngresos(orderRequest.getSalarioAportes(), orderRequest.getGastos());

        MotorReglaResponseDto respuesta = new  MotorReglaResponseDto();
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(orderRequest);
        kieSession.fireAllRules();
        kieSession.dispose();
        log.warn("1. Valor Solicitado {} - valorcuotaredondeada {}", totalINgresosFinal, totcuotacalculada);
        respuesta.setValorAprobado(orderRequest.getValorAprobado());
        respuesta.setValorCuota(totcuotacalculada);
        if (totcuotacalculada > totalINgresosFinal){
            orderRequest.setCodeRespuesta(1699);
            respuesta.setValorAprobado(0);
            orderRequest.setMensajeE("Valor de la cuota > A Ingresos");
        }
        log.warn("2. Valor Solicitado {} - valorcuotaredondeada {} - CodError {} ", totalINgresosFinal, totcuotacalculada, orderRequest.getCodeRespuesta());
        if (orderRequest.getCodeRespuesta() == 1699 && (totalINgresosFinal >= 400000 && totalINgresosFinal <= 1500000)){
            totcuotacalculada = calcularCuota(1500000, orderRequest.getPlazo(),tasa,tasames);
            orderRequest.setCodeRespuesta(0);
            respuesta.setValorCuota(totcuotacalculada);
            respuesta.setValorAprobado(1500000);
            orderRequest.setMensajeE("El valor sugerido nuevo es: " + respuesta.getValorAprobado());
        }
        //log.warn("3. Valor Solicitado {} - valorcuotaredondeada{} - tasaefecmes {}: " + orderRequest.getValorAprobado(), valorcuotaredondeada, tasaefecmes);

        respuesta.setTasaCalculada(tasames);

        respuesta.setMensajeS(orderRequest.getMensajeE());
        respuesta.setCodeRespuesta(orderRequest.getCodeRespuesta());
        respuesta.setNumeroSolicitud(orderRequest.getNumeroSolicitud());
        log.warn("Resultado {} ", respuesta.getMensajeS());
        return respuesta;
    }

    public static double calcularCuota(double valorSolicitado, int plazosolicitado, double tasaEA, double tasaEM){
        double valorcuotaredondeada = 0.0;
        try {
            int plazot = (-(plazosolicitado / 12) * 12);
            //log.warn("plazot {}", plazot);
            double valorcuoptames = (valorSolicitado * (tasaEA / 12)) / (1 - Math.pow(tasaEM, plazot));
            //log.warn("valorcuoptames {}", valorcuoptames);
            valorcuotaredondeada = Math.round(valorcuoptames * 100.0) / 100.0;
            //log.warn("roundDbl {}", roundDbl);
            log.warn("TASA MES {} - PLAZO {}", tasaEM, plazot);
            log.warn("CUOTA CALCULADA {} - VALOR CUOTA REDONDEADO {}: ", valorcuoptames, valorcuotaredondeada);

        }catch (Exception e){
            log.warn("Error en calculo de cuota");
        }
        return valorcuotaredondeada;
    }

    public static double calculaTotalIngresos(double ingresos, double Gastos){
        double totalingresos = 0.0;
        try {
            log.warn("SalarioAportes {} - Gastos {} ", ingresos, Gastos);
            double totalingresmengastos = ingresos - Gastos;
            double factorajuste = (totalingresmengastos * 40 / 100);
            log.warn("factorajuste {} - totalingresmengastos {} ", factorajuste, totalingresmengastos);
            totalingresos = totalingresmengastos - factorajuste;
            log.warn("totalingresos {} ", totalingresos);

        }catch (Exception e){
            log.warn("Error en calcular el total de ingresos ");
        }
        return totalingresos;
    }
}
