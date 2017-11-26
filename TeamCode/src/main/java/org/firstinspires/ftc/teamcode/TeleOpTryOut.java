package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Andrei on 11/22/2017.
 */

@Disabled
@TeleOp(name="Basic: Linear OpMode", group="Linear Opmode")

public class TeleOpTryOut extends LinearOpMode
{
    private DcMotor motor1 = null;
    private DcMotor motor2 = null;
    private DcMotor motor3 = null;
    private DcMotor motor4 = null;
    private Servo arm1 = null;
    private Servo arm2 = null;

    private double PMotSt, PMotDr;
    private boolean cb_grab = false;

    public void Move()
    {
        motor1.setPower(gamepad1.left_stick_y);
        motor2.setPower(gamepad1.left_stick_y);

        motor3.setPower(gamepad1.right_stick_y);
        motor4.setPower(gamepad1.right_stick_y);
    }

    public void GrabCube()
    {
        if(gamepad1.a == true)
            if(cb_grab == true)
            {
                arm1.setPosition(0.5);
                arm2.setPosition(0.5);
            }
            else
            {
                arm1.setPosition(0.9);
                arm2.setPosition(0.9);
            }
    }

    @Override
    public void runOpMode() throws InterruptedException
    {
         motor1 = hardwareMap.get(DcMotor.class, "motor1");
         motor2 = hardwareMap.get(DcMotor.class, "motor2");
         motor3 = hardwareMap.get(DcMotor.class, "motor3");
         motor4 = hardwareMap.get(DcMotor.class, "motor4");
         arm1 = hardwareMap.get(Servo.class, "arm1");
         arm2 = hardwareMap.get(Servo.class, "arm2");

        telemetry.addData("Status:", "Initialized");
        telemetry.update();
        waitForStart();

        while(opModeIsActive())
        {
            Move();
            GrabCube();
        }
        telemetry.addData("Status:" , "Terminated");
        telemetry.update();
    }
}
