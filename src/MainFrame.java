import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainFrame extends JFrame{

    private boolean isButtonClicked = false;
    private Question q;
    public int errorCount;
    public int correctCount;
    private int point;

    JFrame mainframe = new JFrame();

    JLabel points;
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
    JProgressBar progressBar;



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
        //Buttons
        buttonA = new JButton("A");
        buttonB = new JButton("B");
        buttonC = new JButton("C"); // Auf Question umschreiben
        buttonD = new JButton("D");

        answerPanel = new JPanel(new GridLayout(2,2,20,20));
        answerPanel.setOpaque(false);

        answerPanel.add(buttonA);
        answerPanel.add(buttonB);
        answerPanel.add(buttonC);
        answerPanel.add(buttonD);

        Font buttonFont = new Font("Segoe UI", Font.BOLD, 22);

        buttonA.setFont(buttonFont);
        buttonB.setFont(buttonFont);
        buttonC.setFont(buttonFont);
        buttonD.setFont(buttonFont);

        //Punkte
        points = new JLabel("Punkte: 0");
        points.setFont(new Font("Segoe UI", Font.BOLD, 22));
        points.setForeground(new Color(255,215,0)); // Gold
        points.setHorizontalAlignment(SwingConstants.CENTER);


        mainframe.setContentPane(hintergrundPanel);

        mainframe.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(15,15,15,15);


        JLabel title = new JLabel("Millionenshow von Temu");
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

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;

        mainframe.add(answerPanel, gbc);

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
        gbc.gridwidth = 1;
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

        mainframe.add(points, gbc);
        gbc.gridx = 0;
        gbc.gridy = 6;

        mainframe.add(submitPanel, gbc);

        progressBar = new JProgressBar(0,quiz.getQuestionamount()); //immer noch zu verbessern aber schon bisschen schöner

        progressBar.setStringPainted(true);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        mainframe.add(progressBar, gbc);
        point = 0;

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
                point = 0;
                progressBar.setValue(0);
                points.setText("Punkte: " + point);

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
                        point++;
                        points.setText("Punkte: " + point);


                        if(quiz.nextQuestion()){

                        q = quiz.getCurrentQuestion();
                        updateTexts(q);
                        correctCount++;
                        progressBar.setValue(correctCount);
                        }
                        else{
                            progressBar.setValue(100);
                            JOptionPane.showMessageDialog(null,"Das Quiz wurde beendet!" +"\n"+ "Du hast "+ getCorrectCount() + " Fragen beantwortet!" +
                                    "\n" + "Du hast "+ getErrorCount() + " Fehler gemacht!"); //
                            quiz.resetQuiz();
                            correctCount = 1;
                            errorCount = 0;
                            point = 0;
                            progressBar.setValue(0);
                            points.setText("Punkte: " + point);

                        }

                        updateTexts(quiz.getCurrentQuestion());
                        isButtonClicked = false;

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