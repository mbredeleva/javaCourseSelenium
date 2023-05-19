package data_structures;

import com.github.javafaker.Faker;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Employee {
    String firstName;
    String lastName;
    String email;
    int age;
    int salary;
    String department;

    static public Employee generateRandomEmployee(){
        Faker faker = new Faker();
        return new Employee(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.internet().emailAddress(),
                faker.number().numberBetween(1,100),
                faker.number().numberBetween(100,100500),
                faker.pokemon().name()
        );
    }

    public String textRepresentation() {
        return this.getFirstName() + "\n" + this.getLastName() + "\n" + this.getAge() + "\n" + this.getEmail() + "\n"
                + this.getSalary() + "\n" + this.getDepartment() + "\n";
    }
}
