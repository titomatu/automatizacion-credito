package com.banco.solicitud.credito;

import com.banco.solicitud.credito.cliente.dominio.modelo.Cliente;
import com.banco.solicitud.credito.cliente.infraestructura.adaptador.ClienteAdaptador;
import java.util.List;
import org.junit.Assert;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClienteAdaptadorTest {

  @Autowired
  private ClienteAdaptador clienteAdaptador;
  @Test
  @Disabled("Not Implement Test yet ")
  public void pruebaAdactador(){

    List<Cliente> cliente = clienteAdaptador.buscarCliente();
    Assert.assertNotNull(cliente);
    Assert.assertFalse(cliente.isEmpty());
    for (Cliente clien : cliente){
      System.out.println("PRUEBA UNITARIA: " + clien.getNumeroDocumento());
    }
  }


}
