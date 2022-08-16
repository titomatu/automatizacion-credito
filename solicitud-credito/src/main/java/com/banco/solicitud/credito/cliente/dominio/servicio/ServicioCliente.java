package com.banco.solicitud.credito.cliente.dominio.servicio;

import com.banco.solicitud.credito.cliente.dominio.modelo.Cliente;
import com.banco.solicitud.credito.cliente.dominio.servicio.Interface.ConsultarCliente;
import java.util.List;



public abstract class ServicioCliente {


  private ConsultarCliente consultarCliente;
  public ServicioCliente() {}

  public List<Cliente> buscarCliente() {
    return  this.consultarCliente.buscar();
  }

  public void setConsultarCliente(
      ConsultarCliente consultarCliente) {
    this.consultarCliente = consultarCliente;
  }


}
