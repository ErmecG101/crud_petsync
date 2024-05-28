package daos;

import factory.dbFactory;
import models.Cliente;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private PreparedStatement prrd;
    private ResultSet rs;


    public void insertOne(Cliente c) throws SQLException, ClassNotFoundException{
        try{
            dbFactory.createConnection();
            prrd = dbFactory.getConn().prepareStatement("insert into cliente(cpf, email, endereco, imagem, nome) values(?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            prrd.setString(1, c.getCpf());
            prrd.setString(2, c.getEmail());
            prrd.setString(3, c.getEndereco());
            prrd.setString(4, c.getImagem());
            prrd.setString(5, c.getNome());
            int createdRows = prrd.executeUpdate();

            System.out.println("Inserted "+createdRows+" clients into the database!");

            if(createdRows > 0)
                System.out.println("Insertion successfull!");
            else{
                throw new SQLException("An error on the insertion has ocurred!");
            }
        }finally {
            prrd.close();
            dbFactory.getConn().close();
        }
    }

    public void updateOne(Cliente c) throws SQLException, ClassNotFoundException{
        try{
            dbFactory.createConnection();
            prrd = dbFactory.getConn().prepareStatement("update cliente set email = ? , endereco = ? , imagem = ? , nome = ? where cpf = ?", PreparedStatement.RETURN_GENERATED_KEYS);
            prrd.setString(1, c.getEmail());
            prrd.setString(2, c.getEndereco());
            prrd.setString(3, c.getImagem());
            prrd.setString(4, c.getNome());
            prrd.setString(5, c.getCpf());
            int updatedRows = prrd.executeUpdate();

            System.out.println("Updated "+updatedRows+" clients");
            if(updatedRows > 0){
                System.out.println("Update realized successfully");
            }else{
                System.out.println("Something gone wrong with the update or the client doesn't exist anymore.");
            }
        }finally {
            prrd.close();
            dbFactory.getConn().close();
        }
    }

    public List<Cliente> getAllCliente() throws SQLException, ClassNotFoundException{
        try{
            dbFactory.createConnection();
            prrd = dbFactory.getConn().prepareStatement("select * from cliente");
            boolean success = prrd.execute();
            if(success){
                List<Cliente> list = new ArrayList<>();
                rs = prrd.getResultSet();
                while(rs.next()){
                    list.add(new Cliente(rs));
                }
                return list;
            }else{
                System.out.println("Error ocurred on selecting all Clients");
                throw new SQLException("Error ocurred on selecting all Clients");
            }
        }finally {
            prrd.close();
            rs.close();
            dbFactory.getConn().close();
        }
    }

    public ResultSet getAllClienteRAW() throws SQLException, ClassNotFoundException{
        try{
            dbFactory.createConnection();
            prrd = dbFactory.getConn().prepareStatement("select cpf, nome, email, endereco, imagem from cliente");
            boolean success = prrd.execute();
            if(success){
                List<Cliente> list = new ArrayList<>();
                rs = prrd.getResultSet();
                System.out.println("Select realizado com sucesso, ResultSet: "+rs.isClosed());
                return rs;
            }else{
                System.out.println("Error ocurred on selecting all Clients");
                throw new SQLException("Error ocurred on selecting all Clients");
            }
        }finally {
//            prrd.close();
//            rs.close();
//            dbFactory.getConn().close();
        }
    }

    public void deleteOneByCPF(String CPF) throws SQLException, ClassNotFoundException{
        try{
            dbFactory.createConnection();
            prrd = dbFactory.getConn().prepareStatement("delete from cliente where cpf = ?");
            prrd.setString(1, CPF);
            int deletedRows = prrd.executeUpdate();
            System.out.println("Deleted "+deletedRows+" clients from the database");
            if(deletedRows > 0){
                System.out.println("Clients deleted sucessfully");
            }else{
                System.out.println("Something went wrong on deleting clients, perhaps a wrong CPF?");
            }
        }finally {
            prrd.close();
            dbFactory.getConn().close();
        }
    }
}
