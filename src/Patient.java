public class Patient {
    private String IIN;
    private String fullname;
    private int age;
    private Bloodtype bloodtype ;
    public Patient(String IIN, String fullname,int age, Bloodtype bloodtype){
        setIIN(IIN);
        setFullname(fullname);
        setAge(age);
        setBloodtype(bloodtype);
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
    public Bloodtype getBloodtype(){
        return bloodtype;
    }

    public void setIIN(String IIN){
        if(IIN.length()==12 && IIN.chars().allMatch(c ->c>=48 && c<=57)){
            this.IIN=IIN;
        }else{
            System.out.println("invalid IIN, it must contain 12 digits");
        }
    }
    public void setFullname(String fullname){
        if(fullname!=null && !fullname.trim().isEmpty()){
            this.fullname=fullname;
        }else{
            System.out.println("Name can't be empty!");
        }
    }
    public void setAge(int age){
        if(age>=0 && age<=150){
            this.age=age;
        }else{
            System.out.println("Invalid age");
        }
    }
    public void setBloodtype(Bloodtype bloodtype){
        if(bloodtype!=null){
            this.bloodtype=bloodtype;
        }else{
            System.out.println("Bloodtype can't be null");
        }
    }

    public boolean isMinor(){
        return age<18;
    }
    public String getCategory(){
        if (age<18) return "infant";
        if (age>63) return "Retired";
        else return "Adult";
    }
    public enum Bloodtype {
        O, A, B, AB
    }
    public String getDonorCompatibility() {
        switch (this.bloodtype) {
            case O -> { return fullname + " is a universal donor"; }
            case A -> { return fullname + " is a donor for A and AB"; }
            case B -> { return fullname + " is a donor for B and AB"; }
            case AB -> { return fullname + " is a universal recipient"; }
            default -> { return "Unknown blood type"; }
        }
    }
    public String toString(){
        return "Patient{IIN='"+IIN+"', fullname="+fullname+", age='"+age+"',bloodttype="+bloodtype+"}";
    }
}