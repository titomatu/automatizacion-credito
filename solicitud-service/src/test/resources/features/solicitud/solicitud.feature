Feature: Radicar una solicitud de preoferta de crédito

  Scenario: Solicitud rechazada por información RNEC inválida
    Given El cliente no tiene información RNEC correcta y el cliente se ha podido autenticar en el sistema
    When  El cliente ha podido radicar una solicitud de preoferta de crédito
    Then  La solicitud radicada por el cliente es rechazada