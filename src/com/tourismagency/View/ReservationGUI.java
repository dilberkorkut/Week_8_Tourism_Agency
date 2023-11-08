package com.tourismagency.View;

import com.tourismagency.Helper.Config;
import com.tourismagency.Helper.Helper;
import com.tourismagency.Model.Room;

import javax.swing.*;

public class ReservationGUI extends JFrame{
    private JPanel wrapper;
    private JTextField fld_res_name;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JButton completeReservationButton;

    private Room room;

    public ReservationGUI (Room room) {
        this.room = room;

        add(wrapper);
        setSize(900, 600);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

    }




}
