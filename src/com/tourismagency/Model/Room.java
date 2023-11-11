package com.tourismagency.Model;

import com.tourismagency.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Room {
    private int id;
    private int hotel_id;
    private String roomType;
    private String stock;
    private String beds;
    private String tv;
    private String minibar;

    private String hotelName;
    private Hotel hotel;

    public Room(int id, int hotel_id, String roomType, String stock, String beds, String tv, String minibar) {
        this.id = id;
        this.hotel_id = hotel_id;
        this.roomType = roomType;
        this.stock = stock;
        this.beds = beds;
        this.tv = tv;
        this.minibar = minibar;

        this.hotel = Hotel.getFetch(hotel_id);
        this.hotelName = Hotel.getFetch(hotel_id).getName();
    }

    public Room() {

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

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getBeds() {
        return beds;
    }

    public void setBeds(String beds) {
        this.beds = beds;
    }

    public String getTv() {
        return tv;
    }

    public void setTv(String tv) {
        this.tv = tv;
    }

    public String getMinibar() {
        return minibar;
    }

    public void setMinibar(String minibar) {
        this.minibar = minibar;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }


    public static Room getFetch(int id){
        Room obj = null;
        String query = "SELECT * FROM rooms WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                obj = new Room();
                obj.setId(rs.getInt("id"));
                obj.setHotel_id(rs.getInt("hotel_id"));
                obj.setRoomType(rs.getString("room_type"));
                obj.setStock(rs.getString("stock"));
                obj.setBeds(rs.getString("beds"));
                obj.setTv(rs.getString("tv"));
                obj.setMinibar(rs.getString("minibar"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

    public static ArrayList<Room> getList() {
        ArrayList<Room> roomList = new ArrayList<>();
        String query = "SELECT * FROM rooms ";
        Room obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                obj = new Room();
                obj.setId(rs.getInt("id"));
                obj.setHotel_id(rs.getInt("hotel_id"));
                obj.setRoomType(rs.getString("room_type"));
                obj.setStock(rs.getString("stock"));
                obj.setBeds(rs.getString("beds"));
                obj.setTv(rs.getString("tv"));
                obj.setMinibar(rs.getString("minibar"));
                roomList.add(obj);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomList;
    }

    public static ArrayList<Room> getList(int id) {
        ArrayList roomList = new ArrayList<>();
        String query = "SELECT * FROM rooms ";
        Room obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                obj = new Room();
                obj.setId(rs.getInt("id"));
                obj.setHotel_id(rs.getInt("hotel_id"));
                obj.setRoomType(rs.getString("room_type"));
                obj.setStock(rs.getString("stock"));
                obj.setBeds(rs.getString("beds"));
                obj.setTv(rs.getString("tv"));
                obj.setMinibar(rs.getString("minibar"));
                roomList.add(obj);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomList;
    }

    // add room
    public static boolean add(int hotel_id, String roomType, String stock, String beds, String tv, String minibar) {
        String query = "INSERT INTO rooms (hotel_id, room_type,stock,beds,tv,minibar) VALUES (?,?,?,?,?,?) ";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, hotel_id);
            pr.setString(2, roomType);
            pr.setString(3, stock);
            pr.setString(4, beds);
            pr.setString(5, tv);
            pr.setString(6, minibar);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // delete room
    public static boolean delete(int id) {
        String query = "DELETE FROM rooms WHERE id = ?";
        try {
            PreparedStatement pr =DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1 , id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public static ArrayList<Room> getRoomList  (int hotel_id) {
        ArrayList roomList = new ArrayList<>();
        String query = "SELECT * FROM rooms WHERE hotel_id=? ";
        Room obj;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, hotel_id);
            ResultSet rs = pr.executeQuery();
            while(rs.next()){
                obj = new Room();
                obj.setId(rs.getInt("id"));
                obj.setHotel_id(rs.getInt("hotel_id"));
                obj.setRoomType(rs.getString("room_type"));
                obj.setStock(rs.getString("stock"));
                obj.setBeds(rs.getString("beds"));
                obj.setTv(rs.getString("tv"));
                obj.setMinibar(rs.getString("minibar"));
                roomList.add(obj);
            }
            pr.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomList;
    }

    public static int getRoomId(ArrayList<Room> getRoomList , String selectedRoomType){
        for (Room obj : getRoomList) {
            if (obj.getRoomType().equals(selectedRoomType)){
                return obj.getId();
            }
        }
        return 0;
    }

    public static boolean decreaseStock(int id) {
        String query = "UPDATE rooms SET stock=stock-1 WHERE id=?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);

            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public static boolean increaseStock(int id) {
        String query = "UPDATE rooms SET stock=stock+1 WHERE id=?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);

            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }


//    public static boolean update(int id, int hotel_id, String roomType, String stock, String beds, String tv, String minibar) {
//        String query = "UPDATE rooms SET hotel_id=?, room_type=?, stock=?, beds=?, tv=?, minibar=? WHERE id =?";
//
//        try {
//            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
//            pr.setInt(1, hotel_id );
//            pr.setString(2, roomType );
//            pr.setString(3, stock );
//            pr.setString(4, beds );
//            pr.setString(5, tv );
//            pr.setString(6, minibar );
//            pr.setInt(7, id );
//            return pr.executeUpdate() != -1;
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());;
//        }
//        return true;
//    }

}

