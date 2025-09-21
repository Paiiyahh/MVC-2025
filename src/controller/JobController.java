package controller;

import model.Job;
import model.Candidate;
import java.time.LocalDate;
import java.util.*;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.UIManager;

public class JobController {
    private List<Job> jobs;

    public JobController(List<Job> jobs){
        this.jobs = jobs;
        // ตั้ง Font ไทยสำหรับ JOptionPane ของ Controller
        Font thaiFont = new Font("Tahoma", Font.PLAIN, 14);
        UIManager.put("OptionPane.messageFont", thaiFont);
        UIManager.put("OptionPane.buttonFont", thaiFont);
    }

    public List<Job> getOpenJobsSorted(){
        List<Job> out = new ArrayList<>();
        for(Job j: jobs) 
            if(j.isOpen() && j.getDeadline().isAfter(LocalDate.now().minusDays(1))) 
                out.add(j);
        out.sort(Comparator.comparing(Job::getDeadline));
        return out;
    }

    public boolean canApply(Job job, Candidate cand){
        if(job.getType().equalsIgnoreCase("Coop")){
            return cand.getStatus().equalsIgnoreCase("studying");
        } else {
            return cand.getStatus().equalsIgnoreCase("graduated");
        }
    }

    public boolean validateEmail(String email){
        return email != null && email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");
    }

    public boolean apply(Job job, Candidate cand){
        if(!validateEmail(cand.getEmail())){
            JOptionPane.showMessageDialog(null, "อีเมลไม่ถูกต้อง: " + cand.getEmail());
            return false;
        }
        if(!canApply(job, cand)){
            JOptionPane.showMessageDialog(null, "ผู้สมัครไม่มีสถานะตรงกับประเภทงาน ("+job.getType()+")");
            return false;
        }
        String when = java.time.LocalDateTime.now().toString();
        JOptionPane.showMessageDialog(null, "สมัครเรียบร้อย: " + cand.getFullName() + 
            "\nตำแหน่ง: " + job.getTitle() + "\nเวลาสมัคร: " + when);
        return true;
    }
}
