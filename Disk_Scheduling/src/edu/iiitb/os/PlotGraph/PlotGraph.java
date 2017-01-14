package edu.iiitb.os.PlotGraph;


import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class PlotGraph extends JFrame{
   private int totalTracks ;
   private int Xleft ;
   private int Xright;
   private int Ytop ;  
   private int Ybottom ;
   private boolean isBest;
   private int totalX, totalY;
   private int[ ] trackNos;
   private int headMov;
   private int maxDisk;
   private int divFactor;
   
     public PlotGraph (int maxDisk, int arr[], String title, int headMov, boolean isBest,int frameSize)
   {
    	 //divFactor so that it fits in the given size frame
    	divFactor=(int)Math.ceil(maxDisk/frameSize)*2;
    	if(divFactor==0)
    		divFactor=1;

	   this.maxDisk=maxDisk;
	   totalTracks = arr.length;
       trackNos = new int[arr.length];
       for (int i = 0; i < trackNos.length;  i++)
                 trackNos[ i ] = arr[i];
       
       this.headMov=headMov;
        Xleft = 100;
        Xright = arr.length*20+Xleft;
        Ytop = 5;  
        Ybottom = maxDisk/divFactor+40;
   //     this.title=title;

      totalX = Xright - Xleft + 1;
      totalY = Ybottom - Ytop + 1;
      this.isBest=isBest;
      
   }

  //Establish the paint method
   public void paint (Graphics g)
   {
        g.setColor (Color.blue);   //set color of the graph
        drawLineGraph(g);
       
        g.setColor (Color.red);
        //g.drawString(title, 170, frameSize-40); // title
        if(isBest)
        {
        	g.setColor(Color.magenta);
        	g.drawString("Best algorithm for this input with headMov as: "+headMov, 10, Ybottom+30);
        }
        else
        {
        g.setColor (Color.black);
        g.drawString("The total head movement is: "+headMov, 10, Ybottom+30);
        }
   }


    //Draw the line graph
   public void drawLineGraph(Graphics g)
   {
        drawAxes(g);
        int i, x1, y1, x2, y2,  xIncrement, yIncrement;
      //Compute the x and y increments
       xIncrement = totalX/totalTracks;
       yIncrement = totalY / (maxDisk/divFactor);

      //Set the initial origin point
       x1 = Xleft;
       y1 = Ybottom-trackNos[0]/divFactor;
       g.setColor (Color.black);
       g.fillOval(x1, y1, 2, 2);

      //Compute and plot the data points
       for(i=1; i < totalTracks; i++)
      {
          x2 =Xleft + xIncrement *i;
          y2 = Ybottom - yIncrement * trackNos[i]/divFactor;
          g.setColor (Color.black);
          g.fillOval(x2, y2, 2, 2);
          g.setColor (Color.blue);
          g.drawLine(x1, y1, x2, y2);
          x1 = x2;
          y1 = y2;
       }
       
       g.setColor (Color.darkGray);
      //Labels for X axis
      for (i=0; i<totalTracks; i++)
          g.drawString(i+"", Xleft+ i*xIncrement, Ybottom+15);

      //Labels for Y axis
      for (i = 0; i <totalTracks; i++)
          	  g.drawString(trackNos[i]+"", Xleft-30, Ybottom - yIncrement * trackNos[i]/divFactor+7);
      g.drawString(maxDisk+"", Xleft-30, Ybottom - yIncrement * maxDisk/divFactor+7);
   }

  //Draw the axes for the graph
   public void drawAxes (Graphics g)
   {
       g.setColor (Color.black);
       g.drawLine(Xleft, Ytop, Xleft, Ybottom);
       g.drawLine(Xleft, Ybottom, Xright, Ybottom);
   }

}