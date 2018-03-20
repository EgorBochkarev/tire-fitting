package com.netcracker.service;

import com.netcracker.jpa.CarInfo;
import com.netcracker.jpa.User;
import com.netcracker.repository.CarsInfoRepository;
import com.netcracker.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private CarsInfoRepository carsInfoRepository;

    @Override
    public Iterable<User> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        User newUser = new User(user.getName(), user.getLocation(), carsInfoRepository.save(new CarInfo(user.getCarInfo().getCarBrand(), user.getCarInfo().getTireRadius(), user.getCarInfo().getTireType())));
        return usersRepository.save(newUser);
    }

    @Override
    public User getUser(User user) {
        return user;
    }

    @Override
    public User updateUser(User oldUser, User newUser) {
        if ((newUser.getName() != null) && !(newUser.getName().equals(""))){
            oldUser.setName(newUser.getName());
        }
        if ((newUser.getLocation() != null) && !(newUser.getLocation().equals(""))){
            oldUser.setLocation(newUser.getLocation());
        }
        if (newUser.getCarInfo() != null){
            if ((newUser.getCarInfo().getCarBrand() != null) && !(newUser.getCarInfo().getCarBrand().equals(""))){
                oldUser.getCarInfo().setCarBrand(newUser.getCarInfo().getCarBrand());
            }
            if (newUser.getCarInfo().getTireRadius() >= 0){
                oldUser.getCarInfo().setTireRadius(newUser.getCarInfo().getTireRadius());
            }
            if ((newUser.getCarInfo().getTireType() == null) && !(newUser.getCarInfo().getTireType().equals(""))){
                oldUser.getCarInfo().setTireType(newUser.getCarInfo().getTireType());
            }
        }
        return usersRepository.save(oldUser);
    }

    @Override
    public void deleteUser(User user) {
        usersRepository.delete(user);
    }
}
