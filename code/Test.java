package role;

import java.io.*;
import java.util.Vector;

import role.Course;
import role.Teacher;
import role.Student;
import role.FileOperate;

public class Test 
{
	public static Vector<String> courseNames = new Vector<String>();
	public static Vector<String> courseTime = new Vector<String>();
	public static Vector<String> courseTeacher = new Vector<String>();
	
	/* 
	 * 遍历course文件夹内所有文件，
	 * 将课程名字放入courseNames向量中，
	 * 对应的课程时间放入对应下标的courseTime向量中，
	 * 对应的课程老师ID放入对应下标的courseTeacher向量中，
	 * !!!已满人的课程不会被放入这些向量
	*/
	public static void readAllCourses()
	{
		courseNames.clear();
		courseTime.clear();
		courseTeacher.clear();
		String coursePath = "./Course";
		File courseDirectory = new File(coursePath);	
		File[] courseFiles =  courseDirectory.listFiles();
		for(File course : courseFiles)
		{
			try 
			{
				int maxPeople = Integer.parseInt(FileOperate.readAppointedLineNumber(course, 4));
				int nowPeople = Integer.parseInt(FileOperate.readAppointedLineNumber(course, 5));
				if(maxPeople > nowPeople)
				{
					courseNames.addElement(FileOperate.readAppointedLineNumber(course, 1));
					courseTime.add(FileOperate.readAppointedLineNumber(course, 3));
					courseTeacher.add(FileOperate.readAppointedLineNumber(course, 2));
				}
			} 
			catch (NumberFormatException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/*  
	 *  -3 表示用户名格式错误
	 *  -2 表示两次输入的密码不相同
	 *  -1 该用户名已存在
	 *   
	 *   1 表示以教师身份注册成功
	 *   2 表示以学生身份注册成功
	 * */
	
	public static int register(String userName, String passwordFirst, String passwordSecond)
	{
		if(userName.charAt(0) != 't' && userName.charAt(0) != 's')
			return -3;
		
		if(!passwordFirst.equals(passwordSecond))
			return -2;
		
		if(userName.charAt(0) == 't')
		{
			File teacherDirectory = new File("./Teacher");
			if(!teacherDirectory.exists())
				teacherDirectory.mkdir();
			
			String registerTeacher = "./Teacher" + "/" + userName + ".txt";
			File teacherFile = new File(registerTeacher);
			if(teacherFile.exists())
				return -1;
			
			Teacher newTeacher = new Teacher(userName, passwordFirst);
			newTeacher.makeTeacherFile();
			return 1;
		}
		else
		{
			File studentDirectory = new File("./Student");
			if(!studentDirectory.exists())
				studentDirectory.mkdir();
			
			String registerStudent = "./Student" + "/" + userName + ".txt";
			File studentFile = new File(registerStudent);
			if(studentFile.exists())
				return -1;
			
			Student newStudent = new Student(userName, passwordFirst);
			newStudent.makeStudentFile();
			return 2;
		}
	}
	
	/*  
	 *  -3 表示用户名格式错误
	 *  -2 此用户不存在
	 *  -1 表示密码错误 
	 *   0 表示该账户正在被使用
	 *   1 表示以教师身份登陆成功
	 *   2 表示以学生身份登陆成功
	 * */
	public static int signin(String userName, String password) 
	{
		if(userName.charAt(0) != 't' && userName.charAt(0) != 's')
			return -3;
		if(userName.charAt(0) == 't')
		{
			File teacherDirectory = new File("./Teacher");
			if(!teacherDirectory.exists())
				teacherDirectory.mkdir();
			
			String signTeacher = "./Teacher" + "/" + userName + ".txt";
			File teacherFile = new File(signTeacher);
			if(!teacherFile.exists())
				return -2;
			
			try 
			{
				if(!FileOperate.readAppointedLineNumber(teacherFile, 3).equals(password))
					return -1;
				if(FileOperate.readAppointedLineNumber(teacherFile, 1).equals("1"))
					return 0;
				
				Vector<String> everyLineofFile = new Vector<String>();
				String newLineChar = System.getProperty("line.separator");
				for(int i = 0; i < FileOperate.getTotalLines(teacherFile); i++)
				{
					everyLineofFile.add(FileOperate.readAppointedLineNumber(teacherFile, i + 1));
				}
				everyLineofFile.set(0, "1");
				BufferedWriter updateStatus = new BufferedWriter(new FileWriter(teacherFile));
				for(int i = 0; i < everyLineofFile.size(); i++)
					updateStatus.write(everyLineofFile.get(i) + newLineChar);
				updateStatus.flush();
				updateStatus.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 1;
		}
		else
		{
			File studentDirectory = new File("./Student");
			if(!studentDirectory.exists())
				studentDirectory.mkdir();
			
			String signStudent = "./Student" + "/" + userName + ".txt";
			File studentFile = new File(signStudent);
			if(!studentFile.exists())
				return -2;
			
			try 
			{
				if(!FileOperate.readAppointedLineNumber(studentFile, 3).equals(password))
					return -1;
				if(FileOperate.readAppointedLineNumber(studentFile, 1).equals("1"))
					return 0;
				Vector<String> everyLineofFile = new Vector<String>();
				String newLineChar = System.getProperty("line.separator");
				for(int i = 0; i < FileOperate.getTotalLines(studentFile); i++)
				{
					everyLineofFile.add(FileOperate.readAppointedLineNumber(studentFile, i + 1));
				}
				everyLineofFile.set(0, "1");
				BufferedWriter updateStatus = new BufferedWriter(new FileWriter(studentFile));
				for(int i = 0; i < everyLineofFile.size(); i++)
					updateStatus.write(everyLineofFile.get(i) + newLineChar);
				updateStatus.flush();
				updateStatus.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 2;
		}
	}
	
	/*  
	 * 1 表示该用户退出成功
	 * */
	public static int signOut(String userName)
	{
		if(userName.charAt(0) == 't')
		{
			File teacherDirectory = new File("./Teacher");
			if(!teacherDirectory.exists())
				teacherDirectory.mkdir();
			
			String signTeacher = "./Teacher" + "/" + userName + ".txt";
			File teacherFile = new File(signTeacher);
			try 
			{
				
				Vector<String> everyLineofFile = new Vector<String>();
				String newLineChar = System.getProperty("line.separator");
				for(int i = 0; i < FileOperate.getTotalLines(teacherFile); i++)
				{
					everyLineofFile.add(FileOperate.readAppointedLineNumber(teacherFile, i + 1));
				}
				everyLineofFile.set(0, "0");
				BufferedWriter updateStatus = new BufferedWriter(new FileWriter(teacherFile));
				for(int i = 0; i < everyLineofFile.size(); i++)
					updateStatus.write(everyLineofFile.get(i) + newLineChar);
				updateStatus.flush();
				updateStatus.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 1;
		}
		else
		{
			File studentDirectory = new File("./Student");
			if(!studentDirectory.exists())
				studentDirectory.mkdir();
			
			String signStudent = "./Student" + "/" + userName + ".txt";
			File studentFile = new File(signStudent);
			
			try 
			{

				Vector<String> everyLineofFile = new Vector<String>();
				String newLineChar = System.getProperty("line.separator");
				for(int i = 0; i < FileOperate.getTotalLines(studentFile); i++)
				{
					everyLineofFile.add(FileOperate.readAppointedLineNumber(studentFile, i + 1));
				}
				everyLineofFile.set(0, "0");
				BufferedWriter updateStatus = new BufferedWriter(new FileWriter(studentFile));
				for(int i = 0; i < everyLineofFile.size(); i++)
					updateStatus.write(everyLineofFile.get(i) + newLineChar);
				updateStatus.flush();
				updateStatus.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 1;
		}
	}
	
	/*
	 * 把这个课程的所有学生的学生ID放入向量中返回，向量中每个元素表示一个课程信息，格式为“学生ID”
	 * */
	public static Vector<String> getCourseStudentInf(String courseName)
	{
		Vector<String> allStudentInf = new Vector<String>();
		File courseDirectory = new File("./Course");
		if(!courseDirectory.exists())
			courseDirectory.mkdir();
		
		String newCourse = "./Course" + "/" + courseName + ".txt";
		File courseFile = new File(newCourse);
		if(!courseFile.exists())
			return allStudentInf;
		
		try 
		{
			for(int i = 6; i <= FileOperate.getTotalLines(courseFile); i++)
			{
				allStudentInf.add(FileOperate.readAppointedLineNumber(courseFile, i));
			}
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allStudentInf;
	}
	
	/*
	 * 把这个老师创建的所有课程信息存在向量中返回，向量中每个元素表示一个课程信息，格式为“课程名 课程时间 最大人数”
	 * */
	public static Vector<String> getTeacherCourseInf(String teacherName)
	{
		Vector<String> allCourseInf = new Vector<String>();
		File teacherDirectory = new File("./Teacher");
		if(!teacherDirectory.exists())
			teacherDirectory.mkdir();
		
		String registerTeacher = "./Teacher" + "/" + teacherName + ".txt";
		File teacherFile = new File(registerTeacher);
		if(!teacherFile.exists())
			return allCourseInf;
		
		try 
		{
			for(int i = 4; i <= FileOperate.getTotalLines(teacherFile); i++)
			{
				allCourseInf.add(FileOperate.readAppointedLineNumber(teacherFile, i));
			}
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allCourseInf;
	}
	
	/*
	 *把这个学生选的所有课程的信息存在向量中返回，向量中每个元素代表一个课程信息，格式为“课程名 课程时间 老师名” 
	 */
	public static Vector<String> getStudentCourseInf(String studentName)
	{
		Vector<String> allCourseInf = new Vector<String>();
		File studentDirectory = new File("./Student");
		if(!studentDirectory.exists())
			studentDirectory.mkdir();
		
		String newStudent = "./Student" + "/" + studentName + ".txt";
		File studentFile = new File(newStudent);
		if(!studentFile.exists())
			return allCourseInf;
		
		try 
		{
			for(int i = 4; i <= FileOperate.getTotalLines(studentFile); i++)
			{
				allCourseInf.add(FileOperate.readAppointedLineNumber(studentFile, i));
			}
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allCourseInf;
	}
}
