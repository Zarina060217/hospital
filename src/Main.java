import java.time.LocalDateTime;

public class Main{
    public static void main(String[] args){
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
        System.out.println("Doctor 3 specialization:"+doctor1.getSpecialization());
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
        LocalDateTime newDate=LocalDateTime.of(2023,12,30,9,0);
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
