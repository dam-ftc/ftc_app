/*
by alex 24.11.2017
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


@Autonomous(name="Blueteam", group="Pushbot")
//@Disabled
public class AutonomousBlueTeam extends LinearOpMode {

    //initializare motoare
    private DcMotor motorleftfront=null;
    private DcMotor motorleftback=null;
    private DcMotor motorrightfront=null;
    private DcMotor motorrightback=null;
    //private DcMotor motorridicare;
    //private DcMotor motorflip;

    //initializare servo
    Servo armServo = null;

    ColorSensor color_sensor;

    @Override
    public void runOpMode() {


        //declarare motoare
        motorrightback = hardwareMap.dcMotor.get("motorrightback");
        motorrightfront = hardwareMap.dcMotor.get("motorrightfront");
        motorleftback = hardwareMap.dcMotor.get("motorleftback");
        motorleftfront = hardwareMap.dcMotor.get("motorleftfront");
        //motorridicare = hardwareMap.dcMotor.get("motorridicare");
        // motorflip = hardwareMap.dcMotor.get("motorflip");
        armServo = hardwareMap.servo.get("arms");
        color_sensor = hardwareMap.colorSensor.get("color");

        //init servo
        armServo.setPosition(0.99);


        //(driver presses PLAY)
        waitForStart();
        armServo.setPosition(0.5);
        if(color_sensor.red()<color_sensor.blue())
        {
            motorleftback.setPower(0.5);
            motorleftfront.setPower(0.5);
            motorrightback.setPower(-0.5);
            motorrightfront.setPower(-0.5);
            sleep(500);
            motorleftback.setPower(0);
            motorleftfront.setPower(0);
            motorrightback.setPower(0);
            motorrightfront.setPower(0);
        }
        else
        {
            motorleftback.setPower(-0.5);
            motorleftfront.setPower(-0.5);
            motorrightback.setPower(0.5);
            motorrightfront.setPower(0.5);
            sleep(500);
            motorleftback.setPower(0);
            motorleftfront.setPower(0);
            motorrightback.setPower(0);
            motorrightfront.setPower(0);
        }

        telemetry.update();
        //sleep(50);

    }
}

