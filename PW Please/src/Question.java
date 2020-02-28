public class Question {
   
    String[] array = new String[] {
                    "What was your childhood nickname?",
                    "In what city did you meet your spouse/significant other?",
                    "What is the name of your favorite childhood friend?",
                    "What street did you live on in third grade?",
                     "What is your oldest sibling’s birthday month and year? (e.g., January 1900)",
                     "What is the middle name of your youngest child?",
                       
    };
    
    
    String que = "";
    public Question() {
        
       que = getRandomQuestion(array);
        
    }
    
    public String getRandomQuestion(String[] arr) {
    
        int num =(int)(Math.random() * arr.length);
        return array[num];
    }
    
}
