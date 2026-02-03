package database;
import model.Patient;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StaffDAO {
    public void insertPatient(Patient patient){
        String sql="INSERT INTO patient (iin ,fullname,age,bloodtype) VALUES(?,?,?,?)";
        Connection connection=DatabaseConnection.getConnection();
        try{
            PreparedStatement statement=connection.prepareStatement(sql);
            statement.setString(1,patient.getIin());
            statement.setString(2,patient.getFullname());
            statement.setInt(3,patient.getAge());
            statement.setString(4,patient.getBloodtype());
            int rowsInserted=statement.executeUpdate();
            if(rowsInserted>0){
                System.out.println("patient inserted sucessfully");
            }statement.close();
        }catch(SQLException e){
            System.out.println("Insert failed");
            e.printStackTrace();
        }finally{
            DatabaseConnection.closeConnection(connection);
        }
    }public void getAllPatient(){
        String sql="SELECT * FROM patient";
        Connection connection=DatabaseConnection.getConnection();
        try{
            PreparedStatement statement=connection.prepareStatement(sql);
            ResultSet resultSet=statement.executeQuery();
            System.out.println("\n---All patients from database");
            while(resultSet.next()){
                String iin=resultSet.getString("iin");
                String fullname=resultSet.getString("fullname");
                int age=resultSet.getInt("age");
                String bloodtype=resultSet.getString("bloodtype");
                System.out.println("IIN: "+iin);
                System.out.println("Fullname: "+fullname);
                System.out.println("Age: "+age);
                System.out.println("Bloodtype: "+bloodtype);
                System.out.println("---");
            }resultSet.close();
            statement.close();
        }catch(SQLException e){
            System.out.println("Select failed");
            e.printStackTrace();
        }finally{
            DatabaseConnection.closeConnection(connection);
        }
    }public boolean updatePatient(Patient patient){
        String sql="UPDATE patient SET fullname=?,age=?,bloodtype=? WHERE iin=?";
        Connection connection=DatabaseConnection.getConnection();
        try{
            PreparedStatement statement=connection.prepareStatement(sql);
            statement.setString(1,patient.getFullname());
            statement.setInt(2,patient.getAge());
            statement.setString(3,patient.getBloodtype());
            statement.setString(4,patient.getIin());//where condition
            int rowsUpdated=statement.executeUpdate();
            statement.close();
            if(rowsUpdated>0){
                System.out.println("patient updated");
                return true;
            }
        }catch(SQLException e){
            System.out.println("Update failed");
            e.printStackTrace();
        }finally{
            DatabaseConnection.closeConnection(connection);
        }return false;
    }
    public boolean deletePatient(String iin){
        String sql="DELETE FROM patient WHERE iin=?";
        Connection connection=DatabaseConnection.getConnection();
        try{
            PreparedStatement statement=connection.prepareStatement(sql);
            statement.setString(1,iin);
            int rowsDeleted=statement.executeUpdate();
            statement.close();
            if(rowsDeleted>0){
                System.out.println("patient deleted");
                return true;
            }else{
                System.out.println("no patient with such iin");
            }
        }catch(SQLException e){
            System.out.println("delete failed");
            e.printStackTrace();
        }finally{
            DatabaseConnection.closeConnection(connection);
        }return false;
    }public Patient getPatientByIin(String iin){
        String sql="SELECT * FROM patient WHERE iin=?";
        Connection connection=DatabaseConnection.getConnection();
        try{
            PreparedStatement statement=connection.prepareStatement(sql);
            statement.setString(1,iin);
            ResultSet resultSet=statement.executeQuery();
            if(resultSet.next()) {
                return new Patient(
                        resultSet.getString("iin"),
                        resultSet.getString("fullname"),
                        resultSet.getInt("age"),
                        resultSet.getString("bloodtype")
                );
            }
            resultSet.close();
            statement.close();
        }catch(SQLException e){
            System.out.println("failed to load patient");
            e.printStackTrace();
        }finally{
            DatabaseConnection.closeConnection(connection);
        }return null;
    }
    public List<Patient> searchByFullname(String fullname){
        List<Patient> patients=new ArrayList<>();
        String sql="SELECT * FROM patient WHERE fullname ILIKE ? ORDER BY fullname";
        Connection connection=DatabaseConnection.getConnection();
        try{
            PreparedStatement statement=connection.prepareStatement(sql);
            statement.setString(1,"%"+fullname+"%");
            ResultSet resultSet=statement.executeQuery();
            while(resultSet.next()){
                patients.add(new Patient(
                        resultSet.getString("iin"),
                        resultSet.getString("fullname"),
                        resultSet.getInt("age"),
                        resultSet.getString("bloodtype")
                ));
            }
            resultSet.close();
            statement.close();
        }catch(SQLException e){
            System.out.println("failed during search");
            e.printStackTrace();
        }finally{
            DatabaseConnection.closeConnection(connection);
        }return patients;
    }
    public List<Patient> searchByAgeRange(int min, int max){
        List<Patient> patients=new ArrayList<>();
        String sql="SELECT * FROM patient WHERE age BETWEEN ? AND ? ORDER BY age";
        Connection connection=DatabaseConnection.getConnection();
        try{
            PreparedStatement statement=connection.prepareStatement(sql);
            statement.setInt(1,min);
            statement.setInt(2,max);
            ResultSet resultSet=statement.executeQuery();
            while(resultSet.next()){
                patients.add(new Patient(
                        resultSet.getString("iin"),
                        resultSet.getString("fullname"),
                        resultSet.getInt("age"),
                        resultSet.getString("bloodtype")
                ));
            }resultSet.close();
            statement.close();
        }catch(SQLException e){
            System.out.println("failed during search");
            e.printStackTrace();
        }finally{
            DatabaseConnection.closeConnection(connection);
        }return patients;
    }
    public List<Patient> searchByBloodtype(String bgroup){
        List<Patient> patients=new ArrayList<>();
        String sql="SELECT * FROM patient WHERE bloodtype=? ORDER BY fullname";
        Connection connection=DatabaseConnection.getConnection();
        try{
            PreparedStatement statement=connection.prepareStatement(sql);
            statement.setString(1,bgroup);
            ResultSet resultSet=statement.executeQuery();
            while(resultSet.next()){
                patients.add(new Patient(
                        resultSet.getString("iin"),
                        resultSet.getString("fullname"),
                        resultSet.getInt("age"),
                        resultSet.getString("bloodtype")
                ));
            }resultSet.close();
            statement.close();
        }catch(SQLException e){
            System.out.println("failed during search");
            e.printStackTrace();
        }finally{
            DatabaseConnection.closeConnection(connection);
        }return patients;
    }
}
