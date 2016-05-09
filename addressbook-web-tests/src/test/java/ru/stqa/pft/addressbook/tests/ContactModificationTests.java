package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by Антон on 17.04.2016.
 */
public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().gotoHomePage();
        if (! app.contact().isThereAContact()){
            app.contact().createContact(new ContactData().withFirstname("Test First Name").withLastname("Test Last Name").withPhone("12345678").withGroup("test1"));
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.contact().all();
        ContactData contact = new ContactData().withFirstname("Test First Name").withLastname("Test Last Name").withPhone("12345678").withGroup("test1");
        ContactData modifiedContact = new ContactData();
        for (Iterator<ContactData> i = before.iterator(); i.hasNext();){
            modifiedContact = i.next();
        }
        contact = contact.withId(modifiedContact.getId());
        app.contact().modify(before, contact);
        Contacts after = app.contact().all();
        assertEquals(after.size(), before.size());

        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }

}
