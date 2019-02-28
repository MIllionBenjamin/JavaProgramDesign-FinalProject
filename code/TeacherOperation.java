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
	private JLabel lbName=new JLabel("请输入课程名");
	private JTextField tfName=new JTextField(10);
	private JLabel lbTime=new JLabel("请您输入时间");
	private JTextField pfTime=new JTextField(10);
	private JLabel lbNumber=new JLabel("输入人数上限");
	private JTextField pfNumber=new JTextField(10);
	
	private JButton btLogout=new JButton("退出");
	private JButton btPost=new JButton("提交");
	
	
	public TeacherOperation(String name) {
		teacher = new Teacher(name,"*****");
		result.setEditable(false);
		
		Container c=getContentPane();
		c.setLayout(new GridLayout(2,2,10,10));
		JPanel p1=new JPanel(new GridLayout(1,1,10,10));
		JPanel p2=new JPanel(new GridLayout(2,1,10,10));
		
		p1.setBorder(BorderFactory.createTitledBorder("您已开设的课程"));
		p2.setBorder(BorderFactory.createTitledBorder("您可以进行的操作——添加课程"));
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
		
		setTitle("教师操作界面---欢迎"+teacher.getTeacherNameID());
		setSize(1000,300);
		setVisible(true);
		setDefaultCloseOperation(0);
		
		updates(teacher.getTeacherNameID());
		/****************************增加监听***********************/
		btLogout.addActionListener(this);
		btPost.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btLogout) {
			Test.signOut(teacher.getTeacherNameID());
			JOptionPane.showMessageDialog(this,"退出成功");
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
				JOptionPane.showMessageDialog(this,"创建课程成功");
				updates(teacher.getTeacherNameID());
			}
			else if(flag==-1) {
				JOptionPane.showMessageDialog(this,"课程已存在，无法创建同名课程");
			}
		}
	}
	
	public void updates(String Tname) {
		Vector<String> te=Test.getTeacherCourseInf(Tname);
		result.setText("课程名字\t\t课程时间\t\t最大人数\t\t已选人数\n");
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
