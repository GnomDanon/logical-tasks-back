package ru.tasks.logical.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.tasks.logical.user.entity.Role;
import ru.tasks.logical.user.entity.User;
import ru.tasks.logical.user.exception.EmailAlreadyExistsException;
import ru.tasks.logical.user.exception.UserNotFoundException;
import ru.tasks.logical.user.repository.UserRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * Сохранение пользователя
     *
     * @param user пользователь
     * @return сохраненный пользователь
     */
    public User save(User user) {
        return userRepository.save(user);
    }

    /**
     * Создание пользователя
     *
     * @param user пользователь
     * @return созданный пользователь
     * @throws EmailAlreadyExistsException ошибка при вводе уже зарегистрированного email
     */
    public User create(User user) throws EmailAlreadyExistsException {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistsException("Пользователь с таким email уже существует");
        }
        return save(user);
    }

    /**
     * Получение пользователя по email
     * 
     * @param email адрес почты пользователя
     * @return пользователь
     */
    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь с таким email не найден"));
    }

    public User getById(UUID id) throws UserNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    /**
     * Получение пользователя по email. Нужен для Spring Security
     *
     * @return пользователь
     */
    public UserDetailsService userDetailsService()  {
        return this::getByEmail;
    }

    /**
     * Получение текущего пользователя
     * 
     * @return текущий пользователь
     */
    public User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByEmail(email);
    }

    @Deprecated
    public void getAdmin() {
        User user = getCurrentUser();
        user.setRole(Role.ROLE_ADMIN);
        save(user);
    }
}
