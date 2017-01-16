package uk.co.hexeption.api.managers;

import uk.co.hexeption.api.console.AnnotationCommand;
import uk.co.hexeption.api.console.Command;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Hexeption on 16/01/2017.
 */
public final class CommandManager {

    private final Pattern pattern = Pattern.compile("([^\\\"']\\\\S*|\\\".+?\\\"|'.+?')\\\\s*");

    private final List<Command> commands = new ArrayList<>();

    private String commandPrefix = "-";


    public List<Command> getCommands() {

        return commands;
    }

    public String getCommandPrefix() {

        return commandPrefix;
    }

    public void setCommandPrefix(String commandPrefix) {

        this.commandPrefix = commandPrefix;
    }

    public void addCommand(Command command) {

        commands.add(command);
    }

    public boolean executeCommand(String message) {

        String commandName = message.contains(" ") ? message.split(" ")[0] : message;
        for(Command command : getCommands()){
            for(String alias : command.getNames()){
                if(alias.toLowerCase().equals(commandName.toLowerCase())){
                    tryCommand(command, message);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Get Arguments from a String input
     *
     * @param input
     * @return
     */
    private String[] getArguments(String input) {

        Matcher matcher = pattern.matcher(input);
        List<String> list = new ArrayList<>();
        while (matcher.find()) {
            list.add(matcher.group(1).replaceAll("\"", "").replaceAll("'", ""));
        }

        return list.toArray(new String[list.size()]);
    }

    private void listNames(Command command) {

        String nameslist = "Availbale names: ";
        String[] name = command.getNames();
        for (int i = 0; i < name.length; i++) {
            String names = name[i];
            nameslist += names + (i != name.length ? "," : "");
        }

    }

    public void tryCommand(Command command, String input) {

        try {
            String[] args = input.contains(" ") ? getArguments(input.substring(input.indexOf("") + 1)) : null;
            command.execute(input, args);
        } catch (Exception e) {
            e.printStackTrace();
            command.giveHelp();
        }
    }

    public void createCommands(Object ob) {

        for (Method method : ob.getClass().getMethods()) {
            AnnotationCommand.createCommand(this, ob, method);
        }
    }


}
