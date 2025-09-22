package ffwork.domain.user;

public class IndividualUser extends User {
    public IndividualUser(String email, String displayName) {
        super(email, displayName);
    }

    @Override
    public String toString() {
        return "INDIVIDUAL: " + super.toString();
    }
}
