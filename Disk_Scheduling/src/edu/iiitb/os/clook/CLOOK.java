package edu.iiitb.os.clook;

import edu.iiitb.os.dataStructures.BST;
import edu.iiitb.os.dataStructures.Stack;
import edu.iiitb.os.dataStructures.TreeNode;

public class CLOOK {
	private int headMov;
	private int prev;
	private Stack stk;
	private BST trackNo;
	private int [] result_plot;
	private char dir;
	
	public int getHeadMov() {
		return headMov;
	}
	public int[] getResult_plot() {
		return result_plot;
	}

	int count;
	public CLOOK(int headPos, int trackNo[], char dir)
	{
		this.dir=dir;
		stk=new Stack(trackNo.length);
		this.trackNo=new BST();
		this.trackNo.getRoot().setData(headPos);
		for(int i=0;i<trackNo.length;i++)
			this.trackNo.insert(trackNo[i]);
		result_plot=new int[trackNo.length+1];
		headMov=0;
		prev=headPos;
		count=0;
	}
	public void clook()
	{
		result_plot[count++]=trackNo.getRoot().getData();
		System.out.println("Starting at "+trackNo.getRoot().getData());
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
