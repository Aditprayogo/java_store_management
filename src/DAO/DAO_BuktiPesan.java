/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Koneksi.Database;
import Model.BuktiPesan;
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
public class DAO_BuktiPesan implements Model_DAO<BuktiPesan>{
    
    Connection connection;
    
    public DAO_BuktiPesan() {
        
        connection = Database.KoneksiDB();
    
}

    @Override
    public void insert(BuktiPesan object) {
        PreparedStatement statement;
        
        try {
            
            String SELECT = "SELECT * FROM buktipesan where NoPesan=?";
            statement = connection.prepareStatement(SELECT);
            statement.setString(1, object.getNobp());
            ResultSet rs = statement.executeQuery();
            
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Data Sudah Pernah Di Simpan");
                
            } else {
                
                PreparedStatement statement2 = null;
                String INSERT = "insert into buktipesan(NoPesan,TglPesan,KdPlg) values(?,?,?)";
                statement2 = connection.prepareStatement(INSERT);
                statement2.setString(1, object.getNobp());
                statement2.setString(2, object.getTglbp());
                statement2.setInt(3, object.getKodeplg());
                statement2.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
         
            }
                   
        } catch (Exception e) {
            
            e.printStackTrace();
            
        } 
    }
    
    public void insert_detiltransaksi(BuktiPesan object){
        PreparedStatement statement;
        
        try {
            
            String INSERTDETIL = "INSERT into detilpesan(NoPesan,KdBrg,HrgPesan,JmlPesan) values(?,?,?,?)";
            statement = connection.prepareStatement(INSERTDETIL);
            statement.setString(1, object.getNobp());
            statement.setString(2, object.getKodebarang());
            statement.setInt(3, object.getHarga());
            statement.setInt(4, object.getQty());
            statement.executeUpdate();
            
        } catch (Exception e) {
            
            e.printStackTrace();
            
        }
    }
    
    public void update_stock(BuktiPesan object){
        
        PreparedStatement statement;
        int stok_akhir = 0;
        
        try {
            
           String SELECT = "SELECT stok FROM barang where KdBrg=?";
           statement = connection.prepareStatement(SELECT);
           statement.setString(1, object.getKodebarang());
           ResultSet rs = statement.executeQuery();
           
            if (rs.next()) { // jika data di temukan
                
                if (rs.getInt("stok") < 0) {
                    
                    JOptionPane.showMessageDialog(null, "Stock Kode Barang : " + object.getKodebarang() + "Kosong!");
                    stok_akhir = 0;

                } else {
                    
                    //hitung stok akhir berdasarkan qty masing masing kode barang yang ada di JTABLE
                    stok_akhir = rs.getInt("stok") - object.getQty();
                    
                    //update stok barang ke dalam table barang
                    
                    PreparedStatement statement2;
                    String UPDATESTOK = "UPDATE barang set Stok=? where KdBrg=?";
                    statement2 = connection.prepareStatement(UPDATESTOK);
                    statement2.setInt(1, stok_akhir);
                    statement2.setString(2, object.getKodebarang());
                    statement2.executeUpdate();
                    
                }
                
            }
           
        } catch (Exception e) {
            
            e.printStackTrace();
            
        }
        
    }
    
    public String autonumer2(){
        PreparedStatement statement;
        int nomor_berikutnya = 0;
        String urutan = "";
        
        try {
            
            String COUNTER = "SELECT ifnull(max(convert(right(NoPesan,5),signed integer)),0) as kode,"
                    + "ifnull(length(max(convert(right(NoPesan,5)+1,signed integer))),0) as panjang from buktipesan";
            
            statement = connection.prepareStatement(COUNTER);
            ResultSet rs2 = statement.executeQuery();
            
            if (rs2.next()) {
                
                nomor_berikutnya = rs2.getInt("kode") + 1;
                
                if (rs2.getInt("kode") != 0) { //jika nomor transaksi sudah pernah ada
                    
                    if (rs2.getInt("panjang") == 1) { // jika jumlah digitnya adalah 1
                        
                        urutan = "01" + "0000" + nomor_berikutnya;
                        
                    } else if (rs2.getInt("panjang") == 2) { // jika jumlah digitnya adalah 2
                        
                        urutan = "01" + "000" + nomor_berikutnya;
                        
                    } else if (rs2.getInt("panjang")==3){
                        
                        urutan = "01" + "00" + nomor_berikutnya;
                        
                    } else if (rs2.getInt("panjang")==4){
                        
                        urutan = "01" + "0" + nomor_berikutnya;
                        
                    } else if (rs2.getInt("panjang") == 5) {
                        
                        urutan = "01" + nomor_berikutnya;
                    }
                    
                    
                } else { // jika nomor transaksi belum ada
                    
                    urutan = "01" + "00001";
                    
                }
                
            } else {
                
                JOptionPane.showMessageDialog(null, "Data tidak di temukan");
                
            }
            
        } catch (Exception e) {
            
            e.printStackTrace();
            
        }
        
        
        return urutan;
    }
    
    //  untuk menampilkan nama pelanggan di combo pelanggan
    public List<BuktiPesan> isicomboplg(){
        PreparedStatement statement;
        List<BuktiPesan> list = null;
        
        try {
            
            list = new ArrayList<>();
            String TAMPILPLG = "SELECT * FROM pelanggan order by NmPlg";
            statement = connection.prepareStatement(TAMPILPLG);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {                
                
                BuktiPesan b = new BuktiPesan();
                b.setNamaplg(rs.getString("NmPlg"));
                list.add(b);
                
            }
            
            
        } catch (Exception e) {
            
            e.printStackTrace();
            
        }
        
        return list;
    }
    
    
    //  method mendapatkan kode pelanggan berdasarkan nama pelanggan
    public List<BuktiPesan> getkdplg(String NamaPlg) {
        PreparedStatement statement;
        List<BuktiPesan> list = null;
        
        try {
            
            list = new ArrayList<>();
            String CARIPELANGGAN2 = "SELECT * FROM pelanggan where NmPlg=?";
            statement = connection.prepareStatement(CARIPELANGGAN2);
            statement.setString(1, NamaPlg);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                
                BuktiPesan b = new BuktiPesan();
                b.setKodeplg(rs.getInt("KdPlg"));
                list.add(b);
                
            } 
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        
        return list;
    }
    
