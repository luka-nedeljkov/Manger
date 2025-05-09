package me.manger.cli;

import me.manger.model.building.Building;
import me.manger.model.Database;
import me.manger.model.user.Property;

public class BuildingCommand extends CommandBase {

    public static void command(String line) {
        String[] arguments = splitArguments(line);
        if(arguments.length == 1) {
            System.out.println("Usage: building <add|edit|delete|status|list|list-properties> [options...]");
        }
        switch(arguments[1]) {
            case "add" -> {
                Building building = new Building(arguments[2]);
                Database.buildings.add(building);
            }
            case "edit" -> {
                Building building = Database.searchBuilding(arguments[2]);
                if(building == null) {
                    System.out.println("Building not found.");
                    return;
                }
                building.id = arguments[3].toLowerCase().replace(" ", "_");
                building.address = arguments[3];
            }
            case "delete" -> {
                Building building = Database.searchBuilding(arguments[2]);
                if(building == null) {
                    System.out.println("Building not found.");
                    return;
                }
                Database.buildings.remove(building);
                Database.properties.removeAll(building.properties);
                Database.garages.removeAll(building.garages);
                building.manager.buildings.remove(building);
            }
            case "status" -> {
                Building building = Database.searchBuilding(arguments[2]);
                if(building == null) {
                    System.out.println("Building not found.");
                    return;
                }
                System.out.println("ID: " + building.id);
                System.out.println("Address: " + building.address);
                System.out.println("Balance: " + building.balance);
                System.out.println("Manager: " + (building.manager == null ? "No manager" : building.manager.name));
            }
            case "list" -> {
                if(Database.buildings.isEmpty()) {
                    System.out.println("No buildings in database");
                    return;
                }
                for(Building building : Database.buildings) {
                    System.out.println(building.address);
                }
            }
            case "list-properties" -> {
                Building building = Database.searchBuilding(arguments[2]);
                if(building == null) {
                    System.out.println("Building not found.");
                    return;
                }
                for(Property property : building.properties) {
                    System.out.println(property);
                }
                for(Property property : building.garages) {
                    System.out.println(property);
                }
            }
        }
    }

}
