package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteContactFromGroup extends TestBase {

    @BeforeMethod
    public void ensurePreconditionsForContact() {
        if (app.db().contacts().size() == 0) {
            app.goTo().mainPage();
            app.contact().create(new ContactData().withFirstName("Maria").withLastName("Ivanova").
                    withAddress("dsfe").withMobilePhone("qwsdfe").withEmail("qwerty").withGroup("test1"), true);
        }
        if(app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
        }
        if (app.db().groups().iterator().next().getContacts().size() == 0) {
            Groups groups = app.db().groups();
            GroupData selectedGroup = groups.iterator().next();
            Contacts contacts = app.db().contacts();
            ContactData selectedContact = contacts.iterator().next();
            app.contact().selectContactById(selectedContact.getId());
            app.contact().selectGroup(selectedGroup.getName());
            app.contact().addToGroupSelectedContacts();
        }
    }

    @Test
    public void testDeletingFromGroup(){
        app.goTo().mainPage();
        Contacts contactsBefore = app.db().contacts();
        Groups groups = app.db().groups();
        ContactData selectedContact = contactsBefore.iterator().next();
        GroupData fromGroup = groups.iterator().next();
        GroupData selectedGroup = selectedContact.getGroups().iterator().next();
        app.contact().showContactsInGroup(selectedGroup.getName());
        app.contact().selectContactById(selectedContact.getId());
        app.contact().submitDeleteContactFromGroup();
        Contacts contactsAfter = app.db().contacts();
        assertThat(contactsAfter.iterator().next().getGroups(), equalTo(contactsBefore.iterator().next().getGroups().without(fromGroup)));
        app.contact().goBack();
        verifyContactListInUIByGroup(selectedGroup);
    }
}