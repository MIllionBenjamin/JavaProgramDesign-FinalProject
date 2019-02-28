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
import javax.swing.JTextField;

public class StudentOperation extends JFrame implements ActionListener {
	private Student student;
	public static final TextArea result1=new TextArea();
	public static final TextArea result2=new TextArea();
	private JLabel selCourse=new JLabel("请输入要选择的课的名称:");
	private JTextField tfCourse=new JTextField(10);
	
	private JButton btLogout=new JButton("退出");
	private JButton btPost=new JButton("提交");
	public StudentOperation(String name) {
		student=new Student(name,"*****");
		result1.setEditable(false);
		result2.setEditable(false);
		
		Container c=getContentPane();
		c.setLayout(new GridLayout(1,2,10,10));
		JPanel p1=new JPanel(new GridLayout(1,1,10,10));
		JPanel p2=new JPanel(new GridLayout(2,1,10,10));
		JPanel p3=new JPanel(new GridLayout(2,1,10,10));
		p1.setBorder(BorderFactory.createTitledBorder("所有课程如下"));
		p2.setBorder(BorderFactory.createTitledBorder("你已选的课程和选课操作"));
		
		p1.add(result1);
		p2.add(result2);
		p3.add(selCourse);
		p3.add(tfCourse);
		p3.add(btPost);
		p3.add(btLogout);
		p2.add(p3);
		
		c.add(p1);
		c.add(p2);
		
		setTitle("学生操作界面---欢迎"+student.getStudentNameID());
		setSize(1000,300);
		setVisible(true);
		setDefaultCloseOperation(0);
		
		updates(student.getStudentNameID());
		/****************************增加监听***********************/
		btLogout.addActionListener(this);
		btPost.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btLogout) {
			Test.signOut(student.getStudentNameID());
			JOptionPane.showMessageDialog(this,"退出成功");
			this.dispose();
			new LoginFrame();
		}
		else if(e.getSource()==btPost) {
			updates(student.getStudentNameID());
			String Coursesname=tfCourse.getText();
			
			int flag=student.electCourse(Coursesname);
			if(flag==1) {
				JOptionPane.showMessageDialog(this,"选择课程成功");
				updates(student.getStudentNameID());
			}
			else if(flag==-1) {
				JOptionPane.showMessageDialog(this,"课程人数已满，选课失败");
			}
			else if(flag==-2) {
				JOptionPane.showMessageDialog(this,"该课程不在可选范围内(选课人数已满或课程不存在)，选课失败");
			}
			else if(flag==0) {
				JOptionPane.showMessageDialog(this,"课程重复选择，选课失败");
			}
		}
	}
	
	public void updates(String Sname) {
		Vector<String> cour=Test.getStudentCourseInf(Sname);
		result2.setText("课程名字\t\t课程时间\t\t老师\t\t已选人数\n");
		String msg = "";
		for(int i=0;i<cour.size();i++) {
			String temp=cour.get(i);
			String[] arr1=temp.split(" ");
			
			for(int j=0;j<arr1.length;j++) {
				msg+=arr1[j];
				msg+="\t\t";
			}
			msg+=Test.getCourseStudentInf(arr1[0]).size();
			msg+="\n";
			result2.append(msg);
			msg="";
		}
		
		Test.readAllCourses();
		result1.setText("课程名字\t\t课程时间\t\t老师\t\t已选人数\n");
		String msg1 = "";
		for(int i=0;i<Test.courseNames.size();i++) {
			msg1+=Test.courseNames.get(i);
			msg1+="\t\t";
			msg1+=Test.courseTime.get(i);
			msg1+="\t\t";
			msg1+=Test.courseTeacher.get(i);
			msg1+="\t\t";
			msg1+=Test.getCourseStudentInf(Test.courseNames.get(i)).size();
			msg1+="\n";
			
			result1.append(msg1);
			msg1="";
		}
	}
}
