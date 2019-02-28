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
	private JLabel title1=new JLabel("------��ӭʹ�ü��׿γ̹���ϵͳ-------");
	private JLabel lbAccount=new JLabel("���������˺�");
	private JTextField tfAccount=new JTextField(10);
	private JLabel lbPassword=new JLabel("������������");
	private JPasswordField pfPassword=new JPasswordField(10);
	private JButton btLogin=new JButton("��¼");
	private JButton btRegister=new JButton("ע��");
	private JButton btExit=new JButton("�˳�");
	public LoginFrame() {
		/**********************�����ʼ��*****************************/
		super("��¼");
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
		/*****************************���Ӽ���************************/
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
	        	JOptionPane.showMessageDialog(this,"�û�����ʽ����---��ʦ�û�����'t'��ͷ��ѧ���û�����'s'��ͷ");
	        	return;
	        }
	        else if(flag==-2){
	        	JOptionPane.showMessageDialog(this,"���û�������");
	        	return;
	        }
	        else if(flag==-1){
	        	JOptionPane.showMessageDialog(this,"�������");
	        	return;
	        }
	        else if(flag==0){
	        	JOptionPane.showMessageDialog(this,"���˻����ڱ�ʹ��");
	        	return;
	        }
	        else if(flag==1){
	        	JOptionPane.showMessageDialog(this,"��¼�ɹ�----��ӭ��ʦ");
	 	        this.dispose();
	 	        new TeacherOperation(account);
	        }
	        else if(flag==2){
	        	JOptionPane.showMessageDialog(this,"��¼�ɹ�----��ӭѧ��");
	 	        this.dispose();
	 	        new StudentOperation(account);
	        }
	    }
	    else if(e.getSource()==btRegister) {
	        this.dispose();
	        new RegisterFrame();
	    }
	    else {
	        JOptionPane.showMessageDialog(this,"лл����");
	        System.exit(0);
	    }
	}
}
