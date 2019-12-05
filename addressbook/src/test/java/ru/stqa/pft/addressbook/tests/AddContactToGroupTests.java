package ru.stqa.pft.addressbook.tests;

import ru.stqa.pft.addressbook.model.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().mainPage();
            app.contact().create(new ContactData().withFirstName("t").withLastName("tr").
                    withAddress("tre").withMobilePhone("qwe").withEmail("qwert"));
        }
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
            app.goTo().mainPage();
        }
    }

    @Test
    public void testAddContactToGroupTests() {
        ListOfContactsInGroup before = app.db().groupsWithContact();
        ContactData contactToAddToGroup = app.db().contacts().iterator().next();
        GroupData groupToAddContactTo = app.db().groups().iterator().next();
        app.contact().addToGroup(contactToAddToGroup, groupToAddContactTo);
        ListOfContactsInGroup after = app.db().groupsWithContact();
        assertThat(after, equalTo(before.withAdded(new ContactInGroupData().withGroupId(groupToAddContactTo.getId()))));
    }
}