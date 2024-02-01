package AramaAlgoritmasi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class GenislikOncelikliAramaNew {
	
	public static void main(String[] args) {
		
		
		//Klavyeden veri almak için Scanner sınıfından bir nesne oluşturduk
		Scanner input = new Scanner(System.in);
		
		/*
		 * Graf'ı, düğümleri ve komşularını HasMap ile tutuyoruz
		 * HasMap, anahtar-değer şeklinde değerleri saklar
		 * örn: A düğümünün komşuları B ve C ise graf.get(A) => {B, C} olur
		 * 
		 */
		Map<String, Set<String>> graf = new HashMap<>();
		
		/*
		 * ziyaret edilen düğümleri HasSet ile tutuyoruz
		 * hasSet, tekrarlı değerli saklamaz
		 * örn: A,B,C düğümleri ziyaret ediliyorsa => ziyaretEdilenler = {A,B,C} olur
		 */
		Set<String> ziyaretEdileler= new HashSet<>();
		
		/*
		 * linkedLis verileri sıralı bir şekilde tutar
		 * ilk giren ilk çıkar mantığı ile çalışır
		 * örn: A,B,C düğümleri sırayla kuyruğa girerse => kuyruk = [A,B,C] olur
		 */
		Queue<String> kuyruk= new LinkedList<>();
		
		/*
		 * her düğüm geldiği bir öneki düğümün tutmak için hasMap kullanıyoruz
		 * örn: A düğümünden B düğümüne gidersek, parent.put(B,A) yaparız
		 */
		Map<String, String> parent = new HashMap<>();
		
		/*
		 * bulunan yol ArrayLis ile tutuyoruz ki ekleme veya çıkartma gibi güncellemelerde verilerimiz korunsun
		 * bu list verileri sıralı bir şekilde saklar
		 * örn: A,B,C düğümlerinden oluşan bir düğüm varsa =>> yol = [A,B,C] olur
		 */
		List<String> yol = new ArrayList<>();
		
		/*
		 * girdi alma işlemini kontrol etmek için bool değişkeni tanımlıyoruz
		 */
		
		boolean bitmediMi=true;
		
		/*
		 * başlangıç ve hedef düğümlerini alıyoruz
		 */
		System.out.print("başlangıç: ");
		String baslangic=input.next().toUpperCase();
		System.out.print("Hedef: ");
		String hedef=input.next().toUpperCase();
		
		/*
		 * kullanıcıdan köprleri alıyoruz
		 */
		while(bitmediMi) {
			
			//girilen köprüyü büyük harfe çeviriyoruz
			System.out.print("Köprü gir: ");
			String kopru= input.next().toUpperCase();
			
			/*
			 * koprüyü iki düğüme ayırıyoruz
			 * örn: AB köprüsü A ve B düğümlerini birleştirir
			 */
			String dugum1 = kopru.substring(0,1);// koprunun ilk harfi alınır
			String dugum2 = kopru.substring(1,2); // koprunun ikinci harfini alınır
			
			/*
			 * grafı güncelliyoruz
			 * eğer düğüm yoksa , yeni bir hasSet oluşturacağız
			 * eğer düğüm varsa , mevcut hasSet e komşu ekliyoruz
			 */
			
			//eğer graf.get(dugum1) null ise yeni bir hashSet oluştur
			graf.putIfAbsent(dugum1, new HashSet<>()); 
			// dugum1 in komşularına dugum2 yi ekliyoruz
			graf.get(dugum1).add(dugum2);
			
			//eğer graf.get(dugum2) null ise yeni bir hashSet oluşturuyoruz
			graf.putIfAbsent(dugum2, new HashSet<>());
			// düğüm2 nunu komşularına düğüm1 i ekliyoruz
			graf.get(dugum2).add(dugum1);
			
			
			//girdi alma işleminin bitip bitmediğini soruyoryuz
			System.out.print("Bittiyse \"Evet\" değilse \"Hayır\" yazın: ");
			if(input.next().equals("Evet"))
				bitmediMi=false;
		}
		
		// grafı ekrana yazdırıyoruz
		
		System.out.println("\n");
		
		//graf anahtarlarını döngüye alıyoruz
		for(String kpr:graf.keySet())
			System.err.println(kpr+"->"+graf.get(kpr));// her düğüm komşularını ekrana yazdırıyoruz
		
		//başlangıç düğümünü kuyruğa  ve ziyaret edilenlere ekliyoruz
		kuyruk.add(baslangic);// kuyruğun sonuna baslangıç düğümünü ekliyoruz
		ziyaretEdileler.add(baslangic);// ziyaret edilenlere başlangıç düğümlerini ekliyoruz
		
		//kuyruk boş olanadek bakmaya devam ediyoruz
		while(!kuyruk.isEmpty()) {
			//kuruğun başındaki düğümü alıp kuyruktan çıkarıyoruz
			String simdiki= kuyruk.poll();
			
			//hedefe ulaştıysak, dögüyü kırıyoruz
			if(simdiki.equals(hedef))
				break;
			// grafın şimdiki anahtarına karşılık gelen değerini alıyoruz
			Set<String> komsular = graf.get(simdiki);
			
			//her komşu için
			for(String komsu: komsular) {
				//eğer daha önce ziyaret edilmediyse 
				if (!ziyaretEdileler.contains(komsu)) {
					// kuyruğa ve ziyaret edilelere ekle
					kuyruk.add(komsu);// sonuna ekliyoruz
					ziyaretEdileler.add(komsu);// sonuna ekliyoruz
					//komşunun geldiği düğümü simdiki düğüm olarak kaydediyoruz
					parent.put(komsu, simdiki);
				}
			}
		}
		
		//eğer hedefe ulaşılamadıysa
		if(!parent.containsKey(hedef))
			System.out.println("Yol yoktur.");
		else {
			
			// tersten aldığımız yolu baslangıctan hedefe olacak sekilde dödürmek için Stack kullanıyoruz
			// hedeften başlayarak geldiği düğümü taküp ederek baslangıca gidiyoruz
			// stack mantığı son giren ilk çıkar olarak çalışır
			Stack<String> stack = new Stack<>();
			String simdiki = hedef;
			while (!simdiki.equals(baslangic)) {
				stack.push(simdiki);
				simdiki = parent.get(simdiki);
			}
			stack.push(baslangic);
			
			//şimdi satckteki vriyi arrayliste ekliyoruz
			while(!stack.isEmpty())
				yol.add(stack.pop());
			
			// yolu ekrana yazdırıyoruz
			System.out.println("Yol: "+yol);
		}
			
	}

}


