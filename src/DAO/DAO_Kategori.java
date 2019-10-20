/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Koneksi.Database;
import Model.Kategori;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author LENOVO
 */
public class DAO_Kategori implements Model_DAO<Kategori>{
    
    public DAO_Kategori(){
        
        connection = Database.KoneksiDB();
    }
    
    Connection connection;
    String INSERT = "INSERT INTO kategori(KdKategori, NmKategori) values(?,?)";
    String UPDATE = "UPDATE kategori SET NmKategori=? WHERE KdKategori=?";
    String DELETE = "DELETE FROM kategori WHERE KdKategori=?";
    String SELECT = "SELECT * FROM kategori";
    String CARI = "SELECT * FROM kategori WHERE KdKategori LIKE ?";
    String COUNTER = "SELECT max(KdKategori) as kode FROM kategori";
    

    @Override
    public void insert(Kategori object) {
        PreparedStatement statement = null;
        
        try {
            
            statement = connection.prepareStatement(CARI);
            statement.setString(1, object.getKode());
            ResultSet rs = statement.executeQuery();
            
            if (rs.next()) {
                
                JOptionPane.showMessageDialog(null, "Data Sudah Pernah Di simpan");
                
            } else {
                
                PreparedStatement statement2 = null;
                statement2 = connection.prepareStatement(INSERT);
                statement2.setString(1, object.getKode());
                statement2.setString(2, object.getNama());
                statement2.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Di tambah");
                
            }
            
                      
        } catch (Exception e) {
            
            e.printStackTrace();
            
        } finally {
            
            try {
                statement.close();
                
            } catch (SQLException ex) {
                
                Logger.getLogger(DAO_Kategori.class.getName()).log(Level.SEVERE, null, ex);
                
            }
            
        }
    }

    @Override
    public void update(Kategori object) {
        
         PreparedStatement statement = null;
         
        try {
            
             statement  = connection.prepareStatement(UPDATE);
             statement.setString(1, object.getNama());
             statement.setString(2, object.getKode());
             statement.executeUpdate();
             JOptionPane.showMessageDialog(null, "Data Berhasil Di Ubah");
             
        } catch (Exception e) {
            
            e.printStackTrace();
            
        } finally {
            
            try {
                
                statement.close();
                
            } catch (SQLException ex) {
                
                Logger.getLogger(DAO_Kategori.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        
        
    }

    @Override
    public void delete(String id) {
        
        PreparedStatement statement = null;
        
        try {
            
            statement = connection.prepareStatement(DELETE);
            statement.setString(1, id);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Di hapus");
            
            
        } catch (Exception e) {
            
            e.printStackTrace();
            
        } finally {
            
            try {
                
                statement.close();
                
            } catch (SQLException ex) {
                
                Logger.getLogger(DAO_Kategori.class.getName()).log(Level.SEVERE, null, ex);
                
            }
            
        }
        
    }

    @Override
    public List<Kategori> getALl() {
        
        List<Kategori> list = null;
        PreparedStatement statement = null;
        
        try {
            
            list = new ArrayList<Kategori>();
            statement = connection.prepareStatement(SELECT);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {                
                Kategori k = new Kategori();
                k.setKode(rs.getString("KdKategori"));
                k.setNama(rs.getString("NmKategori"));
                list.add(k);
            }
            
        } catch (Exception e) {
            
            e.printStackTrace();
            
        }
        
        return list;
        
    }

    @Override
    public List<Kategori> getCari(String key) {
        
        
        List<Kategori> list = null;
        PreparedStatement statement = null;
        
        try {
            
            list = new ArrayList<Kategori>();
            statement = connection.prepareStatement(CARI);
            statement.setString(1, key);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {                
                Kategori k = new Kategori();
                k.setKode(rs.getString("KdKategori"));
                k.setNama(rs.getString("NmKategori"));
                list.add(k);
            }
            
        } catch (Exception e) {
            
            e.printStackTrace();
            
        }
        
        return list;
        
        
    }
    
    @Override
    public int autonumber(Kategori object) {
        
        PreparedStatement statement = null;
        int nomor = 0;
        
        try {
            
            statement = connection.prepareStatement(COUNTER);
            ResultSet rs = statement.executeQuery();
            
            if (rs.next()) {
                nomor = rs.getInt("Kode")+1;
            }
           
            
        } catch (Exception e) {
            
            e.printStackTrace();
            
        }
        
        return nomor;
        
        
    }
    
}
