package com.banco.solicitud.credito.cliente.infraestructura.api;

import com.banco.solicitud.credito.cliente.dominio.modelo.Cliente;
import com.banco.solicitud.credito.cliente.dominio.servicio.ServicioCliente;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
public class ClienteControlador {


  private final ServicioCliente servicioCliente;

  public ClienteControlador(ServicioCliente servicioCliente) {
    this.servicioCliente = servicioCliente;
  }
  @GetMapping("/buscar")
  public ResponseEntity<List<Cliente>>  buscarCliente(){
    return ResponseEntity.ok(servicioCliente.buscarCliente());

  }

}
