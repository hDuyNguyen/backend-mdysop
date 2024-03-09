package com.project.mdyshop.service.imp;

import com.project.mdyshop.dto.request.AddressRequest;
import com.project.mdyshop.model.Address;
import com.project.mdyshop.model.User;
import com.project.mdyshop.repository.AddressRepository;
import com.project.mdyshop.repository.UserRepository;
import com.project.mdyshop.service.AddressService;
import com.project.mdyshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImp implements AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    UserRepository userRepository;
    @Override
    public Address createAddress(AddressRequest request, User user) {
        Address address = new Address();

        address.setUser(user);
        address.setFirstName(request.getFirstName());
        address.setLastName(request.getLastName());
        address.setCity(request.getCity());
        address.setAddress(request.getAddress());
        address.setPhone(request.getPhone());

        userRepository.save(user);
        return addressRepository.save(address);
    }

    @Override
    public List<Address> getUserAddress(Long userId) {

        return addressRepository.findAddressUser(userId);
    }
}
