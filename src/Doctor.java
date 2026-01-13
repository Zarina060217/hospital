public class Doctor extends medicalStaff{
    private String specialization;
    private static int nextDid=1;
    private static String generateDid(){
        return String.format("D%02d", nextDid++);
    }
    public Doctor(String staffid, String name,double salary,int experienceYears,String specialization){
        super((staffid==null || staffid.trim().isEmpty())?generateDid():staffid,name,salary,experienceYears);
        setSpecialization(specialization);

    }
    public String getSpecialization(){return specialization;}
    public void setSpecialization(String specialization){
        this.specialization=specialization;
    }
    @Override
    public void setStaffid(String staffid){
        if(staffid !=null && staffid.length()>=3){
            boolean startsWithD=staffid.startsWith("D");
            String numericpart=staffid.substring(1);
            boolean restdigits=numericpart.chars().allMatch(c->c>=48 && c<=57);
            if (startsWithD && restdigits){
                this.staffid=staffid;
                return;
            }
        }System.out.println("Invalid doctorid: Must start with 'D' followed by digits(ex: D01)");
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
