package Repository;

import Controller.StudentController;
import Model.Student;

import java.util.List;

public class StudentRepository {
    private StudentController studentController = new StudentController();

    public void save(Student student){
        studentController.save(student);
    }

    public void saveAllStudents(List <Student> students){
        studentController.saveAllStudents(students);
    }

    public void remove(Student student) {
        studentController.remove(student);
    }

    public void edit(Student student){
        studentController.edit(student);
    }

    public List<Student> getAllStudents() {
        return studentController.getAllStudents();
    }

    public Student getStudent(String name) {
        return studentController.getStudent(name);
    }

}
