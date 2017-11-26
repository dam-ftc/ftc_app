package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Andrei on 11/22/2017.
 */

@Disabled
@TeleOp(name="Basic: Linear OpMode", group="Linear Opmode")

public class TeleOpTryOut extends LinearOpMode
{
    private DcMotor mot = null;

    @Override
    public void runOpMode() throws InterruptedException
    {
        mot  = hardwareMap.get(DcMotor.class, "mot");

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();

        while(opModeIsActive())
        {
            telemetry.addData( "Status" , "Motor On" );
            telemetry.update();

            mot.setPower(0.4);
        }
        telemetry.addData("Status" , "Terminated");
        telemetry.update();
        mot.setPower(0);
    }
}
