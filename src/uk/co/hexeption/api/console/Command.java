package uk.co.hexeption.api.console;

/**
 * Created by Hexeption on 16/01/2017.
 */
public interface Command {


    /**
     * @return An array of names available for the command.
     */
    String[] getNames();

    /**
     * An array of usefully information about the command.
     */
    void giveHelp();

    /**
     * @return An description for the command.
     */
    String getDescription();

    /**
     * Execute's the command.
     *
     * @param input
     * @param args
     */
    void execute(String input, String[] args) throws Exception;

}
