import java.util.*;
@SuppressWarnings("unused")


public class Driver {

	public static void main(String[] args) throws Exception {
		int maxrow=944;
		int maxcolumn=1683;
		Double threshold_similarity =0.3;
		
		Integer val = null;
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
		
		
		System.out.println("In process....");
		for(int i=1; i<maxrow; i++) {
    		avgUser[i]=al.averageUser(i,maxcolumn,matrixInput);
    		int rownumval=0;
    	}
    	
		for(int j=1; j<maxcolumn; j++) {
			for(int k=1; k<maxcolumn; k++) {
				double sim = al.similarItemFind(maxrow,k,avgUser,j,matrixInput);
				if(threshold_similarity <= sim){
					al.dataAdd(k, j, null);
				}
			}
		}
		
		
		int[][]b = new int[maxrow][maxcolumn];		

		for(int m=1; m<maxrow; m++) {
			for(int n=1; n<maxcolumn; n++) {
				if(!(matrixInput[m][n]==0)){
					b[m][n]=matrixInput[m][n];
				}
				else{  
					List<Integer> value_sim = al.keyValueGet(n);
					if(!(value_sim!=null)){
						b[m][n]=1;
			    	}
					else{
						Double value_pred = al.totalSumWeighted(value_sim,matrixInput,m,n);
						int val_pr = (int)Math.round(value_pred);
						if(!(val_pr > 5)&& !(val_pr<=0)){
							b[m][n]=val_pr;
						}
						else if(val_pr > 5){
							b[m][n]=5;
						}
						else if(val_pr<=0){
							b[m][n]=1;
						}
					}				
				}
			}
		}
		
		al.fileWrite(maxcolumn, b, f, maxrow);
		System.out.println("Output file generated as \"output.txt\"");
	}
}
