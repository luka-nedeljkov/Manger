package me.manger.model.ledger;

import java.util.ArrayList;

public class LedgerHolder {

    public ArrayList<LedgerEntry> entries;

    public LedgerHolder(ArrayList<LedgerEntry> entries) {
        this.entries = entries;
    }

    public LedgerHolder() {
        entries = new ArrayList<>();
    }

    public void addEntry(String message) {
        entries.add(new LedgerEntry(message));
    }

    @Override
    public String toString() {
        return "LedgerHolder{" +
                "entries=" + entries +
                '}';
    }

}
