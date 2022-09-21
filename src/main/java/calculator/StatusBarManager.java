package calculator;

import javax.swing.*;

public class StatusBarManager
{
    StatusBarManager(JLabel statusBarLabel)
    {
        StatusBarManager.statusBarLabel = statusBarLabel;
        statusBarLabel.setFont(Config.statusBarFont);
        active = true;
    }

    static boolean isActive()
    {
        return active;
    }

    static void set(String text)
    {
        statusBarLabel.setText(text);
    }

    void clear()
    {
        statusBarLabel.setText("");
    }

    private static boolean active;
    private static JLabel statusBarLabel;
}
