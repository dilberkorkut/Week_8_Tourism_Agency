package com.tourismagency.Model;

import com.tourismagency.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class HotelSeason {
    private int id;
    private int hotel_id;
    private String startDate;
    private String endDate;
    private String season;

    private Hotel hotel;

    public HotelSeason(int id, int hotel_id, String startDate, String endDate) {
        this.id = id;
        this.hotel_id = hotel_id;
        this.startDate = startDate;
        this.endDate = endDate;
        
        this.hotel = Hotel.getFetch(hotel_id);
        this.season = startDate + "-" + endDate;
    }

    public HotelSeason() {

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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }


    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public static ArrayList<HotelSeason> getList() {
        ArrayList<HotelSeason> seasonList = new ArrayList<>();
        String query = "SELECT * FROM hotel_seasons ";
        HotelSeason obj;

        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                obj = new HotelSeason();
                obj.setId(rs.getInt("id"));
                obj.setHotel_id(rs.getInt("hotel_id"));
                obj.setStartDate(rs.getString("start_date"));
                obj.setEndDate(rs.getString("end_date"));
                obj.setSeason(rs.getString("season"));

                seasonList.add(obj);
            }
            st.close();
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seasonList;
    }

    public static ArrayList<HotelSeason> getList(int id) {
        ArrayList seasonList = new ArrayList<>();
        String query = "SELECT * FROM hotel_seasons ";
        HotelSeason obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                obj = new HotelSeason();
                obj.setId(rs.getInt("id"));
                obj.setHotel_id(rs.getInt("hotel_id"));
                obj.setStartDate(rs.getString("start_date"));
                obj.setEndDate(rs.getString("end_date"));
                obj.setSeason(rs.getString("season"));
                seasonList.add(obj);
            }
            st.close();
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seasonList;
    }


    public static boolean add(int hotel_id, String startDate, String endDate) {
        String query = "INSERT INTO hotel_seasons (hotel_id,start_date,end_date,season) VALUES (?,?,?,?) ";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, hotel_id);
            pr.setString(2, startDate);
            pr.setString(3, endDate);
            pr.setString(4, startDate + "-" +endDate);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean delete(int id) {
        String query = "DELETE FROM hotel_seasons WHERE id = ?";
        try {
            PreparedStatement pr =DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1 , id);
            // TODO: 5.11.2023  bu kismi acinca null hatasi aliyor. kapatinca duzeliyor.
//            ArrayList<HotelSeason> seasonList = HotelSeason.getList(id);
//            for (HotelSeason season : seasonList) {
//                HotelSeason.delete(season.getId());
//            }
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static HotelSeason getFetch(int id){
        HotelSeason obj = null;
        String query = "SELECT * FROM hotel_seasons WHERE id = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                obj = new HotelSeason();
                obj.setId(rs.getInt("id"));
                obj.setHotel_id(rs.getInt("hotel_id"));
                obj.setStartDate(rs.getString("start_date"));
                obj.setEndDate(rs.getString("end_date"));
                obj.setSeason(rs.getString("season"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

}

