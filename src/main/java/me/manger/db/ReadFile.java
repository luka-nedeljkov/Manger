package me.manger.db;

import me.manger.model.Database;
import me.manger.model.building.Building;
import me.manger.model.building.ReclamationEntry;
import me.manger.model.company.Company;
import me.manger.model.company.Contractor;
import me.manger.model.company.Investor;
import me.manger.model.company.MainContractor;
import me.manger.model.ledger.LedgerEntry;
import me.manger.model.user.Manager;
import me.manger.model.user.Property;
import me.manger.model.user.notifications.NotificationEntry;
import me.manger.model.user.notifications.NotificationHolder;
import me.manger.model.user.paymentHistory.HistoryEntry;
import me.manger.model.user.paymentHistory.HistoryHolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReadFile {

    public static void readDB() {
        Database.company = readCompany();
        Database.investors = readInvestors();
        Database.mainContractors = readMainContractors();
        Database.contractors = readContractors();
        Database.properties = new ArrayList<>();
        Database.garages = new ArrayList<>();
        Database.buildings = readBuildings();
        Database.managers = readManagers();
    }

    private static ArrayList<Manager> readManagers() {
        ArrayList<Manager> managers = new ArrayList<>();

        if(!new File("data/manager").exists()) {
            return managers;
        }

        Path directory = Paths.get(FileNames.MANAGERS);
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory)) {
            for(Path manager : stream) {
                List<String> lines = Files.readAllLines(manager);

                String id = manager.getFileName().toString().substring(0, manager.getFileName().toString().length() - 4);

                NotificationHolder notifications = new NotificationHolder();
                for(int i = 5; i < lines.size(); i++) {
                    String[] data = lines.get(i).split("\\|");
                    NotificationEntry temp = new NotificationEntry(Long.parseLong(data[0]), data[1], data[2]);
                    notifications.entries.add(temp);
                }

                ArrayList<Building> buildingsList = new ArrayList<>();
                String[] buildings = lines.get(4).split("\\|");
                for(String temp : buildings) {
                    Building tempBuilding = Database.searchBuilding(temp);
                    if(tempBuilding != null) {
                        buildingsList.add(tempBuilding);
                    }
                }

                Manager temp = new Manager(id, lines.get(0), lines.get(1), lines.get(2), lines.get(3), notifications, buildingsList);
                managers.add(temp);
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

        return managers;
    }

    private static ArrayList<Building> readBuildings() {
        ArrayList<Building> buildings = new ArrayList<>();

        if(!new File("data/building").exists()) {
            return buildings;
        }

        Path directory = Paths.get(FileNames.BUILDINGS);
        try (DirectoryStream<Path> stream1 = Files.newDirectoryStream(directory)) {
            for(Path building : stream1) {
                Building tempBuilding = new Building();

                Path apartmentsDirectory = Paths.get(building + FileNames.APARTMENTS);
                if(Files.exists(apartmentsDirectory)) {
                    DirectoryStream<Path> apartmentsStream = Files.newDirectoryStream(apartmentsDirectory);
                    for(Path apartmentFile : apartmentsStream) {
                        List<String> lines = Files.readAllLines(apartmentFile);

                        String id = apartmentFile.getFileName().toString().substring(0, apartmentFile.getFileName().toString().length() - 4);

                        NotificationHolder notifications = new NotificationHolder();
                        int i;
                        for(i = 7; i < lines.size(); i++) {
                            if(lines.get(i).equals("#")) {
                                i++;
                                break;
                            }
                            String[] data = lines.get(i).split("\\|");
                            notifications.entries.add(new NotificationEntry(Long.parseLong(data[0]), data[1], data[2]));
                        }
                        Property temp = new Property(id, tempBuilding, "apartment", Integer.parseInt(lines.get(0)),
                                Double.parseDouble(lines.get(1)), lines.get(2), lines.get(3), lines.get(4),
                                Boolean.parseBoolean(lines.get(5)), lines.get(6), notifications, null);

                        HistoryHolder history = new HistoryHolder();
                        for(; i < lines.size(); i++) {
                            String[] data = lines.get(i).split("\\|");
                            history.entries.add(new HistoryEntry(Long.parseLong(data[0]), Double.parseDouble(data[1]), data[2], temp));
                        }
                        temp.history = history;

                        Database.properties.add(temp);
                    }
                }

                Path garagesDirectory = Paths.get(building + FileNames.GARAGES);
                if(Files.exists(garagesDirectory)) {
                    DirectoryStream<Path> garagesStream = Files.newDirectoryStream(garagesDirectory);
                    for(Path garageFile : garagesStream) {
                        List<String> lines = Files.readAllLines(garageFile);

                        String id = garageFile.getFileName().toString().substring(0, garageFile.getFileName().toString().length() - 4);

                        int i;
                        NotificationHolder notifications = new NotificationHolder();
                        for(i = 7; i < lines.size(); i++) {
                            if(lines.get(i).equals("#")) {
                                i++;
                                break;
                            }
                            String[] data = lines.get(i).split("\\|");
                            notifications.entries.add(new NotificationEntry(Long.parseLong(data[0]), data[1], data[2]));
                        }

                        Property temp = new Property(id, tempBuilding, "garage", Integer.parseInt(lines.get(0)),
                                Double.parseDouble(lines.get(1)), lines.get(2), lines.get(3), lines.get(4),
                                Boolean.parseBoolean(lines.get(5)), lines.get(6), notifications, null);

                        HistoryHolder history = new HistoryHolder();
                        for(; i < lines.size(); i++) {
                            String[] data = lines.get(i).split("\\|");
                            history.entries.add(new HistoryEntry(Long.parseLong(data[0]), Double.parseDouble(data[1]), data[2], temp));
                        }
                        temp.history = history;

                        Database.garages.add(temp);
                    }
                }

                Path buildingProperties = Paths.get(building + FileNames.BUILDING);
                String id = building.getFileName().toString();
                List<String> lines = Files.readAllLines(buildingProperties);

                tempBuilding.id = id;
                tempBuilding.address = lines.get(0);
                tempBuilding.balance = Double.parseDouble(lines.get(1));

                int i;
                ArrayList<LedgerEntry> tempLedgerEntries = new ArrayList<>();
                for(i = 2; i < lines.size(); i++) {
                    if(lines.get(i).equals("#")) {
                        i++;
                        break;
                    }
                    String[] data = lines.get(i).split("\\|");
                    LedgerEntry entry = new LedgerEntry(Long.parseLong(data[0]), data[1]);
                    tempLedgerEntries.add(entry);
                }
                tempBuilding.ledger.entries = tempLedgerEntries;

                ArrayList<ReclamationEntry> tempReclamationEntries = new ArrayList<>();
                for(; i < lines.size(); i++) {
                    if(lines.get(i).equals("#")) {
                        i++;
                        break;
                    }
                    String[] data = lines.get(i).split("\\|");
                    String dateCompleted = data[1];
                    ReclamationEntry entry = new ReclamationEntry(Long.parseLong(data[0]), (dateCompleted.equals("-1") ? null : new Date(Long.parseLong(data[1]))),
                            data[2], data[3], Database.searchInvestor(data[4]), Database.searchMainContractor(data[5]),
                            Database.searchContractor(data[6]), Database.searchProperty(data[7]));
                    tempReclamationEntries.add(entry);
                }
                tempBuilding.reclamations.entries = tempReclamationEntries;

                HistoryHolder history = new HistoryHolder();
                for(; i < lines.size(); i++) {
                    String[] data = lines.get(i).split("\\|");
                    history.entries.add(new HistoryEntry(Long.parseLong(data[0]), Double.parseDouble(data[1]), data[2], null));
                }
                tempBuilding.history = history;

                buildings.add(tempBuilding);
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

        return buildings;
    }

    private static ArrayList<Contractor> readContractors() {
        ArrayList<Contractor> contractors = new ArrayList<>();

        if(!new File("data/contractor").exists()) {
            return contractors;
        }

        Path directory = Paths.get(FileNames.CONTRACTORS);
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory)) {
            for(Path file : stream) {
                List<String> lines = Files.readAllLines(file);

                String filename = file.getFileName().toString();
                String id = filename.substring(0, filename.length() - 4);

                contractors.add(new Contractor(id, lines.get(0), lines.get(1), lines.get(2), lines.get(3), lines.get(4)));
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

        return contractors;
    }

    private static ArrayList<MainContractor> readMainContractors() {
        ArrayList<MainContractor> mainContractors = new ArrayList<>();

        if(!new File("data/main_contractor").exists()) {
            return mainContractors;
        }

        Path directory = Paths.get(FileNames.MAIN_CONTRACTORS);
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory)) {
            for(Path file : stream) {
                List<String> lines = Files.readAllLines(file);

                String filename = file.getFileName().toString();
                String id = filename.substring(0, filename.length() - 4);

                mainContractors.add(new MainContractor(id, lines.get(0), lines.get(1), lines.get(2), lines.get(3)));
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

        return mainContractors;
    }

    private static ArrayList<Investor> readInvestors() {
        ArrayList<Investor> investors = new ArrayList<>();

        if(!new File("data/investor").exists()) {
            return investors;
        }

        Path directory = Paths.get(FileNames.INVESTORS);
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory)) {
            for(Path file : stream) {
                List<String> lines = Files.readAllLines(file);

                String filename = file.getFileName().toString();
                String id = filename.substring(0, filename.length() - 4);

                investors.add(new Investor(id, lines.get(0), lines.get(1), lines.get(2), lines.get(3)));
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

        return investors;
    }

    private static Company readCompany() {
        if(!new File("data/company.txt").exists()) {
            return null;
        }

        try {
            List<String> lines = Files.readAllLines(Paths.get(FileNames.COMPANY));
            return new Company(lines.get(0), lines.get(1), lines.get(2));
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

}
