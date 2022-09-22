package calculator;

import config.GuiConfig;

import javax.swing.*;
import java.util.function.Consumer;

abstract class CalculatorButton extends JButton
{
    CalculatorButton(String text)
    {
        super(text);
        setFont(GuiConfig.buttonFont);
    }
}

class CharacterButton extends CalculatorButton
{
    CharacterButton(String text, Consumer<Character> function)
    {
        super(text);
        addActionListener(actionEvent -> function.accept(text.charAt(0)));
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
