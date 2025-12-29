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
        if(doctorid !=null && doctorid.length()>=3){
            boolean startsWithD=doctorid.startsWith("D");
            String numericpart=doctorid.substring(1);
            boolean restdigits=numericpart.chars().allMatch(c->c>=48 && c<=57);
            if (startsWithD && restdigits){
                this.doctorid=doctorid;
            }
        }System.out.println("Invalid doctorid: Must start with 'D' followed by digits(ex: D01)");
    }
    public void setName(String name){
        if(name!=null && !name.trim().isEmpty()){
            this.name=name;
        }else{
            System.out.println("Name can't be empty");
        }
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
