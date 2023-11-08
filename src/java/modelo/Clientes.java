/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashMap;


import javax.swing.table.DefaultTableModel;


//


/**
 *
 * @author braya
 */
public class Clientes extends Persona {
    
    private String idcliente ;
   
    String datos [] = new String[4];
    private String nit,correo,fn_ingreso;
    private int id;
    private Conexion cn;
    private String pass;
    public Clientes(){}


    
    public Clientes(String nit, String correo, String fn_ingreso, int id, String nombres, String apellidos, String telefono, String passw, int genero) {
        super(nombres, apellidos, telefono, passw, genero);
        this.nit = nit;
        this.correo = correo;
        this.fn_ingreso = fn_ingreso;
        this.id = id;
        
    }

      public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
public String getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(String idcliente) {
        this.idcliente = idcliente;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFn_ingreso() {
        return fn_ingreso;
    }

    public void setFn_ingreso(String fn_ingreso) {
        this.fn_ingreso = fn_ingreso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Conexion getCn() {
        return cn;
    }

    public void setCn(Conexion cn) {
        this.cn = cn;
    }
    
    //incripar clave
      public String MD5(String md5) { 
    try{
    java.security.MessageDigest md=  java.security.MessageDigest.getInstance("MD5"); 
     byte[]array = md.digest(md5.getBytes());
     StringBuffer sb = new StringBuffer();
     
     for(int i=0;i< array.length;i++){
     sb.append(Integer.toBinaryString((array[i] & 0xFF)| 0x100).substring(1,3));
     }
     return sb.toString();
     
    }catch(Exception ex){
    System.out.println("error"+ex);
    }
    
    return null;
      }
    // desencriptar
   
      //
          public HashMap tipomarca(){
    HashMap<String,String> drop = new HashMap();
    try{
        cn = new Conexion();
            cn.abrir_conexion();
      
        String query="SELECT idcliente,nit FROM cliente;";
        
        
        ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
        while(consulta.next()){
        drop.put(consulta.getString("idcliente"),consulta.getString("nit"));
        
        }
        
         cn.cerrar_conexion();
    }catch(Exception ex){
    System.out.println("error"+ex);
    }
    return drop;
    }
  //
    public DefaultTableModel leer(){
        DefaultTableModel tabla = new DefaultTableModel();
        try {
            cn = new Conexion();
            cn.abrir_conexion();
            String query;
            query = "Select idcliente as id, nombres, apellidos, nit, generos.genero as genero ,telefono,correo_electronico,fecha_ingreso from cliente inner join generos on cliente.genero = generos.idgenero;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            String encabezado[] = {"id","Nombres","Apellidos","Nit","Genero","Telefono","Correo","Fecha_Ingreso"};
            tabla.setColumnIdentifiers(encabezado);
            String datos [] = new String[8];
            while (consulta.next()){
                datos[0] = consulta.getString("id");
                datos[1] = consulta.getString("Nombres");
                datos[2] = consulta.getString("Apellidos");
                datos[3] = consulta.getString("Nit");
                datos[4] = consulta.getString("Genero");
                datos[5] = consulta.getString("Telefono");
                datos[6] = consulta.getString("Correo_Electronico");
                datos[7] = consulta.getString("Fecha_Ingreso");
                tabla.addRow(datos);
            }
                
            cn.cerrar_conexion();
            
        } catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
            
        }
        
        return tabla;
    }
   // login
    public int login (){
     
        int executar =0;
        
         try {
            cn = new Conexion();
            cn.abrir_conexion();
            String query;
            query = "SELECT usuario,contrasena,idcliente,iduser_clientes FROM user_clientes WHERE usuario ='"+this.getCorreo()+"';";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
             
            while (consulta.next()){
                datos[0] = consulta.getString("usuario");
                datos[1] = consulta.getString("contrasena");
                datos[2] = consulta.getString("idcliente");
                datos[3]=consulta.getString("iduser_clientes");
                 
                break;
            }
          
               String pass1 = MD5(getPassw());
            //comparacion  
            cn.cerrar_conexion();
            
        } catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
            
        }
         
         Clientes a = new Clientes();
         
         
         if(datos[1].equals(a.MD5(getPass()))&& datos[0].equals(getCorreo()) ) {
          //acutliazar
          try {
            int conTrue=1;
            
           
            PreparedStatement parametro;
            cn.abrir_conexion();
            String query;
            query = "UPDATE user_clientes SET conectado = ? WHERE (iduser_clientes = ?);";
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setInt(1,conTrue);
            parametro.setInt(2, Integer.parseInt(datos[3]) );
           
            executar = parametro.executeUpdate();            
            System.out.println("Se inserto: "+ Integer.toString(executar)+ "Registro");
            cn.cerrar_conexion();
           // borrar datos
          
           
           
            
        } catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
            return 0;
        }
         
         }else{
         executar =0;
         
         }
        
         
        
