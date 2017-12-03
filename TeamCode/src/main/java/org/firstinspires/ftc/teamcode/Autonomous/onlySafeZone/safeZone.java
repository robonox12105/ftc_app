package org.firstinspires.ftc.teamcode.Autonomous.onlySafeZone;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;

/**
 * Created by Robonox on 10/18/2017.
 */

@Autonomous(name = "safe zone", group = "prototypes")
public class safeZone extends LinearOpMode {
    private DcMotor motorLeft1;
    private DcMotor motorRight1;
    private DcMotor motorLeft2;
    private DcMotor motorRight2;
    private DcMotor motorLift;
    private DcMotor motorArm;

    // servos
    private Servo bigClaw;
    private Servo wristServo;
    private Servo elbowServo;

    // variables
    private static final double ARM_RETRACTED_POSITION = 0;
    private static final double ARM_EXTENDED_POSITION = 1;

    // for the wrist servo
    private static final double CLAW_OPEN = .5;
    private static final double CLAW_CLOSE = .9;

    private static final double LIFT_POWER = .5;

    private static final double ELBOW_START_POSITION = 1;
    private static final double ELBOW_POP_POSITION = 0;

    //arm power

    private static final double armPower = .05;
    @Override
    public void runOpMode() throws InterruptedException {
        // MOTOR SETUP
        motorLeft1 = hardwareMap.dcMotor.get("motorLeft1");
        motorRight1 = hardwareMap.dcMotor.get("motorRight1");
        motorLeft2 = hardwareMap.dcMotor.get("motorLeft2");
        motorRight2 = hardwareMap.dcMotor.get("motorRight2");
        motorLift = hardwareMap.dcMotor.get("motorLift");
        motorArm = hardwareMap.dcMotor.get("motorArm");

        motorArm.setDirection(FORWARD);
        motorLeft1.setDirection(FORWARD);
        motorLeft2.setDirection(FORWARD);
        motorRight1.setDirection(FORWARD);
        motorRight2.setDirection(FORWARD);
        motorLift.setDirection(FORWARD);



        // SERVO SETUP
        bigClaw = hardwareMap.servo.get("bigClaw");

        bigClaw.setPosition(ARM_RETRACTED_POSITION);

        wristServo = hardwareMap.servo.get("smallClaw");

        
        waitForStart();

        // let's gOOO

        


    }
}