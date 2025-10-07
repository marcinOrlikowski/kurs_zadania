> **Model czasu:** Domyślnie implementujemy własną klasę `FFDateTime` (pola `int`: `year`, `month`, `day`, `hour`, `minute`). **Opcjonalnie** można użyć `java.time.LocalDateTime`
---

## 1) Fabuła (kontekst)
Firma **ffWork** zarządza siecią coworkingów. Potrzebuje narzędzia do:
- rejestrowania **użytkowników** (osoby i firmy),
- dodawania **zasobów** (sale, biurka, urządzenia),
- tworzenia i obsługi **rezerwacji**, cen, zniżek, płatności,
- wystawiania **faktur** oraz generowania **raportów**.

---

## 2) Kryteria OOP (co musi pojawić się w kodzie)
- **Klasy** — spójny model domeny.
- **Dziedziczenie** — wspólne zachowania i specjalizacje.
- **Dwie klasy abstrakcyjne** (min.): `Resource`, `Payment`.
- **Interfejsy** (min. 3): `PricingPolicy`, `Discountable`, `Billable` (mogą być dodatkowe, np. `Repository<T>`).
- **Overriding** (min. 5): m.in. różne `baseRatePerHour()` w zasobach, `describe()`, `Payment#capture()` i `refund()` itd.
- **Overloading** (min. 3): np. `FFDateTime.of(...)` i `parse(...)`; `BookingService.book(...)` przyjmuje (start, end) **lub** (start, durationMinutes); przeciążone konstruktory zasobów.

---

## 3) Specyfikacja klas — **wymagania minimalne**

### 3.1. Czas
#### `FFDateTime` (final, immutable, implements Comparable)
- **Pola (public final):** `int year, month, day, hour, minute`.
- **Konstruktory / fabryki (overloading):**
  - `FFDateTime(int y, int m, int d, int h, int min)` — waliduje zakresy (miesiące 1–12; dni z uwzględnieniem lat przestępnych; 0–23; 0–59).
  - `static FFDateTime of(int y, int m, int d, int h, int min)`.
  - `static FFDateTime parse(String iso)` — akceptuje dokładnie `YYYY-MM-DDTHH:MM` (np. `2025-09-15T10:00`), rzuca `IllegalArgumentException` przy błędzie.
- **Metody:**
  - `int toEpochMinutes()` — liczy minuty od stałej epoki (np. 2000-01-01T00:00).
  - `FFDateTime plusMinutes(int minutes)` — nowa instancja, poprawna arytmetyka (może przejść do kolejnego dnia/miesiąca/roku).
  - `int minutesUntil(FFDateTime other)` — różnica w minutach (other - this).
  - `int compareTo(FFDateTime o)` — porównanie po `toEpochMinutes()`.
  - `String toString()` — `YYYY-MM-DDTHH:MM`.
- **Inwarianty:** obiekt po utworzeniu zawsze reprezentuje poprawną datę i godzinę.
- **Uwaga:** Można dodać `FFTimeRange(start, end)` z metodami `minutes()` i `overlaps(...)`.

> **Opcja:** zamiast `FFDateTime` można użyć `LocalDateTime`. Wtedy proszę opisać różnice (walidacja, arytmetyka, czytelność)

### 3.2. Pieniądze
#### `Money` (value object)
- **Pola:** `BigDecimal amount`; waluta stała: PLN.
- **Konstruktory / fabryki:**
  - `Money(BigDecimal amount)` — skala 2, `RoundingMode.HALF_UP`.
  - `static Money of(String)` lub `of(double)` — **uważnie** dla double (można zabronić i zostawić `String`).
- **Metody:**
  - `Money add(Money other)`, `Money subtract(Money other)`, `Money multiply(BigDecimal m)`, `Money multiply(double m)` (opcjonalnie).
  - `int compareTo(Money other)`; `equals/hashCode`; `toString()` → `123.45 PLN`.
