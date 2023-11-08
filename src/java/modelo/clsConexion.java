
package modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class clsConexion {
    public Connection cnn ;
    private final String pueto="3306";
    private final String BD="proyecto_final";
   // jdbc:mysql://localhost:3306/?user=root
    private final String urlconection= String.format("jdbc:mysql://localhost:%s/%s?serverTimezone=UTC",pueto,BD);
    private final String usuario ="root";
    private final String contraseña="1234";
    private final String jdbc="com.mysql.cj.jdbc.Driver";
    //abrir conexion
     public  void abrir(){
        try{
            Class.forName(jdbc);
            cnn=DriverManager.getConnection(urlconection, usuario, contraseña);
            System.out.println("si se conecto:) ");
         //   JOptionPane.showMessageDialog(null,"Conexion exitosa","exito",JOptionPane.INFORMATION_MESSAGE);//messagebox
        }catch(ClassNotFoundException  | SQLException ex){
         System.out.println("error "+ ex.getMessage());
        }
    
    }
    //cerrar conexion
     public  void cerrar()
     {
        try{
           Class.forName(jdbc);
           cnn.close();
            System.out.println("si se desconecto:) ");
        }catch(ClassNotFoundException  | SQLException ex){
         System.out.println("error "+ ex.getMessage());
        }
    }
    // metodos
}
