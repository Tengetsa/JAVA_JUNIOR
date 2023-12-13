package seminar2.task1;

import java.lang.reflect.Method;

public class Program {
    /*
        Задача 1:
    Создайте абстрактный класс "Animal" с полями "name" и "age".
    Реализуйте два класса-наследника от "Animal" (например, "Dog" и "Cat") с уникальными полями и методами.
    Создайте массив объектов типа "Animal" и с использованием Reflection API выполните следующие действия:
    Выведите на экран информацию о каждом объекте.
    Вызовите метод "makeSound()" у каждого объекта, если такой метод присутствует.
     */

    public static void main(String[] args) {
        Animal[] animals = new Animal[]
            {
                new Cat("Гаражик", 6),
                new Cat("Соня", 4),
                new Cat("Стеша", 9),
                new Dog("Чарли", 16),
                new Dog("Пушистик", 3)
            };

        animalInformation(animals);
    }

    private static void animalInformation(Animal[] animals) {
        for (Animal animal : animals)
        {
            if (animal.getClass().getSimpleName().equals("Cat"))
            {
                System.out.println("Кот: ");
            } else
            {
                System.out.println("Собака: ");
            }

            System.out.println("Кличка: " + animal.name);
            System.out.println("Возраст: " + animal.age);

            try
            {
                Method method = animal.getClass().getMethod("makeSound");
                method.invoke(animal);
            } catch (Exception e) {
                System.out.println("Метод 'makeSound()' не найден.");
            }

            System.out.println();
        }
    }
}
