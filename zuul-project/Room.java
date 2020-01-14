import java.util.Set;
import java.util.HashMap;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public class Room 
{
    public String description;
    private HashMap<String, Room> exits;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The description of the room.
     */
    public String getShortDescription()
    {
        return description;
    }

    public String getLongDescription()
    /**
     * Retourneer een lange omschrijving van deze ruimte, van de vorm:
     *  You are in the kitchen.
     *  Exits: north west
     *  @retur Een omschrijving van de ruimte en haar uitgangen.
     */
    {
        return  description + ".\n" + getExitString();
    }

    /**
     * Retourneer een string met daarin de uitgangen van de ruimte,
     * bijvoorbeeld " Exits: north west".
     * @return Een omschrijving van de aanwezige uitgangen in de 
     * ruimte.
     *
     */
    public String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;

    }

    /**
     * 
     */
    public Room getExit(String direction)
    {

        return exits.get(direction);

    }

}
