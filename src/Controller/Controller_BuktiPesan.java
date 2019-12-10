/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DAO_Barang;
import DAO.DAO_BuktiPesan;
import Model.BuktiPesan;
import View.MBarang;
import View.TBuktiPesan;
import java.awt.Color;
import java.text.DecimalFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LENOVO
 */
public class Controller_BuktiPesan {
    TBuktiPesan form;
    DAO_BuktiPesan model;
    
    String[] header = new String[]{"KODE KATEGORI", "NAMA KATEGORI", "KODE BARANG", "NAMA BARANG", "HARGA", "QTY", "TOTAL"};
    DefaultTableModel tblmodel = new DefaultTableModel(new Object[][]{}, header);
    
    
    public boolean isCellEditable(int rowIndex, int columnIndex){
        return false;
    }
    
    public Controller_BuktiPesan(TBuktiPesan form){
        
        this.form = form;
        model = new DAO_BuktiPesan();
         
   
        form.getTbldetil().setModel(tblmodel);
        form.getTbldetil().setShowGrid(true);
        form.getTbldetil().setShowVerticalLines(true);
        form.getTbldetil().setGridColor(Color.blue);
    }
    
    public void tampilurutkode(){
       
        form.getTxtnobp().setText(model.autonumer2());
    }
    
    public void reset(){
        
        SimpleDateFormat tgl = new SimpleDateFormat("yyyy-MM-dd");
        form.getTxttgbp().setText(String.valueOf(tgl.format(new Date())));
        
        form.getTxttotal().setText("Rp.0");
        form.getCmdpelanggan().setSelectedIndex(0);
        form.getCmbkategori().setSelectedIndex(0);
        form.getCmbbarang().setSelectedIndex(0);
        form.getTxtkdplg().setText("");
        form.getTxtkdbarang().setText("");
        form.getTxtkdkategori().setText("");
        form.getTxtharga().setText("");
        form.getTxtqty().setText("");
        form.getTxtstok().setText("");
        
        tampilurutkode();
        isicombopelanggan();
        isicombokategori();
        tblmodel.setRowCount(0);
        form.getTxtkdplg().requestFocus();
        form.getTxtnobp().setEditable(false);
        form.getTxttgbp().setEditable(false);
        form.getTxtstok().setEditable(false);
        
    }
    
    public void reset2(){
        form.getTxtharga().setText("");
        form.getTxtqty().setText("");
        form.getTxtstok().setText("");
        form.getTxtkdbarang().requestFocus();
        form.getTxtstok().setEditable(false);
    }
    
    public void reset3(){
        form.getCmbkategori().setSelectedIndex(0);
        form.getCmbbarang().setSelectedIndex(0);
        form.getTxtkdbarang().setText("");
        form.getTxtharga().setText("");
        form.getTxtqty().setText("");
        form.getTxtstok().setText("");
        form.getTxtkdkategori().setText("");
        form.getTxtkdbarang().requestFocus();
        form.getTxtstok().setEditable(false);
    }
    
    public void resetrow(){
        int[] selectedrows = form.getTbldetil().getSelectedRows();
            if (selectedrows.length > 0) {
                
                for (int i = selectedrows.length - 1; i >= 0; i--) {
                    
                    tblmodel.removeRow(selectedrows[i]);
                    
                }
            
        }
            
        form.getTxtkdbarang().requestFocus();
    }
    
