package edu.iiitb.os.consolidatedGraph;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

import edu.iiitb.os.clook.CLOOK;
import edu.iiitb.os.cscan.CSCAN;
import edu.iiitb.os.fcfs.FCFS;
import edu.iiitb.os.look.LOOK;
import edu.iiitb.os.scan.SCAN;
import edu.iiitb.os.sstf.SSTF;

class DrawGraph extends JComponent
{
	   private int totalTracks ;
	   private int Xleft ;
	   private int Xright;
	   private int Ytop ;  
	   private int Ybottom ;
	   private int totalX, totalY;
	   private FCFS fcfs;
	   private SSTF sstf;
	   private SCAN scan;
	   private CSCAN cscan;
	   private LOOK look;
	   private CLOOK clook;
	   private String isBest;
	   private int maxDisk;
	   private float divFactor;
	   private String title;
	   private int frameSize;
	   private int xIncrement;
	   private int yIncrement;
	   private String checked;
	   
	   public DrawGraph(int maxDisk,Object obj[], String title, String isBest,int frameSize,String checked) {
		   this.checked=checked;
	    	divFactor=(int)Math.ceil(maxDisk/frameSize)*2;
	    	if(divFactor==0)
	    		divFactor=1;
	       this.frameSize=frameSize;
		   this.maxDisk=maxDisk;
		   this.isBest=isBest;
		   fcfs=(FCFS)obj[0];
		   sstf=(SSTF)obj[1];
		   scan=(SCAN)obj[2];
		   cscan=(CSCAN)obj[3];
		   look=(LOOK)obj[4];
		   clook=(CLOOK)obj[5];
		   
		   totalTracks =cscan.getResult_plot().length; 
	       
	        Xleft = 100;
	        Xright = totalTracks*40+Xleft;
	        Ytop = 5;  
	        Ybottom = (int) (maxDisk/divFactor+40);
	        this.title=title;

	      totalX = Xright - Xleft + 1;
	      totalY = Ybottom - Ytop + 1;
	      //Compute the x and y increments
       xIncrement = totalX/totalTracks;
       yIncrement = (int) (totalY / (maxDisk/divFactor));
    
	}
		  //Establish the paint method
	   public void paintComponent (Graphics g)
	   {
		   
		    drawAxes(g);
		    if(checked.contains("FCFS"))
		    {
	        g.setColor (Color.blue);   //set color of the graph
	        drawLineGraph(g, fcfs.getResult_plot());
		    }
		    if(checked.contains("SSTF"))
		    {
	        g.setColor(Color.MAGENTA);
	        drawLineGraph(g, sstf.getResult_plot());
		    }
		    if(checked.contains(" SCAN"))
		    {
	        g.setColor(Color.ORANGE);
	        drawLineGraph(g, scan.getResult_plot());
		    }
		    if(checked.contains("CSCAN"))
		    {
	        g.setColor(new Color(128, 0, 129));
	        drawLineGraph(g, cscan.getResult_plot());
		    }
		    if(checked.contains("CLOOK"))
		    {
	        g.setColor(Color.GRAY);
	        drawLineGraph(g, clook.getResult_plot());
		    }
		    if(checked.contains(" LOOK"))
		    {
	        g.setColor(Color.RED);
	        drawLineGraph(g, look.getResult_plot());
		    }	        
		       g.setColor (Color.darkGray);
			      //Labels for X axis
			      for (int i=0; i<totalTracks; i++)
			          g.drawString(i+"", Xleft+ i*xIncrement, Ybottom+15);
			      //Labels for Y axis
			      for (int i = 0; i <cscan.getResult_plot().length; i++)
			          	  g.drawString(cscan.getResult_plot()[i]+"", Xleft-30, (int)(Ybottom - yIncrement * cscan.getResult_plot()[i]/divFactor)+7);
			      g.drawString(maxDisk+"", Xleft-30, (int)(Ybottom - yIncrement * maxDisk/divFactor)+7);
	
			g.setColor (Color.red);
	        g.drawString(title, 170, frameSize-40); // title

	        g.setColor(Color.BLACK);
        	g.drawString("Best algorithm for this input is "+isBest, 10, Ybottom+50);

	   }

