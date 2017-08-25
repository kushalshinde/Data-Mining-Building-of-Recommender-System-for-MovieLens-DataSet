import java.io.*;
@SuppressWarnings("unused")


public class FileP implements FilePInterface {
	BufferedWriter writebuff=null;  
	String filein;
	BufferedReader rbuff=null;
	FileWriter writefile=null;
	String fileout;
	
	public FileP(String filein, String fileout) throws Exception{
		this.fileout=fileout;
		this.filein=filein;
		rbuff = new BufferedReader(new FileReader(filein));
	}
	
	@Override
	public void fileopen() {
		
	}
	
	public String fileread(){		
		try {
			 String string=rbuff.readLine();
			 return string;
		} catch (IOException e) {
			e.getMessage();
			e.printStackTrace();
		}
		return null;		
   }
	
	
	@Override
	public void fileclose() {
		
	}

	public void filewrite(String dataout) throws Exception{
		File wfile = new File(fileout);	
		writefile = new FileWriter(wfile.getAbsoluteFile(),true);
		writebuff = new BufferedWriter(writefile);
		writebuff.write(dataout);
		writebuff.flush();
		writebuff.close();
		writefile.close();
		rbuff.close(); 

	}


}
