class PieceWorker extends Employee{
    private double wagePiece;
    private int quantity;
    public PieceWorker(String first,String last,double w,int q){
        super(first,last);
        setWage(w);
        setQuantity(q);}
    public void setWage(double w){
        wagePiece=(w>0?w:0); }
    public void setQuantity(int q){
        quantity=(q>0?q:0);}
    public double earnings(){
        return quantity*wagePiece; }
    public String toStrings(){
        return "PieceWoeker: "+super.toStrings();}
}
