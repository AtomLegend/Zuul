package com.fab;

import com.fab.Creature.Enemy;
import com.fab.Creature.Player;
import com.fab.Util.EnemyDecision;
import com.fab.Util.ValidCommands;

import java.util.Timer;
import java.util.TimerTask;

/**
 * This class is the main class of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.  Users
 * can walk around some scenery. That's all. It should really be extended
 * to make it more interesting!
 * <p>
 * To play this game, create an instance of this class and call the "play"
 * method.
 * <p>
 * This main class creates and initialises all the others: it creates all
 * rooms, creates the parser and starts the game.  It also evaluates and
 * executes the commands that the parser returns.
 *
 * @author Michael Kolling and David J. Barnes
 * @version 1.0 (February 2002)
 */

class Game {
    private Parser parser;
    private Room currentRoom;
    private Player player;

    /**
     * Create the game and initialise its internal map.
     */
    Game() {
        createRooms();
        this.parser = new Parser();
        this.player = new Player();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms() {
        Room outside, theatre, pub, lab, office, gym;

        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        gym = new Room("in the gym");

        // initialise room exits
        outside.setExit("east", theatre);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theatre.setExit("west", outside);

        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);
        office.setExit("north", theatre);
        office.setExit("south", gym);

        gym.setExit("north", office);

        currentRoom = outside;  // start game outside
    }

    /**
     * Main play routine.  Loops until end of play.
     */
    void play() {
        printWelcome();
        startProcessing();
    }

    private void startProcessing() {
        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to Adventure!");
        System.out.println("Adventure is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * If this command ends the game, true is returned, otherwise false is
     * returned.
     */
    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        if (command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        ValidCommands commandWord = command.getCommandWord();
        switch (commandWord) {
            case HELP:
                printHelp();
                break;
            case GO:
                goRoom(command);
                break;
            case QUIT:
                wantToQuit = quit(command);
                break;
            case CHECK:
                checkForEnemies();
                break;
            case ATTACK:
                attackEnemy();
                break;
            case SPEAK:
                speakWithEnemy();
                break;
            case STATS:
                printPlayerStats();
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the
     * command words.
     */
    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /**
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null)
            System.out.println("There is no door!");
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /**
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game. Return true, if this command
     * quits the game, false otherwise.
     */
    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;  // signal that we want to quit
        }
    }

    private void attackEnemy() {
        if (currentRoom.enemy == null) {
            System.out.println("No enemy in sight.");
        } else if (currentRoom.enemy.alive) {
            calcAttack();
        } else {
            System.out.println("Cannot attack enemy, enemy already dead.");
        }
    }

    private void speakWithEnemy() {
        EnemyDecision decision = currentRoom.enemy.reactToSpeakAction();
        if (decision.equals(EnemyDecision.BAD)) {
            this.calcEnemyAttack();
        }
    }

    private void checkForEnemies() {
        if (currentRoom.enemy != null) {
            System.out.println("There is an enemy standing in the room.");
        } else {
            System.out.println("Seems like I'm alone in the room.");
        }
    }

    private void calcAttack() {
        System.out.println("Attacking Enemy");
        double damage = player.currentWeapon.damage;

        Enemy enemy = currentRoom.enemy;

        // Deal the damage to the enemy
        enemy.getHit(damage);

        System.out.println(String.format("Player did %s damage to enemy", damage));

        // If the enemy is still inside he will attack back
        if (enemy.alive) {
            System.out.println("Enemy will hit back");
            new Timer().schedule(
                    new TimerTask() {
                        @Override
                        public void run() {
                            calcEnemyAttack();
                        }
                    }, 1000
            );
        } else {
            System.out.println("Enemy dead. Hurray!");
        }
    }

    private void calcEnemyAttack() {
        double damage = currentRoom.enemy.currentWeapon.damage;

        player.getHit(damage);

        System.out.println(String.format("Enemy did %s damage to player", damage));

        // Player might die from damage
        if (!player.alive) {
            System.out.println("OOPS you're dead, better luck next time");
            return;
        }

        System.out.println("Got hit by enemy, remaining life " + player.health);
        new Timer().schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        calcAttack();
                    }
                }, 1000
        );
    }

    private void printPlayerStats() {
        System.out.println(this.player.getStatString());
    }

}
