package zzn.lida;

public class Employee {
    private String name;  //ming
    private int level;   //等级
    private int salary;  //薪水
    //set设置  get获取
    public Employee() { }
    public Employee(String name, int level) {
        this.name = name;
        this.level = level;
    }
    public Employee(String username, int levesssssl, int salasssry) {
        this.name = username;
        this.level = levesssssl;
        this.salary = salasssry; }
    public  void work(){ System.out.println(name+"做好了本职工作！"); }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }
    public int getSalary() { return salary; }
    public void setSalary(int salary) { this.salary = salary; }
    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", salary=" + salary +
                '}';
    }
}
