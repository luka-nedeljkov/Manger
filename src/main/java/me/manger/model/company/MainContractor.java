package me.manger.model.company;

public class MainContractor {

    public String id;

    public String name;
    public String contactPerson;
    public String email;
    public String phone;

    public MainContractor(String id, String name, String contactPerson, String email, String phone) {
        this.id = id;
        this.name = name;
        this.contactPerson = contactPerson;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return name;
    }

}