/*
 * 
 * Açıklamalar:
 * 
 * Graf yapısını HashMap<String, HashSet<String>> olarak tanımladım. Bu şekilde,
 * her düğümün komşularını bir HashSet ile tutabiliyorum. HashSet, bir veri
 * yapısıdır. HashSet, bir veriyi sadece bir kez tutar. Yani, aynı veriyi tekrar
 * eklemeye çalışırsam, eklenmez. Bu, graf yapısında, aynı kenarı birden fazla
 * eklememeyi sağlar.
 * 
 * Kuyruk yapısını LinkedList<String> olarak tanımladım. Bu şekilde, sadece
 * düğüm ekleyebiliyorum. LinkedList, bir veri yapısıdır. LinkedList, verileri
 * birbirine bağlı düğümler halinde tutar. LinkedList, kuyruk yapısı için uygun
 * bir veri yapısıdır. Çünkü, LinkedList, başına ve sonuna veri eklemeyi ve
 * çıkarmayı kolaylaştırır. Kuyruk yapısı, ilk giren ilk çıkar (FIFO) mantığıyla
 * çalışır. Yani, kuyruğa ilk eklenen veri, kuyruktan ilk çıkar.
 * 
 * Parent HashMap’ini HashMap<String, String> olarak tanımladım. Bu şekilde, her
 * düğümün hangi düğümden geldiğini tutabiliyorum. Parent HashMap’i, yolu bulmak
 * için kullanıyorum. Yolu bulmak için, hedeften başlayarak, parent’ını takip
 * ederek başlangıca gidiyorum. Yolu ters çevirmek için Stack kullanıyorum.
 * Stack, bir veri yapısıdır. Stack, son giren ilk çıkar (LIFO) mantığıyla
 * çalışır. Yani, stack’e son eklenen veri, stack’ten ilk çıkar.
 * 
 * Yolu ArrayList<String> olarak tanımladım. Bu şekilde, yoldaki düğümleri bir
 * liste halinde tutabiliyorum. ArrayList, bir veri yapısıdır. ArrayList,
 * verileri bir dizi halinde tutar. ArrayList, veri eklemek, çıkarmak ve erişmek
 * için uygun bir veri yapısıdır.
 * 
 * 
 * 
 */




