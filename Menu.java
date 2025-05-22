import javax.swing.*;
import java.awt.event.ActionEvent;

public class Menu extends JFrame {
    private JPanel panel1;
    private JTextField input1;
    private JTextField input2;
    private JButton ENTERButton;
    private JComboBox functionBox;
    private JLabel output;
    private double equals = 0;

    public Menu() {
        setContentPane(panel1);
        setBounds(100,100,500,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Zach's Calculator");

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Nimbus look and feel unavailable");
        }

        functionBox.addActionListener((ActionEvent e) -> {
            switch(functionBox.getSelectedIndex()) {
                case 0: case 1: case 2: case 3: case 4:
                    input2.setEditable(true);
                    break;
                default:
                    input2.setEditable(false);
                    break;
            }
        });

        ENTERButton.addActionListener((ActionEvent e) -> {
            try {
                Double.parseDouble(input1.getText()); // Check if input is numerical
                output.setText(output.getText() + input1.getText());

                switch(functionBox.getSelectedIndex()) {
                    case 0:
                        Double.parseDouble(input2.getText()); // Check if input is numerical
                        output.setText(input1.getText() + " + " + input2.getText() + " = ");
                        equals = Double.parseDouble(input1.getText()) + Double.parseDouble(input2.getText());
                        break;
                    case 1:
                        Double.parseDouble(input2.getText()); // Check if input is numerical
                        output.setText(input1.getText() + " - " + input2.getText() + " = ");
                        equals = Double.parseDouble(input1.getText()) - Double.parseDouble(input2.getText());
                        break;
                    case 2:
                        Double.parseDouble(input2.getText()); // Check if input is numerical
                        output.setText(input1.getText() + " × " + input2.getText() + " = ");
                        equals = Double.parseDouble(input1.getText()) * Double.parseDouble(input2.getText());
                        break;
                    case 3:
                        Double.parseDouble(input2.getText()); // Check if input is numerical
                        output.setText(input1.getText() + " ÷ " + input2.getText() + " = ");
                        equals = Double.parseDouble(input1.getText()) / Double.parseDouble(input2.getText());
                        break;
                    case 4:
                        Double.parseDouble(input2.getText()); // Check if input is numerical
                        output.setText(input1.getText() + "^" + input2.getText() + " = ");
                        equals = Math.pow(Double.parseDouble(input1.getText()), Double.parseDouble(input2.getText()));
                        break;
                    case 5:
                        output.setText("sin(" + input1.getText() + ") = ");
                        equals = Math.sin(Double.parseDouble(input1.getText()) * Math.PI/180);
                        break;
                    case 6:
                        output.setText("cos(" + input1.getText() + ") = ");
                        equals = Math.cos(Double.parseDouble(input1.getText()) * Math.PI/180);
                        break;
                    case 7:
                        output.setText("tan(" + input1.getText() + ") = ");
                        equals = Math.tan(Double.parseDouble(input1.getText()) * Math.PI/180);
                        break;
                    case 8:
                        output.setText("arcsin(" + input1.getText() + ") = ");
                        equals = Math.asin(Double.parseDouble(input1.getText())) * 180/Math.PI;
                        break;
                    case 9:
                        output.setText("arccos(" + input1.getText() + ") = ");
                        equals = Math.acos(Double.parseDouble(input1.getText())) * 180/Math.PI;
                        break;
                    case 10:
                        output.setText("arctan(" + input1.getText() + ") = ");
                        equals = Math.atan(Double.parseDouble(input1.getText())) * 180/Math.PI;
                        break;
                    default:
                        output.setText("Error");
                        break;
                }
                // Round to ten decimal points
                equals *= Math.pow(10, 10);
                equals = Math.round(equals);
                equals /= Math.pow(10, 10);

                // Output final calculation
                output.setText(output.getText() + equals);
                input1.setText(String.valueOf(equals));
                input2.setText("");

            } catch(NumberFormatException f) {
                output.setText("Error: Only numbers allowed!");
            }
        });

        setVisible(false);
    }
}
