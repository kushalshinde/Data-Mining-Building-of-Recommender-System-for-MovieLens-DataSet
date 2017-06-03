import java.util.List;

public interface AlgoInterface {
	public List<Integer> keyValueGet(int i);
	public void dataAdd(Integer secondItem, Integer firstItem, Integer third);
	public double similarItemFind(int maxRow,int n,float[] arrayRu,int m,int[][] matrixA);
	public float averageUser(int valuerow,int maxColumn, int[][] matrixA);
	public Double totalSumWeighted(List<Integer> valueSimilarity, int[][] matrixA, int i,int j);
	public void fileWrite(int maxcolumn,int [][]matrixB,FilePInterface iF,int maxrow);	
}