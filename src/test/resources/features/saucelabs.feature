Feature: Agregar productos al carrito en SauceLabs

  Scenario Outline: Agregar productos y validar carrito
    Given estoy en la aplicación de SauceLabs
    And valido que carguen correctamente los productos en la galería
    When agrego <UNIDADES> del siguiente producto "<PRODUCTO>"
    Then valido que el carrito de compra se actualice correctamente

    Examples:
      | PRODUCTO                | UNIDADES |
      | Sauce Labs Backpack     | 1        |
      | Sauce Labs Bolt T-Shirt | 1        |
      | Sauce Labs Bike Light   | 2        |