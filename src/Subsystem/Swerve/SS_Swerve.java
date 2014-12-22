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
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 * @author 1218
 */
public class SS_Swerve extends Subsystem {
    
    O_SwerveModule[] modules = new O_SwerveModule[4];

    Gyro gyro = new Gyro(RobotMap.Gyro); 
    public SS_Swerve() {
        modules[0] = new O_SwerveModule(new O_Point(1,1), RobotMap.SM0_CIM, RobotMap.SM0_CIMile, RobotMap.SM0_banebot, RobotMap.SM0_EncoderA, RobotMap.SM0_EncoderB, RobotMap.SM0_Zero, 35, false);
        modules[1] = new O_SwerveModule(new O_Point(-1,1), RobotMap.SM1_CIM, RobotMap.SM1_CIMile, RobotMap.SM1_banebot, RobotMap.SM1_EncoderA, RobotMap.SM1_EncoderB, RobotMap.SM1_Zero, -35, false);
        modules[2] = new O_SwerveModule(new O_Point(-1,-1), RobotMap.SM2_CIM, RobotMap.SM2_CIMile, RobotMap.SM2_banebot, RobotMap.SM2_EncoderA, RobotMap.SM2_EncoderB, RobotMap.SM2_Zero, -170, true);
        modules[3] = new O_SwerveModule(new O_Point(1,-1), RobotMap.SM3_CIM, RobotMap.SM3_CIMile, RobotMap.SM3_banebot, RobotMap.SM3_EncoderA, RobotMap.SM3_EncoderB, RobotMap.SM3_Zero, 100, true);
        System.out.println("Swerve Modules Initialized");
    }
    
    
    
    public void initDefaultCommand() {
        setDefaultCommand(new C_Swerve());   
    }
    
    public void swerve(O_Vector translationVector, O_Point center, double turnSpeed) {        
        double maxWheelMagnitude = 0;
        
        for(int k = 0; k<4; k++) {
            O_Vector steeringVector = new O_Vector(center, modules[k].location); //initilizes as a radial vector from turning center to wheel
            steeringVector.rotate90(); //steering vector now faces in direction for rotation
            steeringVector.setMagnitude(turnSpeed);
            
            modules[k].wheelVector = translationVector.add(steeringVector); //sum is required wheel vector
            
            //check if this wheel has the highest magnitude
            if(modules[k].wheelVector.getMagnitude() > maxWheelMagnitude) {
                maxWheelMagnitude = modules[k].wheelVector.getMagnitude();
            }
            if (OI.Button_A.get())
            {
                modules[k].isZeroing = true;
                gyro.setSensitivity(.00738888);
                gyro.reset();
            }
        }
        
        //scale vectors so no wheel has to drive over 100%
        if(maxWheelMagnitude > 1.0) { //otherwise Garentess tha there will be a wheel at 100% power - not good when stopped
            double scaleFactor = 1.0 / maxWheelMagnitude;
            for(int k = 0; k<4; k++) {
                modules[k].wheelVector.setMagnitude(scaleFactor * modules[k].wheelVector.getMagnitude());    
            }
        }
        for(int k = 0; k<4; k++) {
            modules[k].update();
        }
        
        
    }
    
    //convenience method
    public void swerve(int heading, double power, O_Point center, double turnSpeed) {
        int robotCentricHeading = heading - (int)(gyro.getAngle() % 360);
        if (robotCentricHeading > 180){
            robotCentricHeading = robotCentricHeading -360; 
        }
        if (robotCentricHeading < -180){
            robotCentricHeading = robotCentricHeading +360; 
        }
        System.out.println("heading: " + heading);
        System.out.println("gyro: " + (gyro.getAngle() % 360));
        System.out.println("robo centric: " + robotCentricHeading);
        O_Vector translationVector = new O_Vector();
        translationVector = translationVector.polarVector(robotCentricHeading, power);
        swerve(translationVector, center, turnSpeed);
    }
    
    /**
     * Publishes all Swerve System Values to the dashboard.
     */
    public void syncDashboard() {
        SmartDashboard.putNumber("Wheel1Angle", modules[0].wheelVector.getAngle());
        SmartDashboard.putNumber("Wheel2Angle", modules[1].wheelVector.getAngle());
        SmartDashboard.putNumber("Wheel3Angle", modules[2].wheelVector.getAngle());
        SmartDashboard.putNumber("Wheel4Angle", modules[3].wheelVector.getAngle());
        SmartDashboard.putNumber("WheelAngle", modules[3].turnEncoder.encoder.getRaw());
        SmartDashboard.putNumber("PIDTarget", modules[3].turn.getSetpoint());
        SmartDashboard.putNumber("Power" + modules[3].turnMotor.getChannel(), modules[3].wheelVector.getMagnitude());
        //System.out.println("Zero Speed: " +  modules[3].zeroSpeedOutput);
        //System.out.println("Error: " + ( modules[3].desiredZeroSpeed - modules[3].turnEncoder.encoder.getRate()) * (modules[3].turnEncoder.encoder.getDirection() ? 1.0 : -1.0));  
        //System.out.println("photogate: " + modules[1].turnEncoder.zeroSensor.get());
        System.out.println("Gyro: " + (gyro.getAngle() % 360 - 180));
        System.out.println("");
        System.out.print("0: ");
        System.out.print(modules[0].turnEncoder.encoder.getRaw());
        System.out.print(", 1: ");
        System.out.print(modules[1].turnEncoder.encoder.getRaw());
        System.out.print(", 2: ");
        System.out.print(modules[2].turnEncoder.encoder.getRaw());
        System.out.print(", 3: ");
        System.out.print(modules[3].turnEncoder.encoder.getRaw());
    }
}