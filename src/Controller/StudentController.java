package Controller;

import Model.Student;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

//Контроллер
public class StudentController {

    private static final String FILE_NAME = "students.txt";


    public void save(Student student){
        try(FileWriter fileWriter = new FileWriter(FILE_NAME)){
            fileWriter.write(student.toString());
            fileWriter.write(System.lineSeparator());
            fileWriter.flush();
        }catch (Exception exception){
            System.out.println("An error occurred while saving the student: " + exception.getMessage());
        }
    }

    public void saveAllStudents(List<Student> students){
        try(FileWriter fileWriter = new FileWriter(FILE_NAME, true)){
            for (int i = 0; i < students.size(); i++) {
                fileWriter.write(students.get(i).toString());
                fileWriter.write(System.lineSeparator());
                fileWriter.flush();
            }
        }catch (Exception exception){
            System.out.println("An error occurred while saving the students: " + exception.getMessage());
        }
    }

    public void remove(Student student) {
        try {
            File file = new File(FILE_NAME);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            List<String> lines = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                if (!line.equals(student.toString())) {
                    lines.add(line);
                }
            }
            reader.close();
            FileWriter writer = new FileWriter(file);
            for (String remainingLine : lines) {
                writer.write(remainingLine + "\n");
            }
            writer.flush();
            writer.close();
        } catch (Exception exception) {
            System.out.println("An error occurred while removing the student: " + exception.getMessage());
        }
    }

    public void edit(Student student){
        List<Student> students = getAllStudents();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getName().equals(student.getName())) {
                students.set(i, student);
                break;
            }
        }
        saveAllStudents(students);
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                students.add(parseStudent(line));
            }
        } catch (Exception exception) {
        }
        return students;
    }

    private Student parseStudent(String line) {
        String[] parts = line.split(",");
        String name = parts[0].split("=")[1];
        float grade = Float.parseFloat(parts[1].split("=")[1]);
        int year = Integer.parseInt(parts[2].split("=")[1]);
        return new Student(name, grade, year);
    }

    public Student getStudent(String name) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Student student = parseStudent(line);
                if (student.getName().equals(name)) {
                    return student;
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred while getting student: " + e.getMessage());
        }

        return null;
    }

}
