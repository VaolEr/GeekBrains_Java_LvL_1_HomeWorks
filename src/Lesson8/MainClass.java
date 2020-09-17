package Lesson8;
import javax.swing.*;

public class MainClass {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            JFrame.setDefaultLookAndFeelDecorated(true);
            new MyWindow().setVisible(true);
        });
    }

}