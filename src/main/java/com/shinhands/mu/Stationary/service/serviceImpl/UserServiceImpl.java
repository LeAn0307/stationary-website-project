package com.shinhands.mu.Stationary.service.serviceImpl;


import com.shinhands.mu.Stationary.dto.UserDTO;
import com.shinhands.mu.Stationary.entity.UserWebsite;
import com.shinhands.mu.Stationary.repository.UserRepository;
import com.shinhands.mu.Stationary.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;
    @Override
    public List<UserDTO> getAllUsers() {
        return mapper.map(userRepository.findAllByDeletedEquals(0L), new TypeToken<List<UserDTO>>(){}.getType());
    }

    @Override
    public UserDTO addUser(UserDTO userDTO) {

        return mapper.map(userRepository.save(mapper.map(userDTO, UserWebsite.class)), UserDTO.class);
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
}
