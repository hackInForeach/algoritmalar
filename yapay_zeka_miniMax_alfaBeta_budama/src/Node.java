public class Node {
    int value;// düğüm değeri
    Node left,right;// düğüm sol ve sağ alt düğümleri
    //constructor blok, düğüm oluşturmak için kurucu blok
    Node(int value){
        this.value=value;
        this.left= null;
        this.right=null;
    }
}
