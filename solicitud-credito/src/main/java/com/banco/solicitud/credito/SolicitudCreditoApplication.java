package com.banco.solicitud.credito;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;




@SpringBootApplication
@EnableFeignClients
public class SolicitudCreditoApplication {


	public static void main(String[] args) {
		SpringApplication.run(SolicitudCreditoApplication.class, args);

	}
	/*@GetMapping
	public  String inicio() {

	  return ("Pagina de Inicio :  LOCALHOST:PUERTO_SERVICIO/NOMBRE PAGINA  **EJEMPLO: http://localhost:8080/login **");
	}
*/


}
