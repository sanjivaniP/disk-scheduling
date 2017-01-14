package edu.iiitb.os.sstf;
import edu.iiitb.os.dataStructures.LinkedList;

public class SSTF {
	private LinkedList trackNo;
	private int headMov;
	private int prev;
	private int [] result_plot;
	private int count;
	public int getHeadMov() {
		return headMov;
	}

	public int[] getResult_plot() {
		return result_plot;
	}

	public SSTF(int headPos, int trackNo[])
	{
		this.trackNo=new LinkedList();
		for(int i=0;i<trackNo.length;i++)
			this.trackNo.insert(trackNo[i]);
		headMov=0;
		prev=headPos;

		//Length of result_plot has additional 1 as it includes initial position too 
		result_plot=new int[trackNo.length+1];
		count=0;
	}
	public void sstf()
	{
		int tNo;
		result_plot[count++]=prev;
		System.out.println("Starting at "+prev);
		while(!trackNo.isEmpty())
		{
		//	System.out.println("Go "+i++);
			tNo=trackNo.findMinDiff(prev);
			System.out.println(tNo);
			result_plot[count++]=tNo;
			headMov+=Math.abs(prev-tNo);
			prev=tNo;
			trackNo.delete(tNo);
		}
	}
}
