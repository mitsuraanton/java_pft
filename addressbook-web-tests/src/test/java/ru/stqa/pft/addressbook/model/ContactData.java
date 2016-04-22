package ru.stqa.pft.addressbook.model;

public class ContactData {
    private final String firstname;
    private final String lastname;
    private final String phone;
    private String group;

    public ContactData(String firstname, String lastname, String phone, String group) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.group = group;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPhone() {
        return phone;
    }

    public String getGroup() {
        return group;
    }
}
