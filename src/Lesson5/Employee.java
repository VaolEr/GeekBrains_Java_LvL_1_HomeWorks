package Lesson5;

public class Employee {
    private  String fullName;
    private  int age;

    private  String workPosition;

    private  String email;
    private  String phoneNumber;

    private  int salary;

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                                                "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    Employee(String fullName, String workPosition, String email, String phoneNumber, int salary, int age){
        this.fullName = fullName;
        this.workPosition = workPosition;

        if(!email.matches(EMAIL_PATTERN)) throw new IllegalArgumentException("Not valid email" + this.fullName);
        this.email = email;

        if(!phoneNumber.matches("(\\+*)\\d{11}")) throw new IllegalArgumentException("Not Valid Phone Number " + this.fullName);
        else this.phoneNumber = phoneNumber;

        if(salary < 0) throw new IllegalArgumentException("Salary value < 0" + this.fullName);
        else this.salary = salary;

        if(age < 0) throw new IllegalArgumentException("Age value < 0" + this.fullName);
        else this.age = age;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if(age < 0) throw new IllegalArgumentException("Age value < 0" + this.fullName);
        else this.age = age;
    }

    public String getWorkPosition() {
        return workPosition;
    }

    public void setWorkPosition(String workPosition) {
        this.workPosition = workPosition;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(!email.matches(EMAIL_PATTERN)) throw new IllegalArgumentException("Not valid email" + this.fullName);
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if(!phoneNumber.matches("(\\+*)\\d{11}")) throw new IllegalArgumentException("Not Valid Phone Number " + this.fullName);
        else this.phoneNumber = phoneNumber;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        if(salary < 0) throw new IllegalArgumentException("Salary value < 0" + this.fullName);
        else this.salary = salary;
    }

    @Override
    public String toString() {
        String result;
        result = "Employee: " + this.fullName +
                 "\nWork position: " + this.workPosition +
                 "\neMail: " + this.email +
                 "\nPhone number: " + this.phoneNumber +
                 "\nSalary: " + this.salary +
                 "\nAge: " + this.age + "\n";
        return result;
    }

    @Override
    public int hashCode() {
        String result;
        result = "Employee: " + this.fullName +
                "\nWork position: " + this.workPosition +
                "\neMail: " + this.email +
                "\nPhone number: " + this.phoneNumber +
                "\nSalary: " + this.salary +
                "\nAge: " + this.age + "\n";
        return result.hashCode();
    }
}
