package com.netcracker.service;

import com.netcracker.dto.UserDto;
import com.netcracker.jpa.Order;
import com.netcracker.jpa.User;
import com.netcracker.repository.AuthorizationAppRepository;
import com.netcracker.repository.OrdersRepository;
import com.netcracker.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public List<UserDto> getAllUsers() {
        Iterable<User> all = usersRepository.findAll();
        List<UserDto> list = new LinkedList<UserDto>();
        for (User user: all){
            list.add(new UserDto(user.getUserId(), user.getName(), user.getCarInfo()));
        }
        return list;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = new User(userDto.getName(), userDto.getCarInfo());
        usersRepository.save(user);
        return new UserDto(user.getUserId(), userDto.getName(), user.getCarInfo());
    }

    @Override
    public UserDto getUser(int userId) {
        User user = usersRepository.findOne(userId);
        return new UserDto(user.getUserId(), user.getName(), user.getCarInfo());
    }

    @Override
    public UserDto updateUser(int userId, UserDto newUser) {
        User oldUser = usersRepository.findOne(userId);
        if ((newUser.getName() != null) && !(newUser.getName().equals(""))){
            oldUser.setName(newUser.getName());
        }
        if (newUser.getCarInfo() != null){
            if (!newUser.getCarInfo().getCarBrand().equals("")){
                oldUser.getCarInfo().setCarBrand(newUser.getCarInfo().getCarBrand());
            }
            if (newUser.getCarInfo().getTireRadius() > 0){
                oldUser.getCarInfo().setTireRadius(newUser.getCarInfo().getTireRadius());
            }
            if (!newUser.getCarInfo().getTireType().equals("")){
                oldUser.getCarInfo().setTireType(newUser.getCarInfo().getTireType());
            }
        }
        usersRepository.save(oldUser);
        return new UserDto(oldUser.getUserId(), oldUser.getName(), oldUser.getCarInfo());
    }

    @Override
    public void deleteUser(int userId) {
        List<Order> list = usersRepository.findOne(userId).getOrders();
        User user = usersRepository.findOne(userId);
        for (Order orders: list){
            orders.setUser(null);
            user.setOrders(null);
            usersRepository.save(user);
            ordersRepository.save(orders);
        }
        authorizationAppRepository.delete(authorizationAppRepository.findProfileByUser(userId));
    }
}
