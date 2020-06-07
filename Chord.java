public class Chord
{
    private int[] formula;
    private String name;
    public Chord(int[] f, String n){
        formula = f;
        name = n;
    }
    
    public int[] getFormula(){
        return formula;
    }
    
    public String getName(){
        return name;
    }
}
