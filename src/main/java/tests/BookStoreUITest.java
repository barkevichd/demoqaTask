package tests;

import static data.Credentials.PASSWORD;
import static data.Credentials.USERNAME;
import static data.URLS.PROFILE_URL;

import base.TestBase;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.Test;


public class BookStoreUITest extends TestBase {

    @Test()
    public void test1() {
        firstTestSteps();
    }

    @Test()
    public void test2() {
        firstTestSteps();
        //delete book
        profilePage.deleteBook(profilePage.addedBooks.get(0));
        //check count of books is 0
        profilePage.addedBooks.shouldHave(CollectionCondition.size(0));
    }

    private void firstTestSteps(){
        //login
        loginPage.login(USERNAME, PASSWORD);
        profilePage.gotoStoreBtn.shouldBe(Condition.visible);
        //Delete all books to make sure test will be clean
        profilePage.deleteAllBooks();
        //assert that no books in collection
        profilePage.addedBooks.shouldHave(CollectionCondition.size(0));
        //navigate to store
        profilePage.gotoStoreBtn.click();
        //check that at least one books is visible
        bookStorePage.allBooksLinks.shouldHave(CollectionCondition.sizeGreaterThan(0));
        //take first book name
        String bookName = bookStorePage.allBooksLinks.get(0).getText();
        //open first book
        bookStorePage.allBooksLinks.get(0).click();
        //add book to collection
        bookStorePage.addToCollectionBtn.click();
        Selenide.confirm();
        //go to profile
        Selenide.open(PROFILE_URL);
        //check 1 book is added to collection
        profilePage.addedBooks.shouldHave(CollectionCondition.size(1));
        //check added book name
        profilePage.assertBookName(profilePage.addedBooks.get(0), bookName);
    }
}
