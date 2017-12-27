package org.firstinspires.ftc.teamcode.DriverControl;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;

/**
 * Created by Robonox on 10/18/2017.
 */

@TeleOp(name = "OpModeMk3", group = "prototypes")
@Disabled
public class OpModeMk3 extends LinearOpMode {
    private DcMotor motorLeft1;
    private DcMotor motorRight1;
    private DcMotor motorLeft2;
    private DcMotor motorRight2;
    private DcMotor motorLift;
    // servos
    private Servo bigClaw;
    private Servo smallClaw;

    // comment out if they use motors instead
    private DcMotor motorArm2;
    private DcMotor motorArm;

    // variables
    private static final double ARM_RETRACTED_POSITION = 0.8;
    private static final double ARM_EXTENDED_POSITION = 0.6;

    private static final double CLAW_OPEN = .5;
    private static final double CLAW_CLOSE = .9;

    private static final double LIFT_POWER = .5;

    //private static final double ELBOW_START_POSITION = 0;
    //private static final double ELBOW_POP_POSITION = 1;
    @Override
    public void runOpMode() throws InterruptedException {
        // MOTOR SETUP
        motorLeft1 = hardwareMap.dcMotor.get("motorLeft1");
        motorRight1 = hardwareMap.dcMotor.get("motorRight1");
        motorLeft2 = hardwareMap.dcMotor.get("motorLeft2");
        motorRight2 = hardwareMap.dcMotor.get("motorRight2");
        motorLift = hardwareMap.dcMotor.get("motorLift");

        motorLeft1.setDirection(FORWARD);
        motorLeft2.setDirection(FORWARD);
        motorRight1.setDirection(FORWARD);
        motorRight2.setDirection(FORWARD);
        motorLift.setDirection(FORWARD);


        // SERVO SETUP
        bigClaw = hardwareMap.servo.get("bigClaw");

        bigClaw.setPosition(ARM_RETRACTED_POSITION);

        smallClaw = hardwareMap.servo.get("smallClaw");

        // COMMENT OUT IF THEY USE SERVO INSTEAD

        motorArm2 = hardwareMap.dcMotor.get("motorArm2");
        motorArm = hardwareMap.dcMotor.get("motorArm");
        motorArm.setDirection(FORWARD);


        waitForStart();

        while (opModeIsActive()) {

                  /*MOVE LEFT */
            if (gamepad1.left_stick_x < 0) {
                motorLeft1.setPower(-.75);
                motorLeft2.setPower(.75);
                motorRight1.setPower(.75);
                motorRight2.setPower(-75);
            }
            /*MOVE RIGHT*/
            else if (gamepad1.left_stick_x > 0) {
                motorLeft1.setPower(.75);
                motorLeft2.setPower(-.75);
                motorRight1.setPower(-.75);
                motorRight2.setPower(.75);
            }
            /*FORWARD*/
            else if (gamepad1.left_stick_y > 0) {
                motorLeft1.setPower(.75);
                motorLeft2.setPower(.75);
                motorRight1.setPower(-.75);
                motorRight2.setPower(-.75);
            }
            /*BACKWARD*/
            else if (gamepad1.left_stick_y < 0) {
                motorLeft1.setPower(-.75);
                motorLeft2.setPower(-.75);
                motorRight1.setPower(.75);
                motorRight2.setPower(.75);
            }
            /*SPIN LEFT*/
            else if (gamepad1.right_stick_x < 0) {
                motorLeft1.setPower(.75);
                motorLeft2.setPower(.75);
                motorRight1.setPower(.75);
                motorRight2.setPower(.75);
            }
            /*SPIN RIGHT*/
            else if (gamepad1.right_stick_x > 0) {
                motorLeft1.setPower(-.75);
                motorLeft2.setPower(-.75);
                motorRight1.setPower(-.75);
                motorRight2.setPower(-.75);
            } else {
                motorLeft1.setPower(0);
                motorLeft2.setPower(0);
                motorRight1.setPower(0);
                motorRight2.setPower(0);
            }

            //LIFT UP-DOWN
            if (gamepad2.right_bumper) {
                motorLift.setPower(LIFT_POWER);
            } else if (gamepad2.left_bumper) {
                motorLift.setPower(-LIFT_POWER);
            } else {
                motorLift.setPower(0);
            }

            //BIG CLAW
            if (gamepad1.a) {
                bigClaw.setPosition(ARM_EXTENDED_POSITION);
            }
            if (gamepad1.b) {
                bigClaw.setPosition(ARM_RETRACTED_POSITION);
            }
            if (gamepad2.x) {
                smallClaw.setPosition(CLAW_OPEN);
            }
            if (gamepad2.y) {
                smallClaw.setPosition(CLAW_CLOSE);
            }

            //SMALL CLAW ARMS
            // A = move second arm motor forward (the second arm)
            // B = move second arm motor backward (the second arm)
            // RIGHT TRIGGER =  moving the first arm and second arm forward slowly
            // Left trigger = moving the first arm back
            // tentative position numbers we need to it first.
            if (gamepad2.a) {
                motorArm2.setPower(.5);
            } else if (gamepad2.b) {
                motorArm2.setPower(-.5);
            } else {
                motorArm2.setPower(0);
            }
            // maybe this won't work unless all of this is under an else if!!
            if (gamepad2.y) {
                motorArm.setPower(.5);
                motorArm2.setPower(-.5);

            } else if (gamepad2.x) {
                motorArm.setPower(-.5);
                motorArm2.setPower(.5);
            } else {
                motorArm.setPower(0);
                motorArm2.setPower(0);
            }
            idle();
        }
    }
}

