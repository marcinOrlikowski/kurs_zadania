package streams;

import streams.generator.HoldingGenerator;
import streams.model.*;
import streams.model.Currency;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Exercises {
    private static final List<Holding> holdings = new HoldingGenerator().generate();

    public static void main(String[] args) {
        System.out.println("=== HOLDINGS ===");
        System.out.println("Number of holdings with at least one company: " + getHoldingsWhereAreCompanies());
        System.out.println("List of all holding names (uppercased): " + getHoldingNames());
        System.out.println("Holding names as string: " + getHoldingNamesAsString());

        System.out.println("\n=== COMPANIES ===");
        System.out.println("Total number of companies in all holdings: " + getCompaniesAmount());
        System.out.println("Company names as LinkedList: " + getAllCompaniesNamesAsLinkedList());

        System.out.println("\n=== USERS ===");
        System.out.println("Total number of users in all holdings: " + getAllUserAmount());
        System.out.println("Users matching predicate (women): " + getUsersForPredicate(u -> u.getSex() == Sex.WOMAN));

        System.out.println("\n=== ACCOUNTS ===");
        Account exampleAccount = new Account(AccountType.ROR1, "1178", new BigDecimal("999.50"), Currency.USD);
        System.out.println("Example account amount in PLN: " + getAccountAmountInPLN(exampleAccount));

        System.out.println("\n=== COMPANIES (FOREACH) ===");
        System.out.println("Executing action for each company:");
        executeForEachCompany(System.out::println);

        System.out.println("\n=== RICHEST WOMAN ===");
        getRichestWoman().ifPresentOrElse(
                woman -> System.out.println("Richest woman: " + woman),
                () -> System.out.println("No woman found.")
        );

        System.out.println("\n=== FIRST N COMPANIES ===");
        Set<String> firstNCompanies = getFirstNCompany(3);
        firstNCompanies.forEach(System.out::println);

        System.out.println("\n=== USERS PER COMPANY ===");
        Map<String, List<User>> usersPerCompany = getUserPerCompany();
        usersPerCompany.forEach((company, users) ->
                System.out.println(company + " -> " + users)
        );

        System.out.println("\n=== USER SEARCH ===");
        try {
            User psikuta = getUser(u -> u.getLastName().equalsIgnoreCase("psikuta"));
            System.out.println("Found user: " + psikuta);
        } catch (IllegalArgumentException e) {
            System.out.println("User not found!");
        }

        System.out.println("\n=== ACCOUNTS MAP ===");
        Map<String, Account> accountsMap = createAccountsMap();
        accountsMap.forEach((number, account) ->
                System.out.println(number + " : " + account)
        );

        System.out.println("\n=== USER NAMES STRING ===");
        System.out.println(getUserNames());

        System.out.println("\n=== ALL USERS (Z → A) ===");
        showAllUser();

        System.out.println("\n=== CURRENCIES ===");
        System.out.println("Currencies set: " + getCurenciesSet());
    }

    /**
     * Napisz metodę, która zwróci liczbę holdingów, w których jest przynajmniej jedna firma.
     */
    public static long getHoldingsWhereAreCompanies() {
        return holdings.stream()
                .filter(holding -> !holding.getCompanies().isEmpty())
                .count();
    }

    /**
     * Napisz metodę, która zwróci nazwy wszystkich holdingów pisane wielką literą w formie listy.
     */
    public static List<String> getHoldingNames() {
        return holdings.stream()
                .map(holding -> holding.getName().toUpperCase())
                .toList();
    }

    /**
     * Zwraca nazwy wszystkich holdingów sklejone w jeden string i posortowane.
     * String ma postać: (Coca-Cola, Nestle, Pepsico)
     */
    public static String getHoldingNamesAsString() {
        String holdingNames = holdings.stream()
                .map(Holding::getName)
                .sorted()
                .reduce((s, s2) -> s + ", " + s2)
                .orElse("");
        return String.format("(%s)", holdingNames);
    }

    /**
     * Zwraca liczbę firm we wszystkich holdingach.
     */
    public static long getCompaniesAmount() {
        return holdings.stream()
                .map(Holding::getCompanies)
                .mapToLong(Collection::size)
                .sum();
    }


    /**
     * Zwraca liczbę wszystkich pracowników we wszystkich firmach.
     */
    public static long getAllUserAmount() {
        return getCompanyStream()
                .map(Company::getUsers)
                .mapToLong(Collection::size)
                .sum();
    }

    /**
     * Zwraca listę wszystkich firm jako listę, której implementacja to LinkedList. Obiektów nie przepisujemy
     * po zakończeniu działania strumienia.
     */
    public static LinkedList<String> getAllCompaniesNamesAsLinkedList() {
        return getCompanyStream()
                .map(Company::getName)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Przelicza kwotę na rachunku na złotówki za pomocą kursu określonego w enum Currency.
     */
    public static BigDecimal getAccountAmountInPLN(Account account) {
        return account
                .getAmount()
                .multiply(BigDecimal.valueOf(account.getCurrency().rate))
                .round(new MathContext(4, RoundingMode.HALF_UP));
    }

    /**
     * Zwraca imiona użytkowników w formie zbioru, którzy spełniają podany warunek.
     */
    public static Set<String> getUsersForPredicate(final Predicate<User> userPredicate) {
        return getUserStream()
                .filter(userPredicate)
                .map(u -> u.getFirstName() + " " + u.getLastName())
                .collect(Collectors.toCollection(HashSet::new));
    }

    /**
     * Dla każdej firmy uruchamia przekazaną metodę.
     */
    public static void executeForEachCompany(Consumer<Company> consumer) {
        getCompanyStream()
                .forEach(consumer);
    }

    /**
     * Wyszukuje najbogatszą kobietę i zwraca ją. Metoda musi uzwględniać to, że rachunki są w różnych walutach.
     */
    //pomoc w rozwiązaniu problemu w zadaniu: https://stackoverflow.com/a/55052733/9360524
    public static Optional<User> getRichestWoman() {
        return getUserStream()
                .filter(u -> u.getSex() == Sex.WOMAN)
                .max(Comparator.comparing(Exercises::getUserAmountInPLN));
    }

    private static BigDecimal getUserAmountInPLN(final User user) {
        return user.getAccounts()
                .stream()
                .map(Exercises::getAccountAmountInPLN)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Zwraca nazwy pierwszych N firm. Kolejność nie ma znaczenia.
     */
    private static Set<String> getFirstNCompany(final int n) {
        return getCompanyStream()
                .map(Company::getName)
                .limit(n)
                .collect(Collectors.toCollection(HashSet::new));
    }

    /**
     * Zwraca mapę firm, gdzie kluczem jest jej nazwa a wartością lista pracowników.
     */
    public static Map<String, List<User>> getUserPerCompany() {
        return getCompanyStream()
                .collect(Collectors.toMap(Company::getName, Company::getUsers));
    }

    /**
     * Zwraca pierwszego z brzegu użytkownika dla podanego warunku. W przypadku kiedy nie znajdzie użytkownika, wyrzuca
     * wyjątek IllegalArgumentException.
     */
    public static User getUser(final Predicate<User> predicate) {
        return getUserStream()
                .filter(predicate)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    /**
     * Zwraca mapę rachunków, gdzie kluczem jest numer rachunku, a wartością ten rachunek.
     */
    public static Map<String, Account> createAccountsMap() {
        return getAccoutStream()
                .collect(Collectors.toMap(
                        Account::getNumber,
                        account -> account
                ));
    }

    /**
     * Zwraca listę wszystkich imion w postaci Stringa, gdzie imiona oddzielone są spacją i nie zawierają powtórzeń.
     */
    public static String getUserNames() {
        return getUserStream()
                .map(User::getFirstName)
                .distinct()
                .reduce((s1, s2) -> s1 + " " + s2)
                .orElse("");
    }

    /**
     * Metoda wypisuje na ekranie wszystkich użytkowników (imię, nazwisko) posortowanych od z do a.
     * Zosia Psikuta, Zenon Kucowski, Zenek Jawowy ... Alfred Pasibrzuch, Adam Wojcik
     */
    public static void showAllUser() {
        getUserStream()
                .map(u -> u.getFirstName() + " " + u.getLastName())
                .sorted(Collections.reverseOrder())
                .forEach(System.out::println);
    }

    /**
     * Zwraca zbiór walut, w jakich są rachunki.
     */
    public static Set<Currency> getCurenciesSet() {
        return getAccoutStream()
                .map(Account::getCurrency)
                .collect(Collectors.toSet());
    }

    /**
     * Zwraca strumień wszystkich firm.
     */
    private static Stream<Company> getCompanyStream() {
        return holdings.stream()
                .map(Holding::getCompanies)
                .flatMap(Collection::stream);
    }

    /**
     * Tworzy strumień użytkowników.
     */
    private static Stream<User> getUserStream() {
        return getCompanyStream()
                .map(Company::getUsers)
                .flatMap(Collection::stream);
    }

    /**
     * Tworzy strumień rachunków.
     */
    private static Stream<Account> getAccoutStream() {
        return getUserStream()
                .map(User::getAccounts)
                .flatMap(Collection::stream);
    }
}
