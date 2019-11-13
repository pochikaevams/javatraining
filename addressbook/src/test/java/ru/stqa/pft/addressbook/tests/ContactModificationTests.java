package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Maria", "Ivanova", "Tomsk", "888", "mail.ru", "test1"));
        }
        app.getNavigationHelper().returnToHomePage();
        List<ContactData> before = app.getContactHelper().getContactsList();
        app.getContactHelper().initContactModification(before.size() - 1);
        ContactData contact = new ContactData("Maria1", "Ivanova1", "Tomsk1", "8881", "mail.ru1", "test1");
        app.getContactHelper().fillContactForm(contact);
        app.getContactHelper().confirmUpdateContact();
        app.getNavigationHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactsList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}

