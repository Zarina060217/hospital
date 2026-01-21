package menu;
import model.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
public class HospitalMenu implements Menu{
    private ArrayList<medicalStaff> allStaff;
    private ArrayList<Patient> patients;
    private ArrayList<Appointment> appointments;
    private Scanner scanner;
    public HospitalMenu(){
        this.allStaff=new ArrayList<>();
        this.scanner=new Scanner(System.in);
        this.patients=new ArrayList<>();
        this.scanner=new Scanner(System.in);
        this.appointments=new ArrayList<>();
        this.scanner=new Scanner(System.in);
        System.out.println("===Hospital Management System===");
        Patient patient1 = new Patient("060217789123", "Dosymova Zhansaya", 17, Patient.Bloodtype.AB);
        Patient patient2 = new Patient("091207589632", "Beisen Bori", 13, Patient.Bloodtype.O);
        Patient patient3 = new Patient("100511789583", "Nazar Hadjar", 13, Patient.Bloodtype.A);
        patients.add(patient1);
        patients.add(patient2);
        patients.add(patient3);
        allStaff.add(new Doctor("D01","Zamira Beisenova",700000.0,35,"Therapy"));
        allStaff.add(new Doctor("D02","Yesirkep Yelnaz",450000,2,"gynecology"));
        allStaff.add(new Nurse("N01","Turan Railya",300000,5,"day",true));
        LocalDateTime date = LocalDateTime.of(2026, 3, 14, 11, 35);
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
    }
    @Override
    public void displayMenu() {
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
    }

    @Override
    public void run() {
        boolean running = true;
        while (running) {
            displayMenu();
            System.out.print("Enter your choice: ");
            try{
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1: addPatient(); break;
                    case 2: viewPatients(); break;
                    case 3: addDoctor(); break;
                    case 4: addNurse(); break;
                    case 5: viewAllStaff(); break;
                    case 6: demonstratePolymorphism(); break;
                    case 7: viewDoctors(); break;
                    case 8: viewNurses(); break;
                    case 9: addAppointment(); break;
                    case 10: viewAppointments(); break;
                    case 0:
                        System.out.println("\nGoodbye!");
                        running = false;
                        break;
                    default: System.out.println("\n Invalid choice");
                }
            }catch(Exception e){
                System.out.println("Error: "+e.getMessage());
                scanner.nextLine();
            }
            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
        scanner.close();
    }
    private void addPatient(){
        System.out.println("\n---ADD PATIENTS---");
        try{
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
        }catch (IllegalArgumentException e){
            System.out.println("Error: "+e.getMessage());
        }
    }
    private void viewPatients(){
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
            System.out.println("Fullname: "+patient.getFullname());
            System.out.println("Age: "+patient.getAge());
            System.out.println("Bloodtype: "+patient.getBloodtype());
            System.out.println(patient.getDonorCompatibility());
            System.out.println(patient.getCategory());
            if (patient.isMinor()){
                System.out.println("Treatment is free");
            }System.out.println();
        }
    }
    public medicalStaff findStaffById(String id){
        for(medicalStaff s: allStaff){
            if(s.getStaffid()!=null && s.getStaffid().equalsIgnoreCase(id)){
                return s;
            }
        }return null;
    }
    private void addDoctor(){
        try{
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
        }catch (IllegalArgumentException e){
            System.out.println("Error: "+e.getMessage());
        }
    }private void viewDoctors(){
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
                double uppedSalary=doctor.SalaryUp();
                System.out.println("Increased Salary: "+uppedSalary);
                System.out.println();
                if(doctor.canPerformSurgery()){
                    System.out.println("Authorized to perform surgical procedures.");
                }
            }
        }
        if(doccount==0){
            System.out.println("No doctors found");
        }
    }
    private void addNurse(){
        try{
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
        }catch (IllegalArgumentException e){
            System.out.println("Error: "+e.getMessage());
        }
    }
    private void viewNurses(){
        System.out.println("\\n========================================");
        System.out.println("                ALL NURSES");
        System.out.println("===========================================");
        int ncount=0;
        for(medicalStaff s : allStaff){
            if(s instanceof Nurse){//filter by type
                Nurse nurse=(Nurse) s;//downcast to access nurse methods
                ncount++;
                System.out.println("Id "+nurse.getStaffid());
                try{
                    nurse.setStaffid(" ");
                }catch(IllegalArgumentException e){
                    System.out.println("Error: "+e.getMessage());
                }
                System.out.println(". "+nurse.getName());
                System.out.println("Salary: "+nurse.getSalary());
                System.out.println("Experience years: "+nurse.getExperienceYears());
                System.out.println("Shift type: "+nurse.getShiftType());
                if(nurse.isHeadNurse()){
                    System.out.println("Head nurse");
                }
                double shiftSalary=nurse.CountSalary();
                System.out.println("Salary of nurse due shifts taken: "+shiftSalary);
                double upSalary=nurse.SalaryUp();
                System.out.println("Salary of nurse due experience: "+upSalary);
                System.out.println();
            }
        }
        if(ncount==0){
            System.out.println("no nurses found");
        }
    }
    private void addAppointment(){
        try{
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
        }catch (IllegalArgumentException e){
            System.out.println("Error: "+e.getMessage());
        }
    }
    private void viewAppointments(){
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
    private void viewAllStaff(){
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
    private void demonstratePolymorphism(){
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
