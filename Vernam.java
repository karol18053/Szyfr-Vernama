package vernam;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Vernam {

	static List<Integer> zakodowane = new ArrayList<Integer>();

	static void Licz(Byte t, int klucz) {

		// Byte klucz = 'a';
		int c = klucz ^ t;
		int m = c ^ klucz;

		zakodowane.add(c);

		System.out.println("Zakodowane: " + c);
		System.out.println("Zdekodowane kod ASCII: " + m);
		System.out.println("Zdekodowany znak: " + (char) m);

	}

	public class Random {

	}

	public static void main(String[] args) {

		Scanner skan = new Scanner(System.in);

		System.out.println("Podaj wiadomoœc do zaszyfrowania: ");

		String tekst = skan.nextLine();

		System.out.println("Wiadomoœc przekazana do zakodowania: " + tekst);
		//////////////////////////////
		SecureRandom r = new SecureRandom();
		r.nextInt();
		int bitsize = 512;
		BigInteger nval = BlumBlumShub.generateN(bitsize, r);

		// now get a seed
		byte[] seed = new byte[bitsize / 8];
		r.nextBytes(seed);

		// now create an instance of BlumBlumShub
		BlumBlumShub bbs = new BlumBlumShub(nval, seed);
		//////////////////////////////
		int klucz = 0;
		int znakInt = 0;
		char znak;
		for (int i = 0; i < tekst.length(); i++) {

			znak = tekst.charAt(i);
			znakInt = znak;
			klucz = bbs.next(8);
			System.out.println("Pobrany znak: " + znak);
			System.out.println("Pobrany znak kod ASCI: " + znakInt);
			System.out.println("Klucz: " + klucz);

			Licz((byte) znakInt, klucz);

		}

		System.out.println("Zakodowanwa wiadomoœæ: ");
		for (int i = 0; i < zakodowane.size(); i++) {
			System.out.print(zakodowane.get(i));
		}

		skan.close();

	}

}
