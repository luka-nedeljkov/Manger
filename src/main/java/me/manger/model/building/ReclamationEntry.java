package me.manger.model.building;

import me.manger.model.company.Contractor;
import me.manger.model.company.Investor;
import me.manger.model.company.MainContractor;
import me.manger.model.user.Property;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReclamationEntry {

    public Date dateCreated;
    public Date dateCompleted;
    public String message;
    public String status;

    public Investor investor;
    public MainContractor mainContractor;
    public Contractor contractor;

    public Property sourceProperty;

    public ReclamationEntry(String message, Investor investor, MainContractor mainContractor,
                            Contractor contractor,Property sourceProperty) {
        this.dateCreated = new Date();
        this.dateCompleted = null;
        this.message = message;
        this.status = "reported";
        this.investor = investor;
        this.mainContractor = mainContractor;
        this.contractor = contractor;
        this.sourceProperty = sourceProperty;
    }

    public ReclamationEntry(long dateCreated, Date dateCompleted, String message, String status,
                            Investor investor, MainContractor mainContractor, Contractor contractor, Property sourceProperty) {
        this.dateCreated = new Date(dateCreated);
        this.dateCompleted = dateCompleted;
        this.message = message;
        this.status = status;
        this.investor = investor;
        this.mainContractor = mainContractor;
        this.contractor = contractor;
        this.sourceProperty = sourceProperty;
    }

    public String getDateCreated() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        return sdf.format(dateCreated);
    }

    public String getDateCompleted() {
        if(dateCompleted == null) {
            return "-";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        return sdf.format(dateCompleted);
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public String getInvestor() {
        return investor.id;
    }

    public String getMainContractor() {
        return mainContractor.id;
    }

    public String getContractor() {
        return contractor.id;
    }

    public Property getSourceProperty() {
        return sourceProperty;
    }

    @Override
    public String toString() {
        return "ReclamationEntry{" +
                "dateCreated=" + dateCreated +
                ", dateCompleted=" + dateCompleted +
                ", message='" + message + '\'' +
                ", status='" + status + '\'' +
                ", investor=" + investor +
                ", mainContractor=" + mainContractor +
                ", contractor=" + contractor +
                ", sourceProperty=" + sourceProperty +
                '}';
    }

}
