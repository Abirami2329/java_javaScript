package queue;

import java.util.Scanner;

public class Queue {
	private static int front, rear, capacity; 
    private static int queue[];
	static int queueSize = 0;
	static Queue q = new Queue(100);
   
    Queue(int size) { 
        front = rear = 0; 
        capacity = size; 
        queue = new int[capacity]; 
    } 
    void queueEnqueue(int item)  { 
        if (capacity == rear) {
            System.out.printf("\nQueue is full\n"); 
            return; 
        } 
        else { 
            queue[rear] = item; 
            rear++; 
        } 
        return; 
    } 
    void queueDequeue()  { 
        if (front == rear) { 
            System.out.printf("\nQueue is empty\n"); 
            return; 
        } 
        else { 
            for (int i = 0; i < rear - 1; i++) { 
                queue[i] = queue[i + 1]; 
            } 
            if (rear < capacity) 
                queue[rear] = 0; 
            rear--; 
        } 
        return; 
    } 
    void queueDisplay() 
    { 
        int i; 
        if (front == rear) { 
            System.out.printf("Queue is Empty\n"); 
            return; 
        } 
        for (i = front; i < rear; i++) { 
            System.out.printf(" %d <= ", queue[i]); 
        } 
        return; 
    } 
   
    void queueFront() 
    { 
        if (front == rear) { 
            System.out.printf("Queue is Empty\n"); 
            return; 
        } 
        System.out.printf("\nFront Element of the queue: %d", queue[front]); 
        return; 
    } 
    public static void showOption() {
		  System.out.println("\n1.ENQUEUE\n2.DEQUEUE\n3.FRONT ELEMENT OF QUEUE\n4.SHOW QUEUE LIST");
			Scanner sc= new Scanner(System.in);
			System.out.println("\nEnter your choice: ");
			int choice= sc.nextInt();
			switch(choice) {
			case 1:
				System.out.println("\nEnter Number: ");
				int enqueueElement =  sc.nextInt();
				q.queueEnqueue(enqueueElement);
				showOption();
				break;
			case 2:
				q.queueDequeue();
				showOption();
				break;
			case 3:
				q.queueFront();
			    showOption();
				break;
			case 4:
			      System.out.print("Elements present in Queue :");
			        q.queueDisplay();
				  showOption();
				break;
			}
    }
    public static void createQueue() {
    	System.out.println("\nEnter the size of queue");
		  Scanner scn= new Scanner(System.in);
		  queueSize = scn.nextInt();
		  for(int i = 0; i < queueSize; i++) {
			  System.out.println("\nEnter the Value : ");
			  int queueValue =  scn.nextInt();
			  q.queueEnqueue(queueValue); 
			  
		  }
		  System.out.print("\nCREATED SUCCESSFULLY");
		  showOption();
    }
	
	public static void main(String[] args) { 
		System.out.println("QUEUE");
		 createQueue();
    } 

}
