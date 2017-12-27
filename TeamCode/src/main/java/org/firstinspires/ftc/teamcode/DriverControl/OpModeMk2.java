package org.firstinspires.ftc.teamcode.DriverControl;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
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

@TeleOp(name = "OpModeMk2", group = "prototypes")
@Disabled
public class OpModeMk2 extends LinearOpMode {
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

    private static final double ELBOW_START_POSITION = 0.4;
    private static final double ELBOW_POP_POSITION = 0.2;

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

        wristServo = hardwareMap.servo.get("wristServo");

        
        waitForStart();

        while (opModeIsActive()){

                  /*MOVE LEFT */
            if (gamepad1.dpad_right)
            {
                motorLeft1.setPower(-.75);
                motorLeft2.setPower(.75);
                motorRight1.setPower(-.75);
                motorRight2.setPower(.75);
            }
            /*MOVE RIGHT*/
             else if (gamepad1.dpad_left)
            {
                motorLeft1.setPower(.75);
                motorLeft2.setPower(-.75);
                motorRight1.setPower(.75);
                motorRight2.setPower(-.75);
            }
            /*FORWARD*/
            else if (gamepad1.dpad_up)
            {
                motorLeft1.setPower(-.75);
                motorLeft2.setPower(-.75);
                motorRight1.setPower(.75);
                motorRight2.setPower(.75);
            }
            /*BACKWARD*/
            else if (gamepad1.dpad_down)
            {
                motorLeft1.setPower(.75);
                motorLeft2.setPower(.75);
                motorRight1.setPower(-.75);
                motorRight2.setPower(-.75);
            }
            /*SPIN LEFT*/
            else if (gamepad1.right_stick_x < 0 )
            {
                motorLeft1.setPower(.3);
                motorLeft2.setPower(.3);
                motorRight1.setPower(.3);
                motorRight2.setPower(.3);
            }
            /*SPIN RIGHT*/
            else if (gamepad1.right_stick_x > 0 )
            {
                motorLeft1.setPower(-.3);
                motorLeft2.setPower(-.3);
                motorRight1.setPower(-.3);
                motorRight2.setPower(-.3);
            } else {
                motorLeft1.setPower(0);
                motorLeft2.setPower(0);
                motorRight1.setPower(0);
                motorRight2.setPower(0);
            }

            //LIFT UP-DOWN
            //Gamepad 2
            if (gamepad2.right_bumper)
            {
                motorLift.setPower(LIFT_POWER);
            } else if (gamepad2.left_bumper)
            {
                motorLift.setPower(-LIFT_POWER);
            } else
            {
                motorLift.setPower(0);
            }


            //BIG CLAW
            if (gamepad2.b)
            {
                bigClaw.setPosition(ARM_EXTENDED_POSITION);
            }
            if (gamepad2.a)
            {
                bigClaw.setPosition(ARM_RETRACTED_POSITION);
            }

            // Wrist Servo (small claw)
            if (gamepad2.x)
            {
                wristServo.setPosition(CLAW_OPEN);
            }
            if (gamepad2.y)
            {
                wristServo.setPosition(CLAW_CLOSE);
            }

            //ELBOW UP
            if (gamepad2.left_trigger == 1)
            {
                elbowServo.setPosition(ELBOW_START_POSITION);
            }
            if (gamepad2.right_trigger == 1)
            {
                elbowServo.setPosition(ELBOW_POP_POSITION);
            }

            //ELBOW GOING OUT/ IN
            if (gamepad2.dpad_up)
            {
                motorArm.setPower(armPower);
            }
            else if (gamepad2.dpad_down)
            {
                motorArm.setPower(-armPower);
            } else
            {
                motorArm.setPower(0);
            }

            idle();

        }
    }
}