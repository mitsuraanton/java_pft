package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Comparator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() {
        app.goTo().gotoHomePage();
        Contacts before = app.contact().all();
        app.goTo().gotoAddNewContactPage();
        ContactData contact = new ContactData().withFirstname("Test First Name").withLastname("Test Last Name").withPhone("12345678").withGroup("test1");
        app.contact().create(contact);
        Contacts after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

}
