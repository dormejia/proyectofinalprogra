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

public class Puesto {
    private int idpuesto;
    private String puestos;
    private Conexion2 cn;

    public Puesto() {
    }

    public Puesto(int idpuesto, String puestos) {
        this.idpuesto = idpuesto;
        this.puestos = puestos;
    }

    public int getIdpuesto() {
        return idpuesto;
    }

    public void setIdpuesto(int idpuesto) {
        this.idpuesto = idpuesto;
    }

    public String getPuestos() {
        return puestos;
    }

    public void setPuestos(String puestos) {
        this.puestos = puestos;
    }

    public DefaultTableModel leer() {
        DefaultTableModel tabla = new DefaultTableModel();
        try {
            cn = new Conexion2();
            cn.abrir_cn();
            String query = "SELECT * FROM puestos";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            String encabezado[] = {"idpuesto", "puestos"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[] = new String[2];
            while (consulta.next()) {
                datos[0] = consulta.getString("idpuesto");
                datos[1] = consulta.getString("puestos");
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
            cn = new Conexion2();
            String query = "INSERT INTO puestos (idpuesto,puestos) VALUES (?,?)";
            cn.abrir_cn();
            parametro = cn.conexionBD.prepareStatement(query);
           //
            parametro.setString(2, getPuestos());
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
            cn = new Conexion2();
            String query = "UPDATE puestos SET puestos = ? WHERE (idpuesto = '"+this.getIdpuesto()+"');";
            cn.abrir_cn();
            parametro = cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getPuestos());
            
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
     cn = new Conexion2();
     PreparedStatement parametro;
     String query="DELETE FROM  puestos WHERE idpuesto = "+this.getIdpuesto()+";";
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
      public HashMap tipoempleado(){
    HashMap<String,String> drop = new HashMap();
    try{
        cn = new Conexion2();
         cn.abrir_cn();
        String query="SELECT * FROM puestos;";
        
      
        ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
        while(consulta.next()){
        drop.put(consulta.getString("idpuesto"),consulta.getString("puestos"));
        
        }
        
         cn.cerrar_cn();
    }catch(Exception ex){
    System.out.println("error"+ex);
    }
    return drop;
    }
    
    public static void main(String [] args){
        Puesto m= new Puesto();
        m.setIdpuesto(3);
        m.setPuestos("hunter");
        int resultado= m.agregar();
         DefaultTableModel tabla = new DefaultTableModel();
        tabla = m.leer();
        System.out.println("el resultado es"+ tabla);
    }
}

