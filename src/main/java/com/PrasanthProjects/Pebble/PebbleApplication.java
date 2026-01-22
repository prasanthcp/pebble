package com.prasanthprojects.pebble;
import com.prasanthprojects.pebble.jwt.jwtfilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableJpaAuditing
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan("com.prasanthprojects.pebble")
public class PebbleApplication {
	public static void main(String[] args) {
        SpringApplication.run(PebbleApplication.class, args);
    }

    @Autowired
    jwtfilter jwtFilter;

    @Bean
    public WebMvcConfigurer corsConfigurer() {

        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("*")
                        .allowedOrigins(
                                "http://localhost:3000",
                                "http://pebble-frontend8712.s3-website-ap-southeast-2.amazonaws.com"
                        )
                        .allowedHeaders("Authorization", "Content-Type");
            }
        };
    }

//    @Bean
//    public UserDetailsService users() {
//        return new InMemoryUserDetailsManager(
//                User.withUsername("Prasanth").password("{noop}Welcome1").roles("USER").build()
//        );
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(auth-> auth
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .requestMatchers("/auth/login").permitAll()
                .anyRequest().authenticated()
                    )
                .csrf(csrf-> csrf.disable()) //.httpBasic(Customizer.withDefaults())
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .headers(headers-> headers.frameOptions(frame->frame.disable()));

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
