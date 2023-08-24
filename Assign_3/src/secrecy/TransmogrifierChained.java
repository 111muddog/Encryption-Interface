package secrecy;
/*
Name: Angus Webb
Date: March 10, 2023
Class Description: Mutates pure ASCII characters with a randomly generated number, by performing a
XOR operation on that number and the index of the ASCII character.
 */
public class TransmogrifierChained implements Transmogrifier<String>{
    private int seed; //the seed that gets entered into the mixer object
    private java.util.Random mixer; //randomizer
    public TransmogrifierChained(int seed){
        this.seed = seed;
        mixer = new java.util.Random(seed);
    }
    public char mutate(char character) {
        //char mutate method: takes in a char and mutates it using "chained" binary comparison
        if (character > 127) { //throw exception if ASCII character is not compatible
            throw new InvalidCodePointException("Please enter a valid character with an ASCII index between 0 and 127.");
        }
        int XOR;
        int randomNumber = mixer.nextInt() & 0x7F; //generate random number between 0 and 127
        XOR = (character ^ randomNumber); //combine the character and random number with XOR operator
        return (char) XOR; //cast the new number to a char and return it
    }
    public String mutate(String toBeMutated){
        //String mutate method: takes in String and mutates it using "chained" binary comparison
        String mutatedProduct = ""; //new String
        char[] letters = toBeMutated.toCharArray(); //turn passed string into array of chars
        for (int i = 0; i < letters.length; i++) {
            mutatedProduct += mutate(letters[i]); //mutate each char while appending it to the new String
        }
        return mutatedProduct; //return the new String
    }
    public String getKey(){ //returns the seed used in the mixer
        return Integer.toString(seed);
    }
    public String getAntiKey(){ //returns the seed used in the mixer
        return Integer.toString(seed); //anti-key same as key
    }
}