package me.manger.model;

import me.manger.model.building.Building;
import me.manger.model.company.Company;
import me.manger.model.company.Contractor;
import me.manger.model.company.Investor;
import me.manger.model.company.MainContractor;
import me.manger.model.user.Property;
import me.manger.model.user.Manager;
import me.manger.model.user.User;

import java.util.ArrayList;

public class Database {

    public static Company company;
    public static ArrayList<Investor> investors;
    public static ArrayList<MainContractor> mainContractors;
    public static ArrayList<Contractor> contractors;

    public static ArrayList<Building> buildings;
    public static ArrayList<Property> properties;
    public static ArrayList<Property> garages;
    public static ArrayList<Manager> managers;

    public static Session session;

    public static Contractor searchContractor(String id) {
        if(contractors == null) {
            throw new RuntimeException("Contractor list is empty");
        }
        for(Contractor contractor : contractors) {
            if(contractor.id.equals(id)) {
                return contractor;
            }
        }
        return null;
    }

    public static MainContractor searchMainContractor(String id) {
        if(mainContractors == null) {
            throw new RuntimeException("Main Contractor list is empty");
        }
        for(MainContractor mainContractor : mainContractors) {
            if(mainContractor.id.equals(id)) {
                return mainContractor;
            }
        }
        return null;
    }

    public static Investor searchInvestor(String id) {
        if(investors == null) {
            throw new RuntimeException("Investor list is empty");
        }
        for(Investor investor : investors) {
            if(investor.id.equals(id)) {
                return investor;
            }
        }
        return null;
    }

    public static Manager searchManager(String id) {
        if(managers == null) {
            throw new RuntimeException("Manager list is empty");
        }
        for(Manager manager : managers) {
            if(manager.id.equals(id)) {
                return manager;
            }
        }
        return null;
    }

    public static Property searchProperty(String id) {
        if(properties == null) {
            throw new RuntimeException("Property list is empty");
        }
        for(Property property : properties) {
            if(property.id.equals(id)) {
                return property;
            }
        }

        if(garages == null) {
            throw new RuntimeException("Garage list is empty");
        }
        for(Property property : garages) {
            if(property.id.equals(id)) {
                return property;
            }
        }

        return null;
    }

    public static User searchUser(String id) {
        if(properties == null) {
            throw new RuntimeException("Property list is empty");
        }
        for(Property property : properties) {
            if(property.id.equals(id)) {
                return property;
            }
        }

        if(garages == null) {
            throw new RuntimeException("Garage list is empty");
        }
        for(Property garage : garages) {
            if(garage.id.equals(id)) {
                return garage;
            }
        }

        if(managers == null) {
            throw new RuntimeException("Manager list is empty");
        }
        for(Manager manager : managers) {
            if(manager.id.equals(id)) {
                return manager;
            }
        }

        return null;
    }

    public static Building searchBuilding(String id) {
        if(buildings == null) {
            throw new RuntimeException("Building list is empty");
        }
        for(Building building : buildings) {
            if(building.id.equals(id)) {
                return building;
            }
        }
        return null;
    }

}
