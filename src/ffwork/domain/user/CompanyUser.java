package ffwork.domain.user;

import java.util.Objects;

public class CompanyUser extends User {
    String companyName;
    String taxId;

    private static final int MIN_COMPANY_NAME_LENGTH = 5;
    private static final int MAX_COMPANY_NAME_LENGTH = 50;
    private static final int TAX_ID_LENGTH = 10;

    public CompanyUser(String email, String companyName, String taxId) {
        super(email);
        validateCompanyName(companyName);
        validateTaxId(taxId);
        this.companyName = companyName.trim();
        this.taxId = taxId.trim();
    }

    public String getTaxId() {
        return taxId;
    }

    public String getDisplayName() {
        return companyName;
    }

    private void validateCompanyName(String displayName) {
        if (displayName == null || displayName.trim().isEmpty()) {
            throw new IllegalArgumentException("Company name cannot be empty");
        }
        if (displayName.trim().length() < MIN_COMPANY_NAME_LENGTH || displayName.trim().length() > MAX_COMPANY_NAME_LENGTH) {
            throw new IllegalArgumentException("Company name need to have 3-30 characters");
        }
    }

    private void validateTaxId(String TaxId) {
        if (TaxId == null || TaxId.trim().isEmpty()) {
            throw new IllegalArgumentException("Tax id cannot be empty");
        }
        if (TaxId.trim().length() != TAX_ID_LENGTH) {
            throw new IllegalArgumentException("Tax id need to have 10 characters");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CompanyUser that = (CompanyUser) o;
        return Objects.equals(companyName, that.companyName) && Objects.equals(taxId, that.taxId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), companyName, taxId);
    }

    @Override
    public String toString() {
        return "COMPANY: email: " + getEmail() + ", company name: " + companyName + ", taxId: " + taxId;
    }
}
