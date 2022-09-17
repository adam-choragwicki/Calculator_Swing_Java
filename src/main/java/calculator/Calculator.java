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

        JButton buttonPostfix = new JButton("PF");
        buttonPostfix.addActionListener(actionEvent -> convertToPostfix());
        buttonsPanel.add(buttonPostfix);

        JLabel emptyLabel = new JLabel();
        buttonsPanel.add(emptyLabel);

        JButton buttonDelete = new JButton("Del");
        buttonDelete.addActionListener(actionEvent -> deleteLastCharacter());
        buttonsPanel.add(buttonDelete);

        JButton buttonClear = new JButton("C");
        buttonClear.addActionListener(actionEvent -> clearResult());
        buttonsPanel.add(buttonClear);

        /* -------------------------------------------------- */

        JButton button7 = new JButton("7");
        addActionToButton(button7);
        buttonsPanel.add(button7);

        JButton button8 = new JButton("8");
        addActionToButton(button8);
        buttonsPanel.add(button8);

        JButton button9 = new JButton("9");
        addActionToButton(button9);
        buttonsPanel.add(button9);

        JButton buttonDivision = new JButton("/");
        addActionToButton(buttonDivision);
        buttonsPanel.add(buttonDivision);

        /* -------------------------------------------------- */

        JButton button4 = new JButton("4");
        addActionToButton(button4);
        buttonsPanel.add(button4);

        JButton button5 = new JButton("5");
        addActionToButton(button5);
        buttonsPanel.add(button5);

        JButton button6 = new JButton("6");
        addActionToButton(button6);
        buttonsPanel.add(button6);

        JButton buttonMultiplication = new JButton("*");
        addActionToButton(buttonMultiplication);
        buttonsPanel.add(buttonMultiplication);

        /* -------------------------------------------------- */

        JButton button1 = new JButton("1");
        addActionToButton(button1);
        buttonsPanel.add(button1);

        JButton button2 = new JButton("2");
        addActionToButton(button2);
        buttonsPanel.add(button2);

        JButton button3 = new JButton("3");
        addActionToButton(button3);
        buttonsPanel.add(button3);

        JButton buttonSubtraction = new JButton("-");
        addActionToButton(buttonSubtraction);
        buttonsPanel.add(buttonSubtraction);

        /* -------------------------------------------------- */

        JButton buttonDot = new JButton(".");
        addActionToButton(buttonDot);
        buttonsPanel.add(buttonDot);

        JButton button0 = new JButton("0");
        addActionToButton(button0);
        buttonsPanel.add(button0);

        JButton buttonEquals = new JButton("=");
        buttonEquals.addActionListener(actionEvent -> evaluate());
        buttonsPanel.add(buttonEquals);

        JButton buttonAddition = new JButton("+");
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
            String newEquation;

            if (currentEquation.length() == 1)
            {
                newEquation = "0";
            }
            else
            {
                newEquation = currentEquation.substring(0, currentEquation.length() - 1);
            }

            labelEquation.setText(newEquation);
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
