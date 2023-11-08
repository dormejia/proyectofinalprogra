
package modelo;
// librerias
import java.io.OutputStream;
import java.sql.ResultSet;
import java.util.HashMap;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;



public class clsproductos {
    private int id_producto,id_marca,existencia;
    private String producto, decripcion,imagen,fecha_creacion;
    private double Qcosto,Qventa;
    private clsConexion cn;
    //constructor

    public clsproductos(int id_producto, int id_marca, int existencia, String producto, String decripcion, String imagen, String fecha_creacion, double Qcosto, double Qventa) {
        this.id_producto = id_producto;
        this.id_marca = id_marca;
        this.existencia = existencia;
        this.producto = producto;
        this.decripcion = decripcion;
        this.imagen = imagen;
        this.fecha_creacion = fecha_creacion;
        this.Qcosto = Qcosto;
        this.Qventa = Qventa;
    }
    // constructor vacio
    public clsproductos(){}
    
    
    
//get and set
    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getId_marca() {
        return id_marca;
    }

    public void setId_marca(int id_marca) {
        this.id_marca = id_marca;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getDecripcion() {
        return decripcion;
    }

    public void setDecripcion(String decripcion) {
        this.decripcion = decripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public double getQcosto() {
        return Qcosto;
    }

    public void setQcosto(double Qcosto) {
        this.Qcosto = Qcosto;
    }

    public double getQventa() {
        return Qventa;
    }

    public void setQventa(double Qventa) {
        this.Qventa = Qventa;
    }
    public HashMap tipomarca(){
    HashMap<String,String> drop = new HashMap();
    try{
        cn = new clsConexion();
        String query="SELECT idmarcas as id, marca FROM marcas;";
        
        cn.abrir();
        ResultSet consulta = cn.cnn.createStatement().executeQuery(query);
        while(consulta.next()){
        drop.put(consulta.getString("id"),consulta.getString("marca"));
        
        }
        
        cn.cerrar();
    }catch(Exception ex){
    System.out.println("error"+ex);
    }
    return drop;
    }
    //
    
    public HashMap tipoproducto(){
    HashMap<String,String> drop = new HashMap();
    try{
        cn = new clsConexion();
        String query="SELECT idproductos,producto FROM productos;";
        
        cn.abrir();
        ResultSet consulta = cn.cnn.createStatement().executeQuery(query);
        while(consulta.next()){
        drop.put(consulta.getString("idproductos"),consulta.getString("producto"));
        
        }
        
        cn.cerrar();
    }catch(Exception ex){
    System.out.println("error"+ex);
    }
    return drop;
    }
    
        // crear
    public int agregar(){
        int retorno =0;
    try{
     cn = new clsConexion();
     PreparedStatement parametro;
     String query="INSERT INTO productos (producto, idmarca, descripcion, imagen, precio_costo, precio_venta, existencia) VALUES (?, ?, ?, ?, ?, ?, ?);";
     cn.abrir();
     parametro =(PreparedStatement)cn.cnn.prepareStatement(query);
     parametro.setString(1, this.getProducto());
     parametro.setInt(2, this.getId_marca());
     parametro.setString(3, this.getDecripcion());
     parametro.setString(4, this.getImagen());
     parametro.setDouble(5, this.getQcosto());
     parametro.setDouble(6, this.getQventa());
    parametro.setInt(7, this.getExistencia());
    retorno= parametro.executeUpdate();
     cn.cerrar();
     
     
    }catch(SQLException ex){
    System.out.println("error "+ex);
    retorno =0;
    }
    return retorno;
    }
    //modificar
    public int modificar(){
            int retorno =0;
    try{
     cn = new clsConexion();
     PreparedStatement parametro;
     String query="UPDATE productos SET producto = ? , idmarca = ?, descripcion = ?, imagen = ?, precio_costo = ?, precio_venta = ?, existencia = ?, fecha_ingreso = current_timestamp WHERE idproductos = "+this.getId_producto()+";";
     cn.abrir();
     parametro =(PreparedStatement)cn.cnn.prepareStatement(query);
     parametro.setString(1, this.getProducto());
     parametro.setInt(2, this.getId_marca());
     parametro.setString(3, this.getDecripcion());
     parametro.setString(4, this.getImagen());
     parametro.setDouble(5, this.getQcosto());
     parametro.setDouble(6, this.getQventa());
    parametro.setInt(7, this.getExistencia());
    retorno= parametro.executeUpdate();
     cn.cerrar();
     
     
    }catch(SQLException ex){
    System.out.println("error "+ex);
    retorno =0;
    }
    return retorno;
    }
    //eliminar
    public int elininar(){
     int retorno =0;
    try{
     cn = new clsConexion();
     PreparedStatement parametro;
     String query="DELETE FROM productos WHERE idproductos = "+this.getId_producto()+";";
     cn.abrir();
     parametro =(PreparedStatement)cn.cnn.prepareStatement(query);
    retorno= parametro.executeUpdate();
     cn.cerrar();
     
     
    }catch(SQLException ex){
    System.out.println("error "+ex);
    retorno =0;
    }
    return retorno;
    }
    
    public DefaultTableModel leer (){
       DefaultTableModel tabla= new DefaultTableModel ();
       try{
       cn = new clsConexion();
       cn.abrir();
       String query="SELECT idproductos AS id, producto,descripcion,marcas.marca AS marca,imagen,precio_costo AS costo,precio_venta AS venta,existencia,fecha_ingreso AS ingreso,idmarca FROM productos inner join marcas on idmarca= marcas.idmarcas ;";
      ResultSet consulta = cn.cnn.createStatement().executeQuery(query);
      String encabezado[] = {"ID","PRODUCTO","DESCRIPCION","MARCA","IMAGEN","Q CONSTO","Q VENTA","EXISTENCIA","FECHA QUE INGRESO","IDMARCA"};
      tabla.setColumnIdentifiers(encabezado);
      String datos [] = new String[10];
      while(consulta.next()){
          datos[0]=consulta.getString("id");
          datos[1]=consulta.getString("producto");
          datos[2]=consulta.getString("descripcion");
          datos[3]=consulta.getString("marca");
          datos[4]=consulta.getString("imagen");
          datos[5]=consulta.getString("costo");      
          datos[6]=consulta.getString("venta");
          datos[7]=consulta.getString("existencia");
          datos[8]=consulta.getString("ingreso");
           datos[9]=consulta.getString("idmarca");
 tabla.addRow(datos);
      }
       cn.cerrar();
       }catch(SQLException ex){
        System.out.println("error "+ex);
       }
        
    return tabla;
    }
    public int mostrar(){return 0 ;}

    public void setImagen(OutputStream os) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
