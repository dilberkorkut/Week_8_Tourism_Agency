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

        // Model userList

        mdl_ad_userList = new DefaultTableModel();
        Object[] col_ad_userList = {"Id","Name Surname","Username","Password","Type"};
        mdl_ad_userList.setColumnIdentifiers(col_ad_userList);
        row_ad_userList = new Object[col_ad_userList.length];
        loadUserModel();

        tbl_ad_userlist.setModel(mdl_ad_userList);
        tbl_ad_userlist.getTableHeader().setReorderingAllowed(false);
        tbl_ad_userlist.getColumnModel().getColumn(0).setMaxWidth(75);

        // add User - listener

        btn_ad_addUser.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_ad_add_name_surname) || Helper.isFieldEmpty(fld_ad_add_uname) || Helper.isFieldEmpty(fld_ad_add_password)) {
                Helper.showMsg("fill");
            } else {
                String name = fld_ad_add_name_surname.getText();
                String uname = fld_ad_add_uname.getText();
                String password = fld_ad_add_password.getText();
                String type = cmb_ad_add_type.getSelectedItem().toString();
                if (User.add(name, uname, password, type)) {
                    Helper.showMsg("done");
                    loadUserModel();
                    fld_ad_add_name_surname.setText(null);
                    fld_ad_add_uname.setText(null);
                    fld_ad_add_password.setText(null);

                } else {
                    Helper.showMsg("error");
                }
            }
        });

        // delete user - listener

        btn_ad_deleteUser.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_ad_delete_userId)) {
                Helper.showMsg("fill");
            } else {
                if (Helper.confirm("sure")) {
                    int userId = Integer.parseInt(fld_ad_delete_userId.getText());
                    if (User.delete(userId)) {
                        Helper.showMsg("done");
                        loadUserModel();
                        fld_ad_delete_userId.setText(null);
                    } else {
                        Helper.showMsg("error");
                    }
                }
            }
        });

        // search user = listener

        btn_ad_userSearch.addActionListener(e -> {
            String name = fld_ad_search_name.getText();
            String uname = fld_ad_search_username.getText();
            String type = cmb_ad_search_type.getSelectedItem().toString();

            String query = User.searchQuery(name, uname, type);

            try {
                ArrayList<User> searchingUser = User.searchUsers(query);
                loadUserModel(searchingUser);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });


        btn_ad_deleteUser.addActionListener(e -> {


        });
    }

    public void loadUserModel(){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_ad_userlist.getModel();
        clearModel.setRowCount(0);

        for(User obj : User.getList()) {
            int i = 0;

            row_ad_userList[i++] = obj.getId();
            row_ad_userList[i++] = obj.getName();
            row_ad_userList[i++] = obj.getUname();
            row_ad_userList[i++] = obj.getPassword();
            row_ad_userList[i++] = obj.getType();
            mdl_ad_userList.addRow(row_ad_userList);
        }
    }
    // search icin
    public void loadUserModel(ArrayList<User> list){
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
