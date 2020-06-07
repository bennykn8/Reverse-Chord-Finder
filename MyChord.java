
public class MyChord
{
    private static final String[] library = {"C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab", "A", "Bb", "B"};
    
    private static final Chord maj = new Chord(new int[]{4, 3, 5}, " Major");
    private static final Chord maj7 = new Chord(new int[]{4, 3, 4, 1}, " Major Seventh");
    private static final Chord dom7 = new Chord(new int[]{4, 3, 3, 2}, " Dominant Seventh");
    
    private static final Chord min = new Chord(new int[]{3, 4, 5}, " Minor");
    private static final Chord min7 = new Chord(new int[]{3, 4, 3, 2}, " Minor Seventh");
    
    private static final Chord dim = new Chord(new int[]{3, 3, 6}, " Diminished");
    private static final Chord fullDim = new Chord(new int[]{3, 3, 3}, " Fully Diminished Seventh");
    private static final Chord halfDim = new Chord(new int[]{3, 3, 4, 2}, " Half Diminished Seventh");
    
    private static final Chord aug = new Chord(new int[]{4, 4, 4}, " Augmented");
    
    private static final Chord[] major = {maj, maj7, dom7};
    private static final Chord[] minor = {min, min7};
    private static final Chord[] diminished = {dim, fullDim, halfDim};
    private static final Chord[] augmented = {aug};
    
    private static final Chord[][] qualities = {major, minor, diminished, augmented};
    
    private static final String[] intervalNames = {"minor second", "major second", "minor third", "major third", "perfect fourth", "tritone", "perfect fifth", "minor sixth", "major sixth", "minor seventh", "major seventh", "perfect octave"};
    private static final String[] lengthNames = {" Triad"," Tetrad"," Pentad"," Hexad"};
    private static final String[] positionNames = {" Root Position", " First Inversion", " Second Inversion", " Third Inversion"};
    
    private int numAdded;
    private String[] notes;
    private int[] libLocations;
    private int[] intervals;
    private int position;
    private String rootNote;
    public MyChord(int quant)
    {
        notes = new String[quant];
        libLocations = new int[quant];
        intervals = new int[quant - 1];
        numAdded = 0;
        position = -1;
        rootNote = "root not found";
    }
    
    public void addNote(String n){
        notes[numAdded] = n;
        numAdded++;
    }
    
    public String[] getNotes(){
        return notes;
    }
    
    public int[] getLibLocations(){
        return libLocations;
    }
    
    public int[] getIntervals(){
        return intervals;
    }
    
    public String[] getIntervalNames(){
        return intervalNames;
    }
    
    public void renameEnharmonics()
    {
        for(int i = 0; i < notes.length; i++)
        {
            if(notes[i].equals("B#"))
                notes[i] = "C";
            else if(notes[i].equals("C#"))
                notes[i] = "Db";
            else if(notes[i].equals("D#"))
                notes[i] = "Eb";
            else if(notes[i].equals("Fb"))
                notes[i] = "E";
            else if(notes[i].equals("E#"))
                notes[i] = "F";
            else if(notes[i].equals("F#"))
                notes[i] = "Gb";
            else if(notes[i].equals("G#"))
                notes[i] = "Ab";
            else if(notes[i].equals("A#"))
                notes[i] = "Bb";
            else if(notes[i].equals("Cb"))
                notes[i] = "B";
        }
    }
    
    public void fillIntervals()
    {
        int prevNoteLocation = 0; 
        for(int i = 0; i < libLocations.length; i++){
            libLocations[i] = findLibraryIndex(i, prevNoteLocation);
            prevNoteLocation = libLocations[i];
        }
        
        for(int i = 0; i < intervals.length; i++)
            intervals[i] = libLocations[i + 1] - libLocations[i];
    }
    
    private int findLibraryIndex(int ind, int start)
    {
        int libInd = start;
        boolean found = false;
        if(start > 11){
            start %= 12;
        }
        int i = start;
        while(!found){
            if(i == library.length)
               i = 0;
            if(library[i].equals(notes[ind]))
               found = true;
            else{
                libInd++;
                i++;
            }
        }
        return libInd;
    }
    
    public String identifyChord()
    {   
        for(int q = 0; q < qualities.length; q++)
            for(int t = 0; t < qualities[q].length; t++)
                if(equals(qualities[q][t]))
                    return rootNote + qualities[q][t].getName() + lengthNames[notes.length - 3] + positionNames[position];
        
        return "chord not found";
    }
   
    private boolean equals(Chord type)
    {
        int typeInd = 0;
        int[] compare = new int[intervals.length];
        for(int i = 0; i < type.getFormula().length; i++){
            typeInd = i;
            for(int x = 0; x < compare.length; x++){
                if(x > 0)
                    typeInd++;
                if(typeInd == type.getFormula().length)
                    typeInd = 0;
                compare[x] = type.getFormula()[typeInd];
            }
            for(int y = 0; y < intervals.length; y++){
                if(intervals[y] != compare[y])
                    break;
                if(y == intervals.length - 1){
                    position = i;
                    if(i == 0)
                        rootNote = notes[i];
                    else
                        rootNote = notes[type.getFormula().length - i];
                }
            }
            if(position != -1)
                break;
        }
        
        if(position == -1)
            return false;
        return true;
    }
    
    public String toString()
    {
        String printNotes = "";
        for(String n : notes)
            printNotes += n + " ";
        return printNotes;
    }
}
