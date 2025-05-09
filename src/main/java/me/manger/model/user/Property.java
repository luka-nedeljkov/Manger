package me.manger.model.user;

import me.manger.model.building.Building;
import me.manger.model.user.notifications.NotificationHolder;
import me.manger.model.user.paymentHistory.HistoryHolder;

public class Property implements User {

    public String id;

    public Building building;
    public String type;
    public int number;
    public double area;
    public String ownerNames;
    public String ownerEmails;
    public String ownerPhones;
    public boolean isRented;

    public String password;

    public NotificationHolder notifications;
    public HistoryHolder history;

    public Property(Building building, String type, int number, double area) {
        id = building.id + "-" + type + number;
        this.building = building;
        this.type = type;
        this.number = number;
        this.area = area;
        ownerNames = "";
        ownerEmails = "";
        ownerPhones = "";
        isRented = false;
        password = "1234";
        notifications = new NotificationHolder();
        history = new HistoryHolder();
    }

    public Property(String id, Building building, String type, int number, double area,
                    String ownerNames, String ownerEmails, String ownerPhones, boolean isRented,
                    String password, NotificationHolder notifications, HistoryHolder history) {
        this.id = id;
        this.building = building;
        this.type = type;
        this.number = number;
        this.area = area;
        this.ownerNames = ownerNames;
        this.ownerEmails = ownerEmails;
        this.ownerPhones = ownerPhones;
        this.isRented = isRented;
        this.password = password;
        this.notifications = notifications;
        this.history = history;

        if(type.equals("apartment")) {
            building.properties.add(this);
            return;
        }
        building.garages.add(this);
    }

    @Override
    public String toString() {
        if(type.equals("apartment")) {
            return "Stan " + number;
        }
        return "Garaža " + number;
    }

}
