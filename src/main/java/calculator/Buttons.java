package calculator;

import javax.swing.*;

abstract class CalculatorButton extends JButton
{
    CalculatorButton(String text)
    {
        super(text);
        setFont(Config.buttonFont);
    }
}

class CharacterButton extends CalculatorButton
{
    CharacterButton(String text)
    {
        super(text);
        addActionListener(actionEvent -> Calculator.appendCharacterToLabelEquation(getText().charAt(0)));
    }
}

class ActionButton extends CalculatorButton
{
    ActionButton(String text, Runnable action)
    {
        super(text);
        addActionListener(actionEvent -> action.run());
    }
}
