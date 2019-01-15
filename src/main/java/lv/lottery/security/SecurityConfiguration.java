//package lv.lottery.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers("/lotteryAdmin", "/newLottery").hasRole("ADMIN")
//                .antMatchers("/start-registration", "stop-registration", "/choose-winner", "/stats").authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
//                .logout()
//                .logoutSuccessUrl("/logout")
//                .permitAll();
//
//        http.formLogin().defaultSuccessUrl("/lotteryAdmin.html", true);
//
//
//    }
//
//    @Autowired
//    public void ConfigureGlobal(AuthenticationManagerBuilder auth) throws Exception{
//        auth.inMemoryAuthentication()
//                .withUser("admin").password("{noop}admin").roles("ADMIN");
////                        .withUser("user")
////                        .password("password")
////                        .roles("ADMIN");
//
//
//
//    }






//    private final DefaultAuthEntryPoint entryPoint;
//    private final SecurityPropertiesBean securityProperties;
//
//    @Autowired
//    public SecurityConfiguration(DefaultAuthEntryPoint entryPoint, SecurityPropertiesBean securityProperties) {
//        this.entryPoint = entryPoint;
//        this.securityProperties = securityProperties;
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser(securityProperties.getAdminName()).password("{noop}" + securityProperties.getAdminPassword()).roles("ADMIN");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
////                .antMatchers("/lotteryAdmin.html", "/newLottery.html", "statistics.html").hasRole("ADMIN")
//                .antMatchers("/start-registration", "/stop=registration", "/choose-winner", "/stats").hasRole("ADMIN")
//                .and()
//                .httpBasic().authenticationEntryPoint(entryPoint)
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//    }
//}
