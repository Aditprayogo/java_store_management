/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Koneksi.Database;

/**
 *
 * @author LENOVO
 */
public class MenuUtama extends javax.swing.JFrame {

    /**
     * Creates new form MenuUtama
     */
    public MenuUtama() {
        initComponents();
        this.setSize(1000, 600);
            jPanel2.setSize(1000,600);
            setLocationRelativeTo(this);
            Koneksi.Database.KoneksiDB();
            
       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu11 = new javax.swing.JMenu();
        jMenu12 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jMenuBar3 = new javax.swing.JMenuBar();
        jMenu13 = new javax.swing.JMenu();
        jMenu14 = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        MMenu = new javax.swing.JMenu();
        MPelanggan = new javax.swing.JMenuItem();
        MKategori = new javax.swing.JMenuItem();
        MBarang = new javax.swing.JMenuItem();
        MPetugas = new javax.swing.JMenuItem();
        MTransaksi = new javax.swing.JMenu();
        TBuktiPesan = new javax.swing.JMenuItem();
        MLaporan = new javax.swing.JMenu();
        LPelanggan = new javax.swing.JMenuItem();
        LBarang = new javax.swing.JMenuItem();
        MExit = new javax.swing.JMenu();

        jMenu1.setText("jMenu1");

        jMenu2.setText("jMenu2");

        jMenu3.setText("jMenu3");

        jMenu4.setText("jMenu4");

        jMenu5.setText("jMenu5");

        jMenu6.setText("jMenu6");

        jMenu7.setText("jMenu7");

        jMenuItem1.setText("jMenuItem1");

        jMenu8.setText("jMenu8");

        jMenuItem2.setText("jMenuItem2");

        jMenuItem3.setText("jMenuItem3");

        jMenuItem4.setText("jMenuItem4");

        jMenuItem5.setText("jMenuItem5");

        jMenuItem6.setText("jMenuItem6");

        jMenuItem7.setText("jMenuItem7");

        jMenu11.setText("File");
        jMenuBar2.add(jMenu11);

        jMenu12.setText("Edit");
        jMenuBar2.add(jMenu12);

        jMenuItem8.setText("jMenuItem8");

        jMenuItem9.setText("jMenuItem9");

        jMenuItem10.setText("jMenuItem10");

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        jMenu13.setText("File");
        jMenuBar3.add(jMenu13);

        jMenu14.setText("Edit");
        jMenuBar3.add(jMenu14);

        jMenuItem11.setText("jMenuItem11");

        jMenuItem12.setText("jMenuItem12");

        jMenuItem13.setText("jMenuItem13");

        jMenuItem14.setText("jMenuItem14");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        MMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Icon/1 - user.png"))); // NOI18N
        MMenu.setText("File Master");

        MPelanggan.setText("Entri Data Pelanggan");
        MPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MPelangganActionPerformed(evt);
            }
        });
        MMenu.add(MPelanggan);

        MKategori.setText("Entri Data Kategori");
        MKategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MKategoriActionPerformed(evt);
            }
        });
        MMenu.add(MKategori);

        MBarang.setText("Entri Data Barang");
        MBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MBarangActionPerformed(evt);
            }
        });
        MMenu.add(MBarang);

        MPetugas.setText("Entri Data Petugas");
        MPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MPetugasActionPerformed(evt);
            }
        });
        MMenu.add(MPetugas);

        jMenuBar1.add(MMenu);

        MTransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Icon/trans-add.png"))); // NOI18N
        MTransaksi.setText("Transaksi");

        TBuktiPesan.setText("Bukti Pesanan");
        MTransaksi.add(TBuktiPesan);

        jMenuBar1.add(MTransaksi);

        MLaporan.setText("Laporan");

        LPelanggan.setText("Laporan Data Pelanggan");
        LPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LPelangganActionPerformed(evt);
            }
        });
        MLaporan.add(LPelanggan);

        LBarang.setText("Laporan Data Barang");
        LBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LBarangActionPerformed(evt);
            }
        });
        MLaporan.add(LBarang);

        jMenuBar1.add(MLaporan);

        MExit.setText("Exit ");
        MExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MExitMouseClicked(evt);
            }
        });
        jMenuBar1.add(MExit);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(363, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(133, 133, 133))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(175, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MPelangganActionPerformed
        
        View.MPelanggan P = new View.MPelanggan();
        P.setVisible(true);
        setLocationRelativeTo(this);
        
    }//GEN-LAST:event_MPelangganActionPerformed

    private void MKategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MKategoriActionPerformed
        // TODO add your handling code here:
         View.MKategoriBarang P = new View.MKategoriBarang();
         P.setVisible(true);
         setLocationRelativeTo(this);
    }//GEN-LAST:event_MKategoriActionPerformed

    private void MBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MBarangActionPerformed

    private void LPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LPelangganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LPelangganActionPerformed

    private void LBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LBarangActionPerformed

    private void MExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MExitMouseClicked
            // TODO add your handling code here:
            System.exit(0);
           
    }//GEN-LAST:event_MExitMouseClicked

    private void MPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MPetugasActionPerformed
        // TODO add your handling code here:
         View.MPetugas P = new View.MPetugas();
         P.setVisible(true);
         setLocationRelativeTo(this);
    }//GEN-LAST:event_MPetugasActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuUtama().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem LBarang;
    private javax.swing.JMenuItem LPelanggan;
    private javax.swing.JMenuItem MBarang;
    private javax.swing.JMenu MExit;
    private javax.swing.JMenuItem MKategori;
    private javax.swing.JMenu MLaporan;
    private javax.swing.JMenu MMenu;
    private javax.swing.JMenuItem MPelanggan;
    private javax.swing.JMenuItem MPetugas;
    private javax.swing.JMenu MTransaksi;
    private javax.swing.JMenuItem TBuktiPesan;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu12;
    private javax.swing.JMenu jMenu13;
    private javax.swing.JMenu jMenu14;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    // End of variables declaration//GEN-END:variables
}
