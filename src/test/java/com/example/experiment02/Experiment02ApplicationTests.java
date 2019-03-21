package com.example.experiment02;

import com.example.experiment02.entity.User;
import com.example.experiment02.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
@SpringBootTest

public class Experiment02ApplicationTests {
    @Autowired
    UserRepository userRepository;
    @Test
    public void contextLoads() {

        //userRepository.addAdress();
        //userRepository.updaUser(1, "flj-update");
        //userRepository.updaUser2(1, "fffff");
        //userRepository.updateAddress(1, 2);
        //userRepository.updateAddress2(1, 1);
        //userRepository.listAddresses(3);
        //userRepository.removeAddress(2);
        userRepository.removeUser(2);
    }

}
