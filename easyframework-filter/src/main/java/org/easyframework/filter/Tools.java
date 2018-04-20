package org.easyframework.filter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.RandomAccessFile;

public class Tools {

	public static BufferedReader bufread; 
    //指定文件路径和名称 
    private static String path = "";//D:/suncity.txt 
    private static  File filename =null; 
    private static String readStr =""; 
    
    public static void init(String path){ 
    	if(null!=path&&""!=path){
    		Tools.path=path;
    		filename = new File(path);
    		Tools.readTxtFile();
    		
    		/*String newStr="";
    		Tools.writeTxtFile(newStr);
    		*/
    		
    		String oldStr="servlet";
    		String replaceStr="servlet-conf";
    		Tools.replaceTxtByStr(oldStr, replaceStr);
    	}
    }
	
	public static String readTxtFile(){ 
        String read; 
        FileReader fileread; 
        try { 
            fileread = new FileReader(filename); 
            bufread = new BufferedReader(fileread); 
            try { 
                while ((read = bufread.readLine()) != null) { 
                    readStr = readStr + read+ "\r\n"; 
                } 
            } catch (IOException e) { 
                // TODO Auto-generated catch block 
                e.printStackTrace(); 
            } 
        } catch (FileNotFoundException e) { 
            // TODO Auto-generated catch block 
            e.printStackTrace(); 
        } 

       // System.out.println("文件内容是 :"+ "\r\n" + readStr); 
        return readStr; 
    } 
	
	
	public static void writeTxtFile(String newStr){ 
        //先读取原有文件内容，然后进行写入操作 
        String filein = newStr + "\r\n" + readStr + "\r\n"; 
        RandomAccessFile mm = null; 
        try { 
            mm = new RandomAccessFile(filename, "rw"); 
            mm.writeBytes(filein); 
        } catch (IOException e1) { 
            // TODO 自动生成  catch  块 
            e1.printStackTrace(); 
        } finally { 
            if (mm != null) { 
                try { 
                    mm.close(); 
                } catch (IOException e2) { 
                    // TODO 自动生成  catch  块 
                    e2.printStackTrace(); 
                } 
            } 
        } 
    } 
	
	
	 /** *//** 
     * 将文件中指定内容的第一行替换为其它内容 . 
     * 
     * @param oldStr 
     *            查找内容 
     * @param replaceStr 
     *            替换内容 
     */ 
    public static void replaceTxtByStr(String oldStr,String replaceStr) { 
        String temp = ""; 
        try { 
            File file = new File(path); 
            FileInputStream fis = new FileInputStream(file); 
            InputStreamReader isr = new InputStreamReader(fis); 
            BufferedReader br = new BufferedReader(isr); 
            StringBuffer buf = new StringBuffer(); 

            // 保存该行前面的内容 
            for (int j = 1; (temp = br.readLine()) != null; j++) { 
            	if(temp.contains(oldStr)){
            		temp=temp.replaceAll(oldStr, replaceStr);
            		buf = buf.append(temp); 
            		buf = buf.append(System.getProperty("line.separator")); 
            	}else{
            		buf = buf.append(temp); 
            		buf = buf.append(System.getProperty("line.separator")); 
            	}
            } 

            // 将内容插入 
           // buf = buf.append(replaceStr); 

            // 保存该行后面的内容 
            while ((temp = br.readLine()) != null) { 
                buf = buf.append(System.getProperty("line.separator")); 
                buf = buf.append(temp); 
            } 

            br.close(); 
            FileOutputStream fos = new FileOutputStream(file); 
            PrintWriter pw = new PrintWriter(fos); 
            pw.write(buf.toString().toCharArray()); 
            pw.flush(); 
            pw.close(); 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } 
    } 
    
    
    /*public static void main(String[] args) {
        Tools.clearFiles("D:\\apache-tomcat-7.0.70\\conf\\xml");
    }*/

    //删除文件和目录
    public static void clearFiles(String workspaceRootPath){
         File file = new File(workspaceRootPath);
         if(file.exists()){
              deleteFile(file);
         }
    }
    public static void deleteFile(File file){
         if(file.isDirectory()){
              File[] files = file.listFiles();
              for(int i=0; i<files.length; i++){
                   deleteFile(files[i]);
              }
         }
         file.delete();
    }
    
    
}
