package customStack;
public class Main {
    public static void main(String[] args) {

        CustomStack stack = new CustomStack();

        System.out.println(stack.isEmpty());
        stack.push(50);
        stack.push(4);
        stack.push(2);
        System.out.println(stack.peek());
        stack.push(44);
        System.out.println(stack.pop());
        System.out.println(stack.peek());
        System.out.println(stack.contains(5));
        System.out.println(stack.size());
        System.out.println(stack.isEmpty());


    }
}