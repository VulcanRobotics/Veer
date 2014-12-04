package Robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    //Swerve Modules
        public static final int driveModule = 1;
        public static final int turnModule = 2;
        //SM0
            public static final int SM0_CIM = 1;
<<<<<<< HEAD
            public static final int SM0_CIMile = 5;
            public static final int SM0_banebot = 1;
=======
            public static final int SM0_CIMile = 1;
            public static final int SM0_banebot = 5;
>>>>>>> d3e3dbad06325767419b5a4ad413eefa15e5ecd1
            public static final int SM0_EncoderA = 1;
            public static final int SM0_EncoderB = 2;
        //SM1
            public static final int SM1_CIM = 2;
<<<<<<< HEAD
            public static final int SM1_CIMile = 6;
            public static final int SM1_banebot = 2;
=======
            public static final int SM1_CIMile = 2;
            public static final int SM1_banebot = 6;
>>>>>>> d3e3dbad06325767419b5a4ad413eefa15e5ecd1
            public static final int SM1_EncoderA = 3;
            public static final int SM1_EncoderB = 4;
        //SM2
            public static final int SM2_CIM = 3;
<<<<<<< HEAD
            public static final int SM2_CIMile = 7;
            public static final int SM2_banebot = 3;
=======
            public static final int SM2_CIMile = 3;
            public static final int SM2_banebot = 7;
>>>>>>> d3e3dbad06325767419b5a4ad413eefa15e5ecd1
            public static final int SM2_EncoderA = 5;
            public static final int SM2_EncoderB = 6;
        //SM3
            public static final int SM3_CIM = 4;
<<<<<<< HEAD
            public static final int SM3_CIMile = 8;
            public static final int SM3_banebot = 4;
=======
            public static final int SM3_CIMile = 4;
            public static final int SM3_banebot = 8;
>>>>>>> d3e3dbad06325767419b5a4ad413eefa15e5ecd1
            public static final int SM3_EncoderA = 7;
            public static final int SM3_EncoderB = 8;
            
         //Joysticks
           public static final int J1 = 1;
           public static final int J2 = 2;
           
}
