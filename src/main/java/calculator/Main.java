package calculator;

import javax.swing.SwingUtilities;

public class Main
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(Calculator::new); // run in Event Dispatch Thread
    }
}
