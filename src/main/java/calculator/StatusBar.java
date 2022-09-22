package calculator;

import config.GuiConfig;
import validation.ValidationFailureReason;

import javax.swing.*;

public class StatusBar extends JLabel
{
    StatusBar()
    {
        setFont(GuiConfig.statusBarFont);
    }

    void set(ValidationFailureReason validationFailureReason)
    {
        setText(validationFailureReason.toString());
    }

    void clear()
    {
        setText("");
    }
}
