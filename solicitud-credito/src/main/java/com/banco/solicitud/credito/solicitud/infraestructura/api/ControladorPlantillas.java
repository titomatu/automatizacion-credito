package com.banco.solicitud.credito.solicitud.infraestructura.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ControladorPlantillas {


  /*@GetMapping(path = "login")
  public String login(@RequestParam(name="name" , required = false, defaultValue = "solicitud Cliente") String name , Model model){
    model.addAttribute("name", name);
   return  "login" ;
  }*/

  @GetMapping(path = "index")
  public String index(@RequestParam(name="name" , required = false, defaultValue = "Index Cliente") String name , Model model){
    model.addAttribute("name", name);
    return  "index" ;
  }

  @GetMapping(path = "datos")
  public String datosCliente(@RequestParam(name="name" , required = false, defaultValue = "Datos Cliente") String name , Model model){
    model.addAttribute("name", name);
    return  "datosCliente" ;
  }

  @GetMapping(path = "recordar")
  public String recordarCliente(@RequestParam(name="name" , required = false, defaultValue = "Datos Cliente") String name , Model model){
    model.addAttribute("name", name);
    return  "recordar" ;
  }

  /*@GetMapping(path = "solicitud")
  public String solicitudCliente(@RequestParam(name="name" , required = false, defaultValue = "Datos  Cliente") String name , Model model){
    model.addAttribute("name", name);
    return  "solicitud" ;
  }*/

  @GetMapping(path = "registro")
  public String registroCliente(@RequestParam(name="name" , required = false, defaultValue = "Datos de Registro Cliente") String name , Model model){
    model.addAttribute("name", name);
    return  "registro" ;
  }

 /* @GetMapping(path = "ValidaOTP")
  public String validarOTP(@RequestParam(name="name" , required = false, defaultValue = "Datos de Registro Cliente") String name , Model model){
    model.addAttribute("name", name);
    return "validaOTP";
  }*/
 /* @GetMapping(path = "Radicar")
  public String formulario(@RequestParam(name="name" , required = false, defaultValue = "Datos de Registro Cliente") String name , Model model){
    model.addAttribute("name", name);
    return  "Radicar" ;
  }

  @GetMapping(path = "validaradicado")
  public String validaradicado(@RequestParam(name="name" , required = false, defaultValue = "Datos de Registro Cliente") String name , Model model){
    model.addAttribute("name", name);
    return  "validaradicado" ;
  }
*/


}
