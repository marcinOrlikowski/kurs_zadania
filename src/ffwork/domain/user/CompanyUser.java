package ffwork.domain.user;

public class CompanyUser extends User {
    String companyName;
    String taxId;

    public CompanyUser(String email, String displayName, String companyName, String taxId) {
        super(email, displayName);
        this.companyName = companyName;
        this.taxId = taxId;
    }
}
