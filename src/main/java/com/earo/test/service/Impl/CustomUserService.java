package com.earo.test.service.Impl;

import com.earo.test.DAO.SysUserRepository;
import com.earo.test.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by lauearo on 15/05/2017.
 */
public class CustomUserService implements UserDetailsService {
    @Autowired
    SysUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SysUser user = userRepository.findByUsername(s);
        if(user.getUsername() == null){
            throw new UsernameNotFoundException("username not found");
        }

        return user;
    }
}
