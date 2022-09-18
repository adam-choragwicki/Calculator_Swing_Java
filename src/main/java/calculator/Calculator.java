package calculator;

import javax.swing.*;
import java.awt.*;

class Calculator extends JFrame
{
    Calculator()
    {
        super("Calculator");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        addComponents();

        setSize(400, 400);
        setMinimumSize(getSize());
        setVisible(true);
    }

    private void addComponents()
    {
        addLabel();
        addButtons();
    }

    private void addLabel()
    {
        labelEquation = new JLabel("0");
        labelEquation.setAlignmentX(Component.RIGHT_ALIGNMENT);

        Font labelResultFont = new Font("", Font.BOLD, 30);
        labelEquation.setFont(labelResultFont);

        add(labelEquation);
    }

    private void addButtons()
    {
        JPanel buttonsPanel = new JPanel(new GridLayout(5, 4, 10, 10));

        buttonsPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);

        JButton buttonPostfix = new JButton("Postfix");
        buttonPostfix.addActionListener(actionEvent -> convertToPostfix());
        buttonsPanel.add(buttonPostfix);

        JLabel emptyLabel = new JLabel();
        buttonsPanel.add(emptyLabel);

        JButton buttonDelete = new JButton(Config.delete);
        buttonDelete.addActionListener(actionEvent -> deleteLastCharacter());
        buttonsPanel.add(buttonDelete);

        JButton buttonClear = new JButton(String.valueOf(Config.clear));
        buttonClear.addActionListener(actionEvent -> clearResult());
        buttonsPanel.add(buttonClear);

        /* -------------------------------------------------- */

        JButton button7 = new JButton(String.valueOf(Config.num7));
        addActionToButton(button7);
        buttonsPanel.add(button7);

        JButton button8 = new JButton(String.valueOf(Config.num8));
        addActionToButton(button8);
        buttonsPanel.add(button8);

        JButton button9 = new JButton(String.valueOf(Config.num9));
        addActionToButton(button9);
        buttonsPanel.add(button9);

        JButton buttonDivision = new JButton(String.valueOf(Config.divisionOperator));
        addActionToButton(buttonDivision);
        buttonsPanel.add(buttonDivision);

        /* -------------------------------------------------- */

        JButton button4 = new JButton(String.valueOf(Config.num4));
        addActionToButton(button4);
        buttonsPanel.add(button4);

        JButton button5 = new JButton(String.valueOf(Config.num5));
        addActionToButton(button5);
        buttonsPanel.add(button5);

        JButton button6 = new JButton(String.valueOf(Config.num6));
        addActionToButton(button6);
        buttonsPanel.add(button6);

        JButton buttonMultiplication = new JButton(String.valueOf(Config.multiplicationOperator));
        addActionToButton(buttonMultiplication);
        buttonsPanel.add(buttonMultiplication);

        /* -------------------------------------------------- */

        JButton button1 = new JButton(String.valueOf(Config.num1));
        addActionToButton(button1);
        buttonsPanel.add(button1);

        JButton button2 = new JButton(String.valueOf(Config.num2));
        addActionToButton(button2);
        buttonsPanel.add(button2);

        JButton button3 = new JButton(String.valueOf(Config.num3));
        addActionToButton(button3);
        buttonsPanel.add(button3);

        JButton buttonSubtraction = new JButton(String.valueOf(Config.subtractionOperator));
        addActionToButton(buttonSubtraction);
        buttonsPanel.add(buttonSubtraction);

        /* -------------------------------------------------- */

        JButton buttonDot = new JButton(String.valueOf(Config.dot));
        addActionToButton(buttonDot);
        buttonsPanel.add(buttonDot);

        JButton button0 = new JButton(String.valueOf(Config.num0));
        addActionToButton(button0);
        buttonsPanel.add(button0);

        JButton buttonEquals = new JButton(String.valueOf(Config.equals));
        buttonEquals.addActionListener(actionEvent -> evaluate());
        buttonsPanel.add(buttonEquals);

        JButton buttonAddition = new JButton(String.valueOf(Config.additionOperator));
        addActionToButton(buttonAddition);
        buttonsPanel.add(buttonAddition);

        /* -------------------------------------------------- */

        add(buttonsPanel);
    }

    private void deleteLastCharacter()
    {
        String currentEquation = labelEquation.getText();

        if (!currentEquation.isEmpty())
        {
            if (currentEquation.length() == 1)
            {
                clearResult();
            }
            else
            {
                labelEquation.setText(currentEquation.substring(0, currentEquation.length() - 1));
            }
        }
    }

    private void addActionToButton(JButton button)
    {
        button.addActionListener(actionEvent -> appendCharacterToLabelEquation(button.getText().charAt(0)));
    }

    private void appendCharacterToLabelEquation(final char character)
    {
        if (labelEquation.getText().equals("0"))
        {
            labelEquation.setText(String.valueOf(character));
        }
        else
        {
            labelEquation.setText(labelEquation.getText() + character);
        }
    }

    private void clearResult()
    {
        labelEquation.setText("0");
    }

    private void convertToPostfix()
    {
        String currentEquation = labelEquation.getText();

        if (!currentEquation.isEmpty())
        {
            labelEquation.setText(Converter.convertToPostfixExpression(currentEquation));
        }
    }

    private void evaluate()
    {
        String currentEquation = labelEquation.getText();

        if (Validator.validateInfixExpression(currentEquation))
        {
            labelEquation.setForeground(Color.black);

            String postfixExpression = Converter.convertToPostfixExpression(currentEquation);
            labelEquation.setText(Evaluator.evaluate(postfixExpression));
        }
        else
        {
            labelEquation.setForeground(Color.red);
        }
    }

    private JLabel labelEquation;
}
