package seminar4;

import seminar4.models.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import java.util.List;
import java.util.Scanner;
import static java.lang.System.exit;

public class App {

    /**
     * Задание
     * =======
     * Создайте базу данных (например, SchoolDB).
     * В этой базе данных создайте таблицу Courses с полями id (ключ), title, и duration.
     * Настройте Hibernate для работы с вашей базой данных.
     * Создайте Java-класс Course, соответствующий таблице Courses, с необходимыми аннотациями Hibernate.
     * Используя Hibernate, напишите код для вставки, чтения, обновления и удаления данных в таблице Courses.
     * Убедитесь, что каждая операция выполняется в отдельной транзакции.
     */


    public static void main(String[] args) {

        String resources = "hibernate.cfg.xml";
        Scanner scanner = new Scanner(System.in);
        int choice;

        try (SessionFactory sessionFactory = new Configuration()
                .configure(resources)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory()) {
            while (true){
                printMenu();
                try {

                    choice = scanner.nextInt();
                    switch (choice) {
                        case 1: addCourse(sessionFactory.getCurrentSession()); break;
                        case 2: viewCourses(sessionFactory.getCurrentSession()); break;
                        case 3: changeCourse(sessionFactory.getCurrentSession()); break;
                        case 4: deleteCourse(sessionFactory.getCurrentSession()); break;
                        case 0: exit(0);
                    }
                }

                catch (Exception ex) {
                    System.out.println("Введите число от 1 до 4 или 0 для выхода");
                    scanner.next();
                }
            }
        }
    }

    //region method
    public static void printMenu() {
        System.out.println("1 - Вставить");
        System.out.println("2 - Чтение");
        System.out.println("3 - Обновить");
        System.out.println("4 - Удалить ");
        System.out.println("0 - Выйти");
        System.out.print("Выберите пункт: ");
    }

    private static void viewCourses(Session session) {
        System.out.println("Cписок курсов: ");
        session.beginTransaction();
        String sql = "FROM Course ";
        List courses = session.createQuery(sql).list();

        for (Object cours : courses) {
            Course course = (Course) cours;
            System.out.println(course);
        }
        session.getTransaction().commit();
    }

    private static void addCourse(Session session) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите название курса: ");
        String title = scanner.next();

        System.out.println("Введите продолжительность курса: ");
        int duration = scanner.nextInt();

        session.beginTransaction();

        Course course = new Course(title, duration);
        session.save(course);
        System.out.println("Курс добавлен");

        session.getTransaction().commit();
    }

    private static void changeCourse(Session session) {

        System.out.println("Введите id для изменения курса: ");
        Scanner scanner = new Scanner(System.in);
        int courseID = scanner.nextInt();

        session.beginTransaction();
        Course retrievedCourse = session.get(Course.class, courseID);

        System.out.println("Введите новое название курса: ");
        String newTitle = scanner.next();

        System.out.println("Введите продолжительность курса: ");
        int newDuration = scanner.nextInt();

        retrievedCourse.updateTitle(newTitle);
        retrievedCourse.updateDuration(newDuration);
        session.update(retrievedCourse);
        System.out.println("Курс обновлён");

        session.getTransaction().commit();
    }


    private static void deleteCourse(Session session) {

        System.out.println("Введите id курса для удалить: ");
        Scanner scanner = new Scanner(System.in);
        int courseID = scanner.nextInt();

        session.beginTransaction();

        Course deleteddCourse = session.get(Course.class, courseID);

        session.delete(deleteddCourse);

        session.getTransaction().commit();
        System.out.println("Курс удалён");
    }
    // endregion
}