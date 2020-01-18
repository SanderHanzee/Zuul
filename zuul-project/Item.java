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
    
   
   /**
    * constructor
    */
   public Item(String newdescription)
   {
    description = newdescription;
    
   }
   
   public String getDescription()
   {
       return description;
   }
}

