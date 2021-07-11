
import java.util.ArrayList;

public class CustomerInfo { // CustomerInfo sinifi olusturuldu.

    // Gerekli olacak degiskenler
    private String adSoyad;               
    private String adres;
    private ArrayList<String> telefonNum;  // Kac tane telefon numarasi oldugu bilinmedigi icin telefon numaralari arraylist'de tutulur.

    // Parametresiz Constructor
    public CustomerInfo() {
        super();
        setAdSoyad("none");
        setAdres("none");
        this.telefonNum = new ArrayList<>();
    }

    // Parametreli Constructor
    public CustomerInfo(String adSoyad, String adres, ArrayList<String> telefonNum) {
        super();
        setAdSoyad(adSoyad);
        setAdres(adres);
        setTelefonNum(telefonNum);
    }

    // toString methodu yazilir.
    @Override
    public String toString() {   // Sirasiyla ad-soyad, adres bilgisi ve musterinin telefon numaralari ekrana yazdirilir.
        return String.format("Ad-soyad: %s  Adres: %s  Telefon Numaralari: %s ", adSoyad, adres, getTelefonNum().toString().replace("[", "").replace("]", ""));  // Telefon numaralari yazdirirken parantezler silindi.
    }
   
    // Getter and Setter methodlar
    public String getAdSoyad() {
        return adSoyad;
    }

    public void setAdSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public ArrayList<String> getTelefonNum() {
        return telefonNum;
    }

    public void setTelefonNum(ArrayList<String> telefonNum) {
        this.telefonNum = telefonNum;
    }
} // CustomerInfo sinifi bitti...

   