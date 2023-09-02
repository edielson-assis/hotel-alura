package com.edielson.dao;

import java.util.List;

import com.edielson.model.Guest;

public interface GuestDao {
    
    void save(Guest guest);
    void update(Guest guest);
    List<Guest> findById(String id);
    List<Guest> findAll();
}