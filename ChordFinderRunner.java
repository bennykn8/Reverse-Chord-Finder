import java.util.*;
public class ChordFinderRunner
{
    public static void main(String[]args)
    {
        Scanner keyboard = new Scanner(System.in);
        
        boolean play = true;
        while(play){
            System.out.print("How many notes in this chord? (3-6) ");
            MyChord a = new MyChord(keyboard.nextInt());
        
            System.out.println();
            for(String n : a.getNotes())
            {
                System.out.print("Enter note: ");
                a.addNote(keyboard.next());
            }
            
            System.out.println(a);
        
            a.renameEnharmonics();
        
            a.fillIntervals();
        
            System.out.print("\nNote Locations: ");
            for(int i : a.getLibLocations())
                System.out.print(i + " ");
            
            System.out.print("\n\nIntervals: ");
            for(int i = 0; i < a.getIntervals().length; i++)
                System.out.print(a.getIntervalNames()[a.getIntervals()[i] - 1] + "(" + a.getIntervals()[i] + ") ");
            
            System.out.println("\n\n" + a.identifyChord());
        
            System.out.print("\nAnother chord? (y/n) ");
            String decision = keyboard.next();
            if(!decision.equals("y"))
                play = false;
            System.out.println();
        }
    }
}
