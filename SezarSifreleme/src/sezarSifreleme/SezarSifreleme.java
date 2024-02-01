package sezarSifreleme;
import java.io.*;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class SezarSifreleme {
	public static void main(String[] args) {
		int j = 1;
		String metin = "boğa".toLowerCase();
		while (j<29) {
			String a = "abcçdefgğhıijklmnoöprsştuüvyz"; // diziyi tek bir string yap
			System.out.print("Şifrelenecek metni gir: ");
			//metin = "boğa";// scan.next();
			System.out.print("Kaydırma aralığı: ");
			int key = j;// scan.nextInt(); // anahtarı gir
			String sifreliMetin = "";
			for (int i = 0; i < metin.length(); i++) {
				char c = metin.charAt(i); // metnin karakterini al
				int index = a.indexOf(c); // karakterin alfabe içindeki konumunu bul
				if (index != -1) { // karakter alfabe içindeyse
					index = (index + key) % a.length(); // yeni konumu hesapla
					c = a.charAt(index); // yeni karakteri al
				}
				sifreliMetin += c; // şifreli metne ekle
			}
			System.out.println(sifreliMetin.toUpperCase() + " " + j); // şifreli metni yazdır
			j++;
			
		}
	}
}
