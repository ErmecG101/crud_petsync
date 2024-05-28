package enums;

public class AnimalEnums {
    public static final int GATO = 1, CACHORRO = 2, COELHO = 3;
    public static final int MASCULINO = 1, FEMININO = 2;
    public static final int PRETO = 1, BRANCO = 2, CINZA =3, CARAMELO =4, AMARELO =5, LARANJA =6, MARROM =7, MULTICOLORIDO=8;

    public static String getTipoAnimal(int id){
        switch (id){
            case GATO:
                return "Gato";
            case CACHORRO:
                return "Cachorro";
            case COELHO:
                return "Coelho";
            default:
                return "Unknown";
        }
    }

    public static String getSexoAnimal(int id){
        switch (id){
            case MASCULINO:
                return "Macho";
            case FEMININO:
                return "Fêmea";
            default:
                return "Unknown";
        }
    }

    public static String getCorPelagem(int id){
        switch (id){
            case PRETO:
                return "Preto";
            case BRANCO:
                return "Branco";
            case CINZA:
                return "Cinza";
            case CARAMELO:
                return "Caramelo";
            case AMARELO:
                return "Amarelo";
            case LARANJA:
                return "Laranja";
            case MARROM:
                return "Marrom";
            case MULTICOLORIDO:
                return "Multicolorido";
            default:
                return "Unknown";
        }
    }
}
