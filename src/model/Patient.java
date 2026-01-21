package model;
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
        if(IIN.length()!=12 && !IIN.chars().allMatch(c ->c>=48 && c<=57)){
           throw new IllegalArgumentException("invalid IIN, it must contain 12 digits");
        }this.IIN=IIN;
    }
    public void setFullname(String fullname){
        if(fullname==null || fullname.trim().isEmpty()){
            throw new IllegalArgumentException("Name can't be empty!");
        }this.fullname=fullname;
    }
    public void setAge(int age){
        if(age<0 || age>150){
            throw new IllegalArgumentException("Invalid age");
        }this.age=age;
    }
    public void setBloodtype(Bloodtype bloodtype){
        if(bloodtype==null){
            throw new IllegalArgumentException("Bloodtype can't be null");
        }this.bloodtype=bloodtype;
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