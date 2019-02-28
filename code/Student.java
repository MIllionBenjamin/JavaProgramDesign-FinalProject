package role;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import role.FileOperate;

public class Student 
{
	private String nameID;
	private String password;
	private int whetherUsing;	// 0 means not using, 1 means using 
	
	public Student(String _nameID, String _password)
	{
		this.nameID = _nameID;
		this.password = _password;
		this.whetherUsing = 0;
	}
	
	public String getStudentNameID()
	{
		return this.nameID;
	}
	
	public String getStudentPassword()
	{
		return this.password;
	}
	
	/*
	 *  -1 表示该学生用户文件已存在
	 *   1 表示学生用户文件创建成功
	 * 
	 * */
	public int makeStudentFile()
	{
		File studentDirectory = new File("./Student");
		if(!studentDirectory.exists())
			studentDirectory.mkdir();
		
		String newStudent = "./Student" + "/" + nameID + ".txt";
		File studentFile = new File(newStudent);
		if(studentFile.exists())
			return -1;
		
		String newLineChar = System.getProperty("line.separator");
		
		try 
		{
			studentFile.createNewFile();
			BufferedWriter outtoStudentFile;	
			outtoStudentFile = new BufferedWriter(new FileWriter(studentFile, true));
			outtoStudentFile.write(whetherUsing + newLineChar);
			outtoStudentFile.write(nameID + newLineChar);
			outtoStudentFile.write(password + newLineChar);
			outtoStudentFile.flush();
			outtoStudentFile.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return 1;
	}
	
	/*
	 *  -2 表示该课程不存在
	 *  -1 表示课程人数已满
	 *   0 表示该课程已选过
	 *   1 表示选课成功
	 *   
	 * */
	public int electCourse(String courseName)
	{
		Test.readAllCourses();
		if(Test.courseNames.indexOf(courseName) != -1)
		{
			if(Test.getCourseStudentInf(courseName).indexOf(this.nameID) != -1)
				return 0;

			File courseDirectory = new File("./Course");
			if(!courseDirectory.exists())
				courseDirectory.mkdir();
			
			String newCourse = "./Course" + "/" + courseName + ".txt";
			File courseFile = new File(newCourse);
			
			String newLineChar = System.getProperty("line.separator");
			
			Vector<String> everyLineofFile = new Vector<String>();
			
			try 
			{
				for(int i = 0; i < FileOperate.getTotalLines(courseFile); i++)
				{
					everyLineofFile.add(FileOperate.readAppointedLineNumber(courseFile, i + 1));
				}
				int newNum = Integer.parseInt(everyLineofFile.get(4)) + 1;
				if(newNum > Integer.parseInt(everyLineofFile.get(3)))
					return -1;
				everyLineofFile.set(4, Integer.toString(newNum));
				BufferedWriter updatePeopleNum = new BufferedWriter(new FileWriter(courseFile));
				for(int i = 0; i < everyLineofFile.size(); i++)
					updatePeopleNum.write(everyLineofFile.get(i) + newLineChar);
				updatePeopleNum.flush();
				updatePeopleNum.close();
				
				BufferedWriter outtoCourseFile;	
				outtoCourseFile = new BufferedWriter(new FileWriter(courseFile, true));
				outtoCourseFile.write(this.nameID + newLineChar);
				outtoCourseFile.flush();
				outtoCourseFile.close();
				
				File studentDirectory = new File("./Student");
				if(!studentDirectory.exists())
					studentDirectory.mkdir();
				
				String newStudent = "./Student" + "/" + nameID + ".txt";
				File studentFile = new File(newStudent);
				if(!studentFile.exists())
					makeStudentFile();

				
				BufferedWriter outtoStudentFile;
				outtoStudentFile = new BufferedWriter(new FileWriter(studentFile, true));
				String electName = everyLineofFile.get(0).replaceAll(newLineChar, "");
				String electTime = everyLineofFile.get(2).replaceAll(newLineChar, "");
				String electTeacher = everyLineofFile.get(1).replaceAll(newLineChar, "");
				outtoStudentFile.write(electName + " " + electTime + " " + electTeacher + newLineChar);
				outtoStudentFile.flush();
				outtoStudentFile.close();
				
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 1;
		}
		else
			return -2;
	}
	
}
