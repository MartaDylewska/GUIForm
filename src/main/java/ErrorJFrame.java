
import javax.swing.*;

public class ErrorJFrame extends JFrame {

    private JTextArea errorTextArea;
    private JButton btnOK;

    public ErrorJFrame(int x, int y, String title){

        setLocation(x,y);
        setTitle(title);
        setResizable(false);
        ErrorJPanel panel = new ErrorJPanel();
        add(panel);
        //odwołanie do metody z panelu
        errorTextArea = panel.getErrorListArea();
        btnOK = panel.createOKBtn();
        panel.add(btnOK);
        pack();
    }
    //ta metoda będzie używana w innym JFrame
    public JTextArea getErrorTextArea(){
        return errorTextArea;
    }
    //ta metodą będzie używana w innym JFrame
    public JButton getBtnOK(){
        return btnOK;
    }


}
