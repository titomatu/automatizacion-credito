package com.banco.solicitud.credito.cliente.infraestructura.rest.cliente;

import com.banco.solicitud.credito.cliente.infraestructura.rest.recurso.ClienteDTO;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "prueba", url = "${feign.client.url.solicitud}")
public interface ClienteRestClien {

    @GetMapping
    List<ClienteDTO> buscarCliente();


}
