
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                FormJFrame okno = new FormJFrame(10,10,"Formularz zgłoszeniowy");
                okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                okno.setVisible(true);
            }
        });


    }
}
