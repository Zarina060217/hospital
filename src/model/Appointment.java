package model;
import java.time.LocalDateTime;
public class Appointment {
    private String appointmentID;
    private String patientName;
    private String doctorName;
    private LocalDateTime date;
    private String status = "Scheduled";

    public Appointment(String appointmentID, String patientName, String doctorName, LocalDateTime date, String status) {
        setAppointmentID(appointmentID);
        setPatientName(patientName);
        setDoctorName(doctorName);
        setDate(date);
        setStatus(status);
    }
    public String getAppointmentID(){return appointmentID;}
    public String getPatientName(){return patientName;}
    public String getDoctorName(){return doctorName;}
    public LocalDateTime getDate(){return date;}
    public String getStatus(){return status;}

    public void setAppointmentID(String appointmentID) {
        if (appointmentID == null || appointmentID.trim().isEmpty()) {
            throw new IllegalArgumentException("Appointment id can't be null");
        }this.appointmentID=appointmentID;
    }
        public void setPatientName(String patientName) {
            if(patientName==null || patientName.trim().isEmpty()){
                throw new IllegalArgumentException("Patient name can't be null");
            }this.patientName = patientName;
        }
        public void setDoctorName(String doctorName) {
            if(doctorName==null || doctorName.trim().isEmpty()){
                throw new IllegalArgumentException("Doctor name can't be null");
            }this.doctorName = doctorName;
        }
        public void setDate(LocalDateTime date) {
            LocalDateTime now=LocalDateTime.now();
            if(date==null){System.out.println("Date can't be null");}
            else if(date.isBefore(now)){System.out.println("Appointment date must be in future!");}
            else{this.date=date;}
        }
        public void setStatus(String status) {
            if(status==null || status.trim().isEmpty()){
                throw new IllegalArgumentException("Status mustn't be empty");
            }this.status = status;
        }

        public void reschedule(LocalDateTime newdate) {
            if (newdate.isAfter(LocalDateTime.now())) {
                this.date = newdate;
                this.status = "Rescheduled";
            } else {
                System.out.println("Error:Can't reschedule to time in past!");
            }
        }

        public void cancel() {this.status = "Cancelled";}

        public String toString() {return "Appointment{appointmentID='" + appointmentID + "', patientName=" + patientName + ",doctorName='" + doctorName + "',date=" + date + ",status='" + status + "'}";}
    }