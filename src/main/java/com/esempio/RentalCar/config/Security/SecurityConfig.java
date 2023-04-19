package com.esempio.RentalCar.config.Security;

import com.esempio.RentalCar.service.CustomUserDetailsService;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("customUserDetailsService")
    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }




    @Override
    public void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());

    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }


    private static final String[] ADMIN_MATCHER =
            {
                    "/utente/profiloAdmin",
                    "/utente/nuovoUtente",
                    "/utente/eliminaUtente",
                    "/utente/listaPrenotazioni",
                    "/veicolo/insmodveicolo",
                    "/veicolo/nuovoVeicolo",
                    "/veicolo/modificaVeicolo",
                    "/veicolo/eliminaVeicolo",
                    "/prenotazioni/listaPrenotazioni"
            };

    private static final String[] CUSTOMER_MATCHER =
            {
                    "/utente/profiloCustomer",
                    "/prenotazioni/nuovaPrenotazione",
                    "/prenotazioni/modificaPrenotazione"

            };


    @Override
    protected void configure(final HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .authorizeRequests()

                .antMatchers("/resources/**").permitAll()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/index", "/Homepage", "/", "/veicolo/veicoli","/utente/registrazione").permitAll()

                .antMatchers(ADMIN_MATCHER).access("hasRole('SUPERUSER')")
                .antMatchers(CUSTOMER_MATCHER).access("hasRole('CUSTOMER')")
                .antMatchers("/utente/modificaUtente","/prenotazioni/eliminaPrenotazione").access("hasAnyRole('CUSTOMER','SUPERUSER')")

                .and()
                //gestisco la parte legata al form di login
                .addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginPage("/login/form") //Dove si trova il form di login
                .loginProcessingUrl("/login")
                .failureUrl("/login/form?error") //in caso di errore
                .usernameParameter("userId")
                .passwordParameter("password")
                .and()
                //gestisco le eccezioni quando non ho l'autorizzazione per entrare dentro una pagina della webApp
                .exceptionHandling()
                .accessDeniedPage("/login/form?forbidden")
                .and()
                //gestisco il logout
                .logout()
                .logoutUrl("/login/form?logout")
                .and().csrf().disable() //utile in fase di sviluppo ma da riattivare in fase operativa
        ;
    }

    public AuthenticationFilter authenticationFilter() throws Exception{
        AuthenticationFilter filter = new AuthenticationFilter();
        filter.setAuthenticationManager(authenticationManagerBean());
        filter.setAuthenticationFailureHandler(failureHandler());
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
        return filter;
    }

    public SimpleUrlAuthenticationFailureHandler failureHandler(){
        return new SimpleUrlAuthenticationFailureHandler("/login/form?error");
    }
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new SuccessHandler();
    }
}

