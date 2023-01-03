package com.redeyesncode.write.controllers;


import com.redeyesncode.write.models.LoginRequestBody;
import com.redeyesncode.write.models.UserDataModel;
import com.redeyesncode.write.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
    @RequestMapping("/redeyesNcode")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;



    @Autowired
    private ApiService apiService;




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
