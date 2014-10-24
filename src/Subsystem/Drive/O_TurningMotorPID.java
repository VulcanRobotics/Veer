/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 //test change
package Subsystem.Drive;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.Talon;

/**
 *
 * @author 1218
 */
public class O_TurningMotorPID extends PIDSubsystem {

    private static final double Kp = 0.0;
    private static final double Ki = 0.0;
    private static final double Kd = 0.0;

    private Talon motor;
    O_TurningEncoder encoder;
    
    // Initialize your subsystem here
    public O_TurningMotorPID(int talonPort, int encoderPort1, int encoderPort2) {
        super("turningMotorPID", Kp, Ki, Kd);
        motor = new Talon(talonPort);
        //encoder = new TurningEncoder(encoderPort1, encoderPort2, 360, 2);
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return 0.0;
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        motor.set(output);
    }
    
    public void setAngle(int angle)
    {
        setSetpoint(angle);
    }
}
