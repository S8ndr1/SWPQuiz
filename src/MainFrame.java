import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame{

    private boolean isButtonClicked = false;

    JFrame mainframe = new JFrame();

    JPanel answerPanel;
    JButton buttonA;
    JButton buttonB;
    JButton buttonC;
    JButton buttonD;
    JLabel question;
    JLabel questionCheck;
    JPanel submitPanel;
    JButton submitButton;
    JButton cancelButton;



    //-1 hat nichts auszusagen, wird später verändert
    private int selectedAnswer = -1;



    public MainFrame(Quiz quiz){

        GridLayout layout = new GridLayout(8,3);

        JLabel title = new JLabel("Joggl's Quiz");
        mainframe.add(title);

        question = new JLabel("Frage...");
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
        submitButton = new JButton("bestätigen");
        cancelButton = new JButton("abbrechen");
        submitPanel.add(submitButton);
        submitPanel.add(cancelButton);
        mainframe.add(submitPanel);


        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                quiz.resetQuiz();                       // zurück zu Frage 1
                selectedAnswer = -1;                    // Auswahl zurücksetzen
                questionCheck.setText("");              // Text löschen
                updateTexts(quiz.getCurrentQuestion()); // erste Frage anzeigen

                buttonA.setEnabled(true);
                buttonB.setEnabled(true);
                buttonC.setEnabled(true);
                buttonD.setEnabled(true);

                buttonA.setBackground(Color.lightGray);
                buttonB.setBackground(Color.lightGray);
                buttonC.setBackground(Color.lightGray);
                buttonD.setBackground(Color.lightGray);
            }
        });

        Question q = quiz.getCurrentQuestion();


        updateTexts(q);

        buttonA.setBackground(Color.lightGray);
        buttonB.setBackground(Color.lightGray);
        buttonC.setBackground(Color.lightGray);
        buttonD.setBackground(Color.lightGray);


        buttonA.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedAnswer = 0;
                buttonA.setBackground(Color.ORANGE);
                isButtonClicked = true;
                buttonB.setEnabled(false);
                buttonC.setEnabled(false);
                buttonD.setEnabled(false);

            }
        });

        buttonB.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedAnswer = 1;
                buttonB.setBackground(Color.ORANGE);
                isButtonClicked = true;

                buttonA.setEnabled(false);
                buttonC.setEnabled(false);
                buttonD.setEnabled(false);

            }
        });

        buttonC.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedAnswer = 2;
                buttonC.setBackground(Color.ORANGE);
                isButtonClicked = true;

                buttonA.setEnabled(false);
                buttonB.setEnabled(false);
                buttonD.setEnabled(false);

            }
        });

        buttonD.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedAnswer = 3;
                buttonD.setBackground(Color.ORANGE);
                isButtonClicked = true;

                buttonA.setEnabled(false);
                buttonB.setEnabled(false);
                buttonC.setEnabled(false);

            }
        });


        // Hier wird ausgegebn ob die Antwort richtig oder falsch war.
        submitButton.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

                if(isButtonClicked) {

                    if (q.isCorrect(selectedAnswer)) {
                        questionCheck.setText("Richtig!");
                        questionCheck.setForeground(Color.GREEN);
                        quiz.nextQuestion();
                        updateTexts(quiz.getCurrentQuestion());
                        isButtonClicked = false;

                        buttonA.setEnabled(true);
                        buttonB.setEnabled(true);
                        buttonC.setEnabled(true);
                        buttonD.setEnabled(true);

                    } else {
                        questionCheck.setText("Falsch!");
                        questionCheck.setForeground(Color.RED);
                        isButtonClicked = false;

                        buttonA.setEnabled(true);
                        buttonB.setEnabled(true);
                        buttonC.setEnabled(true);
                        buttonD.setEnabled(true);
                    }

                    }
                else{
                    JOptionPane.showMessageDialog(null,"Es wurde keine Antwortmöglichkeit gewählt");
                }

                buttonA.setBackground(Color.lightGray);
                buttonB.setBackground(Color.lightGray);
                buttonC.setBackground(Color.lightGray);
                buttonD.setBackground(Color.lightGray);

            }
        });



        mainframe.setLayout(layout);
        mainframe.setSize(800,600);
        mainframe.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainframe.setVisible(true);
    }

    private void updateTexts(Question q) {
        question.setText(q.getQuestionText());
        buttonA.setText(q.getPossibleAnswers()[0]);
        buttonB.setText(q.getPossibleAnswers()[1]);
        buttonC.setText(q.getPossibleAnswers()[2]);
        buttonD.setText(q.getPossibleAnswers()[3]);
    }

}