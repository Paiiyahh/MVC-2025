package dao;
import model.Company;
import model.Job;
import model.Candidate;
import java.nio.file.*;
import java.io.*;
import java.time.LocalDate;
import java.util.*;
public class CSVUtil {

    public static List<Company> readCompanies(String path) throws IOException {
        List<Company> list = new ArrayList<>();
        for(String line: Files.readAllLines(Paths.get(path))){
            line=line.trim();
            if(line.isEmpty()|| line.startsWith("#")) continue;
            String[] f=line.split(",");
            if(f.length<4) continue;
            list.add(new Company(f[0].trim(), f[1].trim(), f[2].trim(), f[3].trim()));
        }
        return list;
    }
    public static List<Job> readJobs(String path) throws IOException {
        List<Job> list = new ArrayList<>();
        for(String line: Files.readAllLines(Paths.get(path))){
            line=line.trim();
            if(line.isEmpty()|| line.startsWith("#")) continue;
            String[] f=line.split(",");
            if(f.length<7) continue;
            String id=f[0].trim();
            String title=f[1].trim();
            String desc=f[2].trim();
            String compId=f[3].trim();
            LocalDate dl=LocalDate.parse(f[4].trim());
            boolean open = f[5].trim().equalsIgnoreCase("open");
            String type = f[6].trim();
            list.add(new Job(id,title,desc,compId,dl,open,type));
        }
        return list;
    }
    public static List<Candidate> readCandidates(String path) throws IOException {
        List<Candidate> list = new ArrayList<>();
        for(String line: Files.readAllLines(Paths.get(path))){
            line=line.trim();
            if(line.isEmpty()|| line.startsWith("#")) continue;
            String[] f=line.split(",");
            if(f.length<5) continue;
            list.add(new Candidate(f[0].trim(), f[1].trim(), f[2].trim(), f[3].trim(), f[4].trim()));
        }
        return list;
    }
}
