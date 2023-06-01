package orm.jpa;


import orm.jpa.model.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class App {
    public static void main(String[] args) {
        RepositoryStudent repo = new RepositoryStudent();
        Student student = new Student();
        student.setFirstName("tran");
        student.setLastName("thi");
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

        //find by id
        Student res = repo.findById(1L);
        System.out.println("Find By Id");
        System.out.println(res.toString());

        // update
        //  student = repo.update(student);
        System.out.println("Update Student");
        System.out.println(student.toString());

        //update first name
        //student = repo.updateFirstName("Huynh",1L);
        System.out.println("Update First Name");
        System.out.println(student.toString());

        //update Last Name
        // student = repo.updatelastName("Huynh Thi",1L);
        System.out.println("Update First Name");
        System.out.println(student.toString());

        //filter Student by FirstName

        List<Student> studentList = repo.filterByFirstName("tran");
        System.out.println("Filter First Name");
        for (Student item : studentList) {
            System.out.println(item.toString());
        }

        // count

        Long count = repo.count();
        System.out.println("number row" + count);

        // sorting

        List<Student> list = repo.findSortingByFirstName();
        for (Student item : list) {
            System.out.println(item.toString());
        }
        // delete Student use JPQL
        //repo.deleteStudent(1L);

        //delete
        //repo.delete(student);

        repo.close();
    }
}
