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

//forked version

/**
 *
 * @author 1218
 */
public class O_SwerveModule {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    O_Point location;
    O_Vector wheelVector;
   Victor cim;
    Victor cimile;
    
    Talon turnMotor;
    TurnEncoder turnEncoder;
    
    public PIDController turn;
    
    boolean isZeroing;
    public O_SwerveModule(O_Point center, int CimPort, int CimilePort, int turnPort, int turnEncoderA, int turnEncoderB, int zeroPort){
        location = center;
        
        cim = new Victor(RobotMap.driveModule, CimPort);
        cimile = new Victor(RobotMap.driveModule, CimilePort);
        turnMotor = new Talon(RobotMap.turnModule, turnPort);
        turnMotor.setExpiration(.1);
        turnEncoder = new TurnEncoder(turnEncoderA, turnEncoderB, zeroPort);
       
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
                turnEncoder.offset = turnEncoder.pidGet(); //distance module is off correct amount
                isZeroing = false;
                turn.enable();
            }
             else
            {
                  turnMotor.set(0.7);
                  turn.disable();
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
        cimile.set(power);
    }
}

class TurnEncoder implements PIDSource{

    Encoder encoder;
    DigitalInput zeroSensor;
    
    double offset;
    
    
    public TurnEncoder(int APort, int BPort, int zeroPort){
        boolean shouldReverse = false;
        if (APort == RobotMap.SM3_EncoderA || APort == RobotMap.SM2_EncoderA) {
            shouldReverse = true;
        }
       
        zeroSensor = new DigitalInput(RobotMap.turnModule, zeroPort);
        offset = 0;
        
        encoder = new Encoder(2, APort, 2, BPort, shouldReverse, CounterBase.EncodingType.k4X);
        encoder.setDistancePerPulse(500.0/360.0);
        encoder.start();
    }
    
    public double pidGet () {
        double angle = (encoder.getDistance() - offset) % 360.0;
        if (angle < 0) {
            angle = angle + 360;
        }
        return (angle - 180);
    }
}
