package pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

public class BookStorePage {

    public ElementsCollection allBooksLinks = $$(By.xpath("//div[@class='rt-tbody']//a"));
    public SelenideElement addToCollectionBtn = $(By.xpath("//button[text()='Add To Your Collection']"));
}
