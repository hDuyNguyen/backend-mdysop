package com.project.mdyshop.repository;

import com.project.mdyshop.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("select a from Address a where a.user.id = :userId")
    List<Address> findAddressUser(@Param("userId") Long userId);
}
