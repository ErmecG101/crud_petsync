package telas.clientes;

import daos.ClienteDAO;
import models.Cliente;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public class InsertClientScreen extends JFrame {
    private JPanel contentPane;
    private JButton btnInsert;
    private JButton btnLimpar;
    private JButton btnFechar;
    private JTextField txtCPF;
    private JTextField txtNome;
    private JTextField txtEmail;
    private JTextField txtEndereco;
    private JLabel lblStatusImagem;
    private JButton btnCarregarImagem;
    private final Cliente cliente = new Cliente();

    private final List<JTextField> campos = new ArrayList<>();

    public InsertClientScreen(Cliente c){
        setContentPane(contentPane);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        btnInsert.setText("Atualizar");
        campos.add(txtNome);
        campos.add(txtCPF);
        campos.add(txtEmail);
        campos.add(txtEndereco);
        txtCPF.setEnabled(false);
        setResizable(false);
        setTitle("Atualizar Cliente");

        txtCPF.setText(c.getCpf());
        txtEndereco.setText(c.getEndereco());
        txtEmail.setText(c.getEmail());
        txtNome.setText(c.getNome());
        lblStatusImagem.setText("Imagem original carregada.");
        cliente.setImagem(c.getImagem());

        configureCarregarImagem();

        btnFechar.addActionListener((x) -> {
            dispose();
        });

        btnLimpar.addActionListener((x) -> {
            limpar();
        });

        btnInsert.addActionListener((x) -> {
            if(validateFields()) {
                cliente.setNome(txtNome.getText());
                cliente.setEmail(txtEmail.getText());
                cliente.setEndereco(txtEndereco.getText());
                cliente.setCpf(txtCPF.getText());

                ClienteDAO cDao = new ClienteDAO();
                try {
                    cDao.updateOne(cliente);
                    limpar();
                    JOptionPane.showMessageDialog(this, "Cliente atualizado com sucesso!");
                    this.setVisible(false);
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Falha ao atualizar cliente!");
                }
            }else{
                JOptionPane.showMessageDialog(this, "Campos invalidos ou vazios, preencha-os corretamente.");
            }
        });
    }

    public InsertClientScreen() {
        setContentPane(contentPane);
        btnInsert.setText("Inserir");
        campos.add(txtNome);
        campos.add(txtCPF);
        campos.add(txtEmail);
        campos.add(txtEndereco);
        txtCPF.setEnabled(true);
        setResizable(false);
        setTitle("Inserir Cliente");

        configureCarregarImagem();
        lblStatusImagem.setText("Nenhuma imagem carregada!");

        btnFechar.addActionListener((x) -> {
            dispose();
        });

        btnInsert.addActionListener((x) -> {
            if(validateFields()) {
                cliente.setNome(txtNome.getText());
                cliente.setEmail(txtEmail.getText());
                cliente.setEndereco(txtEndereco.getText());
                cliente.setCpf(txtCPF.getText());

                ClienteDAO cDao = new ClienteDAO();
                try {
                    cDao.insertOne(cliente);
                    limpar();
                    JOptionPane.showMessageDialog(this, "Cliente inserido com sucesso!");
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Falha ao inserir cliente!");
                }
            }else{
                JOptionPane.showMessageDialog(this, "Campos invalidos ou vazios, preencha-os corretamente.");
            }
        });

    }

    private void configureCarregarImagem(){
        btnCarregarImagem.addActionListener((x) -> {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "JPG & GIF Images", "jpg", "gif", "png", "jpeg");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                if(chooser.getSelectedFile().length() < 500000) {
                    System.out.println("You chose to open this file: " +
                            chooser.getSelectedFile().getName());
                    try {
                        System.out.println("File bytes: " + Arrays.toString(Files.readAllBytes(chooser.getSelectedFile().toPath())));
                        cliente.setImagem(Base64.getEncoder().encodeToString(Files.readAllBytes(chooser.getSelectedFile().toPath())));
                        lblStatusImagem.setText("Imagem Carregada!");
                    } catch (IOException e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Erro ao interpretar imagem selecionada.");
                        lblStatusImagem.setText("Erro ao obter imagem.");
                    }
                }else{
                    System.out.println("Image too large!");
                    lblStatusImagem.setText("Imagem excede tamanho máximo (500Kb)");
                }
            }
        });

    }

    public void limpar(){
        for(JTextField campo : campos){
            campo.setText("");
        }
        cliente.setImagem("");
        lblStatusImagem.setText("Nenhuma imagem carregada!");
    }

    public boolean validateFields(){
        for(JTextField campo : campos){
            if(campo.getText() == null || campo.getText().isEmpty())
                return false;
        }
        if(cliente.getImagem() == null || cliente.getImagem().isEmpty())
            return false;
        return true;
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        InsertClientScreen jframe = new InsertClientScreen();
        jframe.pack();
        jframe.setVisible(true);
    }
}
