package telas;

import telas.animais.InsertAnimalScreen;
import telas.animais.ListarAnimalScreen;
import telas.clientes.InsertClientScreen;
import telas.clientes.ListarClientScreen;

import javax.swing.*;
import java.awt.*;

public class MainScreen extends JFrame {
    private JPanel contentPane;
    private JButton btnCliInsert;
    private JButton btnCliConsultar;
    private JButton btnAniInsert;
    private JButton btnAniConsultar;
    private InsertClientScreen insertClientScreen;
    private ListarClientScreen listarClientScreen;
    private InsertAnimalScreen insertAnimalScreen;
    private ListarAnimalScreen listarAnimalScreen;


    public MainScreen() {
        setContentPane(contentPane);
        configureJFrame();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        btnCliInsert.setIcon(UIManager.getIcon("FileView.floppyDriveIcon"));
        btnCliInsert.addActionListener((x) -> {
            if(insertClientScreen == null) {
                insertClientScreen = new InsertClientScreen();
                insertClientScreen.setLocationRelativeTo(this);
                insertClientScreen.pack();
                insertClientScreen.setVisible(true);
                insertClientScreen.setSize(480,286);
                insertClientScreen.setLocationRelativeTo(this);
            }else{
                insertClientScreen.setVisible(true);
            }
        });

        btnCliConsultar.setIcon(UIManager.getIcon("FileView.directoryIcon"));
        btnCliConsultar.addActionListener((x) -> {
            if(listarClientScreen == null) {
                listarClientScreen = new ListarClientScreen();
                listarClientScreen.pack();
                listarClientScreen.setVisible(true);
                listarClientScreen.setSize(457,471);
                listarClientScreen.setLocationRelativeTo(this);
            }else{
                listarClientScreen.setVisible(true);
            }
            listarClientScreen.refreshTable();
        });

        btnAniInsert.setIcon(UIManager.getIcon("FileView.floppyDriveIcon"));
        btnAniInsert.addActionListener((x) -> {
            if(insertAnimalScreen == null) {
                insertAnimalScreen = new InsertAnimalScreen();
                insertAnimalScreen.pack();
                insertAnimalScreen.setVisible(true);
                insertAnimalScreen.setSize(546,367);
                insertAnimalScreen.setLocationRelativeTo(this);
            }else{
                insertAnimalScreen.setVisible(true);
            }
        });

        btnAniConsultar.setIcon(UIManager.getIcon("FileView.directoryIcon"));
        btnAniConsultar.addActionListener((x) -> {
            if(listarAnimalScreen == null) {
                listarAnimalScreen = new ListarAnimalScreen();
                listarAnimalScreen.pack();
                listarAnimalScreen.setVisible(true);
                listarAnimalScreen.setSize(457,471);
                listarAnimalScreen.setLocationRelativeTo(this);
            }else{
                listarAnimalScreen.setVisible(true);
            }
            listarAnimalScreen.refreshTable();
        });
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        MainScreen jFrame = new MainScreen();
        jFrame.setSize(800,125);
        jFrame.setLocationRelativeTo(null);
        jFrame.pack();
        jFrame.setVisible(true);

    }

    private void configureJFrame(){
        setTitle("Menu Principal");
        setResizable(false);
    }
}
