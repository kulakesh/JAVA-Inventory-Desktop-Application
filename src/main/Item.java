/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.*;
import java.sql.ResultSet;
import java.text.ParseException;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kulakesh
 */
public class Item extends javax.swing.JInternalFrame {

    String listCategory[][];

    public Item() {
        initComponents();
        ini();
        addEventToObject();
        btnNewActionPerformed(null);
    }

    private void ini() {
        jLabel15.setVisible(false);
        txtItemID.setVisible(false);
        new config.functions().numOnly(txtAmount);

    }

    private void loadListArrays() {
        Integer j = 0;
        try {
            new config.functions().ClearTable(tblMain);
            DefaultTableModel mdlClient = (DefaultTableModel) tblMain.getModel();
            ResultSet rs1 = new config.RecordSet().Open("SELECT * FROM items WHERE del=0 ORDER BY name");
            while (rs1.next()) {
                mdlClient.insertRow(tblMain.getRowCount(), new Object[]{j+1, rs1.getString("sap_code"), rs1.getString("category"),
                            rs1.getString("name"), rs1.getString("amount"), rs1.getString("id")});
                j++;
            }
            rs1.close();
            j = 0;

            rs1 = new config.RecordSet().Open("SELECT DISTINCT category FROM items ORDER BY category");
            if (rs1.next()) {
                rs1.last();
                listCategory = new String[rs1.getRow()][5];
                rs1.beforeFirst();
                while (rs1.next()) {
                    listCategory[j][0] = rs1.getString("category");
                    j++;
                }
            }
            rs1.close();

        } catch (Exception e) {
            System.out.println("Invoice>loadListArrays :" + e);
        }
        //System.out.println(Arrays.deepToString(listCategory));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        txtSAPCode = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtItemID = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtItemName = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtCategory = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtAmount = new javax.swing.JTextField();
        scMain = new javax.swing.JScrollPane();
        tblMain = new javax.swing.JTable();
        btnNew = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        scOption = new javax.swing.JScrollPane();
        tblOption = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(152, 22, 22));
        jLabel2.setText("Item Entry");

        jLabel8.setText("SAP Code");

        jLabel15.setText("System Code");

        txtItemID.setEnabled(false);

        jLabel10.setText("Item Name");

        jLabel11.setText("Category");

        jLabel12.setText("ALP");

