package com.banco.solicitud.credito.otp.infraestructura.api;

import com.banco.solicitud.credito.otp.dominio.modelo.Otp;
import com.banco.solicitud.credito.otp.infraestructura.adaptador.OtpAdaptadoraDto;
import com.banco.solicitud.credito.seguridad.infraestructura.rest.cliente.TokenRestClien;
import com.banco.solicitud.credito.seguridad.infraestructura.rest.recurso.AuthRequestDto;
import com.banco.solicitud.credito.seguridad.infraestructura.rest.recurso.AuthResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class OtpControlador {

  @Autowired
  private OtpAdaptadoraDto otpAdaptadoraDto;

  @Autowired
  private Otp otpG;

  @Autowired
  private AuthRequestDto authRequestDto;

  @Autowired
  private TokenRestClien tokenRestClien;


  @GetMapping(path = "/solicitud")
  public String solicitud( Model model){
    return  "solicitud" ;
  }

  @GetMapping(path = "/login")
  public String login( Model model){
    return  "login" ;
  }



  @GetMapping(path = "/validaOTP")
  public String validaOTP( Model model){
    return  "validaOTP" ;
  }

  @PostMapping(path = "/solicitud")
  public String generarOtp (Otp otp, Model model)
  {
    otpG.setUsername(otp.getUsername());
    otpG.setCorreo(otp.getCorreo());
    otp =  otpAdaptadoraDto.ObtenerOtp(otpG);
    otpG.setPassword(otp.getPassword());
    model.addAttribute("password", otp.getPassword());

    return "index";
  }

  @PostMapping(path = "/login")
  public String enviarToken (Otp otp, Model model)
  {
    authRequestDto.setUsername(otp.getUsername());
    authRequestDto.setPassword(otp.getPassword());
    System.out.println(authRequestDto.getUsername()+"-"+authRequestDto.getPassword());
    AuthResponseDto posts = tokenRestClien.authToken(authRequestDto.getUsername(),authRequestDto.getPassword());
    System.out.println(posts.getAccess_token());
    model.addAttribute("mensaje", posts.getAccess_token());

    return "radicar";
  }


}
