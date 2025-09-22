package ffwork.repo;

import ffwork.domain.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryUserRepository implements UserRepository {
    private List<User> users = new ArrayList<>();

    @Override
    public void add(User u) {
        if (u == null) {
            throw new NullPointerException("Cannot add null reference");
        }
        findByEmail(u.getEmail())
                .ifPresent(p -> {
                    throw new IllegalArgumentException("Object with such email already exists");
                });
        users.add(u);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return users;
    }
}
