package com.banco.solicitud.credito.cliente.infraestructura.rest.recurso;

import com.banco.solicitud.credito.cliente.dominio.tipo.TipoDocumento;
import java.time.LocalDate;
import java.util.Date;


public class ClienteDTO {
  private String tipoDocumento;
  private Long numeroDocumento;
  private Date fechaExpedicion;
  private String nombre1;
  private String nombre2;
  private String apellido1;
  private String apellido2;
  private Long celular;
  private String correoElectronico;
  private Double salarioMensual;
  private Date fechaNacimiento;
  private Double totalActivos;
  private Double totalPasivos;
  private String autorizaCentrales;
  private String genero;
  private String tipoResidencia;
  private Integer actividadEconomica;
  private String tipoContrato;
  private String nivelEstudios;
  private String tipoInmueble;
  private String estadoCivil;

  private Double gastos;

  public ClienteDTO(String tipoDocumento, Long numeroDocumento, Date fechaExpedicion,
      String nombre1, String nombre2, String apellido1, String apellido2, Long celular,
      String correoElectronico, Double salarioMensual, Date fechaNacimiento,
      Double totalActivos, Double totalPasivos, String autorizaCentrales, String genero,
      String tipoResidencia, Integer actividadEconomica, String tipoContrato,
      String nivelEstudios, String tipoInmueble, String estadoCivil, Double gastos) {
    this.tipoDocumento = tipoDocumento;
    this.numeroDocumento = numeroDocumento;
    this.fechaExpedicion = fechaExpedicion;
    this.nombre1 = nombre1;
    this.nombre2 = nombre2;
    this.apellido1 = apellido1;
    this.apellido2 = apellido2;
    this.celular = celular;
    this.correoElectronico = correoElectronico;
    this.salarioMensual = salarioMensual;
    this.fechaNacimiento = fechaNacimiento;
    this.totalActivos = totalActivos;
    this.totalPasivos = totalPasivos;
    this.autorizaCentrales = autorizaCentrales;
    this.genero = genero;
    this.tipoResidencia = tipoResidencia;
    this.actividadEconomica = actividadEconomica;
    this.tipoContrato = tipoContrato;
    this.nivelEstudios = nivelEstudios;
    this.tipoInmueble = tipoInmueble;
    this.estadoCivil = estadoCivil;
    this.gastos = gastos;
  }

  public String getTipoDocumento() {
    return tipoDocumento;
  }

  public void setTipoDocumento(String tipoDocumento) {
    this.tipoDocumento = tipoDocumento;
  }

  public Long getNumeroDocumento() {
    return numeroDocumento;
  }

  public void setNumeroDocumento(Long numeroDocumento) {
    this.numeroDocumento = numeroDocumento;
  }

  public Date getFechaExpedicion() {
    return fechaExpedicion;
  }

  public void setFechaExpedicion(Date fechaExpedicion) {
    this.fechaExpedicion = fechaExpedicion;
  }

  public String getNombre1() {
    return nombre1;
  }

  public void setNombre1(String nombre1) {
    this.nombre1 = nombre1;
  }

  public String getNombre2() {
    return nombre2;
  }

  public void setNombre2(String nombre2) {
    this.nombre2 = nombre2;
  }

  public String getApellido1() {
    return apellido1;
  }

  public void setApellido1(String apellido1) {
    this.apellido1 = apellido1;
  }

  public String getApellido2() {
    return apellido2;
  }

  public void setApellido2(String apellido2) {
    this.apellido2 = apellido2;
  }

  public Long getCelular() {
    return celular;
  }

  public void setCelular(Long celular) {
    this.celular = celular;
  }

  public String getCorreoElectronico() {
    return correoElectronico;
  }

  public void setCorreoElectronico(String correoElectronico) {
    this.correoElectronico = correoElectronico;
  }

  public Double getSalarioMensual() {
    return salarioMensual;
  }

  public void setSalarioMensual(Double salarioMensual) {
    this.salarioMensual = salarioMensual;
  }

  public Date getFechaNacimiento() {
    return fechaNacimiento;
  }

  public void setFechaNacimiento(Date fechaNacimiento) {
    this.fechaNacimiento = fechaNacimiento;
  }

  public Double getTotalActivos() {
    return totalActivos;
  }

  public void setTotalActivos(Double totalActivos) {
    this.totalActivos = totalActivos;
  }

  public Double getTotalPasivos() {
    return totalPasivos;
  }

  public void setTotalPasivos(Double totalPasivos) {
    this.totalPasivos = totalPasivos;
  }

  public String getAutorizaCentrales() {
    return autorizaCentrales;
  }

  public void setAutorizaCentrales(String autorizaCentrales) {
    this.autorizaCentrales = autorizaCentrales;
  }

  public String getGenero() {
    return genero;
  }

  public void setGenero(String genero) {
    this.genero = genero;
  }

  public String getTipoResidencia() {
    return tipoResidencia;
  }

  public void setTipoResidencia(String tipoResidencia) {
    this.tipoResidencia = tipoResidencia;
  }

  public Integer getActividadEconomica() {
    return actividadEconomica;
  }

  public void setActividadEconomica(Integer actividadEconomica) {
    this.actividadEconomica = actividadEconomica;
  }

  public String getTipoContrato() {
    return tipoContrato;
  }

  public void setTipoContrato(String tipoContrato) {
    this.tipoContrato = tipoContrato;
  }

  public String getNivelEstudios() {
    return nivelEstudios;
  }

  public void setNivelEstudios(String nivelEstudios) {
    this.nivelEstudios = nivelEstudios;
  }

  public String getTipoInmueble() {
    return tipoInmueble;
  }

  public void setTipoInmueble(String tipoInmueble) {
    this.tipoInmueble = tipoInmueble;
  }

  public String getEstadoCivil() {
    return estadoCivil;
  }

  public void setEstadoCivil(String estadoCivil) {
    this.estadoCivil = estadoCivil;
  }

  public Double getGastos() {
    return gastos;
  }

  public void setGastos(Double gastos) {
    this.gastos = gastos;
  }
}