    public void isitable(){
        int jmlbaris = tblmodel.getRowCount();
        int i, datasama = 0;
        int stok = 1;
        
        //cek inputan qty dan jumlah beli
        if ((form.getTxtqty().getText().isEmpty() == true) || (Integer.parseInt(form.getTxtqty().getText()) 
                > Integer.parseInt(form.getTxtstok().getText()))) {
        
            
            JOptionPane.showMessageDialog(null, "quantity atau stok tidak boleh melebihi jumlah yang telah di tetapkan");
            stok = 0;
            
        }
        
        if (!form.getTxtkdkategori().getText().isEmpty() && stok>0) {
            
            if (jmlbaris == 0) {
                
                tblmodel.addRow(new Object[] {
                    
                    form.getTxtkdkategori().getText(),
                    form.getCmbkategori().getSelectedItem().toString(),
                    form.getTxtkdbarang().getText(),
                    form.getCmbbarang().getSelectedItem().toString(),
                    form.getTxtharga().getText(),
                    form.getTxtqty().getText(),
                    (Integer.parseInt(form.getTxtharga().getText()) * Integer.parseInt(form.getTxtqty().getText()))
     
                });
                
            } else {
                //jika sudah ada di dalam JTABLE , maka cek validasi 
                //tidak boleh kode barang yang sama
                for (i = 0; i < jmlbaris; i++) {
                    
                    if (form.getTxtkdbarang().getText().equals(tblmodel.getValueAt(i, 2).toString())) {
                        
                        datasama = 1;
                        JOptionPane.showMessageDialog(null, "Kode Barang " + tblmodel.getValueAt(i, 2).toString() + " Sudah pernah ada dan di update dengan data baru");
                        
                        tblmodel.setValueAt(form.getTxtkdkategori().getText(), i, 0);
                        tblmodel.setValueAt(form.getCmbkategori().getSelectedItem().toString(), i, 1);
                        tblmodel.setValueAt(form.getTxtkdbarang().getText(), i, 2);
                        tblmodel.setValueAt(form.getCmbbarang().getSelectedItem().toString(), i, 3);
                        tblmodel.setValueAt(form.getTxtharga().getText(), i, 4);
                        tblmodel.setValueAt(form.getTxtqty().getText(), i, 5);
                        tblmodel.setValueAt((Integer.parseInt(form.getTxtharga().getText()) * Integer.parseInt(form.getTxtqty().getText())),i,6);
                        
                        break;
                        
                    } else {
                        
                        
                        datasama = 0;
                        
                        
                    }
                    
                }
                
                // jika kode barang belum ada di dalam list maka add ke dalam jTable
                if (datasama == 0) {
                    
                    tblmodel.addRow(new Object[] {
                    
                        form.getTxtkdkategori().getText(),
                        form.getCmbkategori().getSelectedItem().toString(),
                        form.getTxtkdbarang().getText(),
                        form.getCmbbarang().getSelectedItem().toString(),
                        form.getTxtharga().getText(),
                        form.getTxtqty().getText(),
                        (Integer.parseInt(form.getTxtharga().getText()) * Integer.parseInt(form.getTxtqty().getText()))
     
                    });
                    
                }
                
                
            }
            
        }
        
        
    }
    
    public void hitung_grandtotal() {
        int jmlbaris = tblmodel.getRowCount();
        int total = 0;
        int amount = 0;
        
        DecimalFormat konversi = new DecimalFormat("###,###,###.00");
        for (int i = 0; i < tblmodel.getRowCount(); i++) {
            
            amount = Integer.parseInt(String.valueOf(tblmodel.getValueAt(i, 6).toString()));
            total = amount + total;
            
        }
        
        //jumlah baris berisi data
        if (jmlbaris != 0) {
            
            form.getTxttotal().setText("Rp. "+ konversi.format(Integer.parseInt(String.valueOf(total))));
            
            
        } else {
            
            form.getTxttotal().setText("Rp. 0");
            
        }
    }
    
    public void isiField(int row){
        
        form.getTxtkdkategori().setText(String.valueOf(tblmodel.getValueAt(row, 0)));
        
        form.getCmbkategori().setSelectedItem(tblmodel.getValueAt(row, 1).toString());
        
        form.getTxtkdbarang().setText(tblmodel.getValueAt(row, 2).toString());
        
        form.getCmbbarang().setSelectedItem(tblmodel.getValueAt(row, 3).toString());
        
        form.getTxtharga().setText(String.valueOf(tblmodel.getValueAt(row, 4)));
        
        form.getTxtqty().setText(String.valueOf(tblmodel.getValueAt(row, 5)));
        
    }
    
    public void isicombopelanggan(){
        form.getCmdpelanggan().removeAllItems();
        form.getCmdpelanggan().addItem("-PILIH-");
        for (BuktiPesan b : model.isicomboplg()) {
            
            form.getCmdpelanggan().addItem(b.getNamaplg());
            
        }
    }
    
    public void tampilkdplg() {
        if (form.getCmdpelanggan().getSelectedIndex()!= 0) {
            for (BuktiPesan b : model.getkdplg(form.getCmdpelanggan().getSelectedItem().toString())) {
             
                form.getTxtkdplg().setText(String.valueOf(b.getKodeplg()));
            }
            
        }
    }
    
