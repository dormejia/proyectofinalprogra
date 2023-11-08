package modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion2 {
    public Connection conexionBD;
    //jdbc:mysql://localhost:3306/?user=root
    private final String puerto = "3306";
    private final String bd = "proyecto_final";
    // jdbc:mysql://localhost:%s/%s?serverTimezone=UTC
    private final String urlcn = String.format("jdbc:mysql://localhost:%s/%s?serverTimezone=UTC", puerto,bd);
    private final String user = "root";
    private final String pass = "1234";
    private final String jdbc = "com.mysql.cj.jdbc.Driver";
    
    public void abrir_cn(){
    
        try{
            Class.forName(jdbc);
            conexionBD = DriverManager.getConnection(urlcn, user, pass);
            System.out.println("Conexion Exitosa... ");
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println("EROR " + ex.getMessage());
    
        }
    
    }
    
    public void cerrar_cn(){
        try{
            
            conexionBD.close();
        
        }catch(SQLException ex){
            
            System.out.println("EROR " + ex.getMessage());
    
        }
    }
    }

    //void cerrar_conexion() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    

    //void abrir_conexion() {
       /* throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    class conexionBD {

        static Object createStatement() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public conexionBD() {
        }
    }*/
    
