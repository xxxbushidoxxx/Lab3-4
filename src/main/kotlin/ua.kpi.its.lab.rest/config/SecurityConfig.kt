package ua.kpi.its.lab.rest.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import java.security.AuthProvider


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
    prePostEnabled = true,
    securedEnabled = true,
    jsr250Enabled = true
)
class SecurityConfig {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain =
        http
            .authorizeHttpRequests { authorize ->
                authorize.requestMatchers("/signup").permitAll()
                    .requestMatchers("/admin/**").hasAuthority("EDITOR")
                    .anyRequest().authenticated()
            }
            .httpBasic(Customizer.withDefaults())
            .csrf().disable()
            .build()

    @Bean
    fun userDetailsService(): UserDetailsService {
        val userDetailsService = InMemoryUserDetailsManager()

        val editorUser = User.withUsername("editor")
            .password("editorpassword")
            .authorities("EDITOR")
            .build()

        val viewerUser = User.withUsername("viewer")
            .password("viewerpassword")
            .authorities("VIEWER")
            .build()

        userDetailsService.createUser(editorUser)
        userDetailsService.createUser(viewerUser)

        return userDetailsService
    }
    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder(12)

    @Bean
    fun authProvider(userService: UserDetailsService, passwordEncoder: PasswordEncoder): AuthenticationProvider =
        AuthProvider(userService, passwordEncoder)

    @Bean
    fun authManager(http: HttpSecurity, authenticationProvider: AuthenticationProvider): AuthenticationManager =
        http.getSharedObject(AuthenticationManagerBuilder::class.java).also {
            it.authenticationProvider(authenticationProvider)
        }.build()
}