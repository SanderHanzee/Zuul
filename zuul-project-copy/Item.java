import java.util.ArrayList;
/**
 * class items - geef hier een beschrijving van deze class
 *
 * @author (jouw naam)
 * @version (versie nummer of datum)
 */
public class Item
{
    String description;
    int    weight;
    String name; 
    /**
     * constructor
     */
    public Item(String newName, String newDescription, int newWeight)
    {
        description = newDescription;
        weight      = newWeight;
        name        = newName;
    }

    public String getItemDescription()
    {
        return description;
    }

    public int getItemWeight()
    {
        return weight;
    }
    public String getItemName()
    {
     return name;    
    }
}

