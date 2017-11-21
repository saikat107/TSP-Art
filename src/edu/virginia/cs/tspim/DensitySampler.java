
package edu.virginia.cs.tspim;

import edu.virginia.cs.tspim.util.Config;
import edu.virginia.cs.tspim.util.Util;

public class DensitySampler {
	int [][]sampledArray;
	int [][]presampledArray;
	float threshold;
	
	public DensitySampler(int[][]mat, float avg)
	{
		threshold = avg;
	    presampledArray = new int [mat.length][mat[0].length];
		presampledArray = mat;
		//sampled_array = new int [mat.length-1][mat[0].length-1];
		sampledArray = new int [mat.length/2][mat[0].length/2];
		
	}
	
	public void genSample()
	{
		
		//System.out.println(presampled_array.length + " "+presampled_array[0].length);
		
		for(int k=0;k<presampledArray.length-1;k++)
		{
			
			for(int l=0;l<presampledArray[0].length-1;l++)
			{
				//sampled_array[k][l]=(presampled_array[i][j]+presampled_array[i+1][j]+presampled_array[i][j+1]+presampled_array[i+1][j+1])/4;
				//System.out.println(k+ " "+ l+ ": "+presampled_array[k][l]+" ");

			}
		}
		//int k=0,l=0;
		for(int i=0,k=0;i<presampledArray.length-1;i+=2,k++)
		{
			
			for(int j=0,l=0;j<presampledArray[0].length-1;j+=2,l++)
			{
				sampledArray[k][l]=(presampledArray[i][j]+presampledArray[i+1][j]+presampledArray[i][j+1]+presampledArray[i+1][j+1])/4;
				//System.out.println(k+ " "+ l+ ": "+sampled_array[k][l]+" ");

			}
		}
		
		for(int i=0;i<sampledArray.length;i++)
		{
			for(int j=0;j<sampledArray[0].length;j++)
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
