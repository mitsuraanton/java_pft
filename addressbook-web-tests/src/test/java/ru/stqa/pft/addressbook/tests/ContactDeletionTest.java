package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by Антон on 17.04.2016.
 */
public class ContactDeletionTest extends TestBase{
    @Test
    public void testContactCreation() {
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContacts();
        app.getNavigationHelper().gotoHomePage();
    }
}
