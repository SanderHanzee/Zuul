/**
 * class CommandWord - geef hier een beschrijving van deze class
 *
 * @author (jouw naam)
 * @version (versie nummer of datum)
 */
public enum CommandWord
{
    // Een waarde voor elk opdrachtwoord, samen met de
    // bijbehorende string voor de gebruikersinterface.
    GO("go"), 

    QUIT("quit"), 

    HELP("help"), 

    UNKNOWN("?"),
    
    LOOK("look"), 

    BACK("back"), 

    INVENTORY("inventory"),

    GET("get"), 

    DROP("drop");
    
    // De tekst van de opdracht.
   private String commandString;
    
    /**
     * Initialise with the corresponding command string.
     * @param commandString The command string.
     */
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }
    
    /**
     * @return The command word as a string.
     */
    public String toString()
    {
        return commandString;
    }
}

 
   
   

