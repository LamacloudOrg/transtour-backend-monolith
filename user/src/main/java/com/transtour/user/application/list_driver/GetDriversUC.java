package com.transtour.user.application.list_driver;

import com.transtour.user.application.DriverResponse;
import com.transtour.user.application.DriversResponse;
import com.transtour.user.application.list_driver.query.ListDriversQuery;
import com.transtour.user.domain.Car;
import com.transtour.user.domain.Driver;
import com.transtour.user.domain.User;
import com.transtour.user.infrastructure.persistence.jpa.DriverRepository;
import com.transtour.user.infrastructure.persistence.jpa.UserRepository;
import com.transtour.user.infrastructure.persistence.jpa.UserSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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

        List<DriverResponse> list = userList.stream().map(user -> {
            Optional<Driver> opt = driverRepository.findById(user.getId());
            Set<Car> cars = opt.isPresent() ? opt.get().getCars() : new HashSet<>();

            return new DriverResponse(user.getFullName(),
                    cars
                    , user.getDni());
        }).collect(Collectors.toList());

        return new DriversResponse(list);
    }
}
