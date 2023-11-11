package com.tourismagency.View;

import com.tourismagency.Helper.Config;
import com.tourismagency.Helper.Helper;
import com.tourismagency.Model.Admin;
import com.tourismagency.Model.Agency;
import com.tourismagency.Model.User;

import javax.swing.*;

public class LoginGUI extends JFrame{
    private JPanel wrapper;
    private JPanel wtop;
    private JPanel wbottom;
    private JTextField fld_login_uname;
    private JButton btn_login;
    private JPasswordField fld_login_password;


    public LoginGUI() {
        add(wrapper);
        setSize(400, 600);
        setLocation(Helper.screenCenterPoint("x", getSize()) , Helper.screenCenterPoint("y" , getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);


        btn_login.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_login_uname) || Helper.isFieldEmpty(fld_login_password)) {
                Helper.showMsg("fill");
            } else {
                User user = User.getFetch(fld_login_uname.getText(), fld_login_password.getText());
                if (user == null) {
                    Helper.showMsg("User can not found!");
                } else { //Kullanici bulunursa type'a gore ekrana yonlendirme yapilir
                    switch (user.getType()) {
                        case "admin" :
                            AdminGUI adminGUI = new AdminGUI((Admin) user);
                            break;
                        case "agency" :
                            AgencyGUI agencyGUI = new AgencyGUI((Agency) user);
                            break;
                    }
                    dispose();
                }
            }
        });
    }


    public static void main(String[] args) {
        Helper.setLayout();
        LoginGUI loginGUI = new LoginGUI();
    }
}



