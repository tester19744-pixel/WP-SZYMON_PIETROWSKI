import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener
{

    private JTextField textFieldScreen = new JTextField();

    private JButton[] numbers = new JButton[10];
    private JButton add = new JButton("+");
    private JButton sub = new JButton("-");
    private JButton mul = new JButton("*");
    private JButton div = new JButton("/");
    private JButton eq = new JButton("=");

    private JButton del = new JButton("Del");
    private JButton ce = new JButton("CE");
    private JButton c = new JButton("C");

    public Calculator()
    {
        setTitle("Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(6,3,5,5));
        textFieldScreen.setEditable(false);
        textFieldScreen.setText("0");
        add(textFieldScreen, BorderLayout.NORTH);

        for(int i=0;i<=9;i++)
        {
            numbers[i] = new JButton(String.valueOf(i));
            numbers[i].addActionListener(this);
        }

        panel.add(numbers[1]);
        panel.add(numbers[2]);
        panel.add(numbers[3]);
        panel.add(numbers[4]);
        panel.add(numbers[5]);
        panel.add(numbers[6]);
        panel.add(numbers[7]);
        panel.add(numbers[8]);
        panel.add(numbers[9]);
        panel.add(new JLabel());
        panel.add(numbers[0]);
        panel.add(new JLabel());

        JButton[] ops = {add, sub, mul, div, eq, del, ce, c};
        for(JButton b : ops)
        {
            b.addActionListener(this);
            panel.add(b);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String cmd = e.getActionCommand();
        String text = textFieldScreen.getText();


        if(cmd.matches("[0-9]"))
        {
            if(text.equals("0"))
            {
                textFieldScreen.setText(cmd);
            } else
            {
                textFieldScreen.setText(text + cmd);
            }
        }

        else if(cmd.matches("[+\\-*/]"))
        {
            if(!text.equals("0"))
            {


                if(findOperator(text) != -1)
                {
                    calculate();
                    text = textFieldScreen.getText();
                }

                textFieldScreen.setText(text + cmd);
            }
        }


        else if(cmd.equals("="))
        {
            calculate();
        }


        else if(cmd.equals("Del"))
        {
            if(text.length() > 1)
            {
                textFieldScreen.setText(text.substring(0, text.length()-1));
            } else
            {
                textFieldScreen.setText("0");
            }
        }


        else if(cmd.equals("CE"))
        {
            int opIndex = findOperator(text);
            if(opIndex != -1)
            {
                textFieldScreen.setText(text.substring(0, opIndex+1));
            }
            else
            {
                textFieldScreen.setText("0");
            }
        }

        // C – wszystko
        else if(cmd.equals("C"))
        {
            textFieldScreen.setText("0");
        }
    }

    private void calculate()
    {
        String text = textFieldScreen.getText();
        int opIndex = findOperator(text);

        if(opIndex == -1) return;

        int a = Integer.parseInt(text.substring(0, opIndex));
        int b = Integer.parseInt(text.substring(opIndex+1));
        char op = text.charAt(opIndex);

        int result = 0;

        switch(op)
        {
            case '+': result = a + b; break;
            case '-': result = a - b; break;
            case '*': result = a * b; break;
            case '/': result = (b != 0) ? a / b : 0; break;
        }

        textFieldScreen.setText(String.valueOf(result));
    }

    private int findOperator(String text)
    {
        for(int i=0;i<text.length();i++)
        {
            char c = text.charAt(i);
            if(c=='+' || c=='-' || c=='*' || c=='/')
                return i;
        }
        return -1;
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(Calculator::new);
    }
} 