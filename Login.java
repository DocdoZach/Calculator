import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Login extends JFrame {
    private JPanel panel1;
    private JTextField username;
    private JPasswordField password;
    private JButton loginButton;
    private JLabel notice;
    private int attempts = 4;
    List<List<String>> credentials = new ArrayList<>();
    List<String> tempCredentials;
    int counter = 0;
    String line, tempUsername = "", tempPassword = "";
    Menu menu;

    public Login(Menu menu) {
        this.menu = menu;

        // Read in data from the text file
        String path = System.getProperty("user.dir");
        try{
            FileReader fileReader = new FileReader("credentials.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                counter++;
                if(counter % 2 == 0) {
                    tempPassword = line;
                    tempCredentials = new ArrayList<>();
                    tempCredentials.add(tempUsername);
                    tempCredentials.add(tempPassword);
                    credentials.add(tempCredentials);
                } else {
                    tempUsername = line;
                }
            }

            bufferedReader.close();
            fileReader.close();
        }
        catch (FileNotFoundException f){
            System.out.println("File not found");
        }
        catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

        setContentPane(panel1);
        setBounds(100,100,300,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Zach's Calculator");

        loginButton.addActionListener((ActionEvent e) -> {
            login(menu);
        });

        setVisible(true);
        setFocusable(true);
    }

    public void login(Menu menu) {
        // Make credentials pair from input
        List<String> input = new ArrayList<>();
        input.add(username.getText());

        String completePassword = ""; // getPassword converted from char[] to String
        for(Character digit : password.getPassword()) {
            completePassword += digit;
        }
        input.add(completePassword);

        // Compare to credentials in text file
        for(List<String> pair : credentials) {
            if(input.equals(pair)) {
                setVisible(false);
                menu.setVisible(true);
                System.out.println("Successfully logged in as " + username.getText());
                break;
            }
        }

        attempts--;
        notice.setText("Invalid credentials. " + attempts + " attempts left.");
        if(attempts == 0) {
            JOptionPane.showMessageDialog(
                    null,
                    "You have ran out of login attempts.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            System.exit(0);
        }
    }
}
