package seminar2.task1;

public abstract class Animal {
    String name;
    int age;

    public Animal() {
        this.name = name;
        this.age = age;
    }

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void displayInfo(){
        System.out.printf("Имя: %s; Возраст: %d\n%n", name, age);
    }
}
