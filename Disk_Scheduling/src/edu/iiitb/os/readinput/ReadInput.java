package edu.iiitb.os.readinput;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import edu.iiitb.os.PlotGraph.PlotGraph;
import edu.iiitb.os.clook.CLOOK;
import edu.iiitb.os.consolidatedGraph.ConsolidatedGraph;
import edu.iiitb.os.cscan.CSCAN;
import edu.iiitb.os.fcfs.FCFS;
import edu.iiitb.os.look.LOOK;
import edu.iiitb.os.scan.SCAN;
import edu.iiitb.os.sstf.SSTF;
import edu.iiitb.os.tableview.SummaryTable;

public class ReadInput extends JFrame implements ActionListener{
	
	
	JLabel trackNosLabel,headPosLabel,maxDiskPosLabel,dirLabel;
	JTextField trackNosText,headPosText,maxDiskPosText;
	JRadioButton up,down;
	JButton go;
	JCheckBox individual,consolidated,table;
	ButtonGroup direction;
	public ReadInput() {
		
		setLocation(100,100);
		setSize(800,900);
		setTitle("Disk Scheduling Application");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);

				
		trackNosLabel = new JLabel("ENTER TRACK NUMBER");
		headPosLabel = new JLabel("START HEAD POS");
		maxDiskPosLabel = new JLabel("MAX DISK POS");
		dirLabel = new JLabel("DIRECTION");
		up = new JRadioButton("UP");
		down = new JRadioButton("DOWN");
	    
		
		direction = new ButtonGroup();
		direction.add(up);
		direction.add(down);
		direction.clearSelection();
	    
	    trackNosText = new JTextField();
	    headPosText = new JTextField();
		maxDiskPosText = new JTextField();
		
		trackNosLabel.setBounds(10,10, 200, 20);
		headPosLabel.setBounds(10,50, 200, 20);
		maxDiskPosLabel.setBounds(10,90, 200, 20);
		dirLabel.setBounds(10,130,200, 20);
		
		trackNosText.setBounds(250,10, 200, 20);
		headPosText.setBounds(250,50, 200, 20);
		maxDiskPosText.setBounds(250,90, 200, 20);
		
		up.setBounds(250,130, 50, 20);
		down.setBounds(350,130, 100, 20);
		
		individual = new JCheckBox("SEPARATE GRAPH VIEW");
		table = new JCheckBox("TABLE VIEW");
		consolidated = new JCheckBox("CONSOLIDATED VIEW");
		individual.setBounds(120,150, 200, 50);
		table.setBounds(120,200, 200, 50);
		consolidated.setBounds(120,250, 200, 50);
		
		go=new JButton("GO");
		go.setBounds(150,320,75,50);
		add(trackNosText);
		add(headPosText);
		add(maxDiskPosText);
		
