package com.banco.solicitud.credito.cliente.dominio.servicio.Interface;

import com.banco.solicitud.credito.cliente.dominio.modelo.Cliente;
import java.util.List;

@FunctionalInterface
public interface ConsultarCliente {
  List<Cliente> buscar();

}
