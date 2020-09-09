package world.shiyu.linkedlist;

import java.util.Stack;

public class TestStack {

    public static void main(String[] args){
        Stack<String> stack = new Stack();
        stack.add("Jack");
        stack.add("Tom");
        stack.add("Smith");

        while(stack.size() > 0){
            System.out.println(stack.pop());
        }
    }
}
