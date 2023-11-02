package com.myblog8.controller;

import com.myblog8.entity.Role;
import com.myblog8.entity.User;
import com.myblog8.payload.JWTAuthResponse;
import com.myblog8.payload.LoginDto;
import com.myblog8.payload.SignupDto;
import com.myblog8.repository.RoleRepository;
import com.myblog8.repository.UserRepository;
import com.myblog8.security.JwtTokenProvider;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    @Autowired
    private UserRepository userRepo;
    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JwtTokenProvider tokenProvider;


    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@RequestBody SignupDto signupDto){
       if(userRepo.existsByUsername(signupDto.getUsername())){
           return new ResponseEntity<>("User Already Exist",HttpStatus.BAD_REQUEST);

       }
       if(userRepo.existsByEmail(signupDto.getEmail())){
           return new ResponseEntity<>("Email Already Exist",HttpStatus.BAD_REQUEST);
       }

       User user=new User();
       user.setName(signupDto.getName());
       user.setUsername(signupDto.getUsername());
       user.setEmail(signupDto.getEmail());
       user.setPassword(passwordEncoder.encode(signupDto.getPassword()));

        Role roles = roleRepository.findByName("ROLE_ADMIN").get();

        Set<Role> role=new HashSet<>();
        role.add(roles);
        user.setRoles(role);
        User saveUser = userRepo.save(user);
        SignupDto signupDto1=new SignupDto();
        signupDto1.setName(saveUser.getName());
        signupDto1.setEmail(saveUser.getEmail());
        signupDto1.setUsername(saveUser.getUsername());

        return  new ResponseEntity<>(signupDto1,HttpStatus.CREATED);

    }



    @PostMapping("/signin")
    public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestBody LoginDto
                                                                    loginDto){
        Authentication authentication = authenticationManager.authenticate(new
                UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
// get token form tokenProvider
        String token = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JWTAuthResponse(token));
    }


    private User mapToUser(SignupDto signupDto){
        User user = modelMapper.map(signupDto, User.class);
        return user;
    }

    private SignupDto mapToUser(User user){
        SignupDto signupDto = modelMapper.map(user, SignupDto.class);
        return signupDto;
    }
}