		add(trackNosLabel);
		add(headPosLabel);
		add(maxDiskPosLabel);
		add(maxDiskPosText);
		add(dirLabel);
		add(up);
		add(down);
		add(individual);
		add(table);
		add(consolidated);
		add(go);
		go.addActionListener(this);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent arg0) {
		Object o =arg0.getSource();
		String TrackNos = trackNosText.getText();
		int trackNo[]=null;
		int headPos=0;
		int maxPos=199;
		char dir;
		boolean ok=true;
		if(o.equals(go))
		{
		if(trackNosText.getText().equals(" ")|| headPosText.getText().equals("")||maxDiskPosText.getText().equals(""))
		{
		
			if(trackNosText.getText().equals(""))
				JOptionPane.showMessageDialog(null, "FIELD CANNOT BE EMPTY PLEASE FILL TRACK NO");
			if(headPosText.getText().equals(""))
				JOptionPane.showMessageDialog(null, "FIELD CANNOT BE EMPTY PLEASE FILL START HEAD POS");
			if(maxDiskPosText.getText().equals(""))
				JOptionPane.showMessageDialog(null, "FIELD CANNOT BE EMPTY PLEASE FILL MAX HEAD POS");
			ok=false;
	    }		
/*		else if(Integer.parseInt(headPosText.getText())<0 || Integer.parseInt(maxDiskPosText.getText())<0)
		{
			JOptionPane.showMessageDialog(null, "Track no. cannot be negative");
			ok=false;
		}
*/		if(direction.isSelected(null))
		{
			JOptionPane.showMessageDialog(null, "FIELD CANNOT BE EMPTY PLEASE SELECT DIR");
			ok=false;

		}
		if(!table.isSelected() && !consolidated.isSelected() && !individual.isSelected())
		{
			JOptionPane.showMessageDialog(null, "Select atleast one checkbox");
			ok=false;
		}

		else{   
				String[] tmp = TrackNos.split(",");
				trackNo = new int[tmp.length];
				try
			    {
			    headPos=Integer.parseInt(headPosText.getText());
			    if((headPos<0) || headPos > Integer.parseInt(maxDiskPosText.getText()))
			    		{
					ok=false;
			    	JOptionPane.showMessageDialog(null, "PLEASE ENTER VALUE WITHIN RANGE IN START HEAD POS FIELD");
			    		}
			    }
			    catch(NumberFormatException e) {
			    	JOptionPane.showMessageDialog(null, "Only Single Integer allowed in HeadPos");
					ok=false;
			    }
	
				try
				{
			    maxPos=Integer.parseInt(maxDiskPosText.getText());
					if(maxPos<0)
					{
						System.out.println("here");
				    	JOptionPane.showMessageDialog(null, "Max disk Pos should not be less than 0");
						ok=false;
					}
			    }
			    catch(NumberFormatException e) {
			    	JOptionPane.showMessageDialog(null, "Only Single Integer allowed in Max disk Pos");
					ok=false;
			    }
			    try{
			    	for(int i = 0; i < tmp.length; i++) 
			    	{
			    	trackNo[i] = Integer.parseInt(tmp[i]);
			    		if(trackNo[i]<0)
			    		{
			    			JOptionPane.showMessageDialog(null, "NEGATIVE INTEGERS ARE NOT ALLOWED");
			    			ok=false;
			    		}
			    		if(trackNo[i]>maxPos)
			    		{
			    			JOptionPane.showMessageDialog(null, "TrackNo values cannot exceed max disk pos");
			    			ok=false;			    			
			    		}
			        }
			    }
			    catch(NumberFormatException e) {
			    	JOptionPane.showMessageDialog(null, "ONLY SPACE SEPARATED INTEGERS ALLOWED IN TRACK NO");
					ok=false;
			    	}
			    if(up.isSelected())
				dir='U';
				else
				dir='D';
				if(ok)
				{
			//Call appropriate functions
				int minHeadMov=100000;
				String bestAlgo="";
				int headMov[]=new int[6];
				
				FCFS fcfs=new FCFS(headPos,trackNo);
				System.out.println("FCFS: ");
				fcfs.fcfs();
				headMov[0]=fcfs.getHeadMov();
				System.out.println("Total Head Movement for FCFS= " + fcfs.getHeadMov());
				System.out.println();
				if(fcfs.getHeadMov()<minHeadMov)
					{
					minHeadMov=fcfs.getHeadMov();
					bestAlgo="FCFS";
					}
				
				System.out.println("SSTF: ");
				SSTF sstf=new SSTF(headPos,trackNo);
				sstf.sstf();
				headMov[1]=sstf.getHeadMov();

				System.out.println("Total Head Movement for SSTF= "+sstf.getHeadMov());
				System.out.println();
				if(sstf.getHeadMov()<minHeadMov)
				{
				minHeadMov=sstf.getHeadMov();
				bestAlgo="SSTF";
				}
				else if(sstf.getHeadMov()==minHeadMov)
				bestAlgo+=" SSTF";

				
				System.out.println("SCAN: ");
				SCAN scan=new SCAN(headPos,trackNo,dir,maxPos);
				scan.scan();
				headMov[2]=scan.getHeadMov();

				System.out.println("Total Head Movement for SCAN= "+scan.getHeadMov());
				System.out.println();
				if(scan.getHeadMov()<minHeadMov)
				{
				minHeadMov=scan.getHeadMov();
				bestAlgo="SCAN";
				}
				else if(scan.getHeadMov()==minHeadMov)
				bestAlgo+=" SCAN";

				System.out.println("CSCAN: ");
				CSCAN cscan=new CSCAN(headPos,trackNo,maxPos,dir);
				cscan.cscan();
				headMov[3]=cscan.getHeadMov();

				System.out.println("Total Head Movement for CSCAN= "+cscan.getHeadMov());
				System.out.println();
				if(cscan.getHeadMov()<minHeadMov)
				{
				minHeadMov=cscan.getHeadMov();
				bestAlgo="C-SCAN";
				}
				else if(cscan.getHeadMov()==minHeadMov)
				bestAlgo+=" C-SCAN";

				System.out.println("LOOK: ");
				LOOK look=new LOOK(headPos,trackNo,dir);
				look.look();
				headMov[4]=look.getHeadMov();

				System.out.println("Total Head Movement for LOOK= "+look.getHeadMov());
				if(look.getHeadMov()<minHeadMov)
				{
				minHeadMov=look.getHeadMov();
				bestAlgo="LOOK";
				}
				else if(look.getHeadMov()==minHeadMov)
				bestAlgo+=" LOOK";

				System.out.println();
				System.out.println("CLOOK: ");
				CLOOK clook=new CLOOK(headPos,trackNo,dir);
				clook.clook();
				headMov[5]=clook.getHeadMov();

				System.out.println("Total Head Movement for CLOOK= "+clook.getHeadMov());
				if(clook.getHeadMov()<minHeadMov)
				{
				minHeadMov=clook.getHeadMov();
				bestAlgo="C-LOOK";
				}
				else if(clook.getHeadMov()==minHeadMov)
				bestAlgo+=" C-LOOK";

				System.out.println();
				System.out.println("Best algo for this input is: "+bestAlgo);
				
				if(table.isSelected())
				{
		       JFrame frame = new JFrame("Table View->Best algo according to head movement is: "+bestAlgo);
		        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		        //Create and set up the content pane.
		        SummaryTable newContentPane = new SummaryTable(trackNo, fcfs, sstf, scan, cscan, look, clook);
		        newContentPane.setOpaque(true); //content panes must be opaque
		        frame.setContentPane(newContentPane);

		        //Display the window.
		        frame.pack();
		        frame.setVisible(true);
				}
		        
				int size=700;
				int initialLocX=20;
				int initialLocY=10;
				int buffer=10;

				if(consolidated.isSelected())
				{
				Object ob[]=new Object[6];
				ob[0]=fcfs;
				ob[1]=sstf;
				ob[2]=scan;
				ob[3]=cscan;
				ob[4]=look;
				ob[5]=clook;
				
		       JFrame consolidatedFrm=new ConsolidatedGraph( maxPos,ob, "Consolidated",bestAlgo,size,headMov,trackNo);
		       
		       consolidatedFrm.setSize (size, size);
		       consolidatedFrm.setLocation(initialLocX, initialLocY);
		       consolidatedFrm.setVisible (true);	
		       consolidatedFrm.setTitle("Consolidated view");
		       consolidatedFrm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	  
				}
				//Plot the results for all the algorithms
		       if(individual.isSelected())
		       {
		       	size=380;
				initialLocX=20;
				 initialLocY=10;
				 buffer=10;

		       JFrame fcfsFrm=new PlotGraph( maxPos,fcfs.getResult_plot(), "FCFS", fcfs.getHeadMov(),bestAlgo.contains("FCFS"),size);
		       fcfsFrm.setSize (size, size);
		       fcfsFrm.setLocation(initialLocX, initialLocY);
		       fcfsFrm.setVisible (true);	
		       fcfsFrm.setTitle("FCFS");
		       fcfsFrm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	  

		       JFrame sstfFrm=new PlotGraph( maxPos, sstf.getResult_plot(), "SSTF", sstf.getHeadMov(),bestAlgo.contains("SSTF"),size);
		        sstfFrm.setSize (size, size);
		        sstfFrm.setLocation(initialLocX, initialLocY+size+buffer);
		        sstfFrm.setVisible (true);	
		        sstfFrm.setTitle("SSTF");
		        sstfFrm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        
		        JFrame scanFrm=new PlotGraph( maxPos, scan.getResult_plot(), "SCAN", scan.getHeadMov(),bestAlgo.contains("SCAN") ,size);
		        scanFrm.setSize (size, size);
		        scanFrm.setLocation(initialLocX+size+buffer, initialLocY);
		        scanFrm.setVisible (true);	
		        scanFrm.setTitle("SCAN");
		        scanFrm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        
		        JFrame cscanFrm=new PlotGraph( maxPos, cscan.getResult_plot(), "C-SCAN",cscan.getHeadMov(),bestAlgo.contains("C-SCAN"),size );
		        cscanFrm.setSize (size, size);
		        cscanFrm.setLocation(initialLocX+size+buffer, initialLocY+size+buffer);
		        cscanFrm.setVisible (true);	
		        cscanFrm.setTitle("C-SCAN");
		        cscanFrm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        
		        JFrame lookFrm=new PlotGraph( maxPos, look.getResult_plot(), "LOOK" ,look.getHeadMov(),bestAlgo.contains("LOOK"),size);
		        lookFrm.setSize (size, size);
		        lookFrm.setLocation(initialLocX+size*2+buffer, initialLocY);
		        lookFrm.setVisible (true);
		        lookFrm.setTitle("LOOK");
		        lookFrm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        
		        JFrame clookFrm=new PlotGraph( maxPos, clook.getResult_plot(), "C-LOOK",clook.getHeadMov(),bestAlgo.contains("C-LOOK"),size);
		        clookFrm.setSize (size, size);
		        clookFrm.setLocation(initialLocX+size*2+buffer, initialLocY+size+buffer);
		        clookFrm.setVisible (true);
		        clookFrm.setTitle("C-LOOK");
		        clookFrm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
			}
		}
		}
		}
		}
}