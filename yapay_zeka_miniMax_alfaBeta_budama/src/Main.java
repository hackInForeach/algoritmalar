


public class Main {
    public static void main(String[] args) {

        MiniMax minmax = new MiniMax();

        //layer0
        Node node = new Node(-2); // Kök düğüm, maksi düğümüdür ve -1 değerine sahiptir

        //layer1
        node.left = new Node(-1); // Sol alt düğüm, mini düğümüdür ve -2 değerine sahiptir
        node.right = new Node(-1); // Sağ alt düğüm, mini düğümüdür ve -2 değerine sahiptir

        //Layer2
        node.left.left = new Node(5); // Sol alt düğümün en sol tarafı, değer düğümü
        node.left.right = new Node(3); // Sol alt düğümün en sağ tarafı, değer düğümü
        node.right.left = new Node(0); // Sağ alt düğümün en sol tarafı, değer düğümü
        node.right.right = new Node(1); // Sağ alt düğümün en sağ tarafı, değer düğümü

        // sonuc ekranı
        minmax.minimax(node);








        // Kök düğüm için minimax değerini hesapla ve yazdır
        //int minimaxValue = MiniMax.minimax(node);
        //System.out.println("The minimax value for the root node is: " + minimaxValue);


    }
}