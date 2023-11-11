package com.tourismagency.View;

import com.tourismagency.Helper.Config;
import com.tourismagency.Helper.Helper;
import com.tourismagency.Helper.Item;
import com.tourismagency.Model.*;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ReservationGUI extends JFrame{
    private JPanel wrapper;
    private JTextField fld_res_name;
    private JTextField fld_res_phone;
    private JTextField fld_res_email;
    private JTextField fld_res_checkin;
    private JTextField fld_res_checkout;
    private JTextField fld_res_child_number;
    private JButton btn_reservation;
    private JTextField fld_res_tc;
    private JTextField fld_res_days;
    private JTextField fld_res_adult_number;
    private JTextField fld_res_hostel_type;
    private JTextField fld_res_total_price;
    private JTextField fld_res_room_type;




    private int selectedHotelId;
    private String checkinDate;
    private String checkoutDate;
    private String hotelName;
    private String roomType;
    private String days;
    private int adultNumber;
    private int childNumber;
    private String hostelType;
    private int selectedRoomId;


    private Room room;


    public ReservationGUI (AgencyGUI agencyGUI, int selectedHotelId, String checkinDate, String checkoutDate, String hotelName, String roomType, String days, int adultNumber, int childNumber, String hostelType,int selectedRoomId) {
        this.room = room;
        this.selectedHotelId = selectedHotelId;
        this.selectedRoomId = selectedRoomId;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.hotelName = hotelName;
        this.roomType = roomType;
        this.days = days;
        this.adultNumber = adultNumber;
        this.childNumber = childNumber;
        this.hostelType = hostelType;

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        add(wrapper);
        setSize(900, 800);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);


        fld_res_room_type.setText(roomType);
        fld_res_checkin.setText(checkinDate);
        fld_res_checkout.setText(checkoutDate);
        fld_res_days.setText(days);
        fld_res_adult_number.setText(String.valueOf(adultNumber));
        fld_res_child_number.setText(String.valueOf(childNumber));
        fld_res_hostel_type.setText(hostelType);


        //Degerlendirme 15
       // fiyat hesaplama
        int adultPrice = 0;
        int childPrice = 0;

        Date fieldSeasonCheckin = null;
        Date fieldSeasonCheckout = null;

        try {
            fieldSeasonCheckin = formatter.parse(checkinDate);  // tarih nesnelerinin olusturulmasi
            fieldSeasonCheckout = formatter.parse(checkoutDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for (Price obj : hotelList(selectedRoomId)) { // fiyatlarin guncellenmes
            Date dataSeasonCheckin;
            Date dataSeasonCheckout;

            try {
                dataSeasonCheckin = formatter.parse(obj.getStartDate());
                dataSeasonCheckout = formatter.parse(obj.getEndDate());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            // Price objesinin kontrolu ve atanmasi
            if (obj.getHostelType().equals(hostelType) &&
            dataSeasonCheckin.before(fieldSeasonCheckin) &&
            fieldSeasonCheckout.before(dataSeasonCheckout) &&
            obj.getRoomType().equals(Room.getFetch(selectedRoomId).getRoomType())) {
                if (obj.getAge().equals("Adult 12+")) {
                    adultPrice = Integer.parseInt(obj.getPrice());
                } else if (obj.getAge().equals("Child 4-11")) {
                    childPrice = Integer.parseInt(obj.getPrice());
                }
            }
        }
        //toplam fiyat hesaplamasi ve field alanina atanmasi
        int totalPrice = Integer.parseInt(days) * (adultPrice * adultNumber + childPrice * childNumber);
        fld_res_total_price.setText(String.valueOf(totalPrice));

        //Degerlendirme 16
        //buton - complete reservation
        btn_reservation.addActionListener(e -> {
            //GUI uzerindeki alanlardan degerlerin alinmasi
            String resRoomType = fld_res_room_type.getText();
            String resClientName = fld_res_name.getText();
            String resClientTc = fld_res_tc.getText();
            String resPhone = fld_res_phone.getText();
            String resEmail = fld_res_email.getText();
            String resCheckin = fld_res_checkin.getText();
            String resCheckout = fld_res_checkout.getText();
            String resDays = fld_res_days.getText();
            int resAdultNumber = Integer.parseInt(fld_res_adult_number.getText());
            int resChildNumber = Integer.parseInt(fld_res_child_number.getText());
            String resHostelType = fld_res_hostel_type.getText();
            String resTotalPrice = fld_res_total_price.getText();

            // bos alan kontrolu yapilir.
            if(Helper.isFieldEmpty(fld_res_room_type) || Helper.isFieldEmpty(fld_res_name) ||
            Helper.isFieldEmpty(fld_res_tc) || Helper.isFieldEmpty(fld_res_phone) ||
            Helper.isFieldEmpty(fld_res_email) || Helper.isFieldEmpty(fld_res_checkin) ||
            Helper.isFieldEmpty(fld_res_checkout) || Helper.isFieldEmpty(fld_res_days) ||
            Helper.isFieldEmpty(fld_res_adult_number) || Helper.isFieldEmpty(fld_res_child_number) ||
            Helper.isFieldEmpty(fld_res_hostel_type) || Helper.isFieldEmpty(fld_res_total_price)) {
                Helper.showMsg("fill");

            } else {
                // Reservation eklenirken
                if (Reservation.add(selectedHotelId,resRoomType,resClientName,resClientTc,resPhone,resEmail,
                        resCheckin,resCheckout,resDays,String.valueOf(resAdultNumber),String.valueOf(resChildNumber),resHostelType,
                        Integer.parseInt(resTotalPrice), selectedRoomId)) {
                    //Degerlendirme 17
                    Room.decreaseStock(selectedRoomId);

                    //tablolarin guncellenmesi
                    agencyGUI.loadReservationModel(); // reservation sonrasi reservation list modeli yukleme
                    agencyGUI.loadRoomModel(); // reservation sonrasi stok durumu , room modeli yenIden yukleme
                    agencyGUI.loadSearchRoomModel(); //reservation sonrasi stok durumu ,search room modeli yeniden yukleme
                    Helper.showMsg("done"); //Degerlendirme 20 : uygun mesajlar gonderilmesi

                    dispose(); // pencerenin kapatilmasi
                }
            }
        });
    }
    // otelin fiyat listesini getiren metod
    public ArrayList<Price> hotelList(int room_id) {
        ArrayList<Price> priceListByHotelId = new ArrayList<>();
        for (Price p : Price.getList()) {
            if (p.getHotel_id() == Room.getFetch(room_id).getHotel_id()) {
                priceListByHotelId.add(p);
            }
        }
     return priceListByHotelId;
    }



}
