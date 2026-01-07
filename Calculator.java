import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator implements ActionListener, KeyListener {

    JFrame frame;
    JTextField display;

    JButton[] nums = new JButton[10];
    JButton add, sub, mul, div, dec, eq, del, clr;

    double num1 = 0, num2 = 0;
    char operator = ' ';
    boolean newInput = true;

    Calculator() {
        frame = new JFrame(" Calculator ");
        frame.setSize(420, 560);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        display = new JTextField();
        display.setBounds(40, 25, 330, 55);
        display.setFont(new Font("Arial", Font.BOLD, 26));
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.addKeyListener(this);

        add = new JButton("+");
        sub = new JButton("-");
        mul = new JButton("*");
        div = new JButton("/");
        dec = new JButton(".");
        eq  = new JButton("=");
        del = new JButton("DEL");
        clr = new JButton("CLR");

        JButton[] ops = {add, sub, mul, div, dec, eq, del, clr};
        for (JButton b : ops) {
            b.addActionListener(this);
            b.setFocusable(false);
        }

        for (int i = 0; i < 10; i++) {
            nums[i] = new JButton(String.valueOf(i));
            nums[i].addActionListener(this);
            nums[i].setFocusable(false);
        }

        JPanel grid = new JPanel(new GridLayout(4,4,10,10));
        grid.setBounds(40, 100, 330, 300);

        grid.add(nums[7]); grid.add(nums[8]); grid.add(nums[9]); grid.add(div);
        grid.add(nums[4]); grid.add(nums[5]); grid.add(nums[6]); grid.add(mul);
        grid.add(nums[1]); grid.add(nums[2]); grid.add(nums[3]); grid.add(sub);
        grid.add(dec);     grid.add(nums[0]); grid.add(eq);      grid.add(add);

        del.setBounds(40, 420, 160, 55);
        clr.setBounds(210, 420, 160, 55);

        frame.add(display);
        frame.add(grid);
        frame.add(del);
        frame.add(clr);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Calculator::new);
    }

    // ================= LOGIC =================

    @Override
    public void actionPerformed(ActionEvent e) {

        // NUMBER INPUT
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == nums[i]) {
                writeNumber(String.valueOf(i));
                return;
            }
        }

        // DECIMAL
        if (e.getSource() == dec) {
            if (newInput) {
                display.setText("0.");
                newInput = false;
            } else if (!display.getText().contains(".")) {
                display.setText(display.getText() + ".");
            }
            return;
        }

        // OPERATORS
        if (e.getSource() == add || e.getSource() == sub ||
            e.getSource() == mul || e.getSource() == div) {

            if (!display.getText().isEmpty() && !display.getText().equals("Error")) {
                num1 = Double.parseDouble(display.getText());
                operator = e.getActionCommand().charAt(0);
                newInput = true;
            }
            return;
        }

        // EQUALS
        if (e.getSource() == eq) {
            calculate();
            return;
        }

        // CLEAR
        if (e.getSource() == clr) {
            reset();
            return;
        }

        // DELETE
        if (e.getSource() == del) {
            if (!newInput && !display.getText().isEmpty()) {
                display.setText(display.getText()
                        .substring(0, display.getText().length() - 1));
            }
        }
    }

    // ================= CORE METHODS =================

    private void writeNumber(String n) {
        if (newInput || display.getText().equals("Error")) {
            display.setText(n);
            newInput = false;
        } else {
            display.setText(display.getText() + n);
        }
    }

    private void calculate() {
        if (operator == ' ' || newInput) return;

        num2 = Double.parseDouble(display.getText());
        double res;

        switch (operator) {
            case '+': res = num1 + num2; break;
            case '-': res = num1 - num2; break;
            case '*': res = num1 * num2; break;
            case '/':
                if (num2 == 0) {
                    display.setText("Error");
                    resetFlags();
                    return;
                }
                res = num1 / num2;
                break;
            default: return;
        }

        display.setText(format(res));
        num1 = res;
        resetFlags();
    }

    private void reset() {
        display.setText("");
        num1 = num2 = 0;
        resetFlags();
    }

    private void resetFlags() {
        operator = ' ';
        newInput = true;
    }

    private String format(double v) {
        if (v == (long) v)
            return String.valueOf((long) v);
        return String.valueOf(v);
    }

    // ================= KEYBOARD SUPPORT =================

    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        char c = e.getKeyChar();
        if (Character.isDigit(c)) writeNumber(String.valueOf(c));
        if (c == '.') dec.doClick();
        if (c == '+') add.doClick();
        if (c == '-') sub.doClick();
        if (c == '*') mul.doClick();
        if (c == '/') div.doClick();
        if (c == '=' || e.getKeyCode() == KeyEvent.VK_ENTER) eq.doClick();
        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) del.doClick();
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) clr.doClick();
    }
            }
