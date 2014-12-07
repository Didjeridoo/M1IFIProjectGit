package fr.unice.deptinfo.maven_compiler;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class FileTool 
{
	
	public static void unzipFile(String destinationPath, File file)
	{
		try
        {
            ZipInputStream zipinputstream = null;
            ZipEntry zipentry;
            zipinputstream = new ZipInputStream(new FileInputStream(file));

            zipentry = zipinputstream.getNextEntry();
            while (zipentry != null) 
            { 
                //for each entry to be extracted
                String entryName = zipentry.getName();

                FileOutputStream fileoutputstream;
                
                if(zipentry.isDirectory())
                {
                	File newFile= new File(destinationPath+File.separator+entryName); 
                	newFile.mkdirs(); 
                }
                else
                {
                             
	                fileoutputstream = new FileOutputStream(destinationPath+File.separator+entryName);   
	                copyInputStream(zipinputstream, fileoutputstream);
	               
	                fileoutputstream.flush(); 
	                fileoutputstream.close(); 
                }    
                zipinputstream.closeEntry();
                zipentry = zipinputstream.getNextEntry();

            }//while

            zipinputstream.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
	}
	
	protected static void copyInputStream(InputStream in, OutputStream out) throws IOException
	{
		int n=0; 
		byte[] buf = new byte[1024];
		while ((n = in.read(buf, 0, 1024)) > -1)
            out.write(buf, 0, n);
	}
	
	public static void deleteFile(File file)
	{
		if(file.exists())
			if(file.isDirectory())
			{
				File[] files= file.listFiles(); 
				
				for(File f: files)
				{
					deleteFile(f); 
				}
				
				file.delete(); 
				
			}
			else
				file.delete(); 
	}
	
    /**
     * Utility method to create contribution zip file.
     * 
     * @param inFile The directory to zip.
     * @param outFile The output file name.
     * @throws IOException
     */
    public static void zip(File inFile, File outFile) throws IOException {
        final int buffer = 2048;
        FileOutputStream dest = new FileOutputStream(outFile);
        ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));

        try {
            byte data[] = new byte[buffer];
            List<String> list = listFiles(inFile, "");

            for (String file : list) {
                FileInputStream fis = new FileInputStream(new File(inFile, file));
                BufferedInputStream origin = new BufferedInputStream(fis, buffer);
                ZipEntry entry = new ZipEntry(file);
                out.putNextEntry(entry);

                try {
                    int count;
                    while ((count = origin.read(data, 0, buffer)) != -1) {
                        out.write(data, 0, count);
                    }
                } finally {
                    origin.close();
                    fis.close();
                }
            }
        } catch (Exception e) {
        	e.printStackTrace();
            //log.log(Level.SEVERE, "Problem while zipping file!", e);
        } finally {
            out.close();
            dest.close();
        }
    }
    
    /**
     * List files recursively. Does not include directories.
     * 
     * @param directory The directory to list.
     * @param currentDirName The current directory name.
     * @return A list of paths relative to the directory parameter.
     */
    public static List<String> listFiles(File directory, String currentDirName)
    {
        // List of files / directories
        List<String> files = new ArrayList<String>();
        // Go over entries
        for (File entry : directory.listFiles())
        {
                
            if (entry.isDirectory()) {
                StringBuilder sb  = new StringBuilder(currentDirName);
                sb.append(entry.getName()).append(File.separator); 
                files.addAll( listFiles(entry, sb.toString()) );
            } else {
                files.add(currentDirName + entry.getName());
            }
        }
        
        // Return collection of files
        return files;
    }
    
    public static List<File> listFilesByExtension(File directory, String extension)
    {
    	List<File> files = new ArrayList<File>();
    	
    	if(directory.listFiles()!=null)
	    	for (File entry : directory.listFiles())
	        {
	                
	            if (entry.isDirectory()) {
	                List<File> aux= listFilesByExtension(entry, extension);
	                
	                files.addAll( aux );
	            } else if(entry.getName().endsWith(extension)) 
	            {
	                files.add( entry);
	            }
	        }
	        
        // Return collection of files
        return files;
    }
    

    
    /**
     * Reads the contents of a file into a byte array.
     * 
     * @param file - the file to read, must not be null
     * @return the file contents
     * @throws IOException - in case of an I/O error
     */
    public static byte[] getBytesFromFile(File file) throws IOException
    {
        InputStream inputStream = new FileInputStream(file);
        
        try
        {
         // Get the size of the file
            long length = file.length();
            
            if (length > Integer.MAX_VALUE)
            {
                throw new IOException("The file you are trying to read is too large, length :"+length+", length max : "+Integer.MAX_VALUE);
            }

            // Create the byte array to hold the data
            byte[] bytes = new byte[(int) length];
            int offset = 0;
            int numRead = inputStream.read(bytes, offset, bytes.length - offset);
            while (offset < bytes.length && numRead >= 0)
            {
                offset += numRead;
                numRead = inputStream.read(bytes, offset, bytes.length - offset);
            }
            
            // Ensure all the bytes have been read in
            if (offset < bytes.length)
            {
                throw new IOException("Could not completely read file " + file.getName());
            }
            
            return bytes;
        }
        finally
        {
         // Close the input stream and return bytes
            inputStream.close();
        }
    }
    
    
    public static void copyFilesRecursively(File sourceFile, File sourcePath, File targetPath) throws IOException{
        //Defines no filter
        FileFilter noFilter = new FileFilter() {
            public boolean accept(File file) {
                return true;
            }
        };
        
        copyFilesRecursively(sourceFile, sourcePath, targetPath, noFilter);
    }
    
    public static void copyFilesRecursively(File sourceFile, File sourcePath, File targetPath, FileFilter fileFilter) throws IOException {
        try {
            copy(sourceFile,  new File(targetPath.getCanonicalPath() + sourceFile.getCanonicalPath().replace(sourcePath.getCanonicalPath(),"")));
        } catch (IOException e) {
            throw new IOException("It is not possible to copy one or more files ("+ sourceFile.getName() +"). Error: " + e.getMessage() );
        }
        if (sourceFile.isDirectory()) {
            for (File child : sourceFile.listFiles(fileFilter)) {
                copyFilesRecursively(child, sourcePath, targetPath, fileFilter);
            }
        }

    }
    
    public static void copy(File src, File dst) throws IOException 
    {
        
        //Don't copy if it's a directory
        if (src.isDirectory()) {
            dst.mkdir();
            return;
        }
        
        InputStream inputStream = new FileInputStream(src);
        OutputStream outputStream = new FileOutputStream(dst);
        
        byte[] buf = new byte[1024];
        int len;
        while ((len = inputStream.read(buf)) > 0) {
            outputStream.write(buf, 0, len);
        }
        inputStream.close();
        outputStream.close();
    }
    
	public static void saveFile(String fileName, String content) throws IOException
	{		
		Writer out = new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8");
	    try {
	      out.write(content);
	    }
	    finally {
	      out.close();
	    }
	}
	
	public static void createDirectory(String dir) throws IOException
	{
		File file= new File(dir); 
		
		if(!file.exists())
		{	
			file.mkdirs(); 
		}
		
	}
	
	public static String loadFileIntoString(String file, String encoding)
	{
		StringBuilder text = new StringBuilder();
		 Scanner scanner=null;
		try {
			scanner = new Scanner(new FileInputStream(file), encoding);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	    try {
		    while (scanner.hasNextLine())
		    {
		    	text.append(scanner.nextLine() + "\n");
		    }
		    
		   return text.toString(); 
	    }
	    finally{
	      scanner.close();
	    }
	}
	
	public static Properties loadPropertiesFile(File file)
	{
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
			Properties props= new Properties(); 
			props.load(fis);    
	        fis.close();
	        return props; 
		} catch (FileNotFoundException e) {
			e.printStackTrace();  
			
		} catch (IOException e) {
			e.printStackTrace(); 
		}
		
		return null; 
	}


}
