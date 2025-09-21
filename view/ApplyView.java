package view;

import javax.swing.*;
import model.Job;
import model.Candidate;
import controller.JobController;
import java.awt.*;
import java.util.List;

public class ApplyView extends JPanel {
    private Job job;
    private List<Candidate> candidates;
    private JobController controller;
    private Runnable onApplySuccess;

    public ApplyView(JobController controller, Job job, List<Candidate> candidates, Runnable onApplySuccess) {
        this.controller = controller;
        this.job = job;
        this.candidates = candidates;
        this.onApplySuccess = onApplySuccess;
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());
        JPanel p = new JPanel(new GridLayout(5,2,6,6));
        p.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        Font thaiFont = new Font("Tahoma", Font.PLAIN, 14);

        JLabel lblJob = new JLabel("ตำแหน่ง:");
        lblJob.setFont(thaiFont);
        p.add(lblJob);

        JLabel lblJobTitle = new JLabel(job.getTitle());
        lblJobTitle.setFont(thaiFont);
        p.add(lblJobTitle);

        JLabel lblFirst = new JLabel("ชื่อ:");
        lblFirst.setFont(thaiFont);
        p.add(lblFirst);

        JTextField firstName = new JTextField();
        firstName.setFont(thaiFont);
        p.add(firstName);

        JLabel lblLast = new JLabel("นามสกุล:");
        lblLast.setFont(thaiFont);
        p.add(lblLast);

        JTextField lastName = new JTextField();
        lastName.setFont(thaiFont);
        p.add(lastName);

        JLabel lblEmail = new JLabel("อีเมล:");
        lblEmail.setFont(thaiFont);
        p.add(lblEmail);

        JTextField email = new JTextField();
        email.setFont(thaiFont);
        p.add(email);

        JButton submit = new JButton("ส่งใบสมัคร");
        submit.setFont(thaiFont);
        submit.addActionListener(e -> {
            String s = job.isInternship() ? "studying" : "graduated";
            Candidate cand = new Candidate(
                String.valueOf(System.currentTimeMillis() % 100000000),
                firstName.getText().trim(),
                lastName.getText().trim(),
                email.getText().trim(),
                s
            );

            if(controller.apply(job, cand)){
                candidates.add(cand);
                onApplySuccess.run();
            }
        });

        add(p, BorderLayout.CENTER);
        add(submit, BorderLayout.SOUTH);
    }
}
