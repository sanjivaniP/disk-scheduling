package edu.iiitb.os.tableview;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import edu.iiitb.os.clook.CLOOK;
import edu.iiitb.os.cscan.CSCAN;
import edu.iiitb.os.fcfs.FCFS;
import edu.iiitb.os.look.LOOK;
import edu.iiitb.os.scan.SCAN;
import edu.iiitb.os.sstf.SSTF;

public class SummaryTable extends JPanel {

	Object[][] rows;
	String[] columnNames={"Input","FCFS NextPos","FCFS headMov","SSTF NextPos","SSTF headMov","SCAN NextPos","SCAN headMov","C-SCAN NextPos","C-SCAN headMov","LOOK NextPos","LOOK headMov","C-LOOK NextPos","C-LOOK headMov"};

	public SummaryTable(int input_arr[],FCFS fcfs, SSTF sstf, SCAN scan, CSCAN cscan, LOOK look, CLOOK clook)
	{
		super(new GridLayout(1,0));
		
		rows=new Object[input_arr.length+4][13];
		int count=0,i=0;
		for(i=0;i<input_arr.length+3;i++)
		{

				count=-1;
				if(i<input_arr.length)
				{	rows[i][++count]=input_arr[i];
				}
				else
					rows[i][++count]='-';	
				
				if(i<fcfs.getResult_plot().length)
				{
					rows[i][++count]=fcfs.getResult_plot()[i];
					if(i>0)
					rows[i][++count]=Math.abs(fcfs.getResult_plot()[i]-fcfs.getResult_plot()[i-1]);
					else
					rows[i][++count]=0;	
				}
				else
				{
					rows[i][++count]='-';	
					rows[i][++count]='-';
				}
				if(i<sstf.getResult_plot().length)
				{
					rows[i][++count]=sstf.getResult_plot()[i];
					if(i>0)
					rows[i][++count]=Math.abs(sstf.getResult_plot()[i]-sstf.getResult_plot()[i-1]);
					else
					rows[i][++count]=0;	
				}
				else
				{
					rows[i][++count]='-';	
					rows[i][++count]='-';
				}
				
				if(i<scan.getResult_plot().length)
				{
					rows[i][++count]=scan.getResult_plot()[i];
					if(i>0)
					rows[i][++count]=Math.abs(scan.getResult_plot()[i]-scan.getResult_plot()[i-1]);
					else
					rows[i][++count]=0;	
				}
				else
				{
					rows[i][++count]='-';	
					rows[i][++count]='-';
				}
				
				if(i<cscan.getResult_plot().length)
				{
					rows[i][++count]=cscan.getResult_plot()[i];
					if(i>0)
					rows[i][++count]=Math.abs(cscan.getResult_plot()[i]-cscan.getResult_plot()[i-1]);
					else
					rows[i][++count]=0;	
				}
				else
				{
					rows[i][++count]='-';	
					rows[i][++count]='-';
				}
				
				if(i<look.getResult_plot().length)
				{
					rows[i][++count]=look.getResult_plot()[i];
					if(i>0)
					rows[i][++count]=Math.abs(look.getResult_plot()[i]-look.getResult_plot()[i-1]);
					else
					rows[i][++count]=0;	
				}
				else
				{
					rows[i][++count]='-';	
					rows[i][++count]='-';
				}
				
				if(i<clook.getResult_plot().length)
				{
					rows[i][++count]=clook.getResult_plot()[i];
					if(i>0)
					rows[i][++count]=Math.abs(clook.getResult_plot()[i]-clook.getResult_plot()[i-1]);
					else
					rows[i][++count]=0;	
				}
				else
				{
					rows[i][++count]='-';	
					rows[i][++count]='-';
				}
			}
		count=-1;
		rows[i][++count]="Total Head Mov";
		rows[i][++count]="FCFS";
		rows[i][++count]=fcfs.getHeadMov();
		rows[i][++count]="SSTF";
		rows[i][++count]=sstf.getHeadMov();
		rows[i][++count]="SCAN";
		rows[i][++count]=scan.getHeadMov();
		rows[i][++count]="C-SCAN";
		rows[i][++count]=cscan.getHeadMov();
		rows[i][++count]="LOOK";
		rows[i][++count]=look.getHeadMov();
		rows[i][++count]="C-LOOk";
		rows[i][++count]=clook.getHeadMov();

		JTable table=new JTable(rows,columnNames);
		table.setFillsViewportHeight(true);
        table.setPreferredScrollableViewportSize(new Dimension(1200, 300));
        table.setFillsViewportHeight(true);
        
        
        JScrollPane scrollPane=new JScrollPane(table);	
        add(scrollPane);
				
	}

}
