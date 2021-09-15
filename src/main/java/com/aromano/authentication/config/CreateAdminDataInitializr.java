package com.aromano.authentication.config;

import com.aromano.authentication.dao.RoleRepository;
import com.aromano.authentication.dao.UserRepository;
import com.aromano.authentication.entity.RoleEntity;
import com.aromano.authentication.entity.UserEntity;
import com.aromano.authentication.enums.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Arrays;
import java.util.List;

@Component
public class CreateAdminDataInitializr
        implements ApplicationListener<ContextRefreshedEvent>
{

    @Autowired
    UserRepository userRepository;

    @Autowired RoleRepository roleRepository;

    @Autowired PasswordEncoder passwordEncoder;

    @Value("${api.admin.password}")
    private String adminPass;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        List<UserEntity> users = this.userRepository.findAll();

        if (users.isEmpty()) {
            createUser("Admin", "admin", this.passwordEncoder.encode(this.adminPass), RoleEnum.ROLE_ADMIN.getValue());
        }
    }

    private void createUser(String name, String email, String password, String roleName) {

        RoleEntity role = new RoleEntity(roleName);

        this.roleRepository.save(role);
        UserEntity user = new UserEntity(name, email, password, Arrays.asList(role));
        this.userRepository.save(user);
    }
}
