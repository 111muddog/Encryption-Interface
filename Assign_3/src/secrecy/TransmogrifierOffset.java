package secrecy;
/*
Name: Angus Webb
Date: March 10, 2023
Class Description: Mutates pure ASCII characters by shifting them down a certain amount of indexes based on a key
 */
public class TransmogrifierOffset implements Transmogrifier<Integer> {
    private Integer key; //amount of indexes the ASCII character will shift
    private Integer antikey; //negative inverse of key
    public TransmogrifierOffset(int key){
        this.key = key;
        antikey = key*-1;
    }
    public char mutate(char character) {
        //method mutate: takes in a char and shifts it down "key" amount of ASCII indexes.
        if (character > 127) { //if char is out of bounds throw exception
            throw new InvalidCodePointException("Please enter a valid character with an ASCII index between 0 and 127.");
        }
        int newIndex = character + key;
        if (newIndex > 127){ //if the new index is past 127...
            newIndex -= 128;  //...subtract new index by 128 so that it "loops" back to beginning of ASCII table
        } else if (newIndex < 0){ //if the new index is less than zero...
            newIndex += 128; //add 128
        }
        return (char)(newIndex);
    }
    public String mutate(String toBeMutated){
        //mutate method: takes in a string "toBeMutated" and mutates every char in it using offset
        String mutatedProduct = "";
        char[] letters = toBeMutated.toCharArray(); //convert the string to be mutated into an array of chars
        for (int i = 0; i < letters.length; i++) { //loop through the array char
            mutatedProduct += mutate(letters[i]); //mutate each char while adding it to a new string
        }
        return mutatedProduct; //return mutated string
    }
    public String getKey(){ //returns key used to mutate chars
        return key.toString();
    }
    public Integer getAntiKey(){ //returns antikey used to un-mutate chars
        return antikey;
    }
}