package org.firstinspires.ftc.teamcode.DriverControl;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE;

/**
 * Created by Robonox on 10/18/2017.
 */

@TeleOp(name = "OpModeMk4", group = "prototypes")
public class OpMOdeMk4 extends LinearOpMode {
    //driving motors
    private DcMotor motorLeft1;
    private DcMotor motorRight1;
    private DcMotor motorLeft2;
    private DcMotor motorRight2;
    //motor for lifting claw
    private DcMotor motorLift;
    // servos
    private Servo bigClaw;
    //private Servo wristServo;
    //private Servo elbowServo;
    private Servo leftServo;
    private Servo rightServo;

    // comment out if they use motors instead

    private DcMotor motorArmExtender;
    private DcMotor motorWrist;

    // variables
    private static final double CLOSE_POSITION = 0;
    private static final double OPEN_POSITION = 1;

    private static final double CLAW_OPEN = .2;
    private static final double CLAW_CLOSE = 1;

    private static final double LIFT_POWER = .7;

    private static final double drivingSpeed = .3;
    private static final double turnSpeed = .35;


    @Override
    public void runOpMode() throws InterruptedException {
        // telling robot to
        motorLeft1 = hardwareMap.dcMotor.get("motorLeft1");
        motorRight1 = hardwareMap.dcMotor.get("motorRight1");
        motorLeft2 = hardwareMap.dcMotor.get("motorLeft2");
        motorRight2 = hardwareMap.dcMotor.get("motorRight2");
        motorLift = hardwareMap.dcMotor.get("motorLift");

        //setting the orientation of the driving motors
        motorLeft1.setDirection(FORWARD);
        motorLeft2.setDirection(FORWARD);
        motorRight1.setDirection(FORWARD);
        motorRight2.setDirection(FORWARD);
        motorLift.setDirection(FORWARD);


        // SERVO SETUP
        bigClaw = hardwareMap.servo.get("bigClaw");

        //presetting a position for the big claw


        //telling robot
        //wristServo = hardwareMap.servo.get("wristServo");
        //elbowServo = hardwareMap.servo.get("elbowServo");
        leftServo = hardwareMap.servo.get("leftServo");
        rightServo = hardwareMap.servo.get("rightServo");

        //setting orientation of the motor for extending arm
        motorArmExtender = hardwareMap.dcMotor.get("motorArmExtender");
        motorWrist = hardwareMap.dcMotor.get("motorWrist");
        motorArmExtender.setDirection(FORWARD);
        motorWrist.setDirection(FORWARD);


        waitForStart();

        while (opModeIsActive()) {

            //dpad of controller 1 is used for all linear driving movements of
            //the robot



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


            //the lifting mechanism is controlled by the bumpers
            //on controller 2

            //LIFT UP-DOWN
            if (gamepad2.dpad_up) {
                motorLift.setPower(LIFT_POWER);
            } else if (gamepad2.dpad_down) {
                motorLift.setPower(-LIFT_POWER);
            } else {
                motorLift.setPower(0);
            }

            if (gamepad1.a) {
                closeClaw();
            }
            if (gamepad1.b) {
                openClaw();
            }

            //the opening and closing of the main claw is used by the buttons a & b
            // on controller 2
            //BIG CLAW OPEN-CLOSE
            if (gamepad2.b)
            {
                bigClaw.setPosition(OPEN_POSITION);
            }
            if (gamepad2.a)
            {
                bigClaw.setPosition(CLOSE_POSITION);
            }

            //the opening and closing of the small claw is used by the buttons x & y
            // on controller 2
            //SMALL CLAW OPEN-CLOSE
            // team 11920 graciously supplied us with an extension cord
           /*  if (gamepad2.y)
            {
                wristServo.setPosition(CLAW_OPEN);
            }
            if (gamepad2.x)
            {
                wristServo.setPosition(CLAW_CLOSE);
            }*/

            //SMALL CLAW ARMS

            //motor extending movement of the small claw arm is used by the
            // bumpers on controller 2
            //SMALL CLAW ARM EXTEND-RETRACT
            if (gamepad2.right_bumper) {
                motorArmExtender.setPower(.3);
            } else if (gamepad2.left_bumper) {
                motorArmExtender.setPower(-.3);
            } else {
                motorArmExtender.setPower(0);
            }
            if (gamepad2.x) {
                motorWrist.setPower(.2);

            } else {
                motorWrist.setPower(0);
            }

            if (gamepad2.y){
                motorWrist.setPower(-2);
            } else {
                motorWrist.setPower(0);
            }
            //lifting the middle of the extending arm will be controlled
            //
           /* if (gamepad2.left_trigger == 1) {
                elbowServo.setPosition(1);
            }
            if (gamepad2.right_trigger == 1) {
                elbowServo.setPosition(0);
            }*/

            idle();

        }
    }

    public void closeClaw() {
        leftServo.setPosition(1);
        rightServo.setPosition(0);
    }

    public void openClaw() {
        leftServo.setPosition(0);
        rightServo.setPosition(1);
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
    private void stopDriving()
    {
        motorLeft1.setPower(0);
        motorLeft2.setPower(0);
        motorRight1.setPower(0);
        motorRight2.setPower(0);
    }

}



