package ru.tasks.logical.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.tasks.logical.entity.Role;
import ru.tasks.logical.entity.User;
import ru.tasks.logical.exception.EmailAlreadyExistsException;

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
