import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

public class Quiz {

    private ArrayList<Question> questions;
    private int currentQuestion;

    public Quiz() {
        questions = new ArrayList<>();

        questions.add(new Question("Welches Land hat die längste Küstenlinie der Welt?",
                new String[]{"Russland", "Australien", "Kanada", "USA"}, 2));

        questions.add(new Question("Welches chemische Element trägt das Symbol „W“?",
                new String[]{"Wolfram", "Wasserstoff", "Wismut", "Wolkenium"}, 0));

        questions.add(new Question("Welcher Berg ist der höchste Europas, wenn man den Kaukasus zu Europa zählt?",
                new String[]{"Mont Blanc", "Elbrus", "Matterhorn", "Großglockner"}, 1));

        questions.add(new Question("Welcher Künstler malte die Decke der Sixtinischen Kapelle?",
                new String[]{"Michelangelo", "Raffael", "Leonardo da Vinci", "Donatello"}, 0));

        questions.add(new Question("Wie viele Knochen hat ein erwachsener Mensch normalerweise?",
                new String[]{"186", "226", "206", "246"}, 2));

        questions.add(new Question("Welcher dieser Flüsse mündet in das Schwarze Meer?",
                new String[]{"Loire", "Rhône", "Themse", "Donau"}, 3));

        questions.add(new Question("In welchem Jahr landeten die ersten Menschen auf dem Mond?",
                new String[]{"1967", "1968", "1969", "1970"}, 3));

        questions.add(new Question("Welcher Wissenschaftler formulierte die drei Bewegungsgesetze der klassischen Mechanik?",
                new String[]{"Albert Einstein", "Isaac Newton", "Galileo Galilei", "Johannes Kepler"}, 1));

        questions.add(new Question("Welche Sprache gehört nicht zur romanischen Sprachfamilie?",
                new String[]{"Portugiesisch", "Rumänisch", "Niederländisch", "Spanisch"}, 2));


        currentQuestion = 0;

        Collections.shuffle(questions);

    }
        public Question getCurrentQuestion () {
            return questions.get(currentQuestion);
        }

        public boolean nextQuestion () {
            if (currentQuestion < questions.size() - 1) {
                currentQuestion++;
                return true;
            }else{
                return false;
            }
        }

    public void resetQuiz() {
        currentQuestion = 0;
        Collections.shuffle(questions);
    }
}

