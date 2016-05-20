package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;
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
//            app.contact().createContact(new ContactData().withFirstname("Test First Name").withLastname("Test Last Name").withHomePhone("12345678").withGroup("test1"));
            app.contact().createContact(new ContactData().withFirstname("Test First Name").withLastname("Test Last Name").withHomePhone("12345678"));
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.db().contacts();
//        ContactData contact = new ContactData().withFirstname("Test First Name").withLastname("Test Last Name").withHomePhone("12345678").withGroup("test1");
        ContactData contact = new ContactData().withFirstname("Test First Name").withLastname("Test Last Name").withHomePhone("12345678");
        ContactData modifiedContact = new ContactData().withFirstname("").withLastname("");
        boolean isModifySet = false;
        for (Iterator<ContactData> i = before.iterator(); i.hasNext();){
            ContactData currentContact = i.next();
            if ( (currentContact.getLastname().compareTo(modifiedContact.getLastname()) < 0) || (isModifySet == false) ){
                modifiedContact = currentContact;
                isModifySet = true;
            } else if ((currentContact.getLastname().compareTo(modifiedContact.getLastname()) == 0) && (currentContact.getFirstname().compareTo(modifiedContact.getFirstname()) < 0)){
                modifiedContact = currentContact;
            }
        }
        contact = contact.withId(modifiedContact.getId());
        File photo = new File("src/test/resources/stru.png");
        contact.withPhoto(photo);
        app.contact().modify(before, contact);
        Contacts after = app.db().contacts();
        assertEquals(after.size(), before.size());

        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }

}
