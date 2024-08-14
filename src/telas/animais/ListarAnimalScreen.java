package telas.animais;

import daos.AnimalDAO;
import daos.ClienteDAO;
import enums.AnimalEnums;
import models.Animal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.util.Vector;

public class ListarAnimalScreen extends JFrame {

    private JPanel mainPainel;
    private JTable tblAnimals;
    private JButton btnDelete;
    private JButton btnAlter;
    private JButton btnRefresh;

    public ListarAnimalScreen() throws HeadlessException {
        configureJFrame();

        btnRefresh.addActionListener((x) -> {
            refreshTable();
        });

        btnDelete.addActionListener((x) -> {
            deleteRow();
        });

        btnAlter.addActionListener((x) -> {
            alterRow();
        });
    }

    private void configureJFrame() {
        setTitle("Consultar Animais");
        setResizable(false);
        setContentPane(mainPainel);
        pack();
    }

    public void refreshTable() {
        TableModel tableModel = buildTableModel();
        if (tableModel != null)
            tblAnimals.setModel(tableModel);
        else
            JOptionPane.showMessageDialog(this, "Error on building tableModel.");
    }

    private TableModel buildTableModel() {
        try {
            AnimalDAO aDao = new AnimalDAO();
            ResultSet rs = aDao.getAllAnimalRAW();
            System.out.println(rs.isClosed());
            int columnCount = rs.getMetaData().getColumnCount();

            // Column names.
            Vector<String> columnNames = new Vector<>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                columnNames.add(rs.getMetaData().getColumnName(columnIndex));
            }

            // Data of the table.
            Vector<Vector<Object>> dataVector = new Vector<>();
            while (rs.next()) {
                Vector<Object> rowVector = new Vector<>();
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    switch (columnIndex) {
                        case 3:
                            rowVector.add(AnimalEnums.getTipoAnimal((Integer) rs.getObject(columnIndex)));
                            break;
                        case 4:
                            rowVector.add(AnimalEnums.getCorPelagem((Integer) rs.getObject(columnIndex)));
                            break;
                        case 6:
                            rowVector.add(AnimalEnums.getSexoAnimal((Integer) rs.getObject(columnIndex)));
                            break;
                        default:
                            rowVector.add(rs.getObject(columnIndex));
                    }
                }
                dataVector.add(rowVector);
            }

            return new DefaultTableModel(dataVector, columnNames) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao obter animais");
        }
        return null;
    }

    private void deleteRow() {
        if(tblAnimals.getSelectedRow() < 0){
            JOptionPane.showMessageDialog(this, "Por favor, selecione um registro para deletar.");
            return;
        }
        int chosenOption = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja deletar esse animal?", "Confirmação de exclusão", JOptionPane.YES_NO_OPTION);
        if (chosenOption == JOptionPane.YES_OPTION) {
            try {
                int indexRow = tblAnimals.getSelectedRow();
                int codigo = (int) tblAnimals.getValueAt(indexRow, 0);
                AnimalDAO aDao = new AnimalDAO();
                aDao.deleteById(codigo);
                refreshTable();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao deletar animal");
            }
        }
    }

    private void alterRow(){
        if(tblAnimals.getSelectedRow() < 0){
            JOptionPane.showMessageDialog(this, "Por favor, selecione um registro para alterar.");
            return;
        }
        try {
            AnimalDAO aDao = new AnimalDAO();
            int indexRow = tblAnimals.getSelectedRow();
            int codigo = (int) tblAnimals.getValueAt(indexRow, 0);
            Animal anim = aDao.selectById(codigo);
            InsertAnimalScreen alterAnimal = new InsertAnimalScreen(anim);
            alterAnimal.pack();
            alterAnimal.setVisible(true);
            alterAnimal.setSize(546,367);
            alterAnimal.setLocationRelativeTo(this);
        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao alterar animal");
        }
    }
}
