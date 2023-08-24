package secrecy;
/*
Name: Angus Webb
Date: March 10, 2023
Class Description: Transmogrifier interface shows the public methods that exist in its implementations.
 */
public interface Transmogrifier<E> {
    char mutate(char character); //mutates a single char.
    String mutate(String string); //mutates a string of chars.
    String getKey(); //obtain key used to mutate chars.
    E getAntiKey(); //obtain anti-key used to reverse a mutated char.
}
