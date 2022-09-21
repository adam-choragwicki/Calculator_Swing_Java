package calculator;

import javax.swing.*;

public class StatusBarManager
{
    StatusBarManager(JLabel statusBarLabel)
    {
        StatusBarManager.statusBarLabel = statusBarLabel;
        statusBarLabel.setFont(Config.statusBarFont);
    }

    void set(String text)
    {
        statusBarLabel.setText(text);
    }

    static void clear()
    {
        statusBarLabel.setText("");
    }

    private static JLabel statusBarLabel;
}
