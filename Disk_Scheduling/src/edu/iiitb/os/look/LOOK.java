package edu.iiitb.os.look;

import edu.iiitb.os.dataStructures.BST;
import edu.iiitb.os.dataStructures.Stack;
import edu.iiitb.os.dataStructures.TreeNode;

public class LOOK {
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

	public LOOK(int headPos, int trackNo[],char dir)
	{
		this.dir=dir;
		stk=new Stack(trackNo.length);
		this.trackNo=new BST();
		this.trackNo.getRoot().setData(headPos);
		for(int i=0;i<trackNo.length;i++)
			this.trackNo.insert(trackNo[i]);
		headMov=0;
		prev=headPos;

		//Length of result_plot has additional 1 as it includes initial position too 
		result_plot=new int[trackNo.length+1];
		count=0;
	}
	public void look()
	{
		System.out.println("Starting at "+trackNo.getRoot().getData());
		result_plot[count++]=trackNo.getRoot().getData();
		if(dir=='D')
		{
			trackNo.inorderT(trackNo.getRoot().getLeft(), stk);
			while(!stk.isEmpty())
			{
				plotAndCompute(stk.pop());
			}
			inorderTRight(trackNo.getRoot().getRight());
		}
		else
		{
			inorderTRight(trackNo.getRoot().getRight());
			trackNo.inorderT(trackNo.getRoot().getLeft(), stk);
			while(!stk.isEmpty())
			{
				plotAndCompute(stk.pop());
			}
		}
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

	private void plotAndCompute(int tNo)
	{
		headMov+=Math.abs(prev-tNo);
		prev=tNo;
		System.out.println(tNo);
		result_plot[count++]=tNo;
	}
}
