package org.firstinspires.ftc.teamcode.DriverControl;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE;


/**
 * Created by chris on 1/10/2018.
 */


@TeleOp(name = "OpModeNew2", group = "prototypes")
public class OpModeNew2 extends LinearOpMode {

    // motors
    private DcMotor motorLeft1;
    private DcMotor motorLeft2;
    private DcMotor motorRight1;
    private DcMotor motorRight2;
    private DcMotor motorArmLeft;
    private DcMotor motorArmRight;

    // servos
    private Servo leftServo;
    private Servo rightServo;
    private Servo leftLiveAxel;
    private Servo rightLiveAxel;

    //variables
    private static final double drivingSpeed = .3;
    private static final double turnSpeed = .35;
    private static final double motorArmSpeed = .45;




    @Override
    public void runOpMode() throws InterruptedException {

        // assigning variables to motors
        motorLeft1 = hardwareMap.dcMotor.get("motorLeft1");
        motorLeft2 = hardwareMap.dcMotor.get("motorLeft2");
        motorRight1 = hardwareMap.dcMotor.get("motorRight1");
        motorRight2 = hardwareMap.dcMotor.get("motorRight2");
        motorArmLeft = hardwareMap.dcMotor.get("motorArmLeft");
        motorArmRight = hardwareMap.dcMotor.get("motorArmRight");

        // servos
        leftServo = hardwareMap.servo.get("leftServo");
        rightServo = hardwareMap.servo.get("rightServo");
        leftLiveAxel = hardwareMap.servo.get("leftLiveAxel");
        rightLiveAxel = hardwareMap.servo.get("rightLiveAxel");

        // setting orientation of motors
        motorLeft1.setDirection(FORWARD);
        motorLeft2.setDirection(FORWARD);
        motorRight1.setDirection(FORWARD);
        motorRight2.setDirection(FORWARD);
        motorArmLeft.setDirection(FORWARD);
        motorArmRight.setDirection(FORWARD);

        // INIT
        double leftAxel;

        double rightAxel;
        rightAxel = .7;
        leftAxel = .3;
      /*  leftLiveAxel.setPosition(.8);
        rightLiveAxel.setPosition(0);
        leftServo.setPosition(.3);
        rightServo.setPosition(.7);

        leftServo.setPosition(1);
        rightServo.setPosition(0);
        leftLiveAxel.setPosition(1);
        rightLiveAxel.setPosition(.5);*/


        waitForStart();

        while (opModeIsActive()) {
            // driving **** GAMEPAD 1 ****
            if (gamepad1.dpad_up) {
                driveForward(drivingSpeed);
            } else if (gamepad1.dpad_left) {
                moveLeft(drivingSpeed);
            } else if (gamepad1.dpad_right) {
                moveRight(drivingSpeed);
            } else if (gamepad1.dpad_down) {
                driveBackward(drivingSpeed);
            } else if (gamepad1.right_stick_x < 0) {
                turnLeft(turnSpeed);
            } else if (gamepad1.right_stick_x > 0) {
                turnRight(turnSpeed);
            } else {
                motorLeft1.setPower(0);
                motorLeft2.setPower(0);
                motorRight1.setPower(0);
                motorRight2.setPower(0);
            }

            // motor arm forward backward **** GAMEPAD 2 ****
            if (gamepad2.left_bumper) {
                motorArmLeft.setPower(motorArmSpeed);
                motorArmRight.setPower(-motorArmSpeed);

            } else if (gamepad2.right_bumper) {
                motorArmLeft.setPower(-motorArmSpeed);
                motorArmRight.setPower(motorArmSpeed);
            } else {
                motorArmLeft.setPower(0);
                motorArmRight.setPower(0);
            }

            // opening and closing claw for the glyph **** GAMEPAD 2 ****
            if (gamepad2.a) {
                closeClaw();
            }
            if (gamepad2.b) {
                openClaw();
            }
            if (gamepad1.y) {
                leftLiveAxel.setPosition(.8);
                rightLiveAxel.setPosition(0.1);
                leftServo.setPosition(.3);
                rightServo.setPosition(.7);
            }

            if (gamepad2.y)
            {
                double changeLeft = moveLeftAxelDown(leftAxel);
                double changeRight = moveRightAxelDown(rightAxel);
                leftAxel = changeLeft;
                rightAxel = changeRight;
                if ((changeLeft <= 1 || changeLeft >= 0)) {
                    leftLiveAxel.setPosition(changeLeft);
                }
                if ((changeRight<= 1 || changeRight >= 0)) {
                    rightLiveAxel.setPosition(changeRight);
                }
            }
            if (gamepad2.x) {

                double changeLeft = moveLeftAxelUp(leftAxel);
                double changeRight = moveRightAxelUp(rightAxel);
                leftAxel = changeLeft;
                rightAxel = changeRight;
                if ((changeLeft <= 1 || changeLeft >= 0)) {
                    leftLiveAxel.setPosition(changeLeft);
                }
                if ((changeRight<= 1 || changeRight >= 0)) {
                    rightLiveAxel.setPosition(changeRight);
                }


            }

            if (gamepad2.back) {
                initPos();
            }




            idle();
        }


    }
    // VARIOUS FUNCTIONS FOR CONVENIENCE





    public double moveLeftAxelUp(double leftAxeldiff)
    {
        leftAxeldiff = leftAxeldiff + .05;

        return leftAxeldiff;

    }

    public double moveRightAxelUp( double rightAxeldiff)
    {

        rightAxeldiff = rightAxeldiff - .05;
        return rightAxeldiff;
    }

    public double moveLeftAxelDown(double leftAxeldiff)
    {

        leftAxeldiff = leftAxeldiff - .05;
        return leftAxeldiff;

    }

    public double moveRightAxelDown(double rightAxeldiff) {
        rightAxeldiff = rightAxeldiff + .05;
        return rightAxeldiff;
    }

    public void closeClaw()
    {
        leftServo.setPosition(1);
        rightServo.setPosition(0);
    }

    public void openClaw()
    {
        leftServo.setPosition(.3);
        rightServo.setPosition(.7);
    }

    // driving forward!
    public void driveForward(double power)
    {
        motorLeft1.setPower(-power);
        motorLeft2.setPower(-power);
        motorRight1.setPower(power);
        motorRight2.setPower(power);
    }
    public void driveBackward(double power)
    {
        motorLeft1.setPower(power);
        motorLeft2.setPower(power);
        motorRight1.setPower(-power);
        motorRight2.setPower(-power);

    }
    //move right
    public void moveLeft(double power)
    {
        motorLeft1.setPower(power);
        motorLeft2.setPower(-power);
        motorRight1.setPower(power);
        motorRight2.setPower(-power);
    }
    //moveLeft
    public void moveRight(double power)
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

    //set initial position
    public void initPos() {
        leftLiveAxel.setPosition(.9);
        rightLiveAxel.setPosition(0);
        leftServo.setPosition(.3);
        rightServo.setPosition(.7);
    }

}


