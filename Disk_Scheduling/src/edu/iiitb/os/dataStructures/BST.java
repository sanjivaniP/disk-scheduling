package edu.iiitb.os.dataStructures;

public class BST {
	private TreeNode root=new TreeNode();
	
	public TreeNode getRoot() {
		return root;
	}
	public void setRoot(TreeNode root) {
		this.root = root;
	}
	//Insert into the binary search tree
	public void insert(int val)
	{
		TreeNode x=new TreeNode();
		x.setData(val);
		x.setLeft(null);
		x.setRight(null);
			TreeNode prev=root;
			TreeNode temp=root;
			while(temp!=null)
			{
				prev=temp;
				if(val<temp.getData())
					temp=temp.getLeft();
				else
					temp=temp.getRight();
			}
			if(val<prev.getData())
				prev.setLeft(x);
			else
				prev.setRight(x);

	}
	//Inorder traversal of BST 
	public void inorderT(TreeNode root, Stack stk)
	{
		if(root!=null)
		{
			inorderT(root.getLeft(), stk);
			stk.push(root.getData()); //Push the root data on the stack, to get the descending order on Pop
			inorderT(root.getRight(), stk);
		}
	}
}
