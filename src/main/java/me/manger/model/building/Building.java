package me.manger.model.building;

import me.manger.model.ledger.LedgerHolder;
import me.manger.model.user.Property;
import me.manger.model.user.Manager;
import me.manger.model.user.paymentHistory.HistoryHolder;

import java.util.ArrayList;

public class Building {

    public String id;

    public String address;
    public double balance;

    public LedgerHolder ledger;
    public ReclamationHolder reclamations;
    public HistoryHolder history;
    public ArrayList<Property> properties;
    public ArrayList<Property> garages;

    public Manager manager;

    public Building() {
        this.id = "";
        this.address = "";
        this.balance = 0.0;
        this.ledger = new LedgerHolder();
        this.reclamations = new ReclamationHolder();
        this.history = new HistoryHolder();
        this.properties = new ArrayList<>();
        this.garages = new ArrayList<>();
        this.manager = null;
    }

    public Building(String address) {
        this.id = address.toLowerCase().replace(" ", "_");
        this.address = address;
        this.balance = 0.0;
        this.ledger = new LedgerHolder();
        this.reclamations = new ReclamationHolder();
        this.history = new HistoryHolder();
        this.properties = new ArrayList<>();
        this.garages = new ArrayList<>();
        this.manager = null;
    }

    public Building(String id, String address, double balance) {
        this.id = id;
        this.address = address;
        this.balance = balance;
        this.ledger = new LedgerHolder();
        this.reclamations = new ReclamationHolder();
        this.history = new HistoryHolder();
        this.properties = new ArrayList<>();
        this.garages = new ArrayList<>();
        this.manager = null;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if(balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return address;
    }

}
