package Subsystem.Swerve;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import MathObject.O_Vector;
import MathObject.O_Point;
import Robot.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
/**
 *
 * @author 1218
 */
public class SS_Swerve extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    O_SwerveModule[] modules = new O_SwerveModule[4];
    /**
     *
     */
    public SS_Swerve()
    {
        modules[0] = new O_SwerveModule(new O_Point(1,1), RobotMap.SM0_CIM, RobotMap.SM0_CIMile, RobotMap.SM0_banebot, RobotMap.SM0_EncoderA, RobotMap.SM0_EncoderB);
        modules[1] = new O_SwerveModule(new O_Point(1,-1), RobotMap.SM1_CIM, RobotMap.SM1_CIMile, RobotMap.SM1_banebot, RobotMap.SM1_EncoderA, RobotMap.SM1_EncoderB);
        modules[2] = new O_SwerveModule(new O_Point(-1,-1), RobotMap.SM2_CIM, RobotMap.SM2_CIMile, RobotMap.SM2_banebot, RobotMap.SM2_EncoderA, RobotMap.SM2_EncoderB);
        modules[3] = new O_SwerveModule(new O_Point(-1,1), RobotMap.SM3_CIM, RobotMap.SM3_CIMile, RobotMap.SM3_banebot, RobotMap.SM3_EncoderA, RobotMap.SM3_EncoderB);
        System.out.println("Swerve Modules Initialized");                
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
            
            modules[k].wheelVector = translationVector.add(steeringVector); // add the translation and rotation vectors to get the required wheel vector
            
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
            System.out.prinln("Module number: ");
            System.out.print(k);
            System.out.prinln("Wheel Angle: ");
            System.out.print(modules[k].wheelVector.getAngle());
            System.out.prinln("Wheel Power: ");
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
