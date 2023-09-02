package com.edielson.dao;

import com.edielson.connection.DatabaseConnection;
import com.edielson.dao.ipml.GuestDaoJDBC;
import com.edielson.dao.ipml.ReservationDaoJDBC;

public class DaoFactory {
    
    public static ReservationDao createReservationDao() {
        return new ReservationDaoJDBC(DatabaseConnection.getConnection());
    }

    public static GuestDao createHospedeDao() {
        return new GuestDaoJDBC(DatabaseConnection.getConnection());
    }
}