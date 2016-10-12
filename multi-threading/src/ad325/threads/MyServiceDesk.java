package ad325.threads;

/**
* @Author Kellan Nealy
* MyServiceDesk is a class that extends ServiceDeskBase to fulfill the need
* to serve customers at the same time in a virtual environment
*/
public class MyServiceDesk extends ServiceDeskBase {

	public static void main(String[] args) {
        // make the basic simulation
        MyServiceDesk help = new MyServiceDesk();
        // construct the three customers
    	Customer customer_one = new Customer('*');
    	Customer customer_two = new Customer('&');
    	Customer customer_three = new Customer('@');

    	Runnable serviceDeskOne = new Runnable() {
		    public void run() {
		        help.enqueue1(customer_one);
		        help.enqueue1(customer_two);
		        help.enqueue1(customer_three);
		        help.desk1(customer_one);
		        help.desk1(customer_two);
		        help.desk1(customer_three);
		    }
		};

		Runnable serviceDeskTwo = new Runnable() {
		    public void run() {
		        help.enqueue2(customer_two);
		        help.enqueue2(customer_one);
		        help.enqueue2(customer_three);
		        help.desk2(customer_two);
		        help.desk2(customer_one);
		        help.desk2(customer_three);
		    }
		};

		Runnable serviceDeskThree = new Runnable() {
		    public void run() {
		        help.enqueue3(customer_three);
		        help.enqueue3(customer_one);
		        help.enqueue3(customer_two);
		        help.desk3(customer_three);
		        help.desk3(customer_one);
		        help.desk3(customer_two);
		    }
		};
		
		// create and start a thread for each runnable
		new Thread(serviceDeskOne).start();
		new Thread(serviceDeskTwo).start();
		new Thread(serviceDeskThree).start();
	}
	
	public static void serveCustomer(MyServiceDesk help, NewCustomer cust) {
        help.enqueue3(cust);
    	while (!cust.isBeingServed) {
	    	try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	cust.isBeingServed = true;
        help.desk3(cust);
        cust.isBeingServed = false;
	}

	public void desk1(Customer c) {
        process1(c);
	}
	
	public void desk2(Customer c) {
        process2(c);
	}
	
	public void desk3(Customer c) {
        process3(c);
	}
	
	
	public class NewCustomer extends Customer {
		public boolean isBeingServed;
		
		public NewCustomer(char c) {
			super(c);
			isBeingServed = false;
		}
	}
}
