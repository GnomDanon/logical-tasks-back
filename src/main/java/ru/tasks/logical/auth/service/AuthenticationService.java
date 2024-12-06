package ru.tasks.logical.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.tasks.logical.auth.dto.JwtAuthenticationResponse;
import ru.tasks.logical.auth.dto.SignInRequest;
import ru.tasks.logical.auth.dto.SignUpRequest;
import ru.tasks.logical.user.entity.Role;
import ru.tasks.logical.user.entity.User;
import ru.tasks.logical.user.exception.EmailAlreadyExistsException;
import ru.tasks.logical.user.service.UserService;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    /**
     * Регистрация пользователя
     * @param request данные пользователя
     * @return токен
     * @throws EmailAlreadyExistsException ошибка при вводе уже зарегистрированного email
     */
    public JwtAuthenticationResponse signUp(SignUpRequest request) throws EmailAlreadyExistsException {
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(Role.ROLE_USER)
                .build();

        userService.create(user);

        String jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }

    /**
     * Аутентификация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));

        UserDetails user = userService.userDetailsService().loadUserByUsername(request.getEmail());

        String jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }
}
