package errorhandling;

import javax.swing.*;
import java.awt.*;

public class ErrorHandler
{
    public enum ErrorPhase
    {
        Conversion,
        Evaluation
    }

    public static void showCalculatorErrorDialog(Component component, ErrorPhase errorPhase, String exceptionMessage)
    {
        String errorSummary = "";

        if (errorPhase == ErrorPhase.Conversion)
        {
            errorSummary = "Exception occurred during infix to postfix conversion";
        }
        else if(errorPhase == ErrorPhase.Evaluation)
        {
            errorSummary = "Exception occurred during postfix expression evaluation";
        }

        String message = "Oops, looks like you have found a bug in my calculator.\n";

        if(exceptionMessage != null)
        {
            message += String.format("\nException message: %s", exceptionMessage);
        }

        JOptionPane.showMessageDialog(component, message, errorSummary, JOptionPane.ERROR_MESSAGE);
    }
}
