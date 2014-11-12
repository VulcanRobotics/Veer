package MathObject;

import com.sun.squawk.util.MathUtils;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 1218
 */
public class O_Vector {
    public float x;
    public float y;
    
    public O_Vector(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    public O_Vector() {
        x = 1;
        y = 1;
    }
    
    public O_Vector polarVector(int heading, float power) {
        y = (float)Math.sin((double)heading) * power;
        x = (float)Math.cos((double)heading) * power;
        return this;
    }
    
    public O_Vector(O_Point p1, O_Point p2) {
        x = p2.x - p1.x;
        y = p2.y - p1.y;
        System.out.println(x);
        System.out.println(y);
    }
    
    public void rotate90() {
        // not working now - will be special case for 90
        //trig is slow, less of it in calculating wheel positions the better
        
           //setAngle(getAngle()+90);

        float tempX = x;
        x = y;
        y = -tempX;
    }
    public O_Vector subtract(O_Vector subtractVector) {
        return new O_Vector(x - subtractVector.x, y- subtractVector.y);
    }
    public O_Vector add(O_Vector vectorToAdd) {
        return new O_Vector(x + vectorToAdd.x, y + vectorToAdd.y); 
    }
    
    public float getMagnitude() {
      return (float)Math.sqrt((double)(x*x + y*y));
    }
    public void setMagnitude(float newMagnitude) {
        //System.out.println("newMagnitude is: " + newMagnitude);
        float magnitude = this.getMagnitude();
        if (magnitude == 0) {
            System.out.println("error: magnitude of vector is zero");
        }
        float scaleFactor = newMagnitude/magnitude;
        x = x * scaleFactor;   
        y = y * scaleFactor;
    }
    
    public int getAngle() { 
       return (int)Math.toDegrees(MathUtils.atan2((double)y, (double)x));
    }
    public void setAngle(int angle) {
        float magnitude = this.getMagnitude();
        if (magnitude == 0) {
            System.out.println("error: magnitude of vector is zero");
  
         y = (float)Math.sin(Math.toRadians((double)angle)) * magnitude;
         
         
        x = (float) Math.cos(Math.toRadians((double)angle)) * magnitude;
        
        }      
    }
    
    public String description() {
        return "x: " + String.valueOf(x) + "y: " + String.valueOf(y);
    }
}
