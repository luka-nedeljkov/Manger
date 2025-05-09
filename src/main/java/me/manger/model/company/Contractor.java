package me.manger.model.company;

public class Contractor {

    public String id;

    public String workType;
    public String name;
    public String contactPerson;
    public String email;
    public String phone;

    public Contractor(String id, String workType, String name, String contactPerson, String email, String phone) {
        this.id = id;
        this.workType = workType;
        this.name = name;
        this.contactPerson = contactPerson;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Contractor{" +
                "id='" + id + '\'' +
                ", workType='" + workType + '\'' +
                ", name='" + name + '\'' +
                ", contactPerson='" + contactPerson + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

}
