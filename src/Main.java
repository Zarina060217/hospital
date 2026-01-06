import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Locale;
public class Main{
    private static ArrayList<Patient> patients=new ArrayList<>();
    private static ArrayList<medicalStaff> allStaff=new ArrayList<>();
    private static ArrayList<Appointment> appointments=new ArrayList<>();
    private static Scanner scanner=new Scanner(System.in).useLocale(Locale.US);

    public static void main(String[] args) {
        System.out.println("===Hospital Management System===");
        Patient patient1 = new Patient("060217789123", "Dosymova Zhansaya", 17, Patient.Bloodtype.AB);
        Patient patient2 = new Patient("091207589632", "Beisen Bori", 13, Patient.Bloodtype.O);
        Patient patient3 = new Patient("100511789583", "Nazar Hadjar", 13, Patient.Bloodtype.A);
        patients.add(patient1);
        patients.add(patient2);
        patients.add(patient3);
        allStaff.add(new Doctor("D01","Beisenova Zamira",550000,37,"therapy"));
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
                    viewPatients();
                    break;
                case 3:
                    addMedStaff();
                    break;
                case 4:
                    addDoctor();
                    break;
                case 5:
                    addNurse();
                    break;
                case 6:
                    viewAllStaff();
                    break;
                case 7:
                    demonstratePolymorphism();
                    break;
                case 8:
                    viewDoctors();
                    break;
                case 9:
                    viewNurses();
                    break;
                case 10:
                    addAppointment();
                    break;
                case 11:
                    viewAppointments();
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
        System.out.println("3. Add Staff member");
        System.out.println("4. Add doctor");
        System.out.println("5. Add nurse");
        System.out.println("6. View All Staff (Polymorphic)");
        System.out.println("7. View All Staff Work(Polymorphism demo)");
        System.out.println("8. View Doctors only");
        System.out.println("9. View Nurses only");
        System.out.println("10.Add Appointment");
        System.out.println("11.View Appointments");
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
    private static void viewPatients(){
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
        System.out.print("Enter a salary: ");
        double salary=scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter experience years: ");
        int experienceYears=scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter a specilaization: ");
        String specialization=scanner.nextLine();
        medicalStaff medstaff=new Doctor(staffid, name, salary, experienceYears,specialization);
        allStaff.add(medstaff);
        System.out.println("\nDoctor added successfully");
    }
    private static void viewDoctors(){
        System.out.println("\\n========================================");
        System.out.println("                ALL DOCTORS");
        System.out.println("===========================================");
        int doccount=0;
        for (medicalStaff s : allStaff){
            if(s instanceof Doctor){
                Doctor doctor = (Doctor) s;
                doccount++;
                System.out.println(doccount+". "+doctor.getName());
                System.out.println("Specialization: "+doctor.getSpecialization());
                System.out.println("Experience years: "+doctor.getExperienceYears()+" years");
                if(doctor.isExperienced()){
                    System.out.println("Senior doctor");
                }
                System.out.println();
            }
        }
        if(doccount==0){
            System.out.println("No doctors found");
        }
    }
    private static void addNurse(){
        System.out.println("\n---ADD NURSE---");
        System.out.print("Enter nurseid: ");
        String staffid=scanner.nextLine();
        System.out.print("Enter nurse name: ");
        String name=scanner.nextLine();
        System.out.print("Enter a salary: ");
        double salary=scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter experience years: ");
        int experienceYears=scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter a shiftType: ");
        String shiftType=scanner.nextLine();
        System.out.print("Is head nurse: ");
        boolean isHeadNurse=scanner.nextBoolean();
        scanner.nextLine();
        medicalStaff s=new Nurse(staffid, name, salary, experienceYears, shiftType, isHeadNurse);
        allStaff.add(s);
        System.out.println("\n Nurse added successfully!");

    }
    private static void viewNurses(){
        System.out.println("\\n========================================");
        System.out.println("                ALL NURSES");
        System.out.println("===========================================");
        int ncount=0;
        for(medicalStaff s : allStaff){
            if(s instanceof Nurse){//filter by type
                Nurse nurse=(Nurse) s;//downcast to access nurse methods
                ncount++;
                System.out.println(ncount+". "+nurse.getName());
                System.out.println("Salary: "+nurse.getSalary());
                System.out.println("Experience years: "+nurse.getExperienceYears());
                System.out.println("Shift type: "+nurse.getShiftType());
                if(nurse.isHeadNurse()){
                    System.out.println("Head nurse");
                }
                System.out.println();
            }
        }
        if(ncount==0){
            System.out.println("no nurses found");
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
    private static void viewAppointments(){
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
    private static void addMedStaff(){
        System.out.println("\n---ADD STAFF---");
        System.out.print("Enter medstaff id: ");
        String staffid=scanner.nextLine();
        System.out.print("Enter medstaff name: ");
        String name=scanner.nextLine();
        System.out.print("Enter salary: ");
        double salary=0;
        if(scanner.hasNextDouble()){
            salary=scanner.nextDouble();
        }else{
            System.out.println("Invalid number format for salary!");
            scanner.nextLine();
            return;
        }scanner.nextLine();
        System.out.print("Enter experience years: ");
        int experienceYears=0;
        if(scanner.hasNextInt()){
            experienceYears=scanner.nextInt();
        }scanner.nextLine();
        medicalStaff s=new medicalStaff(staffid,name,salary,experienceYears);
        allStaff.add(s);
        System.out.println("New medstaff added successfully");
    }
    private static void viewAllStaff(){
        System.out.println("\n========================================");
        System.out.println("    ALL STAFF (POLYMORPHIC LIST)");
        System.out.println("========================================");
        if(allStaff.isEmpty()){
            System.out.println("No staff members found");
            return;
        }System.out.println("Total staff: "+allStaff.size());
        System.out.println();
        for(int i=0;i<allStaff.size();i++){
            medicalStaff s = allStaff.get(i);
            System.out.println((i+1)+". "+s);//calls overriden toString()
            if (s instanceof Doctor){
                Doctor doctor=(Doctor) s;//downcasting
                if(doctor.isExperienced()){
                    System.out.println("Senior doc with decade+ experience");
                }
            }else if(s instanceof Nurse){
                Nurse nurse=(Nurse) s;
                if(nurse.isHeadNurse()){
                    System.out.println("Head nurse of department");
                }
            }
            System.out.println();
        }
    }
    private static void demonstratePolymorphism(){
        System.out.println("\n========================================");
        System.out.println("   POLYMORPHISM DEMONSTRATION");
        System.out.println("========================================");
        System.out.println("Calling work() on all staff members:");
        System.out.println();
        for (medicalStaff s : allStaff){
            s.work();
        }System.out.println();
        System.out.println(" Notice: Same method name (work), different output!");
        System.out.println("   This is POLYMORPHISM in action!");
    }

}