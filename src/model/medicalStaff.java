package model;
public abstract class medicalStaff {
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

    public void setStaffid(String staffid){
        if(staffid==null || staffid.trim().isEmpty()){
            throw new IllegalArgumentException("Staffid can't be null");
        }this.staffid=staffid;
    }
    public void setName(String name){
        if(name==null || name.trim().isEmpty()){
            throw new IllegalArgumentException("name can't be empty");
        }
        if(name.length()<2){
            throw new IllegalArgumentException("Name must be at least 2 characters");
        }
        this.name=name;
    }
    public void setSalary(double salary){
        if(salary<0){
            throw new IllegalArgumentException("Salary can't be negative value");
        }this.salary=salary;
    }
    public void setExperienceYears(int experienceYears){
        if (experienceYears<0){
            throw new IllegalArgumentException("Experience years can't be negative");
        }this.experienceYears=experienceYears;
    }
    public abstract void work();
    public abstract String getrole();
    public double SalaryUp(){
        if(experienceYears>=30) salary*=1.15;
        else if (experienceYears>=20) salary*=1.25;
        else if(experienceYears>=10) salary*=1.35;
        return salary;
    }
    public String toString(){
        return "["+getrole()+"]"+name+"(Id: "+staffid +", Salary: "+salary+ "KZT, Experience: "+experienceYears +"years)";
    }

}
