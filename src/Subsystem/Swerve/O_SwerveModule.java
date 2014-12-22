package Subsystem.Swerve;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import MathObject.O_Vector;
import MathObject.O_Point;
import Robot.RobotMap;
import edu.wpi.first.wpilibj.PIDController;
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
        O_Point location;
        O_Vector wheelVector;
        
    final double[] zeroingSpeed = {0.2, 0.24, 0.4, 0.15};
    double zeroSpeedOutput = 0.20;
    double desiredZeroSpeed = 40;
    
    boolean isZeroing = false;
    
    double shouldReverse = 1.0; //1 is forward, //-1 is backwards
    final int maxTurnDegrees  = 150;
    
    public O_SwerveModule(O_Point center, int CimPort, int CimilePort, int turnPort, int turnEncoderA, int turnEncoderB, int zeroPort, double zeroOffset, boolean reverseEncoder){
        location = center;
        cim = new Victor(RobotMap.driveModule, CimPort);
        cim.setExpiration(0.5);
        cimile = new Victor(RobotMap.driveModule, CimilePort);
        cimile.setExpiration(0.5);
        turnMotor = new Talon(RobotMap.turnModule, turnPort);
        turnMotor.setExpiration(0.5);
        turnEncoder = new O_TurnEncoder(turnEncoderA, turnEncoderB, zeroPort, zeroOffset, reverseEncoder);
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
        if (isZeroing) {
            zero();
        }
    }
    
    void zero() {   
        if (isZeroing) {
        if(turnEncoder.zeroSensor.get()) {
            //zero mark reached
            System.out.println("done zeroing");
            turnMotor.set(0);
            turnEncoder.zero();
            turn.enable();
            isZeroing = false;
           
        } else { 
            //zero mark not reached
            System.out.println("zeroing: " + (turnMotor.getChannel() - 1));
            turn.disable();
            zeroSpeedOutput = zeroSpeedOutput + 0.00 * (desiredZeroSpeed - turnEncoder.encoder.getRate()) * (turnEncoder.encoder.getDirection() ? 1.0 : -1.0);
            if(zeroSpeedOutput > 1.0) {
                zeroSpeedOutput = 1.0;
            } else if(zeroSpeedOutput < 0.0) {
                zeroSpeedOutput = 0.0;
            }
            turnMotor.set(zeroSpeedOutput);
            isZeroing = true;
          
        }}
    }
    
    public void setAngle(double angle) {

        if(turnMotor.getChannel() == 2 | turnMotor.getChannel() == 1) {
            angle = angle * -1.0;
        }
        
        if (wheelVector.getMagnitude() > 0.1) {
            int requiredTravel = (int)(angle - turnEncoder.pidGet());
            if (requiredTravel > 180) {
                requiredTravel = requiredTravel - 360;
            }
            if (requiredTravel < -180) {
                requiredTravel = requiredTravel + 360;
            }
            
            System.out.println("required travel: " + requiredTravel);
            if (Math.abs(requiredTravel) > maxTurnDegrees) {
                //should reverse motor and change angle
                if (angle > 0) { 
                    angle = angle - 180;
                }
                if (angle < 0) {
                    angle = angle + 180;
                }
                shouldReverse = -1.0;
                System.out.println("reversed direction");
            }
            else
            {
                shouldReverse = 1.0;
            }

            turn.setSetpoint(angle);
          
        }
        
        //System.out.println("P: "+ turn.getP());
        //System.out.println("Current Angle" + turnEncoder.pidGet());
    }
    
    public void setPower(double power) {
        power = power * shouldReverse; // multiplies by -1 to reverse 
        cim.set(power);
        cimile.set(-power);
    }
}

