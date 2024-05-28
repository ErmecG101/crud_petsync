package telas;

import telas.animais.InsertAnimalScreen;
import telas.animais.ListarAnimalScreen;
import telas.clientes.InsertClientScreen;
import telas.clientes.ListarClientScreen;

import javax.swing.*;

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

        btnCliInsert.addActionListener((x) -> {
            if(insertClientScreen == null) {
                insertClientScreen = new InsertClientScreen();
                insertClientScreen.pack();
                insertClientScreen.setVisible(true);
            }else{
                insertClientScreen.setVisible(true);
            }
        });

        btnCliConsultar.addActionListener((x) -> {
            if(listarClientScreen == null) {
                listarClientScreen = new ListarClientScreen();
                listarClientScreen.pack();
                listarClientScreen.setVisible(true);
            }else{
                listarClientScreen.setVisible(true);
            }
            listarClientScreen.refreshTable();
        });

        btnAniInsert.addActionListener((x) -> {
            if(insertAnimalScreen == null) {
                insertAnimalScreen = new InsertAnimalScreen();
                insertAnimalScreen.pack();
                insertAnimalScreen.setVisible(true);
            }else{
                insertAnimalScreen.setVisible(true);
            }
        });

        btnAniConsultar.addActionListener((x) -> {
            if(listarAnimalScreen == null) {
                listarAnimalScreen = new ListarAnimalScreen();
                listarAnimalScreen.pack();
                listarAnimalScreen.setVisible(true);
            }else{
                listarAnimalScreen.setVisible(true);
            }
            listarAnimalScreen.refreshTable();
        });
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        MainScreen jFrame = new MainScreen();
        jFrame.pack();
        jFrame.setVisible(true);
    }

    private void configureJFrame(){
        setTitle("Menu Principal");
        setResizable(false);
    }
}
