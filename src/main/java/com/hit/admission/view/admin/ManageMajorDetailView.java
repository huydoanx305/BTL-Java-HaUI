package com.hit.admission.view.admin;

import com.github.lgooddatepicker.components.DatePickerSettings;
import com.hit.admission.job.CongBoDiemChuanJob;
import com.hit.admission.components.dialog.ConfirmDialog;
import com.hit.admission.components.table.TableHeader;
import com.hit.admission.controller.MajorDetailController;
import com.hit.admission.dto.CommonResponse;
import com.hit.admission.dto.MajorDetailDTO;
import com.hit.admission.dto.ReadFileExcelResponse;
import com.hit.admission.event.EventButtonSearchClick;
import com.hit.admission.utils.ExcelMajorUtil;
import com.hit.admission.utils.ResourceUtil;
import java.awt.HeadlessException;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.lang3.ObjectUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 *
 * @author Huy Doan
 */
public class ManageMajorDetailView extends javax.swing.JPanel {

    private final MajorDetailController majorDetailController;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public ManageMajorDetailView() {
        initComponents();
        this.majorDetailController = new MajorDetailController();

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
                    if (ObjectUtils.isNotEmpty(tableMajor.getValueAt(selectedRow, 0))) {
                        Integer studentId = Integer.valueOf(tableMajor.getValueAt(selectedRow, 0).toString());
                        jId.setText(studentId.toString());
                    } else {
                        jId.setText(null);
                    }
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
                        jDiemChuan.setText(null);
                    }
                }
            }
        });
    }

    private MajorDetailDTO createMajorDetailDTO() {
        MajorDetailDTO majorDetailDTO = new MajorDetailDTO();
        if (ObjectUtils.isNotEmpty(jId.getText())) {
            majorDetailDTO.setId(Integer.valueOf(jId.getText()));
        }
        majorDetailDTO.setCode(jMaNganh.getText());
        majorDetailDTO.setName(jTenNganh.getText());
        majorDetailDTO.setBlocks(jKhoi.getText());
        if (ObjectUtils.isNotEmpty(jChiTieu.getText())) {
            majorDetailDTO.setAmountStudentReceived(Integer.valueOf(jChiTieu.getText()));
        }
        if (ObjectUtils.isNotEmpty(jDiemChuan.getText())) {
            majorDetailDTO.setBenchMark(Float.valueOf(jDiemChuan.getText()));
        }
        return majorDetailDTO;
    }

    private void clearInput() {
        jId.setText(null);
        jMaNganh.setText(null);
        jTenNganh.setText(null);
        jKhoi.setText(null);
        jChiTieu.setText(null);
        jDiemChuan.setText(null);
    }

    private void setupDialog() throws Exception {
        jChonThoiGian.setTitle("Đăng ký nguyện vọng");
        jChonThoiGian.setSize(470, 175);
        jChonThoiGian.setLocationRelativeTo(null);
        final DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DatePickerSettings settingDateNgayBatDau = new DatePickerSettings();
        settingDateNgayBatDau.setFormatForDatesCommonEra(formatterDate);
        jThoiGianCBD.datePicker.setSettings(settingDateNgayBatDau);
        jThoiGianCBD.setDateTimePermissive(LocalDateTime.now().plusMinutes(1));
        jChonThoiGian.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jChonThoiGian = new javax.swing.JDialog();
        panelBorder2 = new com.hit.admission.components.border.PanelBorder();
        jLabel8 = new javax.swing.JLabel();
        jThoiGianCBD = new com.github.lgooddatepicker.components.DateTimePicker();
        jBtnXacNhan = new javax.swing.JButton();
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
        jSua = new javax.swing.JButton();
        jXoa = new javax.swing.JButton();
        jClear = new javax.swing.JButton();
        jSearch = new com.hit.admission.components.search.Search();
        jFilterYear = new javax.swing.JComboBox<>();
        jExport = new javax.swing.JButton();
        jImport = new javax.swing.JButton();
        jCongBoDiem = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        spTable = new javax.swing.JScrollPane();
        tableMajor = new javax.swing.JTable();

        jLabel8.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel8.setText("Chọn thời gian công bố điểm chuẩn");

        jBtnXacNhan.setBackground(new java.awt.Color(66, 133, 244));
        jBtnXacNhan.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        jBtnXacNhan.setForeground(new java.awt.Color(255, 255, 255));
        jBtnXacNhan.setText("Xác nhận");
        jBtnXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnXacNhanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder2Layout = new javax.swing.GroupLayout(panelBorder2);
        panelBorder2.setLayout(panelBorder2Layout);
        panelBorder2Layout.setHorizontalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder2Layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(jLabel8))
                    .addGroup(panelBorder2Layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(jThoiGianCBD, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(82, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jBtnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(137, 137, 137))
        );
        panelBorder2Layout.setVerticalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(jThoiGianCBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jBtnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout jChonThoiGianLayout = new javax.swing.GroupLayout(jChonThoiGian.getContentPane());
        jChonThoiGian.getContentPane().setLayout(jChonThoiGianLayout);
        jChonThoiGianLayout.setHorizontalGroup(
            jChonThoiGianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jChonThoiGianLayout.createSequentialGroup()
                .addComponent(panelBorder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jChonThoiGianLayout.setVerticalGroup(
            jChonThoiGianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel2.setText("ID");

        jId.setFocusable(false);

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

        jSua.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/Edit.png"))); // NOI18N
        jSua.setText("Tạo/Sửa");
        jSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSuaActionPerformed(evt);
            }
        });

        jXoa.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/Delete.png"))); // NOI18N
        jXoa.setText("Xóa");
        jXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jXoaActionPerformed(evt);
            }
        });

        jClear.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/Refresh.png"))); // NOI18N
        jClear.setText("Làm mới");
        jClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jClearActionPerformed(evt);
            }
        });

        jFilterYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFilterYearActionPerformed(evt);
            }
        });

        jExport.setText("Export file");
        jExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jExportActionPerformed(evt);
            }
        });

        jImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jImportActionPerformed(evt);
            }
        });

        jCongBoDiem.setText("Công bố điểm chuẩn");
        jCongBoDiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCongBoDiemActionPerformed(evt);
            }
        });

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
                        .addGap(150, 150, 150)
                        .addComponent(jSua, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(112, 112, 112)
                        .addComponent(jClear))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(343, 343, 343)
                        .addComponent(jFilterYear, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jCongBoDiem)
                .addGap(18, 18, 18)
                .addComponent(jExport)
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
                        .addComponent(jExport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCongBoDiem)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(jFilterYear))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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

    private void jExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jExportActionPerformed
        ExcelMajorUtil.exportFileExample(majorDetailController, "D:/example.xlsx");
        new ConfirmDialog(null, "Lưu thành công", "File đã được lưu vào D:/example.xlsx");
    }//GEN-LAST:event_jExportActionPerformed

    private void jImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jImportActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("D:\\"));
        fileChooser.setDialogTitle("Chọn file excel");
        fileChooser.setPreferredSize(new java.awt.Dimension(700, 500));
        FileNameExtensionFilter imageFilter
                = new FileNameExtensionFilter("Excel files (*.xlsx)", "xlsx");
        fileChooser.setFileFilter(imageFilter);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String extension = ResourceUtil.getFileExtension(selectedFile);
            if (extension == null || !ResourceUtil.isExcelExtension(extension)) {
                JOptionPane.showMessageDialog(this, "File không hợp lệ! Vui lòng chọn file excel");
                return;
            }
            try {
                ReadFileExcelResponse<List<MajorDetailDTO>> readFileResponse = ExcelMajorUtil.readFile(selectedFile.getAbsolutePath());
                if (readFileResponse.getStatus().equals(Boolean.TRUE)) {
                    CommonResponse response = majorDetailController.createOrUpdateMajorDetails(readFileResponse.getResponse());
                    if (response.getStatus().equals(Boolean.TRUE)) {
                        new ConfirmDialog(null, "Cập nhật thành công", response.getMessage());
                        loadMajors(jSearch.getKeyword().getText());
                    } else {
                        JOptionPane.showMessageDialog(null, response.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, readFileResponse.getMessage());
                }
            } catch (HeadlessException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jImportActionPerformed

    private void jCongBoDiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCongBoDiemActionPerformed
        try {
            setupDialog();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jCongBoDiemActionPerformed

    private void jClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jClearActionPerformed
        clearInput();
        jSearch.getKeyword().setText(null);
        loadMajors(jSearch.getKeyword().getText());
    }//GEN-LAST:event_jClearActionPerformed

    private void jSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSuaActionPerformed
        MajorDetailDTO majorDetailDTO = createMajorDetailDTO();
        try {
            CommonResponse commonResponse = majorDetailController.createOrUpdateMajorDetail(majorDetailDTO);
            if (commonResponse.getStatus().equals(Boolean.TRUE)) {
                new ConfirmDialog(null, "Cập nhật thành công", commonResponse.getMessage());
                loadMajors(jSearch.getKeyword().getText());
            } else {
                JOptionPane.showMessageDialog(null, commonResponse.getMessage());
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Hệ thống đã xảy ra lỗi. Vui lòng quay lại sau!");
        }
    }//GEN-LAST:event_jSuaActionPerformed

    private void jXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jXoaActionPerformed
        int option = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa chi tiết chuyên ngành này?",
                "Xác nhận xoá", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            try {
                CommonResponse response = majorDetailController.deleteMajorDetailById(Integer.valueOf(jId.getText()));
                if (response.getStatus().equals(Boolean.TRUE)) {
                    new ConfirmDialog(null, "Xóa thành công", response.getMessage());
                    clearInput();
                    loadMajors(jSearch.getKeyword().getText());
                } else {
                    JOptionPane.showMessageDialog(null, response.getMessage());
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Hệ thống đã xảy ra lỗi. Vui lòng quay lại sau!");
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jXoaActionPerformed

    private void jBtnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnXacNhanActionPerformed
        // Khởi tạo đối tượng Scheduler
        Scheduler scheduler;
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
            return;
        }
        // Tạo cron expression từ thời gian được chọn bởi người dùng
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("ss mm HH dd MM ? yyyy");
        LocalDateTime selectedDateTime = jThoiGianCBD.getDateTimeStrict();
        String cronExpression = dateFormat.format(selectedDateTime);
        // Tạo công việc (Job)
        JobDetail job = JobBuilder.newJob(CongBoDiemChuanJob.class)
                .withIdentity("congBoDiemChuanJob", "group1")
                .build();
        // Tạo trigger với cron expression
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("triggerCongBoDiem", "group1")
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                .build();
        // Lập lịch công việc với trigger
        try {
            scheduler.scheduleJob(job, trigger);
            new ConfirmDialog(null, "Thành công", "Tạo lịch công bố điểm thành công");
            jChonThoiGian.dispose();
        } catch (SchedulerException e) {
            e.printStackTrace();
            return;
        }
    }//GEN-LAST:event_jBtnXacNhanActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnXacNhan;
    private javax.swing.JTextField jChiTieu;
    private javax.swing.JDialog jChonThoiGian;
    private javax.swing.JButton jClear;
    private javax.swing.JButton jCongBoDiem;
    private javax.swing.JTextField jDiemChuan;
    private javax.swing.JButton jExport;
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
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField jMaNganh;
    private javax.swing.JPanel jPanel1;
    private com.hit.admission.components.search.Search jSearch;
    private javax.swing.JButton jSua;
    private javax.swing.JTextField jTenNganh;
    private com.github.lgooddatepicker.components.DateTimePicker jThoiGianCBD;
    private javax.swing.JButton jXoa;
    private com.hit.admission.components.border.PanelBorder panelBorder1;
    private com.hit.admission.components.border.PanelBorder panelBorder2;
    private javax.swing.JScrollPane spTable;
    private javax.swing.JTable tableMajor;
    // End of variables declaration//GEN-END:variables
}
