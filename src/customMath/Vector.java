package customMath;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import customMath.Point;
import java.lang.Math;

/**
 *
 * @author 1218
 */
public class Vector {
    public float x;
    public float y;
    
    public Vector(float newX, float newY){
        x = newX;
        y = newY;
    }
    
    public Vector()
    {
        x = 1;
        y = 1;
    }
    
    public Vector polarVector(int heading, float power){
        y = (float)Math.sin((double)heading) * power;
        x = (float)Math.cos((double)heading) * power;
        return this;
    }
    
    public Vector(Point p1, Point p2) {
        x = p2.x - p1.x;
        y = p2.y - p1.y;
    }
    
    public void rotate(int rotation)
    {
        if (rotation == 90)
        {
            float tempX = x;
            float tempY = y;
            x = y;
            y = -x;
        }
    }
    public Vector subtract(Vector subtractVector){
        return new Vector(x - subtractVector.x, y- subtractVector.y);
    }
    public Vector add(Vector vectorToAdd) {
        return new Vector(x + vectorToAdd.x, y + vectorToAdd.y); 
    }
    
    public float getMagnitude()
   {
      return (float)Math.sqrt((double)(x*x + y*y));
   }
    public void setMagnitude(float newMagnitude)
    {
        float magnitude = this.getMagnitude();
        if (0 == magnitude){
            System.out.println("error: magnitude of vector is zero");
        }
        float scaleFactor = newMagnitude/magnitude;
        x = x * scaleFactor;
        y = y * scaleFactor;
    }
    
    public int getAngle()
    { 
        return 0;
      // return Math.toDegrees(Math.atan2((double)y, (double)x));
    }
    public void setAngle(int angle)
    {
        float magnitude = this.getMagnitude();
        if (magnitude == 0){
            System.out.println("error: magnitude of vector is zero");
  
         y = (float)Math.sin(Math.toRadians((double)angle)) * magnitude;
        x = (float) Math.cos(Math.toRadians((double)angle)) * magnitude;
        }
            
    }
}
