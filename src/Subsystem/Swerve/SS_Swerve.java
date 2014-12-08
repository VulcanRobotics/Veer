package Subsystem.Swerve;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import MathObject.O_Vector;
import MathObject.O_Point;
import Robot.OI;
import Robot.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.util.Vector;

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
    public SS_Swerve() {
        modules[0] = new O_SwerveModule(new O_Point(1,1), RobotMap.SM0_CIM, RobotMap.SM0_CIMile, RobotMap.SM0_banebot, RobotMap.SM0_EncoderA, RobotMap.SM0_EncoderB, RobotMap.SM0_Zero, 10);
        modules[1] = new O_SwerveModule(new O_Point(-1,1), RobotMap.SM1_CIM, RobotMap.SM1_CIMile, RobotMap.SM1_banebot, RobotMap.SM1_EncoderA, RobotMap.SM1_EncoderB, RobotMap.SM1_Zero, -25);
        modules[2] = new O_SwerveModule(new O_Point(-1,-1), RobotMap.SM2_CIM, RobotMap.SM2_CIMile, RobotMap.SM2_banebot, RobotMap.SM2_EncoderA, RobotMap.SM2_EncoderB, RobotMap.SM2_Zero, 0);
        modules[3] = new O_SwerveModule(new O_Point(1,-1), RobotMap.SM3_CIM, RobotMap.SM3_CIMile, RobotMap.SM3_banebot, RobotMap.SM3_EncoderA, RobotMap.SM3_EncoderB, RobotMap.SM3_Zero, 100);
        System.out.println("Swerve Modules Initialized");          
     
    }
    
    
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new C_Swerve());   
    }
    
    public void swerve(O_Vector translationVector, O_Point center, double turnSpeed) {
        System.out.println("swerving ...");
        
        double maxWheelMagnitude = 0;
        for (int k = 0; k<4; k++){
            O_Vector steeringVector = new O_Vector(center, modules[k].location); //initilizes as a radial vector from turning center to wheel
            steeringVector.rotate90(); // steering vector now faces in direction for rotation
            steeringVector.setMagnitude(turnSpeed);
            
            modules[k].wheelVector = translationVector.add(steeringVector); // add the translation and rotation vectors to get the required wheel vector
            
            //check if this wheel has the highest magnitude
            double wheelMagnitude = modules[k].wheelVector.getMagnitude();
            if (wheelMagnitude > maxWheelMagnitude) {
                maxWheelMagnitude = wheelMagnitude;
            }
            
            if (OI.A.get())
            {
                modules[k].isZeroing = true;
            }
            
        }
        
        
        
        System.out.println("Max Wheel: " + maxWheelMagnitude);
        //scale vectors so no wheel has to drive over 100%
        if (maxWheelMagnitude > 1) { //otherwise Garentess tha there will be a wheel at 100% power - not good when stopped
                 double scaleFactor = 1.0 / maxWheelMagnitude;
             for (int k = 0; k<4; k++) {
                 modules[k].wheelVector.setMagnitude(scaleFactor*modules[k].wheelVector.getMagnitude());
                 
            }
        }
        for (int k = 0; k<4; k++) {
           modules[k].update();
        }
        
        
        SmartDashboard.putNumber("Wheel1Angle", modules[0].wheelVector.getAngle());
        SmartDashboard.putNumber("Wheel2Angle", modules[1].wheelVector.getAngle());
        SmartDashboard.putNumber("Wheel3Angle", modules[2].wheelVector.getAngle());
        SmartDashboard.putNumber("Wheel4Angle", modules[3].wheelVector.getAngle());

    }
    
    //convenience method
    public void swerve(int heading, double power, O_Point center, double turnSpeed) {
        O_Vector translationVector = new O_Vector();
        translationVector = translationVector.polarVector(heading, power);
        swerve(translationVector, center, turnSpeed);
    }
}
