
/**
 * The Data class stores the term, sentence and confidence score in a single object.
 * Author: Lindokuhle Mdlalose
 * Date: 06 March 2024
 */

public class Data {
    private String term;
    private String sentence;
    private double confidenceScore;

    /**
     * Constructs a new Data object with the provided term, sentence and confidenceScore.
     * @param term The term to be stored.
     * @param sentence The sentence to be stored.
     * @param confidenceScore The confidence score to be stored.
     */
    public Data(String term, String sentence, double confidenceScore){
        this.term = term;
        this.sentence = sentence;
        this.confidenceScore = confidenceScore;
    }

    /**
     * Retrieves the term stored in the data object.
     * @return The term stored in the data object.
     */
    public String getTerm(){
        return term;
    }
    
    /**
     * Retrieves the sentence stored in the data object.
     * @return The sentence stored in the data object.
     */
    public String getSentence(){
        return sentence;
    }

    /**
     * Retrieves the confidence score stored in the data object.
     * @return The confidence score stored in the data object.
     */
    public double getConfidenceScore(){
        return confidenceScore;
    }

    /**
     * Sets the term of the Data object to the provided value.
     * @param term The new term to be set.
     */
    public void setTerm(String term){
        this.term = term;
    }

    /**
     * Sets the sentence of the Data object to the provided value.
     * @param sentence The new sentence to be set.
     */
    public void setSentence(String sentence){
        this.sentence = sentence;
    }

    /**
     * Sets the confidence score of the Data object to the provided value.
     * @param confidenceScore The new confidence score to be set.
     */
    public void setConfidenceScore(double confidenceScore){
        this.confidenceScore = confidenceScore;
    }

    /**returns required text as a String*/
    public String toString() {
        return "Term: " + term + ", Sentence: " + sentence + ", Confidence Score: " + confidenceScore;
    }
}
