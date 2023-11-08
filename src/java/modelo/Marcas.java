/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class Marcas {
    private int idmarca;
    private String marca;
    private Conexion3 cn;

    public Marcas() {
    }

    public Marcas(int idmarca, String marca) {
        this.idmarca = idmarca;
        this.marca = marca;
    }

    public int getIdmarca() {
        return idmarca;
    }

    public void setIdmarca(int idmarca) {
        this.idmarca = idmarca;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public DefaultTableModel leer() {
        DefaultTableModel tabla = new DefaultTableModel();
        try {
            cn = new Conexion3();
            cn.abrir_cn();
            String query = "SELECT idmarcas,marca FROM marcas;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            String encabezado[] = {"idmarca", "marca"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[] = new String[2];
            while (consulta.next()) {
                datos[0] = consulta.getString("idmarcas");
                datos[1] = consulta.getString("marca");
                tabla.addRow(datos);
            }
            cn.cerrar_cn();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return tabla;
    }

    public int agregar() {
        int retorno = 0;
        try {
            PreparedStatement parametro;
            cn = new Conexion3();
            String query = "INSERT INTO marcas (marca) VALUES (?);";
            cn.abrir_cn();
            parametro = cn.conexionBD.prepareStatement(query);
            
            parametro.setString(1, getMarca());
            retorno = parametro.executeUpdate();
            cn.cerrar_cn();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            retorno = 0;
        }
        return retorno;
    }

    public int modificar() {
        int retorno = 0;
        try {
            PreparedStatement parametro;
            cn = new Conexion3();
            String query = "UPDATE marcas SET marca = ? WHERE (idmarcas = '"+this.getIdmarca()+"');";
            cn.abrir_cn();
            parametro = cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getMarca());
            
            retorno = parametro.executeUpdate();
            cn.cerrar_cn();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            retorno = 0;
        }
        return retorno;
    }

    public int eliminar() {
        int retorno =0;
    try{
     cn = new Conexion3();
     PreparedStatement parametro;
     String query="DELETE FROM  marcas WHERE  idmarcas = "+this.getIdmarca()+";";
      cn.abrir_cn();
     parametro =(PreparedStatement)cn.conexionBD.prepareStatement(query);
    retorno= parametro.executeUpdate();
      cn.cerrar_cn();
     
     
    }catch(SQLException ex){
    System.out.println("error "+ex);
    retorno =0;
    }
    return retorno;
    }
    public static void main(String [] args){
        Marcas m= new Marcas();
        m.setIdmarca(3);
        m.setMarca("hunter");
        int resultado= m.agregar();
         DefaultTableModel tabla = new DefaultTableModel();
        tabla = m.leer();
        System.out.println("el resultado es"+ tabla);
    }
}
