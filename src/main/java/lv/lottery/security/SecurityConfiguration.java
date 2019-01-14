package lv.lottery.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final SecurityPropertiesBean securityProperties;

    @Autowired
    public SecurityConfiguration(SecurityPropertiesBean securityProperties) {
        this.securityProperties = securityProperties;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(securityProperties.getUsername()).password("{noop}" + securityProperties.getPassword()).roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("lotteryAdmin.html", "statistics.html").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/start-registration", "/stop-registration",
                        "/choose-winner").hasRole("ADMIN")
                .   antMatchers(HttpMethod.GET, "/stats").hasRole("ADMIN")
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/lotteryAdmin.html")
                .loginProcessingUrl("/perform_login")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/perform_logout")
                .permitAll();
    }
}