- **Inwariant:** kwota nigdy ujemna podczas wyliczeń końcowych (chyba że logicznie uzasadnione — wtedy jasno dokumentujemy).

### 3.3. Użytkownicy
#### `class User`
- **Pola:** `String email` (unikalne), `String displayName`.
- **Metody:** akcesory, `toString()`.
- **Podklasy:**
  - `class IndividualUser extends User` — nic dodatkowego lub pole `studentId` (opcjonalne).
  - `class CompanyUser extends User` — **pola:** `String companyName`, `String taxId` (NIP).

### 3.4. Zasoby (dziedziczenie i overriding)
#### `abstract class Resource`
- **Pola:** `String name` (unikalne), `Money customHourlyRate` (opcjonalna niestandardowa stawka).
- **Metody abstrakcyjne:** 
  - `protected abstract Money baseRatePerHour();`
  - `public abstract String describe();` — krótki opis do listowania.
- **Metody konkretne:**
  - `public Money hourlyRate()` — jeśli `customHourlyRate != null` → zwróć ją; inaczej `baseRatePerHour()`.

**Podklasy:**
- `class Room extends Resource`
  - **Pola:** `int seats`, `Set<String> equipment` (np. „projektor”, „whiteboard”).
  - **Overriding:** `baseRatePerHour()`, `describe()`.
- `class Desk extends Resource`
  - **Pola:** `enum DeskType { HOT, FIXED } type`.
  - **Overriding:** jw.
- `class Device extends Resource`
  - **Pola:** `int quantity` (ile sztuk można równolegle zarezerwować).
  - **Overriding:** jw.

### 3.5. Rezerwacje
#### `enum BookingStatus { PENDING, CONFIRMED, CANCELLED, COMPLETED }`

#### `class Booking`
- **Pola:** 
  - `String id` (np. `BK-<yyyyMMdd>-<counter>`), 
  - `User user`, 
  - `Resource resource`, 
  - `FFDateTime start`, `FFDateTime end`,
  - `BookingStatus status`,
  - `Money calculatedPrice` (suma po politykach i zniżkach),
  - `Payment payment` (może być `null`).
- **Inwarianty:** `end` > `start`; `status` zmienia się dozwolonymi przejściami (PENDING→CONFIRMED/CANCELLED; CONFIRMED→COMPLETED/CANCELLED).
- **Metody pomocnicze:** `int durationMinutes()`.

### 3.6. Polityki cen i zniżek (interfejsy + implementacje)
#### `interface PricingPolicy`
- `Money price(Booking booking)` — liczy cenę bazową *za czas trwania* na podstawie `booking.resource.hourlyRate()`.
  - **Reguła minutowa:** cena za minutę = `hourlyRate / 60`; cena = `minutes × pricePerMinute`, zaokrąglanie do 2 miejsc, `HALF_UP`.

**Implementacje:**
- `class StandardPricing implements PricingPolicy`
- `class HappyHoursPricing implements PricingPolicy`
  - **Założenia:** –30% w godzinach 14:00–16:00 (codziennie albo dni robocze — wybierzcie i opiszcie). Zniżka dotyczy tylko części rezerwacji mieszczącej się w HH **lub** całej rezerwacji. Najprościej: jeśli **start godzina** zawiera się w HH → rabat do całej rezerwacji.

#### `interface Discountable`
- `Money applyDiscount(Money base, User user)`.

**Implementacje przykładowe:**
- `NoDiscount` — zwraca `base`,
- `StudentDiscount` — np. –20% dla `IndividualUser`,
- `CompanyTierDiscount` — np. prosta tabela rabatowa po `taxId` lub po nazwie (wystarczy mock).

