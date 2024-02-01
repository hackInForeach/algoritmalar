import java.util.Scanner;

public class UzunluguAyarlanabilirKasaSifreleme {
    public static void main(String[] args) {

        //şifrenin hane sayısını belirt
        Scanner input=new Scanner(System.in);
        System.out.print("Oluşturulacak şifre kaç haneli olsun: ");
        int uzunluk= input.nextInt();

        //orijinal şifre göster
        int[] kriptosuz = sifre(uzunluk);
        System.out.print("Kriptosuz: ");
        for (int i=0;i<kriptosuz.length;i++){
            System.out.print(kriptosuz[i]+", ");

        }

        boolean yanlisMi= true;
        int say;

        System.out.println();
        //kiriptolu şifre göster (girilmesi gereken)
        int[] kriptolu = karistir(kriptosuz);
        System.out.print("Kriptolu(girilmesi gereken): ");
        for (int i=0;i<kriptolu.length;i++){
            System.out.print(kriptolu[i]+", ");
        }
        System.out.println();

        while (yanlisMi){
            System.out.println("Şifreyi Girin(teker teker): ");
            say=0;
            for (int i=0;i<kriptolu.length;i++){
                if (input.nextInt()==kriptolu[i]){
                    System.out.println(i+1+". Şifre doğru");
                    say++;
                }else {
                    System.out.println(i+1+". Şifre yanliş");
                }
            }if (say==uzunluk){
                System.out.println("Şifre doğru");
                for (int i=0;i<kriptolu.length;i++){
                    System.out.printf("%d, ",kriptolu[i]);
                }
                yanlisMi=false;
            }else {
                System.out.println("Şifre yanlış");
            }
        }
    }
    public static int[] sifre(int n) {

        int[] dizi = new int[n];
        for (int i = 0; i < dizi.length; i++) {
            dizi[i] = (int) (100 * Math.random());
        }

        return dizi;
    }
    public static int[] karistir(int[] n) {

        for (int i = 0; i < n.length; i++) {
            int rasgeleIndis = (int) (Math.random() * n.length);
            int tut = n[i];
            n[i] = n[rasgeleIndis];
            n[rasgeleIndis] = tut;
        }
        return n;
    }
}