import java.util.*;
import java.util.Random;
public class DualFA{
		public static String generatePhone(){
			String numbers = "0123456789";
      Random r = new Random();
      String phone = "";
      for (int i = 0; i < 10; i++)
      {
          phone += numbers.charAt(r.nextInt(numbers.length()));
      }
      return phone;
		}

    public static String GenerateOTP() {
        String numbers = "0123456789";
        Random r = new Random();
        String otp = "";
        for (int i = 0; i < 6; i++)
        {
            otp += numbers.charAt(r.nextInt(numbers.length()));
        }
        return otp;
    }
    public static void SendOTP(Request r)
    {
        String otp = GenerateOTP();
        Game.println("OTP Generated: " + otp);
        Game.println("Sending OTP...");
        Game.println("Waiting on response...");
        if (r.getHasFactor()){
            Game.println("OTP Response: " + otp);
        } else {
            Game.println("OTP Response: " + GenerateOTP());
        }
    }
}