    return executar;
    }
   
    
    //--------------
    /**
     *
     * @return 
     */
@Override
    public int agregar(){
        int retorno = 0;
        
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            cn.abrir_conexion();
            String query;
            query = "INSERT INTO cliente(nombres,apellidos,nit,genero,telefono,correo_electronico,fecha_ingreso) VALUES(?,?,?,?,?,?,?);";
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setString(1,getNombres());
            parametro.setString(2,getApellidos());
            parametro.setString(3,getNit());
            parametro.setInt(4,getGenero());
            parametro.setString(5,getTelefono());
            parametro.setString(6,getCorreo());
            parametro.setString(7,getFn_ingreso());
            retorno = parametro.executeUpdate();            
            System.out.println("Se inserto: "+ Integer.toString(retorno)+ "Registro");
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
            return 0;
        }
    
    return retorno;
    
    }
    // agregar usuario
public int agregarUsuario(){

 int retorno = 0;
        
 // buscar id de cliente para guardarlo
 try {
            cn = new Conexion();
            cn.abrir_conexion();
            String query;
            query = "SELECT idcliente FROM cliente WHERE nit='"+this.getNit()+"';";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            while (consulta.next()){
                idcliente = consulta.getString("idcliente");
                break;
            }
                
            cn.cerrar_conexion();
            
        } catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
            
        }
 //insertar a la tabla
        try {
            
            Clientes a = new Clientes();
           
            PreparedStatement parametro;
            cn.abrir_conexion();
            String query;
            query = "INSERT INTO user_clientes (usuario, contrasena, idcliente) VALUES (?, ?, ?);";
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setString(1,getCorreo());
            parametro.setString(2, a.MD5(getPassw()) );
            parametro.setInt(3, Integer.parseInt(idcliente));
           
            retorno = parametro.executeUpdate();            
            System.out.println("Se inserto: "+ Integer.toString(retorno)+ "Registro");
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
            return 0;
        }
    
    return retorno;
    
}






    @Override
  public int modificar(){
      int executar=0;
      try {
            PreparedStatement parametro;
            cn = new Conexion();
            cn.abrir_conexion();
            String query;
            query = "update cliente set nombres=?,apellidos=?,nit=?,genero=?,telefono=?,correo_electronico=?,fecha_ingreso=? where idcliente=?";
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setString(1,getNombres());
            parametro.setString(2,getApellidos());
            parametro.setString(3,getNit());
            parametro.setInt(4,getGenero());
            parametro.setString(5,getTelefono());
            parametro.setString(6,getCorreo());
            parametro.setString(7,getFn_ingreso());
            parametro.setInt(8, getId());
            executar = parametro.executeUpdate();
            System.out.println("se Actualizo: "+ Integer.toString(executar)+ "Registro");
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
        }
        return executar;
    }
   public HashMap genero(){
    HashMap<String,String> drop = new HashMap();
    try{
         cn = new Conexion();
            cn.abrir_conexion();
        String query="SELECT idgenero,genero FROM generos;";
        
        
        ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
        while(consulta.next()){
        drop.put(consulta.getString("idgenero"),consulta.getString("genero"));
        
        }
        
        cn.cerrar_conexion();
    }catch(Exception ex){
    System.out.println("error"+ex);
    }
    return drop;
    }
    @Override
    public int eliminar(){
        int executar =0;
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            cn.abrir_conexion();
            String query;
            query = "delete from cliente where idcliente=?;";
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getId());
            executar = parametro.executeUpdate();
            System.out.println("se Actualizo: "+ Integer.toString(executar)+ "Registro");
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
        }
        return executar;
}  
    
    

    public static void main(String [] args){
        System.out.println("Hola Loca");
        Clientes cl = new Clientes();
        
        cl.setId(2);
        cl.setNombres("Nicolas");
        cl.setApellidos("mendez");
        cl.setNit("635638");
        cl.setGenero(1);
        cl.setTelefono("6547746");
        cl.setCorreo("jhdfkahdsf");
        cl.setFn_ingreso("2023-09-09");
        int resultado = cl.eliminar();
//        DefaultTableModel tabla = new DefaultTableModel();
//        tabla = cl.leer();
        System.out.println("El resultado es"+resultado);
//        System.out.println("El resultado es"+tabla);
    }

  

    
    
       
}
