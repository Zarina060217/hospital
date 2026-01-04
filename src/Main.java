import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main{
    private static ArrayList<Patient> patients=new ArrayList<>();
    private static ArrayList<medicalStaff> allStaff=new ArrayList<>();
    private static ArrayList<Appointment> appointments=new ArrayList<>();
    private static Scanner scanner=new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("===Hospital Management System===");
        Patient patient1 = new Patient("060217789123", "Dosymova Zhansaya", 17, Patient.Bloodtype.AB);
        Patient patient2 = new Patient("091207589632", "Beisen Bori", 13, Patient.Bloodtype.O);
        Patient patient3 = new Patient("100511789583", "Nazar Hadjar", 13, Patient.Bloodtype.A);
        patients.add(patient1);
        patients.add(patient2);
        patients.add(patient3);
        allStaff.add(new Doctor("D01","Beisenova Zamira",550000,37,"theary"));
        allStaff.add(new Doctor("D02","Yesirkep Yelnaz",450000,2,"gynecology"));
        allStaff.add(new Nurse("N01","Turan Railya",300000,5,"day",true));
        LocalDateTime date = LocalDateTime.of(2025, 12, 28, 14, 0);
        medicalStaff doc1=findStaffById("D01");
        medicalStaff doc2=findStaffById("D02");
        if (doc1!=null){
            appointments.add(new Appointment("A01",patient2.getFullname(), doc1.getName(), date, "scheduled"));
        }
        if(doc2!=null){
            appointments.add(new Appointment("A02", patient3.getFullname(),doc2.getName(), date, "scheduled"));
        }
        for (medicalStaff s : allStaff){
            s.work();
        }
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
        }
        scanner.close();
    }
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
        System.out.print("Enter bloodtype(O,A,B,AB): ");
        String bloodInput=scanner.nextLine().toUpperCase();
        Patient.Bloodtype bloodtype=Patient.Bloodtype.valueOf(bloodInput);
        Patient patient=new Patient(IIN, fullname, age, bloodtype);
        patients.add(patient);
        System.out.println("\nPatient added successfully");
    }
    private static void viewAllPatients(){
        System.out.println("\\n========================================");
        System.out.println("                ALL PATIENTS");
        System.out.println("===========================================");
        if (patients.isEmpty()){
            System.out.println("No patients found.");
            return;
        }System.out.println("Total number of patients: "+patients.size());
        System.out.println();
        for(int i=0;i<patients.size();++i){
            Patient patient=patients.get(i);
            System.out.println((i+1)+". "+patient.getIIN());
            System.out.println("; Fullname: "+patient.getFullname());
            System.out.println("; Age: "+patient.getAge());
            System.out.println("; Bloodtype: "+patient.getBloodtype());
            if (patient.isMinor()){
                System.out.println("Treatment is free");
            }System.out.println();
        }
    }
    public static medicalStaff findStaffById(String id){
        for(medicalStaff s: allStaff){
            if(s.getStaffid()!=null && s.getStaffid().equalsIgnoreCase(id)){
                return s;
            }
        }return null;
    }
    private static void addDoctor(){
        System.out.println("\n---ADD DOCTOR---");
        System.out.print("Enter doctorid: ");
        String staffid=scanner.nextLine();
        System.out.print("Enter doctor name: ");
        String name=scanner.nextLine();
        System.out.print("Enter a specilaization: ");
        String specialization=scanner.nextLine();
        System.out.print("Enter a salary: ");
        double salary=scanner.nextDouble();
        System.out.print("Enter experience years: ");
        int experienceYears=scanner.nextInt();
        allStaff.add(new Doctor(staffid,name, salary, experienceYears,specialization));
        System.out.println("\nDoctor added successfully");
    }
    private static void viewAllDoctors(){
        System.out.println("\\n========================================");
        System.out.println("                ALL DOCTORS");
        System.out.println("===========================================");
        for (medicalStaff s : allStaff){
            if(s instanceof Doctor){
                System.out.println(s.toString());
            }
        }
    }
    private static void addAppointment(){
        System.out.println("\n---ADD APPOINTMENT---");
        System.out.print("Enter appointmentid: ");
        String appointmentID=scanner.nextLine();
        System.out.print("Enter patient name: ");
        String patientName=scanner.nextLine();
        System.out.print("Enter doctor name: ");
        String doctorName=scanner.nextLine();
        System.out.print("Enter appointment date(yyyy-MM-dd HH:mm): ");
        String dateInput=scanner.nextLine();
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime date=LocalDateTime.parse(dateInput,formatter);
        System.out.print("Enter appointment status: ");
        String status=scanner.nextLine();
        Appointment appointment=new Appointment(appointmentID,patientName,doctorName,date,status);
        appointments.add(appointment);
        System.out.println("\nAppointment added successfully!");
    }
    private static void viewAllAppointments(){
        System.out.println("\\n========================================");
        System.out.println("                ALL APPOINTMENTS");
        System.out.println("===========================================");
        if(appointments.isEmpty()){
            System.out.println("No appointments found");
            return;
        }System.out.print("Number of all  appointments: "+appointments.size());
        System.out.println();
        for(int i=0;i<appointments.size();++i){
            Appointment appointment=appointments.get(i);
            System.out.println((i+1)+". "+appointment.getAppointmentID());
            System.out.print("; Patient name: "+appointment.getPatientName());
            System.out.print("; Doctor name: "+appointment.getDoctorName());
            System.out.print("; Appointment date: "+appointment.getDate());
            System.out.print("; Appointment status: "+appointment.getStatus());
            System.out.println();
        }
    }
}