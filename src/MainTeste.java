import daos.AnimalDAO;
import models.Animal;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MainTeste {
    public static void main(String[] args) {

//        Animal a = new Animal();
//        a.setCodigo(1);
//        a.setNome("Robertinhoo");
//        a.setImagem("IMAGEM AQUI");
//        a.setCorPelagem(2);
//        a.setObservacoes("OBSERVAÇÕES");
//        a.setDataNascimento(new Date());
//        a.setPeso(4.7f);
//        a.setSexo(1);
//        a.setTipoAnimal(1);

        AnimalDAO aDao = new AnimalDAO();
        try{
            aDao.deleteById(1);
            System.out.println("Sucesso ao deletar Registro");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Erro ao deletar registro");
        }

    }
}
