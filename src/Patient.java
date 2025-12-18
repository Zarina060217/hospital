public class Patient {
    private String IIN;
    private String fullname;
    private int age;
    private String bloodtype ;
    public Patient(String IIN, String fullname,int age, String bloodtype){
        this.IIN=IIN;
        this.fullname=fullname;
        this.age=age;
        this.bloodtype=bloodtype;
    }
    public String getIIN(){
        return IIN;
    }
    public String getFullname(){
        return fullname;
    }
    public int getAge(){
        return age;
    }
    public String getBloodtype(){
        return bloodtype;
    }
    public void setIIN(IIN){
        this.IIN=IIN;
    }
    public void setFullname(fullname){
        this.fullname=fullname;
    }
    public void setAge(age){
        this.age=age;
    }
    public void setBloodtype(bloodtype){
        this.bloodtype=bloodtype;
    }
    public boolean isMinor(){
        return age<18;
    }
    public void getagecategory(double category){
        return category=age/10;
    }

    public String toString(){
        return "Patient{IIN='"+IIN+"', fullname="+fullname+", age='"+age+"',bloodttype="+bloodtype+"}";
    }
}