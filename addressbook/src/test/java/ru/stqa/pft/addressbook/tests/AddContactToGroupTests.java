package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroupTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditionsForContact() {
        if (app.db().contacts().size() == 0) {
            app.goTo().mainPage();
            app.contact().create(new ContactData().withFirstName("Maria").withLastName("Ivanova").
                    withAddress("dsfe").withMobilePhone("qwsdfe").withEmail("qwerty"), true);
        }
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
        }
        if (app.db().contacts().iterator().next().getGroups().size() == app.db().groups().size()) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
        }
    }

    @Test
    public void testAddingToGroup(){
        app.goTo().mainPage();
        Contacts contactsBefore = app.db().contacts();
        Groups groups = app.db().groups();
        ContactData selectedContact = contactsBefore.iterator().next();
        GroupData selectedGroup = groups.iterator().next();
        app.contact().selectContactById(selectedContact.getId());
        app.contact().selectGroup(selectedGroup.getName());
        app.contact().addToGroupSelectedContacts();
        Contacts contactsAfter = app.db().contacts();
        assertThat(contactsAfter.iterator().next().getGroups(), equalTo(contactsBefore.iterator().next().getGroups().withAdded(selectedGroup)));
        verifyContactListInUIByGroup(selectedGroup);
    }
}