	    //Draw the line graph
	   public void drawLineGraph(Graphics g,int trackNos[])
	   {
	        int i, x1, y1, x2, y2;
	      //Set the initial origin point
	       x1 = Xleft;
	       y1 = (int) (Ybottom-trackNos[0]/divFactor);
	       g.fillOval(x1, y1, 2, 2);
	      //Compute and plot the data points
	       for(i=1; i < trackNos.length; i++)
	      {
	          x2 =Xleft + xIncrement *i;
	          y2 = (int) (Ybottom - yIncrement * trackNos[i]/divFactor);
	          g.fillOval(x2, y2, 3, 3);
	          g.drawLine(x1, y1, x2, y2);
	          x1 = x2;
	          y1 = y2;
	       }

	   }
	  //Draw the axes for the graph
	   public void drawAxes (Graphics g)
	   {
	       g.setColor (Color.black);
	       g.drawLine(Xleft, Ytop, Xleft, Ybottom);
	       g.drawLine(Xleft, Ybottom, Xright, Ybottom);
	   }

}

public class ConsolidatedGraph extends JFrame implements ActionListener{
	   private String isBest;
	   private int maxDisk;
	   private String title; 
	   private int frameSize;
	   private Object obj[];
	   private JCheckBox[] btns = new JCheckBox[6];
	   private JButton jb;
	   private DrawGraph dg;
	   private int count;
	   private JLabel headMovLab[]=new JLabel[6];
	   
	   public ConsolidatedGraph (int maxDisk,Object obj[], String title, String isBest,int frameSize,int headMov[],int input[])
	   {
	    	 String inputStr="";
	    	 for(int i=0;i<input.length;i++)
	    		 inputStr+="  "+input[i];
	    	 JLabel inputLab=new JLabel("Input Track Nos"+inputStr);
	    	 inputLab.setBounds(5, 0, 1000, 50);
	    	 add(inputLab);
	    	 count=0;
	    	 this.maxDisk=maxDisk;
	    	 this.isBest=isBest;
	    	 this.title=title;
	    	 this.frameSize=frameSize;
	    	 this.obj=obj;
	        	setLayout(null);
		 	    btns[0] = new JCheckBox("FCFS");
			    btns[1] = new JCheckBox("SSTF");
			    btns[2] = new JCheckBox("SCAN");
			    btns[3] = new JCheckBox("CSCAN");
			    btns[4] = new JCheckBox("LOOK");
			    btns[5] = new JCheckBox("CLOOK");
			    JLabel algo=new JLabel("Algorithms");
			    JLabel totalHeadMov=new JLabel("Total head mov");
		        algo.setBounds(20+75*6, 0, 76, 50);
		        totalHeadMov.setBounds(20+75*6+100,0, 150, 50);
		        add(algo);
		        add(totalHeadMov);
			    for (int i=0;i<6;i++) {
			    	headMovLab[i]=new JLabel(""+headMov[i]);
			       add(btns[i]);
			        btns[i].setBounds(20+75*6, 50+i*50, 76, 50);
			        headMovLab[i].setBounds(20+75*6+130, 50+i*50, 76, 50);
			        add(headMovLab[i]);
			    }

			    jb = new JButton("Show Graph");
				jb.setBounds(20+75*6,50+6*50,150,80);
				add(jb);
						
				jb.addActionListener(this);
	   }

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o =e.getSource();

		if(o.equals(jb))
		{
			if(count!=0)
			{
			 remove(dg);
			 repaint();
			}
			String checked="";
		    if(btns[0].isSelected())
		    	checked+="FCFS";
		    if(btns[1].isSelected())
		    	checked+="SSTF";
		    if(btns[2].isSelected())
		    	checked+=" SCAN";
		    if(btns[3].isSelected())
		    	checked+="CSCAN";
		    if(btns[4].isSelected())
		    	checked+=" LOOK";
		    if(btns[5].isSelected())
		    	checked+="CLOOK";
		    count++;
		    dg=new DrawGraph(maxDisk, obj, title, isBest, frameSize,checked);
		    add(dg);
		    dg.setBounds(10, 10, frameSize-100, frameSize-100);
		    dg.setVisible(true);
		    this.getRootPane().revalidate();
		}
		
	}

}
