package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getNavigationHelper().gotoAddNewContactPage();
        ContactData contact = new ContactData("Test First Name", "Test Last Name", "12345678", "test1");
        app.getContactHelper().fillContactForm(contact, true);
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        Comparator<? super ContactData> byLastNameFirstName = (c1, c2) -> (c1.getLastname()+c1.getFirstname()).compareTo(c2.getLastname()+c2.getFirstname());
        before.sort(byLastNameFirstName);
        after.sort(byLastNameFirstName);
        Assert.assertEquals(before, after);
    }

}
