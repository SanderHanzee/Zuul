import java.util.HashMap; 
/**
 * class player - geef hier een beschrijving van deze class
 *
 * @author (jouw naam)
 * @version (versie nummer of datum)
 */
public class player
{

    private int weightLimit;
    HashMap<String,Item> inventory;
    private int playerWeight;
    private Room currentRoom;

    public player()
    {
        weightLimit = 10;
        playerWeight = 0;
        inventory = new HashMap<String, Item>(); 
        currentRoom = null; 
    }
    public Room getCurrentRoom()
    {
        return currentRoom; 
    }
}

