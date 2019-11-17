
import javax.swing.*;
import java.awt.*;

public class ErrorJPanel extends JPanel {

    private JTextArea errorListArea;
    private JButton buttonOK;


    public Dimension getPreferredSize(){
        return new Dimension(300,200);
    }

    public ErrorJPanel(){
        createErrorArea();
        add(errorListArea);
    }

    public JButton createOKBtn(){
        buttonOK = new JButton();
        buttonOK.setText("OK");
        buttonOK.setBounds(140,250,20,20);
        buttonOK.addActionListener(e -> {
            buttonOK = (JButton)e.getSource();

        });
        return buttonOK;
    }

    public void createErrorArea(){
        errorListArea = new JTextArea();
        errorListArea.setFont(new Font("Serif", Font.PLAIN,16));
        errorListArea.setSize(280,100);
        errorListArea.setBorder(FormJPanel.createNewBlackBorder());
        errorListArea.setWrapStyleWord(true);
        errorListArea.setLineWrap(true);
    }

    public JTextArea getErrorListArea(){
        return errorListArea;
    }

}
