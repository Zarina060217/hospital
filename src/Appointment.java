import java.time.LocalDateTime;

public class Appointment {
    private String appointmentID;
    private String patientName;
    private String doctorName;
    private LocalDateTime date;
    private String status = "Scheduled";

    public Appointment(String appointmentID, String patientName, String doctorName, LocalDateTime date, String status) {
        this.appointmentID = appointmentID;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.date = date;
        this.status = status;
    }

    public String getAppointmentID() {
        return appointmentID;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public void setAppointmentID(String AppointmentID) {
        this.appointmentID = appointmentID;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void reschedule(LocalDateTime newdate) {
        if (newdate.isAfter(LocalDateTime.now())) {
            this.date = newdate;
            this.status = "Rescheduled";
        } else {
            System.out.println("Error:Can't reschedule to time in past!");
        }
    }

    public void cancel() {
        this.status = "Cancelled";
    }

    public String toString() {
        return "Appointment{appointmentID='" + appointmentID + "', patientName=" + patientName + ",doctorName='" + doctorName + "',date=" + date + ",status='" + status + "'}";
    }
}