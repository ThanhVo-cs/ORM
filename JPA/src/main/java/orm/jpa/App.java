package orm.jpa;


import orm.jpa.model.Student;

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

        // update
        student = repo.update(student);
        System.out.println("Update Student");
        System.out.println(student.toString());

        //delete
        repo.delete(student);

        repo.close();
    }
}
