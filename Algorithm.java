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
		
    		for(int i=1; i<maxcolumn; i++){
			for(int j=1;j<maxcolumn;j++){
				matcol[i][j]=0.0;  //included 0 in matrix
			}
		}
	}

	//Finding similarity of items in m and n using average of user array 
	public double similarItemFind(int maxRow,int n,float[] arrayUserAvg,int m,int[][] matrixA){
		double result;
		
		for(int i=1; i<maxRow; i++){
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
    			}
		}
		sqrty = Math.sqrt(y); sqrtz = Math.sqrt(z);
		factorcos = x/(sqrty * sqrtz);
		x=0;y=0;z=0;
		matcol[m][n]=factorcos;
		return factorcos;                 
	}	

	//finding average of a user
	public float averageUser(int valuerow,int maxColumn, int[][] matrixA){
		int total=0, number=0;
		
		for(int k=1;k<maxColumn; k++){
			if(matrixA[valuerow][k]!=0){
				total+=matrixA[valuerow][k]; 
				number++;
			}
		}
		float avg = total/number;
		return avg;
	}

	//Adding similar items to hash
	public void dataAdd(Integer secondItem, Integer firstItem, Integer thirdItem){
		if (!(newList.containsKey(firstItem))){
			List<Integer> l = new ArrayList<Integer>(); 
			l.add(secondItem);
			newList.put(firstItem, l);
		}else{
			newList.get(firstItem).add(secondItem);			 
		}	 
	}
   
    //Getting prediction using Weighted Sum     
	public Double totalSumWeighted(List<Integer> valueSimilarity, int[][] matrixA, int rownum1,int rownum2){
		
		double dividend=0; double divisor=0; double result=0;  
		
		for(int a=0; a<valueSimilarity.size(); a++){
			if(matrixA[rownum1][valueSimilarity.get(a)]!=0){
				result=((matcol[rownum2][valueSimilarity.get(a)])*(matrixA[rownum1][valueSimilarity.get(a)]));
				dividend+=result;
				divisor+=Math.abs(matcol[rownum2][valueSimilarity.get(a)]);

			}
		}
		Double predicted_value=dividend/divisor;
		return predicted_value;
	}


	public void add(){
    	 
	}

	//
	public List<Integer> keyValueGet(int i){
		if (newList.containsKey(i)){  
			return newList.get(i);  
		}
	}
	
    //writing results to output file 
	public void fileWrite(int maxcolumn,int [][]matrixB,FilePInterface fpi,int maxrow){

		StringBuilder strbuild=new StringBuilder();
		
		for(int i=1; i<maxrow; i++){
			for(int j=1;j<maxcolumn;j++){ 
				strbuild.append(i+" "+j+" "+matrixB[i][j]+" \n");
			}
		}
		try {                                                 
			fpi.filewrite(strbuild.toString());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
