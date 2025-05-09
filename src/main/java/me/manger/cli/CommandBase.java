package me.manger.cli;

import java.util.ArrayList;

public abstract class CommandBase {

    public static String[] splitArguments(String line) {
        ArrayList<String> arguments = new ArrayList<>();
        String argument = "";
        boolean isString = false;
        for(int i = 0; i < line.length(); i++) {
            if(line.charAt(i) == '"') {
                isString = !isString;
                continue;
            }
            if(!isString && line.charAt(i) == ' ') {
                arguments.add(argument);
                argument = "";
                continue;
            }
            argument = argument.concat(String.valueOf(line.charAt(i)));
        }
        arguments.add(argument);
        return arguments.toArray(new String[0]);
    }

}
