package com.shinhands.mu.Stationary.service.serviceImpl;


import com.shinhands.mu.Stationary.dto.UserDTO;
import com.shinhands.mu.Stationary.entity.UserWebsite;
import com.shinhands.mu.Stationary.repository.UserLoginRepository;
import com.shinhands.mu.Stationary.repository.UserRepository;
import com.shinhands.mu.Stationary.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Resource
    private UserLoginRepository userLoginRepository;

    @Override
    public List<UserDTO> getAllUsers() {
        return mapper.map(userRepository.findAllByDeletedEquals(0L), new TypeToken<List<UserDTO>>(){}.getType());
    }

    @Override
    public Boolean addUser(UserDTO userDTO) {
        UserWebsite userWebsite = mapper.map(userDTO, UserWebsite.class);
        userWebsite.setDeleted(0L);
        if(userRepository.save(userWebsite) != null) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteUser(long id) {
        UserWebsite oldUser=userRepository.findByIdEqualsAndDeletedEquals(id,0L);
        if (oldUser!=null){
            oldUser.setDeleted(1L);
            userRepository.save(oldUser);
            return true;
        } else return false;
    }

    @Override
    public UserDTO getUserById(long id) {
        UserWebsite oldUser=userRepository.findByIdEqualsAndDeletedEquals(id,0L);
        return mapper.map(oldUser, UserDTO.class);
    }

    @Override
    public Boolean updateUser(long id, UserDTO userDTO) {
        UserWebsite oldUser=userRepository.findByIdEqualsAndDeletedEquals(id,0L);
        if(oldUser==null )
        {
            return false;
        }
        else
        {
            userRepository.save(mapper.map(userDTO,UserWebsite.class));
        }
        return true;
    }

    @Override
    public Boolean checkAdmin(String email, String password) {
        if(userLoginRepository.checkAdmin(email, password) != 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public UserDTO getUserByAccount(String email, String password) {
        return userLoginRepository.getUserByAccount(email, password);
    }
}
