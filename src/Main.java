import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main{
    private static ArrayList<Patient> patients=new ArrayList<>();
    private static ArrayList<Doctor> doctors=new ArrayList<>();
    private static ArrayList<Appointment> appointments=new ArrayList<>();
    private static Scanner scanner=new Scanner(System.in);
    private static void displayMenu(){
        System.out.println("\n========================================");
        System.out.println("  HOSPITAL MANAGEMENT SYSTEM");
        System.out.println("========================================");
        System.out.println("1. Add Patient");
        System.out.println("2. View Patients");
        System.out.println("3. Add Doctor");
        System.out.println("4. View All Doctors");
        System.out.println("5. Add Appointment");
        System.out.println("6. View All Appointments");
        System.out.println("0. Exit");
        System.out.println("========================================");
        System.out.print("Enter your choice: ");
    }
    private static void addPatient(){
        System.out.println("\n---ADD PATIENTS---");
        System.out.print("Enter IIN: ");
        String IIN=scanner.nextLine();
        System.out.print("Enter Full name: ");
        String fullname=scanner.nextLine();
        System.out.print("Enter age: ");
        int age=scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter bloodtype: ");
        Patient.Bloodtype bloodtype=scanner.nextBloodtype();

    }
    public static void main(String[] args) {
        boolean run = true;
        while (run) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    addPatient();
                    break;
                case 2:
                    viewAllPatients();
                    break;
                case 3:
                    addDoctor();
                    break;
                case 4:
                    viewAllDoctors();
                    break;
                case 5:
                    addAppointment();
                    break;
                case 6:
                    viewAllAppointments();
                    break;
                case 0:
                    System.out.println("\nGoodbye!");
                    run = false;
                    break;
                default:
                    System.out.println("\n Invalid choice");
            }
            if (run) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }scanner.close();


        System.out.println("===Hospital Management System===");
        System.out.println();
        Patient patient1=new Patient("060217789123","Dosymova Zhansaya",17,Patient.Bloodtype.AB);
        Patient patient2=new Patient("091207589632","Beisen Bori",13,Patient.Bloodtype.O);
        Patient patient3=new Patient("100511789583","Nazar Hadjar",13,Patient.Bloodtype.A);
        Doctor doctor1=new Doctor("D01","Beisen Zamira","therapist",30);
        Doctor doctor2=new Doctor("D02","Yesirkep Yelnaz","gynecologist",1);
        Doctor doctor3=new Doctor("D03","Shabden Adil","radiologist",13);
        LocalDateTime date= LocalDateTime.of(2025,12,28,14,0);
        Appointment appointment1=new Appointment("A01",patient2.getFullname(),doctor3.getName(),date,"scheduled");
        Appointment appointment2=new Appointment("A02",patient3.getFullname(),doctor2.getName(),date,"scheduled");

        patients.add(new Patient("060217789123","Dosymova Zhansaya",17,Patient.Bloodtype.AB));
        patients.add(new Patient("091207589632","Beisen Bori",13,Patient.Bloodtype.O));
        patients.add(new Patient("100511789583","Nazar Hadjar",13,Patient.Bloodtype.A));
        doctors.add(new Doctor("D01","Beisen Zamira","therapist",30));
        doctors.add(new Doctor("D02","Yesirkep Yelnaz","gynecologist",1));
        doctors.add(new Doctor("D03","Shabden Adil","radiologist",13));
        appointments.add(new Appointment("A01",patient2.getFullname(),doctor3.getName(),date,"scheduled"));
        appointments.add(new Appointment("A02",patient3.getFullname(),doctor2.getName(),date,"scheduled"));

        System.out.println("---Patients---");
        System.out.println(patient1);
        System.out.println(patient2);
        System.out.println(patient3);
        System.out.println();
        System.out.println("---Doctors---");
        System.out.println(doctor1);
        System.out.println(doctor2);
        System.out.println(doctor3);
        System.out.println();
        System.out.println("---Appointments---");
        System.out.println(appointment1);
        System.out.println(appointment2);
        System.out.println();

        System.out.println("---Testing getters---");
        System.out.println("Patient 1 name:"+patient1.getFullname());
        System.out.println("Patient 3 bloodtype"+patient3.getBloodtype());
        System.out.println("Doctor 3 specialization:"+doctor3.getSpecialization());
        System.out.println("Appointment 2 date:"+appointment2.getDate());
        System.out.println();

        System.out.println("---Testing setters---");
        System.out.println("Updating patient3...");
        patient3.setFullname("Niyzabek Kausar");
        patient3.setAge(15);
        patient3.setBloodtype(Patient.Bloodtype.B);
        System.out.println("Updated:"+patient3);
        System.out.println();
        System.out.println("Changing appointment2...");
        appointment2.setStatus("Unscheduled");
        System.out.println("Changed:"+appointment2);
        System.out.println();

        System.out.println("___Testing patient mathods___");
        String compatability=patient1.getDonorCompatibility();
        System.out.println(compatability);
        System.out.println(patient2.getDonorCompatibility());
        System.out.println(patient3.getFullname()+" is Minor: "+patient3.isMinor());
        System.out.println(patient2.getFullname()+" is "+patient2.getCategory());
        System.out.println("___Testing doctor mathods___");
        System.out.println(doctor2.getName()+"could perform surgery:"+doctor2.canPerformSurgery());
        System.out.println(doctor3.getName()+"is experienced: "+doctor3.isExperienced());
        System.out.println("___Testing appointment mathods___");
        appointment2.cancel();
        System.out.println("Appointment 2 after cancellation:"+ appointment2);
        LocalDateTime newDate=LocalDateTime.of(2025,12,30,9,0);
        appointment1.reschedule(newDate);
        System.out.println("appointment1 after reschedule"+ appointment1);

        System.out.println("--- FINAL STATE ---");
        System.out.println("---Patients---");
        System.out.println(patient1);
        System.out.println(patient2);
        System.out.println(patient3);
        System.out.println();
        System.out.println("---Doctors---");
        System.out.println(doctor1);
        System.out.println(doctor2);
        System.out.println(doctor3);
        System.out.println();
        System.out.println("---Appointments---");
        System.out.println(appointment1);
        System.out.println(appointment2);
        System.out.println();
        System.out.println("\n=== Program Complete ===");
    }
}
