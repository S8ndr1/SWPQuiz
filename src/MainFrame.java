import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame{

    private boolean isButtonClicked = false;
    private Question q;
    public int errorCount;
    public int correctCount;

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

        mainframe.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.BOTH;

        JLabel title = new JLabel("Joggl's Quiz");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.weighty = 0.1;

        mainframe.add(title, gbc);

        question = new JLabel("Frage...");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;

        mainframe.add(question, gbc);

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

        //A
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.weighty = 1;

        mainframe.add(buttonA, gbc);
        //B
        gbc.gridx = 1;
        gbc.gridy = 2;

        mainframe.add(buttonB, gbc);
        //C
        gbc.gridx = 0;
        gbc.gridy = 3;

        mainframe.add(buttonC, gbc);
        //D
        gbc.gridx = 1;
        gbc.gridy = 3;

        mainframe.add(buttonD, gbc);

        //Farben
        buttonA.setBackground(new Color(1, 51, 122));
        buttonB.setBackground(new Color(1, 51, 122));
        buttonC.setBackground(new Color(1, 51, 122));
        buttonD.setBackground(new Color(1, 51, 122));

        buttonA.setForeground(Color.WHITE);
        buttonB.setForeground(Color.WHITE);
        buttonC.setForeground(Color.WHITE);
        buttonD.setForeground(Color.WHITE);

        //Hintergrund
        mainframe.getContentPane().setBackground(
                new Color(25, 25, 80)
        );

        //Titel
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setForeground(new Color(255,215,0));

        //Frage
        question.setFont(new Font("Arial", Font.BOLD, 26));
        question.setForeground(Color.WHITE);

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
                setDefaultButtonColor();

            }
        });

        q = quiz.getCurrentQuestion();
        updateTexts(q);

        correctCount = 0;
        errorCount = 0;



        setDefaultButtonColor();


        buttonA.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                isButtonClicked = true;
                selectedAnswer = 0;
                buttonA.setBackground(Color.ORANGE);
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
                setButtonUnenabled();
            }
        });

        buttonC.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedAnswer = 2;
                buttonC.setBackground(Color.ORANGE);
                isButtonClicked = true;
                setButtonUnenabled();

            }
        });

        buttonD.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedAnswer = 3;
                buttonD.setBackground(Color.ORANGE);
                isButtonClicked = true;
                setButtonUnenabled();

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


                        if(quiz.nextQuestion()){

                        q = quiz.getCurrentQuestion();
                        updateTexts(q);}
                        else{
                            JOptionPane.showMessageDialog(null,"Das Quiz wurde beendet!" +"\n"+ "Du hast "+ getCorrectCount() + " Fragen richtig beantwortet!" +
                                    "\n" + "Du hast "+ getErrorCount() + " Fehler gemacht!");

                        }

                        updateTexts(quiz.getCurrentQuestion());
                        isButtonClicked = false;
                        setButtonEnabled();
                        correctCount++;
                    } else {
                        questionCheck.setText("Falsch!");
                        questionCheck.setForeground(Color.RED);
                        isButtonClicked = false;
                        setButtonEnabled();
                        errorCount++;
                    }

                    }
                else{
                    JOptionPane.showMessageDialog(null,"Es wurde keine Antwortmöglichkeit gewählt");
                }
                setDefaultButtonColor();

            }
        });


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

    private void setDefaultButtonColor(){
        buttonA.setBackground(Color.lightGray);
        buttonB.setBackground(Color.lightGray);
        buttonC.setBackground(Color.lightGray);
        buttonD.setBackground(Color.lightGray);
    }

    private void setButtonEnabled(){
        buttonA.setEnabled(true);
        buttonB.setEnabled(true);
        buttonC.setEnabled(true);
        buttonD.setEnabled(true);
    }

    private void setButtonUnenabled(){
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);
    }

    public int getCorrectCount(){
        return correctCount;
    }

    public int getErrorCount(){
        return errorCount;
    }

}