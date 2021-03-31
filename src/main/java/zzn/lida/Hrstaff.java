package zzn.lida;

import org.junit.jupiter.api.Test;

public class Hrstaff extends Employee {

    public Hrstaff() { }
    public Hrstaff(String name) { }
    public Hrstaff(String username, int levesssssl, int salasssry) { super(username, levesssssl, salasssry); }
    public Employee paySalary(Employee employee){
        int level=employee.getLevel();
        if (level<=5&&level>=1){ employee.setSalary(employee.getSalary()+500); }
        else if (level<=9&&level>=6){ employee.setSalary(employee.getSalary()+800); }
        else if (level<=15&&level>=10){ employee.setSalary(employee.getSalary()+1000); }
        else { System.out.println("“不存在对应的职级，无法发放额外浮动薪水”");   return employee; }
        System.out.println("这个人工资是"+employee.getSalary());
        return employee; }
}
