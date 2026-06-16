import java.util.ArrayList;

public class Quiz {

    private ArrayList<Question> questions;
    private int currentQuestion;

    public Quiz() {

        questions = new ArrayList<>();

        questions.add(
                new Question("Welches Land hat die längste Küstenlinie der Welt?", new String[]{"Russland", "Australien", "Kanada", "USA"}, 2)
        );
        questions.add(
                new Question("Welches chemische Element trägt das Symbol „W“?", new String[]{"Wolfram", "Wasserstoff", "Wismut", "Wolkenium"}, 0)
        );
        questions.add(new Question("Welcher Berg ist der höchste Europas, wenn man den Kaukasus zu Europa zählt?", new String[]{"Mont Blanc", "Elbrus", "Matterhorn", "Großglockner"}, 1));
        questions.add(new Question("Welcher Künstler malte die Decke der Sixtinischen Kapelle?", new String[]{"Leonardo da Vinci", "Raffael", "Michelangelo", "Donatello"}, 2));
        questions.add(new Question("Wie viele Knochen hat ein erwachsener Mensch normalerweise?", new String[]{"186", "206", "226", "246"}, 1));
        questions.add(new Question("Welcher dieser Flüsse mündet in das Schwarze Meer?", new String[]{"Donau", "Rhône", "Themse", "Loire"}, 0));
        questions.add(new Question("In welchem Jahr landeten die ersten Menschen auf dem Mond?", new String[]{"1967", "1968", "1969", "1970"}, 3));
        questions.add(new Question("Welcher Wissenschaftler formulierte die drei Bewegungsgesetze der klassischen Mechanik?", new String[]{"Albert Einstein", "Galileo Galilei", "Isaac Newton", "Johannes Kepler"}, 2));
        questions.add(new Question("Welche Sprache gehört nicht zur romanischen Sprachfamilie?", new String[]{"Portugiesisch", "Rumänisch", "Niederländisch", "Spanisch"}, 2));

        currentQuestion = 0;

    }
        public Question getCurrentQuestion () {
            return questions.get(currentQuestion);
        }

        public void nextQuestion () {
            if (currentQuestion < questions.size()-1) {
                currentQuestion++;
            }
        }

    public void resetQuiz() {
        currentQuestion = 0;
    }
}