//    menampilkan nama pelanggan berdasarkan kode yang di input
    public List<BuktiPesan> getnmplg(Integer kode) {
        PreparedStatement statement;
        List<BuktiPesan> list = null;
        
        try {
            
            list = new ArrayList<>();
            String CARIPELANGGAN2 = "SELECT * FROM pelanggan where KdPlg=?";
            statement = connection.prepareStatement(CARIPELANGGAN2);
            statement.setInt(1, kode);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                
                BuktiPesan b = new BuktiPesan();
                b.setNamaplg(rs.getString("NmPlg"));
                list.add(b);
                
            } 
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        
        return list;
    }
    
     //  untuk menampilkan nama pelanggan di combo pelanggan
    public List<BuktiPesan> isicombokategori(){
        PreparedStatement statement;
        List<BuktiPesan> list = null;
        
        try {
            
            list = new ArrayList<>();
            String TAMPILKATEGORI = "SELECT * FROM kategori order by NmKategori";
            statement = connection.prepareStatement(TAMPILKATEGORI);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {                
                
                BuktiPesan b = new BuktiPesan();
                b.setNamakategori(rs.getString("NmKategori"));
                list.add(b);
                
            }
            
            
        } catch (Exception e) {
            
            e.printStackTrace();
            
        }
        
        return list;
    }
    
    public List<BuktiPesan> getkdkategori(String namakat){
        PreparedStatement statement;
        List<BuktiPesan> list = null;
        
        try {
            
            list = new ArrayList<>();
            String CARIKATEGORI2 = "SELECT * FROM kategori where NmKategori=?";
            statement = connection.prepareStatement(CARIKATEGORI2);
            statement.setString(1, namakat);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {   
                
                BuktiPesan b = new BuktiPesan();
                b.setKodekategori(rs.getInt("KdKategori"));
                list.add(b);
                
            }

            
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        
        return list;
    }
    
    public List<BuktiPesan> getnmkategori(Integer kodekat){
        PreparedStatement statement;
        List<BuktiPesan> list = null;
        
        try {
            
            list = new ArrayList<>();
            String CARIKATEGORI2 = "SELECT * FROM kategori where KdKategori=?";
            statement = connection.prepareStatement(CARIKATEGORI2);
            statement.setInt(1, kodekat);
            ResultSet rs = statement.executeQuery();
            
            if (!rs.next()) {
                
                JOptionPane.showMessageDialog(null, "Data tidak di temukan");
                
            } else {
                
                BuktiPesan b = new BuktiPesan();
                b.setNamakategori(rs.getString("NmKategori"));
                list.add(b);
                
            }

        } catch (Exception e) {
            
            e.printStackTrace();
        }
        
        return list;
    }
    
    
    // untuk mentapatkan isi combo barang
    public List<BuktiPesan> isicombobarang(Integer kode){
        
        PreparedStatement statement;
        List<BuktiPesan> list = null;
        
        try {
            
            list = new ArrayList<>();
            String TAMPILBARANG = "SELECT * FROM barang where KdKategori=? order by NmBrg";
            statement = connection.prepareStatement(TAMPILBARANG);
            statement.setInt(1, kode);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {

                BuktiPesan b = new BuktiPesan();
                b.setNamabarang(rs.getString("NmBrg"));
                list.add(b);
                
            }
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        
        
        return list;
        
        
    }
    // untuk mentapatkan isi combo barang berdasarkan inputan kode
    public List<BuktiPesan> isicombobarang2(String kode){
        
        PreparedStatement statement;
        List<BuktiPesan> list = null;
        
        try {
            
            list = new ArrayList<BuktiPesan>();
            String TAMPILBARANG = "SELECT * FROM barang where KdBrg=? order by NmBrg";
            statement = connection.prepareStatement(TAMPILBARANG);
            statement.setString(1, kode);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {

                BuktiPesan b = new BuktiPesan();
                b.setNamabarang(rs.getString("NmBrg"));
                list.add(b);
                
            }
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        
        
        return list;
        
        
    }
    
    //  method mendapatkan kode barang berdasarkan nama pelanggan
    public List<BuktiPesan> getkdbarang(String namabrg) {
        PreparedStatement statement;
        List<BuktiPesan> list = null;
        
        try {
            
            list = new ArrayList<>();
            String CARIBARANG2 = "SELECT * FROM barang where NmBrg=?";
            statement = connection.prepareStatement(CARIBARANG2);
            statement.setString(1, namabrg);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                
                BuktiPesan b = new BuktiPesan();
                b.setKodebarang(rs.getString("KdBrg"));
                b.setHarga(rs.getInt("HargaBrg"));
                b.setStok(rs.getInt("Stok"));
                list.add(b);
                
            } 
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        
        return list;
    }
    
    //  method mendapatkan nama barang berdasarkan kode barang yang di inputkan
    public List<BuktiPesan> getnmbarang(Integer kodebarang) {
        PreparedStatement statement;
        int i=0;
        List<BuktiPesan> list = null;
        
        try {
            
            list = new ArrayList<>();
            String CARIBARANG = "SELECT a.*, b.*, FROM barang a, kategori b, where a.KdKategori=b.KdKategori and KdBrg=?";
            statement = connection.prepareStatement(CARIBARANG);
            statement.setInt(1, kodebarang);
            ResultSet rs = statement.executeQuery();
            BuktiPesan b = new BuktiPesan();
            
            if (!rs.next()) { // jika pencarian tidak ketemu
                
                JOptionPane.showMessageDialog(null, "Data tidak di temukan");
                b.setKodekategori(0);
                b.setNamakategori("");
                b.setKodebarang("");
                b.setNamabarang("");
                b.setHarga(0);
                b.setStok(0);
                list.add(b);
                
            } else {
                
                
                b.setKodekategori(rs.getInt("KdKategori"));
                b.setNamakategori(rs.getString("NmKategori"));
                b.setNamabarang(rs.getString("NmBrg"));
                b.setHarga(rs.getInt("HargaBrg"));
                b.setStok(rs.getInt("Stok"));
                list.add(b);
                
            }
           
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        
        return list;
    }
    
    
    
    

    @Override
    public void update(BuktiPesan object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BuktiPesan> getALl() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BuktiPesan> getCari(String key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int autonumber(BuktiPesan object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
