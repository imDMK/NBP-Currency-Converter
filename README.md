# NBP Currency Converter

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.x-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-Container-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![JUnit](https://img.shields.io/badge/JUnit-5-25A162?style=for-the-badge&logo=junit5&logoColor=white)

Prosta aplikacja w **Spring Boot**, która przelicza ceny komputerów z **USD na PLN** przy użyciu oficjalnego **API NBP**.  
Dane są zapisywane w bazie **PostgreSQL** oraz mogą zostać wyeksportowane do pliku **XML**.

## Funkcjonalności

- pobieranie kursu USD z API NBP dla podanej daty
- przeliczanie wartości USD → PLN
- zapis danych w bazie **PostgreSQL**
- generowanie faktury w formacie **XML**
- wyszukiwanie komputerów po fragmencie nazwy
- wyszukiwanie po dacie księgowania
- sortowanie po nazwie i dacie

## Technologie

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL
- Flyway
- Jackson XML
- Docker
- JUnit 5 / Mockito

## Uruchomienie aplikacji

1. Uruchom bazę **PostgreSQL**
2. Skonfiguruj dane w `application.properties`
3. Uruchom aplikację:

```bash
./gradlew bootRun
```

## Endpointy API

### Inicjalizacja przykładowych danych

```
POST /computers/init
```

### Dodanie komputera

```
POST /computers
```

Przykładowy request:

```json
{
  "name": "ACER Aspire",
  "usdCost": 345,
  "date": "2026-01-05"
}
```

### Lista komputerów

```
GET /computers
```

### Wyszukiwanie po nazwie

```
GET /computers/search?name=acer
```

### Wyszukiwanie po dacie

```
GET /computers/by-date?date=2026-01-05
```

### Sortowanie

```
GET /computers/sorted?sort=name
GET /computers/sorted?sort=accountingDate
```

### Generowanie faktury XML

```
POST /computers/invoice
```

## Cel projektu

Projekt został przygotowany jako aplikacja demonstracyjna backendu w Javie.
Pokazuje integrację z zewnętrznym API (NBP), pracę z bazą danych,
generowanie XML oraz testowanie aplikacji Spring Boot.

## Autor
Dominik Suliga (dominiks8318@gmail.com)
