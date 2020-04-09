import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Question {

    String[][] list = new String[][] {
            { "Where are you from", "North America", "South America", "Asia",
                    "Europe" },
            { "Which sport do you like better ?", "Tennis ball", "Football",
                    "BasketBall", "Swimming" },
            { "What personality do you think you belong to?", "Architect",
                    "Logician", "Commander", "Debater" },
            { "What fruit do you like better", "Apple", "Banana", "Peach",
                    "Watermelon" },
            { "Which month are you born ? ", "January, " + "Feburary, March",
                    "April, May, June", "July, August, September",
                    "October, November, December" },
            { "What food do you like better", "Pasta", "Hamburgers",
                    "Fried Chicken", "Noodle" } };
   
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
                "Europe" };
        this.map.put("Where are you from", temp);
        temp = new String[] { "Tennis ball", "Football", "BasketBall",
                "Swimming" };
        this.map.put("Which sport do you like better ?", temp);
        temp = new String[] { "Architect", "Logician", "Commander", "Debater" };
        this.map.put("What personality do you think you belong to?", temp);
        temp = new String[] { "Apple", "Banana", "Peach", "Watermelon" };
        this.map.put("What fruit do you like better", temp);
        temp = new String[] { "January, " + "Feburary, March",
                "April, May, June", "July, August, September",
                "October, November, December" };
        this.map.put("Which month are you born ? ", temp);
        temp = new String[] { "Pasta", "Hamburgers", "Fried Chicken",
                "Noodle" };
        this.map.put("What food do you like better", temp);
        return this.map;

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
