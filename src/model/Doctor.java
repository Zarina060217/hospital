package model;
public class Doctor extends medicalStaff{
    private String specialization;
    public Doctor(String staffid, String name,double salary,int experienceYears,String specialization){
        super(staffid,name,salary,experienceYears);
        setSpecialization(specialization);
    }
    public String getSpecialization(){return specialization;}
    public void setSpecialization(String specialization){
        if(specialization==null||specialization.trim().isEmpty()){
            throw new IllegalArgumentException("Specialization can't be null");
        }
    }
    @Override
    public void work(){
        System.out.println("Doctor "+name+" is working in "+specialization+ " department.");
    }
    @Override
    public String getrole(){return "Doctor";}
    @Override
    public double SalaryUp(){
        super.SalaryUp();
        System.out.println("Doctor salary due experience: "+salary);
        return salary;
    }
    public boolean isExperienced(){
        return experienceYears>10;
    }
    public boolean canPerformSurgery(){
        return "surgery".equalsIgnoreCase(specialization);
    }
    @Override
    public String toString(){
        return super.toString()+"| Specialization: "+specialization;
    }
}
