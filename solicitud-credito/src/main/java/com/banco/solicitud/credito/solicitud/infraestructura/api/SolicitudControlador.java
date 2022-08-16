package com.banco.solicitud.credito.solicitud.infraestructura.api;

import com.banco.solicitud.credito.cliente.dominio.modelo.Cliente;
import com.banco.solicitud.credito.seguridad.dominio.modelo.Auth;
import com.banco.solicitud.credito.solicitud.dominio.modelo.Solicitud;
import com.banco.solicitud.credito.solicitud.infraestructura.adaptador.SolicitudAdaptadoraDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class SolicitudControlador {

@Autowired
 private SolicitudAdaptadoraDto solicitudAdaptadoraDto;

@Autowired
private Solicitud solicitudG;

@Autowired
private Cliente cliente;

@Autowired
private Auth auth;


@GetMapping(path = "radicar")
public String solicitud( Model model){
    return "radicar";
  }

@PostMapping(path = "radicar")
  public  String generarSolicitud(Solicitud solicitud ,  Model model){

    cliente.setNombre1(solicitud.getCliente().getNombre1());
    cliente.setNombre2(solicitud.getCliente().getNombre2());
    solicitudG.setCliente(solicitud.getCliente());
    solicitudG.setValorSolicitado(solicitud.getValorSolicitado());
    solicitudG.setMensaje("Bearer "+solicitud.getMensaje());
    System.out.println(solicitudG.toString());
   try {
     solicitudAdaptadoraDto.guardarSolicitud(solicitudG);
     model.addAttribute("mensasje", "Su Solicitud Fue Generada de Manera Exitosa");
   }catch (Exception e){
     e.printStackTrace();
   }
  return  "validaradicado" ;
  }

}
