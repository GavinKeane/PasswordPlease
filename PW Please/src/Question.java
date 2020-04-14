import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class Question {

    public static String[][] list = new String[][] {
            { "What continent are you from?", "North America", "South America", "Asia",
                    "Europe", "Africa", "Australia", "Antarctica" },
            { "What is your favorite sport?", "Tennis ball", "Football",
                    "Basketball", "Swimming", "Soccer", "Volleyball", "Golf", "Cricket" },
            { "What personality do you think you belong to?", "Architect", "Logician", "Commander", "Debater", "Advocate", "Mediator", "Protagonist", "Campaigner", "Logistician", "Defender", "Executive", "Consul", "Virtuoso", "Adventurer", "Entrepreneur", "Entertainer" },
            { "What is your favorite fruit?", "Apple", "Banana", "Peach",
                    "Watermelon", "Pear", "Grape" },
            { "What month were you born in? ",  "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"},
            { "What is your favorite food?", "Pasta", "Hamburgers",
                    "Fried Chicken", "Noodle", "Pizza" } };
   	
   	public static Random rand = new Random();
   
    Map<String, String[]> map = new HashMap<>();
   
    Map<Employee, String[]> employeeMap = new HashMap<>();
   
    Map<Employee, String[]> randomAnswer = new HashMap<>();

    public Question() {

        this.map = this.constructQuestion();
        this.list = this.list;
        this.employeeMap = this.constructEmployeeQuestion();
        this.randomAnswer = this.generateRandomAnswers();
    }

    // Generate a security question and a correct answer for each employee randomly
    public Map<Employee, String[]> constructEmployeeQuestion() {
        int cur = 1;

        for (Employee e : Employee.employees) {
            String[] strs = new String[] {};
            strs[0] = this.list[cur - 1][0];
            strs[1] = this.list[cur - 1][(int) (Math.random() * 6 + 1)];
            this.employeeMap.put(e, strs);
            cur++;
            if (cur == 6) {
                cur = 1;
            }
        }
        // TODO Auto-generated method stub
        return this.employeeMap;

    }

    // Generate a random answer to the security question for each employee
    // each employee's security question in randomAnswer map is same as that in employeeMap
    public Map<Employee, String[]> generateRandomAnswers() {
        int cur = 1;

        for (Employee e : Employee.employees) {
            String[] strs = new String[] {};
            strs[0] = this.list[cur - 1][0];
            strs[1] = this.list[cur - 1][(int) (Math.random() * 6 + 1)];
            this.randomAnswer.put(e, strs);
            cur++;
            if (cur == 6) {
                cur = 1;
            }
        }
        // TODO Auto-generated method stub
        return this.randomAnswer;

    }

    // construct question and answer lists
    public Map<String, String[]> constructQuestion() {
        // TODO Auto-generated method stub
        String[] temp = new String[] { "North America", "South America", "Asia",
                "Europe", "Africa", "Antarctica" };
        this.map.put("Where are you from", temp);
        temp = new String[] { "Tennis ball", "Football", "BasketBall",
                "Swimming" };
        this.map.put("Which sport do you like better ?", temp);
        temp = new String[] { "Architect", "Logician", "Commander", "Debater", "Advocate", "Mediator", "Protagonist", "Campaigner", "Logistician", "Defender", "Executive", "Consul", "Virtuoso", "Adventurer", "Entrepreneur", "Entertainer" };
        this.map.put("What personality do you think you belong to?", temp);
        temp = new String[] { "Apple", "Banana", "Peach", "Watermelon" };
        this.map.put("What fruit do you like better", temp);
        temp = new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
        this.map.put("Which month are you born ? ", temp);
        temp = new String[] { "Pasta", "Hamburgers", "Fried Chicken",
                "Noodle" };
        this.map.put("What food do you like better", temp);
        return this.map;

    }

		public static String[] getQA(){
			int r = rand.nextInt(6);
			String q = list[r][0];
			String a = list[r][rand.nextInt(list[r].length-1)+1];
			String[] s = {q, a};
			return s;
		}
		
		public static String getWrongAnswer(String q, String a){
			int index = -1;
			for (int i=0;i<list.length;i++) {
					if (list[i][0].equals(q)) {
						  index = i;
						  break;
					}
			}
			
			String wrong = a;
			
			if(index == -1){
				wrong = "idk";
			}else{
				while(wrong.equals(a)){
					wrong = list[index][rand.nextInt(list[index].length-1)+1];
				}
			}
			return wrong;
		}
    //for debugging, print each employee's firstname , his  security question and answer
    public void printStoredCorrectAnswer() {
        for (Entry<Employee, String[]> e : this.employeeMap.entrySet()) {
            System.out.println(e.getKey().firstnames + " " + e.getValue()[0]
                    + e.getValue()[1]);
        }
    }

    //for debugging, print each employee's firstname , his  security question and random Answer
    public void printRandomAnswer() {
        for (Entry<Employee, String[]> e : this.randomAnswer.entrySet()) {
            System.out.println(e.getKey().firstnames + " " + e.getValue()[0]
                    + e.getValue()[1]);
        }
    }

    //for debugging, print each security question and choices
    public void printAnswerLists() {
        for (String[] s : this.list) {
            for (String ss : s) {
                System.out.println(ss);
            }
        }
    }
}
