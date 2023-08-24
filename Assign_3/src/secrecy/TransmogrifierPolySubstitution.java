package secrecy;
/*
Name: Angus Webb
Date: March 10, 2023
Class Description: Mutates ASCII letters using a keyword, by shifting the letter a certain amount based on the
letters in the keyword.
 */
public class TransmogrifierPolySubstitution implements Transmogrifier<String>{
    private String key; //the keyword that gets repeated
    private int current = 0; //keeps track of which letter we're on in the key
    private char keyIndex[]; //an array of each letter in the keyword
    public TransmogrifierPolySubstitution(String key){
        this.key = key.toUpperCase(); //set key to uppercase
        keyIndex = key.toCharArray(); //fill the keyIndex array with the key's individual letters
        if (!checkKey()) { //if a non-letter is detected in the key, throw exception
            throw new InvalidCodePointException("Non-letter character detected. Please use only letters.");
        }
    }
    public char mutate(char letter) {
        //mutate char method: takes in a char (letter) and mutates it using polysubstitution

        if (letter > 127) { //if char is out of bounds, throw exception
            throw new InvalidCodePointException("Please enter a valid alphabetical character.");
        }
        if (!isLetter(letter)){ //if the entered char is not a letter, return the char (does not get mutated)
            return letter;
        }
        letter = Character.toUpperCase(letter); //ensure letter to uppercase
        if (current >= keyIndex.length){ //check if we are past the key's length...
            current = 0; //...if so, set key index to 0 and start again
        }
        int numToAdd = (keyIndex[current]-65); //change key's letter to its corresponding alphabet index between 0-25
        char newChar = (char)(letter + numToAdd); //add the key's letter index to the inputted letter
        if (newChar > 90){ //if this causes the letter to go out of bounds...
            newChar -=26; //...subtract by 26 to start at the beginning of the alphabet
        }
        current++; //increment key index. Next time method is called, it will use the next letter of the keyword
        return newChar;
    }
    public String mutate(String toBeMutated){
        //mutate String method: takes in a String and mutates it using polysubstitution
        String mutatedProduct = "";
        char[] letters = toBeMutated.toCharArray(); //convert String to a char array
        for (int i = 0; i < letters.length; i++) {
            mutatedProduct += mutate(letters[i]); //perform mutation on each char while appending it to product
        }
        return mutatedProduct; //return finished mutated product
    }
    private boolean checkKey(){
        //method checkKey: ensures that key does not have any non-alphabetical chars in it.
        for (int i = 0; i < keyIndex.length; i++) { //loop through char array
            if (!isLetter(keyIndex[i])){ //if char is not a letter, return false
                return false;
            }
        }
        return true; //return true if no non-alphabetical chars were detected
    }
    public String getKey(){ //method getKey: returns key
        return key;
    }
    public String getAntiKey(){ //method getAntiKey: returns anti-key
        String antikey = "";
        for (int i = 0; i < keyIndex.length; i++) { //loop through each char in the key
            if (keyIndex[i]==65){ //if char is "A", keep it the same since anti-key for A is A
                antikey+=keyIndex[i];
            } else {
                int numToSubtract = keyIndex[i]-65; //change key's letter to its corresponding alphabet index between 0-25
                int charAlphaIndex = 26 - numToSubtract; //get new char's alphabetical index by subtracting old char's index from 26
                char newChar = (char)(charAlphaIndex + 65); //add 65 to get back to its actual ASCII index and cast to char
                antikey += newChar; //add new char to antikey string
            }
        }
        return antikey; //return completed antikey
    }
    private boolean isLetter(char character){
        //method isLetter: takes in a character, returns true if it's an alphabetical letter, false otherwise
        if ((character >=65 && character <=90) || (character >=97 && character <=122)){
            return true;
        } else return false;
    }
}