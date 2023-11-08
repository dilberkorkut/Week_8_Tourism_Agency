package com.tourismagency.Model;

import com.tourismagency.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Hostel {
    private int id;
    private int hotel_id;
    private String hostelType;

    private Hotel hotel;

    public Hostel(int id, int hotel_id, String hostelType) {
        this.id = id;
        this.hotel_id = hotel_id;
        this.hostelType = hostelType;

        this.hotel = Hotel.getFetch(hotel_id);
    }

    public Hostel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getHostelType() {
        return hostelType;
    }

    public void setHostelType(String hostelType) {
        this.hostelType = hostelType;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public static ArrayList<Hostel> getList() {
        ArrayList<Hostel> hostelList = new ArrayList<>();
        String query = "SELECT * FROM hostel_types";
        Hostel obj;

        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                obj = new Hostel();
                obj.setId(rs.getInt("id"));
                obj.setHotel_id(rs.getInt("hotel_id"));
                obj.setHostelType(rs.getString("hostel_types"));
              //  obj.setHotel(Hotel.getFetch(obj.getHotel_id()));


                hostelList.add(obj);
            }
            st.close();
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hostelList;
    }

    public static boolean add(int hotel_id, String hostelType) {
        String query = "INSERT INTO hostel_types (hotel_id,hostel_types) VALUES (?,?)";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, hotel_id);
            pr.setString(2, hostelType);

            return pr.executeUpdate() != -1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Hostel getFetch(int id){
        Hostel obj = null;
        String query = "SELECT * FROM hostels WHERE id = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                obj = new Hostel();
                obj.setId(rs.getInt("id"));
                obj.setHotel_id(rs.getInt("hotel_id"));
                obj.setHostelType(rs.getString("hostel_types"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

}