### 3.7. Płatności i faktury
#### `abstract class Payment`
- **Pola:** `Money amount`, `String paymentId`, `PaymentStatus status` (np. `INITIATED`, `CAPTURED`, `REFUNDED`).
- **Metody abstrakcyjne:** `void capture()`, `void refund()` (rzucają `IllegalStateException` przy nieprawidłowym stanie).
- **Overriding w podklasach:**
  - `class CardPayment extends Payment`
    - Pole: `String last4` (ostatnie 4 cyfry).
    - `capture()` — symulacja autoryzacji; `refund()` — symulacja zwrotu.
  - `class WalletPayment extends Payment`
    - Prosty „portfel” (np. pole w serwisie albo w `User` — wystarczy symulacja). `capture()` zmniejsza saldo, `refund()` je przywraca.

#### `interface Billable`
- `Invoice toInvoice(Booking booking)`.

#### `class Invoice`
- **Pola:** `String invoiceNumber`, `FFDateTime issueDate`, `User buyer`, `Money total`, `String itemDescription` (np. „Rezerwacja <resource> <start–end>”).

### 3.8. Repozytoria (in-memory)
Prosty wzorzec repozytorium:
- `interface UserRepository { void add(User u); Optional<User> findByEmail(String email); List<User> findAll(); }`
- `interface ResourceRepository { void add(Resource r); Optional<Resource> findByName(String name); List<Resource> findAll(); List<Resource> findByType(Class<? extends Resource> t); }`
- `interface BookingRepository { void add(Booking b); Optional<Booking> findById(String id); List<Booking> findAll(); List<Booking> findByResource(Resource r); List<Booking> findByUser(User u); }`

Implementacje: `InMemoryUserRepository`, `InMemoryResourceRepository`, `InMemoryBookingRepository` — trzymają dane w `Map`/`List`.

### 3.9. Serwisy (logika biznesowa)
#### `class BookingService`
- **Zależności:** repozytoria + aktualne `PricingPolicy` i `Discountable`.
- **Metody (overloading):**
  - `Booking book(User u, Resource r, FFDateTime start, FFDateTime end)`
  - `Booking book(User u, Resource r, FFDateTime start, int durationMinutes)` — deleguje do wersji z `end` poprzez `start.plusMinutes(durationMinutes)`.
- **Algorytm `book`:**
  1. Waliduj czasy (`end > start`).
  2. Sprawdź kolizje dla zasobu:
     - dla `Room`/`Desk` — żadne `CONFIRMED`/`PENDING` rezerwacje nie mogą się **nakładać** (`overlaps`).
     - dla `Device` — dozwolonych jest `quantity` równoległych rezerwacji (zlicz nakładające się).
  3. Utwórz `Booking` ze statusem `PENDING` i policz cenę: 
     - `base = pricingPolicy.price(booking)`; 
     - `finalPrice = discount.applyDiscount(base, user)`.
  4. Zapisz w repo, nadaj `id` (format `BK-<yyyyMMdd>-<counter>` po **dacie startu**).
- **Inne metody:** `confirm(String bookingId)`, `cancel(String bookingId)`, `complete(String bookingId)`, `list(...)` (filtry po użytkowniku/zasobie/statusie).

#### `class PaymentService`
- `Payment pay(String bookingId, Card ... | Wallet ...)` — tworzy odpowiedni `Payment`, woła `capture()`, przypina do `Booking`.
- `void refund(String bookingId)` — jeśli `CANCELLED` z opłaconą rezerwacją.

#### `class BillingService implements Billable`
- `Invoice toInvoice(Booking booking)` — numer faktury `INV-<yyyyMMdd>-<counter>` po **dacie wystawienia**.

### 3.10. Raporty
#### `class ReportingService`
- `Map<Resource, Double> utilization(FFDateTime from, FFDateTime to)` — obłożenie w %:
  - Licz minuty z rezerwacji o statusie **CONFIRMED/COMPLETED** w przedziale `[from, to)`,
  - Podziel przez łączną liczbę minut w przedziale (upraszczamy: zasób dostępny 24/7),
  - Zaokrąglij do dwóch miejsc.
