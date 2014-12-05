/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 //test change
package Subsystem.Swerve;

import Robot.RobotMap;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 * @author 1218
 */
public class O_TurningMotor {

    private double Kp = .10;
    private double Ki = 0.0;
    private double Kd = 0.0;

    Talon motor;
    O_TurningEncoder turningEncoder;
    PIDController PID;
    
    // Initialize your subsystem here
    public O_TurningMotor(int talonPort, int encoderPortA, int encoderPortB) {

        
        
        motor = new Talon(RobotMap.turnModule, talonPort); //turing motors on digital breakout 2

        turningEncoder = new O_TurningEncoder(encoderPortA, encoderPortB);
        //motor.set(0.3);
        
        setUpPID();
        updateDashboard();
    }
    
    public void setAngle(int angle) {
        updateDashboard();
        PID.setSetpoint(angle);
    }
    
    
    public void updateDashboard() {
        if (motor.getChannel() == 4)
        {
            SmartDashboard.putNumber("WheelAngle", turningEncoder.pidGet());
           SmartDashboard.putNumber("PIDTarget", PID.getSetpoint());
        
        }
        if (Kp != SmartDashboard.getNumber("TurningP", 6)) {
            Kp = SmartDashboard.getNumber("TurningP", 6);
            setUpPID();
            
        }
        if (Kd != SmartDashboard.getNumber("TurningI", 2)) {
            Kd = SmartDashboard.getNumber("TurningI", 2);
            setUpPID();
        }
        if (Kd != SmartDashboard.getNumber("TurningD", 1)) {
            Kd = SmartDashboard.getNumber("TurningD", 1);
            setUpPID();
        }
        
        System.out.println(PID.getP());
    }
    
    void setUpPID() {
        PID = new PIDController(Kp, Ki, Kd, turningEncoder, motor);
        PID.setInputRange(-180, 180);
        PID.setOutputRange(-.3, .3);
        PID.setContinuous(true);
        PID.enable();
    }
}