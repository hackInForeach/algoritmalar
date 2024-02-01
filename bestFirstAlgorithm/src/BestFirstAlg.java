import java.util.Scanner;

public class BestFirstAlg {

    public static void enIyiYerAlgoritmasi(int[] blokBoyutu, int[] islemBoyutu) {

        int[] yerlestirme = new int[islemBoyutu.length]; // her işlem için yerleştirilen blok numarasını tutar
        int[] blokBoyut2 = new int[blokBoyutu.length]; // her işlem için yerleştirilen bloğun orijinal bloğunu tutar

        for (int i = 0; i < islemBoyutu.length; i++) {
            yerlestirme[i] = -1; // tüm blok -1 ile doldurulu ve henüz yerleştirme işlemi yapılmadı demek
            blokBoyut2[i] = -1;// bu dizi de aynı şekilde
        }
        for (int i = 0; i < islemBoyutu.length; i++) {
            int enUygun = -1; // en uygun blok numarası bu, eşlem işleminde dizide hala -1 varsa uygun yer var demek
            for (int j = 0; j < blokBoyutu.length; j++) {
                if (blokBoyutu[j] >= islemBoyutu[i]) {// bu blok işlemi karşılıyor mu ??
                    if (enUygun == -1) {// uygun yer daha bulun madıysa
                        enUygun = j;//bir sonrakine bak
                    } else if (blokBoyutu[enUygun] > blokBoyutu[j]
                    ) {//daha küçük bir blok bulduysa bu bloğu
                        enUygun = j; // en uygun olarak ata
                    }
                }
            }
            if (enUygun != -1) { // uygun blok blunduysa
                yerlestirme[i] = enUygun; // işlemi bu bloğa ata
                blokBoyut2[i] = blokBoyutu[enUygun];// bloğun orijinal boyutunu kaydet
                //ve
                blokBoyutu[enUygun] -= islemBoyutu[i];//bloğun boyutunu güncelle
            }
        }
        System.out.println("İşlem No.\tİşlem Boyutu\tYerleştirilen Blok");
        for (int i = 0; i < islemBoyutu.length; i++) {
            System.out.print(" " + (i + 1) + "\t\t\t" + islemBoyutu[i] + "\t\t");
            if (yerlestirme[i] != -1) {
                System.out.println("\t\t" + blokBoyut2[i]);
            } else {
                System.out.println("Yerleştirilemedi");
            }
        }
    }

    public static void main(String[] argumans) {

        Scanner scan = new Scanner(System.in);
        System.out.println("Lütfen bir \"Blok Boyutunuzu\" giri: ");
        int blokBoyut = scan.nextInt();
        System.out.println("Lütfen bir \"İşlem Boyutunuzu\" giri: ");
        int islemBoyut = scan.nextInt();

        int[] blockSize = new int[blokBoyut];
        int[] procesSize = new int[islemBoyut];

        for (int i = 0; i < blockSize.length; i++) {
            System.out.print((i + 1) + ". Blok: ");
            blockSize[i] = scan.nextInt();
        }
        for (int i = 0; i < procesSize.length; i++) {
            System.out.print((i + 1) + ". İşlem: ");
            procesSize[i] = scan.nextInt();
        }

        enIyiYerAlgoritmasi(blockSize, procesSize);
    }
}
