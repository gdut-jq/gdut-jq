class Boss extends Employee{
    private double weeklySalary;
    public Boss(String first,String last,double s){
        super(first,last);
        setWeeklySalary(s);}
    public void setWeeklySalary(double s){
        weeklySalary=(s>0?s:0);}
    public double earnings(){
        return weeklySalary; }
    public String toStrings(){
        return "Boss: "+super.toStrings();}
}
