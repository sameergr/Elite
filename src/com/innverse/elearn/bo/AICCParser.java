package com.innverse.elearn.bo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FilenameUtils;

import com.innverse.elearn.model.Organization;
import com.innverse.elearn.model.UploadedScorm;

public class AICCParser {

	private String DESTINATION_DIRECTORY = "D:/Projects/Innverse/Web/ELMS/ELMS/WebContent/resources/scorm/f35b3a0e-0efc-4286-85c0-1a096cddab04Credits Tutorial AICC complete incomplete/";
	private List<String> metadataFilesPath = new ArrayList<String>();
	private List<String> metadataFiles = new ArrayList<String>();
	private UploadedScorm uploadedFile = new UploadedScorm();
	private HashMap<String,String> metadataKeyValueMap = new HashMap<String,String>();
	
	private enum AICCMetadata {
	    AU("au"),
	    CRS("crs"),
	    CST("cst"),
	    DES("des");
	    
	    private String val;
	    AICCMetadata(String val){
	    	this.val = val;
	    }
	    
	    public static AICCMetadata getAICCMetadata(String val){
	    	for (AICCMetadata aiccMetadata : AICCMetadata.values()) {
				if(aiccMetadata.val.equalsIgnoreCase(val)){
					return aiccMetadata;
				}
			}
	    	return null;
	    }
	    
	  }
	
	public AICCParser(){
		init();
	}
	
	public static void main(String[] args) throws Exception{
		AICCParser parser = new AICCParser();
		parser.readFile(parser.DESTINATION_DIRECTORY);
//		AICCMetadata aiccMetadata = AICCMetadata.getAICCMetadata(".au");
//		System.out.println(aiccMetadata);
		
//		if(parser.metadataFiles.contains(".au")){
//            System.out.println("File Name ::- ");
//        }
	}
	
	private void init(){
		metadataFiles.add("au");
		metadataFiles.add("crs");
		metadataFiles.add("cst");
		metadataFiles.add("des");
	}
	
	private void readAUMetadataFile(String filename) throws Exception{
		System.out.println("readAUMetadataFile ::- " + filename);
		File file = new File(filename);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String firstLine = br.readLine();
		String secondLine = br.readLine();
		br.close();
		System.out.println("First Line ::- " + firstLine);
		System.out.println("Second Line ::- " + secondLine);
		String[] keysData = firstLine.split(",");
		String[] valuesData = secondLine.split(",");
		for(int i = 0 ; i < keysData.length; i++){
			keysData[i] = keysData[i].trim();
			valuesData[i] = valuesData[i].trim();
			metadataKeyValueMap.put(keysData[i].substring(1,keysData[i].length()-1), valuesData[i].substring(1,valuesData[i].length()-1));
		}
	}
	
	private void readCRSMetadataFile(String filename) throws Exception{
		File file = new File(filename);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = "";
		while((line = br.readLine()) != null){
			int equalIndex = line.indexOf("=");
			if(equalIndex > -1){
				String keyData = line.substring(0,equalIndex).trim();
				String valueData = line.substring(equalIndex + 1).trim();
				metadataKeyValueMap.put(keyData, valueData);
			}
		}
	}
	
	public UploadedScorm getUploadScrom(String destinationDirectory, long fileSize,String filenameWithoutExt, Organization organization){
		uploadedFile.setMaxScore(Double.valueOf((metadataKeyValueMap.get("Max_Score") != null && !metadataKeyValueMap.get("Max_Score").trim().equalsIgnoreCase("")) ? metadataKeyValueMap.get("Max_Score") : "100"));
		uploadedFile.setMasteryScore(Double.valueOf((metadataKeyValueMap.get("Mastery_Score") != null && !metadataKeyValueMap.get("Mastery_Score").trim().equalsIgnoreCase("")) ? metadataKeyValueMap.get("Mastery_Score") : "100"));
		String file_name = metadataKeyValueMap.get("file_name");
		String launch = (file_name.lastIndexOf("/") > -1 ? file_name.substring(file_name.lastIndexOf("/")+1): file_name );
		uploadedFile.setLaunch(launch);
		uploadedFile.setFileName(filenameWithoutExt);
		uploadedFile.setFilePath(destinationDirectory);
		uploadedFile.setFileSize(fileSize);
		uploadedFile.setManifest(false);
		uploadedFile.setOrganization(organization);
		uploadedFile.setActive(true);
		uploadedFile.setParentRoot("/");
		uploadedFile.setTitle(metadataKeyValueMap.get("Course_Title"));
		uploadedFile.setIdentifier(metadataKeyValueMap.get("System_Vendor"));
		uploadedFile.setPackageType("AICC");
		return uploadedFile;
	}
	
	public void createMetaData(String destinationDirectory) throws Exception{
		readFile(destinationDirectory);
	}
	
	private void readFile(String destinationDirectory) throws Exception{
		File directoryPath = new File(destinationDirectory);
		listFilesForFolder(directoryPath);
		
		if(metadataFilesPath.size() > 0){
			for(String metadataFilePath : metadataFilesPath){
	            String ext = FilenameUtils.getExtension(metadataFilePath);
	    		AICCMetadata aiccMetadata = AICCMetadata.getAICCMetadata(ext);
				switch (aiccMetadata) {
				case AU:
					readAUMetadataFile(metadataFilePath);
					break;
				case CRS:
					readCRSMetadataFile(metadataFilePath);
					break;
				default:
					break;
				}
				
			}
		}
	}
	
	
	private void listFilesForFolder(final File folder) {
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry);
	        } else {
	            String absoluteFileNamePath = fileEntry.getAbsolutePath();
	            String ext = FilenameUtils.getExtension(absoluteFileNamePath);
	            if(metadataFiles.contains(ext.toLowerCase())){
		            System.out.println("File Name ::- " + fileEntry.getName());
		            System.out.println("File Absolute Path ::- " + fileEntry.getAbsolutePath());
	            	metadataFilesPath.add(absoluteFileNamePath);
	            }
	            
	        }
	    }
	}
}
