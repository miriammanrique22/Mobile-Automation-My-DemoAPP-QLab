package view;

import driver.MobileDriverManager;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class ProductView {

    private AppiumDriver driver;

    public ProductView() {
        this.driver = MobileDriverManager.getDriver();
        PageFactory.initElements(
                new AppiumFieldDecorator(driver, Duration.ofSeconds(10)),
                this
        );
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Products']")
    @iOSXCUITFindBy(accessibility = "Products")
    private WebElement productsTitle;

    @AndroidFindBy(accessibility = "Displays number of items in your cart")
    @iOSXCUITFindBy(accessibility = "Cart")
    private WebElement cartIcon;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/plusIV")
    private WebElement plusButton;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/cartBt")
    private WebElement addToCartButton;

    public boolean isOnProductsScreen() {
        try {
            return productsTitle.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean areProductsVisible() {
        try {
            WebElement firstProduct = driver.findElement(
                    AppiumBy.xpath("//android.widget.TextView[@text='Sauce Labs Backpack']")
            );
            return firstProduct.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean openProduct(String productName) {
        try {
            WebElement product = driver.findElement(
                    AppiumBy.accessibilityId(productName)
            );

            System.out.println("Producto encontrado: " + productName);
            product.click();

            return true;

        } catch (Exception e) {
            System.out.println("No se pudo abrir producto: " + productName);
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void tapAddToCart() {
        addToCartButton.click();
    }

    public void increaseQuantity(int quantity) {
        for (int i = 1; i < quantity; i++) {
            plusButton.click();
        }
    }

    public void openCart() {
        cartIcon.click();
    }

    public boolean isProductVisibleInCart(String productName) {
        try {
            WebElement element = driver.findElement(
                    AppiumBy.xpath("//android.widget.TextView[@text='" + productName + "']")
            );
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isOnProductDetailScreen() {
        try {
            return addToCartButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void goBack() {
        driver.navigate().back();
    }
}