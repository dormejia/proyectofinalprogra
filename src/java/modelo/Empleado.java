/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Pablo
 */
public class Empleado extends clsPersona{
 
    private conexion_1 cn;
    private int id, idpuesto;
    private String fecha_inicio_labores, fecha_ingreso;
    
    private String pass,user,dpi;
    private int rol1,rol2;
   String datos [] = new String[6];
    public Empleado(String pass, String user, int rol1, int rol2) {
        this.pass = pass;
        this.user = user;
        this.rol1 = rol1;
        this.rol2 = rol2;
    }

    public Empleado(String pass, String user, int rol1, int rol2, String nombres, String apellidos, String direccion, String telefono, String dpi, String fecha_nacimiento, int genero) {
        super(nombres, apellidos, direccion, telefono, dpi, fecha_nacimiento, genero);
        this.pass = pass;
        this.user = user;
        this.rol1 = rol1;
        this.rol2 = rol2;
    }
    
    
    public Empleado() {
    }

    public Empleado(int id, int idpuesto, String fecha_inicio_labores, String fecha_ingreso, String nombres, String apellidos, String direccion, String telefono, String dpi, String fecha_nacimiento, int genero) {
        super(nombres, apellidos, direccion, telefono, dpi, fecha_nacimiento, genero);
        this.id = id;
        this.idpuesto = idpuesto;
        this.fecha_inicio_labores = fecha_inicio_labores;
        this.fecha_ingreso = fecha_ingreso;
    }

     public int getRol1() {
        return rol1;
    }

    public void setRol1(int rol1) {
        this.rol1 = rol1;
    }

    public int getRol2() {
        return rol2;
    }

    public void setRol2(int rol2) {
        this.rol2 = rol2;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    public int getIdpuesto() {
        return idpuesto;
    }

    public void setIdpuesto(int idpuesto) {
        this.idpuesto = idpuesto;
    }

    public String getFecha_inicio_labores() {
        return fecha_inicio_labores;
    }

    public void setFecha_inicio_labores(String fecha_inicio_labores) {
        this.fecha_inicio_labores = fecha_inicio_labores;
    }

    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }
 public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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
      //
       
      
    // leer
    public DefaultTableModel leer(){
        DefaultTableModel tabla = new DefaultTableModel();
        try {
            cn = new conexion_1();
            cn.abrir_cn();
            String query="";
            query = "SELECT idempleados as id,nombres,apellidos,direccion,telefono,dpi,generos.genero as genero1,fecha_nacimiento,puestos.puestos as puesto,fecha_inicio_labores,fecha_ingreso, empleados.idpuesto, empleados.genero as idge\n" +
" FROM empleados \n" +
" inner join puestos on empleados.idpuesto= puestos.idpuesto \n" +
" inner join generos on empleados.genero= generos.idgenero ;";
            ResultSet consulta = cn.cnbd.createStatement().executeQuery(query);
            String encabezado[] = {"id","Nombres","Apellidos","Direccion","Telefono","DPI","Genero","Fecha Nacimiento","Id puesto","Fecha Inicio","Fecha Ingreso", "idpuesto","idge"};
            tabla.setColumnIdentifiers(encabezado);
            String datos [] = new String[13];
            while (consulta.next()){
                datos[0] = consulta.getString("id");
                datos[1] = consulta.getString("nombres");
                datos[2] = consulta.getString("apellidos");
                datos[3] = consulta.getString("direccion");
                datos[4] = consulta.getString("telefono");
                datos[5] = consulta.getString("dpi");
                datos[6] = consulta.getString("genero1");
                datos[7] = consulta.getString("fecha_nacimiento");
                datos[8] = consulta.getString("puesto");
                datos[9] = consulta.getString("fecha_inicio_labores");
                datos[10] = consulta.getString("fecha_ingreso");
                datos[11] = consulta.getString("idpuesto");
                datos[12] = consulta.getString("idge");
                tabla.addRow(datos);
            }     
            cn.cerrar_cn();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
        return tabla;
    }
    // mostrar empleados si usuario
    
    public DefaultTableModel leersitieneusuario(){
     DefaultTableModel tabla = new DefaultTableModel();
    try {
            cn = new conexion_1();
            cn.abrir_cn();
            String query="SELECT concat(nombres,' ',apellidos)as nombre, tieneusuario,idempleados,puestos.puestos as puesto from empleados inner join puestos on empleados.idpuesto = puestos.idpuesto WHERE tieneusuario  = 0;";
          
            ResultSet consulta = cn.cnbd.createStatement().executeQuery(query);
            String encabezado[] = {"Nombre","TIENE USUARIO","id","PUESTO"};
            tabla.setColumnIdentifiers(encabezado);
            String datos [] = new String[4];
            while (consulta.next()){
                datos[0] = consulta.getString("nombre");
                datos[1] = consulta.getString("tieneusuario");
                datos[2] = consulta.getString("idempleados");
                 datos[3] = consulta.getString("puesto");
             
                tabla.addRow(datos);
            }     
            cn.cerrar_cn();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    return tabla;
    }
    // agregar usuario
    public int agregarUsuario(){
 int retorno1 = 0;
 try {
            
            Empleado a = new Empleado();
           cn = new conexion_1();
            PreparedStatement parametro;
            cn.abrir_cn();
            String query;
            query = "INSERT INTO user_trabajador (idempleado, usuario, contrasena, rolventa, roladmin) VALUES (?, ?, ?, ?, ?);";
            parametro = (PreparedStatement)cn.cnbd.prepareStatement(query);
            parametro.setInt(1,getIdpuesto());
            parametro.setString(2, getUser() );
            parametro.setString(3, a.MD5(getPass()) );
            parametro.setInt(4, getRol1());
            parametro.setInt(5, getRol2());
           
            retorno1 = parametro.executeUpdate();            
            System.out.println("Se inserto: "+ Integer.toString(retorno1)+ "Registro");
            cn.cerrar_cn();
         
        } catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
            return 0;
        }
 try{
        cn = new conexion_1();
        PreparedStatement parametro;
        String query="UPDATE empleados SET tieneusuario = '1' WHERE (idempleados = ?);";
        cn.abrir_cn();
        parametro =  (PreparedStatement)cn.cnbd.prepareStatement(query);
        parametro.setInt(1, this.getIdpuesto());
        retorno1 = parametro.executeUpdate();
        cn.cerrar_cn();
        
    }catch(SQLException ex){
        System.out.println(ex.getMessage());
        retorno1 = 0;
    }
     
 
 
 
    return retorno1;
 
 
  }
    //login
    
       // login
    public int login (){
     
        int executar =0;
        
         try {
            cn = new conexion_1();
             cn.abrir_cn();
            String query;
            query = "SELECT iduser_trabajador as id, idempleado,usuario,contrasena,rolventa,roladmin FROM user_trabajador WHERE usuario='"+this.getUser()+"';";
            ResultSet consulta = cn.cnbd.createStatement().executeQuery(query);
             
            while (consulta.next()){
                datos[0] = consulta.getString("id");
                datos[1] = consulta.getString("idempleado");
                datos[2] = consulta.getString("usuario");
                datos[3]=consulta.getString("contrasena");
                datos[4]=consulta.getString("rolventa");
                datos[5]=consulta.getString("roladmin");
                break;
            }
          
               String pass1 = MD5(getPass());
            //comparacion  
               cn.cerrar_cn();
            
        } catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
            
        }
         
         Clientes a = new Clientes();
         
         
         if(datos[3].equals(a.MD5(getPass()))&& datos[2].equals(getUser()) ) {
          //acutliazar
        executar =2;
         }else{
         executar =0;
         
         }
        
         
        
    return executar;
    }
    
