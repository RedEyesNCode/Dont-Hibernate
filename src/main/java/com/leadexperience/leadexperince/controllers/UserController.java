package com.leadexperience.leadexperince.controllers;


import com.leadexperience.leadexperince.models.LoginRequestBody;
import com.leadexperience.leadexperince.models.StatusCodeModel;
import com.leadexperience.leadexperince.models.UserDataModel;
import com.leadexperience.leadexperince.security.AuthenticationRequest;
import com.leadexperience.leadexperince.security.AuthenticationResponse;
import com.leadexperience.leadexperince.security.MyUserDetails;
import com.leadexperience.leadexperince.service.ApiService;
import com.leadexperience.leadexperince.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
    @RequestMapping("/redeyesNcode")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetails userDetailsService;

    @Autowired
    private ApiService apiService;

    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping("/hello")
    public ResponseEntity<String> getHello(){
        return ResponseEntity.ok("Hello");
    }

//    @CrossOrigin
//    @RequestMapping(value = "/authJWT", method = RequestMethod.POST)
//    public ResponseEntity<?> createJWTFinal(@RequestBody AuthenticationRequest authRequest) throws Exception{
//
//        try {
//            ResponseEntity<?> responseEntity = apiService.loginUserJWT(authRequest.getUsername(), authRequest.getPassword());
//            if(responseEntity.getStatusCodeValue()==200){
//
//                authenticationManager
//                        .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
//
//            }else {
//                return ResponseEntity.badRequest().body(new StatusCodeModel("fail", 400, "Incorrect User Name and Password Response<Entity>"));
//            }
//        }catch(BadCredentialsException e) {
//            throw new Exception("Incorrect username or password", e);
//        }
//
//
//
//        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
//        final String jwt = jwtUtil.generateToken(userDetails);
//
//        return ResponseEntity.ok(new AuthenticationResponse("success",200,"Login Successfully ! ",jwt,apiService.loginUser(authRequest.getUsername(), authRequest.getPassword())));
//    }



    @CrossOrigin
    @RequestMapping(value = "/authJWT", method = RequestMethod.POST)
    public ResponseEntity<?> loginUser(@RequestBody LoginRequestBody loginRequestBody){

        // YOU CAN PUT MNY VALIDATIONS HERE.
        
        return ResponseEntity.ok(apiService.loginUser(loginRequestBody.getUsername(), loginRequestBody.getPassword()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody UserDataModel userDataModel){

        return apiService.signupUser(userDataModel);
    }

}
