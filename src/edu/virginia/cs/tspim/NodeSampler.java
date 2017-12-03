package edu.virginia.cs.tspim;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.opencv.core.Mat;

import edu.virginia.cs.tspim.util.Config;
import edu.virginia.cs.tspim.util.Util;

public class NodeSampler {
	private String imageFile;
	private int sum;
	private float averageThreshold;
	private int[][] samplePreArray;
	private ArrayList<Node> nodeList;
	private double[][] adjacencyMatrix;
	private Image nodeImage;

	public NodeSampler(String imageFile) {
		Config config = Config.getInstance();
		this.imageFile = imageFile;
		this.nodeList = new ArrayList<>();
		nodeImage = new Image(config.getImageWidth(), config.getImageHeight(), config.getScale(), "Nodes Image");
	}

	public List<Node> generateNodeSampleList(Mat mat) throws IOException {
		samplePreArray = new int[mat.rows()][mat.cols()];
		// Util.logln(mat1.get(0, 0).length);
		for (int i = 0; i < mat.rows(); i++) {
			for (int j = 0; j < mat.cols(); j++) {
				samplePreArray[i][j] = (int) mat.get(i, j)[0];
			}
		}
		for (int i = 0; i < mat.rows(); i++) {
			for (int j = 0; j < mat.cols(); j++) {
				sum += samplePreArray[i][j];
			}
		}
	
		averageThreshold = sum / (mat.rows() * mat.cols());
		// Util.logln(average_threshold);
		int numRows = mat.rows();
		int numCols = mat.cols();
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
		//Util.logln(nodeList.size());
		nodeList = (ArrayList<Node>) nodeList.stream().distinct().collect(Collectors.toList());	
		for(Node n: nodeList){
			nodeImage.setScaledNodeInImage(n);
		}
		//nodeImage.showImage();
		return nodeList;
	}

	public Image getNodeImage(){
		return this.nodeImage;
	}
}