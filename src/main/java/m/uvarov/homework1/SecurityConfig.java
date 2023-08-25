package m.uvarov.homework1;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserServiceImpl userService;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .formLogin().disable()
                .logout().disable()
                .authorizeRequests().antMatchers("/users/register", "/health/").permitAll()
                .anyRequest().authenticated()
                .and().httpBasic();

    }

    /**
     *  if you want more complex configuration with login, logout, remember me, and more complex authorization user.
     *  for login page, you can use thymeleaf to create your owen login page, or you can use default login page.
     *
     *      @Override
     *     protected void configure(HttpSecurity http) throws Exception {
     *         http
     *                 .authorizeRequests()
     *                 .antMatchers("/index.html").permitAll()
     *                 .antMatchers("/profile/**").authenticated()
     *                 .antMatchers("/admin/**").hasRole("ADMIN")
     *                 .antMatchers("/management/**").hasAnyRole("ADMIN", "MANAGER")
     *                 .antMatchers("/api/public/test1").hasAuthority("ACCESS_TEST1")
     *                 .antMatchers("/api/public/test2").hasAuthority("ACCESS_TEST2")
     *                 .antMatchers("/api/public/users").hasRole("ADMIN")
     *                 .and()
     *                 .formLogin()
     *                 .loginProcessingUrl("/signin")
     *                 .loginPage("/login").permitAll()
     *                 .usernameParameter("txtUsername")
     *                 .passwordParameter("txtPassword")
     *                 .and()
     *                 .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
     *                 .and()
     *                 .rememberMe().tokenValiditySeconds(2592000).key("mySecret!").rememberMeParameter("checkRememberMe");
     *     }
     * */

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }

}