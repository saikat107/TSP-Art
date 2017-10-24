package edu.virginia.cs.tspim;

import edu.virginia.cs.tspim.util.Util;

public class Density_Sampling {
	int [][]sampled_array;
	int [][]presampled_array;
	float threshold;
	
	Density_Sampling(int[][]mat, float avg)
	{
		threshold = avg;
	    presampled_array = new int [mat.length][mat[0].length];
		presampled_array = mat;
		sampled_array = new int [mat.length-1][mat[0].length-1];
		
	}
	
	void gen_Sample()
	{
		
		System.out.println(presampled_array.length + " "+presampled_array[0].length);
		//int k=0,l=0;
		for(int i=0,k=0;i<presampled_array.length-1;i++,k++)
		{
			
			for(int j=0,l=0;j<presampled_array[0].length-1;j++,l++)
			{
				sampled_array[k][l]=(presampled_array[i][j]+presampled_array[i+1][j]+presampled_array[i][j+1]+presampled_array[i+1][j+1])/4;
			}
		}
		
		/*for(int i=0;i<sampled_array.length;i++)
		{
			for(int j=0;j<sampled_array[0].length;j++)
			{
				System.out.print(sampled_array[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println(sampled_array.length + " "+sampled_array[0].length);*/
		
	}

}
