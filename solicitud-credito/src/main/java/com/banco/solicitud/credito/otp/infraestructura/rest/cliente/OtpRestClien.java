package com.banco.solicitud.credito.otp.infraestructura.rest.cliente;



import com.banco.solicitud.credito.otp.infraestructura.rest.recurso.OtpRequestDto;
import com.banco.solicitud.credito.otp.infraestructura.rest.recurso.OtpResponseDto;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(value = "otp", url = "${feign.client.url.otp}")
public interface OtpRestClien {

 @RequestMapping(method = RequestMethod.POST ,consumes = "application/json")
  OtpResponseDto respuetaOTP(@Param OtpRequestDto otpRequestDto);


}
