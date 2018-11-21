package com.fab;

/*
 * This class is the main class of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 *
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0 (February 2002)
 */

import com.fab.Util.ValidCommands;

import java.util.Arrays;

class CommandWords {

    /**
     * Constructor - initialise the command words.
     */
    CommandWords() {
        // nothing to do at the moment...
    }

    /**
     * Check whether a given String is a valid command word.
     * Returns the equivalent enum value for the entered command or null if not found.
     */
    ValidCommands isCommand(String aString) {
        return Arrays.stream(ValidCommands.values()).filter(e -> e.getValue().equals(aString)).findFirst().orElse(null);
    }

    /*
     * Print all valid commands to System.out.
     */
    void showAll() {
        for (ValidCommands validCommand : ValidCommands.values()) {
            System.out.print(validCommand.toString() + "  ");
        }
        System.out.println();
    }
}
