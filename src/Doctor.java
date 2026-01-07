public class Doctor extends medicalStaff{
    private String specialization;
    public Doctor(String staffid, String name,double salary,int experienceYears,String specialization){
        super(staffid, name, salary, experienceYears);
        setSpecialization(specialization);

    }
    public String getSpecialization(){return specialization;}
    public void setSpecialization(String specialization){
        this.specialization=specialization;
    }

    public void setDoctorid(String doctorid){
        if(doctorid !=null && doctorid.length()>=3){
            boolean startsWithD=doctorid.startsWith("D");
            String numericpart=doctorid.substring(1);
            boolean restdigits=numericpart.chars().allMatch(c->c>=48 && c<=57);
            if (startsWithD && restdigits){
                this.staffid=doctorid;
                return;
            }
        }System.out.println("Invalid doctorid: Must start with 'D' followed by digits(ex: D01)");
    }
    @Override
    public void work(){
        System.out.println("Doctor "+name+" is working in "+specialization+ "department.");
    }
    @Override
    public String getrole(){return "Doctor";}
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
