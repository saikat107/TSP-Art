
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

public class GrayScaleConverter {

	String imageFile;
	int sum;
	float averageThreshold;
	int[][] samplePreArray;
	ArrayList<Node> nodeList = new ArrayList<Node>();
	double[][] adjacencyMatrix;

	public GrayScaleConverter(String img_file) {
		imageFile = img_file;
		sum = 0;
		averageThreshold = 0;
	}

	public float getThreshold() {
		return averageThreshold;
	}

	public void convertToGray() {
		try {
			File input = new File(imageFile);
			BufferedImage image = ImageIO.read(input);

			byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
			Config.getInstance().setImageHeight(image.getHeight());
			Config.getInstance().setImageWidth(image.getWidth());
			Mat mat = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
			mat.put(0, 0, data);

			Mat mat1 = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC1);
			Imgproc.cvtColor(mat, mat1, Imgproc.COLOR_RGB2GRAY);
			// Imgproc.threshold(mat,mat1, 127,255, Imgproc.THRESH_BINARY);

			byte[] data1 = new byte[mat1.rows() * mat1.cols() * (int) (mat1.elemSize())];
			mat1.get(0, 0, data1);

			BufferedImage image1 = new BufferedImage(mat1.cols(), mat1.rows(), BufferedImage.TYPE_BYTE_GRAY);
			image1.getRaster().setDataElements(0, 0, mat1.cols(), mat1.rows(), data1);
			samplePreArray = new int[mat1.rows()][mat1.cols()];
			// Util.logln(mat1.get(0, 0).length);
			for (int i = 0; i < mat1.rows(); i++) {
				for (int j = 0; j < mat1.cols(); j++) {
					samplePreArray[i][j] = (int) mat1.get(i, j)[0];
				}
			}
			for (int i = 0; i < mat1.rows(); i++) {
				for (int j = 0; j < mat1.cols(); j++) {
					sum += samplePreArray[i][j];
				}
			}

			averageThreshold = sum / (mat1.rows() * mat1.cols());
			// Util.logln(average_threshold);
			int numRows = mat1.rows();
			int numCols = mat1.cols();
			int[][] nodesImg = new int[numRows][numCols];
			for (int i = 0; i < numRows; i++) {
				for (int j = 0; j < numCols; j++) {
					nodesImg[i][j] = 255;
				}
			}
			int blockSize = Config.getInstance().getBlockSize();
			for (int i = 0; i < numRows; i += blockSize) {
				for (int j = 0; j < numCols; j += blockSize) {
					double blockAvg = 0;
					for (int x = 0; x < blockSize; x++) {
						for (int y = 0; y < blockSize; y++) {
							if (i + x < numRows && j + y < numCols)
								blockAvg += samplePreArray[i + x][j + y];
						}
					}
					blockAvg /= (blockSize * blockSize);
					if (blockAvg < Config.getInstance().getAlpha() * averageThreshold) {
						int xLimit = blockSize;
						int yLimit = blockSize;
						Node n = new Node();
						if (i + xLimit < numRows && j + yLimit < numCols) {
							int x = i + Util.genarateRandom(0, blockSize - 1);
							int y = j + Util.genarateRandom(0, blockSize - 1);
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
			Util.logln(nodeList.size());
			nodeList = (ArrayList<Node>) nodeList.stream().distinct().collect(Collectors.toList());	
			Util.logln(nodeList.size());
			MST gr = new MST(nodeList.size(), ((nodeList.size() * (nodeList.size() - 1)) / 2), nodeList);
			int l = 0;
			//Writer w1 = new FileWriter("Test.csv");
			for (int i = 0; i < nodeList.size() - 1; i++) {
				Node v1 = nodeList.get(i);
				for (int j = i + 1; j < nodeList.size(); j++) {
					Node v2 = nodeList.get(j);
					double distance = Math.sqrt(((v1.getX()-v2.getX())*(v1.getX()-v2.getX()))+((v1.getY()-v2.getY())*(v1.getY()-v2.getY())));
					if(i!=j){
						gr.edge[l].src = i;
						gr.edge[l].dest = j;
						gr.edge[l].weight = distance;
//						w1.write(String.valueOf(gr.edge[l].src));
//						w1.write(",");
//						w1.write(String.valueOf(gr.edge[l].dest));
//						w1.write(",");
//						w1.write(String.valueOf(gr.edge[l].weight));
//						w1.write("\n");
						l++;
					}
				}
			}
			Util.logln(l);
//			w1.close();
			gr.KruskalMST();
			DensitySampler s = new DensitySampler(samplePreArray, averageThreshold);
			s.genSample();
		} catch (Exception e) {
			// System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public int[][] get_array() {

		return samplePreArray;
	}
}
