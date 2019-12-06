/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DAO_Kategori;
import DAO.DAO_Petugas;
import DAO.Model_DAO;
import Model.Petugas;
import View.MKategoriBarang;
import View.MPetugas;
import java.awt.Color;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class Controller_Petugas {
    
    MPetugas form;
    Model_DAO<Petugas> model;
    List<Petugas> list;
    String[] header;
    
    public Controller_Petugas (MPetugas form) {
        this.form = form;
        model = new DAO_Petugas();
        list = model.getALl();
        header = new String[]{"KODE PETUGAS", "NAMA PETUGAS"};
        
        form.getTblpetugas().setShowGrid(true);
        form.getTblpetugas().setShowVerticalLines(true);
        form.getTblpetugas().setGridColor(Color.blue);
    }
    
}
