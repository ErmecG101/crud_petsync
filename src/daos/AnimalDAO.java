package daos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import factory.dbFactory;
import models.Animal;

public class AnimalDAO {

    private PreparedStatement prrd;
    private ResultSet rs;

    public void insertOne(Animal a) throws SQLException, ClassNotFoundException {
        try{
            dbFactory.createConnection();
            prrd = dbFactory.getConn().prepareStatement("insert into animal(nome, tipoAnimal, pelagem, dataNascimento, sexo, peso, observacoes, imagem)values(?,?,?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            prrd.setString(1, a.getNome());
            prrd.setInt(2, a.getTipoAnimal());
            prrd.setInt(3, a.getCorPelagem());
            prrd.setDate(4, new Date(a.getDataNascimento().getTime()));
            prrd.setInt(5, a.getSexo());
            prrd.setFloat(6, a.getPeso());
            prrd.setString(7, a.getObservacoes());
            prrd.setString(8, a.getImagem());
            int insertedRows = prrd.executeUpdate();
            System.out.println("Inserted "+insertedRows+" rows at database.");
            if(insertedRows > 0)
                System.out.println("Success! Animal inserted sucessfully.");
            else{
                System.out.println("Error on inserting Animal.");
                throw new SQLException("Error on inserting Animal.");
            }
        }finally {
            prrd.close();
            dbFactory.getConn().close();
        }
    }

    public void updateOne(Animal a)throws SQLException, ClassNotFoundException{
        try{
            dbFactory.createConnection();
            prrd = dbFactory.getConn().prepareStatement("UPDATE animal SET nome=?,tipoAnimal=?,pelagem=?,dataNascimento=?,sexo=?,peso=?,observacoes=?,imagem=? WHERE codigo = ?",PreparedStatement.RETURN_GENERATED_KEYS);
            prrd.setString(1, a.getNome());
            prrd.setInt(2, a.getTipoAnimal());
            prrd.setInt(3, a.getCorPelagem());
            prrd.setDate(4, new Date(a.getDataNascimento().getTime()));
            prrd.setInt(5, a.getSexo());
            prrd.setFloat(6, a.getPeso());
            prrd.setString(7, a.getObservacoes());
            prrd.setString(8, a.getImagem());
            prrd.setInt(9, a.getCodigo());
            int updatedRows = prrd.executeUpdate();
            System.out.println("Updated "+updatedRows+" rows");
            if(updatedRows > 0)
                System.out.println("Sucess on updating rows!");
            else{
                System.out.println("Something went wrong and no rows were updated, maybe codigo was incorrect");
            }
        }finally{
            prrd.close();
            dbFactory.getConn().close();
        }
    }

    public List<Animal> getAllAnimal() throws SQLException, ClassNotFoundException{
        try{
            dbFactory.createConnection();
            prrd = dbFactory.getConn().prepareStatement("select * from animal");
            boolean result = prrd.execute();
            if(result){
                List<Animal> list = new ArrayList<>();
                rs = prrd.getResultSet();
                while(rs.next()){
                    list.add(new Animal(rs));
                }
                return list;
            }else{
                System.out.println("Error ocurred on selecting all Animals");
                throw new SQLException("Error ocurred on selecting all Animals");
            }
        }finally {
            prrd.close();
            rs.close();
            dbFactory.getConn().close();
        }
    }

    public ResultSet getAllAnimalRAW() throws SQLException, ClassNotFoundException{
        try{
            dbFactory.createConnection();
            prrd = dbFactory.getConn().prepareStatement("select * from animal");
            boolean result = prrd.execute();
            if(result){
                rs = prrd.getResultSet();
                System.out.println("Select realizado, resultSet: "+rs.isClosed());
                return rs;
            }else{
                System.out.println("Error ocurred on selecting all Animals");
                throw new SQLException("Error ocurred on selecting all Animals");
            }
        }finally {
//            prrd.close();
//            rs.close();
//            dbFactory.getConn().close();
        }
    }

    public void deleteById(int id) throws SQLException, ClassNotFoundException{
        try{
            dbFactory.createConnection();
            prrd = dbFactory.getConn().prepareStatement("delete from animal where codigo = ?");
            prrd.setInt(1, id);
            int deletedRows = prrd.executeUpdate();
            System.out.println("Deleted "+deletedRows);
            if(deletedRows > 0){
                System.out.println("Successfully deleted "+deletedRows+" rows!");
            }else{
                System.out.println("Failed to delete any rows, are you sure the ID is correct?");
            }
        }finally {
            prrd.close();
            dbFactory.getConn().close();
        }
    }

    public Animal selectById(int id) throws SQLException, ClassNotFoundException{
        try{
            dbFactory.createConnection();
            prrd = dbFactory.getConn().prepareStatement("select * from animal where codigo = ?");
            prrd.setInt(1, id);
            boolean result = prrd.execute();
            if(result){
                rs = prrd.getResultSet();
                if(rs.next())
                    return new Animal(rs);
                else
                    return null;
            }
            return null;
        }finally {
            prrd.close();
            rs.close();
            dbFactory.getConn().close();
        }
    }
}
