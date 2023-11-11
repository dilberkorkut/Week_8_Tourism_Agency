package com.tourismagency.View;

import com.tourismagency.Helper.Config;
import com.tourismagency.Helper.Helper;
import com.tourismagency.Model.Hostel;
import com.tourismagency.Model.Hotel;
import com.tourismagency.Model.HotelSeason;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FeaturesGUI extends JFrame{

    private final Hotel hotel;
    private JPanel wrapper;
    private JButton btn_features_select;
    private JCheckBox chk_add_freePark;
    private JCheckBox chk_add_freeWiFi;
    private JCheckBox chk_add_pool;
    private JCheckBox chk_add_fitness;
    private JCheckBox chk_add_concierge;
    private JCheckBox chk_add_spa;
    private JCheckBox chk_add_roomService;
    private JCheckBox chk_add_ultra_all_in;
    private JCheckBox chk_add_allin;
    private JCheckBox chk_add_breakfast;
    private JCheckBox chk_add_fullBoard;
    private JCheckBox chk_add_halfBoard;
    private JCheckBox chk_add_onlyRoom;
    private JCheckBox chk_add_full_credit;


    public FeaturesGUI(Hotel hotel) {
        this.hotel = hotel;

        add(wrapper);
        setSize(600, 400);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);


        btn_features_select.addActionListener(e -> {
            ArrayList<JCheckBox> feature = new ArrayList<>();
            ArrayList<JCheckBox> hostel = new ArrayList<>();
            feature.add(chk_add_freePark);
            feature.add(chk_add_freeWiFi);
            feature.add(chk_add_pool);
            feature.add(chk_add_fitness);
            feature.add(chk_add_concierge);
            feature.add(chk_add_spa);
            feature.add(chk_add_roomService);
            hostel.add(chk_add_ultra_all_in);
            hostel.add(chk_add_allin);
            hostel.add(chk_add_breakfast);
            hostel.add(chk_add_fullBoard);
            hostel.add(chk_add_halfBoard);
            hostel.add(chk_add_onlyRoom);
            hostel.add(chk_add_full_credit);

            String features = "";
            String hostels = "";

            // ArrayList icinde bulunan checkbox lari kontrol eder. features adli String degiskenione ekler.
            for (JCheckBox i : feature) { // feature listesindeki her JCheckbox ogesi tek tek ele alinir.
                if (i.isSelected()) {// her JCheckbox ogesi kontrol edilir. eger secili ise
                    features = features + i.getText() + " "; //Secili olan JCheckBox ögesinin metin icerigi (i.getText()) alinarak "features" adli String degiskenine eklenir.
                }
            }

            for (JCheckBox i : hostel) {
                if (i.isSelected()) {
                    hostels = hostels + i.getText() + " ";
                    Hostel.add(hotel.getId() , i.getText());
                }
            }

            Hotel.update(features, hostels, hotel.getId()); // guncellenecek bilgiler : features, hostels, hotel.getId()
            dispose(); // FeaturesGUI penceresini kapatir.
        });
    }

}
