package Subsystem.Swerve;

import MathObject.O_Point;
import Robot.CommandBase;
import Robot.OI;
import MathObject.O_Vector;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 1218
 */
public class C_Swerve extends CommandBase {

    public C_Swerve() {
        requires(swerve);
    }

    protected void initialize() {
        //swerve.swerve(new O_Vector(-1, 0), new O_Point(0,0), 0.5f);
        
    }

    protected void execute() {
/*
        O_Vector vector = new O_Vector(-(float)OI.joystick1.getY(), (float)OI.joystick1.getX());
        System.out.println("init vector:" + vector.description());
        System.out.println("init vector angle:" + vector.getAngle());
        vector.rotate90();
        System.out.println("rotated 90:" + vector.description());
        System.out.println("rotated vector angle:" + vector.getAngle());
        vector.setMagnitude(1);
        System.out.println("set magnitude:" + vector.description());
        */
        if (!OI.leftThumbClick.get())
        {
            boolean shouldRunTwist = true;
            if (OI.joystick1.getRawAxis(3) > 0.4) //back left button, front left wheel
            {
                System.out.println("turning of front left wheel");
                swerve.swerve(new O_Vector(0, 0), new O_Point(1,-1), (float)OI.joystick1.getRawAxis(4));
                shouldRunTwist = false;
            }
            if (OI.joystick1.getRawAxis(3) < -0.4) //back right button, front right wheel
            {
                System.out.println("turning of front right wheel");
                swerve.swerve(new O_Vector(0, 0), new O_Point(1,1), (float)OI.joystick1.getRawAxis(4));
                shouldRunTwist = false;
            }
             if (OI.R1.get())//back right wheel
            {
                System.out.println("turning of back right wheel");
                swerve.swerve(new O_Vector(0, 0), new O_Point(-1,1), (float)OI.joystick1.getRawAxis(4));
               shouldRunTwist = false;
            }
             if (OI.L1.get()) //back left wheel
            {
                System.out.println("turning of back left wheel");
                swerve.swerve(new O_Vector(0, 0), new O_Point(-1,-1), (float)OI.joystick1.getRawAxis(4));
                shouldRunTwist = false;
            }
            if (shouldRunTwist)
            {
                System.out.println("swerve twisting");
                swerve.swerve(new O_Vector(-(float)OI.joystick1.getY(), (float)OI.joystick1.getX()), new O_Point(0,0), (float)OI.joystick1.getRawAxis(4));
            }
           }
        else
        {
           
            
                //snake mode
            System.out.println("snake mode");
                //swerve.swerve(new O_Vector(0, 0), new O_Point(1/((float)OI.joystick1.getRawAxis(4) + 0.02f ),0), (float)OI.joystick1.getY());
          
         }
        
        //swerve.swerve(new O_Vector(0, 0), new O_Point(0,0), (float)OI.joystick1.getRawAxis(4));
       System.out.flush();
       // System.out.println("Running C_Swerve");
        //System.out.println("execting C_Swerve");
        //swerve.swerve(90, (float)0.75, new O_Point(0,0), 0);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
