package Robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    //Swerve Modules
        //SM0
            public static final int SM0_CIM = 1;
            public static final int SM0_CIMile = 2;
            public static final int SM0_banebot = 1;
            public static final int SM0_EncoderA = 1;
            public static final int SM0_EncoderB = 2;
        //SM1
            public static final int SM1_CIM = 3;
            public static final int SM1_CIMile = 4;
            public static final int SM1_banebot = 2;
            public static final int SM1_EncoderA = 3;
            public static final int SM1_EncoderB = 4;
        //SM2
            public static final int SM2_CIM = 5;
            public static final int SM2_CIMile = 6;
            public static final int SM2_banebot = 3;
            public static final int SM2_EncoderA = 5;
            public static final int SM2_EncoderB = 6;
        //SM3
            public static final int SM3_CIM = 7;
            public static final int SM3_CIMile = 8;
            public static final int SM3_banebot = 4;
            public static final int SM3_EncoderA = 7;
            public static final int SM3_EncoderB = 8;
}
