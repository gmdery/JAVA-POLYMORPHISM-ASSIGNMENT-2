
class Firm {
	
	   //-----------------------------------------------------------------
	   //  Creates a staff of employees for a firm and pays them.
	   //-----------------------------------------------------------------
	   public static void main (String[] args)
	   {
	      Staff personnel = new Staff();

	      personnel.payday();
	   }
	}


 class Staff
	{
	   StaffMember[] staffList;

	   //-----------------------------------------------------------------
	   //  Sets up the list of staff members.
	   //-----------------------------------------------------------------
	    Staff ()
	   {
	      staffList = new StaffMember[8];

	      staffList[0] = new Executive ("Swits", "121 Street 5",
	         "0540-000000", "100-25-9343", 3000);

	      staffList[1] = new Employee ("Julie", "309 NewTown",
	         "0541-111031", "356-87-0145", 1246.15);
	      staffList[2] = new Employee ("Clement", "001 Off North-K",
	         "0202-000210", "090-80-7062", 1169.23);

	      staffList[3] = new Hourly ("Sly", "34 Tenth Ave.",
	         "0565-473843", "579-35-2468", 10.55);

	      staffList[4] = new Volunteer ("Evans", "101 Nad Blvd.",
	         "0575-473474");
	      staffList[5] = new Volunteer ("Ransford", "453 Max Lane",
	         "555-7282");
	      staffList[6] = new Commission ("Dery", "25th Avenue The Estate", 
	    		  "0545103373", "1466-6500", 6.25, (6.25*0.2));
	      staffList[7] = new Commission ("Nadine", "20 Street 19", 
	    		  "0550011223", "1968-3526", 9.75, (9.75*0.2));

	      ((Executive)staffList[0]).awardBonus (500.00);

	      ((Hourly)staffList[3]).addHours (40);
	      ((Commission)staffList[6]).addSales (400);
	      ((Commission)staffList[7]).addSales (950);
	      ((Commission)staffList[6]).addHours (35);
	      ((Commission)staffList[7]).addHours (40);
	   }

	   //-----------------------------------------------------------------
	   //  Pays all staff members.
	   //-----------------------------------------------------------------
	   public void payday ()
	   {
	      double amount;

	      for (int count=0; count < staffList.length; count++)
	      {
	         System.out.println (staffList[count]);

	         amount = staffList[count].pay();  // polymorphic

	         if (amount == 0.0)
	            System.out.println ("Thanks!");
	         else
	            System.out.println ("Paid: " + amount);

	         System.out.println ("-----------------------------------");
	      }
	   }
	}


	 abstract class StaffMember
	{
	    String name;
	    String address;
	    String phone;

	   //-----------------------------------------------------------------
	   //  Sets up a staff member using the specified information.
	   //-----------------------------------------------------------------
	   public StaffMember (String eName, String eAddress, String ePhone)
	   {
	      name = eName;
	      address = eAddress;
	      phone = ePhone;
	   }

	   //-----------------------------------------------------------------
	   //  Returns a string including the basic employee information.
	   //-----------------------------------------------------------------
	   public String toString()
	   {
	      String result = "Name: " + name + "\n";

	      result += "Address: " + address + "\n";
	      result += "Phone: " + phone;

	      return result;
	   }

	   //-----------------------------------------------------------------
	   //  Derived classes must define the pay method for each type of
	   //  employee.
	   //-----------------------------------------------------------------
	   public abstract double pay();
	}

	 class Volunteer extends StaffMember
	{
	   //-----------------------------------------------------------------
	   //  Sets up a volunteer using the specified information.
	   //-----------------------------------------------------------------
	   public Volunteer (String eName, String eAddress, String ePhone)
	   {
	      super (eName, eAddress, ePhone);
	   }

	   //-----------------------------------------------------------------
	   //  Returns a zero pay value for this volunteer.
	   //-----------------------------------------------------------------
	   public double pay()
	   {
	      return 0.0;
	   }
	}

	class Employee extends StaffMember
	{
	    String socialSecurityNumber;
	    double payRate;
	   
	   //-----------------------------------------------------------------
	   //  Sets up an employee with the specified information.
	   //-----------------------------------------------------------------
	   public Employee (String eName, String eAddress, String ePhone,
	                    String socSecNumber, double rate)
	   {
	      super (eName, eAddress, ePhone);

	      socialSecurityNumber = socSecNumber;
	      payRate = rate;
	   }

	   //-----------------------------------------------------------------
	   //  Returns information about an employee as a string.
	   //-----------------------------------------------------------------
	   public String toString()
	   {
	      String result = super.toString();

	      result += "\nSocial Security Number: " + socialSecurityNumber;

	      return result;
	   }

	   //-----------------------------------------------------------------
	   //  Returns the pay rate for this employee.
	   //-----------------------------------------------------------------
	   public double pay()
	   {
	      return payRate;
	   }
	}


	 class Executive extends Employee
	{
	    double bonus;

	   //-----------------------------------------------------------------
	   //  Sets up an executive with the specified information.
	   //-----------------------------------------------------------------
	   public Executive (String eName, String eAddress, String ePhone,
	                     String socSecNumber, double rate)
	   {
	      super (eName, eAddress, ePhone, socSecNumber, rate);

	      bonus = 0;  // bonus has yet to be awarded
	   }

	   //-----------------------------------------------------------------
	   //  Awards the specified bonus to this executive.
	   //-----------------------------------------------------------------
	   public void awardBonus (double execBonus)
	   {
	      bonus = execBonus;
	   }

	   //-----------------------------------------------------------------
	   //  Computes and returns the pay for an executive, which is the
	   //  regular employee payment plus a one-time bonus.
	   //-----------------------------------------------------------------
	   public double pay()
	   {
	      double payment =  super.pay() + bonus;

	      bonus = 0;

	      return payment;
	   }
	}

   class Hourly extends Employee
	{
	    int hoursWorked;

	   //-----------------------------------------------------------------
	   //  Sets up this hourly employee using the specified information.
	   //-----------------------------------------------------------------
	   public Hourly (String eName, String eAddress, String ePhone,
	                  String socSecNumber, double rate)
	   {
	      super (eName, eAddress, ePhone, socSecNumber, rate);

	      hoursWorked = 0;
	   }

	   //-----------------------------------------------------------------
	   //  Adds the specified number of hours to this employee's
	   //  accumulated hours.
	   //-----------------------------------------------------------------
	   public void addHours (int moreHours)
	   {
	      hoursWorked += moreHours;
	   }

	   //-----------------------------------------------------------------
	   //  Computes and returns the pay for this hourly employee.
	   //-----------------------------------------------------------------
	   public double pay()
	   {
	      double payment = payRate * hoursWorked;

	      //hoursWorked = 0;

	      return payment;
	   }

	   //-----------------------------------------------------------------
	   //  Returns information about this hourly employee as a string.
	   //-----------------------------------------------------------------
	   public String toString()
	   {
	      String result = super.toString();

	      result += "\nCurrent hours: " + hoursWorked;

	      return result;
	   }
	}
   
   class Commission extends Hourly {
	   double totalSales;
	   double commissionRate;

	public Commission(String eName, String eAddress, String ePhone, String socSecNumber, double rate, double commisionRate) 
	{
		super(eName, eAddress, ePhone, socSecNumber, rate);
		//this.commissionRate = commissionRate;
		commissionRate = 0.2;
	}
	public void addSales (double totalSales) {
		this.totalSales = totalSales;
	}
	public double pay() {
		return super.pay() + commissionRate;
	}
	public String toString() {
		return super.toString() + ", commission rate is " + commissionRate;
	}
	   
   }


