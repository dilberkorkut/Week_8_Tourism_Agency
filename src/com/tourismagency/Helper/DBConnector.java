package com.tourismagency.Helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private Connection connect = null;

    public Connection connectDB() {
        try {
            this.connect = DriverManager.getConnection(Config.DB_URL, Config.DB_USERNAME, Config.DB_PASSWORD);
            //Config sinifindan alinan baglanti bilgilerini kullanarak veri tabani baglantisi olustururuz.
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return this.connect;
    }

    public static Connection getInstance() {
        DBConnector db = new DBConnector();
        return db.connectDB();
        //bu metot veritabani baglantisi olustumak baglantiyi kullanmak isteyen diger siniflar tarafindan cagrilabilir.
    }
}
