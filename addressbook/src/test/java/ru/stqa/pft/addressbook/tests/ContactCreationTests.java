package ru.stqa.pft.addressbook.tests;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getContactHelper().gotoNewContactPage();
        app.getContactHelper().fillContactForm(new ContactData("Maria","Ivanova","Hellow"));
        app.getContactHelper().submitContactCreation();
        app.returnToHomePage();
    }
}
