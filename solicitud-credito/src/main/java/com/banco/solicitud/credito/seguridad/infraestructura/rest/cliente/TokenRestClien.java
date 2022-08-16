package com.banco.solicitud.credito.seguridad.infraestructura.rest.cliente;

import com.banco.solicitud.credito.seguridad.infraestructura.rest.recurso.AuthRequestDto;
import com.banco.solicitud.credito.seguridad.infraestructura.rest.recurso.AuthResponseDto;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

@FeignClient(value = "generatoke", url = "${feign.client.url.generatoke}")
public interface TokenRestClien {

  @RequestMapping(method = RequestMethod.POST ,consumes = "application/json")
AuthResponseDto authToken(@RequestParam(value = "username") String username , @RequestParam(value = "password") String password );

}
