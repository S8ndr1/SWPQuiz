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
    //eventuell später implementieren:
    //JLabel progressBar;



    //-1 hat nichts auszusagen, wird später verändert
    private int selectedAnswer = -1;



    public MainFrame(Quiz quiz){

        JPanel hintergrundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                // Verlauf von oben nach unten (Pink zu Blau)
                GradientPaint verlauf = new GradientPaint(
                        0,  0, Color.PINK,
                        getWidth() + 500, getHeight() + 500, Color.BLUE
                );

                g2d.setPaint(verlauf);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        mainframe.setContentPane(hintergrundPanel);

        mainframe.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(15,15,15,15);

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

        buttonA = new JButton("A");
        buttonB = new JButton("B");
        buttonC = new JButton("C"); // Auf Question umschreiben
        buttonD = new JButton("D");

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

//Titel
        title.setFont(new Font("Segoe UI", Font.BOLD, 42));
        title.setForeground(new Color(255, 215, 0));
        title.setHorizontalAlignment(SwingConstants.CENTER);

//Frage
        question.setFont(new Font("Segoe UI", Font.BOLD, 28));
        question.setForeground(Color.WHITE);
        question.setHorizontalAlignment(SwingConstants.CENTER);

        JButton[] buttons = {buttonA, buttonB, buttonC, buttonD};

        questionCheck = new JLabel("");
        questionCheck.setFont(new Font("Segoe UI", Font.BOLD, 24));
        questionCheck.setForeground(Color.WHITE);
        questionCheck.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.weighty = 0.1;

        mainframe.add(questionCheck, gbc);

//Bestätigen und Neustarten
        submitPanel = new JPanel();
        submitPanel.setOpaque(false);
        submitButton = new JButton("Bestätigen");
        cancelButton = new JButton("Neustarten");
        submitButton.setBackground(new Color(39, 174, 96));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        submitButton.setFocusPainted(false);

        cancelButton.setBackground(new Color(192, 57, 43));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        cancelButton.setFocusPainted(false);
        submitPanel.add(submitButton);
        submitPanel.add(cancelButton);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.weighty = 0.1;

        mainframe.add(submitPanel, gbc);



        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                quiz.resetQuiz();                       // zurück zu Frage 1

                q = quiz.getCurrentQuestion();

                selectedAnswer = -1;                    // Auswahl zurücksetzen
                questionCheck.setText("");              // Text löschen

                isButtonClicked = false;
                updateTexts(q); // erste Frage anzeigen
                setDefaultButtonColor();
                errorCount = 0;
                correctCount = 1;

            }
        });


        q = quiz.getCurrentQuestion();
        updateTexts(q);

        correctCount = 1; // 1 ist bissl pfusch
        errorCount = 0;


        setDefaultButtonColor();




        buttonA.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                setDefaultButtonColor();
                isButtonClicked = true;
                selectedAnswer = 0;
                buttonA.setBackground(Color.ORANGE);

            }
        });

        buttonB.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                setDefaultButtonColor();
                selectedAnswer = 1;
                buttonB.setBackground(Color.ORANGE);
                isButtonClicked = true;
            }
        });

        buttonC.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                setDefaultButtonColor();
                selectedAnswer = 2;
                buttonC.setBackground(Color.ORANGE);
                isButtonClicked = true;
            }
        });

        buttonD.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                setDefaultButtonColor();
                selectedAnswer = 3;
                buttonD.setBackground(Color.ORANGE);
                isButtonClicked = true;
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
                            JOptionPane.showMessageDialog(null,"Das Quiz wurde beendet!" +"\n"+ "Du hast "+ getCorrectCount() + " Fragen beantwortet!" +
                                    "\n" + "Du hast "+ getErrorCount() + " Fehler gemacht!"); //
                            quiz.resetQuiz();
                            correctCount = 1;
                            errorCount = 0;

                        }

                        updateTexts(quiz.getCurrentQuestion());
                        isButtonClicked = false;
                        correctCount++;
                    } else {
                        questionCheck.setText("Falsch!");
                        questionCheck.setForeground(Color.RED);
                        isButtonClicked = false;
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

    public int getCorrectCount(){
        return correctCount;
    }

    public int getErrorCount(){
        return errorCount;
    }

}