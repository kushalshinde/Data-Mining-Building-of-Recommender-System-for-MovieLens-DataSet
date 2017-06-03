public interface FilePInterface {
	public String fileread();
	public void fileopen();
	public void filewrite(String filedata) throws Exception;
	public void fileclose();
}