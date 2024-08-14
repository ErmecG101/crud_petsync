package telas.clientes;

import daos.ClienteDAO;
import models.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class ListarClientScreen extends JFrame {
    private JTable tblClientes;
    private JPanel panel1;
    private JButton btnAlterar;
    private JButton btnExcluir;
    private JButton btnAtualizar;
    private InsertClientScreen telaAtualizarCliente;

    public ListarClientScreen() throws HeadlessException {
        setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        configureJFrame();

        btnAtualizar.addActionListener((x) -> {
            refreshTable();
        });

        btnExcluir.addActionListener((x) -> {
            deleteRow();
        });

        btnAlterar.addActionListener((x) -> {
            if(tblClientes.getSelectedRow() < 0){
                JOptionPane.showMessageDialog(this, "Por favor, selecione um registro para alterar.");
                return;
            }
            Cliente c = new Cliente();
            int indexRow = tblClientes.getSelectedRow();
            c.setCpf((String) tblClientes.getValueAt(indexRow, 0));
            c.setNome((String) tblClientes.getValueAt(indexRow, 1));
            c.setEmail((String) tblClientes.getValueAt(indexRow, 2));
            c.setEndereco((String) tblClientes.getValueAt(indexRow, 3));
            c.setImagem(tblClientes.getValueAt(indexRow, 4).toString());

            telaAtualizarCliente = new InsertClientScreen(c);
            telaAtualizarCliente.pack();
            telaAtualizarCliente.setVisible(true);
            telaAtualizarCliente.setSize(480,286);
            telaAtualizarCliente.setLocationRelativeTo(this);
        });
    }

    public void refreshTable(){
        TableModel tableModel= buildTableModel();
        if(tableModel != null)
            tblClientes.setModel(tableModel);
        else
            JOptionPane.showMessageDialog(this, "Error on building tableModel.");
    }

    private void deleteRow(){
        if(tblClientes.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um registro para excluir");
            return;
        }
        try{
            int optionChosen = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja apagar este registro?", "Confirmação de exclusão", JOptionPane.YES_NO_OPTION);
            if(optionChosen == JOptionPane.YES_OPTION) {
                int indexRow = tblClientes.getSelectedRow();
                String cpf = (String) tblClientes.getValueAt(indexRow, 0);
                ClienteDAO cDao = new ClienteDAO();
                cDao.deleteOneByCPF(cpf);
                refreshTable();
            }
        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error on deleting row.");
        }
    }

    private TableModel buildTableModel(){
        try{
            ClienteDAO cDao = new ClienteDAO();
            ResultSet rs = cDao.getAllClienteRAW();
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
                    rowVector.add(rs.getObject(columnIndex));
                }
                dataVector.add(rowVector);
            }

            return new DefaultTableModel(dataVector, columnNames){
                @Override
                public boolean isCellEditable(int row, int column){
                    return false;
                }
            };
        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao obter clientes");
        }
        return null;
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            ListarClientScreen jFrame = new ListarClientScreen();
            jFrame.pack();
            jFrame.setVisible(true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void configureJFrame(){
        setTitle("Listar Cliente");
        setResizable(false);
    }
}
