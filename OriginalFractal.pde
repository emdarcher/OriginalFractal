/* this program creates a fractal that is similar to a snowflake pattern */
private int smallestBranch =6;
private int CENTER_X;
private int CENTER_Y;
private double branchAngle = 2*Math.PI/9;
private double startAngle = Math.PI/3;
private double startLen = 300;
public void setup(){
  size(600,600);  
  CENTER_X = width>>1; //divide width by two for center x
  CENTER_Y = height>>1;//divide height by two for center y
  noLoop();
}
public void draw(){
  background(0);
  stroke(0xff,0xff,0xff); //set to white lines
  myFractal(CENTER_X,CENTER_Y,startLen,0,1);//make the center fractal/snowflake
}
public void myFractal(int x, int y, double len, double angle, int new_start){
  double angle0 = angle;
  int eX0 = (int)(len*Math.cos(angle0)+x);
  int eY0 = (int)(len*Math.sin(angle0)+y);
  if(len>smallestBranch){
    if(new_start!=0){
    /* if it is a new start, then draw the center 6 snowflake branches */
      new_start=0;//set to 0, in case it's used elsewhere in the future.
      for(int a=0;a<6;a++){
        myFractal(x,y,len,a*startAngle,0);
      }
    } else {     
    /* else, draw the center line & new smaller fractal branches off of it */
      line(x,y,eX0,eY0);
      for(double i=(len/6);i<=len;i+=(len/6)){
        int sX = (int)(i*Math.cos(angle)+x);
        int sY = (int)(i*Math.sin(angle)+y);
        myFractal(sX,sY,(len-i)/2,angle+branchAngle,0);
        myFractal(sX,sY,(len-i)/2,angle-branchAngle,0);
      }
    } 
  }
}
