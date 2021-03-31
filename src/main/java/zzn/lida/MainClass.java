package zzn.lida;

public class MainClass {

    public static void main(String[] args) {
        //三个小工
        Employee e1=new Employee("lida",5,5000);
        Employee e2=new Employee("caicai",7,5000);
        Employee e3=new Employee("zz",10,5000);
        //发工资的
        Hrstaff h1=new Hrstaff();
        h1.paySalary(e1);
        h1.paySalary(e2);
        h1.paySalary(e3);


        String str="0000000000000";
        if (str.contains("1")){

            System.out.println("ssssssssssssss");
        }
        else
        {
            System.out.println("1111111111111111111");
        }


    }
}
