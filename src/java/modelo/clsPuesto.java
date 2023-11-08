/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.sql.PreparedStatement;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.util.HashMap;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Pablo
 */
public class clsPuesto {
    private int id_puesto;
    private String puesto;
    private conexion_1 cn;

    public int getId_puesto() {
        return id_puesto;
    }

    public void setId_puesto(int id_puesto) {
        this.id_puesto = id_puesto;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public clsPuesto(int id_puesto, String puesto) {
        this.id_puesto = id_puesto;
        this.puesto = puesto;
    }
    
    public clsPuesto(){}
    
    public HashMap drop_sangre(){
        HashMap<String, String> drop = new HashMap();
        try{
            cn= new conexion_1();
            String query = "SELECT idpuesto as id,puestos FROM puestos;";
            cn.abrir_cn();
            ResultSet consulta = cn.cnbd.createStatement().executeQuery(query);
            while(consulta.next()){
                drop.put(consulta.getString("id"), consulta.getString("puestos"));
                //System.out.println("EL ID ES: "+consulta.getString("id"));
            }
            cn.cerrar_cn();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return drop;
    }
    public int agregar(){
        int retorno =0;
    try{
     cn= new conexion_1();
     PreparedStatement parametro;
     String query="INSERT INTO puestos (idpuesto, puestos) VALUES (?, ?);";
     cn.abrir_cn();
     parametro =(PreparedStatement)cn.cnbd.prepareStatement(query);
     parametro.setString(1, this.getPuesto());
    
   
    retorno= parametro.executeUpdate();
     cn.cerrar_cn();
     
     
    }catch(SQLException ex){
    System.out.println("error "+ex);
    retorno =0;
    }
    return retorno;
    }
}
