package com.hit.admission.view.user;

import com.hit.admission.components.scroll.ScrollBar;
import com.hit.admission.components.table.TableButtonCellEditor;
import com.hit.admission.components.table.TableButtonCellRender;
import com.hit.admission.components.table.TableHeader;
import com.hit.admission.controller.MajorController;
import com.hit.admission.controller.MajorDetailController;
import com.hit.admission.dto.MajorDTO;
import java.awt.Color;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import com.hit.admission.event.ButtonClickEvent;

/**
 *
 * @author Huy Doan
 */
public class MajorView extends javax.swing.JPanel {

    private final MajorDetailController majorDetailController;
    
    private final MajorController majorController;

    public MajorView() {
        initComponents();
        this.majorDetailController = new MajorDetailController();
        this.majorController = new MajorController();
        
        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);

        table.setTableHeader(TableHeader.customTableHeader(table.getTableHeader()));
        ButtonClickEvent registerEvent = new ButtonClickEvent() {
            @Override
            public void onClick(int row) {
                System.out.println("Register code " + table.getValueAt(row, 0));
                //handle logic register major here
            }
        };
        table.getColumnModel().getColumn(5).setCellRenderer(new TableButtonCellRender("Đăng ký"));
        table.getColumnModel().getColumn(5).setCellEditor(new TableButtonCellEditor(registerEvent, "Đăng ký"));

        loadFilterYearComboBox();
        loadMajor();
    }

    private void loadFilterYearComboBox() {
        Integer minYear = majorDetailController.getYearMinMajor();
        Integer maxYear = LocalDate.now().getYear();
        for (int i = maxYear; i >= minYear; i--) {
            filterYear.addItem(String.valueOf(i));
        }
    }

    private void loadMajor() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        Integer year = Integer.valueOf(filterYear.getSelectedItem().toString());
        List<MajorDTO> majorDTOs = majorController.getMajors(year, null);
        int numberRowsOfTable = majorDTOs.size();
        model.setRowCount(numberRowsOfTable);
        for (int i = 0; i < numberRowsOfTable; i++) {
            model.setValueAt(majorDTOs.get(i).getCode(), i, 0);
            model.setValueAt(majorDTOs.get(i).getName(), i, 1);
            model.setValueAt(majorDTOs.get(i).getBlocks(), i, 2);
            model.setValueAt(majorDTOs.get(i).getAmountStudentReceived(), i, 3);
            model.setValueAt(majorDTOs.get(i).getBenchMark(), i, 4);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new com.hit.admission.components.border.PanelBorder();
        spTable = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        filterYear = new javax.swing.JComboBox<>();
        search1 = new com.hit.admission.components.search.Search();

        table.setFont(new java.awt.Font("sansserif", 0, 13)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã ngành", "Tên ngành", "Khối", "Chỉ tiêu", "Điểm chuẩn", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setRowHeight(40);
        spTable.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setPreferredWidth(40);
            table.getColumnModel().getColumn(1).setPreferredWidth(250);
            table.getColumnModel().getColumn(2).setPreferredWidth(100);
            table.getColumnModel().getColumn(3).setPreferredWidth(30);
            table.getColumnModel().getColumn(4).setPreferredWidth(40);
            table.getColumnModel().getColumn(5).setPreferredWidth(30);
        }

        filterYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterYearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addComponent(search1, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 805, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(filterYear, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(search1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filterYear, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void filterYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterYearActionPerformed
        loadMajor();
    }//GEN-LAST:event_filterYearActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> filterYear;
    private com.hit.admission.components.border.PanelBorder panelBorder1;
    private com.hit.admission.components.search.Search search1;
    private javax.swing.JScrollPane spTable;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
