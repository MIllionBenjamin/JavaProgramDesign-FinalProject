package role;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegisterFrame extends JFrame implements ActionListener {
	private JLabel lbAccount=new JLabel("请输入学工号");
	private JTextField tfAccount=new JTextField(10);
	private JLabel lbPassword=new JLabel("请您输入密码");
	private JPasswordField pfPassword=new JPasswordField(10);
	private JLabel lbPassword2=new JLabel("输入确认密码");
	private JPasswordField pfPassword2=new JPasswordField(10);
	private JButton btRegister=new JButton("注册");
	private JButton btLogin=new JButton("登录");
	private JButton btExit=new JButton("退出");
	
	public RegisterFrame() {
        /******************界面初始化********************/
        super("注册");
        this.setLayout(new FlowLayout());
        this.add(lbAccount);
        this.add(tfAccount);
        this.add(lbPassword);
        this.add(pfPassword);
        this.add(lbPassword2);
        this.add(pfPassword2);
        this.add(btRegister);
        this.add(btLogin);
        this.add(btExit);
        this.setSize(260,160);
        //GUIUtil.toCenter(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        /****************************增加监听***********************/
        btLogin.addActionListener(this);
        btRegister.addActionListener(this);
        btExit.addActionListener(this);
    }
	
	public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btRegister) {
            String password1=new String(pfPassword.getPassword());
            String password2=new String(pfPassword2.getPassword());
            String account=tfAccount.getText();
            int flag=Test.register(account, password1, password2);
            if(flag==-3) {
            	JOptionPane.showMessageDialog(this,"用户名格式错误---教师用户名以't'开头，学生用户名以's'开头");
	        	return;
            }
            else if(flag==-2) {
            	JOptionPane.showMessageDialog(this,"两次输入的密码不相同");
	        	return;
            }
            else if(flag==-1) {
            	JOptionPane.showMessageDialog(this,"用户名已存在");
	        	return;
            }
            else if(flag==1) {
            	JOptionPane.showMessageDialog(this,"注册成功---教师");
            	this.dispose();
                new LoginFrame();
            }
            else if(flag==2) {
            	JOptionPane.showMessageDialog(this,"注册成功---学生");
            	this.dispose();
                new LoginFrame();
            }
        }
        else if(e.getSource()==btLogin) {
            this.dispose();
            new LoginFrame();
        }
        else {
            JOptionPane.showMessageDialog(this,"谢谢光临");
            System.exit(0);
        }
    }
}
