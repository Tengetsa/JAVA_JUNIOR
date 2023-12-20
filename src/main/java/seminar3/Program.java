package seminar3;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;


public class Program {
    /*
    Разработайте класс Student с полями String name, int age, transient double GPA (средний балл). Обеспечьте поддержку
    сериализации для этого класса. Создайте объект класса Student и инициализируйте его данными. Сериализуйте этот
    объект в файл. Десериализуйте объект обратно в программу из файла. Выведите все поля объекта, включая GPA, и
    обсудите, почему значение GPA не было сохранено/восстановлено.
     */

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Student student = new Student("Виталий", 21, 2.5);
        serializeBin(student);
//        serializeXml(student);
//        serializeJson(student);

//        Student student1 = deserializeXml();
//        Student student1 = deserializeJson();
//        System.out.println(student1);
    }


    public static void serializeBin(Student student) throws IOException {
        try(ObjectOutputStream objectOutputStream =
                    new ObjectOutputStream(new FileOutputStream("studentData.bin"))){
            objectOutputStream.writeObject(student);
            System.out.println("Объект studentData сериализован.");
        }

        try(FileInputStream fileInputStream = new FileInputStream("studentData.bin");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
            student = (Student)objectInputStream.readObject();
            System.out.println("Объект studentData десериализован.");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Имя: " + student.getName());
        System.out.println("Возраст: " + student.getAge());
        System.out.println("GPA (должен быть 0.0, так как transient): " + student.getGPA());
    }

    //region Не работает
//    public static void serializeXml(Student student) throws IOException {
//
//        XmlMapper xmlMapper = new XmlMapper();
////        String studentXml = xmlMapper.writeValueAsString(student);
//
//        xmlMapper.writeValue(new File("studentXml.xml"), student);
//        System.out.println(xmlMapper);
//
////        student =  xmlMapper.readValue("StudentXml.xml", Student.class);
////        Student student1 = new Student(xmlMapper.readValue("StudentXml.xml", Student.class));
//    }
//
//    public static Student deserializeXml() {
//        try {
//            XmlMapper xmlMapper = new XmlMapper();
//            return xmlMapper.readValue("studentData.xml", Student.class);
//
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//
//    public static void serializeJson(Student student) {
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
////            objectMapper.writeValue(new File("studentData.json"), student);
//            System.out.println("Сериализация в Json выполнена успешно");
//            objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
//            objectMapper.writeValue(new File("studentData.json"), student);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private static Student deserializeJson() throws FileNotFoundException {
//        Student student;
//        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("studentData.json"))) {
////            ObjectMapper objectMapper = new ObjectMapper();
////            return objectMapper.readValue(new File("student.json"), Student.class);
//            return student = (Student) ois.readObject();
//
//            } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }
    //endregion

}