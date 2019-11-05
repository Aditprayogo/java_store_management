/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DAO_Pelanggan;
import DAO.Model_DAO;
import Model.Pelanggan;
import Model.TableModel_Pelanggan;
import View.MPelanggan;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author LENOVO
 */
public class Controller_Pelanggan {
    MPelanggan form;
    Model_DAO<Pelanggan> model;
    List<Pelanggan> list;
    
    public Controller_Pelanggan(MPelanggan form){
        this.form = form;
        model = new DAO_Pelanggan();
        list = model.getALl();
    }
    
    public void reset(){
        form.getTxtkdplg().setText("");
        form.getTxtnmplg().setText("");
        form.getTxtalamat().setText("");
//        
        form.getTxttelp().setText("");
    }
    
    public void isiTable() {
        
        list = model.getALl();
        TableModel_Pelanggan tablePelanggan = new TableModel_Pelanggan(list);
        form.getTblplg().setModel(tablePelanggan);
        
    }
    
    public void isiField(int row){
        
        form.getTxtkdplg().setText(String.valueOf(list.get(row).getKode()));
        form.getTxtnmplg().setText(list.get(row).getNama());
        form.getTxtalamat().setText(list.get(row).getAlamat());
        form.getTxttelp().setText(list.get(row).getTelp());
        
    }
    
    public void insert() {
        
        Pelanggan p = new Pelanggan();
        
        p.setKode(form.getTxtkdplg().getText());
        p.setNama(form.getTxtnmplg().getText());
        p.setAlamat(form.getTxtalamat().getText());
        p.setTelp(form.getTxttelp().getText());
        
        model.insert(p);
        
    }
    
    public void update() {
        
        Pelanggan p = new Pelanggan();
        
        p.setKode(form.getTxtkdplg().getText());
        p.setNama(form.getTxtnmplg().getText());
        p.setAlamat(form.getTxtalamat().getText());
        p.setTelp(form.getTxttelp().getText());
        
        model.update(p);
        
    }
    
    public void delete() {
        
        if (!form.getTxtkdplg().getText().trim().isEmpty()) {
            
            String kode = (form.getTxtkdplg().getText());
            model.delete(kode);
            
        } else {
            
            JOptionPane.showMessageDialog(form, "Pilih data yang akan di hapus");
        }
       
    }
    
    public void isiTableCari() {
        
        list = model.getCari(form.getTxtkdplg().getText().trim());
        TableModel_Pelanggan tablePelanggan = new TableModel_Pelanggan(list);
        form.getTblplg().setModel(tablePelanggan);
        
    }
    
    
    
    
}
