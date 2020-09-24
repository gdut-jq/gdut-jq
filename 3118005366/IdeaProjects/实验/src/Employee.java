class Employee {
    private String firstName;
    private String lastName;
    public Employee(String first,String last){
        firstName=first;
        lastName=last;
    }
    public String getEmployeeName(){
        return firstName; }
    public String getLastName(){
        return lastName; }
    public String toStrings(){
        return firstName+' '+lastName; }
    public double earnings(){
        System.out.println("Employ's salary is 0.0");
        return 0.0;}
}
