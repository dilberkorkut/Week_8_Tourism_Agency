package com.tourismagency.View;

import com.tourismagency.Helper.Config;
import com.tourismagency.Helper.Helper;
import com.tourismagency.Model.Admin;
import com.tourismagency.Model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminGUI extends JFrame {

    private final Admin admin;
    private JPanel wrapper;
    private JTabbedPane tabbed_admin_users;
    private JScrollPane scrl_ad_users;
    private JTable tbl_ad_userlist;
    private JPanel pnl_ad_user_form;
    private JTextField fld_ad_search_name;
    private JTextField fld_ad_search_username;
    private JComboBox cmb_ad_search_type;
    private JButton btn_ad_logout;
    private JLabel lbl_ad_welcome;
    private JButton btn_ad_userSearch;
    private JTextField fld_ad_add_name_surname;
    private JTextField fld_ad_add_password;
    private JTextField fld_ad_add_uname;
    private JComboBox cmb_ad_add_type;
    private JButton btn_ad_addUser;
    private JButton btn_ad_deleteUser;
    private JPanel pnl_ad_form_newUser;
    private JTextField fld_ad_delete_userId;
    private DefaultTableModel mdl_ad_userList;
    private Object[] row_ad_userList;

    public AdminGUI (Admin admin) {
        this.admin = admin;

        add(wrapper);
        setSize(1000,500);
        setLocation(Helper.screenCenterPoint("x", getSize()) , Helper.screenCenterPoint("y" , getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        lbl_ad_welcome.setText("Welcome " + admin.getName());

        // table - user model
        mdl_ad_userList = new DefaultTableModel(); // user tablosu icin model olusturulur.
        Object[] col_ad_userList = {"Id","Name Surname","Username","Password","Type"}; // user tablosu sutun basliklari ve sutun sayisi belirlenir.
        mdl_ad_userList.setColumnIdentifiers(col_ad_userList);
        row_ad_userList = new Object[col_ad_userList.length];
        loadUserModel(); // database verileri alinarak user model yuklenir .
        tbl_ad_userlist.setModel(mdl_ad_userList); // tablo model ile iliskilendirilir.
        tbl_ad_userlist.getTableHeader().setReorderingAllowed(false); // tablo sutunlarinin yeniden duzenlenmesi engellenir.
        tbl_ad_userlist.getColumnModel().getColumn(0).setMaxWidth(75); // ilk sutunun max genisligi belirlenir.

        tbl_ad_userlist.getSelectionModel().addListSelectionListener(e -> { //secilen user id'si kutuya yazdirma.
            try {
                //secilen satirdaki user id'si alinir.
                String select_user_id = tbl_ad_userlist.getValueAt(tbl_ad_userlist.getSelectedRow(), 0).toString();
                fld_ad_delete_userId.setText(select_user_id); // alinan id kutucuga yazdirilir.
            } catch (Exception ignored){
            }
        });

        // button - add user
        btn_ad_addUser.addActionListener(e -> { //user ekleme butonunu tikladiktan sonra uyari mesajlari ile ekleme islemini tamamlama.
            if (Helper.isFieldEmpty(fld_ad_add_name_surname) || Helper.isFieldEmpty(fld_ad_add_uname) || Helper.isFieldEmpty(fld_ad_add_password)) {
                Helper.showMsg("fill");
            } else {
                String name = fld_ad_add_name_surname.getText();
                String uname = fld_ad_add_uname.getText();
                String password = fld_ad_add_password.getText();
                String type = cmb_ad_add_type.getSelectedItem().toString();
                if (User.add(name, uname, password, type)) { //user ekleme islemi yapilir ve sonucu mesaj ile gonderilir
                    Helper.showMsg("done");
                    loadUserModel();  // user model yeniden yuklenerek sayfa yenilenir..
                    fld_ad_add_name_surname.setText(null); // arama kutularini temizleme
                    fld_ad_add_uname.setText(null);
                    fld_ad_add_password.setText(null);
                } else {
                    Helper.showMsg("error");
                }
            }
        });

        // button - delete user
        btn_ad_deleteUser.addActionListener(e -> { //user silme butonunu tikladiktan sonra uyari mesajlari ile silme islemini tamamlama.
            if (Helper.isFieldEmpty(fld_ad_delete_userId)) {
                Helper.showMsg("fill");
            } else {
                if (Helper.confirm("sure")) {
                    int userId = Integer.parseInt(fld_ad_delete_userId.getText()); //secilen user id'si alinir.
                    if (User.delete(userId)) {
                        Helper.showMsg("done");
                        loadUserModel(); // user model yeniden yuklenir silme isleminden sonra
                        fld_ad_delete_userId.setText(null); // id kutusu temizlenir.
                    } else {
                        Helper.showMsg("error");
                    }
                }
            }
        });

        // button - search user
        btn_ad_userSearch.addActionListener(e -> { //arama kutularindan alinan verileri saklamak icin degiskenler olusturulur.
            String name = fld_ad_search_name.getText();
            String uname = fld_ad_search_username.getText();
            String type = cmb_ad_search_type.getSelectedItem().toString();

            String query = User.searchQuery(name, uname, type); // searchQuery metodu ile veri tabaninda arama yapmak icin sorgu olusturulur.
            fld_ad_search_name.setText(null); // arama kutularini temizleme
            fld_ad_search_username.setText(null);
            cmb_ad_search_type.setSelectedItem(null);
            try {
                ArrayList<User> searchingUser = User.searchUsers(query); // veri tabani sonuclari ArrayList<User> tipinde bir liste olarak alinir.
                loadUserSearchModel(searchingUser); // elde edilen sonuclar arayuzunde gosterilmek uzere uygun bir modele yuklenir.
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    // user modeli yukleme
    public void loadUserModel(){ // veri tabanindaki tum userlar bu metot ile cekilir tabloya yuklenir.
        DefaultTableModel clearModel = (DefaultTableModel) tbl_ad_userlist.getModel(); // tabloyu temizleme islemi icin mevcut model alinir.
        clearModel.setRowCount(0); // mevcut modelin satir sayisi sifirlanir. boylece tablo temizlenir.
        for(User obj : User.getList()) { // user sinifindaki getList metodu ile tum user'lari iceren bir liste elde edilir.
            int i = 0;
            // user bilgileri sirayla alinir ve model uzerine eklenir.
            row_ad_userList[i++] = obj.getId();
            row_ad_userList[i++] = obj.getName();
            row_ad_userList[i++] = obj.getUname();
            row_ad_userList[i++] = obj.getPassword();
            row_ad_userList[i++] = obj.getType();
            mdl_ad_userList.addRow(row_ad_userList); // modeldeki bu satir tabloya eklenir.
        }
    }

    // search user modeli yukleme
    public void loadUserSearchModel(ArrayList<User> list){ // belirli bir arama filtresi sorgusuna gore filtrelenmis bir user listesi alir ve bu liste uzerinden tabloyu gunceller.
        DefaultTableModel clearModel = (DefaultTableModel) tbl_ad_userlist.getModel();
        clearModel.setRowCount(0);
        for(User obj : list) {
            int i = 0;
            row_ad_userList[i++] = obj.getId();
            row_ad_userList[i++] = obj.getName();
            row_ad_userList[i++] = obj.getUname();
            row_ad_userList[i++] = obj.getPassword();
            row_ad_userList[i++] = obj.getType();
            mdl_ad_userList.addRow(row_ad_userList);
        }
    }
}
