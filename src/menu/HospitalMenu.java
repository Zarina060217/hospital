package menu;
import model.*;
import database.StaffDAO;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
public class HospitalMenu implements Menu{
    private ArrayList<medicalStaff> allStaff;
    private ArrayList<Appointment> appointments;
    private Scanner scanner;
    private StaffDAO staffDAO;
    public HospitalMenu(){
        this.allStaff=new ArrayList<>();
        this.scanner=new Scanner(System.in);
        this.appointments=new ArrayList<>();
        this.scanner=new Scanner(System.in);
        this.staffDAO=new StaffDAO();
        System.out.println("===Hospital Management System===");

        allStaff.add(new Doctor("D01","Zamira Beisenova",700000.0,35,"Therapy"));
        allStaff.add(new Doctor("D02","Yesirkep Yelnaz",450000,2,"gynecology"));
        allStaff.add(new Nurse("N01","Turan Railya",300000,5,"day",true));
        LocalDateTime date = LocalDateTime.of(2026, 3, 14, 11, 35);
        for (medicalStaff s : allStaff){
            s.work();
        }
    }
    @Override
    public void displayMenu() {
        System.out.println("\n========================================");
        System.out.println("  HOSPITAL MANAGEMENT SYSTEM");
        System.out.println("========================================");
        System.out.println("Hospital management");
        System.out.println("1. Add Patient");
        System.out.println("2. View Patients");
        System.out.println("3. Add doctor");
        System.out.println("4. Add nurse");
        System.out.println("5. View All Staff (Polymorphic)");
        System.out.println("6. View All Staff Work(Polymorphism demo)");
        System.out.println("7. View Doctors only");
        System.out.println("8. View Nurses only");
        System.out.println("9.Add Appointment");
        System.out.println("10.View Appointments");
        System.out.println("11.Update Patient");
        System.out.println("12.Delete Patient");
        System.out.println("SEARCH & FILTER");
        System.out.println("13. Search by full name");
        System.out.println("14. Search by age range");
        System.out.println("15. Search by bloodtype");
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
                    case 10:viewAppointments(); break;
                    case 11:updatePatient();break;
                    case 12:deletePatient();break;
                    case 13:searchByFullname();break;
                    case 14:searchByAgeRange();break;
                    case 15:searchByBloodtype();break;
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
            String iin=scanner.nextLine();
            System.out.print("Enter Full name: ");
            String fullname=scanner.nextLine();
            System.out.print("Enter age: ");
            int age=scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter bloodtype(O,A,B,AB): ");
            String bloodtype=scanner.nextLine();
            Patient patient=new Patient(iin, fullname, age, bloodtype);
            staffDAO.insertPatient(patient);
            System.out.println("\nPatient added successfully");
        }catch (java.util.InputMismatchException e){
            System.out.println("Invalid input type");
            scanner.nextLine();
        }catch(IllegalArgumentException e){
            System.out.println("Validation error: "+e.getMessage());
        }
    }
    private void viewPatients(){
        staffDAO.getAllPatient();
    }
    private void updatePatient(){
        System.out.print("Enter IIN to update: ");
        String iin=scanner.nextLine();
        Patient epatient=staffDAO.getPatientByIin(iin);
        if (epatient==null){
            System.out.println("no patient found with iin:"+iin);
            return;
        }System.out.println("current info:");
        System.out.println(epatient.toString());
        System.out.println("New name ["+epatient.getFullname()+"]: ");
        String newName=scanner.nextLine();
        if(newName.trim().isEmpty()){
            newName=epatient.getFullname();
        }
        System.out.println("New age["+epatient+"]: ");
        String ageInput=scanner.nextLine();
        int newAge=ageInput.trim().isEmpty()?epatient.getAge():Integer.parseInt(ageInput);
        System.out.println("New bloodtype["+epatient.getBloodtype()+"]: ");
        String newBlood=scanner.nextLine();
        if(newBlood.trim().isEmpty()){
            newBlood=epatient.getBloodtype();
        }
        Patient upatient=new Patient(iin,newName,newAge,newBlood);
        staffDAO.updatePatient(upatient);
    }
    private void deletePatient(){
        System.out.print("Enter IIN to delete");
        String iin=scanner.nextLine();
        Patient patient=staffDAO.getPatientByIin(iin);
        if(patient==null){
            System.out.println("no patient found with iin:"+iin);
            return;
        }System.out.println("Patient to delete:"+patient);
        System.out.println("Are you sure?(yes/no):");
        String confirm=scanner.nextLine();
        if(confirm.equalsIgnoreCase("yes")){
            if(staffDAO.deletePatient(iin)){
                System.out.println("patient deleted");
            }else{
                System.out.println("fail of deletion");
            }
        }else{
            System.out.println("deletion cancelled");
        }
    }
    private void searchByFullname(){
        System.out.print("Enter full name: ");
        String fullname=scanner.nextLine();
        for(Patient patient:staffDAO.searchByFullname(fullname)){
            System.out.print(patient);
        }
    }
    private void searchByAgeRange(){
        System.out.print("Enter min age: ");
        int min=Integer.parseInt(scanner.nextLine());
        System.out.print("Enter max age: ");
        int max=Integer.parseInt(scanner.nextLine());
        for(Patient patient:staffDAO.searchByAgeRange(min,max)){
            System.out.print(patient);
        }
    }
    private void searchByBloodtype(){
        System.out.print("Enter bloodtype: ");
        String bloodtype=scanner.nextLine();
        for(Patient patient:staffDAO.searchByBloodtype(bloodtype)){
            System.out.print(patient);
        }
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
    }
    private void viewDoctors(){
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
