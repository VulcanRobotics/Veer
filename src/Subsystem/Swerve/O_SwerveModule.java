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
    O_Point location;
    O_Vector wheelVector;
    //Drive
        Victor cim;
        Victor cimile;
    //Turn
        Talon turnMotor;
        TurnEncoder turnEncoder;
        PIDController turn;
    
    boolean isZeroing;

    public O_SwerveModule(O_Point center, int CimPort, int CimilePort, int turnPort, int turnEncoderA, int turnEncoderB, int zeroPort, double zeroOffset){
        location = center;
        
        cim = new Victor(RobotMap.driveModule, CimPort);
        cim.setExpiration(0.5);
        cimile = new Victor(RobotMap.driveModule, CimilePort);
        cimile.setExpiration(0.5);
        turnMotor = new Talon(RobotMap.turnModule, turnPort);
        turnMotor.setExpiration(.5);
        turnEncoder = new TurnEncoder(turnEncoderA, turnEncoderB, zeroPort, zeroOffset);
       
        isZeroing = false;
        
        turn = new PIDController(1.0, 0.1, 0.1, turnEncoder, turnMotor, .0010);
        turn.setInputRange(-180, 180);
        turn.setOutputRange(-.5, .5);
        turn.setContinuous();
        
        turn.enable();
        
        
    }
    
    void update() {
        
        setAngle(wheelVector.getAngle());
        setPower(wheelVector.getMagnitude());
        turn.setPID(SmartDashboard.getNumber("TurningP", 0.01),
                    SmartDashboard.getNumber("TurningI", 0.0),
                    SmartDashboard.getNumber("TurningD", 0.0));
        
        if (turnMotor.getChannel() == 4) {
            SmartDashboard.putNumber("WheelAngle", turnEncoder.pidGet());
            SmartDashboard.putNumber("PIDTarget", turn.getSetpoint());
        }
        SmartDashboard.putNumber("Power" + turnMotor.getChannel(), wheelVector.getMagnitude());
        if (isZeroing) {
            zero();
        }
    }
    
    void zero() {
       if (isZeroing) {
            if (turnEncoder.zeroSensor.get()) {
                turnMotor.set(0);
                turnEncoder.zero();
                isZeroing = false;
                turn.enable();
            }
            else {
                turn.disable();
                turnMotor.set(0.15);
            }
        }
    }
    
    public void setAngle(double angle) {
        turn.setSetpoint(angle);
        System.out.println("P: "+ turn.getP());
        System.out.println(turnEncoder.pidGet());
    }
    
    public void setPower(double power) {
        cim.set(power);
        cimile.set(-power);
    }
}

class TurnEncoder implements PIDSource{

    Encoder encoder;
    DigitalInput zeroSensor;
    
    double offset; // how far the module has to be adjusted to make the dash zero
    double zeroOffset; //how far the groove is away from "true zero" (wheels facing north)
    
    public TurnEncoder(int APort, int BPort, int zeroPort, double zeroOffset){
        boolean shouldReverse = (APort == RobotMap.SM3_EncoderA || APort == RobotMap.SM2_EncoderA);
       
        zeroSensor = new DigitalInput(RobotMap.turnModule, zeroPort);
        offset = 0;
        this.zeroOffset = zeroOffset;
        
        encoder = new Encoder(RobotMap.turnModule, APort, RobotMap.turnModule, BPort, shouldReverse, CounterBase.EncodingType.k4X);
        encoder.setDistancePerPulse(360.0/500.0);
        encoder.start();
    }
    
    public double pidGet () {
        double angle = (encoder.getDistance() - offset + zeroOffset) % 360.0;
        if (angle < 0) {
            angle = angle + 360;
        }
        return (angle - 180);
    }
    
    public void zero () {
        offset = encoder.getDistance();
    }
}
