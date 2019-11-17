

import javax.swing.*;
import java.util.ArrayList;

public class FormJFrame extends JFrame {



    public FormJFrame(int x, int y, String title){

        setLocation(x,y);
        setTitle(title);
        setResizable(false);
        FormJPanel panel = new FormJPanel();
        add(panel);
        pack();
    }

}
