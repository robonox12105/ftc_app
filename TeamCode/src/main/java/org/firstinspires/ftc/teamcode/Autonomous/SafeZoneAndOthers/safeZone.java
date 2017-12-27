package org.firstinspires.ftc.teamcode.Autonomous.onlySafeZone;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;

/**
 * Created by Robonox on 12/3/2017.
 * goes to safe zone.
 * Autonomous code!!
 * It also grabs the block
 * puts it in if aligned correctly
 * releases then backs away from the blocks
 */

@Autonomous(name = "safe zone", group = "prototypes")
public class safeZone extends LinearOpMode {

    // this sets up the motors and servos and also variables
    private DcMotor motorLeft1;
    private DcMotor motorRight1;
    private DcMotor motorLeft2;
    private DcMotor motorRight2;
    private DcMotor motorLift;
    private DcMotor motorArm;

    // servos
   // private Servo bigClaw;
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
        // intialization

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
        //bigClaw = hardwareMap.servo.get("bigClaw");


        wristServo = hardwareMap.servo.get("wristServo");


        waitForStart();

        // let's gOOO
       // bigClaw.setPosition(ARM_RETRACTED_POSITION);
        closeOpen(.5,300);
        driveForwardTime(.5, 1500);

        closeOpen(-.5,300);
        driveBackwardtime(.5, 300);
        stopDriving();




    }


    // FUNCTIONS

    // arm open
    public void closeOpen(double power, long time) throws InterruptedException
    {
        motorArm.setPower(power);
        Thread.sleep(time);
    }
    // driving forward!
    public void driveForward(double power)
    {
        motorLeft1.setPower(-power);
        motorLeft2.setPower(-power);
        motorRight1.setPower(power);
        motorRight2.setPower(power);
    }
    public void driveBackwardtime(double power, long time) throws InterruptedException
    {
        motorLeft1.setPower(power);
        motorLeft2.setPower(power);
        motorRight1.setPower(-power);
        motorRight2.setPower(-power);
        Thread.sleep(time);
    }
    //move right
    public void moveRight(double power)
    {
        motorLeft1.setPower(power);
        motorLeft2.setPower(-power);
        motorRight1.setPower(power);
        motorRight2.setPower(-power);
    }
    //moveLeft
    public void moveLeft(double power)
    {
        motorLeft1.setPower(-power);
        motorLeft2.setPower(power);
        motorRight1.setPower(-power);
        motorRight2.setPower(power);
    }
    //turnLeft
    public void turnLeft(double power)
    {
        motorLeft1.setPower(power);
        motorLeft2.setPower(power);
        motorRight1.setPower(power);
        motorRight2.setPower(power);
    }
    //turnRight
    public void turnRight(double power)
    {
        motorLeft1.setPower(-power);
        motorLeft2.setPower(-power);
        motorRight1.setPower(-power);
        motorRight2.setPower(-power);
    }
    // ABOVE WITH TIME ADDED
    // driving forward!
    public void driveForwardTime(double power, long time) throws InterruptedException
    {
        motorLeft1.setPower(-power);
        motorLeft2.setPower(-power);
        motorRight1.setPower(power);
        motorRight2.setPower(power);
        Thread.sleep(time);
    }
    //move right
    public void moveRightTime(double power, long time) throws InterruptedException
    {
        motorLeft1.setPower(power);
        motorLeft2.setPower(-power);
        motorRight1.setPower(power);
        motorRight2.setPower(-power);
        Thread.sleep(time);
    }
    //moveLeft
    public void moveLeftTime(double power, long time) throws InterruptedException
    {
        motorLeft1.setPower(-power);
        motorLeft2.setPower(power);
        motorRight1.setPower(-power);
        motorRight2.setPower(power);
        Thread.sleep(time);
    }
    //turnLeft
    public void turnLeftTime(double power, long time) throws InterruptedException
    {
        motorLeft1.setPower(power);
        motorLeft2.setPower(power);
        motorRight1.setPower(power);
        motorRight2.setPower(power);
        Thread.sleep(time);
    }
    //turnRight
    public void turnRightTime(double power, long time) throws InterruptedException
    {
        motorLeft1.setPower(-power);
        motorLeft2.setPower(-power);
        motorRight1.setPower(-power);
        motorRight2.setPower(-power);
        Thread.sleep(time);
    }


    // releases glyph
    public void releaseGlyph()
    {
        //bigClaw.setPosition(ARM_RETRACTED_POSITION);
    }

    //moves lifting up for a specific amount of time and power!!
    public void liftUp(double power, long time) throws InterruptedException
    {
        motorLift.setPower(power);
        Thread.sleep(time);
    }

    //moves lifting down for a specific amount of time and power!!
    public void liftDown(double power, long time) throws InterruptedException
    {
        motorLift.setPower(-power);
        Thread.sleep(time);
    }

    // STOPS DRIVING
    public void stopDriving()
    {
        motorLeft1.setPower(0);
        motorLeft2.setPower(0);
        motorRight1.setPower(0);
        motorRight2.setPower(0);
    }
}

