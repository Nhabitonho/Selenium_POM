package zdataProviderEx;

public class Student {
    String name;
    int age;
    String gender;
    public Student(String name, int age, String gender)
    {
        this.name= name;
        this.age=age;
        this.gender=gender;
    }
    // A method to check if student is eligible for registration
    public boolean validateAge()
    {
        if(this.age>2)
        {
            System.out.println("You are eligible to do registration.");
            return true;
        }
        else
            return false;
    }
}
