/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Koneksi;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.awt.HeadlessException;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author LENOVO
 */
public class Database {
     public static Connection con;
     public static Statement stm;
     
     public static Connection KoneksiDB() {
         
         
         if (con == null) {
              try {
                String url ="jdbc:mysql://localhost/db_penjualan";
                String user="root";
                String pass="";

                Class.forName("com.mysql.jdbc.Driver");
                con =(Connection) DriverManager.getConnection(url,user,pass);
                stm = (Statement) con.createStatement();
                JOptionPane.showMessageDialog(null, "Koneksi Berhasil", "Pesan", JOptionPane.INFORMATION_MESSAGE);       
            
            } catch(Exception ex) {
                System.err.println("Koneksi Gagal" +ex.getMessage());
            }
             
         }
         return con;
    }
    
}
