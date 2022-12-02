package com.leadexperience.leadexperince.service;


import com.leadexperience.leadexperince.models.StatusCodeModel;
import com.leadexperience.leadexperince.models.UserDataModel;
import com.leadexperience.leadexperince.respository.UserDataRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Component
@Service
public class ApiService {
    private final static String NO_DATA = "Record not found!";
    private final static String RECORD_FOUND = "Record Found Successfully !";
    private UserDataRepository userDataRepository;

    public ApiService(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    public ResponseEntity<?> signupUser(UserDataModel userDataModel) {
        // Getting all the initial traffic
        Optional<UserDataModel> userDataModelOptional= userDataRepository.findbyNumber(userDataModel.getNumber());

        if(userDataModelOptional.isPresent()){
            return new ResponseEntity<>(new StatusCodeModel("fail",400,"Already Existing user !"), HttpStatus.BAD_REQUEST);
        }else {
            userDataRepository.save(userDataModel);
            return new ResponseEntity<>(new StatusCodeModel("success", 200, "Sign up done successfully !"), HttpStatus.OK);
        }

    }
    public UserDataModel loginUser(String number, String password) {

        Optional<UserDataModel> signupModelOptional = userDataRepository.loginUser(number, password);
        if (signupModelOptional.isPresent()) {

            Optional<UserDataModel> optionalSignupModel = userDataRepository.loginUserData(number, password);
            return optionalSignupModel.get();
        } else {
            return new UserDataModel();
        }
    }

    public ResponseEntity<?> loginUserJWT(String number, String password) {

        Optional<UserDataModel> signupModelOptional = userDataRepository.loginUser(number, password);
        if (signupModelOptional.isPresent()) {

            Optional<UserDataModel> optionalSignupModel = userDataRepository.loginUserData(number, password);
            return ResponseEntity.ok(optionalSignupModel.get());
        } else {
            return ResponseEntity.badRequest().body(new StatusCodeModel("fail", 400, "User not found"));
        }
    }



}
