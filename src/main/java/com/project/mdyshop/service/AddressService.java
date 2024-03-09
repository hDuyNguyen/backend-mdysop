package com.project.mdyshop.service;

import com.project.mdyshop.dto.request.AddressRequest;
import com.project.mdyshop.model.Address;
import com.project.mdyshop.model.User;

import java.util.List;

public interface AddressService {

    Address createAddress(AddressRequest request, User user);

    List<Address> getUserAddress(Long userId);
 }
