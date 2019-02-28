package role;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import role.Course;

public class Teacher 
{
	private String nameID;
	private String password;
	private int whetherUsing;	// 0 means not using, 1 means using 
	
	public Teacher(String _nameID, String _password)
	{
		this.nameID = _nameID;
		this.password = _password;
		this.whetherUsing = 0;
	}
	
	public String getTeacherNameID()
	{
		return this.nameID;
	}
	
	public String getTeacherPassword()
	{
		return this.password;
	}
	
	
	/*
	 *  -1 表示该教师用户文件已存在
	 *   1 表示教师用户文件创建成功
	 * 
	 * */
	public int makeTeacherFile()
	{
		File teacherDirectory = new File("./Teacher");
		if(!teacherDirectory.exists())
			teacherDirectory.mkdir();
		
		String newTeacher = "./Teacher" + "/" + nameID + ".txt";
		File teacherFile = new File(newTeacher);
		if(teacherFile.exists())
			return -1;
		
		String newLineChar = System.getProperty("line.separator");
		
		try 
		{
			teacherFile.createNewFile();
			BufferedWriter outtoTeacherFile;	
			outtoTeacherFile = new BufferedWriter(new FileWriter(teacherFile, true));
			outtoTeacherFile.write(whetherUsing + newLineChar);
			outtoTeacherFile.write(nameID + newLineChar);
			outtoTeacherFile.write(password + newLineChar);
			outtoTeacherFile.flush();
			outtoTeacherFile.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return 1;
	}
	
	/*
	  -1 表示该课程已存在，无法再创建同名课程
	   1 表示创建课程成功
	*/
	public int creatCourse(String courseName, String time, int max)
	{
		
		Course newCourse = new Course(courseName, nameID, time, max);
		if(newCourse.makeCourseFile() == 1)
		{
			String thisTeacher = "./Teacher" + "/" + nameID + ".txt";
			File thisTeacherFile = new File(thisTeacher);
			if(!thisTeacherFile.exists())
				makeTeacherFile();
			
			String newLineChar = System.getProperty("line.separator");
			
			try 
			{
				BufferedWriter outtoTeacherFile;	
				outtoTeacherFile = new BufferedWriter(new FileWriter(thisTeacherFile, true));
				outtoTeacherFile.write(courseName + " " + time + " " + max + newLineChar);
				outtoTeacherFile.flush();
				outtoTeacherFile.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			
			return 1;
		}
		else
			return -1;
	}
	
	
}
