package generics;

import java.util.Objects;

public class Pair<T, U> {
    final T first;
    final U second;

    private Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public static <T, U> Pair<T, U> of(T first, U second) {
        return new Pair<>(first, second);
    }

    public T first() {
        return first;
    }

    public U second() {
        return second;
    }
    /*
     *   Dane:* `p = Pair.of(null, "x")`.
     **  Czynność:* sprawdź `p.first()` oraz reprezentację tekstową.
     **  Oczekiwany wynik:* `p.first()` → `null`; `toString()` zawiera `x` i nie zgłasza wyjątków.
     */

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public String toString() {
        return "Pair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }


}
