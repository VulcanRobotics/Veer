/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 //test change
package Subsystem.Swerve;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Talon;

/**
 *
 * @author 1218
 */
public class O_TurningMotor {

    private static final double Kp = 0.0;
    private static final double Ki = 0.0;
    private static final double Kd = 0.0;

    private Talon motor;
    O_TurningEncoder turningEncoder;
    PIDController PID;
    
    // Initialize your subsystem here
    public O_TurningMotor(int talonPort, int encoderPortA, int encoderPortB) {
        motor = new Talon(2,talonPort); //turing motors on digital breakout 2
        turningEncoder = new O_TurningEncoder(encoderPortA, encoderPortB);
        PID = new PIDController(Kp, Ki, Kd, turningEncoder, motor);
        PID.setInputRange(-180, 180);
        PID.setOutputRange(-1, 1);
        PID.setContinuous(true);
        PID.enable();
    }
    
    public void setAngle(int angle) {
        PID.setSetpoint(angle);
    }
}