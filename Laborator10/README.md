# Sistem concurent de procesare comenzi pentru o cafenea

## Descriere generală

Aplicația simulează un sistem de procesare a comenzilor într-o cafenea folosind concepte de:

- Programare orientată pe obiecte
- Multithreading și programare concurentă
- Design Patterns

Comenzile sunt create, adăugate într-o coadă comună și procesate concurent de mai multe thread-uri. În timpul
procesării, diferite componente sunt notificate automat despre schimbarea statusului comenzilor.

---

# Funcționalități principale

- Crearea produselor prin Factory Pattern
- Construirea comenzilor prin Builder Pattern
- Gestionarea unei cozi globale de comenzi prin Singleton
- Procesarea concurentă a comenzilor folosind ExecutorService
- Notificarea componentelor prin Observer Pattern
- Integrarea unui sistem legacy de plată prin Adapter Pattern
- Utilizarea colecțiilor concurente și a wrapperelor atomice

---

# Fluxul aplicației

1. Se creează produsele (`Coffee`, `Tea`) folosind factory-uri.
2. Se construiesc comenzile folosind `Order.Builder`.
3. Comenzile sunt adăugate într-o coadă globală (`OrderQueue`).
4. Mai mulți workeri procesează comenzile concurent.
5. Observatorii sunt notificați când statusul unei comenzi se schimbă:
    - PRELUATA
    - IN PREGATIRE
    - FINALIZATA
6. Sistemul legacy procesează plata printr-un adaptor.
7. Comenzile procesate sunt salvate într-un istoric thread-safe.

---

# Concepte de multithreading utilizate

## Thread și Runnable

- Crearea și rularea firelor de execuție
- Separarea logicii de execuție de obiectul Thread

## ExecutorService

- Gestionarea unui pool de thread-uri
- Reutilizarea eficientă a thread-urilor

## BlockingQueue

- Thread-urile așteaptă automat până apare o comandă
- Evitarea polling-ului continuu

## synchronized

- Sincronizarea notificării observatorilor

## AtomicInteger

- Increment thread-safe pentru numărul comenzilor procesate

## ConcurrentHashMap

- Stocare thread-safe a istoricului comenzilor

## volatile

- Vizibilitate corectă între thread-uri pentru variabile partajate

---

# Design Patterns utilizate

## Singleton

### Clasă:

`OrderQueue`

### Scop:

Există o singură coadă globală de comenzi în aplicație.

---

## Builder

### Clasă:

`Order.Builder`

### Scop:

Construirea flexibilă și fluentă a comenzilor.

Exemplu:

```java
Order order = new Order.Builder("Maria")
        .addProduct(espresso)
        .priority(2)
        .build();
```

## Factory Method

### Clasă:

`DefaultProductFactory`

### Scop:

Crearea produselor fără expunerea logicii de instanțiere.

## Abstract Factory

### Clase:

`DefaultProductFactory`
`FastCoffeeShopFactory`

### Scop:

Crearea unor familii compatibile de obiecte.

## Observer

### Clase:

`OrderObserver`
`KitchenDisplay`
`ManagerDashboard`

### Scop:

Notificarea automată a componentelor atunci când statusul comenzilor se schimbă.

## Adapter

### Clase:

`LegacyPaymentAdapter`
`LegacyPaymentSystem`

### Scop:

Integrarea unui sistem legacy de plată într-o aplicație modernă.