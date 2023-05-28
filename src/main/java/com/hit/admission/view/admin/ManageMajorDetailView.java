package com.hit.admission.view.admin;

import com.hit.admission.components.dialog.ConfirmDialog;
import com.hit.admission.components.table.TableHeader;
import com.hit.admission.controller.BlockController;
import com.hit.admission.controller.MajorDetailController;
import com.hit.admission.dto.MajorDetailDTO;
import com.hit.admission.event.EventButtonSearchClick;
import com.hit.admission.utils.ExcelMajorUtil;
import com.hit.admission.utils.ResourceUtil;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.lang3.ObjectUtils;

/**
 *
 * @author Huy Doan
 */
public class ManageMajorDetailView extends javax.swing.JPanel {

    private final MajorDetailController majorDetailController;

    private final BlockController blockController;

    public ManageMajorDetailView() {
        initComponents();
        this.majorDetailController = new MajorDetailController();
        this.blockController = new BlockController();

        jImport.setText("Import dữ liệu " + LocalDate.now().getYear());
        tableMajor.setTableHeader(TableHeader.customTableHeader(tableMajor.getTableHeader()));

        loadFilterYearComboBox();
        loadMajors(jSearch.getKeyword().getText());
        handleSelectCellTable();

        jSearch.addEventButtonSearchClick(new EventButtonSearchClick() {
            @Override
            public void onClick(String text) {
                loadMajors(text);
            }
        });

    }

    private void loadFilterYearComboBox() {
        Integer minYear = majorDetailController.getYearMinMajor();
        Integer maxYear = LocalDate.now().getYear();
        for (int i = maxYear; i >= minYear; i--) {
            jFilterYear.addItem(String.valueOf(i));
        }
    }

    private void loadMajors(String keyword) {
        DefaultTableModel model = (DefaultTableModel) tableMajor.getModel();
        Integer year = Integer.valueOf(jFilterYear.getSelectedItem().toString());
        List<MajorDetailDTO> majorDetailDTOs = majorDetailController.getMajorDetailsForAdmin(year, keyword);
        int numberRowsOfTable = majorDetailDTOs.size();
        System.out.println(numberRowsOfTable);
        model.setRowCount(numberRowsOfTable);
        for (int i = 0; i < numberRowsOfTable; i++) {
            model.setValueAt(majorDetailDTOs.get(i).getId(), i, 0);
            model.setValueAt(majorDetailDTOs.get(i).getCode(), i, 1);
            model.setValueAt(majorDetailDTOs.get(i).getName(), i, 2);
            model.setValueAt(majorDetailDTOs.get(i).getBlocks(), i, 3);
            model.setValueAt(majorDetailDTOs.get(i).getAmountStudentReceived(), i, 4);
            model.setValueAt(majorDetailDTOs.get(i).getBenchMark(), i, 5);
        }
    }

