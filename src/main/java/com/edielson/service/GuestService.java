package com.edielson.service;

import java.util.List;

import com.edielson.dao.DaoFactory;
import com.edielson.dao.GuestDao;
import com.edielson.model.Guest;

public class GuestService {

    private GuestDao dao = DaoFactory.createHospedeDao();

    public List<Guest> findAll() {
        return dao.findAll();
    }

    public List<Guest> findById(String id) {
        return dao.findById(id);
    }

    public void save(Guest guest) {
        dao.save(guest);
    }

    public void update(Guest guest) {
        dao.update(guest);
    }
}