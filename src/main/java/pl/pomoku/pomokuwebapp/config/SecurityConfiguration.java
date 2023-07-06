package pl.pomoku.pomokuwebapp.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import pl.pomoku.pomokuwebapp.service.CustomOAuth2UserService;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final UserAuthProvider userAuthProvider;
    private final CustomOAuth2UserService oAuth2UserService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(
                        new JwtAuthFilter(userAuthProvider),
                        BasicAuthenticationFilter.class
                )
                .sessionManagement(customizer -> customizer
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((request) ->
                        request
                                .requestMatchers(HttpMethod.POST,
                                        "/login",
                                        "/register",
                                        "/reset-password-request",
                                        "/verify-token",
                                        "/change-password",
                                        "/oauth/**",
                                        "/v2/api-docs"
                                )
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .cors(Customizer.withDefaults());
        return http.build();
    }
}
