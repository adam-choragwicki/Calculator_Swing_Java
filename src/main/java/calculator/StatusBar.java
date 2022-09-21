package calculator;

import javax.swing.*;

public class StatusBar extends JLabel
{
    StatusBar()
    {
        setFont(Config.statusBarFont);
    }

    void set(FailReason failReason)
    {
        setText(failReason.toString());
    }

    void clear()
    {
        setText("");
    }
}
