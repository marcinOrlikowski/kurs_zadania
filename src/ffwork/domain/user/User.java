package ffwork.domain.user;

import java.util.Objects;

public class User {
    private String email;
    private String displayName;

    public User(String email, String displayName) {
        this.email = email;
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) && Objects.equals(displayName, user.displayName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, displayName);
    }

    @Override
    public String toString() {
        return "email: " + email + "\n" + "Display name: " + displayName;
    }
}
