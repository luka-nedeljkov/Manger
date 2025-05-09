package me.manger.model.user.notifications;

import java.util.ArrayList;

public class NotificationHolder {

    public ArrayList<NotificationEntry> entries;

    public NotificationHolder(ArrayList<NotificationEntry> entries) {
        this.entries = entries;
    }

    public NotificationHolder() {
        entries = new ArrayList<>();
    }

    public void addEntry(String source, String message) {
        entries.add(new NotificationEntry(source, message));
    }

    @Override
    public String toString() {
        return "NotificationHolder{" +
                "entries=" + entries +
                '}';
    }

}
