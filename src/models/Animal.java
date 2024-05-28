package models;

import enums.AnimalEnums;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Animal {

    private int codigo;
    private String nome;
    private int tipoAnimal;
    private int corPelagem;
    private Date dataNascimento;
    private int sexo;
    private float peso;
    private String observacoes;
    private String imagem;

    public Animal() {
    }

    public Animal(ResultSet rs) throws SQLException {
        this.codigo = rs.getInt("codigo");
        this.nome = rs.getString("nome");
        this.tipoAnimal = rs.getInt("tipoAnimal");
        this.corPelagem = rs.getInt("pelagem");
        this.dataNascimento = new Date(rs.getDate("dataNascimento").getTime());
        this.sexo = rs.getInt("sexo");
        this.peso = rs.getFloat("peso");
        this.observacoes = rs.getString("observacoes");
        this.imagem = rs.getString("imagem");
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTipoAnimal() {
        return tipoAnimal;
    }

    public void setTipoAnimal(int tipoAnimal) {
        this.tipoAnimal = tipoAnimal;
    }

    public int getCorPelagem() {
        return corPelagem;
    }

    public void setCorPelagem(int corPelagem) {
        this.corPelagem = corPelagem;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "codigo=" + codigo +
                ", nome='" + nome + '\'' +
                ", tipoAnimal=" + tipoAnimal +
                ", corPelagem=" + corPelagem +
                ", dataNascimento=" + dataNascimento +
                ", sexo=" + sexo +
                ", peso=" + peso +
                ", observacoes='" + observacoes + '\'' +
                ", imagem='" + imagem + '\'' +
                '}';
    }
}
