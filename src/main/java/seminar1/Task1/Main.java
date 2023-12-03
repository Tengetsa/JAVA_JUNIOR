package seminar1.Task1;

/*
    Напишите программу, которая использует Stream API для обработки списка чисел.
    Программа должна вывести на экран среднее значение всех четных чисел в списке.

 */

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        List<Integer> listNumber = new ArrayList<>();
//        addList(listNumber);
//        double a = averageValueEvenNumbers(listNumber);
//        System.out.println(listNumber);
//        System.out.println(a);

        System.out.println(Arrays.asList(1, 10, 0, 5, 14, 5, 8).stream().filter(i -> i % 2 == 0)
                .mapToInt(e -> e).average().orElse(0));
    }


    private static double averageValueEvenNumbers(List<Integer> list) {
        return  list.stream().filter(Main::test)
                .mapToInt(e -> e).average().orElse(0);
    }

    private static boolean test(Integer e) {
        return e % 2 == 0;
    }

    private static void addList(List<Integer> list) {
        int count = 15;
        for (int i = 1; i < count; i++) {
            list.add(i);
        }
    }

//    private static double averageValueEvenNumbers(List<Integer> list) {
//        int sum = 0;
//        int count = 0;
////        for (int i = 0; i < list.size(); i++) {
////            if (list.get(i) % 2 == 0) {
////                sum += list.get(i);
////                count += 1;
////            }
////        }
//        for (Integer integer : list) {
//            if (integer % 2 == 0) {
//                sum += integer;
//                count += 1;
//            }
//        }
//        sum = sum / count;
//        return sum;
//
//    }
}