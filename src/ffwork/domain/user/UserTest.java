package ffwork.domain.user;

public class UserTest {
    public static void main(String[] args) {
        User user = new User("abc@df.com", "user");
        User individualUser = new IndividualUser("abcd@fg.com", "individual user");
        CompanyUser companyUser = new CompanyUser("abcdf@gh.com", "company", "12345667");

        System.out.println(user);
        System.out.println(individualUser);
        System.out.println(companyUser);
    }
}