        tblMain.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SL", "SAP", "Category", "Name", "ALP", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMain.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMainMouseClicked(evt);
            }
        });
        scMain.setViewportView(tblMain);
        tblMain.getColumnModel().getColumn(0).setPreferredWidth(40);
        tblMain.getColumnModel().getColumn(1).setPreferredWidth(200);
        tblMain.getColumnModel().getColumn(2).setPreferredWidth(250);
        tblMain.getColumnModel().getColumn(3).setPreferredWidth(320);
        tblMain.getColumnModel().getColumn(4).setPreferredWidth(120);
        tblMain.getColumnModel().getColumn(5).setPreferredWidth(0);

        btnNew.setText("Refresh");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnDelete.setForeground(new java.awt.Color(244, 18, 45));
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnSave.setForeground(new java.awt.Color(60, 115, 44));
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Ubuntu", 0, 10)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 112, 0));
        jLabel1.setText("Click to Delete / Edit");

        scOption.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scOption.setToolTipText("");

        tblOption.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblOption.getTableHeader().setResizingAllowed(false);
        tblOption.getTableHeader().setReorderingAllowed(false);
        scOption.setViewportView(tblOption);
        tblOption.getColumnModel().getColumn(0).setPreferredWidth(200);
        tblOption.getColumnModel().getColumn(1).setPreferredWidth(10);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(scOption, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(98, 98, 98)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(585, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel12))
                                .addGap(38, 38, 38)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtSAPCode, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtItemID, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                                        .addComponent(jLabel3))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtCategory, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                                        .addComponent(txtItemName))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnNew)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDelete)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSave)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnExit)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scMain, javax.swing.GroupLayout.PREFERRED_SIZE, 674, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel15)
                                .addComponent(txtItemID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8)
                                .addComponent(txtSAPCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(txtCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtItemName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNew)
                            .addComponent(btnDelete)
                            .addComponent(btnSave)
                            .addComponent(btnExit)))
                    .addComponent(scMain, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(scOption, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblMainMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMainMouseClicked
        String idno = tblMain.getValueAt(tblMain.getSelectedRow(), 5).toString();
        if (!idno.equals("") && !idno.equals(null)) {
            txtSAPCode.setText(tblMain.getValueAt(tblMain.getSelectedRow(), 1).toString());
            txtItemID.setText(tblMain.getValueAt(tblMain.getSelectedRow(), 5).toString());
            txtItemName.setText(tblMain.getValueAt(tblMain.getSelectedRow(), 3).toString());
            txtCategory.setText(tblMain.getValueAt(tblMain.getSelectedRow(), 2).toString());
            txtAmount.setText(tblMain.getValueAt(tblMain.getSelectedRow(), 4).toString());
            txtSAPCode.requestFocus();
        }

    }//GEN-LAST:event_tblMainMouseClicked

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        txtSAPCode.setText("");
        txtItemID.setText(new config.functions().GetNextID("I", "items", "id"));
        txtCategory.setText("");
        txtItemName.setText("");
        txtAmount.setText("");
        scOption.setVisible(false);
        loadListArrays();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if (txtSAPCode.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Enter SAP Code",
                    "Item Entry Warnning", JOptionPane.WARNING_MESSAGE);
            new config.functions().SelectAll(txtSAPCode);
            return;
        }
        if (txtCategory.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Enter Item Category",
                    "Item Entry Warnning", JOptionPane.WARNING_MESSAGE);
            new config.functions().SelectAll(txtCategory);
            return;
        }
        if (txtItemName.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Enter Item Name",
                    "Item Entry Warnning", JOptionPane.WARNING_MESSAGE);
            new config.functions().SelectAll(txtItemName);
            return;
        }

        try {
            if (txtItemID.getText().equals(new config.functions().GetNextID("I", "items", "id"))) {
                ResultSet rs2 = new config.RecordSet().Open("SELECT * FROM items WHERE del=0 AND LOWER(name) = '" + txtItemName.getText().replaceAll("'", "").toLowerCase() + "'");
                if (rs2.next()) {
                    JOptionPane.showMessageDialog(this, "Item name already exist",
                            "Item Entry Warnning", JOptionPane.WARNING_MESSAGE);
                    new config.functions().SelectAll(txtItemName);
                    return;
                }
                rs2 = new config.RecordSet().Open("SELECT * FROM items WHERE del=0 AND LOWER(sap_code) = '" + txtSAPCode.getText().replaceAll("'", "").toLowerCase() + "'");
                if (rs2.next()) {
                    JOptionPane.showMessageDialog(this, "Item SAP Code already exist",
                            "Item Entry Warnning", JOptionPane.WARNING_MESSAGE);
                    new config.functions().SelectAll(txtSAPCode);
                    return;
                }
            }
            ResultSet rs1 = new config.RecordSet().Execute("DELETE FROM items WHERE id='" + txtItemID.getText() + "'");
            String insStr = "INSERT INTO items VALUES ("
                    + "'" + txtItemID.getText() + "',"
                    + "'',"
                    + "'" + txtCategory.getText().replaceAll("'", "") + "',"
                    + "'" + txtSAPCode.getText().replaceAll("'", "") + "',"
                    + "'" + txtItemName.getText().replaceAll("'", "") + "',"
                    + "'" + txtAmount.getText() + "',0)";
            rs1 = new config.RecordSet().Execute(insStr);

            JOptionPane.showMessageDialog(this, "Saved",
                    "Item Message", JOptionPane.INFORMATION_MESSAGE);
            btnNewActionPerformed(null);
        } catch (Exception e) {
            System.out.println("item.java-SaveData-Error:" + e);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (txtItemID.getText().equals(new config.functions().GetNextID("I", "items", "id"))) {
            JOptionPane.showMessageDialog(this, "Nothing to Delete. Select from Item list",
                    "Item Entry Warnning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int n = JOptionPane.showConfirmDialog(
                this,
                "Are you sure to Delete this Item",
                "Item Entry Warnning",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
        if (n >= 1) {
            return;
        }
        try {
            ResultSet rs1 = new config.RecordSet().Execute("UPDATE items set del=1 WHERE id='" + txtItemID.getText() + "'");
            JOptionPane.showMessageDialog(this, "Item Deleted",
                    "Item Entry", JOptionPane.INFORMATION_MESSAGE);
            btnNewActionPerformed(null);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }//GEN-LAST:event_btnDeleteActionPerformed
    private void displayOptionMenu(String optField, int posX, int posY) {
        String cloneStr[][] = null;
        JTable actTable = null;
        if (optField.equals("category")) {
            actTable = tblOption;
            cloneStr = listCategory.clone();
        } else if (optField.equals("item")) {
//            actTable = tblOption;
//            cloneStr = listItem.clone();
        } else if (optField.equals("godown")) {
            //cloneStr = listGodown.clone();
        }

        new config.functions().ClearTable(actTable);
        DefaultTableModel mdlOption = (DefaultTableModel) actTable.getModel();
        for (String minStr[] : cloneStr) {
            if (optField.equals("item")) {
                mdlOption.insertRow(actTable.getRowCount(), new Object[]{minStr[0], minStr[1], minStr[2], minStr[3], minStr[4]});
            } else {
                mdlOption.insertRow(actTable.getRowCount(), new Object[]{minStr[0], minStr[1], minStr[2]});
            }
        }
        int maxOptionMenuLength = 10;

        JViewport parent = (JViewport) actTable.getParent();
        JScrollPane enclosing = (JScrollPane) parent.getParent();
        enclosing.setBounds(posX, posY, 300, (int) (Math.max(maxOptionMenuLength, 2) * 18f));
        enclosing.setVisible(true);
    }

    private void addEventToObject() {
        final Color thisColor = new config.Global().textFocus;

        addKeyEventToObject(txtSAPCode, tblMain);
        txtSAPCode.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent arg0) {
                txtSAPCode.setBackground(thisColor);
                new config.functions().SelectAll(txtSAPCode);
            }

            @Override
            public void focusLost(FocusEvent arg0) {
                txtSAPCode.setBackground(new Color(255, 255, 255));
                scOption.setVisible(false);
            }
        });
        txtSAPCode.getDocument().addDocumentListener(new DocumentListener() {

            public void changedUpdate(DocumentEvent e) {
                new config.functions().SelectTableRow(tblMain, 1, txtSAPCode.getText());
            }

            public void removeUpdate(DocumentEvent e) {
                new config.functions().SelectTableRow(tblMain, 1, txtSAPCode.getText());
            }

            public void insertUpdate(DocumentEvent e) {
                new config.functions().SelectTableRow(tblMain, 1, txtSAPCode.getText());
            }
        });
        txtSAPCode.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                txtCategory.requestFocus();
            }
        });

        addKeyEventToObject(txtCategory, tblOption);
        txtCategory.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent arg0) {
                txtCategory.setBackground(thisColor);
                new config.functions().SelectAll(txtCategory);
                displayOptionMenu("category", txtCategory.getX() + txtCategory.getWidth(), txtCategory.getY());
            }

            @Override
            public void focusLost(FocusEvent arg0) {
                txtCategory.setBackground(new Color(255, 255, 255));
                scOption.setVisible(false);
            }
        });
        txtCategory.getDocument().addDocumentListener(new DocumentListener() {

            public void changedUpdate(DocumentEvent e) {
                new config.functions().SelectTableRow(tblOption, 0, txtCategory.getText());
            }

            public void removeUpdate(DocumentEvent e) {
                new config.functions().SelectTableRow(tblOption, 0, txtCategory.getText());
            }

            public void insertUpdate(DocumentEvent e) {
                new config.functions().SelectTableRow(tblOption, 0, txtCategory.getText());
            }
        });
        txtCategory.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (tblOption.getSelectedRow() >= 0) {
                    txtCategory.setText(tblOption.getValueAt(tblOption.getSelectedRow(), 0).toString());
                    txtItemName.requestFocus();
                } else {
                    txtItemName.requestFocus();
                }

            }
        });

        addKeyEventToObject(txtItemName, tblMain);
        txtItemName.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent arg0) {
                txtItemName.setBackground(thisColor);
                new config.functions().SelectAll(txtItemName);
            }

            @Override
            public void focusLost(FocusEvent arg0) {
                txtItemName.setBackground(new Color(255, 255, 255));
            }
        });
        txtItemName.getDocument().addDocumentListener(new DocumentListener() {

            public void changedUpdate(DocumentEvent e) {
                new config.functions().SelectTableRow(tblMain, 3, txtItemName.getText());
            }

            public void removeUpdate(DocumentEvent e) {
                new config.functions().SelectTableRow(tblMain, 3, txtItemName.getText());
            }

            public void insertUpdate(DocumentEvent e) {
                new config.functions().SelectTableRow(tblMain, 3, txtItemName.getText());
            }
        });
        txtItemName.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                txtAmount.requestFocus();
            }
        });
        txtAmount.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                btnSave.requestFocus();
            }
        });
    }

    private void addKeyEventToObject(Object objSource, final JTable tblTarget) {
        ((Component) objSource).addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                // TODO Auto-generated method stub
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // TODO Auto-generated method stub
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_UP:
                        if (tblTarget.getSelectedRow() > 0) {
                            tblTarget.setRowSelectionInterval(tblTarget.getSelectedRow() - 1, tblTarget.getSelectedRow() - 1);
                            new config.functions().scrollToCenter(tblTarget, tblTarget.getSelectedRow() - 1, 1);
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if (tblTarget.getSelectedRow() < tblTarget.getRowCount() - 1) {
                            tblTarget.setRowSelectionInterval(tblTarget.getSelectedRow() + 1, tblTarget.getSelectedRow() + 1);
                            new config.functions().scrollToCenter(tblTarget, tblTarget.getSelectedRow() + 1, 1);
                        }
                        break;
                }

            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JScrollPane scMain;
    private javax.swing.JScrollPane scOption;
    private javax.swing.JTable tblMain;
    private javax.swing.JTable tblOption;
    private javax.swing.JTextField txtAmount;
    private javax.swing.JTextField txtCategory;
    private javax.swing.JTextField txtItemID;
    private javax.swing.JTextField txtItemName;
    public javax.swing.JTextField txtSAPCode;
    // End of variables declaration//GEN-END:variables
}
