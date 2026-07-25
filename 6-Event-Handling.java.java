import java.awt.*;
import java.awt.event.*;

public class SimpleCalculator extends Frame implements ActionListener {

    Label l1, l2, l3;
    TextField t1, t2;
    Button add, sub, mul, div, clear;

    public SimpleCalculator() {

        setTitle("Simple Calculator");
        setSize(400, 300);
        setLayout(null);

        l1 = new Label("First Number:");
        l1.setBounds(50, 50, 100, 20);
        add(l1);

        t1 = new TextField();
        t1.setBounds(170, 50, 150, 25);
        add(t1);

        l2 = new Label("Second Number:");
        l2.setBounds(50, 90, 100, 20);
        add(l2);

        t2 = new TextField();
        t2.setBounds(170, 90, 150, 25);
        add(t2);

        add = new Button("Add");
        add.setBounds(30, 140, 60, 30);
        add(add);

        sub = new Button("Sub");
        sub.setBounds(100, 140, 60, 30);
        add(sub);

        mul = new Button("Mul");
        mul.setBounds(170, 140, 60, 30);
        add(mul);

        div = new Button("Div");
        div.setBounds(240, 140, 60, 30);
        add(div);

        clear = new Button("Clear");
        clear.setBounds(310, 140, 60, 30);
        add(clear);

        l3 = new Label("Result:");
        l3.setBounds(50, 200, 300, 20);
        add(l3);

        add.addActionListener(this);
        sub.addActionListener(this);
        mul.addActionListener(this);
        div.addActionListener(this);
        clear.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == clear) {
            t1.setText("");
            t2.setText("");
            l3.setText("Result:");
            return;
        }

        try {

            double num1 = Double.parseDouble(t1.getText());
            double num2 = Double.parseDouble(t2.getText());
            double result = 0;

            if (e.getSource() == add) {
                result = num1 + num2;
            }
            else if (e.getSource() == sub) {
                result = num1 - num2;
            }
            else if (e.getSource() == mul) {
                result = num1 * num2;
            }
            else if (e.getSource() == div) {

                if (num2 == 0) {
                    l3.setText("Result: Cannot divide by zero");
                    return;
                }

                result = num1 / num2;
            }

            l3.setText("Result: " + result);

        }
        catch (NumberFormatException ex) {
            l3.setText("Enter valid numbers");
        }
    }

    public static void main(String[] args) {
        new SimpleCalculator();
    }
}