import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame{
    JFrame mainframe = new JFrame();

    JPanel answerPanel;
    JButton buttonA;
    JButton buttonB;
    JButton buttonC;
    JButton buttonD;
    JLabel questionCheck;
    JPanel submitPanel;
    JButton submitButton;
    JButton cancelButton;

    //-1 hat nichts auszusagen, wird später verändert
    private int selectedAnswer = -1;


    MainFrame(){

        GridLayout layout = new GridLayout(8,3);

        JLabel title = new JLabel("Joggl's Quiz");
        mainframe.add(title);

        JLabel question = new JLabel("Frage...");
        mainframe.add(question);

        answerPanel = new JPanel();
        buttonA = new JButton("A");
        buttonB = new JButton("B");
        buttonC = new JButton("C"); // Auf Question umschreiben
        buttonD = new JButton("D");
        answerPanel.add(buttonA);
        answerPanel.add(buttonB);
        answerPanel.add(buttonC);
        answerPanel.add(buttonD);
        mainframe.add(answerPanel);

        questionCheck = new JLabel("Richtig / Falsch");
        mainframe.add(questionCheck);

        submitPanel = new JPanel();
        submitButton = new JButton("Submit");
        cancelButton = new JButton("Cancel");
        submitPanel.add(submitButton);
        submitPanel.add(cancelButton);
        mainframe.add(submitPanel);


        // Question-Konstruktor wird aufgerufen, mit der passenden Frage, Antwortmöglichkeiten
        // und der korrekten Antwort
        Question q1 = new Question(
                "Was ist 2+2?",
                new String[]{"67","4","69","42"}, 1);

        // setzt den Text der ersten Frage
        question.setText(q1.getQuestionText());

        // Hier werden den buttons die jeweiligen Antwortmöglichkeiten mitgegeben
        buttonA.setText(q1.getPossibleAnswers()[0]);
        buttonB.setText(q1.getPossibleAnswers()[1]);
        buttonC.setText(q1.getPossibleAnswers()[2]);
        buttonD.setText(q1.getPossibleAnswers()[3]);


        // Dies ist nötig, um im weiteren Verlaufe die Antwort auf ihre Richtigkeit zu prüfen.
        // (durch selectedAnswer)
        // Klickt man einen Button an, ändert sich selectedAnswer auf den jeweiligen Wert
        buttonA.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedAnswer = 0;
            }
        });

        buttonB.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedAnswer = 1;
            }
        });

        buttonC.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedAnswer = 2;
            }
        });

        buttonD.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedAnswer = 3;
            }
        });

        // Hier wird ausgegebn ob die Antwort richtig oder falsch war.
        submitButton.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

                if (q1.isCorrect(selectedAnswer)) {
                    questionCheck.setText("Richtig!");
                }else{
                    questionCheck.setText("Falsch!");
                }

            }
        });

        mainframe.setLayout(layout);
        mainframe.setSize(800,600);
        mainframe.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainframe.setVisible(true);
    }

}