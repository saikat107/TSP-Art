package edu.virginia.cs.tspim.gui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.List;



import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import edu.virginia.cs.tspim.GrayScaleConverter;
import edu.virginia.cs.tspim.Image;
import edu.virginia.cs.tspim.Node;
import edu.virginia.cs.tspim.NodeSampler;
import edu.virginia.cs.tspim.TourExtractor;
import edu.virginia.cs.tspim.TreeEdges;
import edu.virginia.cs.tspim.util.Config;
import edu.virginia.cs.tspim.util.Util;

/**
 *
 * @author saikat
 */
public class MainGui extends javax.swing.JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2152207955078179312L;
	private String filePath;
    private FileOpener fp;

    /**
     * Creates new form MainGui
     */
    public MainGui() {
        this.filePath = "";
        initComponents();
    }
    
    public void setFilePath(String p){
        this.filePath = p;
        filePathTextBox.setText(p);
        if(fp != null){
            fp.setVisible(false);
        }
//        System.out.println();
    }

    
    private void swappedImgCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {                                                    
    	if(evt.getStateChange() == ItemEvent.DESELECTED){
	        iterationNumbeField.setEnabled(false);
	    }
	    else{
	        iterationNumbeField.setEnabled(true);
	    }
	}
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        openButton = new javax.swing.JButton();
        mainScrollPanel = new javax.swing.JScrollPane();
        filePathTextBox = new javax.swing.JTextPane();
        runAlgorithmButton = new javax.swing.JButton();
        alphaChooser = new javax.swing.JSlider();
        alphaLabel = new javax.swing.JLabel();
        blockSizeChooser = new javax.swing.JSpinner();
        blockSizeLabel = new javax.swing.JLabel();
        alphaValurLbl = new javax.swing.JLabel();
        scaleSlider = new javax.swing.JSlider();
        scaleLabel = new javax.swing.JLabel();
        mstImgCheckBox = new javax.swing.JCheckBox();
        tourImgCheckBox = new javax.swing.JCheckBox();
        swappedImgCheckBox = new javax.swing.JCheckBox();
        iterationNumbeField = new javax.swing.JTextField();
        numIterationLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        imgPrefLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        iterationNumbeField.setToolTipText("Numbers only");
        iterationNumbeField.setHorizontalAlignment(SwingConstants.CENTER);
        iterationNumbeField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				char ch = e.getKeyChar();
		        if(ch < '0' || ch >'9'){
		            String text = iterationNumbeField.getText();
		            if(text.length() == 0) return;
		            text = text.substring(0, text.length() - 1);
		            iterationNumbeField.setText(text);
		        }  
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

        openButton.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        openButton.setText("Open");
        openButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openButtonActionPerformed(evt);
            }
        });

        mainScrollPanel.setViewportView(filePathTextBox);

        runAlgorithmButton.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        runAlgorithmButton.setText("Run");
        runAlgorithmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runAlgorithmButtonActionPerformed(evt);
            }
        });

        alphaChooser.setMinorTickSpacing(10);
        alphaChooser.setPaintTicks(true);
        alphaChooser.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                alphaChooserStateChanged(evt);
            }
        });
        alphaChooser.setValue(75);

        alphaLabel.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        alphaLabel.setText("Alpha");

        blockSizeChooser.setRequestFocusEnabled(false);
        blockSizeChooser.setValue(1);
        blockSizeChooser.setVerifyInputWhenFocusTarget(false);

        blockSizeLabel.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        blockSizeLabel.setText("Block Size");

        alphaValurLbl.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        alphaValurLbl.setText("0.75");

        scaleSlider.setMajorTickSpacing(1);
        scaleSlider.setMaximum(10);
        scaleSlider.setMinimum(1);
        scaleSlider.setMinorTickSpacing(1);
        scaleSlider.setPaintLabels(true);
        scaleSlider.setPaintTicks(true);
        scaleSlider.setValue(2);
        

        scaleLabel.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        scaleLabel.setText("Scale");

        mstImgCheckBox.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        mstImgCheckBox.setText("MST Image");
        mstImgCheckBox.setSelected(true);
        

        tourImgCheckBox.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        tourImgCheckBox.setText("TSP Tour Image");
        tourImgCheckBox.setSelected(true);
        tourImgCheckBox.addItemListener(new ItemListener() {			
			@Override
			public void itemStateChanged(ItemEvent e) {
				tourImgCheckBoxItemStateChanged(e);
			}
		});

        swappedImgCheckBox.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        swappedImgCheckBox.setText("TSP Swapped Image");
        swappedImgCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                swappedImgCheckBoxItemStateChanged(evt);
            }
        });
        swappedImgCheckBox.setSelected(true);
        

        iterationNumbeField.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        iterationNumbeField.setText("1");

        numIterationLabel.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        numIterationLabel.setText("Number Of Iteration");

        imgPrefLabel.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        imgPrefLabel.setText("Image Preferences");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(runAlgorithmButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mstImgCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
                .addComponent(tourImgCheckBox)
                .addGap(68, 68, 68)
                .addComponent(swappedImgCheckBox)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(iterationNumbeField, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scaleSlider, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
                    .addComponent(alphaChooser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mainScrollPanel)
                    .addComponent(blockSizeChooser, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(blockSizeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(alphaValurLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(alphaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(openButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scaleLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(numIterationLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(jSeparator1)
            .addComponent(imgPrefLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(mainScrollPanel)
                    .addComponent(openButton, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(alphaValurLbl, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(alphaChooser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(alphaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(scaleSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scaleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(imgPrefLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mstImgCheckBox)
                    .addComponent(tourImgCheckBox)
                    .addComponent(swappedImgCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(iterationNumbeField, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(numIterationLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(blockSizeChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(blockSizeLabel))
                .addGap(18, 18, 18)
                .addComponent(runAlgorithmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>    
    protected void tourImgCheckBoxItemStateChanged(ItemEvent e) {
    	if(e.getStateChange() == ItemEvent.DESELECTED){
    		swappedImgCheckBox.setEnabled(false);
	        iterationNumbeField.setEnabled(false);
	    }
	    else{
	    	swappedImgCheckBox.setEnabled(true);
	        iterationNumbeField.setEnabled(true);
	    }	
	}

	private void openButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
        fp = new FileOpener(this);
        fp.setVisible(true);
    }                                          

    private void runAlgorithmButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        int d = alphaChooser.getValue();
        double alpha = d / 100.00;
        int scale = scaleSlider.getValue();
        int blockSize = (int)blockSizeChooser.getValue();
        boolean mstImgShow = mstImgCheckBox.isSelected();
        boolean tourImgShow = tourImgCheckBox.isSelected();
        boolean swapImagShow = swappedImgCheckBox.isSelected();
        int numIter = Integer.parseInt(iterationNumbeField.getText());
        if(filePath.endsWith(".jpg") || filePath.endsWith(".JPG") || 
                filePath.endsWith(".png") || filePath.endsWith(".PNG") ||
                filePath.endsWith(".bmp") || filePath.endsWith(".BMP") ||
                filePath.endsWith(".jpeg") || filePath.endsWith(".JPEG")){
            //System.out.println(filePath + " " + alpha + " " + blockSize );
        	Config.getInstance().setAlpha(alpha);
        	Config.getInstance().setFileName(filePath);
        	Config.getInstance().setBlockSize(blockSize);
        	Config.getInstance().setScale(scale);
        	
        	Util.logln(Config.getInstance());
        	//this.setVisible(false);
        	Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					System.loadLibrary( Core.NATIVE_LIBRARY_NAME);
		        	String file_name = Config.getInstance().getFileName();//input_sc.nextLine();
		            GrayScaleConverter con = new GrayScaleConverter(file_name);
		            try {
						Mat mat = con.convertToGray();
						NodeSampler mstGen = new NodeSampler(file_name);
						List<Node> nodeList = mstGen.generateNodeSampleList(mat);
						Util.logln(nodeList.size());
						List<TreeEdges> tree = Util.generateMST(nodeList);
						TourExtractor extractor = new TourExtractor(Config.getInstance().getImageWidth(), 
								Config.getInstance().getImageHeight(), Config.getInstance().getScale());
						extractor.extractTourImage(tree, mstImgShow, tourImgShow, swapImagShow, numIter);
						Image nodes = mstGen.getNodeImage();
						//nodes.showImage();
						if(mstImgShow){
							Image mst = extractor.getMSTImage();
							mst.showImage();
						}
						if(tourImgShow){
							Image tour = extractor.getTourImage();
							tour.showImage();
							if(swapImagShow){
								Image swp = extractor.getSwappedImage();
								swp.showImage();
							}
						}
						
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
        	t.start();
        	try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        	
        }
        else if(filePath == ""){
            JDialog dialog = new JDialog(this);
            dialog.setSize(300, 100);
            JLabel lb = new JLabel();
            lb.setText("Please select a file first");
            dialog.add(lb);
            dialog.setVisible(true);
        }
        else{
            JDialog dialog = new JDialog(this);
            dialog.setSize(300, 100);
            JLabel lb = new JLabel();
            lb.setText("Only JPG PNG BMP\n files are supported");
            dialog.add(lb);
            dialog.setVisible(true);
        }
    }                                                  

    private void alphaChooserStateChanged(javax.swing.event.ChangeEvent evt) {                                          
         int v = alphaChooser.getValue();
         double d = v / 100.0;
         alphaValurLbl.setText(String.format("%.2f", d));
    }                                         

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
                new MainGui().setVisible(true);
//            }
//        });
    }

 // Variables declaration - do not modify                     
    private javax.swing.JSlider alphaChooser;
    private javax.swing.JLabel alphaValurLbl;
    private javax.swing.JSpinner blockSizeChooser;
    private javax.swing.JTextPane filePathTextBox;
    private javax.swing.JTextField iterationNumbeField;
    private javax.swing.JLabel alphaLabel;
    private javax.swing.JLabel blockSizeLabel;
    private javax.swing.JLabel scaleLabel;
    private javax.swing.JLabel numIterationLabel;
    private javax.swing.JLabel imgPrefLabel;
    private javax.swing.JScrollPane mainScrollPanel;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSlider scaleSlider;
    private javax.swing.JCheckBox mstImgCheckBox;
    private javax.swing.JButton openButton;
    private javax.swing.JButton runAlgorithmButton;
    private javax.swing.JCheckBox swappedImgCheckBox;
    private javax.swing.JCheckBox tourImgCheckBox;
    // End of variables declaration                                    
}
