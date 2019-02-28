package role;

import java.io.*;  

public class FileOperate 
{
	// 读取文件指定行。
	 public static String readAppointedLineNumber(File sourceFile, int lineNumber)  
	            throws IOException 
	 { 
		 	FileReader in = new FileReader(sourceFile);  
	        LineNumberReader reader = new LineNumberReader(in);  
	        String s = "";  

	        int lines = 0;  
	        
	        while (s != null) 
	        {  
	            lines++;  
	            s = reader.readLine();  
	            if((lines - lineNumber) == 0) 
	            {  
	            	reader.close();  
	    	        in.close();  
	                return s;  
	            }  
	        }
	        reader.close();  
	        in.close();  
	        return s;
	   }  
	 // 文件内共有多少行
	 static int getTotalLines(File file) throws IOException 
	 {  

	        FileReader in = new FileReader(file);  
	        LineNumberReader reader = new LineNumberReader(in);  
	        String s = reader.readLine();  
	        int lines = 0;  
	        while (s != null) 
	        {  
	            lines++;  
	            s = reader.readLine();  
	            if(lines>=2)
	            {  
	                if(s!=null)
	                {  

	                }  

	            }  
	        }  

	        reader.close();  
	        in.close();  
	        return lines;  

	   }  

	
}
