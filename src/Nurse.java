public class Nurse extends medicalStaff{
    private String shiftType;
    private boolean isHeadNurse;
    public Nurse(String staffid, String name, double salary,int experienceYears,String shiftType,boolean isHeadNurse){
        super(staffid, name, salary,experienceYears);
        this.shiftType = shiftType;
        this.isHeadNurse = isHeadNurse;
    }
    public String getShiftType(){return shiftType;}
    public void setShiftTypes(String shiftType){this.shiftType=shiftType;}
    public boolean isHeadNurse(){return isHeadNurse;}
    public void setHeadNurse(boolean headNurse){isHeadNurse=headNurse;}
    public void setNurseid(String nurseid){
        if(nurseid!=null && nurseid.length()>=3){
          boolean startsWithN=nurseid.startsWith("N");
          String numericpart=nurseid.substring(1);
          boolean restdigits=numericpart.chars().allMatch(c->c>=48 && c<=57);
          if(startsWithN && restdigits){
              this.staffid=nurseid;
              return;
          }
        }System.out.println("Invalid nurseid: Must start with 'N' followed by digits(ex:N01)");
    }
    public double Countingbonus(){
        double bonus=0;
        if (isHeadNurse) bonus+=20000.0;
        if ("night".equalsIgnoreCase(shiftType)) bonus+=15000.0;
        if ("holiday".equalsIgnoreCase(shiftType)) bonus+=25000.0;
        return bonus;
    }
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
        System.out.println(getrole()+name+" takes care of the patients.");
    }
    @Override
    public String toString(){
        return super.toString()+"| Shift: "+shiftType+"| HeadNurse: "+isHeadNurse;
    }

}
