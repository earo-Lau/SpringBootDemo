package com.earo.test.DAO;

import com.earo.test.model.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lauearo on 15/05/2017.
 */
public interface SysUserRepository extends JpaRepository<SysUser, Long> {
    SysUser findByUsername(String username);
}
