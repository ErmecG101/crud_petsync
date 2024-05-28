package telas.animais;

import daos.AnimalDAO;
import models.Animal;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;

public class InsertAnimalScreen extends JFrame {


    private JPanel mainPanel;
    private JButton btnInsert;
    private JButton btnLimpar;
    private JButton btnFechar;
    private JTextField txtNome;
    private JComboBox cbTipAnimal;
    private JComboBox cbCorPelagem;
    private JComboBox cbSexo;
    private JTextField txtPeso;
    private JTextField txtObservacoes;
    private JButton carregarImagemButton;
    private JLabel lblStatusImagem;
    private JTextField txtData;
    private final List<Object> campos = new ArrayList<>();
    private final Animal animal = new Animal();

    public InsertAnimalScreen(Animal a) throws HeadlessException {
        configurarJFrame("Atualizar Animal");
        mapearListaCampos();
        configureCarregarImagem();
        btnInsert.setText("Atualizar");
        animal.setCodigo(a.getCodigo());
        animal.setImagem(a.getImagem());
        txtPeso.setText(a.getPeso()+"");
        txtData.setText(new SimpleDateFormat("dd/MM/yyyy").format(a.getDataNascimento()));
        txtNome.setText(a.getNome());
        txtObservacoes.setText(a.getObservacoes());
        cbCorPelagem.setSelectedIndex(a.getCorPelagem()-1);
        cbTipAnimal.setSelectedIndex(a.getTipoAnimal()-1);
        cbSexo.setSelectedIndex(a.getSexo()-1);
        lblStatusImagem.setText("Imagem original carregada.");
        btnInsert.addActionListener((x) -> {
            atualizarAnimal();
        });

        btnLimpar.addActionListener((x) -> {
            limpar();
        });

        btnFechar.addActionListener((x) -> {
            dispose();
        });
        lblStatusImagem.setText("Imagem original carregada.");

    }

    public InsertAnimalScreen() throws HeadlessException {
        configurarJFrame("Inserir Animal");
        mapearListaCampos();
        configureCarregarImagem();
        btnInsert.setText("Inserir");
        btnInsert.addActionListener((x) -> {
            inserirAnimal();
        });

        btnLimpar.addActionListener((x) -> {
            limpar();
        });

        btnFechar.addActionListener((x) -> {
            dispose();
        });
        lblStatusImagem.setText("Nenhuma imagem carregada!");

    }

    private void configurarJFrame(String tittle){
        setTitle(tittle);
        setResizable(false);
        pack();
        setContentPane(mainPanel);
    }

    private void mapearListaCampos(){
        campos.add(txtNome);
        campos.add(cbTipAnimal);
        campos.add(cbCorPelagem);
        campos.add(cbSexo);
        campos.add(txtPeso);
        campos.add(txtObservacoes);
        campos.add(txtData);
    }

    private void inserirAnimal(){
        if(validarCampos()){
            animal.setTipoAnimal(cbTipAnimal.getSelectedIndex()+1);
            animal.setNome(txtNome.getText());
            animal.setSexo(cbSexo.getSelectedIndex()+1);
            animal.setPeso(Float.parseFloat(txtPeso.getText()));
            animal.setObservacoes(txtObservacoes.getText());
            animal.setCorPelagem(cbCorPelagem.getSelectedIndex()+1);
            AnimalDAO aDao = new AnimalDAO();
            try{
                aDao.insertOne(animal);
                JOptionPane.showMessageDialog(this, "Sucesso ao inserir Animal!");
                limpar();
            }catch (Exception e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao inserir Animal!");
            }
        }else{
            JOptionPane.showMessageDialog(this, "Campos vazios ou inválidos!");
        }
    }

    private boolean validarCampos(){
        for(Object campo: campos){
            if(campo instanceof JTextField){
                if(((JTextField) campo).getText() == null || ((JTextField) campo).getText().isEmpty())
                    return false;
            }
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        if(!txtPeso.getText().toLowerCase().equals(txtPeso.getText().toUpperCase()))
            return false;
        try{
            Date data = sdf.parse(txtData.getText());
            animal.setDataNascimento(data);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return animal.getImagem() != null && !animal.getImagem().isEmpty();
    }

    private void configureCarregarImagem(){
        carregarImagemButton.addActionListener((x) -> {
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
                        animal.setImagem(Base64.getEncoder().encodeToString(Files.readAllBytes(chooser.getSelectedFile().toPath())));
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

    private void limpar(){
        for(Object campo: campos){
            if(campo instanceof JTextField){
                ((JTextField) campo).setText("");
            }else if(campo instanceof JComboBox){
                ((JComboBox<?>) campo).setSelectedIndex(0);
            }
        }
        animal.setImagem("");
        lblStatusImagem.setText("Nenhuma imagem carregada!");
    }

    private void atualizarAnimal(){
        if(validarCampos()){
            try{
                animal.setTipoAnimal(cbTipAnimal.getSelectedIndex()+1);
                animal.setNome(txtNome.getText());
                animal.setSexo(cbSexo.getSelectedIndex()+1);
                animal.setPeso(Float.parseFloat(txtPeso.getText()));
                animal.setObservacoes(txtObservacoes.getText());
                animal.setCorPelagem(cbCorPelagem.getSelectedIndex()+1);
                AnimalDAO aDao = new AnimalDAO();
                aDao.updateOne(animal);
                JOptionPane.showMessageDialog(this, "Sucesso ao atualizar dados do animal");
                limpar();
                dispose();
            }catch (Exception e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao realizar atualização de dados do animal.");
            }
        }else{
            JOptionPane.showMessageDialog(this, "Campos vazios ou inválidos!");
        }

    }
}
