package me.manger.db;

import me.manger.model.building.Building;
import me.manger.model.Database;
import me.manger.model.building.ReclamationEntry;
import me.manger.model.company.Company;
import me.manger.model.company.Contractor;
import me.manger.model.company.Investor;
import me.manger.model.company.MainContractor;
import me.manger.model.ledger.LedgerEntry;
import me.manger.model.user.Property;
import me.manger.model.user.Manager;
import me.manger.model.user.notifications.NotificationEntry;
import me.manger.model.user.paymentHistory.HistoryEntry;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class WriteFile {

    public static void writeDB() {
        deleteDirectory(new File("data"));
        createDirs();
        writeCompany(Database.company);
        writeInvestors(Database.investors);
        writeMainContractors(Database.mainContractors);
        writeContractors(Database.contractors);
        writeBuildings(Database.buildings);
        writeApartments(Database.properties);
        writeGarages(Database.garages);
        writeManagers(Database.managers);
    }

    private static void createDirs() {
        new File("data").mkdir();
        new File("data/building").mkdir();
        new File("data/contractor").mkdir();
        new File("data/investor").mkdir();
        new File("data/main_contractor").mkdir();
        new File("data/manager").mkdir();
        try {
            new File("data/company.txt").createNewFile();
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeManagers(ArrayList<Manager> managers) {
        try {
            for(Manager temp : managers) {
                String content = temp.name + '\n'
                        + temp.email + '\n'
                        + temp.phone + '\n'
                        + temp.password + '\n';
                for(Building building : temp.buildings) {
                    content += building.id + '|';
                }
                content = content.substring(0, content.length() - 1).concat("\n");
                for(NotificationEntry tempNotif : temp.notifications.entries) {
                    content += tempNotif.date.getTime() + "|" + tempNotif.source + "|" + tempNotif.message + '\n';
                }
                Files.write(Paths.get(FileNames.MANAGERS + "/" + temp.id + ".txt"), content.getBytes());
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeBuildings(ArrayList<Building> buildings) {
        try {
            for(Building temp : buildings) {
                String content = temp.address + '\n'
                        + temp.balance + '\n';
                for(LedgerEntry tempEntry : temp.ledger.entries) {
                    content += tempEntry.date.getTime() + "|" + tempEntry.message + '\n';
                }
                content += "#\n";
                for(ReclamationEntry tempRecl : temp.reclamations.entries) {
                    content += tempRecl.dateCreated.getTime() + "|" + (tempRecl.dateCompleted == null ? -1 : tempRecl.dateCompleted.getTime()) +
                            "|" + tempRecl.message + "|" + tempRecl.status +
                            "|" + tempRecl.investor.id + "|" + tempRecl.mainContractor.id +
                            "|" + tempRecl.contractor.id + "|" + tempRecl.sourceProperty.id + '\n';
                }
                content += "#\n";
                for(HistoryEntry tempHist : temp.history.entries) {
                    content += tempHist.date.getTime() + "|" + tempHist.amount + "|" + tempHist.message + '\n';
                }
                new File(FileNames.BUILDINGS + "/" + temp.id).mkdir();
                new File(FileNames.BUILDINGS + "/" + temp.id + FileNames.APARTMENTS).mkdir();
                new File(FileNames.BUILDINGS + "/" + temp.id + FileNames.GARAGES).mkdir();
                Files.write(Paths.get(FileNames.BUILDINGS + "/" + temp.id + "/building.txt"), content.getBytes());
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeGarages(ArrayList<Property> garages) {
        try {
            for(Property temp : garages) {
                String content = String.valueOf(temp.number) + '\n'
                        + temp.area + '\n'
                        + temp.ownerNames + '\n'
                        + temp.ownerEmails + '\n'
                        + temp.ownerPhones + '\n'
                        + temp.isRented + '\n'
                        + temp.password + '\n';
                for(NotificationEntry tempNotif : temp.notifications.entries) {
                    content += tempNotif.date.getTime() + "|" + tempNotif.source + "|" + tempNotif.message + '\n';
                }
                content += "#\n";
                for(HistoryEntry tempHist : temp.history.entries) {
                    content += tempHist.date.getTime() + "|" + tempHist.amount + "|" + tempHist.message + '\n';
                }
                Files.write(Paths.get(FileNames.BUILDINGS + "/" + temp.building.id + FileNames.GARAGES + "/" +  temp.id + ".txt"), content.getBytes());
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeApartments(ArrayList<Property> properties) {
        try {
            for(Property temp : properties) {
                String content = String.valueOf(temp.number) + '\n'
                        + temp.area + '\n'
                        + temp.ownerNames + '\n'
                        + temp.ownerEmails + '\n'
                        + temp.ownerPhones + '\n'
                        + temp.isRented + '\n'
                        + temp.password + '\n';
                for(NotificationEntry tempNotif : temp.notifications.entries) {
                    content += tempNotif.date.getTime() + "|" + tempNotif.source + "|" + tempNotif.message + '\n';
                }
                content += "#\n";
                for(HistoryEntry tempHist : temp.history.entries) {
                    content += tempHist.date.getTime() + "|" + tempHist.amount + "|" + tempHist.message + '\n';
                }
                Files.write(Paths.get(FileNames.BUILDINGS + "/" + temp.building.id + FileNames.APARTMENTS + "/" +  temp.id + ".txt"), content.getBytes());
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeContractors(ArrayList<Contractor> contractors) {
        try {
            for(Contractor temp : contractors) {
                String content = temp.workType + '\n'
                        + temp.name + '\n'
                        + temp.contactPerson + '\n'
                        + temp.email + '\n'
                        + temp.phone;
                Files.write(Paths.get(FileNames.CONTRACTORS + "/" +  temp.id + ".txt"), content.getBytes());
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeMainContractors(ArrayList<MainContractor> mainContractors) {
        try {
            for(MainContractor temp : mainContractors) {
                String content = temp.name + '\n'
                        + temp.contactPerson + '\n'
                        + temp.email + '\n'
                        + temp.phone;
                Files.write(Paths.get(FileNames.MAIN_CONTRACTORS + "/" +  temp.id + ".txt"), content.getBytes());
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeInvestors(ArrayList<Investor> investors) {
        try {
            for(Investor temp : investors) {
                String content = temp.name + '\n'
                        + temp.contactPerson + '\n'
                        + temp.email + '\n'
                        + temp.phone;
                Files.write(Paths.get(FileNames.INVESTORS + "/" +  temp.id + ".txt"), content.getBytes());
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeCompany(Company company) {
        if(company == null) {
            System.out.println("Company is null");
            return;
        }
        try {
            String content = company.name + '\n'
                    + company.email + '\n'
                    + company.phoneNumber;
            Files.write(Paths.get(FileNames.COMPANY), content.getBytes());
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteDirectory(File folder) {
        File[] files = folder.listFiles();
        if(files!=null) {
            for(File f: files) {
                if(f.isDirectory()) {
                    deleteDirectory(f);
                } else {
                    f.delete();
                }
            }
        }
        folder.delete();
    }


}
