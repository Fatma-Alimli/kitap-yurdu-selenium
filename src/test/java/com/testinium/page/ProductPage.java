package com.testinium.page;

import com.testinium.methods.Methods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ProductPage {

    Methods methods;

    public ProductPage() {
        methods = new Methods();
    }

    public void scrollAndSelect() {

        methods.sendKeys(By.id("search-input"), "oyuncak");
        methods.click(By.cssSelector(".common-sprite.button-search"));

        List<WebElement> elementList = methods
                .findElement(By.id("product-table"))
                .findElements(By.className("product-cr"));

        methods.scrollToWebElement(elementList.get(6));

        String id = "";

        methods.waitBySeconds(2);
        for (int index = 3; index < 7; index++) {
            WebElement element = elementList.get(index);
            element.findElement(By.className("add-to-favorites")).click();

            if (index == 5) {
                id = element.getAttribute("id");
            }

            methods.waitBySeconds(1);
        }

        methods.driver.navigate().back();
        methods.click(By.xpath("/html/body/div[5]/div/div[2]/a[1]"));
        methods.click(By.xpath("//*[@id='mainNav']/div[1]/div/div[2]/a"));
        methods.click(By.xpath("//*[@id='landing-point']/div[4]/a[2]/img"));

        methods.waitBySeconds(1);
        Select select = new Select(methods.findElement(By.xpath("//*[@id='content']/div/div/div[1]/div/div[1]/select")));

        select.selectByVisibleText("Yüksek Oylama");

        WebElement allBooksElement = methods.findElement(By.xpath("//*[@id='mainNav']/div[1]/ul/li[1]/div[2]/ul/li[3]/span"));
        methods.moveToElement(allBooksElement).build().perform();
        methods.waitBySeconds(3);

        WebElement hobyElement = methods.findElement(By.xpath("//*[@id='mainNav']/div[1]/ul/li[1]/div[2]/ul/li[3]/div/div[1]/div/ul[2]/li[14]/a"));
        methods.moveToElement(hobyElement).click().build().perform();


        Random random = new Random();
        int index = random.nextInt(15);

        WebElement bookElements = methods.findElement(By.cssSelector("#content > div > div:nth-child(1) > div > div:nth-child(2) > ul"))
                .findElements(By.className("mg-b-10")).get(index);

        methods.waitBySeconds(1);
        methods.moveToElement(bookElements).build().perform();

        methods.waitBySeconds(1);
        bookElements.findElement(By.className("add-to-cart")).click();

        methods.moveToElement(methods.findElement(By.xpath("//*[@id='header-top']/div/div[2]/ul/li[1]/div/ul/li/a"))).build().perform();
        methods.waitBySeconds(1);
        methods.moveToElement(methods.findElement(By.xpath("//*[@id='header-top']/div/div[2]/ul/li[1]/div/ul/li/div/ul/li[1]/a"))).click().build().perform();
        methods.waitBySeconds(1);

        WebElement favoriteElement = methods.findElement(By.cssSelector("#" + id));
        methods.moveToElement(favoriteElement).build().perform();

        WebElement favoriteElementButton = favoriteElement.findElement(By.cssSelector("div.hover-menu > a:nth-child(3)"));
        favoriteElementButton.click();
        methods.waitBySeconds(5);

        WebElement basketElement = methods.findElement(By.id("sprite-cart-icon"));
        methods.moveToElement(basketElement).build().perform();
        basketElement.click();
        methods.waitBySeconds(3);

        methods.findElement(By.cssSelector("#js-cart")).click();
        methods.waitBySeconds(2);

        WebElement quantity = methods.findElement(By.xpath("//input[@name='quantity']"));
        quantity.clear();
        methods.sendKeys(By.xpath("//input[@name='quantity']"), "2");
        methods.click(By.cssSelector(".fa.fa-refresh.green-icon"));
        //Assert.assertTrue(methods.isElementVisible(By.className(".grid_9.alpha")));
        methods.waitBySeconds(3);

        methods.findElement(By.cssSelector(".right>a")).click();
        methods.waitBySeconds(3);

        methods.findElement(By.xpath("//*[@id='shipping-tabs']/a[2]")).click();
        methods.sendKeys(By.id("address-firstname-companyname"), "Fatma");
        methods.sendKeys(By.id("address-lastname-title"), "Alımlı");

        Select city = new Select(methods.findElement(By.name("zone_id")));
        city.selectByVisibleText("İstanbul");

        methods.waitBySeconds(4);
        Select district = new Select(methods.findElement(By.name("county_id")));
        district.selectByVisibleText("KAĞITHANE");

        methods.sendKeys(By.id("district"), "Talatpaşa Mahallesi");
        methods.sendKeys(By.id("address-address-text"), "TestiniumTestiniumTestiniumTestiniumTestiniumTestiniumTestinium");
        methods.sendKeys(By.id("address-postcode"), "34000");
        methods.sendKeys(By.id("address-mobile-telephone"), "5554443322");
        methods.waitBySeconds(3);
        methods.findElement(By.id("button-checkout-continue")).click();
        methods.waitBySeconds(2);
        methods.findElement(By.id("button-checkout-continue")).click();


        methods.waitBySeconds(8);
        methods.sendKeys(By.id("credit-card-owner"), "Fatma Alımlı");


        List<String> cardNumberList = Arrays.asList("5555", "4444", "3333", "2222");


        for (int cardNumberIndex = 1; cardNumberIndex < 5; cardNumberIndex++) {
            methods.sendKeys(By.id("credit_card_number_" + cardNumberIndex), cardNumberList.get(cardNumberIndex - 1));
        }

        Select month = new Select(methods.findElement(By.id("credit-card-expire-date-month")));
        month.selectByVisibleText("11");

        Select year = new Select(methods.findElement(By.id("credit-card-expire-date-year")));
        year.selectByVisibleText("2022");

        methods.sendKeys(By.id("credit-card-security-code"), "234");

        methods.waitBySeconds(3);
        methods.findElement(By.xpath("//*[@id='button-checkout-continue']")).click();

        boolean isError = methods.isElementVisible(By.xpath("//*[@id='form-credit-card']/div[2]/table/tbody/tr[5]/td/span"));

        if (isError) {
            methods.findElement(By.className("checkout-logo")).click();
            methods.waitBySeconds(2);

            WebElement userElement = methods.findElement(By.xpath("//*[@id='header-top']/div/div[1]/div[1]/ul/li/a"));
            methods.moveToElement(userElement).build().perform();

            methods.waitBySeconds(2);

            WebElement exitElement = methods.findElement(By.xpath("//*[@id='header-top']/div/div[1]/div[1]/ul/li/div/ul/li[4]/a"));
            methods.moveToElement(exitElement).click().build().perform();
        }

    }

}
