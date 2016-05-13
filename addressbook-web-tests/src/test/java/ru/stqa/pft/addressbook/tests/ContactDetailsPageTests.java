package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Anton.Mitsura on 12.05.2016.
 */
public class ContactDetailsPageTests extends TestBase {

    @Test
    public void testContactEmails(){
        app.goTo().gotoHomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        ContactData contactInfoFromContactDetailsForm = app.contact().infoFromContactDetailsForm(contact);
        assertThat(contact.getFirstname() + " " + contact.getLastname(), equalTo(contactInfoFromContactDetailsForm.getFirstnameLastname()));
        assertThat(contactInfoFromEditForm.getFirstname() + " " + contactInfoFromEditForm.getLastname(), equalTo(contactInfoFromContactDetailsForm.getFirstnameLastname()));
        assertThat(contactInfoFromEditForm.getNickname(), equalTo(contactInfoFromContactDetailsForm.getNickname()));
        assertThat(contactInfoFromEditForm.getTitle(), equalTo(contactInfoFromContactDetailsForm.getTitle()));
        assertThat(contactInfoFromEditForm.getCompany(), equalTo(contactInfoFromContactDetailsForm.getCompany()));
        assertThat(contact.getAddress(), equalTo(contactInfoFromContactDetailsForm.getAddress()));
        assertThat(contactInfoFromEditForm.getAddress(), equalTo(contactInfoFromContactDetailsForm.getAddress()));
        assertThat(contactInfoFromEditForm.getHomePhone(), equalTo(contactInfoFromContactDetailsForm.getHomePhone()));
        assertThat(contactInfoFromEditForm.getMobilePhone(), equalTo(contactInfoFromContactDetailsForm.getMobilePhone()));
        assertThat(contactInfoFromEditForm.getWorkPhone(), equalTo(contactInfoFromContactDetailsForm.getWorkPhone()));
        assertThat(contactInfoFromEditForm.getEmail1(), equalTo(contactInfoFromContactDetailsForm.getEmail1()));
        assertThat(contactInfoFromEditForm.getEmail2(), equalTo(contactInfoFromContactDetailsForm.getEmail2()));
        assertThat(contactInfoFromEditForm.getEmail3(), equalTo(contactInfoFromContactDetailsForm.getEmail3()));
        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromContactDetailsForm)));
        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromContactDetailsForm)));

    }

    private String mergeEmails(ContactData contact) {

        return Arrays.asList(contact.getEmail1(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> ! s.equals(""))
                .collect(Collectors.joining("\n"));

    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactPhoneTests::cleaned)
                .collect(Collectors.joining("\n"));
    }
}
