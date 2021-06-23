import java.util.*;
public class User 
{ 
    String userID;
    String userName;
    String userPassword;
    long userMobileNumber;

    public String getUserID() 
    {
        return userID;
    }
    public String getUserName() 
    {
        return userName;
    }
    public String getUserPassword() 
    {
        return userPassword;
    }
    public long getUserMobileNumber() 
    {
        return userMobileNumber;
    }
    public void setUserID()
    {
        UUID uuid=UUID.randomUUID(); 
        this.userID = uuid.toString();
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }
    public void setUserPassword(String userPassword) 
    {
        this.userPassword = userPassword;
    }
    public void setUserMobileNumber(long userMobileNumber) 
    {
        this.userMobileNumber = userMobileNumber;
    }


}
