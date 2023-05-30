package orm.jpa;


import orm.jpa.model.Student;

import java.util.List;

public class App {
    public static void main(String[] args) {
        RepositoryStudent repo = new RepositoryStudent();
        Student student = new Student();
        student.setFirstName("Huynh");
        student.setLastName("Thi");
        //add
        student = repo.add(student);
        System.out.println("Add Student");
        System.out.println(student.toString());

        // find
        student = repo.find(student);
        System.out.println("Find Student");
        System.out.println(student.toString());

        // find firstName

        List<String> result = repo.findFirstNames();
        System.out.println("Find First Name");
        for (String item : result) {
            System.out.println(item);
        }

        // find lastName
        List<String> results = repo.findLastNames();
        System.out.println("Find Last Name");
        for (String item : results) {
            System.out.println(item);
        }

        // update
        student = repo.update(student);
        System.out.println("Update Student");
        System.out.println(student.toString());

        //delete
        repo.delete(student);

        repo.close();
    }
}
