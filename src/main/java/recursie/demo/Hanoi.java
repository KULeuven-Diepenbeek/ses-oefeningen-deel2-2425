package recursie.demo;

public class Hanoi {

    public static void hanoi(int n, String from, String to, String hulp) {
        if (n==0) return;
        hanoi(n-1, /* from */ from, /* to */ hulp, /* hulp */ to);
        System.out.println("Move from " + from + " to " + to);
        hanoi(n-1, hulp, to, from);
    }

    public static void main(String[] args) {
        hanoi(4, "A", "C", "B");
    }
}
