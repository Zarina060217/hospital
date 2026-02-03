package database;
import model.Patient;
public class TestInsert {
    public static void main(String[] args) {
        Patient patient = new Patient("060217600941", "Abibulla Zarina", 19, "AB");
        StaffDAO dao = new StaffDAO();
        dao.insertPatient(patient);
    }
}
