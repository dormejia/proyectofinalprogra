
package modelo;
import modelo.Conexion;
import modelo.Clientes;


import java.sql.ResultSet;
import java.util.HashMap;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;


import javax.swing.table.DefaultTableModel;

import org.apache.tomcat.util.codec.binary.Base64;
public class clsproveedor extends Clientes {
    
      Conexion  cn ;
    
     private int idproveedor ;
     private String proveedro;
     private String direccion;

    public int getIdproveedor() {
        return idproveedor;
    }

    public void setIdproveedor(int idproveedor) {
        this.idproveedor = idproveedor;
    }

    public String getProveedro() {
        return proveedro;
    }

    public void setProveedro(String proveedro) {
        this.proveedro = proveedro;
    }
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
//constructores
    public clsproveedor(int idproveedor, String proveedro, String direccion) {
        this.idproveedor = idproveedor;
        this.proveedro = proveedro;
        this.direccion = direccion;
    }

    public clsproveedor(int idproveedor, String proveedro, String direccion, String nit, String correo, String fn_ingreso, int id, String nombres, String apellidos, String telefono, String passw, int genero) {
        super(nit, correo, fn_ingreso, id, nombres, apellidos, telefono, passw, genero);
        this.idproveedor = idproveedor;
        this.proveedro = proveedro;
        this.direccion = direccion;
    }
    
     public clsproveedor(){}
    
     //metodos
     @Override
     public int agregar(){
          int retorno = 0;
        
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            cn.abrir_conexion();
            String query;
            query = "INSERT INTO proveedores (proveedor, nit, direccion, telefono) VALUES (?, ?, ?, ?);";
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setString(1,getProveedro());
            parametro.setString(2,this.getNit());
           parametro.setString(3, this.getDireccion());
            parametro.setString(4,getTelefono());

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
    public int eliminar(){
         int executar =0;
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            cn.abrir_conexion();
            String query;
            query = "DELETE FROM proveedores WHERE idproveedores=?;";
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, this.getIdproveedor());
            executar = parametro.executeUpdate();
            System.out.println("se Actualizo: "+ Integer.toString(executar)+ "Registro");
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
        }
        return executar;
     }
    public DefaultTableModel buscar(){
         DefaultTableModel tabla= new DefaultTableModel ();
       try{
       cn = new Conexion();
       cn.abrir_conexion();
       String query="SELECT idproveedores as id, proveedor,nit,direccion,telefono  FROM proveedores;";
      ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
      String encabezado[] = {"ID","PROVEEDOR","NIT","DIRECCION","TELEFONO"};
      tabla.setColumnIdentifiers(encabezado);
      String datos [] = new String[5];
      while(consulta.next()){
          datos[0]=consulta.getString("id");
          datos[1]=consulta.getString("proveedor");
          datos[2]=consulta.getString("nit");
          datos[3]=consulta.getString("direccion");
          datos[4]=consulta.getString("telefono");
 tabla.addRow(datos);
      }
        cn.cerrar_conexion();
       }catch(SQLException ex){
        System.out.println("error "+ex);
       }
        
    return tabla;
     }
     @Override
    public int modificar(){
          int executar=0;
      try {
            PreparedStatement parametro;
            cn = new Conexion();
            cn.abrir_conexion();
            String query;
            query = "UPDATE proveedores SET proveedor = ?, nit = ?, direccion = ?, telefono = ? WHERE (idproveedores = ?);";
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
           parametro.setString(1,getProveedro());
            parametro.setString(2,this.getNit());
           parametro.setString(3, this.getDireccion());
            parametro.setString(4,getTelefono());
            parametro.setInt(5, this.getIdproveedor());
            executar = parametro.executeUpdate();
            System.out.println("se Actualizo: "+ Integer.toString(executar)+ "Registro");
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
        }
        return executar;
}
    
     public HashMap tipoproveedor(){
    HashMap<String,String> drop1 = new HashMap();
    try{
       cn = new Conexion();
            cn.abrir_conexion();
        String query="SELECT idproveedores, proveedor FROM proveedores;";
        
        
        ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
        while(consulta.next()){
        drop1.put(consulta.getString("idproveedores"),consulta.getString("proveedor"));
        
        }
        
                    cn.cerrar_conexion();

    }catch(Exception ex){
    System.out.println("error"+ex);
    }
    return drop1;
    }
    
    
    
    
}