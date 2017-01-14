package edu.iiitb.os.dataStructures;

public class Queue {
//Queue is FIFO data structure
 private Object arr[];
 private int rear=-1;
 private int front=-1;
 public Queue(int size) {
	 arr=new Object[size];
 }
 //Insert new element at the end of the queue
 public void enqueue(Object obj) {
		 arr[++rear]=obj;
 }
 //Delete from the front of the queue and return the deleted value
 public Object dequeue() {
	 return arr[++front];
 }
 //Check if the queue is empty
 public boolean isEmpty()
 {
	 return rear==front;
 }
}
