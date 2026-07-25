import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudentRegistration extends JFrame implements ActionListener {

    JLabel l1, l2, l3, l4, l5;
    JTextField t1, t2;
    JComboBox<String> branch;
    JRadioButton male, female;
    JCheckBox java, python;
    JButton submit, clear;
    JTextArea result;
    ButtonGroup bg;

    public StudentRegistration() {

        setTitle("Student Registration Form");
        setSize(500, 550);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        l1 = new JLabel("USN");
        l1.setBounds(50, 40, 100, 25);
        add(l1);

        t1 = new JTextField();
        t1.setBounds(170, 40, 200, 25);
        add(t1);

        l2 = new JLabel("Name");
        l2.setBounds(50, 80, 100, 25);
        add(l2);

        t2 = new JTextField();
        t2.setBounds(170, 80, 200, 25);
        add(t2);

        l3 = new JLabel("Branch");
        l3.setBounds(50, 120, 100, 25);
        add(l3);

        String branches[] = {
                "Computer Science",
                "Information Technology",
                "ECE",
                "EEE",
                "Mechanical"
        };

        branch = new JComboBox<>(branches);
        branch.setBounds(170, 120, 200, 25);
        add(branch);

        l4 = new JLabel("Gender");
        l4.setBounds(50, 160, 100, 25);
        add(l4);

        male = new JRadioButton("Male");
        male.setBounds(170, 160, 70, 25);

        female = new JRadioButton("Female");
        female.setBounds(250, 160, 80, 25);

        bg = new ButtonGroup();
        bg.add(male);
        bg.add(female);

        add(male);
        add(female);

        l5 = new JLabel("Skills");
        l5.setBounds(50, 200, 100, 25);
        add(l5);

        java = new JCheckBox("Java");
        java.setBounds(170, 200, 80, 25);

        python = new JCheckBox("Python");
        python.setBounds(260, 200, 80, 25);

        add(java);
        add(python);

        submit = new JButton("Submit");
        submit.setBounds(120, 250, 100, 30);
        add(submit);

        clear = new JButton("Clear");
        clear.setBounds(250, 250, 100, 30);
        add(clear);

        result = new JTextArea();
        result.setBounds(50, 300, 380, 170);
        result.setEditable(false);
        add(result);

        submit.addActionListener(this);
        clear.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == submit) {

            if (t1.getText().trim().isEmpty() || t2.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(this,
                        "USN and Name are mandatory!");

                return;
            }

            String gender = "";

            if (male.isSelected())
                gender = "Male";
            else if (female.isSelected())
                gender = "Female";
            else
                gender = "Not Selected";

            String skills = "";

            if (java.isSelected())
                skills += "Java ";

            if (python.isSelected())
                skills += "Python";

            result.setText(
                    "----- Student Details -----\n\n" +
                    "USN : " + t1.getText() + "\n" +
                    "Name : " + t2.getText() + "\n" +
                    "Branch : " + branch.getSelectedItem() + "\n" +
                    "Gender : " + gender + "\n" +
                    "Skills : " + skills
            );
        }

        if (e.getSource() == clear) {

            t1.setText("");
            t2.setText("");
            branch.setSelectedIndex(0);
            bg.clearSelection();
            java.setSelected(false);
            python.setSelected(false);
            result.setText("");
        }
    }

    public static void main(String[] args) {

        new StudentRegistration();

    }
}
