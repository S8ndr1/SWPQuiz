import java.util.ArrayList;
import java.util.Collections;

public class Quiz {

    // Speichert alle Fragen des Quiz
    private ArrayList<Question> questions;

    // Speichert die aktuelle Frage
    private int currentQuestion;

    public Quiz() {

        // Erstellt die Fragenliste
        questions = new ArrayList<>();

        // Fragen werden hinzugefügt

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
                new String[]{"1967", "1968", "1969", "1970"}, 2));

        questions.add(new Question("Welcher Wissenschaftler formulierte die drei Bewegungsgesetze der klassischen Mechanik?",
                new String[]{"Albert Einstein", "Isaac Newton", "Galileo Galilei", "Johannes Kepler"}, 1));

        questions.add(new Question("Welche Sprache gehört nicht zur romanischen Sprachfamilie?",
                new String[]{"Portugiesisch", "Rumänisch", "Niederländisch", "Spanisch"}, 2));

        questions.add(new Question("Welcher Planet ist der größte in unserem Sonnensystem?",
                new String[]{"Mars", "Saturn", "Jupiter", "Venus"}, 2));

        // Das Quiz beginnt bei der ersten Frage
        currentQuestion = 0;

        // Mischt die Reihenfolge der Fragen
        Collections.shuffle(questions);
    }

        // Gibt die aktuelle Frage zurück
        public Question getCurrentQuestion () {
            return questions.get(currentQuestion);
        }

        // Wechselt zur nächsten Frage
        public boolean nextQuestion () {

            // Solange noch Fragen vorhanden sind
            if (currentQuestion < questions.size() - 1) {
                currentQuestion++;
                return true;
            }
            else{
                // Keine weiteren Fragen vorhanden
                return false;
            }
        }

    //Quiz wieder auf Frage 1 zurückstellen
    public void resetQuiz() {
        currentQuestion = 0;
        Collections.shuffle(questions);
    }

    // Gibt die Anzahl aller Fragen zurück
    public int getQuestionamount(){
        return questions.size();
    }
}