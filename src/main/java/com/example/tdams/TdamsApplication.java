package com.example.tdams;

import com.example.tdams.model.Role;
import com.example.tdams.model.UserC;
import com.example.tdams.service.RoleService;
import com.example.tdams.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@SpringBootApplication
public class TdamsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TdamsApplication.class, args);
    }
    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    CommandLineRunner run(RoleService roleService, UserService userService){
        return args -> {
            Role role_admin = new Role();
            role_admin.setName("ROLE_ADMIN");
            Role role_cust = new Role();
            role_cust.setName("ROLE_CUSTOMER");
            Role role_vendor = new Role();
            role_vendor.setName("ROLE_VENDOR");
            Role role_delp = new Role();
            role_delp.setName("ROLE_DELIVERY_PERSONNEL");

            List<Role> roles = new ArrayList<>();
            roles.add(role_admin);
            roles.add(role_cust);
            roles.add(role_vendor);
            roles.add(role_delp);

            roleService.addRoles(roles);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            formatter = formatter.withLocale(Locale.US);
            LocalDate dob = LocalDate.parse("1997-08-21", formatter);

            UserC userC = new UserC();
            userC.setFirstName("Debasis");
            userC.setLastName("Buxy");
            userC.setMob("7596893541");
            userC.setDob(dob);
            userC.setUsername("admin");
            userC.setPassword("admin");
            userC.setRole(role_admin);

            userService.saveUser(userC);
        };
    }
}
