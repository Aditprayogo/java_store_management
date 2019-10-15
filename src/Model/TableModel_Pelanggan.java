/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author LENOVO
 */
import java.util.List;
import javax.swing.table.AbstractTableModel;
        
public class TableModel_Pelanggan extends AbstractTableModel{
    
    List<Pelanggan> list;
    public TableModel_Pelanggan(List<Pelanggan> list){
        this.list = list;
    }

    @Override
    public int getRowCount() {
        
        return list.size();
  
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       switch(columnIndex){
           case 0:
               return list.get(rowIndex).getKode();
           case 1:
               return list.get(rowIndex).getNama();
           case 2:
               return list.get(rowIndex).getAlamat();
           case 3:
               return list.get(rowIndex).getTelp();
           default:
               return null;
       }
    }
    
    @Override
    public String getColumnName(int column) {
       switch(column){
           case 0:
               return "KODE";
           case 1:
               return "NAMA PELANGGAN";
           case 2:
               return "ALAMAT";
           case 3:
               return "TELEPON";
           default:
               return null;
       }
    }
    
    
    
}
