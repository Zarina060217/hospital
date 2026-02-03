package database;
import model.Patient;
public class TestSelect {
    public static void main(String[] args){
        StaffDAO dao=new StaffDAO();
        dao.getAllPatient();
    }
}
