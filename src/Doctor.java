public class Doctor {
    private String doctorid;
    private String name;
    private String specialization;
    private int experienceYears;
    public Doctor(String doctorid, String name,String specialization, int experienceYears){
        this.doctorid=doctorid;
        this.name=name;
        this.specialization=specialization;
        this.experienceYears=experienceYears;
    }
    public String getDoctorid(){
        return doctorid;
    }
    public String getName(){
        return name;
    }
    public String getSpecialization(){
        return specialization;
    }
    public int getExpeienceYears(){
        return experienceYears;
    }
    public void setDoctorid(doctorid){
        this.doctorid=doctorid;
    }
    public void setName(name){
        this.name=name;
    }
    public void setSpecialization(specialization){
        this.specialization=specialization;
    }
    public void setExperienceYears(experienceYears){
        this.experienceYears;
    }
    public boolean isExperienced(){
        return experienceYears>10;
    }
    public boolean canPerformSurgery(){
        return specialization="surgery";
    }
    public String toString(){
        return "Doctor{doctorid='"+doctorid+"', name="+name+", specialization='"+specialization+"', experienxeYears="+experienceYears+"}";
    }

}
