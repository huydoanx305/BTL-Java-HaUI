package com.hit.admission.view.admin;

import com.hit.admission.components.scroll.ScrollBar;
import com.hit.admission.components.table.TableHeader;
import com.hit.admission.controller.StudentController;
import com.hit.admission.dto.StudentDTO;
import java.awt.Color;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Huy Doan
 */
public class Test extends javax.swing.JPanel {

    private final StudentController studentController;

    public Test() {
        initComponents();
        studentController = new StudentController();

        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        spTable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tableStudent.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableStudent.setTableHeader(TableHeader.customTableHeader(tableStudent.getTableHeader()));
        
        loadStudents();
    }

    private void loadStudents() {
//        DefaultTableModel model = (DefaultTableModel) tableStudent.getModel();
//        List<StudentDTO> students = studentController.getStudents();
//        int numberRowsOfTable = students.size();
//        model.setRowCount(numberRowsOfTable);
//        for (int i = 0; i < numberRowsOfTable; i++) {
//            model.setValueAt(students.get(i).getId(), i, 0);
//            model.setValueAt(students.get(i).getLastName() + " " + students.get(i).getFirstName(), i, 1);
//            model.setValueAt(students.get(i).getOrderNumber(), i, 2);
//            model.setValueAt(students.get(i).getCitizenIdentityNumber(), i, 3);
//            model.setValueAt(students.get(i).getCitizenIdentityIssueDate(), i, 4);
//            model.setValueAt(students.get(i).getCitizenIdentityIssuedBy(), i, 5);
//            model.setValueAt(students.get(i).getEmail(), i, 6);
//            model.setValueAt(students.get(i).getPhoneNumber(), i, 7);
//            model.setValueAt(students.get(i).getGender(), i, 8);
//            model.setValueAt(students.get(i).getBirthDay(), i, 9);
//            model.setValueAt(students.get(i).getEthnic(), i, 10);
//            model.setValueAt(students.get(i).getAddress(), i, 11);
//        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new com.hit.admission.components.border.PanelBorder();
        jLabel2 = new javax.swing.JLabel();
        jId = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jCMND = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jEmail = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jHoTen = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jNguoiCap = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPhone = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jSBD = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jDanToc = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jDiaChi = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jThem = new javax.swing.JButton();
        jSua = new javax.swing.JButton();
        jXoa = new javax.swing.JButton();
        jClear = new javax.swing.JButton();
        jSearch = new com.hit.admission.components.search.Search();
        jLabel1 = new javax.swing.JLabel();
        jNgaySinh = new com.toedter.calendar.JDateChooser();
        jNgayCap = new com.toedter.calendar.JDateChooser();
        jGioiTinh = new javax.swing.JComboBox<>();
        spTable = new javax.swing.JScrollPane();
        tableStudent = new javax.swing.JTable();

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel2.setText("ID");

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel3.setText("CMND/CCCD");

        jLabel4.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel4.setText("Email");

        jLabel5.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel5.setText("Họ tên");

        jLabel6.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel6.setText("Người cấp");

        jLabel7.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel7.setText("SĐT");

        jLabel8.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel8.setText("SBD");

        jLabel9.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel9.setText("Ngày cấp");

        jLabel12.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel12.setText("Ngày sinh");

        jLabel13.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel13.setText("Dân tộc");

        jLabel14.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel14.setText("Giới tính");

        jLabel10.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel10.setText("Địa chỉ");

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(jThem, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                .addComponent(jSua, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(jXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(jClear)
                .addGap(58, 58, 58))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jThem)
                    .addComponent(jSua)
                    .addComponent(jClear)
                    .addComponent(jXoa))
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 22)); // NOI18N
        jLabel1.setText("Quản lý ứng viên");

        jNgaySinh.setBorder(null);
        jNgaySinh.setDateFormatString("yyyy-MM-dd");
        jNgaySinh.setMinimumSize(new java.awt.Dimension(13, 28));
        jNgaySinh.setPreferredSize(new java.awt.Dimension(13, 28));

        jNgayCap.setBorder(null);
        jNgayCap.setDateFormatString("yyyy-MM-dd");
        jNgayCap.setMinimumSize(new java.awt.Dimension(13, 28));
        jNgayCap.setPreferredSize(new java.awt.Dimension(13, 28));

        jGioiTinh.setBackground(new java.awt.Color(255, 255, 255));
        jGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));
        jGioiTinh.setMinimumSize(new java.awt.Dimension(13, 28));
        jGioiTinh.setPreferredSize(new java.awt.Dimension(13, 28));

        tableStudent.setFont(new java.awt.Font("sansserif", 0, 13)); // NOI18N
        tableStudent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Họ Tên", "SBD", "CMND/CCCD", "Ngày cấp", "Người cấp", "Email", "SĐT", "Giới tính", "Ngày sinh", "Dân tộc", "Địa chỉ"
            }
        ));
        tableStudent.setRowHeight(30);
        spTable.setViewportView(tableStudent);
        if (tableStudent.getColumnModel().getColumnCount() > 0) {
            tableStudent.getColumnModel().getColumn(0).setPreferredWidth(50);
            tableStudent.getColumnModel().getColumn(1).setPreferredWidth(180);
            tableStudent.getColumnModel().getColumn(2).setPreferredWidth(60);
            tableStudent.getColumnModel().getColumn(3).setPreferredWidth(150);
            tableStudent.getColumnModel().getColumn(4).setPreferredWidth(80);
            tableStudent.getColumnModel().getColumn(5).setPreferredWidth(180);
            tableStudent.getColumnModel().getColumn(6).setPreferredWidth(150);
            tableStudent.getColumnModel().getColumn(7).setPreferredWidth(100);
            tableStudent.getColumnModel().getColumn(8).setPreferredWidth(60);
            tableStudent.getColumnModel().getColumn(9).setPreferredWidth(80);
            tableStudent.getColumnModel().getColumn(10).setPreferredWidth(70);
            tableStudent.getColumnModel().getColumn(11).setPreferredWidth(250);
        }

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8)
                    .addComponent(jLabel12))
                .addGap(20, 20, 20)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSBD, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jHoTen, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jId, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(70, 70, 70)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9)
                    .addComponent(jLabel13))
                .addGap(20, 20, 20)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jNguoiCap)
                    .addComponent(jDanToc)
                    .addComponent(jCMND)
                    .addComponent(jNgayCap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(70, 70, 70)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel7)
                    .addComponent(jLabel4)
                    .addComponent(jLabel10))
                .addGap(20, 20, 20)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPhone, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDiaChi, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jEmail, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jGioiTinh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30))
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(356, 356, 356)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                .addGap(298, 298, 298))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spTable)
                .addContainerGap())
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addGap(20, 20, 20)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCMND, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jNguoiCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jNgayCap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jNgaySinh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jDanToc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField jCMND;
    private javax.swing.JButton jClear;
    private javax.swing.JTextField jDanToc;
    private javax.swing.JTextField jDiaChi;
    private javax.swing.JTextField jEmail;
    private javax.swing.JComboBox<String> jGioiTinh;
    private javax.swing.JTextField jHoTen;
    private javax.swing.JTextField jId;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private com.toedter.calendar.JDateChooser jNgayCap;
    private com.toedter.calendar.JDateChooser jNgaySinh;
    private javax.swing.JTextField jNguoiCap;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jPhone;
    private javax.swing.JTextField jSBD;
    private com.hit.admission.components.search.Search jSearch;
    private javax.swing.JButton jSua;
    private javax.swing.JButton jThem;
    private javax.swing.JButton jXoa;
    private com.hit.admission.components.border.PanelBorder panelBorder1;
    private javax.swing.JScrollPane spTable;
    private javax.swing.JTable tableStudent;
    // End of variables declaration//GEN-END:variables
}
