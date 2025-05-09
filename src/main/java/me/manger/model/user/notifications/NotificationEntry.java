package me.manger.model.user.notifications;

import me.manger.model.user.User;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NotificationEntry implements User {

    public Date date;
    public String source;
    public String message;

    public NotificationEntry(String source, String message) {
        date = new Date();
        this.source = source;
        this.message = message;
    }

    public NotificationEntry(long date, String source, String message) {
        this.date = new Date(date);
        this.source = source;
        this.message = message;
    }

    public String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        return sdf.format(date);
    }

    public String getSource() {
        return source;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "NotificationEntry{" +
                "date=" + date +
                ", source='" + source + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

}
