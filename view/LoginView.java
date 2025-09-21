package view;
import javax.swing.*;
import java.awt.*;
public class LoginView extends JDialog {
    private boolean authenticated = false;
    public LoginView(Frame owner){
        super(owner, "Login", true);
        setSize(300,180);
        setLocationRelativeTo(owner);
        init();
    }
    private void init(){
        JPanel p=new JPanel(new GridLayout(3,2,6,6));
        p.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        p.add(new JLabel("Username:"));
        JTextField u=new JTextField();
        p.add(u);
        p.add(new JLabel("Password:"));
        JPasswordField pw=new JPasswordField();
        p.add(pw);
        JButton ok=new JButton("Login");
        ok.addActionListener(e->{
            // very simple auth: username=student / password=pass -> student role
            if(u.getText().equals("student") && new String(pw.getPassword()).equals("pass")){
                authenticated=true;
                JOptionPane.showMessageDialog(this, "Login สำเร็จ (student)");
                dispose();
            } else if(u.getText().equals("admin") && new String(pw.getPassword()).equals("adminpass")){
                authenticated=true;
                JOptionPane.showMessageDialog(this, "Login สำเร็จ (admin)");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Login ล้มเหลว");
            }
        });
        getContentPane().add(p, BorderLayout.CENTER);
        getContentPane().add(ok, BorderLayout.SOUTH);
    }
    public boolean isAuthenticated(){ return authenticated;}
}
