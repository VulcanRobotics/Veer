package drive;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import customMath.Vector;
import customMath.Point;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Talon;
/**
 *
 * @author 1218
 */
public class SS_SwerveModule extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public Point location;  
    
    Talon cimPower;
    Talon cimilePower;
    TurningMotorPID turningMotor;
    
    Vector wheelVector;
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void init(Point newLocation, int cimPort, int cimilePort, int banebotPort, int encoderPort1, int encoderPort2)
    {
        location = newLocation;
        
        cimPower = new Talon(cimPort);
        cimilePower = new Talon(cimPort);
        turningMotor = new TurningMotorPID(banebotPort, encoderPort1, encoderPort2);
    }
    
    public void update()
    {
        double wheelPower = wheelVector.getMagnitude();
        cimPower.set(wheelPower);
        cimilePower.set(wheelPower);
        
        double wheelAngle = wheelVector.getAngle();
    }
}
