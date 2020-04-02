/*
  classes used for encrypt and generate the password.
  3/1 Yuxuan Hu - constructors
                - getPassword
  3/2 Yuxuan Hu - encrypt function



*/



import java.util.ArrayList;
import java.util.Random;
public class CypherMachine {

    public CypherMachine() {

    }

    public String getPassword(){
      ArrayList<String> pool1 = new ArrayList<String>();
      pool1.add("satisfaction");
      pool1.add("desk");
      pool1.add("clothes");
      pool1.add("charity");
      pool1.add("drama");
      pool1.add("finding");
      pool1.add("consequence");
      pool1.add("inspector");
      pool1.add("performance");
      pool1.add("population");
      pool1.add("restaurant");
      pool1.add("person");
      pool1.add("mode");
      pool1.add("collection");
      pool1.add("patience");
      pool1.add("chemistry");
      pool1.add("office");
      pool1.add("teaching");
      pool1.add("application");
      pool1.add("possibility");
      ArrayList<String> pool2 = new ArrayList<String>();
      pool2.add("advice");
      pool2.add("theory");
      pool2.add("interaction");
      pool2.add("signature");
      pool2.add("shirt");
      pool2.add("possibility");
      pool2.add("art");
      pool2.add("uncle");
      pool2.add("apartment");
      pool2.add("examination");
      pool2.add("response");
      pool2.add("obligation");
      pool2.add("celebration");
      pool2.add("baseball");
      pool2.add("classroom");
      pool2.add("player");
      pool2.add("marketing");
      pool2.add("inspector");
      pool2.add("love");
      pool2.add("city");
      ArrayList<String> pool3 = new ArrayList<String>();
      pool3.add("0");
      pool3.add("1");
      pool3.add("2");
      pool3.add("3");
      pool3.add("4");
      pool3.add("5");
      pool3.add("6");
      pool3.add("7");
      pool3.add("8");
      pool3.add("9");
      ArrayList<String> pool4 = new ArrayList<String>();
      pool4.add("!");
      pool4.add("@");
      pool4.add("#");
      pool4.add("$");
      pool4.add("%");
      pool4.add("^");
      pool4.add("&");
      pool4.add("*");
      pool4.add("(");
      pool4.add(")");
      pool4.add("_");
      pool4.add("-");
      pool4.add("=");
      pool4.add("+");
      pool4.add("[");
      pool4.add("]");
      pool4.add("?");
      pool4.add(">");
      pool4.add("<");
      pool4.add(";");
      pool4.add(":");
      pool4.add("{");
      pool4.add("}");
      Random rand = new Random();
      int ran1 = rand.nextInt(20);
      int ran2 = rand.nextInt(20);
      int ran3 = rand.nextInt(10);
      int ran4 = rand.nextInt(23);
      String password = pool1.get(ran1) + pool2.get(ran2) + pool3.get(ran3) + pool4.get(ran4);
      return password;
    }
    //Input is the Passward. Function would use a random way to encrypt the plaintext
    //Renturn the ciphertext
    public static String Encrypt(String Password){
      Random rand = new Random();
      String encrypt = "";
      int s = rand.nextInt(2);
      if(s == 0){
        encrypt = ShiftCipher(Password);
      }else if(s == 1){
        encrypt = MACipher(Password);
      }

      return encrypt;
    }

    //Shift the plaintext random positions down the alphabet
    //Input password
    //return ciphertext
    private static String ShiftCipher(String Password){
      String output = "";
      Random rand = new Random();
      int s = rand.nextInt(25);
      for (int i=0; i<Password.length(); i++)
        {
            char ch = (char)(((int)Password.charAt(i) + s - 97) % 26 + 97);
            output = output + ch;
        }
      return output;
    }

    private static String MACipher(String Password){
      String output = "";
      for (int i=0; i<Password.length(); i++)
        {
          char tmp = MAcase(Password.charAt(i));
          output += tmp;
        }
      return output;
    }

    private static char MAcase(char input){
      char output;
      if(input == 'a'){
        output = 'n';
      }else if(input == 'b'){
        output = 'o';
      }else if(input == 'c'){
        output = 'k';
      }else if(input == 'd'){
        output = 'e';
      }else if(input == 'e'){
        output = 'y';
      }else if(input == 'f'){
        output = 'a';
      }else if(input == 'g'){
        output = 'b';
      }else if(input == 'h'){
        output = 'c';
      }else if(input == 'i'){
        output = 'd';
      }else if(input == 'j'){
        output = 'f';
      }else if(input == 'k'){
        output = 'g';
      }else if(input == 'l'){
        output = 'h';
      }else if(input == 'm'){
        output = 'i';
      }else if(input == 'n'){
        output = 'j';
      }else if(input == 'o'){
        output = 'l';
      }else if(input == 'p'){
        output = 'm';
      }else if(input == 'q'){
        output = 'p';
      }else if(input == 'r'){
        output = 'q';
      }else if(input == 's'){
        output = 'r';
      }else if(input == 't'){
        output = 's';
      }else if(input == 'u'){
        output = 't';
      }else if(input == 'v'){
        output = 'u';
      }else if(input == 'w'){
        output = 'v';
      }else if(input == 'x'){
        output = 'w';
      }else if(input == 'y'){
        output = 'x';
      }else if(input == 'z'){
        output = 'z';
      }else if(input == '0'){
        output = '2';
      }else if(input == '1'){
        output = '3';
      }else if(input == '2'){
        output = '4';
      }else if(input == '3'){
        output = '5';
      }else if(input == '4'){
        output = '6';
      }else if(input == '5'){
        output = '7';
      }else if(input == '6'){
        output = '8';
      }else if(input == '7'){
        output = '9';
      }else if(input == '8'){
        output = '0';
      }else if(input == '9'){
        output = '1';
      }else{
        output = input;
      }
      return output;
    }
}
