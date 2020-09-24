class HourlyWorker extends Employee{
    private double wage;
    private double hours;
    public HourlyWorker(String first,String last ,double w,double h) {
        super(first,last);
        setWage(w);
        setHours(h);}
    public void setWage (double w){
        wage=(w>0?w:0); }
    public void setHours(double h){
        hours=(h>=0&&h<168?h:0); }
    public double earnings(){
        return wage*hours;}
    public String toStrings(){
        return "HourlyWorker: "+super.toStrings(); }
}

