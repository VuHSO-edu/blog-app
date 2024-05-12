package com.vti.blogapp.service;

import com.vti.blogapp.dto.UserDto;
import com.vti.blogapp.entity.Role;
import com.vti.blogapp.form.UserCreateForm;
import com.vti.blogapp.mapper.UserMapper;
import com.vti.blogapp.repository.RoleRepository;
import com.vti.blogapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;
    @Override
    public UserDto create(UserCreateForm form) {
        var user = UserMapper.map(form);
        var encodePassword = passwordEncoder.encode(form.getPassword());
        user.setPassword(encodePassword);
        var role = roleRepository.findByType(Role.Type.EMPLOYEE);
        user.setRoles(Set.of(role));
        var saveUser = userRepository.save(user);
        return UserMapper.map(saveUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        var authorities = new ArrayList<GrantedAuthority>();
        var roles = user.getRoles();
        for (var role : roles) {
           var type = role.getType().toString();
           var authority = new SimpleGrantedAuthority(type);
           authorities.add(authority);
        }
        return new User(username, user.getPassword(), authorities);
    }
}
