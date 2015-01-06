
package Robot;

import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    
    public static Joystick joystick1;
    public static Joystick joystick2;
    
    public static Button Snake;
    public static Button GoToHeading;
    public static Button Pivot0;
    public static Button Pivot1;
    public static Bumper Pivot2;
    public static Button ZeroModules;
    public static Button ResetGyro;
    public static Button CancelZeroModules;
    public static Button ButtonUnused;
    
    public OI() {
        joystick1 = new Joystick(RobotMap.J1);
        joystick2 = new Joystick(RobotMap.J2);
        
        Snake = new JoystickButton(joystick1, ButtonType.LeftThumb);
            Snake.whileHeld(new Subsystem.Swerve.C_Snake());
        GoToHeading = new JoystickButton(joystick1, ButtonType.RightThumb);
            GoToHeading.whileHeld(new Subsystem.Swerve.C_GoToHeading());
        Pivot0 = new JoystickButton(joystick1, ButtonType.L1);
            Pivot0.whileHeld(new Subsystem.Swerve.C_Pivot());
        Pivot1 = new JoystickButton(joystick1, ButtonType.R1);
            Pivot1.whileHeld(new Subsystem.Swerve.C_Pivot());
        Pivot2 = new Bumper(joystick1, Axis.Trigger);
            Pivot2.whileHeld(new Subsystem.Swerve.C_Pivot());
        ZeroModules = new JoystickButton(joystick1, ButtonType.A);
            ZeroModules.whenPressed(new Subsystem.Swerve.C_ZeroModules());
        ResetGyro = new JoystickButton(joystick1, ButtonType.B);
        	ResetGyro.whenPressed(new Subsystem.Swerve.C_ResetGyro());
        CancelZeroModules = new JoystickButton(joystick1, ButtonType.X);
    }
    
    public static double leftY() {
        return joystick1.getRawAxis(1);
    }
    
    public static double leftX() {
        return -joystick1.getRawAxis(2);
    }
    
    public static double rightX() {
        return joystick1.getRawAxis(4);
    }
    
    public static double rightY() {
        return -joystick1.getRawAxis(5);
    }
    
    
    public static int leftAngle() {
        return (int)Math.toDegrees( MathUtils.atan2(leftY(), leftX()));
    }
    
    public static double leftMagnitude() {
        return Math.sqrt(leftX() * leftX() + leftY() * leftY());
    }
    
    public static int rightAngle() {
        return (int)Math.toDegrees( MathUtils.atan2(rightY(), rightX()));
    }
    
    public static double rightMagnitude() {
        return Math.sqrt(rightX() * rightX() + rightY() * rightY());
    }
   
    public class Bumper extends JoystickButton {
        GenericHID joystick;
        int TriggerAxis;

        public Bumper(GenericHID joystick, int TriggerAxis) {
            super(joystick, TriggerAxis);
           this.TriggerAxis = TriggerAxis;
            this.joystick = joystick;
        }

        public boolean getLeft() {
            return joystick.getRawAxis(TriggerAxis) > 0.1;
        }
        public boolean getRight() {
        	return joystick.getRawAxis(TriggerAxis) < -0.1;
        }
    }
    
    public class rightBumper extends JoystickButton {
        GenericHID joystick;
        int buttonNumber;

        public rightBumper(GenericHID joystick, int buttonNumber) {
            super(joystick, buttonNumber);
            this.buttonNumber = buttonNumber;
            this.joystick = joystick;
        }

        public boolean get() {
            return joystick.getRawAxis(buttonNumber) < -0.1;
        }
    }
    public static class Axis {
	public static int LeftX = 1;
	public static int LeftY = 2;
	public static int Trigger = 3;
	public static int RightX = 4;
	public static int RightY = 5;
	public static int DPadLeftRight = 6;
    }
    public static class ButtonType {
        public static int A = 1;
	public static int B = 2;
	public static int X = 3;
	public static int Y = 4;
	public static int L1 = 5;
	public static int R1 = 6;
	public static int LeftThumb = 9;
	public static int RightThumb = 10;
    }
}
