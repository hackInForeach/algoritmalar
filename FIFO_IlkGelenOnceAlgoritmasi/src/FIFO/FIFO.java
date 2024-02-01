package FIFO;

import java.util.Random;
import java.util.Scanner;

public class FIFO {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		
		//-------------------------------------
		
		System.out.println("İşlem sayısı: ");
		int islemSayisi = scan.nextInt();
		
		//-------------------------------------
		
		String[] dizi= new String[islemSayisi];
		int sayac = 0;

		//-------------------------------------
		
		String islem="";
		String varisSuresi ="";
		String islemSuresi ="";
		
		//-------------------------------------
		
		// belli işlemde dizi doldurur
		while (sayac < islemSayisi) {

			
			//System.out.print("İşlem Adı: ");
			islem = String.valueOf((char)(65+(sayac))+"_"+(65+(sayac)));//scan.next();=> elle girmek için printleri aç ve bu kodu yerleştir /  String.valueOf((char)(65+(sayac))+"_"+(65+(sayac))) => random değer için bunu yerleştir
			
			//System.out.print("Varış Süresi: ");
			varisSuresi =String.valueOf(random(islemSayisi)); // scan.next(); => elle girmek için printleri aç ve bu kodu yerleştir / String.valueOf(random(islemSayisi)); => random değer için bunu yerleştir
			
			//System.out.print("İşlem Süresi: ");
			islemSuresi = String.valueOf(random(islemSayisi)+1);//scan.next();=> elle girmek için printleri aç ve bu kodu yerleştir /  String.valueOf(random(islemSayisi)+1); => random değer için bunu yerleştir
			
			//diziyi doldurmak için kullanılır
			dizi[sayac]= "\t| işlem adı: "+(char)80+"_"+islem+
						 "\t| Variş Süresi: "+varisSuresi+
						 "\t| İşlem Süresi: "+islemSuresi;
			
			sayac++;
		}
		
		//-------------------------------------
		
		
		//Oluşturulan düzensiz diziyi ekrana basar
		for (int i = 0; i < dizi.length; i++) {
			String string = dizi[i];
			System.out.println(string);
			
		}
		
		//-------------------------------------
		
		//işlenecek değer adlarını belirle
		
		String varis="";
		String sure="";
		String ad="";
		String temp="";
		
		double varis2=0;
		double sure2=0;
		
		int varisDegeri=0;
		int minIndeks=0;
		int minDeger=0;
		int jDeger=0;
		double indisNo=0;
		
		double toplamYanit = 0;
		double ortYanit=0;
		
		boolean ilkIslem=false;
		
		double[] isleme1 = new double[dizi.length-1];
		double[] varisIs1 = new double[dizi.length-1];
		//-------------------------------------
		
		// gelen processlerin doğru sıraya sokulma işlemi
		for (int i = 0; i < dizi.length; i++) {

			//"Variş Süresi: " ifadesinin ilk harfi olan V’nin dizideki konumu 15 ise, +14 ekleyerek 29. konumdan başlayabiliriz
			ad = dizi[i].substring(dizi[i].indexOf("İşlem adı: ") + 11, dizi[i].indexOf(" ", dizi[i].indexOf("İşlem adı: ") + 11));
			//varis = dizi[i].substring(dizi[i].indexOf("Variş Süresi: ") + 14, dizi[i].indexOf("\t", dizi[i].indexOf("Variş Süresi: ") + 14));
			sure = dizi[i].substring(dizi[i].indexOf("İşlem Süresi: ") + 14);
			//newDizi[i]=dizi[i];
			
			//-------------------------------------
			
			// dizi[i] elemanının içinde "Variş Süresi: " ifadesinin bulunduğu yerden başlayarak, sonraki bir tab karakterinin bulunduğu yere kadar olan alt diziyi alır
			varis = dizi[i].substring(dizi[i].indexOf("Variş Süresi: ") + 14, dizi[i].indexOf("\t", dizi[i].indexOf("Variş Süresi: ") + 14));
			
			// varis değerini int'e dönüştürür
			varisDegeri = Integer.parseInt(varis);
			
			
			//-------------------------------------
			
			
			boolean deger = i>0;
			for (int k = 0; k < dizi.length; k++) {
			   
				// en küçük elemanın indeksini tutan değişken
			    minIndeks = k;
			    
			    // en küçük elemanın değerini tutan değişken
			    minDeger = Integer.parseInt(dizi[k].substring(dizi[k].indexOf("Variş Süresi: ") + 14, dizi[k].indexOf("\t", dizi[k].indexOf("Variş Süresi: ") + 14)));
			    
			    // dizinin geri kalan kısmında en küçük elemanı arar
			    for (int j = k; j < dizi.length; j++) {
			    
			    	// j. elemanın değerini tutan değişken
			        jDeger = Integer.parseInt(dizi[j].substring(dizi[j].indexOf("Variş Süresi: ") + 14, dizi[j].indexOf("\t", dizi[j].indexOf("Variş Süresi: ") + 14)));
			        
			        // eğer j. eleman daha küçükse, minIndeks ve minDeger'k günceller
			        if (jDeger < minDeger) {
			            minIndeks = j;
			            minDeger = jDeger;
			        }
			    }
			    
			  //-------------------------------------
			    
			    // eğer en küçük eleman k'nıncı elemandan farklıysa, yerlerini değiştirir
			    if (minIndeks > k) {
			        temp = dizi[k];
			        dizi[k] = dizi[minIndeks];
			        dizi[minIndeks] = temp;
			    }
			    
			}
			
			//işlem kilit noktası(30-10-2023 20:00)
				if(i<dizi.length-1)
					//oluşan dizinini son elemanını alma
					isleme1[i]=Double.parseDouble(dizi[i].substring(dizi[i].indexOf("İşlem Süresi: ") + 14));
				if(i+1<dizi.length)
					//oluşan dizinin ilk elemanını alma
					varisIs1[i]=Double.parseDouble(dizi[i+1].substring(dizi[i+1].indexOf("Variş Süresi: ") + 14, dizi[i+1].indexOf("\t", dizi[i+1].indexOf("Variş Süresi: ") + 14)));
			
			
		}

