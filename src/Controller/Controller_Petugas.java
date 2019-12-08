/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DAO_Kategori;
import DAO.DAO_Pelanggan;
import DAO.DAO_Petugas;
import DAO.Model_DAO;
import Model.Barang;
import Model.Petugas;
import Model.TableModel_Pelanggan;
import Model.TableModel_Petugas;
import View.MKategoriBarang;
import View.MPelanggan;
import View.MPetugas;
import java.awt.Color;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LENOVO
 */
public class Controller_Petugas {
    
    MPetugas form;
    Model_DAO<Petugas> model;
    List<Petugas> list;
    String[] header;
    
    public Controller_Petugas(MPetugas form){
        this.form = form;
        model = new DAO_Petugas();
        list = model.getALl();
        header = new String[] {"KODE PETUGAS", "NAMA PETUGAS", "ALAMAT", "TELEPON"};
    }
    
     public void reset(){
        form.getTxtkdpetugas().setText("");
        form.getTxtnmpetugas().setText("");
        form.getTxtalamat().setText("");
        form.getTxttelp().setText("");
    }
     
       public void isiTable() {
        
        list = model.getALl();
        TableModel_Petugas tablePetugas = new TableModel_Petugas(list);
        form.getTblpetugas().setModel(tablePetugas);
        
    }
       
     public void isiField(int row){
        
        form.getTxtkdpetugas().setText(String.valueOf(list.get(row).getKode()));
        form.getTxtnmpetugas().setText(list.get(row).getNama());
        form.getTxtalamat().setText(list.get(row).getAlamat());
        form.getTxttelp().setText(list.get(row).getTelepon());
        
    }
     
    public void insert() {
        
        Petugas p = new Petugas();
        p.setKode(form.getTxtkdpetugas().getText());
        p.setNama(form.getTxtnmpetugas().getText());
        p.setAlamat(form.getTxtalamat().getText());
        p.setTelepon(form.getTxttelp().getText());
        
        model.insert(p);
        
    }
    
    public void update() {
        
        Petugas p = new Petugas();
        
        p.setKode(form.getTxtkdpetugas().getText());
        p.setNama(form.getTxtnmpetugas().getText());
        p.setAlamat(form.getTxtalamat().getText());
        p.setTelepon(form.getTxttelp().getText());
        
        model.update(p);
        
    }
    
    public void delete() {
        if (!form.getTxtkdpetugas().getText().trim().isEmpty()) {
            
            String kode = (form.getTxtkdpetugas().getText());
            model.delete(kode);
            
        } else {
            
            JOptionPane.showMessageDialog(form, "Pilih Data yang akan di hapus");
            
        }
    }
    
    public void isiTableCari() {
        
        list = model.getCari(form.getTxtkatakunci().getText().trim());
        
        DefaultTableModel tblmodel = new DefaultTableModel(new Object[][]{}, header);
        
        Object[] data = new Object[header.length];
        
        for (Petugas p : list) {
            data[0] = p.getKode();
            data[1] = p.getNama();
            data[2] = p.getAlamat();
            data[3] = p.getTelepon();
         
            tblmodel.addRow(data);
            
        }
        
        form.getTblpetugas().setModel(tblmodel);
    }
    
    
    
}
