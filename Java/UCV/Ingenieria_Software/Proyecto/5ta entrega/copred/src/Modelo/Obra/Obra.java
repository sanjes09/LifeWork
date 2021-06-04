package Modelo.Obra;
import javax.swing.Icon;

public abstract class Obra {
    //ATRIBUTOS
    private String titulo;
    private String identificador;
    private String aCreacion;
    private String nombreA;
    private String apellidoA;
    private String Ubicacion;
    private char estatus;
    private String descripcion;
    private Icon imagen;

    //Tipos
    public static final String TYPE_FOTO="OA-FOT";
    public static final String TYPE_ESCULTURA="OA-ESC";
    public static final String TYPE_ARQUITECTURA="A-A";
    public static final String TYPE_VITRAL="OA-VIT";
    public static final String TYPE_ELEMENTO_PLASTICO="OA-ELP";
    public static final String TYPE_MOSAICO="OA-MOS";
    public static final String TYPE_MURAL="OA-MUR";
    public static final String TYPE_MURAL_DIPTICO="OA-MUD";
    public static final String TYPE_MURAL_TRIPTICO="OA-MUT";
    public static final String TYPE_MURAL_POLIPTICO="OA-MUP";
    public static final String TYPE_PINTURA="OA-PIN";
    public static final String TYPE_PINTURA_DIPTICO="OA-PID";
    public static final String TYPE_PINTURA_TRIPTICO="OA-PIT";
    public static final String TYPE_PINTURA_POLIPTICO="OA-PIP";
    
    //MÉTODOS
        //CONSTRUCTOR
        public Obra(String titulo, String identificador, String aCreacion, String nombreA, String Ubicacion,
        char estatus, String descripcion,Icon imagen, String apellidoA){
       
        this.titulo = titulo;
        this.identificador = identificador;
        this.aCreacion = aCreacion;
        this.nombreA = nombreA;
        this.Ubicacion = Ubicacion;
        this.estatus = estatus;
        this.descripcion = descripcion;
        this.apellidoA = apellidoA;
        this.imagen = imagen;
    
        }

        //SET'S Y GET'S PARA CADA ATRIBUTO
        public String get_Titulo(){

            return titulo;

        }

        public void set_Titulo(String titulo){

            this.titulo = titulo;

        }

        public String get_Identificador(){

            return identificador;

        }

        public void set_Identificador(String identificador){

            this.identificador = identificador;

        }

        public String get_ACreacion(){

            return aCreacion;

        }

        public void set_ACreacion(String aCreacion){

            this.aCreacion = aCreacion;

        }

        public String get_NombreA(){

            return nombreA;

        }

        public void set_NombreA(String nombreA){

            this.nombreA = nombreA;

        }

        public String get_Ubicacion(){

            return Ubicacion;

        }

        public void set_Ubicacion(String Ubicacion){

            this.Ubicacion = Ubicacion;

        }

        public char get_Estatus(){

            return estatus;

        }

        public void set_Estatus(char estatus){

            this.estatus = estatus;

        }

        public String get_Descripcion(){

            return descripcion;

        }

        public void set_Descripcion(String descripcion){

            this.descripcion = descripcion;

        }

        public Icon get_Imagen(){

            return imagen;

        }

        public void set_Imagen(Icon imagen){

            this.imagen = imagen;

        }

        public String get_ApellidoA(){

            return apellidoA;

        }

        public void set_ApellidoA(String apellidoA){
       
        this.apellidoA = apellidoA;
    
    }
    
}
