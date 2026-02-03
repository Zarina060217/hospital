package model;
public class Patient {
    private String iin;
    private String fullname;
    private int age;
    private String bloodtype ;
    public Patient(String iin, String fullname,int age, String bloodtype){
        setIin(iin);
        setFullname(fullname);
        setAge(age);
        setBloodtype(bloodtype);
    }
    public String getIin(){
        return iin;
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

    public void setIin(String iin){
        if(iin.length()!=12 && !iin.chars().allMatch(c ->c>=48 && c<=57)){
           throw new IllegalArgumentException("invalid IIN, it must contain 12 digits");
        }this.iin=iin;
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
    public void setBloodtype(String bloodtype){
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

    public String getDonorCompatibility() {
        switch (this.bloodtype) {
            case "O" -> { return fullname + " is a universal donor"; }
            case "A"-> { return fullname + " is a donor for A and AB"; }
            case "B" -> { return fullname + " is a donor for B and AB"; }
            case "AB" -> { return fullname + " is a universal recipient"; }
            default -> { return "Unknown blood type"; }
        }
    }
    public String toString(){
        return "Patient{IIN='"+iin+"', fullname="+fullname+", age='"+age+"',bloodttype="+bloodtype+"}";
    }
}