package step;

import org.junit.Assert;
import view.ProductView;

import java.util.ArrayList;
import java.util.List;

public class ProductStep {

    ProductView productView = new ProductView();
    private List<String> lastAddedProducts = new ArrayList<>();

    public void addProductToCart(String productName, int quantity) {

        boolean productOpened = productView.openProduct(productName);
        Assert.assertTrue("No se pudo hacer click en el producto: " + productName, productOpened);

        Assert.assertTrue(
                "No se abrió el detalle del producto: " + productName,
                productView.isOnProductDetailScreen()
        );

        if (quantity > 1) {
            productView.increaseQuantity(quantity);
        }

        productView.tapAddToCart();
        lastAddedProducts.add(productName);

        productView.goBack();
    }

    public void openCart() {
        productView.openCart();
    }

    public boolean isProductVisible(String productName) {
        return productView.isProductVisibleInCart(productName);
    }

    public List<String> getLastAddedProducts() {
        return lastAddedProducts;
    }
}