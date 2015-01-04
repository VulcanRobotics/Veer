package Robot;

import edu.wpi.first.wpilibj.command.Command;
import Subsystem.Swerve.SS_Swerve;
/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase stores creates and stores each control system. To access a
 * subsystem elsewhere in your code in your code use CommandBase.exampleSubsystem
 * @author Author
 */
public abstract class CommandBase extends Command {

    public static OI oi;
    public static SS_Swerve swerve;
    
    public static void init() {
        
        swerve = new SS_Swerve();        
        //has to be last
        oi = new OI();
        
    }

    public CommandBase(String name) {
        super(name);
    }
    
    public CommandBase() {
        super();
    }
}
