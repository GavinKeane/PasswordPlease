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

    public String Encrypt(String Password){

    }

    private String Shiftcipher(String Password){

    }

}
