package com.ra.service;

import com.ra.dto.request.UserRequestDTO;
import com.ra.dto.response.UserResponseDTO;
import com.ra.model.Role;
import com.ra.model.User;
import com.ra.repository.UserReponsitory;
import com.ra.security.jwt.JWTProvider;
import com.ra.security.principle.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserReponsitory userReponsitory;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleService roleService;
    @Autowired
    private AuthenticationProvider authenticationProvider;
    @Autowired
    private JWTProvider jwtProvider;
    @Override
    public User register(User user) {
        //ma hoa mat khau
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //role
        Set<Role> roles=new HashSet<>();
        if (user.getRoles()==null ||user.getRoles().isEmpty()){
            roles.add(roleService.findByRoleName("USER"));
        }else {
            user.getRoles().forEach(role -> {
                roles.add(roleService.findByRoleName(role.getName()));
            });
        }
        User newUser=new User();
        newUser.setUserName(user.getUserName());
        newUser.setEmail(user.getEmail());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setStatus(user.getStatus());
        newUser.setRoles(roles);
        return  userReponsitory.save(newUser);
    }

    @Override
    public UserResponseDTO login(UserRequestDTO userRequestDTO) {

        Authentication authentication;
        authentication=authenticationProvider
                .authenticate(new UsernamePasswordAuthenticationToken(userRequestDTO.getUserName(),userRequestDTO.getPassword()));
        UserPrinciple userPrinciple= (UserPrinciple) authentication.getPrincipal();

        System.out.println(userPrinciple.getUsername());
        return UserResponseDTO.builder().
                token(jwtProvider.generateToken(userPrinciple)).userName(userPrinciple.getUsername())
                .roles(userPrinciple.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet())).build();
    }
}
