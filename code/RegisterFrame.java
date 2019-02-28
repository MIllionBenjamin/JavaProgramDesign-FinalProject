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
	private JLabel lbAccount=new JLabel("������ѧ����");
	private JTextField tfAccount=new JTextField(10);
	private JLabel lbPassword=new JLabel("������������");
	private JPasswordField pfPassword=new JPasswordField(10);
	private JLabel lbPassword2=new JLabel("����ȷ������");
	private JPasswordField pfPassword2=new JPasswordField(10);
	private JButton btRegister=new JButton("ע��");
	private JButton btLogin=new JButton("��¼");
	private JButton btExit=new JButton("�˳�");
	
	public RegisterFrame() {
        /******************�����ʼ��********************/
        super("ע��");
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
        /****************************���Ӽ���***********************/
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
            	JOptionPane.showMessageDialog(this,"�û�����ʽ����---��ʦ�û�����'t'��ͷ��ѧ���û�����'s'��ͷ");
	        	return;
            }
            else if(flag==-2) {
            	JOptionPane.showMessageDialog(this,"������������벻��ͬ");
	        	return;
            }
            else if(flag==-1) {
            	JOptionPane.showMessageDialog(this,"�û����Ѵ���");
	        	return;
            }
            else if(flag==1) {
            	JOptionPane.showMessageDialog(this,"ע��ɹ�---��ʦ");
            	this.dispose();
                new LoginFrame();
            }
            else if(flag==2) {
            	JOptionPane.showMessageDialog(this,"ע��ɹ�---ѧ��");
            	this.dispose();
                new LoginFrame();
            }
        }
        else if(e.getSource()==btLogin) {
            this.dispose();
            new LoginFrame();
        }
        else {
            JOptionPane.showMessageDialog(this,"лл����");
            System.exit(0);
        }
    }
}
