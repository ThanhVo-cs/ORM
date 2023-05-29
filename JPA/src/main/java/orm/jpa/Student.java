package orm.jpa;

import lombok.Data;

@Data
public class Student {
    private Long id;
    private String firstName;
    private String lastName;
}
