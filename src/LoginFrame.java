import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField userField;
    private JButton loginButton;

    public LoginFrame() {
        // Fenster Eigenschaften
        setTitle("Anmeldung");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 180);
        setLocationRelativeTo(null); // Zentrieren


        initLayout();//Geladen
        initLayoutListeners();//Funktion
        setVisible(true);
    }

    private void initLayout() {
        //Style
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        //Label
        JLabel userLabel = new JLabel("Benutzername:");
        panel.add(userLabel);

        //Eingabefeld
        userField = new JTextField(20);
        panel.add(userField);

        //Button
        loginButton = new JButton("Anmelden");
        panel.add(loginButton);

        add(panel);
    }
    //ActionListener
    private void initLayoutListeners() {
        loginButton.addActionListener(e -> {

            Quiz quiz = new Quiz();
            //Neues Fenster MainFrame und dieses Fenster LoginFrame schließen
            new MainFrame(quiz);
            this.dispose();
        });
    }
}