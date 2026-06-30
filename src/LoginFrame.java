import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {

    // Eingabefeld für den Benutzernamen
    private JTextField userField;

    // Button zum Starten des Quiz
    private JButton loginButton;

    public LoginFrame() {
        // Fenster Eigenschaften
        setTitle("Anmeldung");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 180);
        setLocationRelativeTo(null); // Zentrieren

        initLayout();//Oberfläche erstellen
        initLayoutListeners();//Button-Funktionen hinzufügen
        setVisible(true);
    }

    private void initLayout() {

        // Hauptpanel mit GridLayout
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        // Eingabefeld für den Benutzernamen
        JLabel userLabel = new JLabel("Benutzername:");
        panel.add(userLabel);

        // Button zum Starten des Quiz
        userField = new JTextField(20);
        panel.add(userField);

        // Button zum Starten des Quiz
        loginButton = new JButton("Anmelden");
        panel.add(loginButton);

        add(panel);
    }

    //ActionListener
    private void initLayoutListeners() {
        // Wird ausgeführt, wenn auf "Anmelden" geklickt wird
        loginButton.addActionListener(e -> {

            // Prüft, ob ein Benutzername eingegeben wurde
            if(userField.getText().trim().isEmpty()){
                JOptionPane.showMessageDialog(null,"Geben Sie bitte einen namen ein.");
            }
            else {
                // Erstellt ein neues Quiz
                Quiz quiz = new Quiz();
                //Neues Fenster MainFrame und dieses Fenster LoginFrame schließen
                new MainFrame(quiz);
                this.dispose();
            }
        });
    }
}