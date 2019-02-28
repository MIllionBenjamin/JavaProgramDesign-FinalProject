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

public class LoginFrame extends JFrame implements ActionListener {
	private JLabel title1=new JLabel("------欢迎使用简易课程管理系统-------");
	private JLabel lbAccount=new JLabel("请您输入账号");
	private JTextField tfAccount=new JTextField(10);
	private JLabel lbPassword=new JLabel("请您输入密码");
	private JPasswordField pfPassword=new JPasswordField(10);
	private JButton btLogin=new JButton("登录");
	private JButton btRegister=new JButton("注册");
	private JButton btExit=new JButton("退出");
	public LoginFrame() {
		/**********************界面初始化*****************************/
		super("登录");
		this.setLayout(new FlowLayout());
		//this.add(lbWelcome);
		this.add(title1);
		this.add(lbAccount);
		this.add(tfAccount);
		this.add(lbPassword);
		this.add(pfPassword);
		this.add(btLogin);
		this.add(btRegister);
		this.add(btExit);
		this.setSize(260,160);
		this.setDefaultCloseOperation(0);
		this.setResizable(false);
		this.setVisible(true);
		/*****************************增加监听************************/
		btLogin.addActionListener(this);
		btRegister.addActionListener(this);
		btExit.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
	    if(e.getSource()==btLogin) {
	        String account=tfAccount.getText();
	        String password=new String(pfPassword.getPassword());
	        
	        int flag=Test.signin(account, password);
	        
	        if(flag==-3) {
	        	JOptionPane.showMessageDialog(this,"用户名格式错误---教师用户名以't'开头，学生用户名以's'开头");
	        	return;
	        }
	        else if(flag==-2){
	        	JOptionPane.showMessageDialog(this,"此用户不存在");
	        	return;
	        }
	        else if(flag==-1){
	        	JOptionPane.showMessageDialog(this,"密码错误");
	        	return;
	        }
	        else if(flag==0){
	        	JOptionPane.showMessageDialog(this,"该账户正在被使用");
	        	return;
	        }
	        else if(flag==1){
	        	JOptionPane.showMessageDialog(this,"登录成功----欢迎教师");
	 	        this.dispose();
	 	        new TeacherOperation(account);
	        }
	        else if(flag==2){
	        	JOptionPane.showMessageDialog(this,"登录成功----欢迎学生");
	 	        this.dispose();
	 	        new StudentOperation(account);
	        }
	    }
	    else if(e.getSource()==btRegister) {
	        this.dispose();
	        new RegisterFrame();
	    }
	    else {
	        JOptionPane.showMessageDialog(this,"谢谢光临");
	        System.exit(0);
	    }
	}
}
