public class MiniMax {
    // Bir düğümün değer düğüm olup olmadığını kontrol eden bir yöntem
    public static boolean isLeaf(Node node) {
        return (node.left == null && node.right == null);
    }

    // Bir düğümün maksi düğümü olup olmadığını kontrol eden bir yöntem
    public static boolean isMax(Node node) {
        return (node.value == -1);
    }

    // Bir düğümün mini düğümü olup olmadığını kontrol eden bir yöntem
    public static boolean isMin(Node node) {
        return (node.value == -2);
    }

    // Bir düğümün değerini döndüren bir yöntem
    public static int utility(Node node) {
        return node.value;
    }

    // Minimax algoritması
    public static int minimax(Node node) {
        // Eğer düğüm değer düğüm ise, değerini döndür
        if (isLeaf(node)) {
            System.out.println("The minimax value for the leaf node " + node.value + " is: " + utility(node)); // Düğümün değerini yazdır
            return utility(node);
        }

        // Eğer düğüm maksimizasyon düğümü ise, sol ve sağ alt düğümlerinin minimax değerlerinin maksimumunu döndür
        if (isMax(node)) {
            int max = Math.max(minimax(node.left), minimax(node.right)); // Sol ve sağ alt düğümlerinin minimax değerlerinin maksimumunu hesapla
            System.out.println("The minimax value for the max node is: " + max); // Maksimizasyon düğümünün minimax değerini yazdır
            return max;
        }

        // Eğer düğüm minimizasyon düğümü ise, sol ve sağ alt düğümlerinin minimax değerlerinin minimumunu döndür
        if (isMin(node)) {
            int min = Math.min(minimax(node.left), minimax(node.right)); // Sol ve sağ alt düğümlerinin minimax değerlerinin minimumunu hesapla
            System.out.println("The minimax value for the min node is: " + min); // Minimizasyon düğümünün minimax değerini yazdır
            return min;
        }

        // Hiçbir koşul sağlanmazsa, -1 döndür
        return -1;
    }
}

