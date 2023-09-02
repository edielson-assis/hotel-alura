package com.edielson.dao;

import java.util.List;

import com.edielson.model.Reservation;

public interface ReservationDao {
    
    void save(Reservation reservation);
    void update(Reservation reservation);
    void deleteById(Integer id);
    List<Reservation> findById(String id);
    List<Reservation> findAll();
}