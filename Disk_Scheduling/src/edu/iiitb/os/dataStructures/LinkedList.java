package edu.iiitb.os.dataStructures;
class Node
{
	private int data;
	private Node next;
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public Node getNext() {
		return next;
	}
	public void setNext(Node next) {
		this.next = next;
	}
	
}

public class LinkedList {
	private Node head=null;
		//Insert at the beginning of the linked list
	public void insert(int val)
	{
		Node x=new Node();
		x.setData(val);
		x.setNext(head);
		head=x;
	
	}
	//Delete the given value from the linked list
	public void delete(int val)
	{
		Node prev=head;
		Node temp=head.getNext();
		if(head.getData()==val)
			head=head.getNext();
		else
		while(temp!=null)
		{
			if(temp.getData()==val)
			{
				prev.setNext(temp.getNext());
				break;
			}
			else
			{
				prev=prev.getNext();
				temp=temp.getNext();
			}
		}
	}
//Check the node having data that has min difference with prev
	public int findMinDiff(int prev)
	{
		int min=1000000;
		int minVal=0;
		Node temp=head;
		while(temp!=null)
		{
			if(Math.abs(temp.getData()-prev)<min)
			{
				min=Math.abs(temp.getData()-prev);
				minVal=temp.getData();
			}
			temp=temp.getNext();
		}
		return minVal;
	}
//Check is linked list is empty
	public boolean isEmpty()
	{
		return head==null;
	}
/*	void display()
	{
		Node temp=head;
		while(temp!=null)
		{
			System.out.print(" "+temp.data+" ");
			temp=temp.next;
		}
	}*/
}
