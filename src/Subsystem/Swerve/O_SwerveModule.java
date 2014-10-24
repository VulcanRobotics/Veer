package Subsystem.Swerve;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import MathObject.O_Vector;
import MathObject.O_Point;
import edu.wpi.first.wpilibj.Talon;

//forked version

/**
 *
 * @author 1218
 */
public class O_SwerveModule {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public O_Point location;  
    Talon CIM;
    Talon CIMile;
    O_TurningMotor turningMotor;
    
    public O_Vector wheelVector;
    
    public O_SwerveModule(O_Point location, int CIMPort, int CIMilePort, int banebotPort, int encoderPortA, int encoderPortB)
    {
        this.location = location;
        CIM = new Talon(CIMPort);
        CIMile = new Talon(CIMilePort);
        turningMotor = new O_TurningMotor(banebotPort, encoderPortA, encoderPortB);
    }
    
    public void update()
    {
        double wheelPower = wheelVector.getMagnitude();
        CIM.set(wheelPower);
        CIMile.set(wheelPower);
        
        double wheelAngle = wheelVector.getAngle();
    }
}
