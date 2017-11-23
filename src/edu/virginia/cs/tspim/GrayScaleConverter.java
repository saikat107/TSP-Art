
package edu.virginia.cs.tspim;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import edu.virginia.cs.tspim.util.Config;

public class GrayScaleConverter {

	private String imgFile;
//	MSTGenerator mstGen ;

	public GrayScaleConverter(String img_file) {
		this.imgFile = img_file;
//		mstGen = new MSTGenerator(imgFile);
	}

	public Mat convertToGray() throws IOException {
			File input = new File(this.imgFile);
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
//			mstGen.generateMST(mat1);
			//data.length;
			return mat1;
	}
}
