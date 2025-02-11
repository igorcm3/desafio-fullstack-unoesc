package br.edu.unoesc.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }
     
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
     
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
         
        return authProvider;
    }
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/inscricao/**").hasAnyAuthority("ALUNO")
                .antMatchers("/usuario/usuarios").hasAnyAuthority("ADMIN")
                .antMatchers("/disciplina/disciplinas").hasAnyAuthority("ADMIN", "PROFESSOR")
                .antMatchers("/disciplina/**").hasAnyAuthority("ADMIN")
                .antMatchers("/curso/cursos").hasAnyAuthority("ADMIN, ALUNO")
                .antMatchers("/curso/**").hasAnyAuthority("ADMIN")                
                .antMatchers("/", "/usuario/cadastro", "/usuario/novo").permitAll()
                .anyRequest().authenticated()
            .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("cpf")
                .passwordParameter("senha")
                .defaultSuccessUrl("/home/menu").permitAll()
            .and()
                .logout().permitAll();

    }

}
