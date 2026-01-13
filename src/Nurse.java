public class Nurse extends medicalStaff{
    private String shiftType;
    private boolean isHeadNurse;
    private static int nextNid=1;
    private static String generateNid(){
        return String.format("N%02d",nextNid++);
    }
    public Nurse(String staffid, String name, double salary,int experienceYears,String shiftType,boolean isHeadNurse){
        super((staffid==null || staffid.trim().isEmpty())?generateNid():staffid, name, salary,experienceYears);
        setShiftType(shiftType);
        setHeadNurse(isHeadNurse);
    }
    public String getShiftType(){return shiftType;}
    public void setShiftType(String shiftType){this.shiftType=shiftType;}
    public boolean isHeadNurse(){return isHeadNurse;}
    public void setHeadNurse(boolean headNurse){isHeadNurse=headNurse;}
    public void setStaffid(String staffid){
        if(staffid!=null && staffid.length()>=3){
          boolean startsWithN=staffid.startsWith("N");
          String numericpart=staffid.substring(1);
          boolean restdigits=numericpart.chars().allMatch(c->c>=48 && c<=57);
          if(startsWithN && restdigits){
              this.staffid=staffid;
              return;
          }
        }System.out.println("Invalid nurse id: Must start with 'N' followed by digits(ex:N01)");
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
