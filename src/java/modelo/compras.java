
package modelo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class compras {
    
    private int idcompras,no_orden,idprovedor,idproducto,cantidad;
    private double precio_costo;
    private String fecha_orden;
    
   int idcompra,existecia1;
Conexion cn;
    public compras(int idcompras, int no_orden, int idprovedor, int idproducto, int cantidad, double precio_costo, String fecha_orden) {
        this.idcompras = idcompras;
        this.no_orden = no_orden;
        this.idprovedor = idprovedor;
        this.idproducto = idproducto;
        this.cantidad = cantidad;
        this.precio_costo = precio_costo;
        this.fecha_orden = fecha_orden;
    }
public compras(){}
    
    public int getIdcompras() {
        return idcompras;
    }

    public void setIdcompras(int idcompras) {
        this.idcompras = idcompras;
    }

    public int getNo_orden() {
        return no_orden;
    }

    public void setNo_orden(int no_orden) {
        this.no_orden = no_orden;
    }

    public int getIdprovedor() {
        return idprovedor;
    }

    public void setIdprovedor(int idprovedor) {
        this.idprovedor = idprovedor;
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

    public double getPrecio_costo() {
        return precio_costo;
    }

    public void setPrecio_costo(double precio_costo) {
        this.precio_costo = precio_costo;
    }

    public String getFecha_orden() {
        return fecha_orden;
    }

    public void setFecha_orden(String fecha_orden) {
        this.fecha_orden = fecha_orden;
    }
    
     public int agregarcompra(){
        int retorno = 0;
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            cn.abrir_conexion();
            String query;
            query = "INSERT INTO compras( no_orden_compra, idproveedor,fecha_orden) VALUES (?,?,?);";
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setInt(1,this.getNo_orden());
            parametro.setInt(2,this.getIdprovedor());
            parametro.setString(3,this.getFecha_orden());
         
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
    // agrgar a compra detalle
     public int agregarcompradetalle(){  
        int retorno = 0;
        // id compra
        try {
            cn = new Conexion();
            cn.abrir_conexion();
            String query;
            query = "SELECT idcompas FROM compras where no_orden_compra= '"+this.getNo_orden()+"';";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            while (consulta.next()){
                idcompra = Integer.parseInt(consulta.getString("idcompas"));
                break;
            }   
            cn.cerrar_conexion();
            
        }catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
            return 0;
        }
        
        
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            cn.abrir_conexion();
            String query;
            query = "INSERT INTO compras_detalle (idcompra, idproducto, cantidad, precio_costo_unitario) VALUES (?,?,?,?);";
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setInt(1,idcompra);
            parametro.setInt(2,this.getIdproducto());
            parametro.setInt(3,this.getCantidad());
            parametro.setDouble(4, this.getPrecio_costo());
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
        existecia1 = existecia1 + cantidad;
            cn.abrir_conexion();
        PreparedStatement parametro;
        String query="UPDATE productos SET precio_costo =?, precio_venta = ?, existencia = ? WHERE (idproductos = "+this.getIdproducto()+");";
        
            double precioventanuevo = this.getPrecio_costo()+(this.getPrecio_costo()*0.25);    
        
        parametro =  (PreparedStatement)cn.conexionBD.prepareStatement(query);
        parametro.setDouble(1,this.getPrecio_costo());
        parametro.setDouble(2,precioventanuevo);
        parametro.setInt(3,existecia1);
      
        retorno = parametro.executeUpdate();
        cn.cerrar_conexion();
        retorno=2;
    }catch(SQLException ex){
        System.out.println(ex.getMessage());
        retorno = 0;
    }
    return retorno;
    }
   
    public DefaultTableModel leer (){
       DefaultTableModel tabla= new DefaultTableModel ();
       try{
      cn = new Conexion();
      cn.abrir_conexion();
       String query="SELECT 		no_orden_compra,\n" +
"			fecha_orden,\n" +
"			proveedores.proveedor as proveedor, # inner proveedor ya\n" +
"            productos.producto as producto, # innwr producto \n" +
"            compras_detalle.cantidad as cantidad, #inner compradetalle\n" +
"            compras_detalle.precio_costo_unitario as precio, #inner comprdetalle\n" +
"            compras.fecha_ingreso as fechaIngreso\n" +
"FROM 		compras\n" +
"			inner join compras_detalle \n" +
"			on compras.idcompas = compras_detalle.idcompra\n" +
"			inner join proveedores \n" +
"			on compras.idproveedor= proveedores.idproveedores\n" +
"            inner join productos \n" +
"			on compras_detalle.idproducto = productos.idproductos\n" +
"order by fecha_orden asc;";
      ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
      String encabezado[] = {"ID","PRODUCTO","DESCRIPCION","MARCA","IMAGEN","Q CONSTO","Q VENTA"};
      tabla.setColumnIdentifiers(encabezado);
      String datos [] = new String[7];
      while(consulta.next()){
          datos[0]=consulta.getString("no_orden_compra");
          datos[1]=consulta.getString("fecha_orden");
          datos[2]=consulta.getString("proveedor");
          datos[3]=consulta.getString("producto");
          datos[4]=consulta.getString("cantidad");
          datos[5]=consulta.getString("precio");      
          datos[6]=consulta.getString("fechaIngreso");
 tabla.addRow(datos);
      }
      cn.cerrar_conexion();
       }catch(SQLException ex){
        System.out.println("error "+ex);
       }
        
    return tabla;
    }
    
    
    
}
