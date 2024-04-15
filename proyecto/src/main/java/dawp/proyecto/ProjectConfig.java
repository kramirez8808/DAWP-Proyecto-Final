package dawp.proyecto;

// ------ EXTERNAL IMPORTS ------
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

// ------ INTERNAL IMPORTS ------

@Configuration
public class ProjectConfig implements WebMvcConfigurer {
    
    /* CAMBIO DE IDIOMA */
    /* localeResolver se utiliza para crear una sesión de cambio de idioma */
    @Bean
    public LocaleResolver localeResolver() {
        var slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.getDefault());
        slr.setLocaleAttributeName("session.current.locale");
        slr.setTimeZoneAttributeName("session.current.timezone");
        return slr;
    }
    
    /* localeChangeInterceptor se utiliza para crear un interceptor de cambio de idioma */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        var lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    /* addInterceptors se utiliza para agregar el interceptor de cambio de idioma */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
    
    //Bean para poder acceder a los Messages.properties en código...
    @Bean("messageSource")
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource= new ResourceBundleMessageSource();
        messageSource.setBasenames("messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
    
    /* SEGURIDAD */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/logout").setViewName("logout");
    }

    /* Users para TESTING */    
    @Bean
    public UserDetailsService users() {
        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}123")
                .roles("USER", "VENDEDOR", "ADMIN")
                .build();
        UserDetails sales = User.builder()
                .username("seller")
                .password("{noop}456")
                .roles("USER", "VENDEDOR")
                .build();
        UserDetails user = User.builder()
                .username("user")
                .password("{noop}789")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user, sales, admin);
    }

    /*  Bean for testing  */
    @Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
            .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
            )
            .authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/productos/", "/productos/guardar", "/productos/eliminar/**",
                "/productos/modificar/**", "/marcas/", "/marcas/guardar", "/marcas/eliminar/**",
                "/marcas/modificar/**", "/categorias/", "/categorias/guardar", "/categorias/eliminar/**",
                "/categorias/modificar/**", "/estilos/", "/estilos/guardar", "/estilos/eliminar/**",
                "/estilos/modificar/**", "/usuarios/**").hasRole("ADMIN")
                .requestMatchers("/facturar/carrito")
                .hasRole("USER")
                .anyRequest().permitAll()
                    
			)
			// .authorizeHttpRequests((authorize) -> authorize
			// 	.requestMatchers("/", "/productos/tienda/", "/productos/detalle/**", "/marcas/tienda/",
            //                     "/marcas/busqueda/", "/categorias/tienda/", "/categorias/busqueda/",
            //                     "/estilos/tienda/", "/estilos/busqueda/").permitAll()
            //     .requestMatchers("/css/**", "/js/**", "/webjars/**").permitAll()
			// )
            .formLogin(form -> form
                .loginPage("/login")
                .permitAll()
            )
            .logout(logout -> logout
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            );
            

		return http.build();
	}

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {
        build.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    //     http
    //             .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer
    //                 .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
    //             )
                
    //     		.authorizeHttpRequests((authorize) -> authorize
    //     			.requestMatchers("/", "/productos/tienda/", "/productos/detalle/**", "/marcas/tienda/",
    //                                 "/marcas/busqueda/", "/categorias/tienda/", "/categorias/busqueda/",
    //                                 "/estilos/tienda/", "/estilos/busqueda/", "/registro/**", "/refrescarBoton", "/carrito/**").permitAll()
    //                 .requestMatchers("/css/**", "/js/**", "/webjars/**").permitAll()
    //                 .requestMatchers("/productos/", "/productos/guardar", "/productos/eliminar/**",
    //                                 "/productos/modificar/**", "/marcas/", "/marcas/guardar", "/marcas/eliminar/**",
    //                                 "/marcas/modificar/**", "/categorias/", "/categorias/guardar", "/categorias/eliminar/**",
    //                                 "/categorias/modificar/**", "/estilos/", "/estilos/guardar", "/estilos/eliminar/**",
    //                                 "/estilos/modificar/**", "/usuarios/**").hasRole("ADMIN")
    //     		)
    //             .formLogin(form -> form
    //                 .loginPage("/login")
    //                 .permitAll()
    //             )
    //             .logout(logout -> logout
    //                 // .invalidateHttpSession(true)
    //                 // .deleteCookies("JSESSIONID")
    //                 .permitAll()
    //             );
    //     return http.build();
    // }
}
