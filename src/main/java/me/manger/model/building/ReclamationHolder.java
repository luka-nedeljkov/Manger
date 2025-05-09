package me.manger.model.building;

import me.manger.model.company.Contractor;
import me.manger.model.company.Investor;
import me.manger.model.company.MainContractor;
import me.manger.model.user.Property;

import java.util.ArrayList;

public class ReclamationHolder {

    public ArrayList<ReclamationEntry> entries;

    public ReclamationHolder(ArrayList<ReclamationEntry> entries) {
        this.entries = entries;
    }

    public ReclamationHolder() {
        entries = new ArrayList<>();
    }

    public void addEntry(String message, Investor investor, MainContractor mainContractor,
                         Contractor contractor, Property sourceProperty) {
        entries.add(new ReclamationEntry(message, investor, mainContractor, contractor, sourceProperty));
    }

    @Override
    public String toString() {
        return "ReclamationHolder{" +
                "entries=" + entries +
                '}';
    }

}
