package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

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
        app.goTo().mainPage();
    }

    @BeforeMethod
    public void ensurePreconditionsForGroup() {
        if(app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test add"));
        }
    }

    @Test
    public void testDeletingFromGroup(){
        Contacts contacts = app.db().contacts();
        contacts.removeIf(contactData -> contactData.getGroups().size()==0);
        if(contacts.size() == 0){
            app.goTo().mainPage();
            app.contact().create(new ContactData().withFirstName("Maria").withLastName("Ivanova").
                    withAddress("dsfe").withMobilePhone("qwsdfe").withEmail("qwerty").withGroup("test1"), true);
            contacts = app.db().contacts();
            contacts.removeIf(contactData -> contactData.getGroups().size()==0);
        }
        ContactData modifiedContact = contacts.iterator().next();
        ContactData contactBefore = app.db().contactById(modifiedContact.getId());
        GroupData selectedGroup = modifiedContact.getGroups().iterator().next();
        app.goTo().mainPage();
        app.contact().showContactsInGroup(selectedGroup.getName());
        app.contact().selectContactById(modifiedContact.getId());
        app.contact().submitDeleteContactFromGroup();
        ContactData updatedContact = app.db().contactById(modifiedContact.getId());
        assertThat(updatedContact.getGroups(), equalTo(contactBefore.deleteGroup(selectedGroup).getGroups()));
        verifyContactListInUIByGroup(selectedGroup);
    }
}