import com.univocity.parsers.common.processor.RowListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class Assignment {
    private static ReviewEntry[] reviews = new ReviewEntry[8544];
    String sentenceID = "1";
    int previousSentenceID = 1;
    int totalScore = 0;
    int totalPhrases = 0;
    public static String fileName = "C:\\Users\\jimal\\Desktop\\Assignment\\src\\movieReviews.tsv";

    public static void main(String[] args) {

        Assignment assignment = new Assignment();
        assignment.parseTsv(fileName);

        Scanner obj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter sentence ID");
        String sentenceIdInput = obj.nextLine();  // Read user input

        //Select the object from the reviews array with sentenceId as its position
        ReviewEntry selectedReview = reviews[Integer.parseInt(sentenceIdInput)];

        //Compute the average by dividing total score by total number of phrases per ID.
        int totalScore = selectedReview.totalScore / selectedReview.numberOfPhrases;

        System.out.println("Total score " + selectedReview.totalScore + " : phrases = " + selectedReview.numberOfPhrases);

        //Based on the average score, print feedback
        if (totalScore >= 0 && totalScore <= 2) {
            System.out.println("Sentence ID  " + sentenceIdInput + " has " + selectedReview.numberOfPhrases + " phrases with an average rating of " + totalScore + ". The overall sentiment is negative");
        } else if (totalScore > 2 && totalScore <= 3) {
            System.out.println("Sentence ID " + sentenceIdInput + " has " + selectedReview.numberOfPhrases + " phrases with an average rating of " + totalScore + ". The overall sentiment is neutral");
        } else if (totalScore > 3 && totalScore <= 4) {
            System.out.println("Sentence ID  " + sentenceIdInput + "has " + selectedReview.numberOfPhrases + " phrases with an average rating of " + totalScore + ". The overall sentiment is positive");
        }
    }

    public void parseTsv(String fileName) {
        TsvParserSettings settings = new TsvParserSettings();
        settings.getFormat().setLineSeparator("\\t");
        TsvParser parser = new TsvParser(settings);
        //Read the file and add each line into the list
        List<String[]> allRows = parser.parseAll(new File(fileName));

        for (int i = 1; i < allRows.size(); i++) {

            //get the value in the 2nd column which is the sentence ID
            sentenceID = Arrays.asList(allRows.get(i)).get(1);

            //Compare the IDs to know if the sentence ID has increased
            if (Integer.parseInt(sentenceID) > previousSentenceID) {
                //create a ReviewEntry object
                reviews[Integer.parseInt(sentenceID) - 1] = new ReviewEntry(
                        sentenceID,
                        totalScore,
                        totalPhrases
                );
                previousSentenceID = Integer.parseInt(sentenceID);
                totalScore = 0; //
                totalPhrases = 0;
            } else { //if the sentense Id has not increased
                totalPhrases++;//increment the number of phrases with the same sentence ID
                totalScore += Integer.parseInt(Arrays.asList(allRows.get(i)).get(3)); //Add the sentiment scores of phrases with same sentence ID
            }

        }
    }
}
