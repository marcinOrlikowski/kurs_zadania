package ffwork.repo;

import ffwork.domain.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryUserRepository implements UserRepository {
    private List<User> users = new ArrayList<>();

    @Override
    public void add(User user) {
        validateNull(user);
        validateIfUserAlreadyExists(user);
        users.add(user);
    }

    private void validateIfUserAlreadyExists(User user) {
        findByEmail(user.getEmail())
                .ifPresent(p -> {
                    throw new IllegalArgumentException("Object with such email already exists");
                });
    }

    private static void validateNull(User user) {
        if (user == null) {
            throw new NullPointerException("Cannot add null reference");
        }
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
