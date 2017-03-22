package lr3.Server;

import javax.swing.*;

public class TextWriter {

    private JTextArea textArea;

    public TextWriter(JTextArea textArea) {
        this.textArea = textArea;
    }

    public void appendText(String text) {
        textArea.append(text + "\n");
    }

}
