package orm.jpa;


import orm.jpa.model.School;
import orm.jpa.model.Student;
import orm.jpa.model.Teacher;
import orm.jpa.model.Tutor;
import orm.jpa.repository.ReposirotySchool;
import orm.jpa.repository.RepositoryStudent;
import orm.jpa.repository.RepositoryTeacher;
import orm.jpa.repository.RepositoryTutor;

import java.util.List;

public class App {
    public static void main(String[] args) {
        RepositoryStudent repo = new RepositoryStudent();
        ReposirotySchool repoSchool = new ReposirotySchool();
        RepositoryTutor repoTutor = new RepositoryTutor();
        RepositoryTeacher repoTeacher = new RepositoryTeacher();
        Student student = new Student();
        student.setFirstName("Duong Huynh");
        student.setLastName("Thi");
        //add
        student = repo.add(student);
        System.out.println("Add Student");
        System.out.println(student.toString());

        //add Tutor
        Tutor tutor = new Tutor("first_name1", "last_name1");
        repoTutor.add(tutor);
        student.setTutor(tutor);

        repo.addTutors(student.getId(), tutor);
        System.out.println("Tutor" + tutor.toString());

        // add School
        School school = new School("School_1", "City_1");
        repoSchool.add(school);

        // add Teacher

//        Teacher teacher1 = new Teacher("Mary", "Bos");
//        teacher1.setSchool(school);
//        repoTeacher.add(teacher1);
//
//        Teacher teacher2 = new Teacher("Tom", "Hamilton");
//        teacher2.setSchool(school);
//        repoTeacher.add(teacher2);
//
//        System.out.println("Teacher Info" + teacher1.toString());
//        System.out.println("Teacher Info" + teacher2.toString());

        Teacher teacher = new Teacher("firstname_1", "lastname_1");
        teacher.addStudent(new Student("SFirstName_1", "SLastName_1"));
        teacher.addStudent(new Student("SFirstName_2", "SLastName_2"));
        repoTeacher.add(teacher);

        repoSchool.addStudent(school.getId(), student);

        school = repoSchool.find(school.getId());

        repoSchool.removeStudent(school.getId(), student);

        student = repo.findById(student.getId());
        System.out.println("Student Info: " + student.toString());
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
