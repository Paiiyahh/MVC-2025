package view;

import javax.swing.*;
import controller.JobController;
import model.Job;
import model.Candidate;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JobController controller;
    private List<Job> jobs;
    private List<Candidate> candidates;

    public static void showFrame(JobController controller, List<Job> jobs, List<Candidate> cands) {
        MainFrame frame = new MainFrame(controller, jobs, cands);
        frame.setVisible(true);
    }

    public MainFrame(JobController controller, List<Job> jobs, List<Candidate> cands) {
        this.controller = controller;
        this.jobs = jobs;
        this.candidates = cands;

        setTitle("ระบบรับสมัครงาน Job Fair");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(createJobListPanel(), "JobList");
        add(mainPanel);

        cardLayout.show(mainPanel, "JobList");
    }

    private JPanel createJobListPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        Font thaiFont = new Font("Tahoma", Font.PLAIN, 14);

        JLabel title = new JLabel("ตำแหน่งงานที่เปิดรับสมัคร", SwingConstants.CENTER);
        title.setFont(new Font("Tahoma", Font.BOLD, 18));
        panel.add(title, BorderLayout.NORTH);

        DefaultListModel<Job> listModel = new DefaultListModel<>();
        for (Job job : jobs) {
            if (job.isOpen()) listModel.addElement(job);
        }

        JList<Job> jobList = new JList<>(listModel);
        jobList.setFont(thaiFont);
        jobList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jobList.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> {
            JLabel lbl = new JLabel(value.getTitle() + " (" + value.getCompanyId() + ")");
            lbl.setFont(thaiFont);
            return lbl;
        });

        panel.add(new JScrollPane(jobList), BorderLayout.CENTER);

        JButton applyButton = new JButton("สมัครงาน");
        applyButton.setFont(thaiFont);
        applyButton.addActionListener(e -> {
            Job selected = jobList.getSelectedValue();
            if (selected == null) {
                JOptionPane.showMessageDialog(this, "กรุณาเลือกตำแหน่งงาน");
                return;
            }

            ApplyView applyView = new ApplyView(controller, selected, candidates, () -> {
                cardLayout.show(mainPanel, "JobList");
            });

            mainPanel.add(applyView, "Apply");
            cardLayout.show(mainPanel, "Apply");
        });

        panel.add(applyButton, BorderLayout.SOUTH);
        return panel;
    }
}
