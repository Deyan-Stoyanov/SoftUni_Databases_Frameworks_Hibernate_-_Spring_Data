package com.demo.services.impl;

import com.demo.dao.AddressDao;
import com.demo.model.entities.Address;
import com.demo.services.api.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    private final AddressDao addressDao;

    @Autowired
    public AddressServiceImpl(AddressDao addressDao) {
        this.addressDao = addressDao;
    }

    @Override
    public void registerAddress(Address address) {
        this.addressDao.saveAndFlush(address);
    }
}
