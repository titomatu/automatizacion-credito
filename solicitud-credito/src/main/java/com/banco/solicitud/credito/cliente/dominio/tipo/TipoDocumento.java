package com.banco.solicitud.credito.cliente.dominio.tipo;


import java.util.Arrays;
import java.lang.*;
import javax.management.InvalidAttributeValueException;
import org.w3c.dom.DocumentType;

public enum TipoDocumento {


  CEDULA_DE_CIUDADANIA("CC","Cedula de Ciudadania"),
  TARJETA_DE_IDENTIDAD("TI","Tarjeta de Identidad"),
  CEDULA_DE_EXTRANGERI("CE","Cedula e Extrangeria"),
  PASAPORTE("PA","Pasasporte"),
  DOCUEMTO_EXTRANJERIA("DE","Documento de Extrangeria");


private  String valor = "";
private  String descripcion = "";

  TipoDocumento() {
  }

  private TipoDocumento(String valor, String descripcion ) {
    this.valor = valor;
    this.descripcion = descripcion;

  }

  public String getValor() {
    return valor;
  }

  public String getDescripcion() {
    return descripcion;
  }


  public TipoDocumento obtenerDescripcion(String valor){
    try{
        if (!((valor.isEmpty() || valor.isBlank()))){
            return Arrays.stream(values()).filter(TipoDocumento -> valor.equals(TipoDocumento.getValor())).findFirst().orElseThrow(() -> new InvalidAttributeValueException("El Valor: "+ valor + " No Existe" + DocumentType.class.getCanonicalName()));
        }

    }catch(Exception ex){
          System.out.print("DESC : " + ex.getMessage());
    }
    return  null;
  }

  public TipoDocumento obtenerValor(String valor){
    try{
      if (!((valor.isEmpty() || valor.isBlank()))){
        return Arrays.stream(values()).filter(TipoDocumento -> valor.equals(TipoDocumento.getDescripcion())).findFirst().orElseThrow(() -> new InvalidAttributeValueException("El Valor: "+ valor + " No Existe" + DocumentType.class.getCanonicalName()));
      }

    }catch(Exception ex){
      System.out.print("VALOR : " + ex.getMessage());
    }
    return  null;
  }
}