		// işlem kısmı
	
		int say =0;
		for (int k = 0; k < dizi.length; k++) {
			
			
			
			/*
			if(dizi.length-1 != 0)
		    	indisNo = (dizi.length-(dizi.length-1));
		    else
		    	indisNo=0;
		    */
			
		    //hesaplama için düzenlenöiş indislerden gidilcek
		    sure2 =Double.parseDouble(dizi[k].substring(dizi[k].indexOf("İşlem Süresi: ") + 14));
			varis2 =Double.parseDouble(dizi[k].substring(dizi[k].indexOf("Variş Süresi: ") + 14, dizi[k].indexOf("\t", dizi[k].indexOf("Variş Süresi: ") + 14)));
		    
			//değerlerin bir öncekileri alınıp işlem yapabilmek için
			double sure2BirOnce =Double.parseDouble(dizi[k].substring(dizi[k].indexOf("İşlem Süresi: ") + 14));
			double varis2BirOnce =Double.parseDouble(dizi[k].substring(dizi[k].indexOf("Variş Süresi: ") + 14, dizi[k].indexOf("\t", dizi[k].indexOf("Variş Süresi: ") + 14)));
		    	
			
			
				//değerlerin bir sonraki indislerinden gilir
				
				//double sure2BirSonra  = Double.parseDouble(dizi[k+1].substring(dizi[k+1].indexOf("İşlem Süresi: ") + 14));
				//double varis2BirSonra = Double.parseDouble(dizi[k+1].substring(dizi[k+1].indexOf("Variş Süresi: ") + 14, dizi[k+1].indexOf("\t", dizi[k+1].indexOf("Variş Süresi: ") + 14)));
			
//			if (dizi[k]==dizi[k+1]) {
//				
//				sure2=0;
//				ilkIslem=true;
//			}
			
			// Asıl işlem: ilk process olarak gelen haricinde geri kalanın hesaplanması
			// 
			
			
			
			say++;
		}
		
		//işlem kilit noktası(30-10-2023 20:00)
		double is3 = 0;
		double varis3 = 0;
		for (int i = 0; i < dizi.length-1; i++) {
			//gerçeleşen iş:
			
			is3 += isleme1[i];
			varis3 = varisIs1[i];
			
			toplamYanit +=is3-varis3;
			
			//--------
			
		}
		
		
		//toplamYanit=fark; // dizi ye at for dön bak ne var
		
		//-------------------------------------
		System.out.println();
		//ekrana sıralanmış olarak diziyi basar
		for (int i = 0; i < dizi.length; i++) {
			
			String string = dizi[i];
			System.out.println(string);
			
		}
		
		//-------------------------------------
		
		ortYanit =toplamYanit/dizi.length;
		
		System.err.println();
		double ms = toplamYanit;
		double saniye = ms/1000;
		double dakika = saniye/60;
		double saat = dakika/60;
		dakika=dakika%60;
		saniye=saniye%60;
		ms=ms%1000;
		System.err.printf("\t|saat: %02d\n\t|dakika: %02d\n\t|saniye: %02d\n\t|milisaniye: %03d\n\n\tToplam yanıt süresi: %.2fms",(int)saat,(int)dakika,(int)saniye,(int)ms,toplamYanit);
		
		System.out.println("\n\n\n");
		
		ms = ortYanit;
		saniye = ms/1000;
		dakika = saniye/60;
		saat = dakika/60;
		dakika=dakika%60;
		saniye=saniye%60;
		ms=ms%1000;
		System.err.printf("\t|saat: %02d\n\t|dakika: %02d\n\t|saniye: %02d\n\t|milisaniye: %03d\n\n\tOrtalama yanıt süresi: %.2fms\n",(int)saat,(int)dakika,(int)saniye,(int)ms,ortYanit);

		
		
	}
	//random sayı oluiturur
	static int random(int aralik) {
		Random rand = new Random();
		return (int) rand.nextInt(aralik);
	}

}
