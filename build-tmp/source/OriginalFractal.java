import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class OriginalFractal extends PApplet {

/* this program creates a fractal that is similar to a snowflake pattern */
private int smallestBranch =6;
private int CENTER_X;
private int CENTER_Y;
private double branchAngle = 2*Math.PI/9;
private double startAngle = Math.PI/3;
private double startLen = 300;
public void setup(){
  size(600,600);  
  CENTER_X = width>>1;
  CENTER_Y = height>>1;
  noLoop();
}
public void draw(){
  background(0);
  stroke(0xff,0xff,0xff);
  myFractal(CENTER_X,CENTER_Y,startLen,0,1); 
}
public void myFractal(int x, int y, double len, double angle, int new_start){
  double angle0 = angle;
  int eX0 = (int)(len*Math.cos(angle0)+x);
  int eY0 = (int)(len*Math.sin(angle0)+y);

  if(len>smallestBranch){
    /* if it is a new start, then draw center branches */
    if(new_start!=0){
      new_start=0;
      for(int a=0;a<6;a++){
        myFractal(x,y,len,a*startAngle,0);
      }
    } else {     
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
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "OriginalFractal" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
