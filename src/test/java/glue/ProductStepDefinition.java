package glue;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import step.ProductStep;
import view.ProductView;

public class ProductStepDefinition {

    ProductView productsView = new ProductView();
    ProductStep productStep = new ProductStep();

    @Given("estoy en la aplicación de SauceLabs")
    public void estoy_en_la_aplicacion_de_sauceLabs() {
        Assert.assertTrue("La app no se abrió correctamente",
                productsView.isOnProductsScreen());
    }

    @And("valido que carguen correctamente los productos en la galería")
    public void valido_que_carguen_correctamente_los_productos() {
        boolean productsLoaded = productsView.areProductsVisible();
        Assert.assertTrue("Los productos no se cargaron correctamente", productsLoaded);
    }

    @When("agrego {int} del siguiente producto {string}")
    public void agrego_del_siguiente_producto(int unidades, String producto) {
        productStep.addProductToCart(producto, unidades);
    }

    @Then("valido que el carrito de compra se actualice correctamente")
    public void valido_que_el_carrito_se_actualice() {
        productStep.openCart();

        for (String producto : productStep.getLastAddedProducts()) {
            boolean isDisplayed = productStep.isProductVisible(producto);
            Assert.assertTrue("El producto " + producto + " no se muestra en el carrito", isDisplayed);
        }
    }
}