package com.caseStudy.ShopAll.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void globalSecurityConfig(AuthenticationManagerBuilder auth) throws Exception{
        auth.
                jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select email,password,active from users where email = ?")
                .authoritiesByUsernameQuery("select email,role from users where email = ?");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .authorizeRequests().antMatchers(HttpMethod.OPTIONS , "/**").permitAll()
                .antMatchers("/enterUsers").permitAll()
                .antMatchers("/allProducts").permitAll()
                .antMatchers("/allProducts/{category}").permitAll()
                .antMatchers("/products/{productId}").permitAll()
                .antMatchers("/allProducts/{min}/{max}").permitAll()
                .antMatchers("/allProducts/{category}/{min}/{max}").permitAll()
                .antMatchers("/search/{searchedItem}").permitAll()
                .anyRequest().authenticated()
                .and().httpBasic();
        http.cors();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
