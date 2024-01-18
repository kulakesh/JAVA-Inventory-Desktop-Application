/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import config.iReport;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.*;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class Invoice extends javax.swing.JInternalFrame {

    String listItem[][];
    String listInvoice[][];

    public Invoice() {
        initComponents();
        ini();
        addEventToObject();
        btnNewActionPerformed(null);
    }

    private void ini() {
        try {
            javax.swing.text.MaskFormatter dateMask = new javax.swing.text.MaskFormatter("##-##-####");
            dateMask.install(txtInvoiceDate);
        } catch (ParseException e1) {
        }
        jLabel15.setVisible(false);
        txtItemID.setVisible(false);
        new config.functions().numOnly(txtQnty);
        new config.functions().numOnly(txtDiscount);
        new config.functions().numOnly(txtDiscountP);
        new config.functions().numOnly(txtTax);
        new config.functions().numOnly(txtTaxP);
        new config.functions().numOnly(txtPaidAmount);


        try {
            int j = 1;
            DefaultTableModel mdlClient = (DefaultTableModel) tblClient.getModel();
            ResultSet rs1 = new config.RecordSet().Open("SELECT id,cid,name FROM client WHERE del=0 ORDER BY name");
            while (rs1.next()) {
                mdlClient.insertRow(tblOption.getRowCount(), new Object[]{j, rs1.getString("id"),rs1.getString("cid"), rs1.getString("name")});
                j++;
            }
            rs1.close();

        } catch (Exception e) {
            System.out.println("Invoice>ini :" + e);
        }

        ChangeListener changeListener = new ChangeListener() {

            public void stateChanged(ChangeEvent changeEvent) {
                JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
                int index = sourceTabbedPane.getSelectedIndex();
                if (sourceTabbedPane.getTitleAt(index).equals("Payment")) {
                    Double TotalAmount = 0.0;
                    for (int i = 0; i < tblMain.getRowCount(); i++) {
                        TotalAmount += Double.parseDouble(tblMain.getValueAt(i, 5).toString().replaceAll(",", ""));
                    }
                    //Double tTaxP = Double.parseDouble(txtTaxP.getText().replaceAll(",", ""));
                    //txtTax.setText(String.format("%1$,.2f", (TotalAmount * tTaxP) / 100));
                    txtTotalAmount.setText(String.format("%1$,.2f", TotalAmount));
                }
                if (sourceTabbedPane.getTitleAt(index).equals("Customer")) {
                    txtClientName.requestFocus();
                }
            }
        };
        jtpMain.addChangeListener(changeListener);

    }

    private void loadListArrays() {
        Integer j = 0;
        try {
            j = 0;
            ResultSet rs1 = new config.RecordSet().Open("SELECT a.id,a.sap_code,a.name,a.amount, "
                    + "IFNULL(IFNULL(SUM(b.gd),0)-IFNULL(SUM(c.quantity),0),0) as avl "
                    + "FROM items a LEFT JOIN stock_item b ON  a.id=b.item_id "
                    + "LEFT JOIN sales_item c ON  a.id=c.item_id "
                    + "GROUP BY a.id ORDER BY a.name");
            if (rs1.next()) {
                rs1.last();
                listItem = new String[rs1.getRow()][5];
                rs1.beforeFirst();
                while (rs1.next()) {
//                    int sStock=0,sSales=0;
//                    ResultSet rs2 = new config.RecordSet().Open("SELECT IFNULL(SUM(gd),0) as vlu FROM stock_item WHERE item_id='"+ rs1.getString("id") +"'");
//                    if (rs1.next()) {
//                        sStock = rs2.getInt("vlu");
//                    }
//                    rs2.close();
//                    rs2 = new config.RecordSet().Open("SELECT IFNULL(SUM(quantity),0) as vlu FROM sales_item WHERE item_id='"+ rs1.getString("id") +"'");
//                    if (rs1.next()) {
//                        sSales = rs2.getInt("vlu");
//                    }
//                    rs2.close();
                    listItem[j][0] = rs1.getString("id");
                    listItem[j][1] = rs1.getString("sap_code");
                    listItem[j][2] = rs1.getString("name");
                    listItem[j][3] = rs1.getString("avl");
                    listItem[j][4] = rs1.getString("amount");
                    j++;
                }
            }
            rs1.close();
            j = 0;
            rs1 = new config.RecordSet().Open("SELECT a.id,b.name,a.invoice_date FROM sales a, "
                    + "client b WHERE a.client_id=b.id AND a.del=0 ORDER BY a.invoice_date");
            if (rs1.next()) {
                rs1.last();
                listInvoice = new String[rs1.getRow()][3];
                rs1.beforeFirst();
                while (rs1.next()) {
                    listInvoice[j][0] = rs1.getString("id");
                    listInvoice[j][1] = rs1.getString("name");
                    listInvoice[j][2] = rs1.getString("invoice_date");
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

        scOption1 = new javax.swing.JScrollPane();
        tblOption1 = new javax.swing.JTable();
        jtpMain = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        scOption = new javax.swing.JScrollPane();
        tblOption = new javax.swing.JTable();
        txtSAPCode = new javax.swing.JTextField();
        txtItemID = new javax.swing.JTextField();
        txtQnty = new javax.swing.JTextField();
        txtItemName = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtAmount = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        scMain = new javax.swing.JScrollPane();
        tblMain = new javax.swing.JTable();
        lblAvl = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        txtClientName = new javax.swing.JTextField();
        txtClientID = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        txtCity = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtState = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClient = new javax.swing.JTable();
        txtCID = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtTotalAmount = new javax.swing.JTextField();
        txtDiscount = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        txtDiscountP = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        txtAfterDiscount = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel24 = new javax.swing.JLabel();
        txtTax = new javax.swing.JTextField();
        txtTaxP = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtPaidAmount = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtBalance = new javax.swing.JTextField();
        txtHandling = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        txtTaxable = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        cmbTaxType = new javax.swing.JComboBox();
        chkCFormRequired = new javax.swing.JCheckBox();
        chkCFormReceived = new javax.swing.JCheckBox();
        jLabel34 = new javax.swing.JLabel();
        txtTotalAfterTax = new javax.swing.JTextField();
        txtHandlingP = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        txtNetPayable = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        txtCarrier = new javax.swing.JTextField();
        txtShipmentNo = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtNetWeight = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        txtInvoiceDate = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        txtInvoiceNo = new javax.swing.JTextField();
        btnNew = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        txtRefNo = new javax.swing.JTextField();
        btnPrint = new javax.swing.JButton();
        pnlPrint = new javax.swing.JPanel();
        btnPrint1 = new javax.swing.JButton();
        btnPrint2 = new javax.swing.JButton();
        btnPrint3 = new javax.swing.JButton();
        btnPrint4 = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();

        scOption1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scOption1.setToolTipText("");

        tblOption1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblOption1.getTableHeader().setResizingAllowed(false);
        tblOption1.getTableHeader().setReorderingAllowed(false);
        scOption1.setViewportView(tblOption1);
        if (tblOption1.getColumnModel().getColumnCount() > 0) {
            tblOption1.getColumnModel().getColumn(0).setPreferredWidth(100);
            tblOption1.getColumnModel().getColumn(1).setPreferredWidth(150);
            tblOption1.getColumnModel().getColumn(2).setPreferredWidth(100);
        }

        jtpMain.setNextFocusableComponent(txtClientName);

        jLabel8.setText("SAP Code");

        scOption.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scOption.setToolTipText("");

        tblOption.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "", "", "", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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
        if (tblOption.getColumnModel().getColumnCount() > 0) {
            tblOption.getColumnModel().getColumn(0).setPreferredWidth(1);
            tblOption.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblOption.getColumnModel().getColumn(2).setPreferredWidth(200);
            tblOption.getColumnModel().getColumn(3).setPreferredWidth(50);
            tblOption.getColumnModel().getColumn(4).setPreferredWidth(80);
        }

        txtSAPCode.setNextFocusableComponent(txtItemName);
        txtSAPCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSAPCodeActionPerformed(evt);
            }
        });

        txtItemID.setEnabled(false);

        txtItemName.setNextFocusableComponent(txtQnty);

        jLabel12.setText("ALP");

        txtAmount.setNextFocusableComponent(txtAmount);

        jLabel10.setText("Item Name");

        jLabel15.setText("System Code");

        jLabel11.setText("Quantity");

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(152, 22, 22));
        jLabel3.setText("Item Details");

        tblMain.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SL", "SAP", "Name", "Rate", "Qnt", "Value", "ItemID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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
        if (tblMain.getColumnModel().getColumnCount() > 0) {
            tblMain.getColumnModel().getColumn(0).setPreferredWidth(40);
            tblMain.getColumnModel().getColumn(1).setPreferredWidth(90);
            tblMain.getColumnModel().getColumn(2).setPreferredWidth(200);
            tblMain.getColumnModel().getColumn(3).setPreferredWidth(80);
            tblMain.getColumnModel().getColumn(4).setPreferredWidth(60);
            tblMain.getColumnModel().getColumn(5).setPreferredWidth(80);
            tblMain.getColumnModel().getColumn(6).setMinWidth(0);
            tblMain.getColumnModel().getColumn(6).setPreferredWidth(0);
            tblMain.getColumnModel().getColumn(6).setMaxWidth(0);
        }

        lblAvl.setForeground(new java.awt.Color(159, 18, 18));
        lblAvl.setText(" ");

        btnAdd.setForeground(new java.awt.Color(60, 115, 44));
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(scOption, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(214, 214, 214))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 737, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(125, 125, 125)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(txtQnty, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblAvl))
                                            .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(txtSAPCode, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel15)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtItemID))
                                            .addComponent(txtItemName, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scMain, javax.swing.GroupLayout.DEFAULT_SIZE, 775, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel15)
                                .addComponent(txtItemID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8)
                                .addComponent(txtSAPCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtItemName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtQnty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAvl))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAdd)
                        .addGap(0, 119, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(scMain, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addComponent(scOption, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jtpMain.addTab("Item", jPanel2);

        jLabel5.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(152, 22, 22));
        jLabel5.setText("Customer Details");

        jLabel7.setText("ID");

        txtClientID.setEnabled(false);

        jLabel9.setText("Name");

        jLabel1.setText("Address");

        jLabel13.setText("City");

        jLabel14.setText("State");

        tblClient.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SL", "", "ID", "Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblClient);
        if (tblClient.getColumnModel().getColumnCount() > 0) {
            tblClient.getColumnModel().getColumn(0).setPreferredWidth(40);
            tblClient.getColumnModel().getColumn(1).setResizable(false);
            tblClient.getColumnModel().getColumn(1).setPreferredWidth(0);
            tblClient.getColumnModel().getColumn(2).setPreferredWidth(90);
            tblClient.getColumnModel().getColumn(3).setPreferredWidth(200);
        }

        txtCID.setEditable(false);
        txtCID.setNextFocusableComponent(txtClientName);
        txtCID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCIDKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel7)
                            .addComponent(jLabel1)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14))
                        .addGap(81, 81, 81)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtClientName, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                            .addComponent(txtAddress)
                            .addComponent(txtState, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                            .addComponent(txtCity)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(txtCID, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtClientID, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 737, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(294, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtClientID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtClientName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtState, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(90, Short.MAX_VALUE))
        );

        jtpMain.addTab("Customer", jPanel1);

        jLabel16.setText("Total Amount");

        jLabel17.setText("Discount");

        jLabel18.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(152, 22, 22));
        jLabel18.setText("Payment Details");

        txtDiscountP.setText("0");

        jLabel27.setText("%");

        jLabel33.setText("Total After Discount");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 737, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17)
                            .addComponent(jLabel33))
                        .addGap(80, 80, 80)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtTotalAmount, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDiscount, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                            .addComponent(txtAfterDiscount))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDiscountP, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel27)))
                .addContainerGap(302, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(txtDiscountP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAfterDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33))
                .addContainerGap(193, Short.MAX_VALUE))
        );

        jtpMain.addTab("Payment", jPanel4);

        jLabel30.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(152, 22, 22));
        jLabel30.setText("Tax Details");

        jLabel24.setText("Tax");

        txtTaxP.setText("14.5");

        jLabel28.setText("%");

        jLabel19.setText("Paid Amount");

        txtPaidAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPaidAmountActionPerformed(evt);
            }
        });

        jLabel20.setText("Balance");

        txtHandling.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHandlingActionPerformed(evt);
            }
        });

        jLabel29.setText("Handling");

        jLabel31.setText("Taxable Amount");

        jLabel32.setText("Tax Type");

        cmbTaxType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "VAT", "CST" }));

        chkCFormRequired.setText("C-Form Required");

        chkCFormReceived.setText("C-Form Received");

        jLabel34.setText("Total After Tax");

        txtTotalAfterTax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalAfterTaxActionPerformed(evt);
            }
        });

        txtHandlingP.setText("0.65");

        jLabel35.setText("%");

        jLabel36.setText("Net Payable");

        txtNetPayable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNetPayableActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 737, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel24)
                                    .addComponent(jLabel31)
                                    .addComponent(jLabel32)
                                    .addComponent(jLabel34)
                                    .addComponent(jLabel36))
                                .addGap(80, 80, 80))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addGap(132, 132, 132)))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtHandling, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                            .addComponent(txtNetPayable, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPaidAmount)
                            .addComponent(txtTax)
                            .addComponent(txtBalance)
                            .addComponent(txtTaxable)
                            .addComponent(cmbTaxType, 0, 109, Short.MAX_VALUE)
                            .addComponent(txtTotalAfterTax))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(txtTaxP, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23)
                                .addComponent(jLabel28))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(chkCFormRequired)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chkCFormReceived))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(txtHandlingP, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel35)))))
                .addContainerGap(347, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel30)
                    .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTaxable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(cmbTaxType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkCFormRequired)
                    .addComponent(chkCFormReceived))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(txtTaxP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(txtTotalAfterTax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtHandlingP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel35)
                        .addComponent(txtHandling, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNetPayable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPaidAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBalance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addContainerGap())
        );

        jtpMain.addTab("Tax", jPanel5);

        jLabel21.setText("Name Of Carrier");

        jLabel22.setText("Shipment No");

        jLabel23.setText("Net Weight");

        jLabel25.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(152, 22, 22));
        jLabel25.setText("Payment Details");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 737, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addComponent(jLabel21)
                            .addComponent(jLabel22))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNetWeight, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCarrier, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                            .addComponent(txtShipmentNo))))
                .addContainerGap(302, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel25)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtCarrier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txtShipmentNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtNetWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(193, Short.MAX_VALUE))
        );

        jtpMain.addTab("Shipping", jPanel3);

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(152, 22, 22));
        jLabel2.setText("Invoice");

        jLabel4.setText("Invoice No");

        jLabel6.setText("Invoice Date");

        txtInvoiceNo.setEnabled(false);
        txtInvoiceNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtInvoiceNoKeyPressed(evt);
            }
        });

        btnNew.setText("Refresh");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnDelete.setForeground(new java.awt.Color(244, 18, 45));
        btnDelete.setText("Delete");

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

        jLabel26.setText("Ref. No");

        txtRefNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRefNoKeyPressed(evt);
            }
        });

        btnPrint.setForeground(new java.awt.Color(243, 92, 6));
        btnPrint.setText("Print");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        btnPrint1.setForeground(new java.awt.Color(243, 92, 6));
        btnPrint1.setText("Delivery Challan");
        btnPrint1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrint1ActionPerformed(evt);
            }
        });

        btnPrint2.setForeground(new java.awt.Color(243, 92, 6));
        btnPrint2.setText("Transporter Copy");
        btnPrint2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrint2ActionPerformed(evt);
            }
        });

        btnPrint3.setForeground(new java.awt.Color(243, 92, 6));
        btnPrint3.setText("Extra Copy");
        btnPrint3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrint3ActionPerformed(evt);
            }
        });

        btnPrint4.setForeground(new java.awt.Color(243, 92, 6));
        btnPrint4.setText("Invoice");
        btnPrint4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrint4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlPrintLayout = new javax.swing.GroupLayout(pnlPrint);
        pnlPrint.setLayout(pnlPrintLayout);
        pnlPrintLayout.setHorizontalGroup(
            pnlPrintLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrintLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPrintLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPrintLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnPrint1)
                        .addGap(23, 23, 23))
                    .addGroup(pnlPrintLayout.createSequentialGroup()
                        .addComponent(btnPrint2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlPrintLayout.createSequentialGroup()
                        .addGroup(pnlPrintLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnPrint3)
                            .addComponent(btnPrint4))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        pnlPrintLayout.setVerticalGroup(
            pnlPrintLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPrintLayout.createSequentialGroup()
                .addComponent(btnPrint4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnPrint1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPrint2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPrint3)
                .addContainerGap())
        );

        jLabel37.setForeground(new java.awt.Color(255, 153, 0));
        jLabel37.setText("F3 to popup list");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtpMain)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnNew)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnDelete)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnSave)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnPrint)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnExit)
                                        .addGap(158, 158, 158))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(pnlPrint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(101, 101, 101)))
                                .addComponent(scOption1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtInvoiceNo, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel26))
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(137, 137, 137)
                                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 737, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtRefNo, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtInvoiceDate, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtRefNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel26))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtInvoiceNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtInvoiceDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jtpMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(scOption1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNew)
                            .addComponent(btnDelete)
                            .addComponent(btnSave)
                            .addComponent(btnExit)
                            .addComponent(btnPrint))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlPrint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtInvoiceNoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtInvoiceNoKeyPressed
    }//GEN-LAST:event_txtInvoiceNoKeyPressed

    private void txtSAPCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSAPCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSAPCodeActionPerformed

    private void tblMainMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMainMouseClicked
        String idno = tblMain.getValueAt(tblMain.getSelectedRow(), 6).toString();

        txtSAPCode.setText(tblMain.getValueAt(tblMain.getSelectedRow(), 1).toString());
        txtItemID.setText(tblMain.getValueAt(tblMain.getSelectedRow(), 6).toString());
        txtItemName.setText(tblMain.getValueAt(tblMain.getSelectedRow(), 2).toString());
        txtQnty.setText(tblMain.getValueAt(tblMain.getSelectedRow(), 4).toString());
        Double tAmt = Double.parseDouble(tblMain.getValueAt(tblMain.getSelectedRow(), 3).toString()) / Double.parseDouble(tblMain.getValueAt(tblMain.getSelectedRow(), 4).toString());
        txtAmount.setText(String.format("%1$,.2f", tAmt));
        btnAdd.setText("Edit");
    }//GEN-LAST:event_tblMainMouseClicked

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        txtAddress.setText("");
        txtAmount.setText("");
        txtBalance.setText("");
        txtCarrier.setText("");
        txtCity.setText("");
        txtClientID.setText("");
        txtCID.setText("");
        txtClientID.setVisible(false);
        txtClientName.setText("");
        txtDiscount.setText("");
        txtInvoiceDate.setText(new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime()));
        txtInvoiceNo.setText(new config.functions().GetNextID("I", "sales", "id"));
        txtItemID.setText("");
        txtItemName.setText("");
        txtNetWeight.setText("");
        txtPaidAmount.setText("");
        txtQnty.setText("");
        txtSAPCode.setText("");
        txtShipmentNo.setText("");
        txtState.setText("");
        txtTotalAmount.setText("");
        txtDiscount.setText("0");
        txtDiscountP.setText("0");
        txtAfterDiscount.setText("0");
        txtTaxable.setText("0");
        cmbTaxType.setSelectedItem("VAT");
        lblAvl.setText("");
        chkCFormRequired.setVisible(false);
        chkCFormRequired.setSelected(false);
        chkCFormReceived.setVisible(false);
        chkCFormReceived.setSelected(false);
        txtTax.setText("0");
        txtTaxP.setText("0");
        txtTotalAfterTax.setText("0");
        txtHandling.setText("0");
        txtNetPayable.setText("0");
        txtPaidAmount.setText("0");
        txtBalance.setText("0");
        scOption.setVisible(false);
        scOption1.setVisible(false);
        jtpMain.setSelectedIndex(0);
        new config.functions().ClearTable(tblMain);
        txtRefNo.requestFocus();
        pnlPrint.setVisible(false);
        loadListArrays();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if (tblMain.getRowCount() <= 0) {
            JOptionPane.showMessageDialog(this, "No Item found",
                    "Invoice Warnning", JOptionPane.WARNING_MESSAGE);
            new config.functions().SelectAll(txtSAPCode);
            return;
        }
        if (txtClientID.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Please select a Customer first",
                    "Invoice Warnning", JOptionPane.WARNING_MESSAGE);
            jtpMain.setSelectedIndex(1);
            new config.functions().SelectAll(txtClientName);
            return;
        }

        try {
            ResultSet rs1 = new config.RecordSet().Execute("DELETE FROM sales WHERE id='" + txtInvoiceNo.getText() + "'");
            String insStr = "INSERT INTO sales VALUES ("
                    + "'" + txtInvoiceNo.getText() + "',"
                    + "'" + txtRefNo.getText() + "',"
                    + "'" + txtClientID.getText() + "',"
                    + "'" + new config.functions().cSqlDate(new config.functions().cDateCalender(txtInvoiceDate.getText())) + "',"
                    + "'NOW()',"
                    + "'" + txtTotalAmount.getText().replace(",", "") + "',"
                    + "'" + txtTax.getText().replace(",", "") + "',"
                    + "'" + txtTaxP.getText().replace(",", "") + "',"
                    + "'" + txtDiscount.getText().replace(",", "") + "',"
                    + "'" + txtDiscountP.getText().replace(",", "") + "',"
                    + "'" + txtHandling.getText().replace(",", "") + "',"
                    + "'" + txtHandlingP.getText().replace(",", "") + "',"
                    + "'" + cmbTaxType.getSelectedItem().toString().replace(",", "") + "',"
                    + "'" + (chkCFormRequired.isSelected() ? "1" : "0") + "',"
                    + "'" + (chkCFormReceived.isSelected() ? "1" : "0") + "',"
                    + "'" + txtPaidAmount.getText().replace(",", "") + "',"
                    + "'" + txtCarrier.getText() + "',"
                    + "'" + txtShipmentNo.getText() + "',"
                    + "'" + txtNetWeight.getText() + "',"
                    + "0)";
            rs1 = new config.RecordSet().Execute(insStr);
            rs1 = new config.RecordSet().Execute("DELETE FROM sales_item WHERE sales_id='" + txtInvoiceNo.getText() + "'");
            for (int i = 0; i < tblMain.getRowCount(); i++) {
                insStr = "INSERT INTO sales_item VALUES ("
                        + "'" + txtInvoiceNo.getText() + "',"
                        + "'" + tblMain.getValueAt(i, 6) + "',"
                        + "'" + tblMain.getValueAt(i, 3) + "',"
                        + "'" + tblMain.getValueAt(i, 4) + "',0,0)";
                rs1 = new config.RecordSet().Execute(insStr);
            }
            JOptionPane.showMessageDialog(this, "Saved",
                    "InvoiceMessage", JOptionPane.INFORMATION_MESSAGE);
            btnNewActionPerformed(null);
        } catch (Exception e) {
            System.out.println("Invoice.java-SaveData-Error:" + e);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        if (tblMain.getRowCount() > 0) {
            Object[] options = {"Yes", "No"};
            int n = JOptionPane.showOptionDialog(
                    this,
                    "You have " + tblMain.getRowCount() + " UnSaved Invoice, Do you want to Exit anyway ?",
                    "Warnning",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE, null, options, options[1]);
            if (n == 0) {
                dispose();
            }
        } else {
            dispose();
        }
    }//GEN-LAST:event_btnExitActionPerformed

    private void txtRefNoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRefNoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRefNoKeyPressed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (!txtItemID.getText().equals("")) {
            //TODO
            int rowPos = tblMain.getRowCount();
            for (int i = 0; i < tblMain.getRowCount(); i++) {
                if (txtItemID.getText().equals(tblMain.getValueAt(i, 6))) {
                    if (btnAdd.getText().equals("Add")) {
                        JOptionPane.showMessageDialog(null, "Item alrady exist",
                                "Item Entry Warnning", JOptionPane.WARNING_MESSAGE);
                        new config.functions().SelectAll(txtItemName);
                        return;
                    } else {
                        ((DefaultTableModel) tblMain.getModel()).removeRow(i);
                        rowPos = i;
                        break;
                    }
                }
            }
            String tRate = String.format("%1$,.2f", Double.parseDouble(txtAmount.getText()));
            String tQnty = String.format("%1$,.2f", Double.parseDouble(txtQnty.getText()));
            Double amt = Double.parseDouble(txtAmount.getText()) * Double.parseDouble(txtQnty.getText());
            String tAmount = String.format("%1$,.2f", amt);

            DefaultTableModel mdlMain = (DefaultTableModel) tblMain.getModel();
            mdlMain.insertRow(rowPos, new Object[]{rowPos + 1, txtSAPCode.getText(), txtItemName.getText(), txtAmount.getText(), txtQnty.getText(), tAmount, txtItemID.getText()});
        }
        txtSAPCode.setText("");
        txtItemName.setText("");
        txtQnty.setText("");
        txtAmount.setText("");
        btnAdd.setText("Add");
        txtSAPCode.requestFocus();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        if(pnlPrint.isVisible()){
            pnlPrint.setVisible(false);
        }else{
            pnlPrint.setVisible(true);
        }

    }//GEN-LAST:event_btnPrintActionPerformed

    private void txtPaidAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPaidAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPaidAmountActionPerformed

    private void txtHandlingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHandlingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHandlingActionPerformed

    private void txtTotalAfterTaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalAfterTaxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalAfterTaxActionPerformed

    private void txtNetPayableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNetPayableActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNetPayableActionPerformed

    private void txtCIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCIDKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCIDKeyPressed

    private void btnPrint1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrint1ActionPerformed
        String PaidWord = " ", CForm = " ";
        try {
            ResultSet rs2 = new config.RecordSet().Open("SELECT ROUND(amount+tax+handling-discount,2) as tamt,tax_type, is_c_req, is_c_rec FROM sales WHERE id='" + txtInvoiceNo.getText() + "'");
            if (rs2.next()) {
                PaidWord = new config.NumToWords().convertAll(rs2.getString("tamt")).toUpperCase();
                if (rs2.getString("tax_type").equals("CST")){
                    if (rs2.getString("is_c_req").equals("1")){
                        if (rs2.getString("is_c_rec").equals("0")){
                          CForm = "C Form Due";
                        }else{
                          CForm = "C Form Received";
                        }
                    }
                }
            }
            rs2.close();
        } catch (SQLException ex) {
            Logger.getLogger(Invoice.class.getName()).log(Level.SEVERE, null, ex);
        }

        iReport rpt1 = new config.iReport();
        String saprt = (config.Global.OS.equals("Linux") ? "/" : "\\/");
        rpt1.setPath("invoice.jasper");
        rpt1.setParam("InvoiceID=" + txtInvoiceNo.getText());
        rpt1.setParam("PaidWords=" + PaidWord);
        rpt1.setParam("BG_PATH=" + config.Global.AppPath + saprt + "media" + saprt);
        rpt1.setParam("TITLE=Delivery Challan");
        rpt1.setParam("CForm=" + CForm);
        rpt1.ShowReport();
    }//GEN-LAST:event_btnPrint1ActionPerformed

    private void btnPrint2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrint2ActionPerformed
        String PaidWord = " ", CForm = " ";
        try {
            ResultSet rs2 = new config.RecordSet().Open("SELECT ROUND(amount+tax+handling-discount,2) as tamt,tax_type, is_c_req, is_c_rec FROM sales WHERE id='" + txtInvoiceNo.getText() + "'");
            if (rs2.next()) {
                PaidWord = new config.NumToWords().convertAll(rs2.getString("tamt")).toUpperCase();
                if (rs2.getString("tax_type").equals("CST")){
                    if (rs2.getString("is_c_req").equals("1")){
                        if (rs2.getString("is_c_rec").equals("0")){
                          CForm = "C Form Due";
                        }else{
                          CForm = "C Form Received";
                        }
                    }
                }
            }
            rs2.close();
        } catch (SQLException ex) {
            Logger.getLogger(Invoice.class.getName()).log(Level.SEVERE, null, ex);
        }

        iReport rpt1 = new config.iReport();
        String saprt = (config.Global.OS.equals("Linux") ? "/" : "\\/");
        rpt1.setPath("invoice.jasper");
        rpt1.setParam("InvoiceID=" + txtInvoiceNo.getText());
        rpt1.setParam("PaidWords=" + PaidWord);
        rpt1.setParam("BG_PATH=" + config.Global.AppPath + saprt + "media" + saprt);
        rpt1.setParam("TITLE=Transporter Copy");
        rpt1.setParam("CForm=" + CForm);
        rpt1.ShowReport();
    }//GEN-LAST:event_btnPrint2ActionPerformed

    private void btnPrint3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrint3ActionPerformed
        String PaidWord = " ", CForm = " ";
        try {
            ResultSet rs2 = new config.RecordSet().Open("SELECT ROUND(amount+tax+handling-discount,2) as tamt,tax_type, is_c_req, is_c_rec FROM sales WHERE id='" + txtInvoiceNo.getText() + "'");
            if (rs2.next()) {
                PaidWord = new config.NumToWords().convertAll(rs2.getString("tamt")).toUpperCase();
                if (rs2.getString("tax_type").equals("CST")){
                    if (rs2.getString("is_c_req").equals("1")){
                        if (rs2.getString("is_c_rec").equals("0")){
                          CForm = "C Form Due";
                        }else{
                          CForm = "C Form Received";
                        }
                    }
                }
            }
            rs2.close();
        } catch (SQLException ex) {
            Logger.getLogger(Invoice.class.getName()).log(Level.SEVERE, null, ex);
        }

        iReport rpt1 = new config.iReport();
        String saprt = (config.Global.OS.equals("Linux") ? "/" : "\\/");
        rpt1.setPath("invoice.jasper");
        rpt1.setParam("InvoiceID=" + txtInvoiceNo.getText());
        rpt1.setParam("PaidWords=" + PaidWord);
        rpt1.setParam("BG_PATH=" + config.Global.AppPath + saprt + "media" + saprt);
        rpt1.setParam("TITLE=Extra Copy");
        rpt1.setParam("CForm=" + CForm);
        rpt1.ShowReport();
    }//GEN-LAST:event_btnPrint3ActionPerformed

    private void btnPrint4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrint4ActionPerformed
        String PaidWord = " ", CForm = " ";
        try {
            ResultSet rs2 = new config.RecordSet().Open("SELECT ROUND(amount+tax+handling-discount,2) as tamt,tax_type, is_c_req, is_c_rec FROM sales WHERE id='" + txtInvoiceNo.getText() + "'");
            if (rs2.next()) {
                System.out.println(">>>>>"+rs2.getString("tamt"));
                PaidWord = new config.NumToWords().convertAll(rs2.getString("tamt")).toUpperCase();
                if (rs2.getString("tax_type").equals("CST")){
                    if (rs2.getString("is_c_req").equals("1")){
                        if (rs2.getString("is_c_rec").equals("0")){
                          CForm = "C Form Due";
                        }else{
                          CForm = "C Form Received";
                        }
                    }
                }
            }
            rs2.close();
        } catch (SQLException ex) {
            Logger.getLogger(Invoice.class.getName()).log(Level.SEVERE, null, ex);
        }

        iReport rpt1 = new config.iReport();
        String saprt = File.separator;
        rpt1.setPath("invoice.jasper");
        rpt1.setParam("InvoiceID=" + txtInvoiceNo.getText());
        rpt1.setParam("PaidWords=" + PaidWord);
        rpt1.setParam("BG_PATH=" + config.Global.AppPath + saprt + "media" + saprt);
        rpt1.setParam("TITLE=Tax Invoice");
        rpt1.setParam("CForm=" + CForm);
        rpt1.ShowReport();
    }//GEN-LAST:event_btnPrint4ActionPerformed
    private void displayOptionMenu(String optField, int posX, int posY) {
        String cloneStr[][] = null;
        JTable actTable = null;
        if (optField.equals("invoice")) {
            actTable = tblOption1;
            cloneStr = listInvoice.clone();
        } else if (optField.equals("item")) {
            actTable = tblOption;
            cloneStr = listItem.clone();
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
        int maxOptionMenuLength = 9;
        if (cloneStr.length < maxOptionMenuLength) {
            maxOptionMenuLength = cloneStr.length;
            actTable.getColumnModel().getColumn(1).setPreferredWidth(100);
            actTable.getColumnModel().getColumn(2).setPreferredWidth(197);
        } else {
            actTable.getColumnModel().getColumn(1).setPreferredWidth(92);
            actTable.getColumnModel().getColumn(2).setPreferredWidth(190);
        }
        JViewport parent = (JViewport) actTable.getParent();
        JScrollPane enclosing = (JScrollPane) parent.getParent();
        enclosing.setBounds(posX, posY, 300, (int) (Math.max(maxOptionMenuLength, 2) * 18f));
        enclosing.setVisible(true);
    }

    private void addEventToObject() {
        final Color thisColor = new config.Global().textFocus;

        addKeyEventToObject(txtSAPCode, tblOption);
        txtSAPCode.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent arg0) {
                txtSAPCode.setBackground(thisColor);
                new config.functions().SelectAll(txtSAPCode);
                displayOptionMenu("item", txtSAPCode.getX() + txtSAPCode.getWidth(), txtSAPCode.getY());
            }

            @Override
            public void focusLost(FocusEvent arg0) {
                txtSAPCode.setBackground(new Color(255, 255, 255));
                scOption.setVisible(false);
            }
        });
        txtSAPCode.getDocument().addDocumentListener(new DocumentListener() {

            public void changedUpdate(DocumentEvent e) {
                new config.functions().SelectTableRow(tblOption, 1, txtSAPCode.getText());
            }

            public void removeUpdate(DocumentEvent e) {
                new config.functions().SelectTableRow(tblOption, 1, txtSAPCode.getText());
            }

            public void insertUpdate(DocumentEvent e) {
                new config.functions().SelectTableRow(tblOption, 1, txtSAPCode.getText());
            }
        });
        txtSAPCode.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (tblOption.getSelectedRow() >= 0) {
                    txtItemID.setText(tblOption.getValueAt(tblOption.getSelectedRow(), 0).toString());
                    txtSAPCode.setText(tblOption.getValueAt(tblOption.getSelectedRow(), 1).toString());
                    txtItemName.setText(tblOption.getValueAt(tblOption.getSelectedRow(), 2).toString());
                    lblAvl.setText(tblOption.getValueAt(tblOption.getSelectedRow(), 3).toString());
                    txtAmount.setText(tblOption.getValueAt(tblOption.getSelectedRow(), 4).toString());

                    txtQnty.requestFocus();
                } else {
                    txtItemName.requestFocus();
                }

            }
        });
        txtSAPCode.addKeyListener(new KeyListener() {

            public void keyTyped(KeyEvent e) {
            }

            public void keyReleased(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_ESCAPE:
                        scOption.setVisible(false);
                        jtpMain.setSelectedIndex(1);
                        txtClientName.requestFocus();
                        break;
                }

            }
        });
        addKeyEventToObject(txtItemName, tblOption);
        txtItemName.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent arg0) {
                txtItemName.setBackground(thisColor);
                new config.functions().SelectAll(txtItemName);
                displayOptionMenu("item", txtItemName.getX() + txtItemName.getWidth(), txtItemName.getY());
            }

            @Override
            public void focusLost(FocusEvent arg0) {
                txtItemName.setBackground(new Color(255, 255, 255));
                scOption.setVisible(false);
            }
        });
        txtItemName.getDocument().addDocumentListener(new DocumentListener() {

            public void changedUpdate(DocumentEvent e) {
                new config.functions().SelectTableRow(tblOption, 2, txtItemName.getText());
            }

            public void removeUpdate(DocumentEvent e) {
                new config.functions().SelectTableRow(tblOption, 2, txtItemName.getText());
            }

            public void insertUpdate(DocumentEvent e) {
                new config.functions().SelectTableRow(tblOption, 2, txtItemName.getText());
            }
        });
        txtItemName.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (tblOption.getSelectedRow() >= 0) {
                    txtItemID.setText(tblOption.getValueAt(tblOption.getSelectedRow(), 0).toString());
                    txtSAPCode.setText(tblOption.getValueAt(tblOption.getSelectedRow(), 1).toString());
                    txtItemName.setText(tblOption.getValueAt(tblOption.getSelectedRow(), 2).toString());
                    lblAvl.setText(tblOption.getValueAt(tblOption.getSelectedRow(), 3).toString());
                    txtAmount.setText(tblOption.getValueAt(tblOption.getSelectedRow(), 4).toString());

                }
                txtQnty.requestFocus();
            }
        });
        txtItemName.addKeyListener(new KeyListener() {

            public void keyTyped(KeyEvent e) {
            }

            public void keyReleased(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_ESCAPE:
                        scOption.setVisible(false);
                        jtpMain.setSelectedIndex(1);
                        txtClientName.requestFocus();
                        break;
                }

            }
        });

        addKeyEventToObject(txtClientName, tblClient);
        txtClientName.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent arg0) {
                txtClientName.setBackground(thisColor);
                new config.functions().SelectAll(txtItemName);
            }

            @Override
            public void focusLost(FocusEvent arg0) {
                txtItemName.setBackground(new Color(255, 255, 255));
            }
        });
        txtClientName.getDocument().addDocumentListener(new DocumentListener() {

            public void changedUpdate(DocumentEvent e) {
                new config.functions().SelectTableRow(tblClient, 2, txtClientName.getText());
            }

            public void removeUpdate(DocumentEvent e) {
                new config.functions().SelectTableRow(tblClient, 2, txtClientName.getText());
            }

            public void insertUpdate(DocumentEvent e) {
                new config.functions().SelectTableRow(tblClient, 2, txtClientName.getText());
            }
        });
        txtClientName.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (tblClient.getSelectedRow() >= 0) {
                    try {
                        ResultSet rs1 = new config.RecordSet().Open("SELECT id,cid,name,address,city,state FROM client WHERE id='" + tblClient.getValueAt(tblClient.getSelectedRow(), 1).toString() + "'");
                        if (rs1.next()) {
                            txtCID.setText(rs1.getString("cid"));
                            txtClientID.setText(rs1.getString("id"));
                            txtClientName.setText(rs1.getString("name"));
                            txtAddress.setText(rs1.getString("address"));
                            txtCity.setText(rs1.getString("city"));
                            txtState.setText(rs1.getString("state"));
                        }
                        rs1.close();

                    } catch (Exception ee) {
                        System.out.println("Invoice>clint key enter :" + ee);
                    }
                }
            }
        });
        txtClientName.addKeyListener(new KeyListener() {

            public void keyTyped(KeyEvent e) {
            }

            public void keyReleased(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_ESCAPE:
                        jtpMain.setSelectedIndex(2);
                        txtTotalAmount.requestFocus();
                        break;
                }

            }
        });

        //kkkk
        addKeyEventToObject(txtRefNo, tblOption1);
        txtRefNo.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent arg0) {
                txtRefNo.setBackground(thisColor);
            }

            @Override
            public void focusLost(FocusEvent arg0) {
                txtRefNo.setBackground(new Color(255, 255, 255));
                scOption1.setVisible(false);
            }
        });
        txtRefNo.getDocument().addDocumentListener(new DocumentListener() {

            public void changedUpdate(DocumentEvent e) {
                new config.functions().SelectTableRow(tblOption1, 1, txtRefNo.getText());
            }

            public void removeUpdate(DocumentEvent e) {
                new config.functions().SelectTableRow(tblOption1, 1, txtRefNo.getText());
            }

            public void insertUpdate(DocumentEvent e) {
                new config.functions().SelectTableRow(tblOption1, 1, txtRefNo.getText());
            }
        });
        txtRefNo.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if ((tblOption1.getSelectedRow() >= 0) && scOption1.isVisible() == true) {
                    String id = tblOption1.getValueAt(tblOption1.getSelectedRow(), 0).toString();
                    try {
                        ResultSet rs1 = new config.RecordSet().Open("SELECT * FROM sales WHERE id='" + id + "'");
                        if (rs1.next()) {
                            txtInvoiceNo.setText(rs1.getString("id"));
                            txtRefNo.setText(rs1.getString("ref_no"));
                            txtInvoiceDate.setText(new config.functions().cSqlToDate(rs1.getString("invoice_date")));
                            txtTotalAmount.setText(rs1.getString("amount"));
                            txtDiscount.setText(rs1.getString("discount"));
                            txtDiscountP.setText(rs1.getString("discount_p"));
                            txtAfterDiscount.setText(String.format("%1$,.2f", Double.parseDouble(txtTotalAmount.getText().replaceAll(",", ""))-Double.parseDouble(txtDiscount.getText().replaceAll(",", ""))));
                            txtTaxable.setText(txtAfterDiscount.getText());
                            cmbTaxType.setSelectedItem(rs1.getString("tax_type"));
                            chkCFormRequired.setVisible(cmbTaxType.getSelectedItem().equals("CST") ? true : false);
                            chkCFormRequired.setSelected(rs1.getString("is_c_req").equals("1") ? true : false);
                            chkCFormReceived.setVisible(rs1.getString("is_c_req").equals("1") ? true : false);
                            chkCFormReceived.setSelected(rs1.getString("is_c_rec").equals("1") ? true : false);
                            txtTax.setText(rs1.getString("tax"));
                            txtTaxP.setText(rs1.getString("tax_p"));
                            txtTotalAfterTax.setText(String.format("%1$,.2f", Double.parseDouble(txtTaxable.getText().replaceAll(",", ""))+Double.parseDouble(txtTax.getText().replaceAll(",", ""))));
                            txtHandling.setText(rs1.getString("handling"));
                            txtHandlingP.setText(rs1.getString("handling_p"));
                            txtNetPayable.setText(String.format("%1$,.2f", Double.parseDouble(txtTotalAfterTax.getText().replaceAll(",", ""))+Double.parseDouble(txtHandling.getText().replaceAll(",", ""))));
                            txtPaidAmount.setText(rs1.getString("paid"));
                            txtBalance.setText(String.format("%1$,.2f", Double.parseDouble(txtNetPayable.getText().replaceAll(",", ""))-Double.parseDouble(txtPaidAmount.getText().replaceAll(",", ""))));
                            txtCarrier.setText(rs1.getString("carrier"));
                            txtShipmentNo.setText(rs1.getString("shipment_no"));
                            txtNetWeight.setText(rs1.getString("weight"));
                            txtInvoiceDate.requestFocus();
                            ResultSet rs2 = new config.RecordSet().Open("SELECT * FROM client WHERE id='" + rs1.getString("client_id") + "'");
                            if (rs2.next()) {
                                txtClientID.setText(rs2.getString("id"));
                                txtClientName.setText(rs2.getString("name"));
                                txtAddress.setText(rs2.getString("address"));
                                txtCity.setText(rs2.getString("city"));
                                txtState.setText(rs2.getString("state"));
                            }
                            rs2.close();
                            new config.functions().ClearTable(tblMain);
                            rs2 = new config.RecordSet().Open("SELECT a.*,b.sap_code,b.name FROM sales_item a,items b WHERE a.item_id=b.id AND a.sales_id='" + id + "'");
                            if (rs2.next()) {
                                rs2.beforeFirst();
                                while (rs2.next()) {
                                    DefaultTableModel mdlMain = (DefaultTableModel) tblMain.getModel();
                                    mdlMain.insertRow(tblMain.getRowCount(), new Object[]{tblMain.getRowCount() + 1,
                                                rs2.getString("sap_code"),
                                                rs2.getString("name"),
                                                rs2.getString("rate"),
                                                rs2.getString("quantity"),
                                                rs2.getDouble("rate") * rs2.getDouble("quantity"),
                                                rs2.getString("item_id")});
                                }
                            }
                            rs2.close();
                        }
                        rs1.close();
                    } catch (Exception ev) {
                        System.out.println("Receive.java-SaveData-Error:" + ev);
                    }
                } else {
                    txtInvoiceDate.requestFocus();
                }

            }
        });
        txtRefNo.addKeyListener(new KeyListener() {

            public void keyTyped(KeyEvent e) {
            }

            public void keyReleased(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_ESCAPE:
                        scOption1.setVisible(false);
                        break;
                    case KeyEvent.VK_F3:
                        displayOptionMenu("invoice", txtRefNo.getX() + txtRefNo.getWidth(), txtRefNo.getY() + txtRefNo.getHeight());
                        break;
                }

            }
        });
        addFocusEvent(txtQnty);
        txtQnty.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                txtAmount.requestFocus();
            }
        });
        addFocusEvent(txtAmount);
        txtAmount.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                btnAddActionPerformed(e);
            }
        });
        // ENTER Event
        addFocusEvent(txtRefNo);
        txtRefNo.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                txtInvoiceDate.requestFocus();
            }
        });
        txtInvoiceDate.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                txtSAPCode.requestFocus();
            }
        });
        txtTotalAmount.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                txtDiscount.requestFocus();
            }
        });
        txtDiscount.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Double tAmt = Double.parseDouble(txtTotalAmount.getText().replaceAll(",", ""));
                Double tDiscount = Double.parseDouble(txtDiscount.getText().replaceAll(",", ""));
                txtDiscountP.setText(String.format("%1$,.2f", (100 * tDiscount) / tAmt));
                txtAfterDiscount.setText(String.format("%1$,.2f", (tAmt - tDiscount)));
                txtAfterDiscount.requestFocus();
            }
        });
        txtDiscountP.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Double tAmt = Double.parseDouble(txtTotalAmount.getText().replaceAll(",", ""));
                Double tDiscountP = Double.parseDouble(txtDiscountP.getText().replaceAll(",", ""));
                txtDiscount.setText(String.format("%1$,.2f", (tAmt * tDiscountP) / 100));
                txtDiscount.requestFocus();
            }
        });
        txtAfterDiscount.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                txtTaxable.setText(txtAfterDiscount.getText());
                jtpMain.setSelectedIndex(3);
                txtTaxable.requestFocus();
            }
        });
        txtTaxable.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                cmbTaxType.requestFocus();
            }
        });
        cmbTaxType.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (cmbTaxType.getSelectedItem().toString().equals("CST")) {
                    txtTaxP.setText("2");
                    chkCFormRequired.setVisible(true);
                }else{
                    txtTaxP.setText("14.5");
                    chkCFormRequired.setVisible(false);
                }
            }
        });
        chkCFormRequired.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {//checkbox has been selected
                    chkCFormReceived.setVisible(true);
                } else {
                    chkCFormReceived.setVisible(false);
                };
            }
        });

        txtTax.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Double tAmt = Double.parseDouble(txtTaxable.getText().replaceAll(",", ""));
                Double tTax = Double.parseDouble(txtTax.getText().replaceAll(",", ""));
                txtTotalAfterTax.setText(String.format("%1$,.2f", (tAmt + tTax)));
                txtTotalAfterTax.requestFocus();
            }
        });
        txtTaxP.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Double tAmt = Double.parseDouble(txtTaxable.getText().replaceAll(",", ""));
                Double tTaxP = Double.parseDouble(txtTaxP.getText().replaceAll(",", ""));
                txtTax.setText(String.format("%1$,.2f", (tAmt * tTaxP) / 100));
                txtTax.requestFocus();
            }
        });
        txtTotalAfterTax.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                txtHandlingP.requestFocus();
            }
        });
        txtHandlingP.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Double tAmt = Double.parseDouble(txtTaxable.getText().replaceAll(",", ""));
                Double tHandP = Double.parseDouble(txtHandlingP.getText().replaceAll(",", ""));
                txtHandling.setText(String.format("%1$,.2f", (tAmt * tHandP) / 100));
                txtHandling.requestFocus();
            }
        });
        txtHandling.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Double tAmt = Double.parseDouble(txtTaxable.getText().replaceAll(",", ""));
                Double tHandling = Double.parseDouble(txtHandling.getText().replaceAll(",", ""));
                txtNetPayable.setText(String.format("%1$,.2f", (tAmt + tHandling)));
                txtNetPayable.requestFocus();
            }
        });
        txtNetPayable.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                txtPaidAmount.requestFocus();
                new config.functions().SelectAll(txtPaidAmount);
            }
        });
        txtPaidAmount.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Double tAmt = Double.parseDouble(txtNetPayable.getText().replaceAll(",", ""));
                Double tPaid = Double.parseDouble(txtPaidAmount.getText().replaceAll(",", ""));
                txtBalance.setText(String.format("%1$,.2f", (tAmt - tPaid)));
                txtBalance.requestFocus();
            }
        });
        txtBalance.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                jtpMain.setSelectedIndex(4);
                txtCarrier.requestFocus();
            }
        });
        txtCarrier.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                txtShipmentNo.requestFocus();
            }
        });
        txtShipmentNo.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                txtNetWeight.requestFocus();
            }
        });
        txtNetWeight.addActionListener(new ActionListener() {

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

    private void addFocusEvent(final JTextField tblTarget) {
        final Color thisColor = new config.Global().textFocus;
        tblTarget.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent arg0) {
                tblTarget.setBackground(thisColor);
                new config.functions().SelectAll(tblTarget);
            }

            @Override
            public void focusLost(FocusEvent arg0) {
                tblTarget.setBackground(new Color(255, 255, 255));
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnPrint1;
    private javax.swing.JButton btnPrint2;
    private javax.swing.JButton btnPrint3;
    private javax.swing.JButton btnPrint4;
    private javax.swing.JButton btnSave;
    private javax.swing.JCheckBox chkCFormReceived;
    private javax.swing.JCheckBox chkCFormRequired;
    private javax.swing.JComboBox cmbTaxType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTabbedPane jtpMain;
    private javax.swing.JLabel lblAvl;
    private javax.swing.JPanel pnlPrint;
    private javax.swing.JScrollPane scMain;
    private javax.swing.JScrollPane scOption;
    private javax.swing.JScrollPane scOption1;
    private javax.swing.JTable tblClient;
    private javax.swing.JTable tblMain;
    private javax.swing.JTable tblOption;
    private javax.swing.JTable tblOption1;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtAfterDiscount;
    private javax.swing.JTextField txtAmount;
    private javax.swing.JTextField txtBalance;
    public javax.swing.JTextField txtCID;
    private javax.swing.JTextField txtCarrier;
    private javax.swing.JTextField txtCity;
    private javax.swing.JTextField txtClientID;
    public javax.swing.JTextField txtClientName;
    private javax.swing.JTextField txtDiscount;
    private javax.swing.JTextField txtDiscountP;
    private javax.swing.JTextField txtHandling;
    private javax.swing.JTextField txtHandlingP;
    private javax.swing.JFormattedTextField txtInvoiceDate;
    public javax.swing.JTextField txtInvoiceNo;
    private javax.swing.JTextField txtItemID;
    private javax.swing.JTextField txtItemName;
    private javax.swing.JTextField txtNetPayable;
    private javax.swing.JTextField txtNetWeight;
    private javax.swing.JTextField txtPaidAmount;
    private javax.swing.JTextField txtQnty;
    public javax.swing.JTextField txtRefNo;
    private javax.swing.JTextField txtSAPCode;
    private javax.swing.JTextField txtShipmentNo;
    private javax.swing.JTextField txtState;
    private javax.swing.JTextField txtTax;
    private javax.swing.JTextField txtTaxP;
    private javax.swing.JTextField txtTaxable;
    private javax.swing.JTextField txtTotalAfterTax;
    private javax.swing.JTextField txtTotalAmount;
    // End of variables declaration//GEN-END:variables
}
