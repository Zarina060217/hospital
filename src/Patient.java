public class Patient {
    private String name;
    private String surname;
    private int IIN;
    private String phonenumber;
    public Patient(String name, String surname,int IIN, String phonenumber){
        this.name=name;
        this.surname=surname;
        this.IIN=IIN;
        this.phonenumber=phonenumber;
    }
    public String getName(){
        return name;
    }
    public String getSurname(){
        return surname;
    }
    public int getIIN(){
        return IIN;
    }
    public String getPhonenumber(){
        return phonenumber;
    }



}
