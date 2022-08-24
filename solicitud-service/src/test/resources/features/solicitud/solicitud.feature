Feature: Radicar una solicitud de preoferta de crédito

  Scenario: Solicitud rechazada por información RNEC inválida
    Given El cliente no tiene información RNEC correcta y el cliente se ha podido autenticar en el sistema
    When  El cliente ha podido radicar una solicitud de preoferta de crédito
    Then  La solicitud radicada por el cliente es rechazada

  Scenario: Solicitud rechazada por información Centrales no satisfactorias
    Given El cliente con reporte negativo en Centrales y el cliente se ha podido autenticar en el sistema
    When  El cliente ha podido radicar una solicitud de preoferta de crédito
    Then  La solicitud radicada por el cliente es rechazada

  Scenario: El cliente cumple con todas las condiciones para una preoferta de crédito aprobada
    Given El cliente cuenta con información válida en Centrales, RNEC, Aportes, cumple con la capacidad de endeudamiento  y tiene usuario y OTP válida
    When  El cliente ha podido radicar una solicitud de preoferta de crédito
    Then  La preoferta de crédito es aprobada
