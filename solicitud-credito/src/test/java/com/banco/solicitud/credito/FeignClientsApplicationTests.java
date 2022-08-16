package com.banco.solicitud.credito;

import com.banco.solicitud.credito.cliente.infraestructura.rest.cliente.ClienteRestClien;
import com.banco.solicitud.credito.cliente.infraestructura.rest.recurso.ClienteDTO;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;




@SpringBootTest
public class FeignClientsApplicationTests {
  @Autowired
  private ClienteRestClien clienteRestClien;

  @Test
  @Disabled("Not Implement Test yet ")
  public void shouldLoadAllPosts() {
    final List<ClienteDTO> posts = clienteRestClien.buscarCliente();
    Assert.assertNotNull(posts);
    Assert.assertFalse(posts.isEmpty());
    for (ClienteDTO post : posts){
      System.out.println("PRUEBA UNITARIA: " +post.getNumeroDocumento());
    }
  }

}
