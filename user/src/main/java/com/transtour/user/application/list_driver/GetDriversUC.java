package com.transtour.user.application.list_driver;

import com.transtour.user.application.DriversResponse;
import com.transtour.user.application.list_driver.query.ListDriversQuery;
import com.transtour.user.domain.User;
import com.transtour.user.infrastructure.persistence.jpa.DriverRepository;
import com.transtour.user.infrastructure.persistence.jpa.UserRepository;
import com.transtour.user.infrastructure.persistence.jpa.UserSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetDriversUC {
    private final UserRepository userRepository;

    private final DriverRepository driverRepository;

    public GetDriversUC(UserRepository userRepository, DriverRepository driverRepository) {
        this.userRepository = userRepository;
        this.driverRepository = driverRepository;
    }

    public DriversResponse find(ListDriversQuery query) {
        Specification<User> specification = UserSpecification.findAllUserByRol(query.getRolName());
        List<User> userList = userRepository.findAll(specification);

        //TODO:se cambia implementacion.

        return new DriversResponse(new ArrayList<>());
    }
}
