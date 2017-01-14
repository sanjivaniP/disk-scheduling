package edu.iiitb.os.fcfs;
import edu.iiitb.os.dataStructures.Queue;

public class FCFS {
	private int headMov;
	private int prev;
	private int trackNo[];
	private int [] result_plot;
	private int count;
	public int getHeadMov() {
		return headMov;
	}
	public int[] getResult_plot() {
		return result_plot;
	}

	public FCFS(int headPos, int trackNo[])
	{
		headMov=0;
		prev=headPos;
		this.trackNo=new int[trackNo.length];
		this.trackNo=trackNo;
	//Length of result_plot has additional 1 as it includes initial position too 
		result_plot=new int[trackNo.length+1];
		count=0;
	}
	public void fcfs()
	{
		Queue fcfsq=new Queue(trackNo.length);
		int tNo;
		for(int i=0;i<trackNo.length;i++)
		{
			fcfsq.enqueue(trackNo[i]);
		}
		result_plot[count++]=prev;
		System.out.println("Starting with "+prev);
		while(!fcfsq.isEmpty())
		{
			tNo=(Integer)fcfsq.dequeue();
			headMov+=Math.abs(prev-tNo);
			prev=tNo;
			System.out.println(tNo);
			result_plot[count++]=tNo;
		}
	}
}
