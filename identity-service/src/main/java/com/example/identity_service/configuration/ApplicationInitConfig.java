package com.example.identity_service.configuration;

import com.example.identity_service.entity.User;
import com.example.identity_service.enums.Role;
import com.example.identity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    final PasswordEncoder passwordEncoder;

    @Bean
    @ConditionalOnProperty(prefix = "spring",
    value = "datasource.driverClassName",
    havingValue = "com.mysql.jdbc.Driver")
    ApplicationRunner applicationRunner (UserRepository userRepository){
        return args -> {
            if (userRepository.findByUsername("admin1").isEmpty()){
                var roles = new HashSet<String>();
                roles.add(Role.ADMIN.name());
                User user1 = User.builder()
                        .username("admin1")
                        .password(passwordEncoder.encode("admin1"))
                        //.roles(roles)
                        .build();

                userRepository.save(user1);
                log.warn("admin1 user has been created with default password: admin, please change it");
            }
        };
    }
}
