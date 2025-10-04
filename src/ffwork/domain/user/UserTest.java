package ffwork.domain.user;

public class UserTest {
    public static void main(String[] args) {
        User user = new User("abc@df.com", "user     ");
        User individualUser = new IndividualUser("abcd@fg.com ", "individual user");
        User companyUser = new CompanyUser("abcdf@gh.com", "company    ", "1234566798");

        System.out.println(user);
        System.out.println(individualUser);
        System.out.println(companyUser);
    }
}
