/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Koneksi.Database;
import Model.Barang;
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
public class DAO_Barang implements Model_DAO<Barang>{
    
    public DAO_Barang() {
        connection = Database.KoneksiDB();
    }
    
    Connection connection;
    String INSERT = "INSERT INTO barang(KdBrg, NmBrg, Satuan, HargaBrg, Stok, KdKategori) values(?,?,?,?,?,?)";
    String UPDATE = "UPDATE barang SET NmBrg=?, Satuan=?, HargaBrg=? , Stok=?, KdKategori=?  WHERE KdBrg=?";
    String DELETE = "DELETE FROM barang WHERE KdBrg=?";
    String SELECT = "SELECT a.*, b.* FROM barang a, kategori b, WHERE a.KdKategori=b.KdKategori order by KdBrg";
    String CARI = "SELECT a.*, b.* FROM barang a, kategori b, WHERE a.KdKategori=b.KdKategori and KdBrg LIKE ?";
    String CARIKATEGORI = "SELECT * FROM kategori WHERE KdKategori=?";
    String COMBO = "SELECT KdKategori FROM kategori order by convert(right(KdKategori,2),signed integer)";
    String COUNTER = "SELECT ifnull(max(convert(right(KdBrg,2), signed integer)),0) as panjang "
            + "ifnull(length(max(convert(rigth(KdBrg,2),signed integer))),0) as panjang"
            + "from barang where KdKategori=?";

    @Override
    public void insert(Barang object) {
         PreparedStatement statement = null;
        
        try {
            statement = connection.prepareStatement(CARI);
            statement.setString(1, object.getKodebarang());
            ResultSet rs = statement.executeQuery();
            
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Data Sudah Pernah Di Simpan");
                
            } else {
                
                PreparedStatement statement2 = null;
                statement2 = connection.prepareStatement(INSERT);
                statement2.setString(1, object.getKodebarang());
                statement2.setString(2, object.getNamabarang());
                statement2.setString(3, object.getSatuan());
                statement2.setInt(4, object.getHarga());
                statement2.setInt(5, object.getStock());
                statement2.setInt(6, object.getKodekategori());
                statement2.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
         
            }
                   
        } catch (Exception e) {
            
            e.printStackTrace();
            
        } finally {
            
            try {
                statement.close();
                
            } catch (SQLException ex) {
                
                Logger.getLogger(DAO_Barang.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void update(Barang object) {
        PreparedStatement statement = null;
        
        try {
            statement = connection.prepareStatement(CARI);
            statement.setString(1, object.getKodebarang());
            ResultSet rs = statement.executeQuery();
            
            if (rs.next()) {
                
                statement = connection.prepareStatement(UPDATE);
                statement.setString(1, object.getNamabarang());
                statement.setString(2, object.getSatuan());
                statement.setInt(3, object.getHarga());
                statement.setInt(4, object.getStock());
                statement.setInt(5, object.getKodekategori());
                statement.setString(6, object.getKodebarang());
                statement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
                
            } else {
                
                JOptionPane.showMessageDialog(null, "Kode barang tersebut belum pernah di simpan");

            }
                   
        } catch (Exception e) {
            
            e.printStackTrace();
            
        } finally {
            
            try {
                statement.close();
                
            } catch (SQLException ex) {
                
                Logger.getLogger(DAO_Barang.class.getName()).log(Level.SEVERE, null, ex);
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
            JOptionPane.showMessageDialog(null, "Data berhasil di hapus");
   
            
        } catch (Exception e) {
            
            e.printStackTrace();
            
        } finally {
            
            try {
                
                statement.close();
                
            } catch (Exception ex) {
                
                Logger.getLogger(DAO_Barang.class.getName()).log(Level.SEVERE, null, ex);

            }
            
        }
         
    }

    @Override
    public List<Barang> getALl() {
        List<Barang> list = null;
        PreparedStatement statement = null;
        
        try {
            
            list = new ArrayList<Barang>();
            statement = connection.prepareStatement(SELECT);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {                
               Barang b = new Barang();
               b.setKodebarang(rs.getString("KdBrg"));
               b.setNamabarang(rs.getString("NmBrg"));
               b.setSatuan(rs.getString("Satuan"));
               b.setHarga(rs.getInt("HargaBrg"));
               b.setStock(rs.getInt("Stok"));
               b.setKodekategori(rs.getInt("a.KdKategori"));
               b.setNamakategori(rs.getString("NmKategori"));
               list.add(b);
            }
            
                    
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        
        return list;
    }

    @Override
    public List<Barang> getCari(String key) {
     
        List<Barang> list = null;
        PreparedStatement statement = null;
        
        try {
            
            list = new ArrayList<Barang>();
            statement = connection.prepareStatement(CARI);
            statement.setString(1, "%"+key+"%");
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {                
                
                Barang b = new Barang();
                b.setKodebarang(rs.getString("KdBrg"));
                b.setNamabarang(rs.getString("NmBrg"));
                b.setSatuan(rs.getString("Satuan"));
                b.setHarga(rs.getInt("HargaBrg"));
                b.setStock(rs.getInt("Stok"));
                b.setKodekategori(rs.getInt("a.KdKategori"));
                b.setNamakategori(rs.getString("NmKategori"));
                list.add(b);
                
            }

            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        
        return list;
        
    }

    @Override
    public int autonumber(Barang object) {
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
    
    public String autonumber2(Integer id) {
        PreparedStatement statement = null;
        int nomor_berikutnya = 0;
        String urutan = "";
        
        try {
            statement = connection.prepareStatement(COUNTER);
            statement.setInt(1, id);
            ResultSet rs2 = statement.executeQuery();
            
            if (rs2.next()) {
                nomor_berikutnya = rs2.getInt("kode") + 1;
                
                if (rs2.getInt("kode") != 0) { // jika kategori sudah pernah ada 
                    
                    if (rs2.getInt("panjang") == 1) { //jika jumlah digitnya adalah 1
                        
                        urutan = "B" + id + "0" + nomor_berikutnya;
                        
                    } else if (rs2.getInt("panjang") == 2) {
                        
                        //jika jumlah digitnya adalah 2 
                        urutan = "B" + id + nomor_berikutnya;
                    }
                    
                } else { // jika kode kategori belum pernah ada 
                    
                    urutan = "B" + id + "01";
                    
                }
                        
            } else {
                
                JOptionPane.showMessageDialog(null, "Data tidak di temukan");
                
            }
                    
                    
                    
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return urutan;
    }
    
    public List<Barang> IsiCombo() {
        
        PreparedStatement statement = null;
        List<Barang> list = null;
        
        try {
            list = new ArrayList<Barang>();
            statement = connection.prepareStatement(COMBO);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {                
                Barang b = new Barang();
                b.setKodekategori(rs.getInt("KdKategori"));
                list.add(b);
            }
            
        } catch (Exception e) {
            
            e.printStackTrace();
            
        }
        
        return list;
        
    }
    
    public List<Barang> getDataKategori(Integer id) {
        PreparedStatement statement = null;
        List<Barang> list = null;
        
        try {
            list = new ArrayList<Barang>();
            statement = connection.prepareStatement(CARIKATEGORI);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {                
                Barang b = new Barang();
                b.setNamakategori(rs.getString("NmKategori"));
                list.add(b);
            }
            
        } catch (Exception e) {
            
            e.printStackTrace();
            
        }
        
        return list;
    }
    
}
