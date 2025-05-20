package me.manger.model.company;

public class Investor {

    public String id;

    public String name;
    public String contactPerson;
    public String email;
    public String phone;

    public Investor(String id, String name, String contactPerson, String email, String phone) {
        this.id = id;
        this.name = name;
        this.contactPerson = contactPerson;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return name;
    }

}
