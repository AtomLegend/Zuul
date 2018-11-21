package com.fab;

import com.fab.Creature.Enemy;

import java.util.Random;
import java.util.Set;
import java.util.HashMap;

/*
 * Class Room - a room in an adventure game.
 *
 * This class is the main class of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 *
 * A "Room" represents one location in the scenery of the game.  It is
 * connected to other rooms via exits.  For each existing exit, the room
 * stores a reference to the neighboring room.
 *
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0 (February 2002)
 */

class Room {
    private String description;
    private HashMap exits;        // stores exits of this room.
    public Enemy enemy;

    /**
     * Create a room described "description". Initially, it has no exits.
     * "description" is something like "in a kitchen" or "in an open court
     * yard".
     */
    Room(String description) {
        this.description = description;
        exits = new HashMap();
        createRandomEnemy();
    }

    /**
     * Creates an enemy based on random booleans
     */
    private void createRandomEnemy() {
        Random random = new Random();

        boolean shouldCreateEnemy = random.nextBoolean();

        if (!shouldCreateEnemy) {
            return;
        }
        this.enemy = new Enemy();
    }

    /// Getter & Setter

    /**
     * Define an exit from this room.
     */
    void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    /**
     * Return the description of the room (the one that was defined in the
     * constructor).
     */
    public String getShortDescription() {
        return description;
    }

    /**
     * Return a long description of this room, in the form:
     * You are in the kitchen.
     * Exits: north west
     */
    String getLongDescription() {
        return "You are " + description + ".\n" + getExitString();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     */
    private String getExitString() {
        StringBuilder returnString = new StringBuilder("Exits:");
        Set keys = exits.keySet();
        for (Object key : keys) returnString.append(" ").append(key);
        return returnString.toString();
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     */
    Room getExit(String direction) {
        return (Room) exits.get(direction);
    }
}

