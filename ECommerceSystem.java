import java.util.Scanner;

public class ECommerceSystem
{
    public static void main(String[] args) 
    { 
        String userID;
        String userName;
        String userPassword;
        int userChoice,userStatus,userType;
        Admin admin=new Admin();
        Scanner sc=new Scanner(System.in);
        
        System.out.println("Enter type of User:\n1.Admin\n2.Customer");
        userType=sc.nextInt();
        if(userType==1)
        {

        }
        else if(userType==2)
        {
            do
            {
                System.out.println("Choose one of the below Operation:\n1.Create Account\n2.Sign in\n3.Exit");
                userStatus=sc.nextInt();
                switch(userStatus)
                {
                    case 1:
                            System.out.println("Enter Username: ");
                            userName=sc.next();
                            System.out.println("Enter User Password: ");
                            userPassword=sc.next();
                            Customer customer=new Customer();
                            customer.createCustomer(userName, userPassword);
                            admin.customerList.put(customer.getUserID(),customer);
                            System.out.println(admin.customerList);
                            break;
                    case 2:
                            break;
                    case 3:
                            break;
                    default: System.out.print("Wrong Choice!!!");
                }
            }while(userStatus!=3);
        }
        do
        {
            System.out.println("Enter your Choice:\n1.View all categories\n2.View category by ID\n3.");
            userChoice=sc.nextInt();
            switch(userChoice)
            {
                case 1: 
                    
            }
        }while(userChoice!=5);
    }
}