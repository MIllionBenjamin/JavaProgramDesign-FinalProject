package role;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedReader;  
import java.io.BufferedWriter;  
import java.io.FileWriter;
import java.io.IOException;  

public class Course 
{
	private String courseName;
	private String teacherName;
	private String courseTime;
	private int maxNum;
	private int nowNum;
	
	public Course(String name, String teacher, String time, int max)
	{
		this.courseName = name;
		this.teacherName = teacher;
		this.courseTime = time;
		this.maxNum = max;
		this.nowNum = 0;
	}
	
	/*
	 * -1 表示课程已存在 无法再次创建
	 *  1 表示课程创建成功
	*/
	public int makeCourseFile()
	{
		File courseDirectory = new File("./Course");
		if(!courseDirectory.exists())
			courseDirectory.mkdir();
		
		String newCourse = "./Course" + "/" + courseName + ".txt";
		File courseFile = new File(newCourse);
		if(courseFile.exists())
			return -1;
		
		String newLineChar = System.getProperty("line.separator");
		
		try 
		{
			courseFile.createNewFile();
			BufferedWriter outtoCourseFile;	
			outtoCourseFile = new BufferedWriter(new FileWriter(courseFile, true));
			outtoCourseFile.write(courseName + newLineChar);
			outtoCourseFile.write(teacherName + newLineChar);
			outtoCourseFile.write(courseTime + newLineChar);
			outtoCourseFile.write(maxNum + newLineChar);
			outtoCourseFile.write(nowNum + newLineChar);
			outtoCourseFile.flush();
			outtoCourseFile.close();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}
	
	
}
