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
        submitButton = new JButton("Submit");
        cancelButton = new JButton("Cancel");
        submitPanel.add(submitButton);
        submitPanel.add(cancelButton);
        mainframe.add(submitPanel);



        Question q = quiz.getCurrentQuestion();


        updateTexts(q);

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

                if (q.isCorrect(selectedAnswer)) {
                    questionCheck.setText("Richtig!");
                    quiz.nextQuestion();
                    updateTexts(quiz.getCurrentQuestion());

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

    private void updateTexts(Question q) {
        question.setText(q.getQuestionText());
        buttonA.setText(q.getPossibleAnswers()[0]);
        buttonB.setText(q.getPossibleAnswers()[1]);
        buttonC.setText(q.getPossibleAnswers()[2]);
        buttonD.setText(q.getPossibleAnswers()[3]);
    }

}