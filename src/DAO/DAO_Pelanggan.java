/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Koneksi.Database;
import Model.Pelanggan;
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
public class DAO_Pelanggan implements Model_DAO<Pelanggan>{
    
    public DAO_Pelanggan(){
        connection = Database.KoneksiDB();
    }
    
    Connection connection;
    String INSERT = "INSERT INTO pelanggan(KdPlg, NmPlg, AlamatPlg, TelpPlg) values(?, ?, ?, ?)";
    String UPDATE = "UPDATE pelanggan SET NmPlg=?, AlamatPlg=?, TelpPlg=? WHERE KdPlg=?";
    String DELETE = "DELETE FROM pelanggan WHERE KdPlg=?";
    String SELECT = "SELECT * FROM pelanggan";
    String CARI = "SELECT * FROM pelanggan WHERE NmPlg LIKE ? OR AlamatPlg LIKE ? OR KdPlg LIKE ?";

    @Override
    public void insert(Pelanggan object) {
        PreparedStatement statement = null;
        
        try {
            statement = connection.prepareStatement(INSERT);
            statement.setString(1, object.getKode());
            statement.setString(2, object.getNama());
            statement.setString(3, object.getAlamat());
            statement.setString(4, object.getTelp());
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
    public void update(Pelanggan object) {
        PreparedStatement statement = null;
        
        try {
            statement = connection.prepareStatement(UPDATE);
            statement.setString(1, object.getKode());
            statement.setString(2, object.getNama());
            statement.setString(3, object.getAlamat());
            statement.setString(4, object.getTelp());
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
    public void delete(String kode) {
        PreparedStatement statement = null;
        
        try {
            
            statement = connection.prepareStatement(DELETE);
            statement.setString(1, kode);
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
    public List<Pelanggan> getALl() {
        List<Pelanggan> list = null;
        PreparedStatement statement = null;
        
        try {
            
            list = new ArrayList<Pelanggan>();
            statement = connection.prepareStatement(SELECT);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {                
                Pelanggan p = new Pelanggan();
                p.setKode(rs.getString("KdPlg"));
                p.setNama(rs.getString("NmPlg"));
                p.setAlamat(rs.getString("AlamatPlg"));
                p.setTelp(rs.getString("TelpPlg"));
                list.add(p);
            }
            
                    
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        
        return list;
    }

    @Override
    public List<Pelanggan> getCari(String key) {
        List<Pelanggan> list = null;
        PreparedStatement statement = null;
        
        try {
            
            list = new ArrayList<Pelanggan>();
            statement.setString(1, "%"+key+"%");
            statement.setString(2, "%"+key+"%");
            statement.setString(3, "%"+key+"%");
            
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {                
                
                Pelanggan p = new Pelanggan();
                p.setKode(rs.getString("KdPlg"));
                p.setNama(rs.getString("NmPlg"));
                p.setAlamat(rs.getString("AlamatPlg"));
                p.setTelp(rs.getString("TelpPlg"));
                list.add(p);
                
            }

            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        
        return list;
    }

    @Override
    public int autonumber(Pelanggan object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
