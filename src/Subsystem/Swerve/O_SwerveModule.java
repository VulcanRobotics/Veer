package Subsystem.Swerve;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import MathObject.O_Vector;
import MathObject.O_Point;
import Robot.RobotMap;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 *
 * @author 1218
 */
public class O_SwerveModule {
    //Drive
        Victor cim;
        Victor cimile;
    //Turn
        Talon turnMotor;
        O_TurnEncoder turnEncoder;
        PIDController turn;
    //Data
        boolean isZeroing;
        O_Point location;
        O_Vector wheelVector;
        
    final double[] zeroingSpeed = {0.2, 0.24, 0.4, 0.15};
    
    public O_SwerveModule(O_Point center, int CimPort, int CimilePort, int turnPort, int turnEncoderA, int turnEncoderB, int zeroPort, double zeroOffset, boolean reverseEncoder){
        location = center;
        cim = new Victor(RobotMap.driveModule, CimPort);
        cim.setExpiration(0.5);
        cimile = new Victor(RobotMap.driveModule, CimilePort);
        cimile.setExpiration(0.5);
        turnMotor = new Talon(RobotMap.turnModule, turnPort);
        turnMotor.setExpiration(0.5);
        turnEncoder = new O_TurnEncoder(turnEncoderA, turnEncoderB, zeroPort, zeroOffset, reverseEncoder);
       
        isZeroing = false;
        
        turn = new PIDController(1.0, 0.1, 0.1, turnEncoder, turnMotor, .0010) {{
            setInputRange(-180, 180);
            setOutputRange(-.85, .85);
            setContinuous();
            enable();
        }};
    }
    
    void update() {
        setAngle(wheelVector.getAngle());
        setPower(wheelVector.getMagnitude());
        turn.setPID(SmartDashboard.getNumber("TurningP", 0.01),
                    SmartDashboard.getNumber("TurningI", 0.0),
                    SmartDashboard.getNumber("TurningD", 0.0));
        if(isZeroing) {
            zero();
        }
    }
    
    void zero() {
        if(turnEncoder.zeroSensor.get()) {
            turnMotor.set(0);
            turnEncoder.zero();
            isZeroing = false;
            turn.enable();
        } else {
            turn.disable();
            turnMotor.set(zeroingSpeed[turnMotor.getChannel() - 1]);
        }
    }
    
    public void setAngle(double angle) {
        if(turnMotor.getChannel() == 2 | turnMotor.getChannel() == 1) {
            turn.setSetpoint(-angle);
        } else {
            turn.setSetpoint(angle);
        }
        System.out.println("P: "+ turn.getP());
        System.out.println("Current Angle" + turnEncoder.pidGet());
    }
    
    public void setPower(double power) {
        cim.set(power);
        cimile.set(-power);
    }
}

