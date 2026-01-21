package model;
public class Nurse extends medicalStaff implements WageUp{
    private String shiftType;
    private boolean isHeadNurse;
    public Nurse(String staffid, String name, double salary,int experienceYears,String shiftType,boolean isHeadNurse){
        super(staffid, name, salary,experienceYears);
        setShiftType(shiftType);
        setHeadNurse(isHeadNurse);
    }
    public String getShiftType(){return shiftType;}
    public void setShiftType(String shiftType){
        if(shiftType==null ||shiftType.trim().isEmpty()){
            throw new IllegalArgumentException("Shiftype can't be empty");
        }
    }
    public boolean isHeadNurse(){return isHeadNurse;}
    public void setHeadNurse(boolean headNurse){isHeadNurse=headNurse;}
    @Override
    public double Countingbonus(){
        double bonus=0;
        if (isHeadNurse) bonus+=20000.0;
        if ("night".equalsIgnoreCase(shiftType)) bonus+=15000.0;
        if ("holiday".equalsIgnoreCase(shiftType)) bonus+=25000.0;
        return bonus;
    }
    @Override
    public double CountSalary(){
        double baseSalary = 30*12000.0;
        this.salary=baseSalary+Countingbonus();
        return this.salary;
    }
    @Override
    public String getrole(){
        return isHeadNurse? "Head Nurse":"Staff Nurse";
    }
    @Override
    public void work(){
        System.out.println(getrole()+" "+name+" takes care of the patients.");
    }
    @Override
    public double SalaryUp(){
        super.SalaryUp();
        System.out.println("Increased salary:"+salary);
        return salary;
    }
    @Override
    public String toString(){
        return super.toString()+"| Shift: "+shiftType+"| HeadNurse: "+isHeadNurse;
    }

}
