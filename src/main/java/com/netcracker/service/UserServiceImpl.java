package com.netcracker.service;

import com.netcracker.jpa.CarInfo;
import com.netcracker.jpa.User;
import com.netcracker.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Iterable<User> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        User newUser;
        if (user.getCarInfo() == null){
            newUser = user;
            newUser.setCarInfo(new CarInfo());
        return usersRepository.save(newUser);
        }   else{
            newUser = user;
            return usersRepository.save(newUser);
        }

    }

    @Override
    public User getUser(int userId) {
        return usersRepository.findOne(userId);
    }

    @Override
    public User updateUser(int oldUserId, User newUser) {
        User oldUser = usersRepository.findOne(oldUserId);
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
            if ((newUser.getCarInfo().getTireType() != null) && !(newUser.getCarInfo().getTireType().equals(""))){
                oldUser.getCarInfo().setTireType(newUser.getCarInfo().getTireType());
            }
        }
        return usersRepository.save(oldUser);
    }

    @Override
    public void deleteUser(int userId) {
        usersRepository.delete(userId);
    }
}
