package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertFalse;

public class AddContactToGroupTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditionsForContact() {
        if (app.db().contacts().size() == 0) {
            app.goTo().mainPage();
            app.contact().create(new ContactData().withFirstName("Maria").withLastName("Ivanova").
                    withAddress("dsfe").withMobilePhone("qwsdfe").withEmail("qwerty"), true);
        }
        app.goTo().mainPage();
    }

    @BeforeMethod
    public void ensurePreconditionsForGroup() {
        if(app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test"));
        }
    }

    @Test
    public void testAddingToGroup(){
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        GroupData selectedGroup = groups.iterator().next();
        ContactData modifiedContact = contacts.iterator().next();
        ContactData contactBefore = app.db().contactById(modifiedContact.getId());
        app.goTo().mainPage();
        app.contact().showContactsInGroup("[all]");
        assertFalse(selectedGroup.getContacts().contains(modifiedContact));
        assertFalse(modifiedContact.getGroups().contains(selectedGroup));
        app.contact().selectContactById(modifiedContact.getId());
        app.contact().selectGroup(selectedGroup.getName());
        app.contact().addToGroupSelectedContacts();
        ContactData updatedContact = app.db().contactById(modifiedContact.getId());
        assertThat(updatedContact.getGroups(), equalTo(contactBefore.addGroup(selectedGroup).getGroups()));
        verifyContactListInUIByGroup(selectedGroup);
    }
}