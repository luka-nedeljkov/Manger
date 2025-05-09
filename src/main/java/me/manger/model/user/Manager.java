package me.manger.model.user;

import me.manger.model.building.Building;
import me.manger.model.user.notifications.NotificationHolder;

import java.util.ArrayList;

public class Manager implements User {

    public String id;

    public String name;
    public String email;
    public String phone;

    public String password;

    public NotificationHolder notifications;

    public ArrayList<Building> buildings;

    public Manager(String id, String name, String email, String phone, String password, NotificationHolder notifications, ArrayList<Building> buildings) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.notifications = notifications;
        this.buildings = buildings;
        for(Building temp : buildings) {
            temp.manager = this;
        }
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", notifications=" + notifications +
                ", buildings=" + buildings +
                '}';
    }

}
