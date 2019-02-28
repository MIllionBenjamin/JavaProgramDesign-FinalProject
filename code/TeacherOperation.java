package role;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class TeacherOperation extends JFrame implements ActionListener {
	private Teacher teacher;
	public static final TextArea result=new TextArea();
	private JLabel lbName=new JLabel("������γ���");
	private JTextField tfName=new JTextField(10);
	private JLabel lbTime=new JLabel("��������ʱ��");
	private JTextField pfTime=new JTextField(10);
	private JLabel lbNumber=new JLabel("������������");
	private JTextField pfNumber=new JTextField(10);
	
	private JButton btLogout=new JButton("�˳�");
	private JButton btPost=new JButton("�ύ");
	
	
	public TeacherOperation(String name) {
		teacher = new Teacher(name,"*****");
		result.setEditable(false);
		
		Container c=getContentPane();
		c.setLayout(new GridLayout(2,2,10,10));
		JPanel p1=new JPanel(new GridLayout(1,1,10,10));
		JPanel p2=new JPanel(new GridLayout(2,1,10,10));
		
		p1.setBorder(BorderFactory.createTitledBorder("���ѿ���Ŀγ�"));
		p2.setBorder(BorderFactory.createTitledBorder("�����Խ��еĲ���������ӿγ�"));
		p1.add(result);
		p2.add(lbName);
		p2.add(tfName);
		p2.add(lbTime);
		p2.add(pfTime);
		p2.add(lbNumber);
		p2.add(pfNumber);
		p2.add(btPost);
		p2.add(btLogout);
		
		c.add(p1);
		c.add(p2);
		
		setTitle("��ʦ��������---��ӭ"+teacher.getTeacherNameID());
		setSize(1000,300);
		setVisible(true);
		setDefaultCloseOperation(0);
		
		updates(teacher.getTeacherNameID());
		/****************************���Ӽ���***********************/
		btLogout.addActionListener(this);
		btPost.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btLogout) {
			Test.signOut(teacher.getTeacherNameID());
			JOptionPane.showMessageDialog(this,"�˳��ɹ�");
			this.dispose();
			new LoginFrame();
		}
		else if(e.getSource()==btPost) {
			updates(teacher.getTeacherNameID());
			String LbName=tfName.getText();
			String Times=pfTime.getText();
			String Num=pfNumber.getText();
			int flag=teacher.creatCourse(LbName, Times, Integer.parseInt(Num));
			if(flag==1) {
				JOptionPane.showMessageDialog(this,"�����γ̳ɹ�");
				updates(teacher.getTeacherNameID());
			}
			else if(flag==-1) {
				JOptionPane.showMessageDialog(this,"�γ��Ѵ��ڣ��޷�����ͬ���γ�");
			}
		}
	}
	
	public void updates(String Tname) {
		Vector<String> te=Test.getTeacherCourseInf(Tname);
		result.setText("�γ�����\t\t�γ�ʱ��\t\t�������\t\t��ѡ����\n");
		String msg = "";
		for(int i=0;i<te.size();i++) {
			String temp=te.get(i);
			String[] arr1=temp.split(" ");
			
			for(int j=0;j<arr1.length;j++) {
				msg+=arr1[j];
				msg+="\t\t";
			}
			msg+=Test.getCourseStudentInf(arr1[0]).size();
			msg+="\n";
			result.append(msg);
			msg="";
		}
	}
}
