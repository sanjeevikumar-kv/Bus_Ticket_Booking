import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Welcome to Bus Booking ");
		
		
		/* Creating Bus object...*/
		ArrayList<Bus> buses=new ArrayList<>();
		buses.add(new Bus(1,true,40));
		buses.add(new Bus(2,false,25));
		buses.add(new Bus(3,true,35));
		System.out.println("-----------------------------------------------------------------------");

		//display bus Info
		for(Bus b:buses)
			b.displayBusInfo();
		
		/* Creating Booking object*/
		ArrayList<Booking> bookings=new ArrayList<>();
		
		
		int userOpt=1;
		while(userOpt!=2) {
			System.out.println("-----------------------------------------------------------------------");
			System.out.print("Options:\n1.Book\n2.Exit\nSelect Option:");
			userOpt=sc.nextInt();
			if(userOpt==1) {
				Booking booking=new Booking();
				if(booking.isAvailable(bookings,buses)) {
					bookings.add(booking);
			System.out.println("-----------------------------------------------------------------------");
					System.out.println("Your Booking is Confirmed.");
						System.out.println("-----------------------------------------------------------------------");

				}
				else {
				System.out.println("-----------------------------------------------------------------------");
					System.out.println("Sorry. Bus is full. Try another Bus or Date.");
				System.out.println("-----------------------------------------------------------------------");
				}

			}
				else if(userOpt==2){
			System.out.println("-----------------------------------------------------------------------");
		    System.out.println("Thank you visit Again");
			System.out.println("-----------------------------------------------------------------------");

				}
			else{
				System.out.println("-----------------------------------------------------------------------");
				System.out.println("Invalid Option");
				System.out.println("-----------------------------------------------------------------------");

			}
				
		}
	}

}

// Booking Class

class Booking {
    private String passengerName;
    private int busNo;
    private String date;

    public Booking() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter passenger Name: ");
        passengerName = sc.next();
        System.out.print("Enter Bus Number: ");
        busNo = sc.nextInt();
        boolean validDate = false;
        while (!validDate) {
            System.out.print("Enter Date (dd-MM-yyyy): ");
            String inputDate = sc.next();  // Use a local variable to get input
            // Validate date format
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            sdf.setLenient(false);
            try {
                sdf.parse(inputDate); // This will throw ParseException if the date is invalid
                date = inputDate;  // Assign to the instance variable only if valid
                validDate = true;  // Exit the loop if the date is valid
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please enter the date in dd-MM-yyyy format.");
            }
        }
    }

    public boolean isAvailable(ArrayList<Booking> bookings, ArrayList<Bus> buses) {
        if (date == null) {
            System.out.println("The date is null for this booking.");
            return false;
        }
        
        int cap = 0;
        for (Bus b : buses) {
            if (b.getBusno() == busNo)
                cap = b.getCapacity();
        }
        
        int booked = 0;
        for (Booking bo : bookings) {
            if (bo.busNo == busNo) {
                if (bo.date == null) {
                    System.out.println("Found a booking with a null date.");
                    continue;
                }
                if (bo.date.equals(this.date))  
                    booked++;
            }
        }

        return booked < cap;
    }
}


// Bus Class

class Bus {
	private int busNo;
	private boolean ac;
	private int capacity;
	
	
	//accessor method
	public int getBusno() {
		return busNo;
	}
	//mutator method
	public void setBusno(int bno) {
		busNo=bno;
	}
	
	//accessor method
	public int getCapacity() {
		return capacity;
	}
	// mutator method
	public void setCapacity(int cap) {
		capacity=cap;
	}
	
	//accessor method
	public boolean getAcStatus() {
		return ac;
	}
	//mutator method
	public void setAcStatus(boolean acs) {
		 ac=acs;
	}
	
	public Bus(int bno,boolean acs,int cap){
		this.busNo=bno;
		this.ac=acs;
		this.capacity=cap;
	}
	
	public void displayBusInfo() {
		System.out.println("Bus No:"+busNo+" Ac:"+ac+" Total Capicity:"+capacity);
	}

}

