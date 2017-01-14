package edu.iiitb.os.scan;
import edu.iiitb.os.dataStructures.*;
public class SCAN {
	private int headMov;
	private int prev;
	private Stack stk;
	private BST trackNo;
	private int [] result_plot;
	private int count;
	private char dir;
	//To give the final value of headMov to the calling function
	public int getHeadMov() {
		return headMov;
	}
	//To give the final value of result_plot array to the calling function
	public int[] getResult_plot() {
		return result_plot;
	}

	public SCAN(int headPos, int trackNo[], char dir,int maxPos)
	{
		int extraLen=1;
		this.dir=dir;
		
		this.trackNo=new BST();
		
		//set Root's data to the initial head position
		this.trackNo.getRoot().setData(headPos);
		
		//Inserting in the given track nos. in the BST
		for(int i=0;i<trackNo.length;i++)
			this.trackNo.insert(trackNo[i]);
		
		//Insert 0 as the head traverses till 0 as one end in SCAN algorithm
/*		if(this.trackNo.getRoot().getLeft()!=null && this.trackNo.getRoot().getRight()!=null)
		{*/
		if(dir=='D')
		this.trackNo.insert(0);
		else
		this.trackNo.insert(maxPos);

		extraLen++;
//		}
		headMov=0;
		stk=new Stack(trackNo.length+extraLen);
		prev=headPos;
		
		//Length of result_plot has additional 2 as it includes initial position and 0 too 
		result_plot=new int[trackNo.length+extraLen];
		count=0;
	}
	public void scan()
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
			inorderTRight(trackNo.getRoot().getRight());
		}
		else 
		{
			inorderTRight(trackNo.getRoot().getRight());
			trackNo.inorderT(trackNo.getRoot().getLeft(),stk);
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
