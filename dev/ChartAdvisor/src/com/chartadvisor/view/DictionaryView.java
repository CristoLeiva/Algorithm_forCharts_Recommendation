/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chartadvisor.view;

import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Cristo
 */
public class DictionaryView extends javax.swing.JFrame {

    /**
     * Creates new form DictionaryView
     */
    public DictionaryView() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/chartadvisor/view/images/mainicon.png"))).getImage());// ADD!!
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnl_Main = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jtxt_LOM = new javax.swing.JTextField();
        jtxt_label = new javax.swing.JTextField();
        jtxt_type = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jbtn_modify = new javax.swing.JButton();
        jbtn_add = new javax.swing.JButton();
        jbtn_delete = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jlist_dictionary = new javax.swing.JList();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chart Advisor - Dictionary");

        jpnl_Main.setBackground(new java.awt.Color(244, 244, 244));
        jpnl_Main.setLayout(null);

        jPanel1.setBackground(new java.awt.Color(244, 244, 244));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true), "Settings", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(51, 51, 51))); // NOI18N
        jPanel1.setLayout(null);

        jtxt_LOM.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtxt_LOM.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jtxt_LOM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxt_LOMActionPerformed(evt);
            }
        });
        jPanel1.add(jtxt_LOM);
        jtxt_LOM.setBounds(70, 150, 150, 30);

        jtxt_label.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtxt_label.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jtxt_label.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxt_labelActionPerformed(evt);
            }
        });
        jPanel1.add(jtxt_label);
        jtxt_label.setBounds(70, 50, 150, 30);

        jtxt_type.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtxt_type.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jtxt_type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxt_typeActionPerformed(evt);
            }
        });
        jPanel1.add(jtxt_type);
        jtxt_type.setBounds(70, 100, 150, 30);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("LOM:");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(10, 150, 50, 30);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Type:");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(10, 100, 50, 30);

        jbtn_modify.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/chartadvisor/view/images/modify.png"))); // NOI18N
        jPanel1.add(jbtn_modify);
        jbtn_modify.setBounds(30, 200, 50, 40);

        jbtn_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/chartadvisor/view/images/add.png"))); // NOI18N
        jPanel1.add(jbtn_add);
        jbtn_add.setBounds(150, 200, 50, 40);

        jbtn_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/chartadvisor/view/images/delete.png"))); // NOI18N
        jPanel1.add(jbtn_delete);
        jbtn_delete.setBounds(90, 200, 50, 40);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Label:");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(10, 50, 50, 30);

        jpnl_Main.add(jPanel1);
        jPanel1.setBounds(10, 10, 230, 250);

        jPanel3.setBackground(new java.awt.Color(244, 244, 244));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true), "Available Elements", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(51, 51, 51))); // NOI18N
        jPanel3.setLayout(null);

        jlist_dictionary.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jlist_dictionary);

        jPanel3.add(jScrollPane2);
        jScrollPane2.setBounds(10, 50, 350, 190);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("LOM");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel3.add(jLabel2);
        jLabel2.setBounds(270, 20, 50, 30);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Label");
        jLabel5.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel3.add(jLabel5);
        jLabel5.setBounds(20, 20, 50, 30);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Type");
        jLabel6.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel3.add(jLabel6);
        jLabel6.setBounds(140, 20, 50, 30);

        jpnl_Main.add(jPanel3);
        jPanel3.setBounds(250, 10, 370, 250);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnl_Main, javax.swing.GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnl_Main, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtxt_LOMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxt_LOMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxt_LOMActionPerformed

    private void jtxt_labelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxt_labelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxt_labelActionPerformed

    private void jtxt_typeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxt_typeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxt_typeActionPerformed

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
            java.util.logging.Logger.getLogger(DictionaryView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DictionaryView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DictionaryView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DictionaryView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DictionaryView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbtn_add;
    private javax.swing.JButton jbtn_delete;
    private javax.swing.JButton jbtn_modify;
    private javax.swing.JList jlist_dictionary;
    private javax.swing.JPanel jpnl_Main;
    private javax.swing.JTextField jtxt_LOM;
    private javax.swing.JTextField jtxt_label;
    private javax.swing.JTextField jtxt_type;
    // End of variables declaration//GEN-END:variables

    public JButton getJbtn_add() {
        return jbtn_add;
    }

    public void setJbtn_add(JButton jbtn_add) {
        this.jbtn_add = jbtn_add;
    }

    public JButton getJbtn_delete() {
        return jbtn_delete;
    }

    public void setJbtn_delete(JButton jbtn_delete) {
        this.jbtn_delete = jbtn_delete;
    }

    public JButton getJbtn_modify() {
        return jbtn_modify;
    }

    public void setJbtn_modify(JButton jbtn_modify) {
        this.jbtn_modify = jbtn_modify;
    }

    public JList getJlist_dictionary() {
        return jlist_dictionary;
    }

    public void setJlist_dictionary(JList jlist_dictionary) {
        this.jlist_dictionary = jlist_dictionary;
    }

    public JPanel getJpnl_Main() {
        return jpnl_Main;
    }

    public void setJpnl_Main(JPanel jpnl_Main) {
        this.jpnl_Main = jpnl_Main;
    }

    public JTextField getJtxt_LOM() {
        return jtxt_LOM;
    }

    public void setJtxt_LOM(JTextField jtxt_LOM) {
        this.jtxt_LOM = jtxt_LOM;
    }

    public JTextField getJtxt_label() {
        return jtxt_label;
    }

    public void setJtxt_label(JTextField jtxt_label) {
        this.jtxt_label = jtxt_label;
    }

    public JTextField getJtxt_type() {
        return jtxt_type;
    }

    public void setJtxt_type(JTextField jtxt_type) {
        this.jtxt_type = jtxt_type;
    }
    
}
