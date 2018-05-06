package xyz.shuttle.filebox.frontend.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import xyz.shuttle.filebox.frontend.services.user.UserService;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConf extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bcryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/vaadinServlet/**", "/VAADIN/**", "/PUSH/**",
//                        "/UIDL/**", "/login", "/login/**", "/register",
//                        "/environment").permitAll()
//                .antMatchers("/**").fullyAuthenticated()
//                .and()
//                .csrf().disable()
//                .exceptionHandling()
//                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login/"));
        http
                .authorizeRequests()
                .antMatchers("/vaadinServlet/**", "/VAADIN/**", "/PUSH/**",
                        "/UIDL/**", "/login", "/login/**", "/register",
                        "/environment").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll();
    }

    @Bean
    public PasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean(name = "authenticationManager")
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}