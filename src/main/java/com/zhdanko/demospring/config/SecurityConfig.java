package com.zhdanko.demospring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/rental_car/client/groups", "/rental_car/client/cars/{carGroupID}", "/rental_car/client/order/{carID}", "/rental_car/client/order", "/rental_car/client/order/result", "/rental_car/client/order/result/{clientOrderID}").permitAll()
                .antMatchers("/rental_car/admin/", "/rental_car/admin/orders/page/{page}", "/rental_car/admin/cars/page/{page}", "/rental_car/admin/orders/{orderID}", "/rental_car/admin/orders/orderInfo").hasAnyRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .deleteCookies("remove")
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("admin").password(passwordEncoder().encode("secret")).roles("ADMIN");
    }
}
