package com.banco.solicitud.credito.solicitud.infraestructura.rest.cliente;

import com.banco.solicitud.credito.solicitud.dominio.modelo.Solicitud;
import com.banco.solicitud.credito.solicitud.infraestructura.rest.recurso.SolicitudDto;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "radicar", url = "${feign.client.url.solicitud}")
public interface SolicitudRestCliente {


  @RequestMapping(method = RequestMethod.POST ,consumes = "application/json")
  SolicitudDto radicarSolicitud (@RequestHeader(value = "Authorization", required = true)  String password,@Param SolicitudDto solicitudDto);

  //Authorization

  /*
  *   @RequestMapping(method = RequestMethod.POST ,consumes = "application/json")
AuthResponseDto authToken(@RequestParam(value = "username") String username , @RequestParam(value = "password") String password );

  * */

}
