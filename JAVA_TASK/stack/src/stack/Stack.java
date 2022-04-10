package stack;

import java.util.Scanner;

public class Stack {
	static Stack stk = new Stack();
	static final int MAX = 1000;
    int top;
    int a[] = new int[MAX];
 
    boolean isEmpty() {
        return (top < 0);
    }
    Stack() {
        top = -1;
    }
 
    boolean push(int x) {
        if (top >= (MAX - 1)) {
            System.out.println("Stack Overflow");
            return false;
        } else {
            a[++top] = x;
            System.out.println(x + " pushed into stack");
            return true;
        }
    }
 
    public int pop() {
        if (top < 0) {
            System.out.println("Stack Underflow");
            return 0;
        }
        else {
            int x = a[top--];
            return x;
        }
    }
 
    public int peek() {
        if (top < 0) {
            System.out.println("Stack Underflow");
            return 0;
        }
        else {
            int x = a[top];
            return x;
        }
    }
    
    public void print() {
    for(int i = top;i>-1;i--){
      System.out.print(" "+ a[i]);
    }
    
  }
    public static void createStack() {
    	System.out.println("\nEnter the size of Stack");
		  Scanner scn= new Scanner(System.in);
		  int sizeOfStack =  scn.nextInt();
		  for(int i = 0; i < sizeOfStack; i++) {
			  System.out.println("\nEnter the Value : ");
			  int stackValue =  scn.nextInt();
			  stk.push(stackValue);
		  }
		  System.out.print("\nCREATED SUCCESSFULLY");
		  showOption();
    }
    public static void showOption() {
		  System.out.println("\n1.PUSH\n2.POP\n3.SHOW TOP ELEMENT\n4.SHOW STACK ELEMENTS");
			Scanner sc= new Scanner(System.in);
			System.out.println("\nEnter your choice: ");
			int choice= sc.nextInt();
			switch(choice) {
			case 1:
				System.out.println("\nEnter Number: ");
				int pushElement =  sc.nextInt();
				stk.push(pushElement);
				showOption();
				break;
			case 2:
				System.out.println(stk.pop() + " Popped from stack");
				 showOption();
				break;
			case 3:
				System.out.println("Top element is :" + stk.peek());
			    showOption();
				break;
			case 4:
			      System.out.print("Elements present in stack :");
			      stk.print();
				  showOption();
				break;
			}
    }
 public static void main(String args[])
  {
	 System.out.println("STACK");
	 createStack();
  }

}
