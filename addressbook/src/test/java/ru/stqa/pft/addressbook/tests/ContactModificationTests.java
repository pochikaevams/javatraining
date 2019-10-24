package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getContactHelper().selectContact();
        app.getContactHelper().editContact();
        app.getContactHelper().fillContactForm(new ContactData("Ivan","Petrov","Petrovsky"));
        app.getContactHelper().confirmUpdateContact();
        app.getNavigationHelper().returnToHomePage();
    }
}

