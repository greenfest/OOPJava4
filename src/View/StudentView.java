package View;
import Model.Student;
import Repository.StudentRepository;
import java.util.Scanner;
//Меню

public class StudentView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentRepository repository = new StudentRepository();

    public void run() {
        boolean shouldContinue = true;
        while (shouldContinue) {
            System.out.println("1. Add student");
            System.out.println("2. Edit student");
            System.out.println("3. Remove student");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    editStudent();
                    break;
                case 3:
                    removeStudent();
                    break;
                case 4:
                    shouldContinue = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }
        System.out.println("Exiting the program...");
    }

    private static void addStudent() {
        System.out.print("Enter name: ");
        String name = scanner.next();
        System.out.print("Enter grade: ");
        float grade = scanner.nextFloat();
        System.out.print("Enter year: ");
        int year = scanner.nextInt();

        Student student = new Student(name, grade, year);
        repository.save(student);
        System.out.println("Student added successfully.");
    }

    private static void editStudent() {
        System.out.print("Enter name of the student you want to edit: ");
        String name = scanner.next();
        Student student = repository.getStudent(name);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter new name: ");
        student.setName(scanner.next());
        System.out.print("Enter new grade: ");
        student.setGrade(scanner.nextFloat());
        System.out.print("Enter new year: ");
        student.setYear(scanner.nextInt());

        repository.edit(student);
        System.out.println("Student edited successfully.");
    }

    private static void removeStudent() {
        System.out.print("Enter name of the student you want to remove: ");
        String name = scanner.next();
        Student student = repository.getStudent(name);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        repository.remove(student);
        System.out.println("Student removed successfully.");
    }
}