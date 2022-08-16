package com.banco.solicitud.credito.cliente.infraestructura.ensamblador;

import org.mapstruct.Mapper;
import com.banco.solicitud.credito.cliente.dominio.modelo.Cliente;
import com.banco.solicitud.credito.cliente.infraestructura.rest.recurso.ClienteDTO;
import java.util.List;



@Mapper(componentModel = "spring")
public interface ClienteEnsamblador {

  List<Cliente> convierteDTOaModelo(List<ClienteDTO> cliente);

}
