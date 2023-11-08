
package modelo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashMap;


import javax.swing.table.DefaultTableModel;


public class ventas {
    private int nofactura;
    private String serie;
    private String fecha_factura;
    private int idcliente;
    private int idempleado;
    private String fecha_ingreso;
    private int idventa;
    private int idproducto;
    private int cantidad;
    private double precio_unitario;
    private String user;
    private String nit;
    
   int idcliente1;
          int idempleado1;
     
int existecia1;
    
    Conexion cn;
     public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }
     public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    public int getNofactura() {
        return nofactura;
    }

    public void setNofactura(int nofactura) {
        this.nofactura = nofactura;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getFecha_factura() {
        return fecha_factura;
    }

    public void setFecha_factura(String fecha_factura) {
        this.fecha_factura = fecha_factura;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public int getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(int idempleado) {
        this.idempleado = idempleado;
    }

    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public int getIdventa() {
        return idventa;
    }

    public void setIdventa(int idventa) {
        this.idventa = idventa;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public ventas(int nofactura, String serie, String fecha_factura, int idcliente, int idempleado, String fecha_ingreso, int idventa, int idproducto, int cantidad, double precio_unitario) {
        this.nofactura = nofactura;
        this.serie = serie;
        this.fecha_factura = fecha_factura;
        this.idcliente = idcliente;
        this.idempleado = idempleado;
        this.fecha_ingreso = fecha_ingreso;
        this.idventa = idventa;
        this.idproducto = idproducto;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
    }
    public ventas(){}
    
     public DefaultTableModel leer (){
       DefaultTableModel tabla= new DefaultTableModel ();
       try{
      cn = new Conexion();
      cn.abrir_conexion();
       String query=" v"
               + ""
               + ""
               + ""
               + "";
      ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
      String encabezado[] = {"ID","PRODUCTO","DESCRIPCION","MARCA","IMAGEN","Q CONSTO","Q VENTA","EXISTENCIA","FECHA QUE INGRESO","IDMARCA"};
      tabla.setColumnIdentifiers(encabezado);
      String datos [] = new String[10];
      while(consulta.next()){
          datos[0]=consulta.getString("no_factura");
          datos[1]=consulta.getString("serie");
          datos[2]=consulta.getString("fecha_factura");
          datos[3]=consulta.getString("cliente");
          datos[4]=consulta.getString("empleado");
          datos[5]=consulta.getString("producto");      
          datos[6]=consulta.getString("cantidad");
          datos[7]=consulta.getString("prcioU");
          datos[8]=consulta.getString("PrecioTotal");
           datos[9]=consulta.getString("ftransaxion");
 tabla.addRow(datos);
      }
      cn.cerrar_conexion();
       }catch(SQLException ex){
        System.out.println("error "+ex);
       }
        
    return tabla;
    }
    //metodos
    
      public int agregarventa(){
            
          
        int retorno = 0;
        //idcliente
        try {
            cn = new Conexion();
            cn.abrir_conexion();
            String query;
            query = "SELECT idcliente FROM cliente WHERE nit ='"+this.getNit()+"';";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            while (consulta.next()){
                idcliente1 = Integer.parseInt(consulta.getString("idcliente"));
                break;
            }   
            cn.cerrar_conexion();
            
        }catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
            return 0;
        }
        //idempleado
        try {
            cn = new Conexion();
            cn.abrir_conexion();
            String query;
            query = "SELECT idempleado FROM user_trabajador WHERE usuario='"+this.getUser()+"';";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            while (consulta.next()){
                idempleado1 = Integer.parseInt(consulta.getString("idempleado"));
                break;
            }   
            cn.cerrar_conexion();
            
        }catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
            return 0;
        }
        //insert a venta
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            cn.abrir_conexion();
            String query;
            
            query = "INSERT INTO ventas (no_factura, serie, fecha_factura, idcliente, idempleado) VALUES (?,?,?,?,?);";
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setInt(1,this.getNofactura());
            parametro.setString(2,this.getSerie());
            parametro.setString(3,this.getFecha_factura());
            parametro.setInt(4,idcliente1);
            parametro.setInt(5,idempleado1);
            retorno = parametro.executeUpdate(); 
            
            System.out.println("Se inserto: "+ Integer.toString(retorno)+ "Registro");
            cn.cerrar_conexion();
            retorno =2;
        } catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
            return 0;
        }
       
   
    return retorno;
    
    }
    // insert a venta detalle
public int agregarventadetalle(){
int retorno = 0;
double precio = 0,cant ;
 int idventa1 = 0;
 //buscar idventa
          try {
            cn = new Conexion();
            cn.abrir_conexion();
            String query;
            query = "SELECT idventas FROM ventas WHERE no_factura='"+this.getNofactura()+"' ;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            while (consulta.next()){
                idventa1  = Integer.parseInt(consulta.getString("idventas")) ;
                break;
            }   
            cn.cerrar_conexion();
            
        }catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
            return 0;
        }
          //buscar cantidad
          try {
            cn = new Conexion();
            cn.abrir_conexion();
            String query;
            query = "SELECT precio_venta FROM productos WHERE idproductos='"+this.getIdproducto()+"';";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            while (consulta.next()){
                precio  = Double.parseDouble(consulta.getString("precio_venta")) ;
                break;
            }   
            cn.cerrar_conexion();
            cant= (double)getCantidad();
            precio = precio * 1;
        }catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
            return 0;
        }
          
        //insert ventadetalle
        try {
            
            
            PreparedStatement parametro;
            cn = new Conexion();
            cn.abrir_conexion();
            String query;
            
            query = "INSERT INTO ventas_detalle (idventa, idproducto, cantidad, precio_unitario) VALUES (?, ?, ?,?);";
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setInt(1,idventa1);
            parametro.setInt(2,this.getIdproducto());
            parametro.setInt(3,this.getCantidad());
            parametro.setDouble(4, precio);
            retorno = parametro.executeUpdate();            
            System.out.println("Se inserto: "+ Integer.toString(retorno)+ "Registro");
            cn.cerrar_conexion();
            retorno =2;
        } catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
            return 0;
        }


    return retorno;
}
public int modificar(){
    
        int retorno = 0;
        //obtener cantidad
        try {
            cn = new Conexion();
            cn.abrir_conexion();
            String query;
            query = "SELECT existencia FROM productos where idproductos='"+this.getIdproducto()+"';";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            while (consulta.next()){
               existecia1 = Integer.parseInt(consulta.getString("existencia"));
                break;
            }   
            cn.cerrar_conexion();
            
        }catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
            return 0;
        }
        
        
        
    try{
        int cantidad = this.getCantidad();
        existecia1 = existecia1 - cantidad;
            cn.abrir_conexion();
        PreparedStatement parametro;
        String query="UPDATE productos SET existencia = ? WHERE (idproductos = '"+this.getIdproducto()+"');";
        
                
        
        parametro =  (PreparedStatement)cn.conexionBD.prepareStatement(query);
        
        
        parametro.setInt(1,existecia1);
      
        retorno = parametro.executeUpdate();
        cn.cerrar_conexion();
        retorno=2;
    }catch(SQLException ex){
        System.out.println(ex.getMessage());
        retorno = 0;
    }
    return retorno;
    }
   
  
    
    
    
}
