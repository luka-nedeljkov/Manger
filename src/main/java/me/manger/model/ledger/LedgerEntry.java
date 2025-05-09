package me.manger.model.ledger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LedgerEntry {

    public Date date;
    public String message;

    public LedgerEntry(String message) {
        date = new Date();
        this.message = message;
    }

    public LedgerEntry(long date, String message) {
        this.date = new Date(date);
        this.message = message;
    }

    public String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        return sdf.format(date);
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "LedgerEntry{" +
                "date=" + date +
                ", message='" + message + '\'' +
                '}';
    }

}
