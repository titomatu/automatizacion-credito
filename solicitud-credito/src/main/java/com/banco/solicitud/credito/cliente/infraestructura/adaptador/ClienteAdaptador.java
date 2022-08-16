package com.banco.solicitud.credito.cliente.infraestructura.adaptador;

import com.banco.solicitud.credito.compartidos.excepcion.InvalidoRequestStatus;
import feign.FeignException;
import com.banco.solicitud.credito.cliente.dominio.servicio.ServicioCliente;
import com.banco.solicitud.credito.cliente.dominio.servicio.Interface.ConsultarCliente;
import com.banco.solicitud.credito.cliente.infraestructura.ensamblador.ClienteEnsamblador;
import com.banco.solicitud.credito.cliente.infraestructura.rest.cliente.ClienteRestClien;
import com.banco.solicitud.credito.compartidos.excepcion.ClienteRequestExcepcion;
import org.springframework.stereotype.Service;

@Service
public class ClienteAdaptador extends ServicioCliente {


  private  final ClienteEnsamblador clienteEnsamblador;
  private  final ClienteRestClien clienteRestClien;

  public ClienteAdaptador(ClienteRestClien clienteRestClien , ClienteEnsamblador clienteEnsamblador) {
    this.clienteEnsamblador = clienteEnsamblador;
    this.clienteRestClien = clienteRestClien;
    super.setConsultarCliente(obtenerCliente());
  }

  private ConsultarCliente obtenerCliente(){
    return () -> {
      try {
        return clienteEnsamblador.convierteDTOaModelo(clienteRestClien.buscarCliente());
      }catch(FeignException.NotFound e){
        return null;
      }catch (FeignException.FeignServerException e){
        throw  new ClienteRequestExcepcion(e);
      }catch (FeignException.BadRequest e){
        throw new InvalidoRequestStatus(e);
      }

    };

  }



}
