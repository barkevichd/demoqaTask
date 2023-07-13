package pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

public class ProfilePage {

    public SelenideElement gotoStoreBtn = $(By.id("gotoStore"));
    public SelenideElement deleteAllBooksBtn = $(
            By.xpath("//div[@class='mt-2 buttonWrap row']//button[text()='Delete All Books']"));

    public SelenideElement modalWindow = $(By.className("modal-content"));
    public SelenideElement modalWindowOkBtn = modalWindow.$(By.id("closeSmallModal-ok"));

    public ElementsCollection addedBooks = $$(
            By.xpath("//div[@class='rt-tbody']//div[@role='row' and not(contains(@class, '-padRow'))]"));

    public void deleteAllBooks() {
        if (addedBooks.size() > 0) {
            deleteAllBooksBtn.click();
            modalWindowOkBtn.click();
            Selenide.confirm();
            modalWindow.shouldNotBe(Condition.visible);
        }
    }

    public void deleteBook(SelenideElement rowWithBook) {
        rowWithBook.$(By.id("delete-record-undefined")).click();
        modalWindowOkBtn.click();
        Selenide.confirm();
        modalWindow.shouldNotBe(Condition.visible);
    }

    public void assertBookName(SelenideElement rowWithBook, String bookName) {
        rowWithBook.$(By.xpath(".//a")).shouldHave(Condition.text(bookName));
    }
}
