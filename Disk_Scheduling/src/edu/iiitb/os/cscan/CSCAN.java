package edu.iiitb.os.cscan;
import edu.iiitb.os.dataStructures.*;

public class CSCAN {
	private int headMov;
	private int prev;
	private Stack stk;
	private BST trackNo;
	private int [] result_plot;
	private int count;
	private char dir;
	public int getHeadMov() {
		return headMov;
	}
	public int[] getResult_plot() {
		return result_plot;
	}
	public CSCAN(int headPos, int trackNo[], int maxPos, char dir)
	{
		int extraLen=1;
		this.trackNo=new BST();
		this.trackNo.getRoot().setData(headPos);
		for(int i=0;i<trackNo.length;i++)
			this.trackNo.insert(trackNo[i]);
		this.dir=dir;
		if(dir=='D')
		this.trackNo.insert(0);
		else
		this.trackNo.insert(maxPos);
		extraLen++;
		result_plot=new int[trackNo.length+extraLen];
		stk=new Stack(trackNo.length+extraLen);

		count=0;
		headMov=0;
		prev=headPos;
	}
	public void cscan()
	{
		System.out.println("Starting at "+trackNo.getRoot().getData());
		result_plot[count++]=trackNo.getRoot().getData();
		if(dir=='D')
		{
			trackNo.inorderT(trackNo.getRoot().getLeft(),stk);
			while(!stk.isEmpty())
			{
				plotAndCompute(stk.pop());
			}
			trackNo.inorderT(trackNo.getRoot().getRight(),stk);
			while(!stk.isEmpty())
			{
				plotAndCompute(stk.pop());
			}
		}
		else
		{
			inorderTRight(trackNo.getRoot().getRight());
			inorderTRight(trackNo.getRoot().getLeft());
		}
	}
	private void plotAndCompute(int tNo)
	{
		headMov+=Math.abs(prev-tNo);
		prev=tNo;
		System.out.println(tNo);
		result_plot[count++]=tNo;
	}
	private void inorderTRight(TreeNode root)
	{
		if(root!=null)
		{
			inorderTRight(root.getLeft());
			plotAndCompute(root.getData());
			inorderTRight(root.getRight());
		}
	}

}
