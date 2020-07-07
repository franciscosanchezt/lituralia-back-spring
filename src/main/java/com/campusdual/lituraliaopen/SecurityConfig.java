package com.campusdual.lituraliaopen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Autowired
//    DataSource dataSource;
//
//    //Enable jdbc authentication
//    @Autowired
//    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication().dataSource(dataSource)
//            .passwordEncoder(passwordEncoder())
//            .usersByUsernameQuery("SELECT user_, password, enabled FROM lituralia.tuser where user_ = ?")
//            .authoritiesByUsernameQuery("SELECT u.user_, t.rolename FROM lituralia.tuser u\n" +
//                                        "    INNER JOIN lituralia.tuser_role tr on u.user_ = tr.user_\n" +
//                                        "    LEFT OUTER JOIN lituralia.trole t on tr.id_rolename = t.id_rolename\n" +
//                                        "    WHERE u.user_  = ?")
//        ;
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().
            authorizeRequests()
            .antMatchers("/*").permitAll()
            .antMatchers(HttpMethod.OPTIONS, "/api/**").permitAll()
//            .anyRequest().authenticated()
            .and().httpBasic();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("demo").password("{noop}demouser").roles("USER");
    }


}
