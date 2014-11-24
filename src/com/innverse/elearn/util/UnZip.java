package com.innverse.elearn.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnZip
{
    List<String> fileList;
    private static final String INPUT_ZIP_FILE = "D:/Projects/Innverse/Web/ELMS/ELMS/WebContent/resources/scorm/f35b3a0e-0efc-4286-85c0-1a096cddab04Credits Tutorial AICC complete incomplete.zip";
    private static final String OUTPUT_FOLDER = "D:/Projects/Innverse/Web/ELMS/ELMS/WebContent/resources/scorm/f35b3a0e-0efc-4286-85c0-1a096cddab04Credits Tutorial AICC complete incomplete";
 
    public static void main( String[] args )
    {
    	UnZip unZip = new UnZip();
    	unZip.unZipIt(INPUT_ZIP_FILE,OUTPUT_FOLDER);
    }
 
    /**
     * Unzip it
     * @param zipFile input zip file
     * @param output zip file output folder
     */
    public void unZipIt(String zipFile, String outputFolder){
 
     byte[] buffer = new byte[1024];
 
     try{
 
    	//create output directory is not exists
    	File folder = new File(outputFolder);
    	if(!folder.exists()){
    		folder.mkdir();
    	}
 
    	//get the zip file content
    	ZipInputStream zis = 
    		new ZipInputStream(new FileInputStream(zipFile));
    	//get the zipped file list entry
    	ZipEntry ze = zis.getNextEntry();
 
    	while(ze!=null){
 
    	   String fileName = ze.getName();
           File newFile = new File(outputFolder + File.separator + fileName);
 
           System.out.println("file unzip : "+ newFile.getAbsoluteFile());
 
            //create all non exists folders
            //else you will hit FileNotFoundException for compressed folder
            //writeDir(newFile);
           
           if (ze.isDirectory()){
        	   newFile.mkdirs();
           }else{
               new File(newFile.getParent()).mkdirs();
 
	            FileOutputStream fos = new FileOutputStream(newFile);             
	 
	            int len;
	            while ((len = zis.read(buffer)) > 0) {
	            	fos.write(buffer, 0, len);
	            }

	            fos.close();   
           }
           ze = zis.getNextEntry();
    	}
 
        zis.closeEntry();
    	zis.close();
 
    	System.out.println("Done");
 
    }catch(IOException ex){
       ex.printStackTrace(); 
    }
   }    
    
    private void writeDir(File f){
        try{
             if(f.mkdir()) { 
                 System.out.println("Directory Created");
            } else {
            System.out.println("Directory is not created");
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }    
}