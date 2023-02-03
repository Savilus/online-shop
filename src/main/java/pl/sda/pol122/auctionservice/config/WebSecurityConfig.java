package pl.sda.pol122.auctionservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class WebSecurityConfig {

    @Autowired
    DataSource dataSource;


    @Bean
    public UserDetailsManager users(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
                        .requestMatchers(
                                "/", "/about", "/index", "/why","/shop", "/product/details/*", "/shop/*" ,
                                "/cart", "/css/*","/images/kategorie/electronic/*", "/images/kategorie/house/*",
                                "/images/kategorie/sport/*" ,"/images" , "/images/*","/fonts/*", "/js/*", "/shop/allProducts/*",
                                "/signUp")
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .formLogin(form -> form.loginPage("/login").permitAll())
                .logout((logout) -> logout.permitAll())
                .csrf()
                .disable()
                ;

        return http.build();
    }

}