    private void handleSelectCellTable() {
        ListSelectionModel cellSelectionModel = tableMajor.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = tableMajor.getSelectedRow();
                if (selectedRow != -1) {
                    Integer studentId = Integer.valueOf(tableMajor.getValueAt(selectedRow, 0).toString());
                    jId.setText(studentId.toString());
                    jMaNganh.setText(tableMajor.getValueAt(selectedRow, 1).toString());
                    jTenNganh.setText(tableMajor.getValueAt(selectedRow, 2).toString());
                    jKhoi.setText(tableMajor.getValueAt(selectedRow, 3).toString());
                    if (ObjectUtils.isNotEmpty(tableMajor.getValueAt(selectedRow, 4))) {
                        jChiTieu.setText(tableMajor.getValueAt(selectedRow, 4).toString());
                    } else {
                        jChiTieu.setText(null);
                    }
                    if (ObjectUtils.isNotEmpty(tableMajor.getValueAt(selectedRow, 5))) {
                        jDiemChuan.setText(tableMajor.getValueAt(selectedRow, 5).toString());
                    } else {
                        jChiTieu.setText(null);
                    }
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new com.hit.admission.components.border.PanelBorder();
        jLabel2 = new javax.swing.JLabel();
        jId = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jMaNganh = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTenNganh = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jKhoi = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jChiTieu = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jDiemChuan = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jThem = new javax.swing.JButton();
        jSua = new javax.swing.JButton();
        jXoa = new javax.swing.JButton();
        jClear = new javax.swing.JButton();
        jSearch = new com.hit.admission.components.search.Search();
        jFilterYear = new javax.swing.JComboBox<>();
        jFileMau = new javax.swing.JButton();
        jImport = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        spTable = new javax.swing.JScrollPane();
        tableMajor = new javax.swing.JTable();

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel2.setText("ID");

        jId.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel3.setText("Mã ngành");

        jMaNganh.setFocusable(false);

        jLabel4.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel4.setText("Tên ngành");

        jTenNganh.setFocusable(false);

        jLabel5.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel5.setText("Khối");

        jKhoi.setFocusable(false);

        jLabel6.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel6.setText("Chỉ tiêu");

        jLabel7.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel7.setText("Điểm chuẩn");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jThem.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/Add.png"))); // NOI18N
        jThem.setText("Thêm");

        jSua.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/Edit.png"))); // NOI18N
        jSua.setText("Sửa");

        jXoa.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/Delete.png"))); // NOI18N
        jXoa.setText("Xóa");

        jClear.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/Refresh.png"))); // NOI18N
        jClear.setText("Làm mới");

        jFilterYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFilterYearActionPerformed(evt);
            }
        });

        jFileMau.setText("Lấy file mẫu");
        jFileMau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFileMauActionPerformed(evt);
            }
        });

        jImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jImportActionPerformed(evt);
            }
        });

        jButton2.setText("Công bố điểm chuẩn");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jThem, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(89, 89, 89)
                        .addComponent(jSua, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(107, 107, 107)
                .addComponent(jXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jClear)
                        .addGap(75, 75, 75))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jFilterYear, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jFileMau)
                .addGap(18, 18, 18)
                .addComponent(jImport, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jImport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jFileMau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(jFilterYear))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jThem)
                    .addComponent(jSua)
                    .addComponent(jClear)
                    .addComponent(jXoa))
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 22)); // NOI18N
        jLabel1.setText("Quản lý chi tiết chuyên ngành");

        tableMajor.setFont(new java.awt.Font("sansserif", 0, 13)); // NOI18N
        tableMajor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Mã ngành", "Tên ngành", "Khối", "Chỉ tiêu", "Điểm chuẩn"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableMajor.setRowHeight(40);
        spTable.setViewportView(tableMajor);
        if (tableMajor.getColumnModel().getColumnCount() > 0) {
            tableMajor.getColumnModel().getColumn(0).setPreferredWidth(60);
            tableMajor.getColumnModel().getColumn(1).setPreferredWidth(100);
            tableMajor.getColumnModel().getColumn(2).setPreferredWidth(250);
            tableMajor.getColumnModel().getColumn(3).setPreferredWidth(100);
        }

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spTable)
                .addContainerGap())
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5))
                .addGap(49, 49, 49)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jKhoi, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jId, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(70, 70, 70)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6))
                .addGap(20, 20, 20)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jChiTieu)
                    .addComponent(jMaNganh))
                .addGap(70, 70, 70)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel4))
                .addGap(20, 20, 20)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDiemChuan, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTenNganh, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(30, 30, 30))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                .addGap(304, 304, 304)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(271, 271, 271))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(24, 24, 24)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jMaNganh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTenNganh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jKhoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jChiTieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDiemChuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spTable, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jFilterYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFilterYearActionPerformed
        loadMajors(jSearch.getKeyword().getText());
    }//GEN-LAST:event_jFilterYearActionPerformed

    private void jFileMauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFileMauActionPerformed
        ExcelMajorUtil.exportFileExample(majorDetailController, "D:/example.xlsx");
        new ConfirmDialog(null, "Lưu thành công", "File đã được lưu vào D:/example.xlsx");
    }//GEN-LAST:event_jFileMauActionPerformed

    private void jImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jImportActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn file excel");
        fileChooser.setPreferredSize(new java.awt.Dimension(700, 500));
        FileNameExtensionFilter imageFilter
                = new FileNameExtensionFilter("Excel files (*.xlsx)", "xlsx");
        fileChooser.setFileFilter(imageFilter);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String extension = ResourceUtil.getFileExtension(selectedFile);
            if (extension == null || !ResourceUtil.isImageExtension(extension)) {
                JOptionPane.showMessageDialog(this, "File không hợp lệ! Vui lòng chọn file ảnh");
                return;
            }
            ///
        }
    }//GEN-LAST:event_jImportActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JTextField jChiTieu;
    private javax.swing.JButton jClear;
    private javax.swing.JTextField jDiemChuan;
    private javax.swing.JButton jFileMau;
    private javax.swing.JComboBox<String> jFilterYear;
    private javax.swing.JTextField jId;
    private javax.swing.JButton jImport;
    private javax.swing.JTextField jKhoi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField jMaNganh;
    private javax.swing.JPanel jPanel1;
    private com.hit.admission.components.search.Search jSearch;
    private javax.swing.JButton jSua;
    private javax.swing.JTextField jTenNganh;
    private javax.swing.JButton jThem;
    private javax.swing.JButton jXoa;
    private com.hit.admission.components.border.PanelBorder panelBorder1;
    private javax.swing.JScrollPane spTable;
    private javax.swing.JTable tableMajor;
    // End of variables declaration//GEN-END:variables
}
