package seminar1.Task1;

import java.util.stream.Stream;

public class Program {
    public static void main(String[] args) {

        averageValueEvenNumbers();
    }

    public static void averageValueEvenNumbers() {
        System.out.println(Stream.of(1, 2, 3, 4, 5, 6, 7, 18).filter(i -> i % 2 == 0)
                .mapToInt(e -> e).average().orElse(0));

    }
}

