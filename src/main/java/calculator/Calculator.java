package calculator;

import javax.swing.*;
import javax.swing.border.BevelBorder;
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

        setSize(Config.windowWidth, Config.windowHeight);
        setMinimumSize(getSize());
        setVisible(true);
    }

    private void addComponents()
    {
        addEquationLabel();
        addButtons();
        addStatusBar();
    }

    private void addEquationLabel()
    {
        labelEquation = new JLabel(Config.emptyResultContent);
        labelEquation.setAlignmentX(Component.RIGHT_ALIGNMENT);
        labelEquation.setFont(Config.labelEquationFont);
        add(labelEquation);
    }

    private void addButtons()
    {
        JPanel buttonsPanel = new JPanel(new GridLayout(5, 4, 10, 10));

        buttonsPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);

        CharacterButton buttonLeftParentheses = new CharacterButton(String.valueOf(Characters.leftParentheses));
        buttonsPanel.add(buttonLeftParentheses);

        CharacterButton buttonRightParentheses = new CharacterButton(String.valueOf(Characters.rightParentheses));
        buttonsPanel.add(buttonRightParentheses);

        ActionButton buttonDelete = new ActionButton(Actions.delete, this::deleteLastCharacter);
        buttonsPanel.add(buttonDelete);

        ActionButton buttonClear = new ActionButton(String.valueOf(Actions.clear), this::clearResult);
        buttonsPanel.add(buttonClear);

        /* -------------------------------------------------- */

        CharacterButton button7 = new CharacterButton(String.valueOf(Characters.num7));
        buttonsPanel.add(button7);

        CharacterButton button8 = new CharacterButton(String.valueOf(Characters.num8));
        buttonsPanel.add(button8);

        CharacterButton button9 = new CharacterButton(String.valueOf(Characters.num9));
        buttonsPanel.add(button9);

        CharacterButton buttonDivision = new CharacterButton(String.valueOf(Operators.divisionOperator));
        buttonsPanel.add(buttonDivision);

        /* -------------------------------------------------- */

        CharacterButton button4 = new CharacterButton(String.valueOf(Characters.num4));
        buttonsPanel.add(button4);

        CharacterButton button5 = new CharacterButton(String.valueOf(Characters.num5));
        buttonsPanel.add(button5);

        CharacterButton button6 = new CharacterButton(String.valueOf(Characters.num6));
        buttonsPanel.add(button6);

        CharacterButton buttonMultiplication = new CharacterButton(String.valueOf(Operators.multiplicationOperator));
        buttonsPanel.add(buttonMultiplication);

        /* -------------------------------------------------- */

        CharacterButton button1 = new CharacterButton(String.valueOf(Characters.num1));
        buttonsPanel.add(button1);

        CharacterButton button2 = new CharacterButton(String.valueOf(Characters.num2));
        buttonsPanel.add(button2);

        CharacterButton button3 = new CharacterButton(String.valueOf(Characters.num3));
        buttonsPanel.add(button3);

        CharacterButton buttonSubtraction = new CharacterButton(String.valueOf(Operators.subtractionOperator));
        buttonsPanel.add(buttonSubtraction);

        /* -------------------------------------------------- */

        CharacterButton buttonDot = new CharacterButton(String.valueOf(Characters.dot));
        buttonsPanel.add(buttonDot);

        CharacterButton button0 = new CharacterButton(String.valueOf(Characters.num0));
        buttonsPanel.add(button0);

        ActionButton buttonEquals = new ActionButton(String.valueOf(Actions.equals), this::evaluate);
        buttonsPanel.add(buttonEquals);

        CharacterButton buttonAddition = new CharacterButton(String.valueOf(Operators.additionOperator));
        buttonsPanel.add(buttonAddition);

        /* -------------------------------------------------- */

        add(buttonsPanel);
    }

    private void addStatusBar()
    {
        JPanel statusPanel = new JPanel();
        statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        statusPanel.setPreferredSize(new Dimension(getWidth(), getHeight()));
        statusPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        add(statusPanel);

        JLabel statusBarLabel = new JLabel();
        statusPanel.add(statusBarLabel);

        statusBarManager = new StatusBarManager(statusBarLabel);
    }

    private void deleteLastCharacter()
    {
        String currentEquation = labelEquation.getText();

        labelEquation.setForeground(Color.black);
        StatusBarManager.clear();

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

    public static void appendCharacterToLabelEquation(final char character)
    {
        labelEquation.setForeground(Color.black);
        StatusBarManager.clear();

        if (labelEquation.getText().equals(Config.emptyResultContent))
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
        StatusBarManager.clear();
        labelEquation.setForeground(Color.black);
        labelEquation.setText(Config.emptyResultContent);
    }

    private void evaluate()
    {
        String currentEquation = labelEquation.getText();
        ValidationResult validationResult = Validator.validateInfixExpression(currentEquation);

        if (validationResult.isSuccess())
        {
            labelEquation.setForeground(Color.black);

            String postfixExpression = Converter.convertToPostfixExpression(currentEquation);
            labelEquation.setText(Evaluator.evaluate(postfixExpression));
        }
        else
        {
            labelEquation.setForeground(Color.red);
            statusBarManager.set(validationResult.getFailReason().toString());
        }
    }

    private static JLabel labelEquation;
    private StatusBarManager statusBarManager;
}
