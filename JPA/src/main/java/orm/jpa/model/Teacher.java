package orm.jpa.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 150)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 200)
    private String lastName;

    // 1 School have mutilple Teacher
    @ManyToOne
    private School school;

    @ManyToMany(cascade = {
            CascadeType.PERSIST, CascadeType.MERGE
    })

    @JoinTable(
            name = "teachers_students",
            joinColumns = {@JoinColumn(name = "teacher_id")},
            inverseJoinColumns = {@JoinColumn(name = "student_id")},
            uniqueConstraints = {@UniqueConstraint(columnNames = {"teacher_id", "student_id"})}
    )
    private Set<Student> students = new HashSet<>();

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public Teacher(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public void addStudent(Student student) {
        boolean added = students.add(student);
        if (added) {
            student.getTeachers().add(this);
        }
    }

    public void removeStudent(Student student) {
        boolean remove = students.remove(student);
        if (remove) {
            student.getTeachers().remove(this);
        }
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", school=" + school +
                ", students=" + students +
                '}';
    }
}
