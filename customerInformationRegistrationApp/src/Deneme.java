
/*
Çift bagli liste (doubly linked list) veri yapısı kullanımına dayanan ve müsterilerin soyada göre
alfabetik sıralı olarak tutuldugu bir müsteri bilgi kayıt uygulaması gelistirilmesi istenmektedir.
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Deneme { // Deneme sinifi

    public static void main(String[] args) {  // main methodu

        Scanner keyboard = new Scanner(System.in);
        int secim = 0;                           // Kullanicinin yapmıs oldugu sayisal secimi tutacak degisken.
        // Kullaniciya menu hakkinda bilgi verilir. 
        System.out.println("Gerceklestirmek istediginiz islem icin islemin karsisindaki numarayi tuslamaniz gerekmektedir.");
        System.out.printf(
                "Menu:%n"
                + "1- Dosyadan okuma yap.%n"
                + "2- Musteri ekle.%n"
                + "3- Musteriyi ekrana yazdir.%n"
                + "4- Musteriyi sil.%n"
                + "5- Tum kayitlari artan sirada yazdir.%n"
                + "6- Tum kayitlari azalan sirada yazdir.%n"
                + "7- Cikis.%n"
        );

        DoublyLinkedList list = new DoublyLinkedList(); // DoublyLinkedlİst sinifi tipinde, list adinda bir nesne olusturulur.
        boolean dosyaOkundu = false;                    // Dosyadaki verilerin tekrar okunmasi engellenir.
        while (secim != 7) {                            // Kullanicini girmis oldugu deger 7 olmadigi surece(7-Cikis) while dongusu doner. 

            System.out.print("Secimizi giriniz: ");     // Kullanicidan secim yapmasi istenilir.
            secim = keyboard.nextInt();
            System.out.println();

            // Kullanici 1 girdiyse bu blok calisir.
            if (secim == 1) {
                //Dosya okuma islemi gerceklestirilir.
                if (dosyaOkundu == false) {   // Dosya daha once okunmadiysa dosya okunur.
                    dosyaOkundu = true;
                    Scanner fileIn = null;
                    try {
                        fileIn = new Scanner(new FileInputStream("customer.txt"));  // "customer.txt" adli metin belgesinden veriler okunur.

                    } catch (FileNotFoundException e) {
                        System.out.println("Dosya ile ilgili bir hata olustu....");
                        System.exit(0);
                    }

                    while (fileIn.hasNext()) {             // Dosyada okunacak veri kalmayincaya kadar doner.
                        String line = fileIn.nextLine();   // Tum satir okunur.
                        String[] dizi = line.split(",");   // Satirdaki veriler virgullerden bolunur ve dizi adli bir array'e eklenir.

                        String adSoyad = dizi[0];          // dizi'nin ilk elemani ad-soyad bilgisini icerir. ( Satirin ilk verisi)
                        String adres = dizi[1];            // dizi'nin ikşnci elemanş adres bilgisini icerir. ( Satirin ikinci verisi)
                        ArrayList<String> telefonNum = new ArrayList<>();   // Musterinin kac tane telefoon numarasi oldugu bilinmefdigi icin telefon numaralarini ttucak bir ArrsyList acilir.

                        for (int i = 2; i < dizi.length; i++) {  //  Dosyadan okunan satirin ilk iki elemani musterinin ad-soyad ve adres bilgisi oldugu icin for dongusu ikiden baslatilmistir.
                            telefonNum.add(dizi[i]);  // Telefon numaralari ArrayListte eklenir.
                        }
                        CustomerInfo customer = new CustomerInfo(adSoyad, adres, telefonNum);   // Gelen bilgilerle bir customer nesnesi olusturulur.
                        list.addSortedByLastName(customer); // Olusturulan nesne soyada gore siralama yapan methoda parametre olarak gonderilir.

                    }
                    System.out.println("Dosyadan veriler basari ile okundu.");
                    fileIn.close();  // Dosya kapatilir...

                } else {           // Dosya daha once okunduysa kullanici bilgilendirilir.
                    System.out.println("Dosyadaki veriler daha once okunmustu. Bu veriler dosyadan yalnizca bir kez okunabilir.");
                }
                // Kullanici 2 girdiyse bu blok calisir.
            } else if (secim == 2) {
                Scanner scan = new Scanner(System.in);
                System.out.printf("Sirasiyla adınızı-soyadınızı, adresinizi ve telefon numara(lar)nizi giriniz: %n"
                        + "Bilgilendirme: Lutfen veri girisi sirasinda veriler arasina virgul koyunuz! %n");

                String data = scan.nextLine(); // Eklenmek istenen musteriyr ait tum bilgiler kullanicidan alinir.
                String[] musteriBilgileri = data.split(","); // Musteri bilgileri virgul ile ayrilarak girilir.
                String adSoyad = musteriBilgileri[0];  // Girilen ilk deger isim soyisimdir.
                String adres = musteriBilgileri[1];   // Girilen ikinci deger adres bilgisidir.
                //Geri kalan bilgiler musterinin telefon numaralaridir. Kac tane oldugu bilinmedigi icin ArrayList kullanilmistir.
                ArrayList<String> telefonNum = new ArrayList<>();
                for (int i = 2; i < musteriBilgileri.length; i++) {   // musteriBilgileri adli listenin ilk iki elemani musterinin ad-soyad ve adres bilgisi oldugu icin for dongusu ikiden baslatilmistir.
                    telefonNum.add(musteriBilgileri[i]);
                }
                CustomerInfo customer = new CustomerInfo(adSoyad, adres, telefonNum);  // CustomerInfo sinifindan bir customer nesnesi olusturulur.
                list.addSortedByLastName(customer);  // Olusturulan customer nesnesi soyada gore sirali ekleme yapan methoda parametre olarak gonderilir.

                // Kullanici 3 girdiyse bu blok calisir.   
            } else if (secim == 3) {
                Scanner scan = new Scanner(System.in);
                System.out.println("Program case sensitive ozelligine sahiptir. Bu durumu goz onunde bulundurunuz ! (Buyuk-kucuk harfe karsi duyarli)");
                System.out.print("Bilgileri yazdirmak istediginiz musterinin ad ve soyadini giriniz: ");
                String adSoyad = scan.nextLine();  // Klavyeden adli ve soyadi girilen musteri bilgileri yazdirilir.
                list.printCustomerInfo(adSoyad);   // Bu musterinin bilgilerini yazdiracak printCustomerInfo adli method cagirilir.

                // Kullanici 4 girdiyse bu blok calisir.
            } else if (secim == 4) {
                Scanner scan = new Scanner(System.in);
                System.out.println("Program case sensitive ozelligine sahiptir. Bu durumu  goz onunde bulundurunuz! (Buyuk-kucuk harfe karsi duyarli)");
                System.out.print("Silmek istediginiz musterinin ad ve soyadini giriniz:");
                String adSoyad = scan.nextLine(); // Klavyeden silinmesi istenen musterinin adi ve soyadi alinir.
                list.deleteCustomer(adSoyad);     // Bu musteriyi listeden silecek olan deleteCustomer adli method cagirilir.

                // Kullanici 5 girdiyse bu blok calisir.
            } else if (secim == 5) {
                list.printInAscendingOrder();   // LinkedList artan sirada ekrana yazdirilir.

                // Kullanici 6 girdiyse bu blok calisir.
            } else if (secim == 6) {
                list.printInDescendingOrder();  // LinkedList azalan sirada ekrana yazdirilir.
            } else {
                System.out.println("Lütfen 1-7 arasinda bir sayi tuslayiniz!");
            }

        } // while dongusu bitti...

        System.out.println("Programdan cikis yapiliyor...");   //Kullanici klavyeden 7 girmisse while dongusunden cikilir ve kullanici bilgilendirilir.

    }  // main methodu bitti...
} // Deneme sinifi bitti...
