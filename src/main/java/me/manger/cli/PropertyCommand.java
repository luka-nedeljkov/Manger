package me.manger.cli;

import me.manger.model.building.Building;
import me.manger.model.Database;
import me.manger.model.user.Property;

public class PropertyCommand extends CommandBase {

    public static void command(String line) {
        String[] arguments = splitArguments(line);
        if(arguments.length == 1) {
            System.out.println("Usage: apartment <add|edit|delete|status|list> [options...]");
        }
        switch(arguments[1]) {
            case "add" -> {
                String[] properties = arguments[2].split(",");
                Building building = Database.searchBuilding(properties[0]);
                if(properties.length != 4) {
                    System.out.println("Invalid arguments");
                }
                if(building == null) {
                    System.out.println("Building not found.");
                    return;
                }
                Property property = new Property(
                        building, properties[1],
                        Integer.parseInt(properties[2]), Double.parseDouble(properties[3])
                );
                switch(properties[1]) {
                    case "apartment" -> {
                        Database.properties.add(property);
                        building.properties.add(property);
                    }
                    case "garage" -> {
                        Database.garages.add(property);
                        building.garages.add(property);
                    }
                }
            }
            case "edit" -> {
                Property property = Database.searchProperty(arguments[2]);
                if(property == null) {
                    System.out.println("Property not found.");
                    return;
                }
                property.password = arguments[3];
            }
            case "delete" -> {
                Property property = Database.searchProperty(arguments[2]);
                if(property == null) {
                    System.out.println("Property not found.");
                    return;
                }

                if(property.type.equals("apartment")) {
                    property.building.properties.remove(property);
                    Database.properties.remove(property);
                } else {
                    property.building.garages.remove(property);
                    Database.garages.remove(property);
                }
            }
            case "status" -> {
                Property property = Database.searchProperty(arguments[2]);
                if(property == null) {
                    System.out.println("Property not found.");
                    return;
                }
                System.out.println("ID: " + property.id);
                System.out.println("Building: " + property.building.address);
                System.out.println("Type: " + property.type);
                System.out.println("Number: " + property.number);
                System.out.println("Area: " + property.area);
            }
            case "list" -> {
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
