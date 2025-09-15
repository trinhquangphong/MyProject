package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Comparable<Student>, Serializable, Cloneable {
    private static final long serialVersionUID = 1;
    private int id;
    private String name;
    private int age;
    private String gender;
    private String userCode;
    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

//    public String getName() {
//        return name;
//    }
//
//    public int getAge() {
//        return age;
//    }

    // Comparable: So sánh theo tuổi
    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.age, other.age);
    }

    // Cloneable: override clone()
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone(); // Shallow copy
    }

    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "', age=" + age + "}";
    }
}
