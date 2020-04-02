import java.util.*;
import java.util.Random;
public class DualFA{
    public static String GenerateOTP() {
        String numbers = "0123456789";
        Random r = new Random();
        String otp = "";
        for (int i = 0; i < 6; i++)
        {
            otp += numbers.charAt(r.nextInt(numbers.length()));
        }
        Game.println("OTP Generated: " + otp);
        return otp;
    }
    public static void SendOTP(Request r)
    {
        String otp = GenerateOTP();
        Game.println("Sending OTP...");
        Game.println("Waiting on response...");
        if (r.getHasFactor()){
            Game.println("OTP Response: " + otp);
        } else {
            Game.println("OTP Response: " + GenerateOTP());
        }
    }
}