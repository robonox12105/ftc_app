package org.firstinspires.ftc.teamcode.DriverControl;

/**
 * Created by Robonox <3 on 12/27/2017.
 */
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE;


@TeleOp(name = "OpModeNew", group = "prototypes")
public class OpModeNew extends LinearOpMode {

    // motors
    private DcMotor motorLeft1;
    private DcMotor motorLeft2;
    private DcMotor motorRight1;
    private DcMotor motorRight2;
    private DcMotor motorArm;

    // servos
    private Servo leftServo;
    private Servo rightServo;

    //variables
    private static final double drivingSpeed = .3;
    private static final double turnSpeed = .35;
    private static final double motorArmSpeed = .4;


    @Override
    public void runOpMode() throws InterruptedException {

        // assigning variables to motors
        motorLeft1 = hardwareMap.dcMotor.get("motorLeft1");
        motorLeft2 = hardwareMap.dcMotor.get("motorLeft2");
        motorRight1 = hardwareMap.dcMotor.get("motorRight1");
        motorRight2 = hardwareMap.dcMotor.get("motorRight2");
        motorArm = hardwareMap.dcMotor.get("motorArm");

        // servos
        leftServo = hardwareMap.servo.get("leftServo");
        rightServo = hardwareMap.servo.get("rightServo");

        // setting orientation of motors
        motorLeft1.setDirection(FORWARD);
        motorLeft2.setDirection(FORWARD);
        motorRight1.setDirection(FORWARD);
        motorRight2.setDirection(FORWARD);
        motorArm.setDirection(FORWARD);

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
                stopDriving();
            }

            // motor arm forward backward **** GAMEPAD 2 ****
            if (gamepad2.left_bumper) {
                motorArm.setPower(motorArmSpeed);
            } else if (gamepad2.right_bumper) {
                motorArm.setPower(-motorArmSpeed);
            }

            // opening and closing claw for the glyph **** GAMEPAD 1 ****
            if (gamepad1.a) {
                closeClaw();
            }
            if (gamepad1.b) {
                openClaw();
            }

        }


    }



}

    // VARIOUS FUNCTIONS FOR CONVENIENCE

    public void closeClaw() {
        leftServo.setPosition(0);
        rightServo.setPosition(1);
    }

    public void openClaw() {
        leftServo.setPosition(1);
        rightServo.setPosition(0);
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

    // STOPS DRIVING
    public void stopDriving()
    {
        motorLeft1.setPower(0);
        motorLeft2.setPower(0);
        motorRight1.setPower(0);
        motorRight2.setPower(0);
    }