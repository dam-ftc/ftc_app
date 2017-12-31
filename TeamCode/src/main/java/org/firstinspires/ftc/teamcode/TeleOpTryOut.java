package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Andrei on 11/22/2017.
 */

//@Disabled
@TeleOp(name="Basic: Linear OpMode", group="Linear Opmode")

public class TeleOpTryOut extends LinearOpMode
{
    private DcMotor motor1 = null;//motoare stanga
    private DcMotor motor2 = null;
    private DcMotor motor3 = null;//motare dreapta
    private DcMotor motor4 = null;
    private DcMotor motor_misc1 = null;//motor pentru
    private DcMotor motor_misc2 = null;//motor pentru
    private Servo arm1 = null;//brat prindere stang
    private Servo arm2 = null;//brat prindere drept

    private double PMotSt, PMotDr;
    private boolean cb_grab = false;//Sunt cuburile apucate? (adevarat/fals)

    public void Move()//Miscarea robotului fata spate stanga dreapta, fiecare joystick controleaza 2 motoare
    {
        if(gamepad1.right_stick_y > gamepad1.right_stick_x)
        {
            motor1.setPower(gamepad1.right_stick_y);
            motor2.setPower(gamepad1.right_stick_y);

            motor3.setPower(-gamepad1.right_stick_y);
            motor4.setPower(-gamepad1.right_stick_y);
        }
        else
        {
            motor1.setPower(-gamepad1.right_stick_x);
            motor2.setPower(-gamepad1.right_stick_x);

            motor3.setPower(-gamepad1.right_stick_x);
            motor4.setPower(-gamepad1.right_stick_x);
        }
/*        motor1.setPower(gamepad1.left_stick_y);
        motor2.setPower(gamepad1.left_stick_y);

        motor3.setPower(-gamepad1.right_stick_y);
        motor4.setPower(-gamepad1.right_stick_y); */
    }

    public void Strafe()
    {

    }

    public void MotMisc()//Miscarea motoarelor pentru relicva si ridicarea cuburilor
    {
        if(gamepad1.dpad_up)
            motor_misc1.setPower(0.5);
        else if(gamepad1.dpad_down)
            motor_misc1.setPower(-0.5);
        else
            motor_misc1.setPower(0);

        if(gamepad1.dpad_left)
             motor_misc2.setPower(0.5);
        else if(gamepad1.dpad_right)
            motor_misc2.setPower(-0.5);
        else
            motor_misc2.setPower(0);
    }


/*
    Controlarea servo-urilor care prind cuburile
                PozBrat1    PozBrat2
    Brat inchis:  120(0.47)         160(0.62)
    Brat deschis: 60()          210()
*/
    public void GrabCube()
    {
        if(gamepad1.a == true)
            if(cb_grab == true)
            {
                cb_grab = false;
                arm1.setPosition(0.5);
                arm2.setPosition(0.5);
            }
            else
            {
                cb_grab = true;
                arm1.setPosition(0.9);
                arm2.setPosition(0.9);
            }
    }

/*
  Legenda controale:
   Joystick stanga - Cele 2 motoare pentru rotile din stanga
   Joystick dreapta - Cele 2 motoare pentru rotile din dreapta
   DPad Sus si DPad Jos -
   DPad Stanga si DPad Dreapta -

   Main(). Aici se initializeaza fiecare componenta si se activeaza subprogramele necesare
*/
    @Override
    public void runOpMode() throws InterruptedException
    {
         motor1 = hardwareMap.get(DcMotor.class, "motor1");
         motor2 = hardwareMap.get(DcMotor.class, "motor2");
         motor3 = hardwareMap.get(DcMotor.class, "motor3");
         motor4 = hardwareMap.get(DcMotor.class, "motor4");
        motor_misc1 = hardwareMap.get(DcMotor.class, "motor_misc1");
        motor_misc2 = hardwareMap.get(DcMotor.class, "motor_misc2");
         arm1 = hardwareMap.get(Servo.class, "arm1");
         arm2 = hardwareMap.get(Servo.class, "arm2");

        telemetry.addData("Status:", "Initialized");
        telemetry.update();
        waitForStart();

        while(opModeIsActive())
        {
            Move();
            /*Strafe();*/
            GrabCube();
            MotMisc();
        }
        telemetry.addData("Status:" , "Terminated");
        telemetry.update();
    }
}
