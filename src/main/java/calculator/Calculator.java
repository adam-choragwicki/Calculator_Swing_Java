package calculator;

import config.Actions;
import config.Characters;
import config.GuiConfig;
import config.Operators;
import conversion.Converter;
import errorhandling.ErrorHandler;
import evaluation.Evaluator;
import validation.ValidationResult;
import validation.Validator;

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

        setSize(GuiConfig.windowWidth, GuiConfig.windowHeight);
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
        labelEquation = new JLabel(GuiConfig.emptyEquationContent);
        labelEquation.setAlignmentX(Component.RIGHT_ALIGNMENT);
        labelEquation.setFont(GuiConfig.labelEquationFont);
        add(labelEquation);
    }

    private void addButtons()
    {
        JPanel buttonsPanel = new JPanel(new GridLayout(5, 4, 10, 10));

        buttonsPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);

        CharacterButton buttonLeftParentheses = new CharacterButton(String.valueOf(Characters.leftParentheses), this::appendCharacterToLabelEquation);
        buttonsPanel.add(buttonLeftParentheses);

        CharacterButton buttonRightParentheses = new CharacterButton(String.valueOf(Characters.rightParentheses), this::appendCharacterToLabelEquation);
        buttonsPanel.add(buttonRightParentheses);

        ActionButton buttonDelete = new ActionButton(Actions.delete, this::deleteLastCharacterFromLabelEquation);
        buttonsPanel.add(buttonDelete);

        ActionButton buttonClear = new ActionButton(String.valueOf(Actions.clear), this::clearResult);
        buttonsPanel.add(buttonClear);

        /* -------------------------------------------------- */

        CharacterButton button7 = new CharacterButton(String.valueOf(Characters.num7), this::appendCharacterToLabelEquation);
        buttonsPanel.add(button7);

        CharacterButton button8 = new CharacterButton(String.valueOf(Characters.num8), this::appendCharacterToLabelEquation);
        buttonsPanel.add(button8);

        CharacterButton button9 = new CharacterButton(String.valueOf(Characters.num9), this::appendCharacterToLabelEquation);
        buttonsPanel.add(button9);

        CharacterButton buttonDivision = new CharacterButton(String.valueOf(Operators.divisionOperator), this::appendCharacterToLabelEquation);
        buttonsPanel.add(buttonDivision);

        /* -------------------------------------------------- */

        CharacterButton button4 = new CharacterButton(String.valueOf(Characters.num4), this::appendCharacterToLabelEquation);
        buttonsPanel.add(button4);

        CharacterButton button5 = new CharacterButton(String.valueOf(Characters.num5), this::appendCharacterToLabelEquation);
        buttonsPanel.add(button5);

        CharacterButton button6 = new CharacterButton(String.valueOf(Characters.num6), this::appendCharacterToLabelEquation);
        buttonsPanel.add(button6);

        CharacterButton buttonMultiplication = new CharacterButton(String.valueOf(Operators.multiplicationOperator), this::appendCharacterToLabelEquation);
        buttonsPanel.add(buttonMultiplication);

        /* -------------------------------------------------- */

        CharacterButton button1 = new CharacterButton(String.valueOf(Characters.num1), this::appendCharacterToLabelEquation);
        buttonsPanel.add(button1);

        CharacterButton button2 = new CharacterButton(String.valueOf(Characters.num2), this::appendCharacterToLabelEquation);
        buttonsPanel.add(button2);

        CharacterButton button3 = new CharacterButton(String.valueOf(Characters.num3), this::appendCharacterToLabelEquation);
        buttonsPanel.add(button3);

        CharacterButton buttonSubtraction = new CharacterButton(String.valueOf(Operators.subtractionOperator), this::appendCharacterToLabelEquation);
        buttonsPanel.add(buttonSubtraction);

        /* -------------------------------------------------- */

        CharacterButton buttonDot = new CharacterButton(String.valueOf(Characters.dot), this::appendCharacterToLabelEquation);
        buttonsPanel.add(buttonDot);

        CharacterButton button0 = new CharacterButton(String.valueOf(Characters.num0), this::appendCharacterToLabelEquation);
        buttonsPanel.add(button0);

        ActionButton buttonEquals = new ActionButton(String.valueOf(Actions.equals), this::evaluate);
        buttonsPanel.add(buttonEquals);

        CharacterButton buttonAddition = new CharacterButton(String.valueOf(Operators.additionOperator), this::appendCharacterToLabelEquation);
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

        statusBar = new StatusBar();
        statusPanel.add(statusBar);
    }

    private void modifyLabelEquation()
    {
        labelEquation.setForeground(Color.black);
        statusBar.clear();
    }

    private void appendCharacterToLabelEquation(final char character)
    {
        modifyLabelEquation();

        if (labelEquation.getText().equals(GuiConfig.emptyEquationContent))
        {
            labelEquation.setText(String.valueOf(character));
        }
        else
        {
            labelEquation.setText(labelEquation.getText() + character);
        }
    }

    private void deleteLastCharacterFromLabelEquation()
    {
        modifyLabelEquation();

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

    private void clearResult()
    {
        modifyLabelEquation();
        labelEquation.setText(GuiConfig.emptyEquationContent);
    }

    private void evaluate()
    {
        String currentEquation = labelEquation.getText();
        ValidationResult validationResult = Validator.validateInfixExpression(currentEquation);

        if (validationResult.isSuccess())
        {
            labelEquation.setForeground(Color.black);

            String postfixExpression;

            ErrorHandler.ErrorPhase phase = ErrorHandler.ErrorPhase.Conversion;

            try
            {
                postfixExpression = Converter.convertToPostfixExpression(currentEquation);

                phase = ErrorHandler.ErrorPhase.Evaluation;

                labelEquation.setText(Evaluator.evaluate(postfixExpression));
            }
            catch (Exception e)
            {
                ErrorHandler.showCalculatorErrorDialog(this, phase, e.getMessage());
            }
        }
        else
        {
            labelEquation.setForeground(Color.red);
            statusBar.set(validationResult.validationFailureReason());
        }
    }

    private JLabel labelEquation;
    private StatusBar statusBar;
}
