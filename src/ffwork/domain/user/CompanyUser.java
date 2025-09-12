package ffwork.domain.user;

import java.util.Objects;

public class CompanyUser extends User {
    String companyName;
    String taxId;

    public CompanyUser(String email, String displayName, String companyName, String taxId) {
        super(email, displayName);
        this.companyName = companyName;
        this.taxId = taxId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
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
        return super.toString() + "\n" + "Company name: " + companyName + "\n" + "TaxId: " + taxId;
    }
}
