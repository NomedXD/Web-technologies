package by.bsuir.task4;

import java.util.List;

import java.util.stream.IntStream;

public class Prime {
    private static Boolean isPrime(Integer number) {
        return number > 1 && IntStream.range(2, (int)Math.sqrt(number) + 1).filter(divider -> number % divider == 0).findAny().isEmpty();
    }

    public static List<Integer> findIndexes(Integer[] numbers) {
        return IntStream.range(0, numbers.length).filter(index -> isPrime(numbers[index])).boxed().toList();
    }
}
