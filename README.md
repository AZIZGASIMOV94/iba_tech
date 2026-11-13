# IBA Tech Interview Tasks

[![Build](https://github.com/AZIZGASIMOV94/iba_tech/actions/workflows/build.yml/badge.svg)](https://github.com/AZIZGASIMOV94/iba_tech/actions/workflows/build.yml)
![Java 17](https://img.shields.io/badge/Java-17-orange)

Solutions to two algorithm tasks from the IBA Tech technical interview, with tests and complexity analysis.

| Task | Class | Time | Space |
|---|---|---|---|
| Car Parking Roof | [`CarParkingRoof`](src/main/java/io/github/azizgasimov94/ibatech/parking/CarParkingRoof.java) | O(n log n) | O(n) |
| Count Countries | [`CountryCounter`](src/main/java/io/github/azizgasimov94/ibatech/countries/CountryCounter.java) | O(rows × cols) | O(rows × cols) |

---

## 1. Car Parking Roof

Cars are parked at given positions on a line. Find the length of the shortest roof that covers at least `k` cars. A roof from position `a` to `b` has length `b − a + 1`.

**Example:** cars = `[6, 2, 12, 7]`, k = `3` → **6** (roof from 2 to 7 covers cars 2, 6, 7).

**Approach:** sort the positions. The optimal roof always covers `k` *consecutive* cars in sorted order, so slide a window of size `k` and take the minimum of `sorted[i + k − 1] − sorted[i] + 1`.

Positions are `long` so very large coordinates don't overflow, the input array is not modified, and an invalid `k` is rejected with a clear message.

## 2. Count Countries

A map is a grid of colours. A country is a group of same-coloured cells connected horizontally or vertically. Count the countries.

**Example:**

```
5 4 4
4 3 4
3 2 4
2 2 2
3 3 4
1 4 4
4 1 1
```

→ **11** countries.

**Approach:** scan every cell; each unvisited cell starts a new country, and a breadth-first search marks all cells of the same colour reachable from it. BFS uses an explicit `ArrayDeque` instead of recursion, so a 1000 × 1000 map works without a `StackOverflowError`.

---

## Build and run

Requires Java 17+ and Maven 3.8+.

```bash
mvn clean verify                         # compile + run tests
java -jar target/iba-tech-tasks.jar      # run both examples
```

Output:

```
Car Parking Roof: cars=[6, 2, 12, 7], k=3 -> 6
Count Countries: 11
```

## Tests

15 JUnit 5 tests cover the task examples, edge cases (empty map, single car, `k = 1`, `k = n`), invalid input, overflow, large inputs, and regression cases for the bugs listed below.

## Revision notes

This repository was revisited and cleaned up after the interview. Fixes compared to the first version:

- **Car Parking Roof** used `list.get(2)`, which only worked for `k = 3`; any other `k` gave a wrong answer or an `IndexOutOfBoundsException`. Replaced with an O(n) sliding window over the sorted array.
- **Count Countries** never compared cell colours, so every map returned **1**. It also stored regions in a `Set` keyed by shape, so two separate countries with the same shape were counted once. Rewritten as a colour-aware BFS.
- Recursive DFS replaced with iterative BFS to avoid stack overflows on large maps.
- Two separate IntelliJ projects merged into one Maven project with proper packages; committed `.class`, `out/` and `.idea/` files removed.
- Added unit tests and GitHub Actions CI.

## Project structure

```
src
├── main/java/io/github/azizgasimov94/ibatech
│   ├── Main.java
│   ├── parking/CarParkingRoof.java
│   └── countries/CountryCounter.java
└── test/java/io/github/azizgasimov94/ibatech
    ├── parking/CarParkingRoofTest.java
    └── countries/CountryCounterTest.java
```
