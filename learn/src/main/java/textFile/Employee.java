package textFile;

public class Employee {
    public Employee(String name, double salary, int year, int month, int day) {
        this.name = name;
        this.salary = salary;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    private String name;

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public String getHireDay() {
        String hireDate = this.year + "-" + this.month + "-" + this.day;
        return hireDate;
    }

    private double salary;
    private int year;
    private int month;
    private int day;


}
