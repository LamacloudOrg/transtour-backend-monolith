package com.transtour.user.application.list_driver;

import com.transtour.user.application.DriverResponse;
import com.transtour.user.application.DriversResponse;
import com.transtour.user.application.list_driver.query.ListDriversQuery;
import com.transtour.user.domain.User;
import com.transtour.user.infrastructure.persistence.jpa.SessionRepository;
import com.transtour.user.infrastructure.persistence.jpa.UserRepository;
import com.transtour.user.infrastructure.persistence.jpa.UserSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetDriversUC {
    private final UserRepository userRepository;

    private final SessionRepository sessionRepository;

    public GetDriversUC(UserRepository userRepository, SessionRepository sessionRepository) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
    }

    public DriversResponse find(ListDriversQuery query) {
        Specification<User> specification = UserSpecification.findAllUserByRol(query.getRolName());
        List<User> userList = userRepository.findAll(specification);

        List<DriverResponse> driverResponseList = userList.stream()
                .map(user -> new DriverResponse(user.getFullName(), new HashSet<>(), user.getDni()))
                .collect(Collectors.toList());

        return new DriversResponse(driverResponseList);
    }
}
