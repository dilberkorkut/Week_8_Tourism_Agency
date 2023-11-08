package com.tourismagency.Model;

import com.tourismagency.Helper.DBConnector;
import com.tourismagency.Helper.Helper;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Hotel {
    private int id;
    private String name;
    private String city;
    private String region;
    private String address;
    private String email;
    private String phone;
    private String starRate;
    private String facilityFeatures = "";
    private String hostelType = "";

    public Hotel(int id, String name, String city, String region, String address, String email, String phone, String starRate, String facilityFeatures, String hostelType) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.region = region;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.starRate = starRate;
        this.facilityFeatures = facilityFeatures;
        this.hostelType = hostelType;
    }

    public Hotel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStarRate() {
        return starRate;
    }

    public void setStarRate(String starRate) {
        this.starRate = starRate;
    }

    public String getFacilityFeatures() {
        return facilityFeatures;
    }

    public void setFacilityFeatures(String facilityFeatures) {
        this.facilityFeatures = facilityFeatures;
    }

    public String getHostelType() {
        return hostelType;
    }

    public void sethostelType(String hostelType) {
        this.hostelType = hostelType;
    }


    public static ArrayList<Hotel> getList() {
        ArrayList<Hotel> hotelList = new ArrayList<>();
        String query = "SELECT * FROM hotels ";
        Hotel obj;

        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                obj = new Hotel();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setCity(rs.getString("city"));
                obj.setRegion(rs.getString("region"));
                obj.setAddress(rs.getString("address"));
                obj.setEmail(rs.getString("email"));
                obj.setPhone(rs.getString("phone"));
                obj.setStarRate(rs.getString("star_rate"));
                obj.setFacilityFeatures(rs.getString("facility_features"));
                obj.sethostelType(rs.getString("hostel_types"));
                hotelList.add(obj);
            }
            st.close();
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotelList;
    }


    public static ArrayList<Hotel> getList(int id){
        ArrayList<Hotel> hotelList = new ArrayList<>();
        String query = "SELECT * FROM hotels WHERE id";
        Hotel obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                obj = new Hotel();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setCity(rs.getString("city"));
                obj.setRegion(rs.getString("region"));
                obj.setAddress(rs.getString("address"));
                obj.setEmail(rs.getString("email"));
                obj.setPhone(rs.getString("phone"));
                obj.setStarRate(rs.getString("star_rate"));
                obj.setFacilityFeatures(rs.getString("facility_features"));
                obj.sethostelType(rs.getString("hostel_types"));
                hotelList.add(obj);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotelList;
    }

    // add hotel
    public static boolean add(String name, String city, String region, String address, String email, String phone, String starRate) {
        String query = "INSERT INTO hotels (name,city,region,address,email, phone,star_rate) VALUES (?,?,?,?,?,?,?) ";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, name);
            pr.setString(2, city);
            pr.setString(3, region);
            pr.setString(4, address);
            pr.setString(5, email);
            pr.setString(6, phone);
            pr.setString(7, starRate);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static ArrayList<Hotel> searchHotels(String query) throws SQLException {
        ArrayList<Hotel> hotelList = new ArrayList<>();
        Hotel obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                obj = new Hotel();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setCity(rs.getString("city"));
                obj.setRegion(rs.getString("region"));
                obj.setAddress(rs.getString("address"));
                obj.setEmail(rs.getString("email"));
                obj.setPhone(rs.getString("phone"));
                obj.setStarRate(rs.getString("star_rate"));
                obj.setFacilityFeatures(rs.getString("facility_features"));
                obj.sethostelType(rs.getString("hostel_type"));
                hotelList.add(obj);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return hotelList;
    }

    public static String searchQuery(String name, String city, String region, String starRate){
        String query = "SELECT * FROM hotels WHERE name LIKE '%{{name}}%' AND city LIKE '%{{city}}%' AND region LIKE '%{{region}}%' AND star_rate LIKE '%{{star_rate}}%'";
        query = query.replace("{{name}}", name);
        query = query.replace("{{city}}", city);
        query = query.replace("{{region}}", region);
        if(!starRate.isEmpty()){
            query += " AND star_rate = '{{star_rate}}'";
            query = query.replace("{{star_rate}}", starRate);
        }
        return query;
    }

    public static boolean update(String facilityFeatures, String hostelTypes, int hotel_id) {
        String query = "UPDATE hotels SET facility_features=?,hostel_types=? WHERE id =?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, facilityFeatures );
            pr.setString(2, hostelTypes );
            pr.setInt(3, hotel_id );
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        }
        return true;
    }

    // delete user
    public static boolean delete(int id) {
        String query = "DELETE FROM hotels WHERE id = ?";
        try {
            PreparedStatement pr =DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1 , id);
            //ArrayList<Hotel> hotelList = Hotel.getList(id);
//            for (Hotel hotel : hotelList) {
//                Hotel.delete(hotel.getId());
//            }
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public static Hotel getFetch(int id){
        Hotel obj = null;
        String query = "SELECT * FROM hotels WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                obj = new Hotel();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setCity(rs.getString("city"));
                obj.setRegion(rs.getString("region"));
                obj.setAddress(rs.getString("address"));
                obj.setEmail(rs.getString("email"));
                obj.setPhone(rs.getString("phone"));
                obj.setStarRate(rs.getString("star_rate"));
                obj.setFacilityFeatures(rs.getString("facility_features"));
                obj.sethostelType(rs.getString("hostel_types"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }
}
