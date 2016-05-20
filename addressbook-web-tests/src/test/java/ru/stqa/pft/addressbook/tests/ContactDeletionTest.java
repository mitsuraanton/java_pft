package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by Антон on 17.04.2016.
 */
public class ContactDeletionTest extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().gotoHomePage();
        if (app.contact().all().size() == 0){
//            app.contact().createContact(new ContactData().withFirstname("Test First Name").withLastname("Test Last Name").withHomePhone("12345678").withGroup("test1"));
            app.contact().createContact(new ContactData().withFirstname("Test First Name").withLastname("Test Last Name").withHomePhone("12345678"));
        }
    }

    @Test
    public void testContactCreation() {
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        Contacts after = app.db().contacts();
        assertEquals(after.size(), before.size() - 1);

        assertThat(after, equalTo(before.without(deletedContact)));
    }
}
