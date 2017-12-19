package Setup;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Class that reads the highscores.
 * @author Barbara, Blazej, Jordan, Samuel, Stijn, Yvar
 * @version 1.2
 */
public class ScoreReader {

	private final int numberOfScores = 10;

    /**
     * Gets the scores of different players.
     * @return scores of different players
     */
    public int[] getScores(){
        String[] data = readData();
        int[] scores = new int[numberOfScores];
        for(int x = 0; x < data.length; x+=3){
            if(data[x + 1] != null){
                scores[x/3] = Integer.parseInt(data[x + 2]);
            }    
        }
        return scores;
    }

    /**
     * Get names of different players.
     * @return name of different players
     */
    public String[] getNames(){
        String[] data = readData();
        String[] names = new String[numberOfScores];
        for(int x = 0; x < data.length; x+=3){
            if(data[x + 1] != null){
                names[x/3] = data[x + 1];
            }    
        }
        return names;
    }

    /**
     * Update the file with the score.
     * @param score score of a certain player
     * @param playerName name of a certain player
     */
    public void updateFile(int score, String playerName){
        //update file, we still need a window that will get players name, Stijn is working on it
        updateFile(score, playerName , getScores(), getNames());
    }

    /**
     * Read highscore file.
     * @return returns the data displayed in highscore window
     */
    public String[] readData(){
        String[] data = new String[numberOfScores*3];
        //try to read the file
        try{
            FileReader scoreFile = new FileReader("highscore.txt");
            Scanner in = new Scanner(scoreFile);
            int counter = 0;
            //while loops gets all data from it, in.next() will return Strings
            //example data[0] = 1: , data[1] = Mike; data[2] = 300;, data[3] = 2:; data[4] = John; data[5] = 200;
            while(counter < data.length) {
            //here we get all the top scores we want to look at
                data[counter] = in.next();
                counter++;
            }
            scoreFile.close();
        }
        catch(Exception e){
            //do nothing if there is no file, returns an empty array
        }
        return data;
    }
    //not sure if we need it, but it checks if the same name has the same score

    /**
     * Checks if a score is already there.
     * @param inputScore
     * @param inputName
     * @param outputScores
     * @param outputNames
     * @return
     */
    public boolean checkIfExisting(int inputScore, String inputName, int[] outputScores, String[] outputNames){
        for(int i = 0; i < outputScores.length; i++){
            if(outputNames[i]==null){
                if(inputScore == outputScores[i] && inputName.equals(outputNames[i])){
                    return true;
                }
            }

        }
        return false;
    }

    /**
     * Update the file.
     * @param inputScore
     * @param inputName
     * @param outputScores
     * @param outputNames
     */
    public void updateFile(int inputScore, String inputName, int[] outputScores, String[] outputNames ){
        //check if the score is a highscore
        if(inputScore > outputScores[outputScores.length-1] && !checkIfExisting(inputScore, inputName, outputScores, outputNames)){
            //add it to the correct position in the array
            outputScores[outputScores.length-1] = inputScore;
            outputNames[outputNames.length-1] = inputName;
            for(int i = outputScores.length-1; i >= 1; i--)
            {
              while(outputScores[i]>outputScores[i-1])
              {
                outputScores[i] = outputScores[i-1];
                outputScores[i-1] = inputScore;
                outputNames[i] = outputNames[i-1];
                outputNames[i-1] = inputName;
              }
            }
        }
        //add the new arrays to the new file
        try{
            //createfile
            PrintWriter out = new PrintWriter("highscore.txt");
            for (int j = 0; j < outputScores.length; j++)
            {

              out.println((j+1) + ": " + outputNames[j] + " " + outputScores[j]);

            }
            out.close();
        }
        catch(Exception e){
        }
    }
}    


	    
