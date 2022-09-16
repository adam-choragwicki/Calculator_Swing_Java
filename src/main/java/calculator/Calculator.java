package calculator;

import javax.swing.*;

class Calculator extends JFrame
{
    Calculator()
    {
        super("Calculator");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setSize(400, 400);
        setMinimumSize(getSize());
        setVisible(true);
    }
}
