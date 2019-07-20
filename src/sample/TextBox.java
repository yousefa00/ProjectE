package sample;

import javafx.application.Platform;
import javafx.scene.control.Label;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Raja on 5/10/2017.
 */
public class TextBox {

    public static String getNextString(String textbox, Label sceneLabel) {
        if (sceneLabel.getText().length() + 1 <= textbox.length()) {
            return textbox.substring(0, sceneLabel.getText().length() + 1);
        }
        else {
            return textbox;
        }
    }

    public static void runTextBox(final String message, final Label label, final int delay) {
        label.setText("");
        final Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run () {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        label.setText(getNextString(message, label));
                        if (label.getText().length() + 1 > message.length()) {
                            timer.cancel();
                        }
                    }
                });
            }
        }, delay,35);
    }

}
