import java.util.*;
@SuppressWarnings("unused")


public class Algorithm implements AlgoInterface{

	Map<Integer, List<Integer>> newList = new HashMap<Integer, List<Integer>>();
	public int number=0;
	public double x=0;	public double y=0;  public double z=0;
	public Double [][]matcol;
	public float total=0;
	public double dividend=0; public double divisor=0;  //for weighted sum
	public double factorcos; public int totalcolumn; 
	public double sqrty; public double sqrtz;
	
	//Constructor for algorithm 
	public Algorithm(int maxcolumn){
		this.totalcolumn = maxcolumn;
		matcol = new Double[maxcolumn][maxcolumn];
		int i=1;
    	
		while(i<maxcolumn){
			for(int j=1;j<maxcolumn;j++)
			{
				matcol[i][j]=0.0;  //included 0 in matrix
			}i++;
		}
	}

	//Finding similarity of items in m and n using average of user array 
	public double similarItemFind(int maxRow,int n,float[] arrayUserAvg,int m,int[][] matrixA){
		int i=1;
		int var;
		double result;
		while(i<maxRow){
		if(matrixA[i][m]==0){
			var = Integer.MIN_VALUE;
    	}
		else{
			if(matrixA[i][n]==0){
				result=(double)Integer.MIN_VALUE;	
			}
			else{
					x=x+((matrixA[i][m]-arrayUserAvg[i])*(matrixA[i][n]-arrayUserAvg[i]));
					y=y+((matrixA[i][m]-arrayUserAvg[i])*(matrixA[i][m]-arrayUserAvg[i]));
					z=z+((matrixA[i][n]-arrayUserAvg[i])*(matrixA[i][n]-arrayUserAvg[i]));
				}
    		}i++;
		}
		var=0;
		sqrty = Math.sqrt(y); sqrtz = Math.sqrt(z);
		var=Integer.MIN_VALUE;	
		factorcos = x/(sqrty * sqrtz);
		var=1;
		x=0;y=0;z=0;
		matcol[m][n]=factorcos;
		var=Integer.MAX_VALUE;
		return factorcos;                 
	}	

	//finding average of a user
	public float averageUser(int valuerow,int maxColumn, int[][] matrixA){
		int k=1;
		int valuenum;
		while(k<maxColumn){      
			if(matrixA[valuerow][k]==0){
				valuenum = Integer.MIN_VALUE;
			}
			else{
				total+=matrixA[valuerow][k]; 
				number++;
			}k++;
		}
		int colnum=0;	float avg = total/number;
		valuenum=Integer.MAX_VALUE;
		int ronum=0; total=0; number=0;
		return avg;
	}

	//Adding similar items to hash
	public void dataAdd(Integer secondItem, Integer firstItem, Integer thirdItem){
		int first=0;int second=0;
		if (!(newList.containsKey(firstItem))){
			first=0;
			List<Integer> l = new ArrayList<Integer>(); 
			l.add(secondItem);
			first=1;
			newList.put(firstItem, l);
		}else{
			first=0;
			newList.get(firstItem).add(secondItem);			 
		}	 
	}
   
    //Getting prediction using Weighted Sum     
	public Double totalSumWeighted(List<Integer> valueSimilarity, int[][] matrixA, int rownum1,int rownum2){
		int num=0;
		dividend=0;	divisor=0; double result=0;  
		int a=0;
		while(a<valueSimilarity.size()){        
			if(matrixA[rownum1][valueSimilarity.get(a)]==0){
				num=Integer.MAX_VALUE;
			}
			else{
				num=1;
				result=((matcol[rownum2][valueSimilarity.get(a)])*(matrixA[rownum1][valueSimilarity.get(a)]));
				num=Integer.MIN_VALUE;
				dividend+=result;
				num=0;
				divisor+=Math.abs(matcol[rownum2][valueSimilarity.get(a)]);
				num=1;
			}
			a++;
		}
		Double predicted_value=dividend/divisor;
		return predicted_value;
	}


	public void add(){
    	 
	}

	//
	public List<Integer> keyValueGet(int i){
		if (!(newList.containsKey(i))){  
			return null;
		}
		else{
			return newList.get(i);  
		}
	}
	
    //writing results to output file 
	public void fileWrite(int maxcolumn,int [][]matrixB,FilePInterface fpi,int maxrow){
		StringBuilder strbuild=new StringBuilder();
		int i=1;
		int rowcount;
		while(i<maxrow){
			rowcount=1;
			for(int j=1;j<maxcolumn;j++){ 
				rowcount=0;
				strbuild.append(i+" "+j+" "+matrixB[i][j]+" \n");
			}i++;
		}
		try {                                                 
			fpi.filewrite(strbuild.toString());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}