- `Map<String, Money> revenueByResource(FFDateTime from, FFDateTime to)` oraz `Money totalRevenue(...)` — suma z **opłaconych** rezerwacji (po `capture`).

---

## 4) CLI (REPL) — komendy i format
- **Użytkownicy**
  - `ADD_USER INDIVIDUAL <email> <fullName>`
  - `ADD_USER COMPANY <email> <companyName> <nip>`
  - `LIST_USERS`
- **Zasoby**
  - `ADD_ROOM <name> <seats> <hourlyRate>`
  - `ADD_DESK <name> <hot|fixed> <hourlyRate>`
  - `ADD_DEVICE <name> <quantity> <hourlyRate>`
  - `LIST_RESOURCES [TYPE=<ROOM|DESK|DEVICE>]`
- **Rezerwacje**
  - `BOOK <userEmail> <resourceName> <startIso> <endIso>`
  - `BOOK <userEmail> <resourceName> <startIso> <durationMinutes>`
  - `CONFIRM <bookingId>`
  - `CANCEL <bookingId>`
  - `LIST_BOOKINGS [USER=<email>] [RESOURCE=<name>] [STATUS=<PENDING|CONFIRMED|CANCELLED|COMPLETED>]`
- **Polityki cen / zniżek**
  - `SET_PRICING STANDARD|HAPPY_HOURS`
  - `SET_DISCOUNT NONE|STUDENT|COMPANY_TIER`
- **Płatności / faktury**
  - `PAY <bookingId> CARD <last4>` | `PAY <bookingId> WALLET`
  - `INVOICE <bookingId>`
- **Raporty**
  - `REPORT UTILIZATION <fromIso> <toIso>`
  - `REPORT REVENUE <fromIso> <toIso>`
- **Pomoc / wyjście**
  - `HELP` — drukuje krótką ściągę,
  - `QUIT` — kończy program.

**Zachowanie I/O:**
- Sukces: `OK: <krótki opis>` + ewentualne dane (ID, kwoty).
- Błąd: `ERROR: <treść>` (np. walidacja dat, brak użytkownika/zasobu, kolizja).

---

## 5) Dokładne reguły i założenia (ważne przy ocenie)
1. **Format daty/godziny** w CLI: `YYYY-MM-DDTHH:MM`. Parsowane wyłącznie przez `FFDateTime.parse(...)` (lub `LocalDateTime.parse(...)` — jeśli wybrano alternatywę).
2. **Kolizje**: zakresy `[start, end)` nakładają się, jeśli `startA < endB && startB < endA` (porównania po minutach od epoki).
3. **Cennik**: bazowo `pricePerMinute = hourlyRate / 60`. Całość `minutes × pricePerMinute` → skala 2, HALF_UP.
4. **Happy Hours**: –30% (opisany wybór: cała rezerwacja gdy start ∈ HH **lub** tylko część w HH).
5. **Zniżki**: stosowane **po** policzeniu ceny bazowej zgodnie z polityką (kompozycja `policy` → `discount`).
6. **Płatności**: `capture()` zmienia `status` płatności na `CAPTURED`; `refund()` wymaga wcześniejszego `CAPTURED`. Zwrot możliwy po `CANCELLED`.
7. **Raporty**: upraszczamy dostępność zasobów do 24/7 (brak kalendarza świąt/godzin otwarcia).
8. **Identyfikatory**: 
   - Rezerwacje: `BK-<yyyyMMdd>-<counter>` na podstawie `booking.start`.
   - Faktury: `INV-<yyyyMMdd>-<counter>` na podstawie `issueDate`.
9. **Pakiety**: `time`, `money`, `domain` (user/resource/booking), `pricing`, `discount`, `payment`, `billing`, `report`, `cli`, `repo`, `service`.

---

