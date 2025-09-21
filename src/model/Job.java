package model;

import java.time.LocalDate;

public class Job {
    private String id;
    private String title;
    private String description;
    private String companyId;
    private LocalDate deadline;
    private boolean open;
    private String type; 

    public Job(String id, String title, String description, String companyId, LocalDate deadline, boolean open, String type){
        this.id=id; this.title=title; this.description=description; this.companyId=companyId;
        this.deadline=deadline; this.open=open; this.type=type;
    }

    public String getId(){return id;}
    public String getTitle(){return title;}
    public String getDescription(){return description;}
    public String getCompanyId(){return companyId;}
    public LocalDate getDeadline(){return deadline;}
    public boolean isOpen(){return open;}
    public String getType(){return type;}
    public boolean isInternship(){ return type.equalsIgnoreCase("Coop"); }

    public String toString(){return id + " - " + title + " (" + type + ")";}
}
