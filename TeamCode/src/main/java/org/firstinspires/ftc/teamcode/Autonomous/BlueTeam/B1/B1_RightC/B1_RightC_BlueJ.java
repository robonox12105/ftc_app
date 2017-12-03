package org.firstinspires.ftc.teamcode.Autonomous.BlueTeam.B1.B1_RightC;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Robonox on 11/19/2017.
 */

@Autonomous(name="B1_RightC_BlueJ", group="Autonomous")
public class B1_RightC_BlueJ extends LinearOpMode {

    private DcMotor motorLeft1;
    private DcMotor motorRight1;
    private DcMotor motorLeft2;
    private DcMotor motorRight2;
    private ElapsedTime runtime = new ElapsedTime();


    static final double     FORWARD_SPEED = 0.5;
    static final double     TURN_SPEED    = 0.4;

    @Override
    public void runOpMode() {

        motorLeft1 = hardwareMap.dcMotor.get("motorLeft1");
        motorRight1 = hardwareMap.dcMotor.get("motorRight1");
        motorLeft2 = hardwareMap.dcMotor.get("motorLeft2");
        motorRight2 = hardwareMap.dcMotor.get("motorRight2");

        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        waitForStart();

        // Step 1:
        //motorLeft1.setPower( );
        //motorRight1.setPower( );
        //motorLeft2.setPower( );
        //motorRight2.setPower( );
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 2.0)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        sleep(1000);
        // Step 2:
        //motorLeft1.setPower( );
        //motorRight1.setPower(  );
        //motorLeft2.setPower(  );
        //motorRight2.setPower(  );
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.0)) {
            telemetry.addData("Path", "Leg 2: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        sleep(1000);
        // Step 3:
        //motorLeft1.setPower( );
        //motorRight1.setPower( );
        //motorLeft2.setPower(- );
        //motorRight2.setPower( );
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.0)) {
            telemetry.addData("Path", "Leg 3: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        // Step 4:  ADD MORE STEPS IF NECESSARY
        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);
    }
}