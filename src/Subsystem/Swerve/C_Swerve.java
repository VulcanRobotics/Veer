package Subsystem.Swerve;

import MathObject.O_Point;
import Robot.CommandBase;
import MathObject.O_Vector;
import Robot.OI;
/**
 *
 * @author 1218
 */
public class C_Swerve extends CommandBase {

    public C_Swerve() {
        requires(swerve);
    }

    protected void initialize() {
        
    }

    protected void execute() {
        if(!OI.leftThumbClick.get()) {
            boolean shouldRunTwist = true;
            if(OI.joystick1.getRawAxis(3) > 0.4) { //back left button, front left wheel
                System.out.println("turning of front left wheel");
                swerve.swerve(new O_Vector(0, 0), new O_Point(1,-1), OI.joystick1.getRawAxis(4));
                shouldRunTwist = false;
            }
            if(OI.joystick1.getRawAxis(3) < -0.4) { //back right button, front right wheel
                System.out.println("turning of front right wheel");
                swerve.swerve(new O_Vector(0, 0), new O_Point(1,1), OI.joystick1.getRawAxis(4));
                shouldRunTwist = false;
            }
            if(OI.Button_R1.get()) { //back right wheel
                System.out.println("turning of back right wheel");
                swerve.swerve(new O_Vector(0, 0), new O_Point(-1,1), -1*OI.joystick1.getRawAxis(4));
               shouldRunTwist = false;
            }
            if(OI.Button_L1.get()) { //back left wheel
                System.out.println("turning of back left wheel");
                swerve.swerve(new O_Vector(0, 0), new O_Point(-1,-1), -1*OI.joystick1.getRawAxis(4));
                shouldRunTwist = false;
            }
            if(shouldRunTwist) {
                System.out.println("swerve twisting");
                swerve.swerve(new O_Vector(-OI.joystick1.getY(), OI.joystick1.getX()), new O_Point(0,0), -1*OI.joystick1.getRawAxis(4));
            }
        } else {
            //snake mode
            System.out.println("snake mode");
            swerve.swerve(new O_Vector(0, 0), new O_Point(0, 1/(OI.joystick1.getRawAxis(4) + 0.02 )), OI.joystick1.getY() * ((OI.joystick1.getRawAxis(4) >= 0) ? 1 : -1));
        }
        swerve.syncDashboard();
    }
    
    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
