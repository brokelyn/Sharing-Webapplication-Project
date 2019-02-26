package de.hhu.propra.sharingplatform.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecConfig extends WebSecurityConfigurerAdapter {


    private final UserDetailsService userDetailsService;

    @Autowired
    public SecConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/", "/sale", "/css/**", "/images/**", "/public/images/*", "/h2/**",
                "/user/register")
            .permitAll() // h2 has to be removed in production
            .antMatchers("/conflicts/**")
            .hasRole("admin")
            .anyRequest().authenticated();
        http.formLogin().permitAll();
        http.logout().permitAll();

        http.userDetailsService(userDetailsService);

        // to be able to use the faker we need these options.
        // Later they should be removed again
        http.headers().frameOptions().disable();
        http.csrf().disable();

    }

}