    // agregar
    @Override
    public int agregar(){
        int retorno = 0;
    try{
        cn = new conexion_1();
        PreparedStatement parametro;
        String query="INSERT INTO empleados(nombres,apellidos, direccion,telefono,dpi, genero, fecha_nacimiento, idpuesto, fecha_inicio_labores) VALUES (?,?,?,?,?,?,?,?,?);";
        cn.abrir_cn();
        parametro =  (PreparedStatement)cn.cnbd.prepareStatement(query);
        parametro.setString(1, getNombres());
        parametro.setString(2, getApellidos());
        parametro.setString(3, getDireccion());
        parametro.setString(4, getTelefono());
        parametro.setString(5, getDpi());
        parametro.setInt(6, getGenero());
        parametro.setString(7, getFecha_nacimiento());
        parametro.setInt(8, getIdpuesto());
        parametro.setString(9, getFecha_inicio_labores());
        
        
        retorno = parametro.executeUpdate();
        cn.cerrar_cn();
        
    }catch(SQLException ex){
        System.out.println(ex.getMessage());
        retorno = 0;
    }
    return retorno;
    }
    
    @Override
    public int modificar(){
        int retorno = 0;
    try{
        cn = new conexion_1();
        PreparedStatement parametro;
        String query="update empleados set nombres=?,apellidos=?,direccion=?,telefono=?,dpi=?, genero=?, fecha_nacimiento=?,idpuesto=?, fecha_inicio_labores=? where idempleados=?;";
        cn.abrir_cn();
        parametro =  (PreparedStatement)cn.cnbd.prepareStatement(query);
        parametro.setString(1, getNombres());
        parametro.setString(2, getApellidos());
        parametro.setString(3, getDireccion());
        parametro.setString(4, getTelefono());
        parametro.setString(5, getDpi());
        parametro.setInt(6, getGenero());
        parametro.setString(7, getFecha_nacimiento());
        parametro.setInt(8, this.getIdpuesto());
        parametro.setString(9, getFecha_inicio_labores());
       
        parametro.setInt(10, this.getId());
        retorno = parametro.executeUpdate();
        cn.cerrar_cn();
        
    }catch(SQLException ex){
        System.out.println(ex.getMessage());
        retorno = 0;
    }
    return retorno;
    }
    
    
    @Override
    public int eliminar(){
        int retorno = 0;
    try{
        cn = new conexion_1();
        PreparedStatement parametro;
        String query="delete from empleados where idempleados=?;";
        cn.abrir_cn();
        parametro =  (PreparedStatement)cn.cnbd.prepareStatement(query);
        parametro.setInt(1, this.getId());
        retorno = parametro.executeUpdate();
        cn.cerrar_cn();
        
    }catch(SQLException ex){
        System.out.println(ex.getMessage());
        retorno = 0;
    }
    return retorno;
    }
   
    
    
    public static void main(String [] args){
        System.out.println("Hola Loca");
        Empleado cl = new Empleado();
        
        //cl.setId(2);
        cl.setNombres("Nicolas");
        cl.setApellidos("mendez");
        cl.setDireccion("Guate");
        cl.setTelefono("464654");
        cl.setDpi("654656");
        cl.setGenero(1);
        cl.setFecha_nacimiento("2000-01-01");
        int resultado=cl.agregar();
       DefaultTableModel tabla = new DefaultTableModel();
       tabla=cl.leer();
        System.out.println("El resultado es"+resultado);
        //System.out.println("El resultado es"+tabla);
    }

   

   
}
