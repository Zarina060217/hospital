public class medicalStaff {
    protected String staffid;
    protected String name;
    protected double salary;
    protected int experienceYears;

    public medicalStaff(String staffid, String name, double salary, int experienceYears){
        setStaffid(staffid);
        setName(name);
        setSalary(salary);
        setExperienceYears(experienceYears);
    }

    public String getStaffid(){return staffid;}
    public String getName(){return name;}
    public double getSalary(){return salary;}
    public int getExperienceYears(){return experienceYears;}

    public void setStaffid(String staffid){this.staffid=staffid;}
    public void setName(String name){
        if(name!=null && !name.trim().isEmpty()){
            this.name=name;
        }else{
            System.out.println("Name can't be empty");
        }
    }
    public void setSalary(double salary){
        if(salary>=0){
            this.salary=salary;
        }else{
            System.out.println("Business rule:no negative salary!");
        }
    }
    public void setExperienceYears(int experienceYears){
        if (experienceYears>=0){
            this.experienceYears=experienceYears;
        }else{
            System.out.println("Experience years must be positive!");
        }
    }
    public void work(){
        System.out.println(name+"is working.");
    }
    public String getrole(){
        return "Medical staff member";
    }
    public double SalaryUp(){
        if(experienceYears>=30) salary*=1.15;
        else if (experienceYears>=20) salary*=1.25;
        else if(experienceYears>=10) salary*=1.35;
        return salary;
    }
    @Override
    public String toString(){
        return "["+getrole()+"]"+name+"(Id: "+staffid +", Salary: "+salary+ "KZT, Experience: "+experienceYears +"years)";
    }

}
