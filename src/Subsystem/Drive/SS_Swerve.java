package Subsystem.Drive;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import MathObject.O_Vector;
import MathObject.O_Point;
import edu.wpi.first.wpilibj.command.Subsystem;
/**
 *
 * @author 1218
 */
public class SS_Swerve extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    SS_SwerveModule[] modules;
    /**
     *
     */
    public void SS_Swerve()
    {
         for ( int k = 0; k < 4; k++) {
           modules[k] = new SS_SwerveModule();
        }
        modules[0].location = new O_Point(1,1) ;
        modules[1].location = new O_Point(1,-1);
        modules[2].location = new O_Point(-1,-1);
        modules[3].location = new O_Point(-1,1);
         System.out.println("done init swerve");
                
    }
    
    
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new C_Swerve());
       
}
    public void swerve(O_Vector translationVector, O_Point center, float turnSpeed) {
        System.out.println("swerving ...");
        float maxWheelMagnitude = 0;
   
        for (int k = 0; k<4; k++){
            
            O_Vector steeringVector = new O_Vector(center, modules[k].location); //initilizes as a radial vector from turning center to wheel
            steeringVector.rotate(90); // steering vector now faces in direction for rotation
            steeringVector.setMagnitude(turnSpeed);
            
            modules[k].wheelVector = translationVector.add(steeringVector); // ad the translation and rotation vectors to get the required wheel vector
            
            //check if this wheel has the highest magnitude
            float wheelMagnitude = modules[k].wheelVector.getMagnitude();
            if ( wheelMagnitude > maxWheelMagnitude)
            {
                maxWheelMagnitude = wheelMagnitude;
            }
        }
        //scale vectors so no wheel has to drive over 100%
        float scaleFactor = (float)1.0/maxWheelMagnitude;
        for (int k = 0; k<4; k++) {
            modules[k].wheelVector.setMagnitude(scaleFactor*modules[k].wheelVector.getMagnitude());
        }
        // log wheel position
        for (int k = 0; k<4; k++) {
            System.out.print(k);
            System.out.print(modules[k].wheelVector.getAngle());
            System.out.print(modules[k].wheelVector.getMagnitude());
        }
    }
    
    //convenience method
    public void swerve(int heading, float power, O_Point center, float turnSpeed) {
        O_Vector translationVector = new O_Vector();
        translationVector = translationVector.polarVector(heading, power);
        
        swerve(translationVector, center, turnSpeed);
    }
}