## 6) Plan pracy (subtaski)
1. `time/` — `FFDateTime` (+ opcjonalnie `FFTimeRange`) z testami jednostkowymi parsera i arytmetyki.
2. `money/` — `Money`.
3. `domain/` — `User` + podklasy; `Resource` + podklasy; `BookingStatus`, `Booking`.
4. `repo/` — repozytoria in-memory.
5. `pricing/`, `discount/` — implementacje interfejsów.
6. `service/` — `BookingService`, `PaymentService`, `BillingService`.
7. `report/` — `ReportingService`.
8. `cli/` — REPL: parser komend, ładne komunikaty, formatowanie kwot.
9. Testy ręczne wg sekcji poniżej, ew. JUnit do krytycznych elementów.

---

## 7) Testy ręczne (must pass)
**Test 0 — Dane startowe**
- `ADD_ROOM "Sala Alfa" 12 80`
- `ADD_DESK "Hot-1" hot 25`
- `ADD_DEVICE "Projektor-1" 2 40`
- `ADD_USER INDIVIDUAL anna@ex.com "Anna Nowak"`
- `ADD_USER COMPANY biuro@acme.pl "ACME Sp. z o.o." 5211234567`
- `SET_PRICING STANDARD`
- `SET_DISCOUNT NONE`
- **Spodziewane:** `LIST_RESOURCES`, `LIST_USERS` zwracają powyższe pozycje.

**Test 1 — Rezerwacja i płatność (overloading)**
- `BOOK biuro@acme.pl "Sala Alfa" 2025-09-15T10:00 2025-09-15T12:00` → `PENDING`, cena `160.00 PLN`.
- `CONFIRM <id>` → `CONFIRMED`.
- `PAY <id> CARD 4242` → `Payment captured method=CARD last4=4242`.
- `INVOICE <id>` → `Invoice total=160.00 PLN buyer=ACME Sp. z o.o.`.
- `BOOK biuro@acme.pl "Sala Alfa" 2025-09-16T09:00 90` → `120.00 PLN`.

**Test 2 — Kolizje**
- Mając `CONFIRMED` `10:00–12:00`, próba `11:00–13:00` → `ERROR: resource not available`.
- Równoległa rezerwacja innego zasobu — przechodzi.

**Test 3 — Happy Hours**
- `SET_PRICING HAPPY_HOURS`
- `BOOK anna@ex.com "Hot-1" 2025-09-17T14:00 2025-09-17T16:00` → ~`35.00 PLN` (–30% od `50.00`).

**Test 4 — Zniżki**
- `SET_DISCOUNT STUDENT`
- `BOOK anna@ex.com "Sala Alfa" 2025-09-18T09:00 2025-09-18T11:00` → `128.00 PLN`.
- `BOOK biuro@acme.pl "Sala Alfa" 2025-09-18T12:00 2025-09-18T14:00` → `160.00 PLN`.

**Test 5 — Anulowanie i refund**
- Po `PAY ... WALLET`, `CANCEL <id>` → `CANCELLED` + `refund processed method=WALLET`.

**Test 6 — Ilość urządzeń**
- Dwie rezerwacje `Projektor-1` w tym samym czasie przy `quantity=2` — przechodzą; trzecia → błąd.

**Test 7 — Raporty**
- `REPORT UTILIZATION 2025-09-15T00:00 2025-09-20T00:00` → procent obłożenia per zasób.
- `REPORT REVENUE 2025-09-01T00:00 2025-09-30T23:59` → suma i podział.

---

## 8) Definicja ukończenia (DoD)
- Komendy działają zgodnie ze specyfikacją, komunikaty są czytelne.
- Spełnione minimalne wymagania OOP (abstrakcje, interfejsy, overriding, overloading).
- `FFDateTime` (lub `LocalDateTime`) poprawnie parsuje i porównuje czasy.
- Cenniki, zniżki, płatności, faktury i raporty funkcjonują zgodnie z opisem.
- Kod ma rozsądny podział na pakiety, jest czytelny i skomentowany tam, gdzie to potrzebne.

Powodzenia! 💪
