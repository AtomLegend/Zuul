package com.fab;

import com.fab.Util.ValidCommands;

/**
 * This class is the main class of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 * <p>
 * This class holds information about a command that was issued by the user.
 * A command currently consists of two strings: a command word and a second
 * word (for example, if the command was "take map", then the two strings
 * obviously are "take" and "map").
 * <p>
 * The way this is used is: Commands are already checked for being valid
 * command words. If the user entered an invalid command (a word that is not
 * known) then the command word is <null>.
 * <p>
 * If the command had only one word, then the second word is <null>.
 *
 * @author Michael Kolling and David J. Barnes
 * @version 1.0 (February 2002)
 */

class Command {
    private ValidCommands commandWord;
    private String secondWord;

    /**
     * Create a command object. First and second word must be supplied, but
     * either one (or both) can be null. The command word should be null to
     * indicate that this was a command that is not recognised by this game.
     */
    Command(ValidCommands command, String secondWord) {
        commandWord = command;
        this.secondWord = secondWord;
    }

    /**
     * Return the command word (the first word) of this command. If the
     * command was not understood, the result is null.
     */
    ValidCommands getCommandWord() {
        return commandWord;
    }

    /**
     * Return the second word of this command. Returns null if there was no
     * second word.
     */
    String getSecondWord() {
        return secondWord;
    }

    /**
     * Return true if this command was not understood.
     */
    boolean isUnknown() {
        return (commandWord == null);
    }

    /**
     * Return true if the command has a second word.
     */
    boolean hasSecondWord() {
        return (secondWord != null);
    }
}

