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
    public String getDoctorid(){return doctorid;}
    public String getName(){return name;}
    public String getSpecialization(){return specialization;}
    public int getExpeienceYears(){return experienceYears;}

    public void setDoctorid(String doctorid){
        this.doctorid=doctorid;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setSpecialization(String specialization){
        this.specialization=specialization;
    }
    public void setExperienceYears(int experienceYears){
        if (experienceYears>=0){
            this.experienceYears=experienceYears;
        }else{
            System.out.println("Experience years must be positive!");
        }
    }

    public boolean isExperienced(){
        return experienceYears>10;
    }
    public boolean canPerformSurgery(){
        return "surgery".equalsIgnoreCase(specialization);
    }
    public String toString(){
        return "Doctor{doctorid='"+doctorid+"', name="+name+", specialization='"+specialization+"', experienxeYears="+experienceYears+"}";
    }
}
