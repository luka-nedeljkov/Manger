package me.manger;

import me.manger.cli.BuildingCommand;
import me.manger.cli.PropertyCommand;
import me.manger.db.ReadFile;
import me.manger.db.WriteFile;

import java.util.Scanner;

public class CLI {

    public static void main(String[] args) {
        ReadFile.readDB();
        while(true) {
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine().trim();
            if(line.matches("exit")) {
                break;
            }
            if(line.matches("building|(building .+)")) {
                BuildingCommand.command(line);
            }
            if(line.matches("property|(property .+)")) {
                PropertyCommand.command(line);
            }
        }
        WriteFile.writeDB();
    }

}
