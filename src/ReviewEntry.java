
public class ReviewEntry {

    public String sentenceID = null;
    public int totalScore = 0;
    public int numberOfPhrases = 0;

    public ReviewEntry() {
    }

    public ReviewEntry(String sentenceID, int totalScore, int numberOfPhrases) {
        this.totalScore = totalScore;
        this.sentenceID = sentenceID;
        this.numberOfPhrases = numberOfPhrases;
    }

    public String getSentenceID() {
        return sentenceID;
    }

    public void setSentenceID(String sentenceID) {
        this.sentenceID = sentenceID;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getNumberOfPhrases() {
        return numberOfPhrases;
    }

    public void setNumberOfPhrases(int numberOfPhrases) {
        this.numberOfPhrases = numberOfPhrases;
    }
}
