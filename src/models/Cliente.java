package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Cliente {

    private String cpf;
    private String nome;
    private String email;
    private String imagem;
    private String endereco;

    public Cliente() {
    }

    public Cliente(ResultSet rs) throws SQLException {
        this.cpf = rs.getString("cpf");
        this.nome = rs.getString("nome");
        this.email = rs.getString("email");
        this.imagem = rs.getString("imagem");
        this.endereco = rs.getString("endereco");
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", imagem='" + imagem + '\'' +
                ", endereco='" + endereco + '\'' +
                '}';
    }
}
