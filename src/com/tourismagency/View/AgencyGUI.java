package com.tourismagency.View;

import com.tourismagency.Helper.Config;
import com.tourismagency.Helper.Helper;
import com.tourismagency.Helper.Item;
import com.tourismagency.Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class AgencyGUI extends JFrame {

    private final Agency agency ;

    private JPanel wrapper;
    private JButton btn_logout_agent;
    private JTabbedPane pnl_ag_room;
    private JScrollPane scrl_ag_hotel;
    private JTable tbl_ag_hotelList;
    private JTextField fld_ag_src_hotel_name;
    private JTextField fld_ag_src_hotelCity;
    private JTextField fld_ag_src_hotelRegion;
    private JComboBox cmb_ag_src_hotelStar;
    private JButton btn_ag_src_hotel;
    private JLabel lbl_ag_welcome;
    private JTextField fld_ag_newHotelName;
    private JTextField fld_ag_newHotelCity;
    private JTextField fld_ag_newHotelReg;
    private JTextField fld_ag_newHotelAddress;
    private JLabel fld_ag_hotel_name;
    private JTextField fld_ag_newHotelPhone;
    private JComboBox cmb_ag_newHotelStar;
    private JTextField fld_ag_hotel_id;
    private JButton btn_ag_hotel_delete;
    private JButton btn_ag_features;
    private JScrollPane scrl_ag_room;
    private JTable tbl_ag_roomList;
    private JTextField fld_ag_src_room_hotel_name;
    private JTextField fld_ag_room_check_in;
    private JTextField fld_ag_room_check_out;
    private JButton btn_ag_src_room_search;
    private JTextField fld_ag_room_add_stock;
    private JTextField fld_ag_room_add_bed;
    private JComboBox cmb_ag_add_room_room;
    private JComboBox cmb_ag_add_room_hotelName;
    private JComboBox cmb_ag_add_room_tv;
    private JComboBox cmb_ag_add_room_minibar;
    private JButton btn_ag_add_room;
    private JTextField fld_ag_room_add_room_id;
    private JButton btn_ag_delete_room;
    private JTable tbl_ag_priceList;
    private JTextField fld_ag_newEmail;
    private JTable tbl_ag_search_room_list;
    private JComboBox cmb_ag_price_season;
    private JTextField fld_ag_price_add_price;
    private JComboBox getCmb_ag_price_select_thotel;
    private JComboBox cmb_ag_price_hostel_type;
    private JComboBox cmb_ag_price_age;
    private JButton btn_ag_add_price;
    private JButton btn_ag_price_delete;
    private JTextField fld_ag_price_price_id;
    private JComboBox cmb_ag_seas_add_hotel_name;
    private JTextField fld_ag_seas_add_start_date;
    private JTextField fld_ag_seas_add_end_date;
    private JButton btn_ag_seas_add_season;
    private JButton deleteSeasonButton;
    private JTextField fld_ag_season_id;
    private JTable tbl_ag_season_list;
    private JButton btn_ag_delete_season_id;
    private JButton btn_ag_search_room_search;
    private JComboBox cmb_ag_price_select_hotel;
    private JComboBox cmb_ag_price_select_season;
    private JComboBox cmb_ag_price_select_hostel_type;
    private JComboBox cmb_ag_price_select_age;
    private JTextField fld_ag_add_price;
    private JButton btn_ag_price_add_price;
    private JButton btn_ag_price_delete_price;
    private JTextField cmb_agprice_delete_price_id;
    private JTextField fld_ag_search_room_city;
    private JTextField fld_ag_search_room_checkin;
    private JTextField fld_ag_search_room_checkout;
    private JComboBox cmb_ag_search_room_age_adult;
    private JTextField fld_search_room_selected_room_id;
    private JComboBox cmb_ag_price_select_room_type;
    private JTextField fld_ag_search_room_region;
    private JComboBox cmb_search_room_hostel_type;
    private JButton btn_search_room_reservation;
    private JComboBox cmb_ag_search_room_age_chil;
    private JComboBox cmb_ag_room_src_roomType;

    private DefaultTableModel mdl_ag_hotelList;
    private Object[] row_ag_hotelList;

    private DefaultTableModel mdl_ag_roomList;
    private Object[] row_ag_roomList;

    private DefaultTableModel mdl_ag_priceList;
    private Object[] row_ag_priceList;

    private DefaultTableModel mdl_ag_season_list;
    private Object[] row_ag_seasonList;

    private DefaultTableModel mdl_ag_search_room_list;
    private Object[] row_ag_search_room_list;

    public AgencyGUI(Agency agency) {
        this.agency = agency;

        add(wrapper);
        setSize(1350, 800);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        // Model hotelList
        lbl_ag_welcome.setText("Welcome " + agency.getName());

        mdl_ag_hotelList = new DefaultTableModel();
        Object[] col_ag_hotelList = {"Id","Name","City","Region","Address","Email","Phone","Star","Facility Features","Hostel Types"};
        mdl_ag_hotelList.setColumnIdentifiers(col_ag_hotelList);
        row_ag_hotelList = new Object[col_ag_hotelList.length];
        loadHotelModel();
        tbl_ag_hotelList.setModel(mdl_ag_hotelList);
        tbl_ag_hotelList.getTableHeader().setReorderingAllowed(false);
        tbl_ag_hotelList.getColumnModel().getColumn(0).setMaxWidth(75);
        loadHotelModel();
        // tabloda secilen otelin id sini kutucuga yazdirma
        tbl_ag_hotelList.getSelectionModel().addListSelectionListener(e -> {
            try {
                String select_hotel_id = tbl_ag_hotelList.getValueAt(tbl_ag_hotelList.getSelectedRow(), 0).toString();
                fld_ag_hotel_id.setText(select_hotel_id);
            } catch (Exception ignored){
            }
        });

        // table season list
        mdl_ag_season_list = new DefaultTableModel();
        Object[] col_ag_seasonList = {"ID","Hotel ID","Start Date","End Date","Season"};
        mdl_ag_season_list.setColumnIdentifiers(col_ag_seasonList);
        row_ag_seasonList = new Object[col_ag_seasonList.length];

        tbl_ag_season_list.setModel(mdl_ag_season_list);
        tbl_ag_season_list.getTableHeader().setReorderingAllowed(false);
        tbl_ag_season_list.getColumnModel().getColumn(0).setMaxWidth(75);
        loadSeasonModel();
        loadSeasonHotelCombo();

        tbl_ag_season_list.getSelectionModel().addListSelectionListener(e -> {
            try {
                String select_price_id = tbl_ag_season_list.getValueAt(tbl_ag_season_list.getSelectedRow(), 0).toString();
                fld_ag_season_id.setText(select_price_id);
            } catch (Exception ignored){
            }
        });


        //table room list
        mdl_ag_roomList = new DefaultTableModel();
        Object[] col_ag_roomList = {"ID","Hotel ID","Room Type","Stock","Beds","TV","Minibar"};
        mdl_ag_roomList.setColumnIdentifiers(col_ag_roomList);
        row_ag_roomList = new Object[col_ag_roomList.length];
        loadRoomModel();

        tbl_ag_roomList.setModel(mdl_ag_roomList);
        tbl_ag_roomList.getTableHeader().setReorderingAllowed(false);
        tbl_ag_roomList.getColumnModel().getColumn(0).setMaxWidth(75);
        loadRoomModel();
        loadHotelComboRoom();

        //tabloda secilen oldanin id sini kutucuga yazdirma
        tbl_ag_roomList.getSelectionModel().addListSelectionListener(e -> {
            try {
                String select_room_id = tbl_ag_roomList.getValueAt(tbl_ag_roomList.getSelectedRow(), 0).toString();
                fld_ag_room_add_room_id.setText(select_room_id);
            } catch (Exception ignored){
            }
        });

        // table price list
        //(int hotel_id, int season_id, String startDate, String endDate, String season, int hostel_id, String hostelType, String roomType, String age, String price)
        mdl_ag_priceList = new DefaultTableModel();
        Object[] col_ag_priceList = {"ID","Hotel ID","Season ID","Start Date", "End Date","Season","Hostel ID","Hostel Type","Room Type","Age","Price"};
        mdl_ag_priceList.setColumnIdentifiers(col_ag_priceList);
        row_ag_priceList = new Object[col_ag_priceList.length];
        loadPriceModel();

        tbl_ag_priceList.setModel(mdl_ag_priceList);
        tbl_ag_priceList.getTableHeader().setReorderingAllowed(false);
        tbl_ag_priceList.getColumnModel().getColumn(0).setMaxWidth(75);
        loadPriceModel();
        loadHotelComboPrice();
        //tabloda secilen oldanin fiyat id sini kutucuga yazdirma
        tbl_ag_priceList.getSelectionModel().addListSelectionListener(e -> {
            try {
                String select_price_id = tbl_ag_priceList.getValueAt(tbl_ag_priceList.getSelectedRow(), 0).toString();
                fld_ag_price_price_id.setText(select_price_id);
            } catch (Exception ignored){
            }
        });



        // TABLE - search room

        mdl_ag_search_room_list = new DefaultTableModel();
       // Object[] col_ag_searchRoom = {"Start Date","End Date","Season","ID","Hotel Name","City","Region","Address","Email","Phone","Star","Facility Features","Hostel Types","Room Type","Stock","Beds","TV","Minibar"};
        Object[] col_ag_searchRoom = {"ID" ,"Hotel Name",/*"Start Date","End Date",*/"Season","City","Region","Address","Email","Phone","Star","Facility Features","Hostel Types","Room Type","Stock","Beds","TV","Minibar"};
        mdl_ag_search_room_list.setColumnIdentifiers(col_ag_searchRoom);
        row_ag_search_room_list = new Object[col_ag_searchRoom.length];
        loadSearchRoomModel();
        tbl_ag_search_room_list.setModel(mdl_ag_search_room_list);
        tbl_ag_search_room_list.getTableHeader().setReorderingAllowed(false);
        tbl_ag_search_room_list.getColumnModel().getColumn(0).setMaxWidth(75);

        //tabloda secilen oldanin hotel id sini kutucuga yazdirma
        tbl_ag_search_room_list.getSelectionModel().addListSelectionListener(e -> {
            try {
                String select_room_id = tbl_ag_search_room_list.getValueAt(tbl_ag_search_room_list.getSelectedRow(), 0).toString();
                fld_search_room_selected_room_id.setText(select_room_id);
            } catch (Exception ignored){
            }
        });



        // TODO: 6.11.2023 combolari check


        // Hotel Management
        // search hotel
        btn_ag_src_hotel.addActionListener(e -> {
            String name = fld_ag_src_hotel_name.getText();
            String city = fld_ag_src_hotelCity.getText();
            String region = fld_ag_src_hotelRegion.getText();
            String starRate = cmb_ag_src_hotelStar.getSelectedItem().toString();

            String query = Hotel.searchQuery(name, city, region, starRate);
            try {
                ArrayList<Hotel> searchingHotel = Hotel.searchHotels(query);
                loadHotelModel(searchingHotel);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        // new hotel add butonu
        btn_ag_features.addActionListener(e -> {
            String hotelStar = cmb_ag_newHotelStar.getSelectedItem().toString();
            if(Helper.isFieldEmpty(fld_ag_newHotelName) || Helper.isFieldEmpty(fld_ag_newHotelCity) || Helper.isFieldEmpty(fld_ag_newHotelReg) || Helper.isFieldEmpty(fld_ag_newHotelAddress) || Helper.isFieldEmpty(fld_ag_newEmail) || Helper.isFieldEmpty(fld_ag_newHotelPhone)) {
                Helper.showMsg("fill");
            } else {
                if (Hotel.add(fld_ag_newHotelName.getText(), fld_ag_newHotelCity.getText(), fld_ag_newHotelReg.getText(),fld_ag_newHotelAddress.getText() ,fld_ag_newEmail.getText(), fld_ag_newHotelPhone.getText(),  hotelStar)) {
                    fld_ag_newHotelName.setText(null);
                    fld_ag_newHotelCity.setText(null);
                    fld_ag_newHotelReg.setText(null);
                    fld_ag_newHotelAddress.setText(null);
                    fld_ag_newEmail.setText(null);
                    fld_ag_newHotelPhone.setText(null);

                    int selectLastId = Hotel.getList().get(Hotel.getList().size() - 1).getId();
                    FeaturesGUI fg = new FeaturesGUI(Hotel.getFetch(selectLastId));

                    fg.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            loadHotelModel();
                            loadSeasonHotelCombo();
                            loadHotelComboRoom();
                            loadHotelComboPrice();
                            Helper.showMsg("done");
                        }
                    });
                }
            }
        });
        // delete hotel butonu
        btn_ag_hotel_delete.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_ag_hotel_id)) {
                Helper.showMsg("fill");
            } else {
                if (Helper.confirm("sure")) {
                    int hotelId = Integer.parseInt(fld_ag_hotel_id.getText());
                    if (Hotel.delete(hotelId)) {
                        Helper.showMsg("done");
                        loadHotelModel();
                        fld_ag_hotel_id.setText(null);
                    } else {
                        Helper.showMsg("error");
                    }
                }
            }
        });

        // Season Management
        // season add butonu
        btn_ag_seas_add_season.addActionListener(e -> {
            Item hotelCombo = (Item) cmb_ag_seas_add_hotel_name.getSelectedItem();
            if(Helper.isFieldEmpty(fld_ag_seas_add_start_date) || Helper.isFieldEmpty(fld_ag_seas_add_end_date)) {
                Helper.showMsg("fill");
            } else {
                if (HotelSeason.add(hotelCombo.getKey(), fld_ag_seas_add_start_date.getText(), fld_ag_seas_add_end_date.getText())) {
                    loadSeasonModel();
                    fld_ag_seas_add_start_date.setText(null); //fld alanlari sifirliyor ekledikten sonra
                    fld_ag_seas_add_end_date.setText(null);
                    Helper.showMsg("done");
                    loadSeasonHotelCombo(); // combo alanlari sifirlaniyor ekledikten sonra
                }
            }
        });
        // season delete button
        btn_ag_delete_season_id.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_ag_season_id)) {
                Helper.showMsg("fill");
            } else {
                if (Helper.confirm("sure")) {
                    int seasonId = Integer.parseInt(fld_ag_season_id.getText());
                    if (HotelSeason.delete(seasonId)) {
                        Helper.showMsg("done");
                        loadSeasonModel();
                        fld_ag_season_id.setText(null);
                    } else {
                        Helper.showMsg("error");
                    }
                }
            }
        });

        // Room Management
        // add room butonu
        btn_ag_add_room.addActionListener(e -> {
            Item hotelCombo = (Item) cmb_ag_add_room_hotelName.getSelectedItem();
            String roomType = cmb_ag_add_room_room.getSelectedItem().toString();
            String tv = cmb_ag_add_room_tv.getSelectedItem().toString();
            String minibar = cmb_ag_add_room_minibar.getSelectedItem().toString();
            if(Helper.isFieldEmpty(fld_ag_room_add_stock) || Helper.isFieldEmpty(fld_ag_room_add_bed)) {
                Helper.showMsg("fill");
            } else {
                if (Room.add(hotelCombo.getKey(), roomType, fld_ag_room_add_stock.getText(), fld_ag_room_add_bed.getText(), tv, minibar )) {
                    loadRoomModel();
                    fld_ag_room_add_stock.setText(null);
                    fld_ag_room_add_bed.setText(null);
                   // loadHotelComboRoom();
                }
            }
        });
        // delete room butonu
        btn_ag_delete_room.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_ag_room_add_room_id)) {
                Helper.showMsg("fill");
            } else {
                if (Helper.confirm("sure")) {
                    int roomId = Integer.parseInt(fld_ag_room_add_room_id.getText());
                    if (Room.delete(roomId)) {
                        Helper.showMsg("done");
                        loadRoomModel();
                        fld_ag_room_add_room_id.setText(null);
                    } else {
                        Helper.showMsg("error");
                    }
                }
            }
        });

        // Price Management
        // add price butonu
        // TODO: 5.11.2023 -
        btn_ag_price_add_price.addActionListener(e -> {
            Item selectHotelCombo = (Item) cmb_ag_price_select_hotel.getSelectedItem();
            Item seasonCombo = (Item) cmb_ag_price_select_season.getSelectedItem();
            String seasonNameCombo = cmb_ag_price_select_season.getSelectedItem().toString();

            Item hostelTypeCombo = (Item) cmb_ag_price_select_hostel_type.getSelectedItem();
            String hostelTypeNameCombo = cmb_ag_price_select_hostel_type.getSelectedItem().toString();
            String priceAgeCombo = cmb_ag_price_select_age.getSelectedItem().toString();

            String roomTypeNameCombo = cmb_ag_price_select_room_type.getSelectedItem().toString();

            if(Helper.isFieldEmpty(fld_ag_add_price)) {
                Helper.showMsg("fill");
            } else {
                //(int hotel_id, int season_id, String startDate, String endDate, String season, int hostel_id, String hostelType, String roomType, String age, String price)
                if (Price.add(selectHotelCombo.getKey(), seasonCombo.getKey(), HotelSeason.getFetch(seasonCombo.getKey()).getStartDate(),
                        HotelSeason.getFetch(seasonCombo.getKey()).getEndDate(),seasonNameCombo, hostelTypeCombo.getKey(), hostelTypeNameCombo,
                        roomTypeNameCombo,priceAgeCombo, fld_ag_add_price.getText() )) {
                    loadPriceModel();
                    //loadHotelComboPrice();
                  //  loadHostelTypePrice();

                    // TODO: 5.11.2023 buraya bakilacak loadHostelType
                    // TODO: 5.11.2023 hostel type sisteme kayitli olanlar gelsin.
                    // TODO: 5.11.2023 season sisteme kayitli olanlar gelsin
                }
            }
        });
       // delete price for price id
        btn_ag_price_delete_price.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_ag_price_price_id)) {
                Helper.showMsg("fill");
            } else {
                if (Helper.confirm("sure")) {
                    int priceId = Integer.parseInt(fld_ag_price_price_id.getText());
                    if (Price.delete(priceId)) {
                        Helper.showMsg("done");
                        loadPriceModel();
                        fld_ag_price_price_id.setText(null);
                    } else {
                        Helper.showMsg("error");
                    }
                }
            }
        });


        // price otel listesi tiklaninca sezon ve hostel otomatik yuklenecek.
        cmb_ag_price_select_hotel.addActionListener(e -> {
            loadSeasonTypePrice();
            loadRoomTypePrice();
            loadHostelTypePrice();
        });

        // SEARCH ROOM MANAGEMENT
        // search room button
        btn_ag_search_room_search.addActionListener(e -> {
            loadSearchRoomModel();
            //loadSearchHostelType();

        });
        cmb_ag_search_room_age_adult.addActionListener(e -> {
          //  loadAgeSearchRoom();

        });
        btn_search_room_reservation.addActionListener(e -> {
            //loadSearchHostelType();
        });
    }


    // hotel management-load hotelList
    public void loadHotelModel(){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_ag_hotelList.getModel();
        clearModel.setRowCount(0);
        for(Hotel obj : Hotel.getList()) {
            int i = 0;
            row_ag_hotelList[i++] = obj.getId();
            row_ag_hotelList[i++] = obj.getName();
            row_ag_hotelList[i++] = obj.getCity();
            row_ag_hotelList[i++] = obj.getRegion();
            row_ag_hotelList[i++] = obj.getAddress();
            row_ag_hotelList[i++] = obj.getEmail();
            row_ag_hotelList[i++] = obj.getPhone();
            row_ag_hotelList[i++] = obj.getStarRate();
            row_ag_hotelList[i++] = obj.getFacilityFeatures();
            row_ag_hotelList[i++] = obj.getHostelType();
            mdl_ag_hotelList.addRow(row_ag_hotelList);

        }

    }
    // HOTEL MANAGEMENT
    // hotel management-search icin search HotelModel
    public void loadHotelModel(ArrayList<Hotel> searchingHotel){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_ag_hotelList.getModel();
        clearModel.setRowCount(0);
        for(Hotel obj : Hotel.getList()) {
            int i = 0;
            row_ag_hotelList[i++] = obj.getId();
            row_ag_hotelList[i++] = obj.getName();
            row_ag_hotelList[i++] = obj.getCity();
            row_ag_hotelList[i++] = obj.getRegion();;
            row_ag_hotelList[i++] = obj.getStarRate();
            mdl_ag_hotelList.addRow(row_ag_hotelList);
        }
    }
    // SEASON MANAGEMENT
    // season management- load seasonList
    public void loadSeasonModel(){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_ag_season_list.getModel();
        clearModel.setRowCount(0);
        for(HotelSeason obj : HotelSeason.getList()) {
            int i = 0;
            row_ag_seasonList[i++] = obj.getId();
            row_ag_seasonList[i++] = obj.getHotel_id();
            row_ag_seasonList[i++] = obj.getStartDate();
            row_ag_seasonList[i++] = obj.getEndDate();
            row_ag_seasonList[i++] = obj.getEndDate();
            mdl_ag_season_list.addRow(row_ag_seasonList);
        }
    }
    // season management - kayitli otelleri yukleme - season eklerken kayitli otellerden secerek ekleme yapacagiz.
    public void loadSeasonHotelCombo() {
        cmb_ag_seas_add_hotel_name.removeAllItems();
        for (Hotel obj : Hotel.getList()) {
            cmb_ag_seas_add_hotel_name.addItem(new Item(obj.getId(), obj.getName()));
        }
    }
    //ROOM MANAGEMENT
    // room management- load roomList
    public void loadRoomModel(){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_ag_roomList.getModel();
        clearModel.setRowCount(0);
        for(Room obj : Room.getList()) {
            int i = 0;
            row_ag_roomList[i++] = obj.getId();
            row_ag_roomList[i++] = obj.getHotel_id();
            row_ag_roomList[i++] = obj.getRoomType();
            row_ag_roomList[i++] = obj.getStock();
            row_ag_roomList[i++] = obj.getBeds();
            row_ag_roomList[i++] = obj.getTv();
            row_ag_roomList[i++] = obj.getMinibar();
            mdl_ag_roomList.addRow(row_ag_roomList);
        }
    }
    // room management- kayitli otelleri yukleme - oda eklerken kayitli otellerden secerek oda ekleme yapacagiz.
    public void loadHotelComboRoom() {
        cmb_ag_add_room_hotelName.removeAllItems();
        for (Hotel obj : Hotel.getList()) {
            cmb_ag_add_room_hotelName.addItem(new Item(obj.getId(), obj.getName()));
        }
    }

    // TODO: 6.11.2023 ptice tablosu duzenle. hostel type ve sezonlar otomatik yuklenecek
    // {"ID","Hotel ID","Season","Hostel Type","Room Type","Age","Price"};
    // PRICE MANAGEMENT
    // price management- load priceList

    public void loadPriceModel(){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_ag_priceList.getModel();
        clearModel.setRowCount(0);
        for(Price obj : Price.getList()) {
            int i = 0;
            //(int hotel_id, int season_id, String startDate, String endDate, String season, int hostel_id, String hostelType, String roomType, String age, String price)
            row_ag_priceList[i++] = obj.getId();
            row_ag_priceList[i++] = obj.getHotel_id();
            row_ag_priceList[i++] = obj.getSeason_id();
            row_ag_priceList[i++] = obj.getStartDate();
            row_ag_priceList[i++] = obj.getEndDate();
            row_ag_priceList[i++] = obj.getSeason();
            row_ag_priceList[i++] = obj.getHostel_id();
            row_ag_priceList[i++] = obj.getHostelType();//obj.getHostel().getHostelType();
            row_ag_priceList[i++] = obj.getRoomType();
            row_ag_priceList[i++] = obj.getAge();
            row_ag_priceList[i++] = obj.getPrice();
            mdl_ag_priceList.addRow(row_ag_priceList);
        }
    }
    // price management - kayitli otelleri yukleme - price eklerken kayitli otellerden secerek ekleme yapacagiz.
    public void loadHotelComboPrice() {
        cmb_ag_price_select_hotel.removeAllItems();
        for (Hotel obj : Hotel.getList()) {
            cmb_ag_price_select_hotel.addItem(new Item(obj.getId(), obj.getName()));
        }
    }

    // price management - kayitli sezonlari yukleme - price eklerken kayitli sezonlar yuklenecek..
    public void loadSeasonTypePrice() {
        cmb_ag_price_select_season.removeAllItems();
        for (HotelSeason obj : HotelSeason.getList()) {
            if (cmb_ag_price_select_hotel.getSelectedItem() == null) {
                cmb_ag_price_select_season.removeAllItems();
            } else if (Hotel.getFetch(obj.getHotel_id()).getName().equals(cmb_ag_price_select_hotel.getSelectedItem().toString())) {
                cmb_ag_price_select_season.addItem(new Item(obj.getId(), obj.getSeason()));
            }
        }
    }
    // price management - kayitli pansiyon tiplerini yukleme - price eklerken kayitli pansiyon tipleri yuklenecek..
    public void loadHostelTypePrice() {
        cmb_ag_price_select_hostel_type.removeAllItems();
        for (Hostel obj : Hostel.getList()) {
            if (cmb_ag_price_select_hotel.getSelectedItem() == null) {
                cmb_ag_price_select_hostel_type.removeAllItems();
            } else if (Hotel.getFetch(obj.getHotel_id()).getName().equals(cmb_ag_price_select_hotel.getSelectedItem().toString())) {
                cmb_ag_price_select_hostel_type.addItem(new Item(obj.getId(), obj.getHostelType()));
            }
        }
    }
    // price management - kayitli oda tipi yukleme - price eklerken kayitli oda tipleri yuklenecek..
    public void loadRoomTypePrice() {
        cmb_ag_price_select_room_type.removeAllItems();
        for (Room obj : Room.getList()) {
            if (cmb_ag_price_select_hotel.getSelectedItem() == null) {
                cmb_ag_price_select_room_type.removeAllItems();
            } else if (Hotel.getFetch(obj.getHotel_id()).getName().equals(cmb_ag_price_select_hotel.getSelectedItem().toString())) {
                cmb_ag_price_select_room_type.addItem(new Item(obj.getId(), obj.getRoomType()));
            }
        }
    }



        public void loadSearchRoomModel() {
            DefaultTableModel clearModel = (DefaultTableModel) tbl_ag_search_room_list.getModel();
            clearModel.setRowCount(0);
            for(SearchRoomResult obj : SearchRoomResult.getSearchRoomResult(SearchRoomResult.searchRoomQuery(fld_ag_search_room_city.getText(),fld_ag_search_room_region.getText(),fld_ag_search_room_checkin.getText(),fld_ag_search_room_checkout.getText()))) {
                int i = 0;
                row_ag_search_room_list[i++] = obj.getHotel_id();
                row_ag_search_room_list[i++] = obj.getHotelName();
               // row_ag_search_room_list[i++] = obj.getStartDate();
               // row_ag_search_room_list[i++] = obj.getEndDate();
                row_ag_search_room_list[i++] = obj.getSeason();
                row_ag_search_room_list[i++] = obj.getCity();
                row_ag_search_room_list[i++] = obj.getRegion();
                row_ag_search_room_list[i++] = obj.getAddress();
                row_ag_search_room_list[i++] = obj.getEmail();
                row_ag_search_room_list[i++] = obj.getPhone();
                row_ag_search_room_list[i++] = obj.getStarRate();
                row_ag_search_room_list[i++] = obj.getFacilityFeatures();
                row_ag_search_room_list[i++] = obj.getHostelType();
                row_ag_search_room_list[i++] = obj.getRoomType();
                row_ag_search_room_list[i++] = obj.getStock();
                row_ag_search_room_list[i++] = obj.getBeds();
                row_ag_search_room_list[i++] = obj.getTv();
                row_ag_search_room_list[i++] = obj.getMinibar();

                mdl_ag_search_room_list.addRow(row_ag_search_room_list);
            }
        }

    // price management select age
//    public void loadAgeSearchRoom() {
//        cmb_ag_price_select_age.removeAllItems();
//        for (Price obj : Price.getList()) {
//            if (cmb_ag_price_select_hotel.getSelectedItem() == null) {
//                cmb_ag_price_select_age.removeAllItems();
//            } else if (Hotel.getFetch(obj.getHotel_id()).getName().equals(cmb_ag_price_select_hotel.getSelectedItem().toString())) {
//                cmb_ag_price_select_age.addItem(new Item(obj.getId(), obj.getAge()));
//            }
//        }
//    }








    // search room management  kayitli pansiyon tipi yukleme
//    public void loadSearchHostelType() {
//        cmb_search_room_hostel_type.removeAllItems();
//        for (Hostel obj : Hostel.getList()) {
//            if (cmb_ag_price_select_hotel.getSelectedItem() == null) {
//                cmb_search_room_hostel_type.removeAllItems();
//            } else if (Hotel.getFetch(obj.getHotel_id()).getName().equals(cmb_ag_price_select_hotel.getSelectedItem().toString())) {
//                cmb_search_room_hostel_type.addItem(new Item(obj.getId(), obj.getHostelType()));
//            }
//        }
//    }
 }

