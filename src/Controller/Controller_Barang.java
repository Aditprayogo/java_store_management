/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DAO_Barang;
import DAO.DAO_Pelanggan;
import Model.Barang;
import View.MBarang;
import View.MPelanggan;
import java.awt.Color;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LENOVO
 */
public class Controller_Barang {
    
    MBarang form;
    DAO_Barang model;
    List<Barang> list;
    String[] header;
    
    public Controller_Barang(MBarang form){
        
        this.form = form;
        model = new DAO_Barang();
        list = model.getALl();
        header = new String[] {"KODE BARANG", "HARGA BARANG", "SATUAN", "HARGA", "STOK", "KODE KATEGORI", "NAMA KATEGORI"};
        
        form.getTblbarang().setShowGrid(true);
        form.getTblbarang().setShowVerticalLines(true);
        form.getTblbarang().setGridColor(Color.blue);
    }
    
    public void isicombokateogri() {
        
        form.getCmbkategori().removeAllItems();
        form.getCmbkategori().addItem("-PILIH-");
        for (Barang B : model.IsiCombo()) {
            form.getCmbkategori().addItem(B.getKodekategori().toString());
        }
        
    }
    
    public void tampilurutkode(){
        if (form.getCmbkategori().getSelectedIndex() > 0) {
            
            form.getTxtkdbarang().setText(String.valueOf(model.autonumber2(Integer.parseInt
        (form.getCmbkategori().getSelectedItem().toString()))));
            
        }
    }
    
    public void isitable() {
        
        list = model.getALl();
        
        DefaultTableModel tblmodel = new DefaultTableModel(new Object[][]{}, header){
            
            public boolean isCellEditable(int rowIndex, int columnIndex){
                return false;
            }
        };
        
        Object[] data = new Object[header.length];
        for (Barang B : list) {
            data[0] = B.getKodebarang();
            data[1] = B.getNamabarang();
            data[2] = B.getSatuan();
            data[3] = B.getHarga();
            data[4] = B.getStock();
            data[5] = B.getKodekategori();
            data[6] = B.getNamakategori();
            tblmodel.addRow(data);
            
        }
        
        form.getTblbarang().setModel(tblmodel);
        
    }
    
    public void reset(){
        
        form.getCmbkategori().setSelectedIndex(0);
        form.getTxtnmbarang().setText("");
        form.getTxtkdbarang().setText("");
        form.getTxtnmbarang().setText("");
        form.getCmbsatuan().setSelectedIndex(0);
        form.getTxtharga().setText("");
        form.getTxtstock().setText("");
        form.getTxtkatakunci().setText("");
        
        form.getTxtnmbarang().requestFocus();
        form.getTxtnmkategori().setEditable(false);
        form.getTxtkdbarang().setEditable(false);
        isicombokateogri();
        tampilurutkode();
        isitable();
    }
    
    public void isiField(int row){
        
        form.getCmbkategori().setSelectedItem(String.valueOf(list.get(row).getKodekategori()));
        
        form.getTxtnmkategori().setText(list.get(row).getNamakategori());
        
        form.getTxtkdbarang().setText(list.get(row).getKodebarang());
        
        form.getTxtnmbarang().setText(list.get(row).getNamabarang());
        
        form.getCmbsatuan().setSelectedItem(String.valueOf(list.get(row).getSatuan()));
        
        form.getTxtharga().setText(String.valueOf(list.get(row).getHarga()));
        
        form.getTxtstock().setText(String.valueOf(list.get(row).getStock()));
        
    }
    
    public void insert() {
        
        Barang b = new Barang();
        
        b.setKodebarang(form.getTxtkdbarang().getText());
        
        b.setNamabarang(form.getTxtnmbarang().getText());
        
        b.setSatuan(Integer.parseInt(form.getCmbsatuan().getSelectedItem().toString()));
        
        b.setHarga(Integer.parseInt(form.getTxtharga().getText()));
        
        b.setStock(Integer.parseInt(form.getTxtstock().getText()));
        
        b.setKodekategori(Integer.parseInt(form.getCmbkategori().getSelectedItem().toString()));
        
        model.insert(b);
        
    }
    
    public void update() {
         
        Barang b = new Barang();
       
        b.setNamabarang(form.getTxtnmbarang().getText());
        
        b.setSatuan(Integer.parseInt(form.getCmbsatuan().getSelectedItem().toString()));
        
        b.setHarga(Integer.parseInt(form.getTxtharga().getText()));
        
        b.setStock(Integer.parseInt(form.getTxtstock().getText()));
        
        b.setKodekategori(Integer.parseInt(form.getCmbkategori().getSelectedItem().toString()));
        
        b.setKodebarang(form.getTxtkdbarang().getText());
        
        model.update(b);
    }
    
    public void delete() {
        if (!form.getTxtkdbarang().getText().trim().isEmpty()) {
            
            String id = (form.getTxtkdbarang().getText());
            model.delete(id);
            
        } else {
            
            JOptionPane.showMessageDialog(form, "Pilih data yang akan di hapus");
        }
    }
    
    public void isiTableCari(){
        list = model.getCari(form.getTxtkatakunci().getText().trim());
        
        DefaultTableModel tblmodel = new DefaultTableModel(new Object[][]{}, header);
        
        Object[] data = new Object[header.length];
        
        for (Barang B : list) {
            data[0] = B.getKodebarang();
            data[1] = B.getNamabarang();
            data[2] = B.getSatuan();
            data[3] = B.getHarga();
            data[4] = B.getStock();
            data[5] = B.getKodekategori();
            data[6] = B.getNamakategori();
            tblmodel.addRow(data);
            
        }
        
        form.getTblbarang().setModel(tblmodel);
    }
    
    public void tampilnamakategori(){
        
        if (form.getCmbkategori().getSelectedIndex() != 0) {
            for (Barang b : model.getDataKategori(Integer
                    .parseInt(form.getCmbkategori().getSelectedItem().toString()))) 
            {
                
                form.getTxtnmkategori().setText(b.getNamakategori());
                
            }
        }
        
    }
}
