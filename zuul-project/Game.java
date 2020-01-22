import java.util.*;
import java.util.HashMap;
import java.util.ArrayList;
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Stack<Room> roomHistory;
    ArrayList<Item> inventory = new ArrayList<Item>();  
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        roomHistory = new Stack<Room>();

    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room spongebob, patrick, octo, maatemmer, sorbetpartybar, krokantekrab, keukenkrokantekrab;

        // create the rooms
        spongebob = new Room("spongebob is the main place of the game");
        patrick = new Room("in Patrick huis");
        octo = new Room("in Octo huis");
        maatemmer = new Room("in the Maatemmer");
        sorbetpartybar = new Room("in the Sorbetparybar");
        krokantekrab = new Room("in the Krokantekrab");
        keukenkrokantekrab = new Room("Keuken krokante krab");

        // geformuleerd op Noord - Oost - Zuid - West 
        // initialise room exits

        spongebob.setExit("north", maatemmer);
        spongebob.setExit("east" , octo);
        spongebob.setExit("south", patrick);

        patrick.setExit("north", spongebob);

        octo.setExit("north", sorbetpartybar);
        octo.setExit("east", krokantekrab);
        octo.setExit("west", spongebob);

        maatemmer.setExit("south", spongebob);

        sorbetpartybar.setExit("south", octo);

        krokantekrab.setExit("west", octo);

        octo.setItem(new Item("keta"));

        currentRoom = spongebob;  // start game in spongebobs house
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());

    }

    /**
     * PrintLocationInfo afzonderlijk
     */

    //   private void printLocationInfo()
    //   {
    //       System.out.println("You are " + currentRoom.getShortDescription());
    //      System.out.println("Exits: " );
    //   
    //         
    //       
    //   }
    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
            System.out.println("I don't know what you mean...");
            break;

            case HELP:
            printHelp();
            break;

            case GO:
            goRoom(command);
            break;

            case QUIT:
            wantToQuit = quit(command);
            break;
            
            case LOOK:
            look();
            break;
            
            case BACK:
            goBack();
            break;
            
            case INVENTORY:
            printInventory();
            break;
            
            case GET:
            getItem(command);
            break;
            
            case DROP:
            dropItem(command);
            break;

        }
        return wantToQuit;
    }

    /* 
    if (commandWord.equals("help")) {
    printHelp();
    }
    else if (commandWord.equals("go")) {
    goRoom(command);
    }
    else if (commandWord.equals("look")) {
    look();
    }
    else if (commandWord.equals("eat")) {
    eat();
    }
    else if (commandWord.equals("quit")) {
    wantToQuit = quit(command);
    }
    else if (commandWord.equals("back")) {
    goBack();
    }
    else if(commandWord.equals("inventory")) {
    printInventory();
    }
    else if(commandWord.equals("get")) {
    getItem(command);
    }
    else if(commandWord.equals("drop")) {
    dropItem(command);
    }
     */


    private void dropItem(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Get what?");
            return;
        }

        String item = command.getSecondWord();

        // Try to leave current room.
        Item newItem = null;
        int index =0;
        for(int i=0; i < inventory.size(); i++){
            if(inventory.get(i).getDescription().equals(item));{
                newItem = inventory.get(i);
                index = i;
            }
        }
        if (newItem == null) {
            System.out.println("that item is not in your inventory");
        }
        else {
            inventory.remove(index);
            currentRoom.setItem(new Item(item));
            System.out.println("you dropped " + item);

        }
    }

    private void getItem(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Get what?");
            return;
        }

        String item = command.getSecondWord();

        // Try to leave current room.
        Item newItem = currentRoom.getItem(item);

        if (newItem == null) {
            System.out.println("That item is not in the room");
        }
        else {
            inventory.add(newItem);
            currentRoom.removeItem(item);
            System.out.println("you picked up " + item);
        }
    }

    public void printInventory()
    {
        String output = "";
        for(int i=0; i < inventory.size(); i++){
            output += inventory.get(i).getDescription() + " ";  
        }
        System.out.println("je hebt bij je");
        System.out.println(output); 
    }
    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            roomHistory.push(currentRoom);
            currentRoom = nextRoom;

            System.out.println("You are " + currentRoom.getLongDescription());
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    private void look()
    {
        System.out.println(currentRoom.getLongDescription());
    }

    private void eat()
    {
        System.out.println("You have eaten now and not hungry anymore");
    }

    private void goBack()
    {
        if (roomHistory.empty())
        { System.out.println("U bent al helemaal terug gegaan.");
        }
        else {
            currentRoom = roomHistory.pop();
            System.out.println(currentRoom.getLongDescription());
        }
    }
}
