

public class Question {


    private String questionText;
    private String[] possibleAnswers;
    private int correctAnswer;

    public Question(String questionText, String[] answers, int correctAnswer) {
        this.questionText = questionText;
        this.possibleAnswers = answers;
        this.correctAnswer = correctAnswer;
    }

    // getter Methode um die zu Frage anzeigen zu lassen
    public String getQuestionText() {
        return questionText;
    }


    // getter Methode um die möglichen Antworten den Buttons zuzuweißen
    public String[] getPossibleAnswers() {
        return possibleAnswers;
    }


    // Prüft ob die ausgewählte Antwort richtig ist (siehe MainFrame)
    public boolean isCorrect(int answerIndex) {
        return answerIndex == correctAnswer;

    }
}