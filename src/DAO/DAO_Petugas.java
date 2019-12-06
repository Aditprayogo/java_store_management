/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Koneksi.Database;
import Model.Pelanggan;
import Model.Petugas;
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
public class DAO_Petugas implements Model_DAO<Petugas>{
    
    public DAO_Petugas() {
        
        connection = Database.KoneksiDB();
    
    }
    
    Connection connection;
    String INSERT = "INSERT INTO petugas(KdPetugas, NmPetugas, AlamatPetugas, TelpPetugas) values(?, ?, ?, ?)";
    String UPDATE = "UPDATE petugas SET NmPetugas=?, AlamatPetugas=?, TelpPetugas=? WHERE KdPetugas=?";
    String DELETE = "DELETE FROM petugas WHERE KdPetugas=?";
    String SELECT = "SELECT * FROM petugas";
    String CARI = "SELECT * FROM petugas WHERE NmPetugas LIKE ? OR AlamatPetugas LIKE ? OR KdPetugas LIKE ?";
    String COUNTER = "SELECT max(KdPetugas) as kode FROM petugas";

    @Override
    public void insert(Petugas object) {
        PreparedStatement statement = null;
        
        try {
            statement = connection.prepareStatement(INSERT);
            statement.setString(1, object.getKode());
            statement.setString(2, object.getNama());
            statement.setString(3, object.getAlamat());
            statement.setString(4, object.getTelepon());
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Di tambah");
                    
        } catch (Exception e) {
            
            e.printStackTrace();
            
        } finally {
            
            try {
                statement.close();
                
            } catch (SQLException ex) {
                
                Logger.getLogger(DAO_Pelanggan.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void update(Petugas object) {
         PreparedStatement statement = null;
        
        try {
            statement = connection.prepareStatement(UPDATE);
            statement.setString(1, object.getKode());
            statement.setString(2, object.getNama());
            statement.setString(3, object.getAlamat());
            statement.setString(4, object.getTelepon());
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Di ubah");
                    
        } catch (Exception e) {
            
            e.printStackTrace();
            
        } finally {
            
            try {
                statement.close();
                
            } catch (SQLException ex) {
                
                Logger.getLogger(DAO_Pelanggan.class.getName()).log(Level.SEVERE, null, ex);
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
                
                Logger.getLogger(DAO_Pelanggan.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public List<Petugas> getALl() {
        List<Petugas> list = null;
        PreparedStatement statement = null;
        
        try {
            
            list = new ArrayList<Petugas>();
            statement = connection.prepareStatement(SELECT);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {                
                Petugas p = new Petugas();
                p.setKode(rs.getString("KdPetugas"));
                p.setNama(rs.getString("NmPetugas"));
                p.setAlamat(rs.getString("AlamatPetugas"));
                p.setTelepon(rs.getString("TelpPetugas"));
                list.add(p);
            }
            
                    
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        
        return list;
    }

    @Override
    public List<Petugas> getCari(String key) {
        List<Petugas> list = null;
        PreparedStatement statement = null;
        
        try {
            
            list = new ArrayList<>();
            statement.setString(1, "%"+key+"%");
            statement.setString(2, "%"+key+"%");
            statement.setString(3, "%"+key+"%");
            
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {                
                
                Petugas p = new Petugas();
                p.setKode(rs.getString("KdPetugas"));
                p.setNama(rs.getString("NmPetugas"));
                p.setAlamat(rs.getString("AlamatPetugas"));
                p.setTelepon(rs.getString("TelpPetugas"));
                list.add(p);
                
            }

            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        
        return list;
    }

    @Override
    public int autonumber(Petugas object) {
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
