
package edu.virginia.cs.tspim;

import edu.virginia.cs.tspim.util.Config;
import edu.virginia.cs.tspim.util.Util;

public class Density_Sampling {
	int [][]sampled_array;
	int [][]presampled_array;
	float threshold;
	
	public Density_Sampling(int[][]mat, float avg)
	{
		threshold = avg;
	    presampled_array = new int [mat.length][mat[0].length];
		presampled_array = mat;
		//sampled_array = new int [mat.length-1][mat[0].length-1];
		sampled_array = new int [mat.length/2][mat[0].length/2];
		
	}
	
	public void gen_Sample()
	{
		
		//System.out.println(presampled_array.length + " "+presampled_array[0].length);
		
		for(int k=0;k<presampled_array.length-1;k++)
		{
			
			for(int l=0;l<presampled_array[0].length-1;l++)
			{
				//sampled_array[k][l]=(presampled_array[i][j]+presampled_array[i+1][j]+presampled_array[i][j+1]+presampled_array[i+1][j+1])/4;
				//System.out.println(k+ " "+ l+ ": "+presampled_array[k][l]+" ");

			}
		}
		//int k=0,l=0;
		for(int i=0,k=0;i<presampled_array.length-1;i+=2,k++)
		{
			
			for(int j=0,l=0;j<presampled_array[0].length-1;j+=2,l++)
			{
				sampled_array[k][l]=(presampled_array[i][j]+presampled_array[i+1][j]+presampled_array[i][j+1]+presampled_array[i+1][j+1])/4;
				//System.out.println(k+ " "+ l+ ": "+sampled_array[k][l]+" ");

			}
		}
		
		for(int i=0;i<sampled_array.length;i++)
		{
			for(int j=0;j<sampled_array[0].length;j++)
			{
				//System.out.print(sampled_array[i][j]+" ");
			}
	//		System.out.println();
		}
		
//		Util.writeImage(sampled_array, "Shortened/" + Config.getInstance().getFileName()); 
		//System.out.println(sampled_array.length + " "+sampled_array[0].length);
		//System.out.println(presampled_array.length + " "+presampled_array[0].length);
		
	}
	

}
