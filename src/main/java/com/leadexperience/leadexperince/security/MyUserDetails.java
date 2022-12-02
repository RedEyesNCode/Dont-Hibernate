package com.leadexperience.leadexperince.security;

import com.leadexperience.leadexperince.models.UserDataModel;
import com.leadexperience.leadexperince.respository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class MyUserDetails  implements UserDetailsService {

    private UserDataRepository userDataRepository;


    @Autowired
    public MyUserDetails(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserDataModel> signupModelOptional = userDataRepository.findbyNumber(username);
        if(signupModelOptional.isPresent()){
            UserDataModel userDataModel = signupModelOptional.get();
            return new User(userDataModel.getNumber(), userDataModel.getPassword(), new ArrayList<>());
        }else {
            throw new UsernameNotFoundException("User Name Not Found");
        }


    }

    public void loadByNumberAndPassword(String number, String password)throws UsernameNotFoundException{
        Optional<UserDataModel> signupModelOptional = userDataRepository.loginUser(number, password);
        if(signupModelOptional.isPresent()){

            Optional<UserDataModel> optionalSignupModel = userDataRepository.loginUserData(number,password);
            return;
        }else {
            throw new IllegalArgumentException();
        }



    }
}
