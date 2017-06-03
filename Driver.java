import java.util.*;
@SuppressWarnings("unused")


public class Driver {

	public static void main(String[] args) throws Exception {
		int maxrow=944;
		Integer val = null;
		int maxcolumn=1683;
		Double threshold_similarity =0.3;
		FilePInterface f = null;
		AlgoInterface al=new Algorithm(maxcolumn);
		try {
			f = new FileP("train_all_txt.txt","output.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}

		String s = "";
		int[][]matrixInput = new int[maxrow][maxcolumn];
		ArrayList<Integer> ab = new ArrayList<Integer>();
		
		while((s = f.fileread())!=null){
			int rownumval=ab.size();
			String[] split = s.split("\\s+");
			rownumval=ab.hashCode();
		    val = Integer.parseInt(split[2]);
		    matrixInput[Integer.parseInt(split[0])][Integer.parseInt(split[1])]=val;
        }
		
		float[] avgUser  = new float[maxrow];
		int i=1;
		int j=1;
    System.out.println("In process....");
		while(i<maxrow){
    		avgUser[i]=al.averageUser(i,maxcolumn,matrixInput);
    		int rownumval=0;
    		i++;
    	}
    	
		while(maxcolumn>j){
			int k=1;
			int rownum;
			while(maxcolumn>k){
				rownum=1;
				double sim = al.similarItemFind(maxrow,k,avgUser,j,matrixInput);
				if(!(sim >= threshold_similarity)){
					rownum=0;
				}
				else{
					rownum=1;al.dataAdd(k, j, null);
				}k++;
			}j++;
		}
		int m=1;
		int[][]b = new int[maxrow][maxcolumn];		
		int numval=0;
		while(maxrow>m){
			int n=1;
			while(n<maxcolumn){
				if(!(matrixInput[m][n]==0)){
					numval=0;
					b[m][n]=matrixInput[m][n];
				}
				else{  
					List<Integer> value_sim = al.keyValueGet(n);
					if(!(value_sim!=null)){
						numval=1;
						b[m][n]=1;
			    	}
					else{
						Double value_pred = al.totalSumWeighted(value_sim,matrixInput,m,n);
						numval=2;
						int val_pr = (int)Math.round(value_pred);
						if(!(val_pr > 5)&& !(val_pr<=0)){
							numval=0;
							b[m][n]=val_pr;
						}
						else if(val_pr > 5){
							numval=1;
							b[m][n]=5;
						}
						else if(val_pr<=0){
							numval=0;
							b[m][n]=1;
						}
					}				
				}n++;
			}m++;
		}
		
		al.fileWrite(maxcolumn, b, f, maxrow);
		numval=0;
		System.out.println("Output file generated as \"output.txt\"");
	}
}