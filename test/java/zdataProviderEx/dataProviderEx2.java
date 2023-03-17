package zdataProviderEx;

import java.util.ArrayList;
import java.util.Iterator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class dataProviderEx2 {
    // A data provider method with return type as 2D array
    @DataProvider(name = "DataContainer")
    public Iterator<Student> studentDetails(){
        ArrayList<Student> s=new ArrayList<Student>();
        s.add(new Student("Kiran", 28, "Male"));
        s.add(new Student("Amod", 28, "Male"));
        s.add(new Student("Anu", 28, "Female"));
        return s.iterator();
    }
    // You must need to mention data provider method name in Test method
    @Test(dataProvider = "DataContainer")
    public void methodWithSingleAttribute(Student a)
    {
        if(a.validateAge())
        {
            System.out.println("Student registered with details as Name = "+a.name + " , Age ="+a.age +" , Gender ="+a.gender);
        }
        else
        {
            System.out.println("Student not registered with details as Name = "+a.name + " , Age ="+a.age +" , Gender ="+a.gender);
        }
    }
}
