package com.edielson.service;

import java.util.List;

import com.edielson.dao.DaoFactory;
import com.edielson.dao.ReservationDao;
import com.edielson.model.Reservation;

public class ReservationService {

    private ReservationDao dao = DaoFactory.createReservationDao();

    public List<Reservation> findAll() {
        return dao.findAll();
    }

    public List<Reservation> findById(String id) {
        return dao.findById(id);
    }

    public void save(Reservation reservation) {
        dao.save(reservation);
    }

    public void update(Reservation reservation) {
        dao.update(reservation);
    }

    public void remove(Integer id) {
        dao.deleteById(id);
    }
}