
package edu.virginia.cs.tspim;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import edu.virginia.cs.tspim.util.Config;
import edu.virginia.cs.tspim.util.Util;

class GrayScale_Conversion {

	String image_file;
	int sum;
	float average_threshold;
	int[][] sample_pre_array;
	ArrayList<Node> nodeList = new ArrayList<Node>();
	double[][] adjacency_matrix;

	GrayScale_Conversion(String img_file) {
		image_file = img_file;
		sum = 0;
		average_threshold = 0;
	}

	public float get_threshold() {
		return average_threshold;
	}

	public void convert_to_Gray() {
		try {

			File input = new File(image_file);
			BufferedImage image = ImageIO.read(input);

			byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
			Mat mat = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
			mat.put(0, 0, data);

			Mat mat1 = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC1);
			Imgproc.cvtColor(mat, mat1, Imgproc.COLOR_RGB2GRAY);
			// Imgproc.threshold(mat,mat1, 127,255, Imgproc.THRESH_BINARY);

			byte[] data1 = new byte[mat1.rows() * mat1.cols() * (int) (mat1.elemSize())];
			mat1.get(0, 0, data1);

			BufferedImage image1 = new BufferedImage(mat1.cols(), mat1.rows(), BufferedImage.TYPE_BYTE_GRAY);
			image1.getRaster().setDataElements(0, 0, mat1.cols(), mat1.rows(), data1);
			sample_pre_array = new int[mat1.rows()][mat1.cols()];
			// Util.logln(mat1.get(0, 0).length);
			for (int i = 0; i < mat1.rows(); i++) {
				for (int j = 0; j < mat1.cols(); j++) {
					sample_pre_array[i][j] = (int) mat1.get(i, j)[0];
				}
			}
			for (int i = 0; i < mat1.rows(); i++) {
				for (int j = 0; j < mat1.cols(); j++) {
					sum += sample_pre_array[i][j];
				}
			}

			average_threshold = sum / (mat1.rows() * mat1.cols());
			// Util.logln(average_threshold);
			int numRows = mat1.rows();
			int numCols = mat1.cols();
			int[][] nodesImg = new int[numRows][numCols];
			for (int i = 0; i < numRows; i++) {
				for (int j = 0; j < numCols; j++) {
					nodesImg[i][j] = 255;
				}
			}

			for (int i = 0; i < numRows; i += Config.BLOCK_SIZE) {
				for (int j = 0; j < numCols; j += Config.BLOCK_SIZE) {
					double blockAvg = 0;
					for (int x = 0; x < Config.BLOCK_SIZE; x++) {
						for (int y = 0; y < Config.BLOCK_SIZE; y++) {
							if (i + x < numRows && j + y < numCols)
								blockAvg += sample_pre_array[i + x][j + y];
						}
					}
					blockAvg /= (Config.BLOCK_SIZE * Config.BLOCK_SIZE);
					if (blockAvg < Config.ALPHA * average_threshold) {
						int xLimit = Config.BLOCK_SIZE;
						int yLimit = Config.BLOCK_SIZE;
						Node n = new Node();
						if (i + xLimit < numRows && j + yLimit < numCols) {
							int x = i + Util.genarateRandom(0, Config.BLOCK_SIZE - 1);
							int y = j + Util.genarateRandom(0, Config.BLOCK_SIZE - 1);
							nodesImg[x][y] = 0;
							n.setX(x);
							n.setY(y);

						} else {
							int x = i + Util.genarateRandom(0, numRows - i - 1);
							int y = j + Util.genarateRandom(0, numCols - j - 1);
							nodesImg[x][y] = 0;
							n.setX(x);
							n.setY(y);
						}
						nodeList.add(n);
					}
				}
			}

			Util.writeImage(nodesImg, "NodesImg/" + Config.fileName);
			Util.logln(nodeList.size());
			Writer w = new FileWriter("Node.csv");
			
			for(int i=0;i<nodeList.size();i++)
			{
				Node n = nodeList.get(i);
				w.write(String.valueOf(n.x)+ " "+String.valueOf(n.y));
				w.write("\n");
			}
			
			w.close();
			
			//ArrayList<Node>ulist=new ArrayList<Node>();
			
			nodeList = (ArrayList<Node>) nodeList.stream().distinct().collect(Collectors.toList());
			
			Util.logln(nodeList.size());
			// adjacency_matrix = new double[nodeList.size()][nodeList.size()];
			/*
			 * for (int i =0; i<nodeList.size();i++) { Node v1 =
			 * nodeList.get(i); for(int j=0;j<nodeList.size();j++) { Node v2 =
			 * nodeList.get(j); double distance =
			 * Math.sqrt((v1.getX()-v2.getX())*(v1.getX()-v2.getX())+(v1.getY()-
			 * v2.getY())*(v1.getY()-v2.getY())); adjacency_matrix[i][j] =
			 * distance;
			 * 
			 * if(i==j) { adjacency_matrix[i][j] = 0.0; continue; }
			 * 
			 * if (adjacency_matrix[i][j] == 0.0)
			 * 
			 * {
			 * 
			 * adjacency_matrix[i][j] = Config.MAX_VALUE;
			 * 
			 * }
			 * 
			 * 
			 * } } /* for(int i =0;i<nodeList.size();i++) { for(int
			 * j=0;j<nodeList.size();j++)
			 * System.out.print(adjacency_matrix[i][j]+ "\t");
			 * System.out.println();
			 * 
			 * }
			 */
			/*
			 * KruskalAlgorithm al = new KruskalAlgorithm(nodeList.size());
			 * //al.kruskalAlgorithm(adjacency_matrix,nodeList);
			 */
			MST gr = new MST(nodeList.size(), ((nodeList.size() * (nodeList.size() - 1)) / 2), nodeList);
			int l = 0;
			Writer w1 = new FileWriter("Test.csv");

			for (int i = 0; i < nodeList.size() - 1; i++) {
				Node v1 = nodeList.get(i);
				for (int j = i + 1; j < nodeList.size(); j++) {
					Node v2 = nodeList.get(j);
					double distance = Math.sqrt(((v1.getX()-v2.getX())*(v1.getX()-v2.getX()))+((v1.getY()-v2.getY())*(v1.getY()-v2.getY())));
					if(i!=j)
					{
						gr.edge[l].src = i;
						gr.edge[l].dest = j;
						gr.edge[l].weight = distance;
						w1.write(String.valueOf(gr.edge[l].src));
						w1.write(",");
						w1.write(String.valueOf(gr.edge[l].dest));
						w1.write(",");
						w1.write(String.valueOf(gr.edge[l].weight));
						w1.write("\n");
						l++;
					}

				}
			}
			Util.logln(l);
			w1.close();
			gr.KruskalMST();

			Density_Sampling s = new Density_Sampling(sample_pre_array, average_threshold);
			s.gen_Sample();

			File ouptut = new File("grayImg/" + Config.fileName);
			ImageIO.write(image1, "jpg", ouptut);
		} catch (Exception e) {
			// System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public int[][] get_array() {

		return sample_pre_array;
	}
}
