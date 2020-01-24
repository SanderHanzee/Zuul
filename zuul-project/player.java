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

    public player()
    {
        weightLimit = 10;
        playerWeight = 0;
        inventory = new HashMap<String, Item>();  
    }
}