    //method ini digunakan untuk menampilkan nama pelanggan berdasarkan inputan text kode pelanggan
    public void tampilnmplg(){
        for (BuktiPesan b : model.getnmplg(Integer.parseInt(form.getTxtkdplg().getText()))) {
            
            form.getCmdpelanggan().setSelectedItem(b.getNamaplg());
            
        }
    }
    
    public void isicombokategori() {
        form.getCmbkategori().removeAllItems();
        form.getCmbkategori().addItem("--PILIH--");
        for (BuktiPesan b : model.isicombokategori()) {
            
            form.getCmbkategori().addItem(b.getNamakategori());
            
        }
    }
    
    public void tampilkdkategori(){
        if (form.getCmbkategori().getSelectedIndex() != 0) {
            for (BuktiPesan b : model.getkdkategori(form.getCmbkategori().getSelectedItem().toString())) {
                
                form.getTxtkdkategori().setText(String.valueOf(b.getKodekategori()));
                
            }
        }
    }
    
    public void tampilnmkategori(){
        for (BuktiPesan b : model.getnmkategori(Integer.parseInt(form.getTxtkdkategori().getText()))) {
            form.getCmbkategori().setSelectedItem(b.getNamakategori());
        }
    }
    
    public void isicombobarang() {
        form.getCmbbarang().removeAllItems();
        form.getCmbbarang().addItem("--PILIH--");
        for (BuktiPesan b : model.isicombobarang(Integer.parseInt(form.getTxtkdkategori().getText()))) {
            
            form.getCmbbarang().addItem(b.getNamabarang());
            
        }
    }
    
    public void isicombobarang2() {
        form.getCmbbarang().removeAllItems();
        form.getCmbbarang().addItem("--PILIH--");
        for (BuktiPesan b : model.isicombobarang2(form.getTxtkdbarang().getText())) {
            
            form.getCmbbarang().addItem(b.getNamabarang());
            form.getCmbbarang().setSelectedItem(b.getNamabarang());
            
        }
    }
    
    public void tampilkdbarang(){
        if (form.getCmbkategori().getSelectedIndex() != 0) {
            for (BuktiPesan b : model.getkdbarang(form.getCmbbarang().getSelectedItem().toString())) {
                
                form.getTxtkdbarang().setText(String.valueOf(b.getKodebarang()));
                form.getTxtharga().setText(String.valueOf(b.getHarga()));
                form.getTxtstok().setText(String.valueOf(b.getStok()));   
                
            }
        }
    }
    
    //mentampilkan data barang berdasarkan text kode barang
    public void tampilnmbarang(){
        
        for (BuktiPesan b : model.getnmbarang(Integer.parseInt(form.getTxtkdbarang().getText()))) {
            
            form.getCmbbarang().setSelectedItem(b.getNamabarang());
            form.getCmbkategori().setSelectedItem(b.getNamakategori());
            form.getTxtkdkategori().setText(String.valueOf(b.getKodekategori()));
            form.getTxtharga().setText(String.valueOf(b.getHarga()));
            form.getTxtstok().setText(String.valueOf(b.getStok()));
            
            if (b.getKodebarang().equals("")) {
                
                form.getTxtkdbarang().setText("");
                
            }
            
        }
        
        
    }
    
    public void simpan_transaksi(){
        BuktiPesan b = new BuktiPesan();
        
        b.setNobp(form.getTxtnobp().getText());
        b.setTglbp(form.getTxttgbp().getText());
        b.setKodeplg(Integer.parseInt(form.getTxtkdplg().getText()));
        model.insert(b);
    }
    
    public void simpan_detiltransaksi(){
        
        int jmlbaris = tblmodel.getRowCount();
        int simpan = 0;
        int i = 0;
        
        for ( i = 0; i < 10; i++) {
            
            BuktiPesan b = new BuktiPesan();
            
            b.setNobp(form.getTxtnobp().getText());
            b.setKodebarang(tblmodel.getValueAt(i, 2).toString());
            b.setHarga(Integer.parseInt(tblmodel.getValueAt(i, 4).toString()));
            b.setQty(Integer.parseInt(tblmodel.getValueAt(i, 5).toString()));
            model.insert_detiltransaksi(b);
            model.update_stock(b);
            simpan = simpan + 1;
            
        }
        
        if (simpan > 0 ) {
            
            JOptionPane.showMessageDialog(null, "detil belanja berhasil di simpan dan stok barang berhasil di ubah");
            
        }
        
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
}
