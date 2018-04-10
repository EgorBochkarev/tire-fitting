package com.netcracker.service;

import com.netcracker.dto.UserWrapper;
import com.netcracker.jpa.CarInfo;
import com.netcracker.jpa.Order;
import com.netcracker.jpa.User;
import com.netcracker.repository.AuthorizationAppRepository;
import com.netcracker.repository.OrdersRepository;
import com.netcracker.repository.SpecificationForJpa;
import com.netcracker.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private AuthorizationAppRepository authorizationAppRepository;

//    @Override
//    public Iterable<User> getAllUsers() {
//        return usersRepository.findAll();
//    }
//
//    @Override
//    public User createUser(User user) {
//        if (user.getCarInfo() == null){
//            user.setCarInfo(new CarInfo());
//        }
//        return usersRepository.save(user);
//    }
//
//    @Override
//    public User getUser(int userId) {
//        return usersRepository.findOne(userId);
//    }
//
//    @Override
//    public User updateUser(int oldUserId, User newUser) {
//        User oldUser = usersRepository.findOne(oldUserId);
//        if ((newUser.getName() != null) && !(newUser.getName().equals(""))){
//            oldUser.setName(newUser.getName());
//        }
//        if ((newUser.getLocation() != null) && !(newUser.getLocation().equals(""))){
//            oldUser.setLocation(newUser.getLocation());
//        }
//        if (newUser.getCarInfo() != null){
//            if ((newUser.getCarInfo().getCarBrand() != null) && !(newUser.getCarInfo().getCarBrand().equals(""))){
//                oldUser.getCarInfo().setCarBrand(newUser.getCarInfo().getCarBrand());
//            }
//            if (newUser.getCarInfo().getTireRadius() >= 0){
//                oldUser.getCarInfo().setTireRadius(newUser.getCarInfo().getTireRadius());
//            }
//            if ((newUser.getCarInfo().getTireType() != null) && !(newUser.getCarInfo().getTireType().equals(""))){
//                oldUser.getCarInfo().setTireType(newUser.getCarInfo().getTireType());
//            }
//        }
//        return usersRepository.save(oldUser);
//    }

    @Override
    public List<UserWrapper> getAllUsers() {
        Iterable<User> all = usersRepository.findAll();
        List<UserWrapper> list = new LinkedList<UserWrapper>();
        for (User user: all){
            list.add(new UserWrapper(user.getUserId(), user.getName(), user.getLocation(), user.getCarInfo().getCarBrand(), user.getCarInfo().getTireRadius(), user.getCarInfo().getTireType()));
        }
        return list;
    }

    @Override
    public UserWrapper createUser(UserWrapper userWrapper) {
        User user = new User(userWrapper.getName(), userWrapper.getLocation());
        user.setCarInfo(new CarInfo(userWrapper.getCarBrand(), userWrapper.getTireRadius(), userWrapper.getTireType()));
        usersRepository.save(user);
        return new UserWrapper(user.getUserId(), userWrapper.getName(), userWrapper.getLocation(), user.getCarInfo().getCarBrand(), user.getCarInfo().getTireRadius(), user.getCarInfo().getTireType());
    }

    @Override
    public UserWrapper getUser(int userId) {
        User user = usersRepository.findOne(userId);
        return new UserWrapper(user.getUserId(), user.getName(), user.getLocation(), user.getCarInfo().getCarBrand(), user.getCarInfo().getTireRadius(), user.getCarInfo().getTireType());
    }

    @Override
    public UserWrapper updateUser(int oldUserId, UserWrapper newUser) {
        User oldUser = usersRepository.findOne(oldUserId);
        if ((newUser.getName() != null) && !(newUser.getName().equals(""))){
            oldUser.setName(newUser.getName());
        }
        if ((newUser.getLocation() != null) && !(newUser.getLocation().equals(""))){
            oldUser.setLocation(newUser.getLocation());
        }
            if ((newUser.getCarBrand() != null) && !(newUser.getCarBrand().equals(""))){
                oldUser.getCarInfo().setCarBrand(newUser.getCarBrand());
            }
            if (newUser.getTireRadius() >= 0){
                oldUser.getCarInfo().setTireRadius(newUser.getTireRadius());
            }
            if ((newUser.getTireType() != null) && !(newUser.getTireType().equals(""))){
                oldUser.getCarInfo().setTireType(newUser.getTireType());
            }
        usersRepository.save(oldUser);
        return new UserWrapper(oldUser.getUserId(), oldUser.getName(), oldUser.getLocation(), oldUser.getCarInfo().getCarBrand(), oldUser.getCarInfo().getTireRadius(), oldUser.getCarInfo().getTireType());
    }

    @Override
    public void deleteUser(int userId) {
        List<Order> list = usersRepository.findOne(userId).getOrders();
        User user = usersRepository.findOne(userId);
        for (Order orders : list) {
            orders.setUser(null);
            user.setOrders(null);
            usersRepository.save(user);
            ordersRepository.save(orders);
        }
        if (authorizationAppRepository.findOne(Specifications.where(SpecificationForJpa.checkRetriesUser(user))) == null) {
            usersRepository.delete(user);
        }   else
        authorizationAppRepository.delete(authorizationAppRepository.findOne(Specifications.where(SpecificationForJpa.checkRetriesUser(user))));
    }
}
