package com.transtour.user.application.list_driver;

import com.transtour.user.application.DriverResponse;
import com.transtour.user.application.DriversResponse;
import com.transtour.user.application.list_driver.query.ListDriversQuery;
import com.transtour.user.domain.User;
import com.transtour.user.infrastructure.persistence.jpa.UserRepository;
import com.transtour.user.infrastructure.persistence.jpa.UserSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetDriversUC {
    private final UserRepository userRepository;

    public GetDriversUC(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public DriversResponse find(ListDriversQuery query) {
        Specification<User> specification = UserSpecification.findAllUserByRol(query.getRolName());
        List<User> userList = userRepository.findAll(specification);

        List<DriverResponse> list = userList.stream().map(user -> {
            return new DriverResponse(user.getFullName(), new ArrayList<>(), user.getDni());
        }).collect(Collectors.toList());

        return new DriversResponse(list);
    }
}
