
import java.util.Scanner;

public class DoublyLinkedList {  // DoublyLinkedList classi olusturuldu.

    Node head;  // LinkedList'in basini gosterecek Node tipindeki degisken
    Node tail;  // LinkedList'in sonunu gösterecek Node tipindeki degisken

    private class Node { // inner class olan Node classi olusturuldu.

        private CustomerInfo data;   // CustomerInfo tipindeki degisken
        private Node previous;       // Onceki bag sahasi icin gerekli olan Node tipindeki degisken
        private Node next;           // Sonraki bag sahasi icin gerekli olan Node tipindeki degisken  

        // Parametreli Constructor 
        public Node(CustomerInfo data, Node next, Node previous) {
            this.previous = previous;
            this.next = next;
            this.data = new CustomerInfo(data.getAdSoyad(), data.getAdres(), data.getTelefonNum());
        }
    } // Node classi bitti...

    // DoublyLinkedList classinin Parametresiz Constructori
    public DoublyLinkedList() {
        head = null;  // head null yapilir.
        tail = null;  // tail null yapilir.
    }

    public boolean isEmpty() {  // Listenin bos olup olmadigini kontrol eden method.
        return head == null;    // Listenin head'i null ise liste bostur, true dondurur.
    }

    public Node find(String adSoyad) {  // Adi ve soyadi verilen bir musterinin listede olup olmadigini bulan method.
        Node temp = head;               // Head'den baslanarak liste dolsilir.
        while (temp != null) {
            if (temp.data.getAdSoyad().equals(adSoyad)) {  // Listenin suanki elemaninin ad-soyadi parametre olarak gelen ad-soyada esi mi kontrol edilir.
                return temp;  // Esitse bu isimde bir musteri bulunmaktadir. Bu musterinin liste adresi dondurulur.
            }
            temp = temp.next; // Liste sonuna ilerlenir.
        }
        return null;  // Eger eleman bulunamazsa null dondurulur.
    }

    public void printCustomerInfo(String adSoyad) {  // Ad ve soyadi verilen musterinin bilgilerini yazdirmayi saglayan method.
        if (find(adSoyad) != null) {                 // Eger bu ad-soyada sahip bir musteri var ise yazdirilir.
            System.out.println(find(adSoyad).data.toString());
        } else {                                     // Bu ad-soyada sahip bir musteri yok ise kullanici bilgilendirilir.
            System.out.println("Bu isme sahip bir musteri listede bulunmamaktadir.");
        }
    }

    public String findLastName(String adSoyad) {  // Verilen bir ad-soyad uzerinden son string soyad olacak sekilde soyadi bulup donduren method.
        Scanner scan = new Scanner(adSoyad);
        String soyad = "";                        // Bos string
        while (scan.hasNext()) {  // ad-soyad degiskeninde okunacak bir deger olana kadar doner.
            soyad = scan.next();
        }
        return soyad;
    }

    public String findFirstName(String adSoyad) { // Verilen bir ad-soyad uzerinden son string soyad ise geri kalan tum strignler ad olacak sekilde adi bulup donduren method.
        String soyad = findLastName(adSoyad); // Once soyad bulunur.
        String isim = adSoyad.substring(0, adSoyad.length() - soyad.length());  // Isim bulunur.
        return isim;
    }

    public void addSortedByLastName(CustomerInfo data) { // Verileri soyada gore alfabetik olarak sirali sekilde listeye ekleyen method.
        String soyad = findLastName(data.getAdSoyad());  // Gelen data'nin (customerInfo nesnesi) soyadi "findLatName" adli method ile bulunur ve soyad degiskenine atanir.
        String isim = findFirstName(data.getAdSoyad());  // Gelen data'nin (customerInfo nesnesi) adi "findFirstName" adli method ile bulunur ve isim degiskenine atanir.

        //*******************************************************************************************************************************************************************
        //Basa ekleme durumu 
        if (isEmpty()) {  // Liste bos ise head ve tail ayni olacak sekilde yeni bir dugum olusturulur.
            head = tail = new Node(data, null, head); // Bu dugumun previous'u null'ı ,next'i head'i gosterir.( Bu dugum ilk dugum olacagindan head = null'dir.)     
        } /* Parametre olarak gelen musterinin soyadi head'in soyadindan kucuk ise bu durumda bu kisi  head'in onune yerlesmelidir 
         ya da
         Parametre olarak gelen musterinin soyadi headin soyadina esitse ve parametre olarak gelen musterinin adi head'in adindan 
       alfabetik olarak once geliyorsa bu musteri head'in onune eklenmelidir. (Ornegin paramete olarak gelen musteri adi-soyadi: Ahmet Demir ,Head'in adi soyadi:Ali Demir)
         */ else if ((soyad.compareTo(findLastName(head.data.getAdSoyad())) < 0) || soyad.compareTo(findLastName(head.data.getAdSoyad())) == 0 && isim.compareTo(findFirstName(head.data.getAdSoyad())) < 0) {
            head = head.previous = new Node(data, head, null); // Artik yeni head bu yeni dugum olmalidir. Eski head'in previous'i da bu dugumu gostermelidir. Bu yeni dugumun previous'i null, next'i eski head olacaktir.
        } /*
       Parametre olarak gelen musterinin soyadi head'in soyadina esit ve adi head'in adindan buyukse(alfabetik olarak daha sonra gelmeliyse) bu durumda
       head'den sonra bu soyada sahip baska birilerinin olup olmadigi arastirilmalidir.
         */ else if (soyad.compareTo(findLastName(head.data.getAdSoyad())) == 0 && isim.compareTo(findFirstName(head.data.getAdSoyad())) > 0) {
            if ((soyad.compareTo(findLastName(head.next.data.getAdSoyad())) == 0)) {  // head.next bu soyada sahipse eger parametre olarak gelen soyadi bu kisiyle de karsilastirmak gerekir.

                Node temp = head.next;  // bir temp degiskeni tanimlayarak bunu head'in next'i olarak atayalim.
                Node oncekiNode = head; // oncekiNode da head olsun.
                // Bu soyada sahip olan ve ismi bizim eklemek istedigimiz isimden buyuk olan bir isim-soyisim bulana kadar while dongusu donmelidir.
                while (isim.compareTo(findFirstName(temp.data.getAdSoyad())) >= 0 && soyad.compareTo(findLastName(temp.data.getAdSoyad())) == 0) {
                    // temp while bittiginde bize bu soyada sahip olup ismi bizim eklemek istedigimiz isimden alfabetik olarak daha sonra gelen ilk elemanin yerini gosterir.
                    if (isim.compareTo(findFirstName(temp.data.getAdSoyad())) == 0 && soyad.compareTo(findLastName(temp.data.getAdSoyad())) == 0) {  // temp'in hem adi hem soyadi gelen ad soyada esitse demekki bu ad soyada sahip biri listede vardir.
                        System.out.println(isim + "" + soyad + " adinda biri bulunmaktadir. Bu sebeple bu isim listeye tekrar eklenemez.");  // Bu nedenle listeye bu isim eklenemez.
                        return;
                    } else {
                        oncekiNode = temp;
                        temp = temp.next;
                    }
                }
                // oncekiNode dedigimiz temp'in previous'u idi. Temp'in previous'u ile temp arasina yeni dugum eklenmelidir.
                // Yeni dugumun next'i temp'i, previous'u oncekiNode dedigimiz temp'in previous'unu gosterir.
                oncekiNode.next = temp.previous = new Node(data, temp, oncekiNode);

            } else {  // head dısında eklemek istedigimiz kisinin soyadina sahip baska birinin olmadigi durumda else blogu calisir.
                if (isim.compareTo(findFirstName(head.data.getAdSoyad())) > 0) {  // Eklemek istedigimiz kisinin adi alfabetik olarak daha sonra geliyor ise bu kisi head'in next'ine yerlesmelidir.
                    head.next = head.next.previous = new Node(data, head.next, head);
                } else { // Eklemek istedigimiz kisinin adi ve soyadi head ile ayni ise bu kisi listeye eklenemez.
                    System.out.println(isim + "" + soyad + " adinda biri bulunmaktadir. Bu sebeple bu isim listeye tekrar eklenemez.");
                    return;
                }
            }

        } else if (soyad.compareTo(findLastName(head.data.getAdSoyad())) == 0 && isim.compareTo(findFirstName(head.data.getAdSoyad())) == 0) {  //Parametre olarak gonderilen isim ve soyisim head'in isim ve soyismine esitse bu durumda bu isim listeye eklenemez.
            System.out.println(isim + "" + soyad + " adinda biri bulunmaktadir. Bu sebeple bu isim listeye tekrar eklenemez.");
        }
        //****************************************************************************************************************************************************************
        // Sona Ekleme Durumu
        /*
         Parametre olarak gonderilen soyad tail'in soyadindan alfabetik olarak sonra geliyorsa bu durumda parametre olarak gelen soyad tail yapilmali yani sona eklenmelidir.
         veya 
         parametre olarak gelen soyad ve tail'in soyadi ayni ancak eklenmek istenen kisinin adi tail'in adindan alfabetik olarak daha sonra geliyorsa bu durumda da 
         listeye eklenmek istenen mustrinin adi en sona yerlesmelidir. Yani tail olmalidir.
        */
        else if ((soyad.compareTo(findLastName(tail.data.getAdSoyad())) > 0) || soyad.compareTo(findLastName(tail.data.getAdSoyad())) == 0 && isim.compareTo(findFirstName(tail.data.getAdSoyad())) > 0) {
            tail = tail.next = new Node(data, null, tail);

        /*
            Parametere olarak gelen soyad tail'in soyadina esit ve parametre olarak gelen isim tail'in isminden alfabetik olarak daha once geliyorsa bu durumunda
            tail'den baska bu soyada sahip musterilerin varligi incelenmelidir.
            Var ise bu durumda gonderilen musterinin hangi noktada listeye baglanmasi gerektigi bulunmalidir.
            */
        } else if (soyad.compareTo(findLastName(tail.data.getAdSoyad())) == 0 && isim.compareTo(findFirstName(tail.data.getAdSoyad())) < 0) {

            if ((soyad.compareTo(findLastName(tail.previous.data.getAdSoyad())) == 0)) {  // tail'in oncesindeki dugumun ad soyad bilgisi eklenmek istenen musterinin soyadina esitse yani bu soyada sahip tail'den baska birileri de bu soyada sahip ise bu blok calisir.

                Node temp = tail.previous;   // tail ile kontrol etmistir. temp degiskenini tail'den geriye liseyi dolasacak sekilde ayarlayabiliriz.
                Node sonrakiNode = temp.next;// sonraki dugum su an tail oluyor.
                while (isim.compareTo(findFirstName(temp.data.getAdSoyad())) <= 0 && soyad.compareTo(findLastName(temp.data.getAdSoyad())) == 0) {  // Parametre olarak gelen musteri ismi temp degiskeninin isminden kucuk ya da esitse ve soyadi temp'in soyadina esitse bu blok calisir.

                    if ((isim.compareTo(findFirstName(temp.data.getAdSoyad())) == 0)) {  // Eklemek istedigimiz isim, listede bu soyada sahip olup aynı zamanda bu isme sahip herhangi biri var ise bu durumda listeye eklenemez.
                        System.out.println(isim + "" + soyad + " adinda biri bulunmaktadir. Bu sebeple bu isim listeye tekrar eklenemez."); // Kullanici bilgilendirilir.
                        return;
                    } else {  // Bu soyada sahip olanlar arasinda eklemek istedigimiz isme sahip biri yok ise eklemek istedigimiz ismin yeri bulunmaya calisilir.
                        temp = temp.previous;
                        sonrakiNode = temp.next;
                    }

                }// while dongusu, eklenmek istenen isim temp'in gosterdigi bir isimden artik kucuk olmadigi yerde biter.
                temp.next = sonrakiNode.previous = new Node(data, sonrakiNode, temp);  // temp'in next'i gelen bilgilerle olsuturulmus yeni bir dugumu gosterir. Yeni dugumun next'i sonrakiNode, yeni dugumun previous'u temp'i gosterir.

            } else {  // tail dısında bu soyada sahip baska birinin olmaması duurmunda else calisir.
                /*
                   else if'e girerken soyadi esit ve ismi alfabetik olarak once geliyorsa kosulunun saglanmasi gerektigi icin burada
                   gelen ismin tail'in isminden buyuk olma(alfabetik olarak sonra gelme) ya da esit olma durumu soz konusu olamaz.
                */
                // Gelen isim tail'in isminden alfabetik olarak once geliyorsa bu isim tail'in onune eklenir.
                    tail.previous.next = tail.previous = new Node(data, tail, tail.previous);
 
            }
        } else if (soyad.compareTo(findLastName(tail.data.getAdSoyad())) == 0 && isim.compareTo(findFirstName(tail.data.getAdSoyad())) == 0) {  //Parametre olarak gonderilen isim ve soyisim tail'in isim ve soyismine esitse bu durumda bu isim listeye eklenemez.
            System.out.println(isim + "" + soyad + " adinda biri bulunmaktadir. Bu sebeple bu isim listeye tekrar eklenemez.");
        } //***********************************************************************************************************************************
          // Basa veya sona ekleme durumlari disindaki otada bir yere ekleme durumu
        else {
            Node current = head.next;  // Head'in next'i olan bir current degiskeni tanimlanir.
            Node previousNode = head;  // Previous ise head olarak atanir.
            while (current != null && findLastName(current.data.getAdSoyad()).compareTo(soyad) <= 0) {  // current null olmadigi surece ve current'in soyadi parametre olarak gelen soyaddan buyuk olmadigi(alfabetik olarak sonra gelme durumu) surece doner while dongusu.
                /*
                current'in soyadi ve gelen soyad esit ve gelen isim current'in isminden kucuk ise if bloguna girilir.
                */
                if (findLastName(current.data.getAdSoyad()).compareTo(soyad) == 0 && isim.compareTo(findFirstName(current.data.getAdSoyad())) < 0) {
                    previousNode.next = current.previous = new Node(data, current, previousNode); // Bu durumda yeni dugum current'in onune gelmelidir.
                    return;

                    /*
                    current'in soyadi ve gelen soyad esit ve gelen isin current'in isminden buyuk ise bu durumda else if bloguna girilir. 
                    Ancak bu soyada sahip baska birilerinin olma durumu incelenmelidir.
                    */
                } else if (findLastName(current.data.getAdSoyad()).compareTo(soyad) == 0 && isim.compareTo(findFirstName(current.data.getAdSoyad())) > 0) {
                    // Gelen soyad current'in next'inin de soyadina esitse yani bu soyada sahip baska biri daha var ise if calisir.
                    if ((soyad.compareTo(findLastName(current.next.data.getAdSoyad())) == 0)) {
                        Node temp = current.next;  // temp, current2in bir sonraki
                        Node oncekiNode = current; // oncekiNode dedigimiz ise current'in kendisidir.
                        /*
                        while dongusu gelen soyad temp'in soyadina esit ve gelen isim temp'in isminden alfabetik olarak sonra geldigi surece doner.
                       */
                        while (isim.compareTo(findFirstName(temp.data.getAdSoyad())) > 0 && soyad.compareTo(findLastName(temp.data.getAdSoyad())) == 0) {
                            oncekiNode = temp; // oncekiNode temp yapilir
                            temp = temp.next;  // temp, temp2in bir sonrasi olur.
                        }
                        // while bittiginde eklenmek istenen musterinin yeri bulunmus olur.
                        oncekiNode.next = temp.previous = new Node(data, temp, oncekiNode); // oncekiNode ile temp arasina yeni bir dugum olusturulur.

                    } else { // Bu soyada sahip yalnizca current var ve gelen isim current'in isminden alfabetik olarak sonra geliyor ise else blogu calisir.         
                            current.next = current.next.previous = new Node(data, current.next, current);
                    }
                    return;
                // current'in adi ve soyadi tamamen gelen ad ve soyada esit ise bu durumda yeni gelen musteri listeye eklenemez. Kullanici bilgilendirilir.
                } else if (findLastName(current.data.getAdSoyad()).compareTo(soyad) == 0 && isim.compareTo(findFirstName(current.data.getAdSoyad())) == 0) {
                    System.out.println(isim + "" + soyad + " adinda biri bulunmaktadir. Bu sebeple bu isim listeye tekrar eklenemez.");
                    return;
                }
                previousNode = current;
                current = current.next;
            }  // while bitti...
            //current null ise ya da gelen soyad current'in soyadindan alfabetik olarak once geliyor ise bu durumda current ve previousNode arasina bir dugum eklenir.
            previousNode.next = current.previous = new Node(data, current, previousNode); 
        }
    } // Ekleme islemi yapan method bitti...

    public void deleteCustomer(String adSoyad) {  // Adi ve soyadi verilen musteriyi listeden silen method.
        if (!isEmpty()) {        // Liste bos degilse if bloguna girilir.
            Node current = head; // Listeyi gezmemizi saglayan Node tipindeki current degiskeni.
            while (current != null) { // Current null olmadigi surece yani tail olmadigi surece doner while dongusu.

                if (find(adSoyad) == current) {      // Bu ad soyada sahip olan musterinin adresi find methodu ile bulunur ve bu adres current'a esitse if bloguna girilir . 
                    if (current.previous == null) {  // current'in previous'u null ise current ilk elemandir.(head)
                        head = current.next;         // head silinecegi icin ve current su an head'i gosterdigi icin head current'in next'i yapilir.
                        head.previous = null;
                        
                    } else if (current.next == null) {// current'in next'i null ise current son elemandir. (tail)
                        tail = current.previous;
                        tail.next = null; // current'in previous'u tail yapilir. Son eleman silinmis olur.                       
                        
                    } else {
                        //Silinecek eleman basta ya da sonda degilse bu bloga girilir.
                        System.out.println("Listeden silinen musterinin bilgileri: " + current.data.toString()); // Silinecek musteriinin bilgileri yazdirilir.
                        Node temp1 = current.previous; // temp1, current'in oncesini
                        Node temp2 = current.next;     // temp2, current'in sonrasini gosterir.
                        current = null;  // Silinecek elemnan current oldugu  icin current null yapilir.
                        // Bag tekrardan olusturulur.
                        temp1.next = temp2;
                        temp2.previous = temp1;
                    }
                    break;
                } else if (find(adSoyad) == null) {  // Gonderilen ad soyada sahip bir musteri yoksa yani find methodu null dondururse bu blok calisilir.
                    System.out.println("Bu isimde bir musteri bulunmamaktadir.");
                    break;
                } else {  // Find methodunun verilen bu ad soyad icin dondurdugu adres current degilse else blogu calisir ve current bir sonraki yapilir.
                    current.next.previous = current;
                    current = current.next;
                }
            } // while dongusu bitti...

        } else {  // Liste bos ise else calisir.
            System.out.println("Hata! Liste bos bu islemi gerceklestiremezsiniz.");
        }

    } // Silme islemi yapan method bitti...

    public void printInAscendingOrder() {      // Tüm kayıtları artan alfabetik sırada (A’dan Z’ye) ekrana yazdıran method.
        Node position = head;       // Postion adli bir degisken listeyi gezmemizi sağlar. Bu nedenler head'den baslatililir.
        if (!isEmpty()) {           // Liste bos degilse ekrana yazdirma islemi gerceklestirilir.
            while (position != null) {  //Position degiskenimiz null olmadigi surece yani liste sonu olmadigi surece doner.
                System.out.println(position.data.toString());  // Musteri bilgileri ekrana yazdirilir.
                position = position.next; // position'a position'in bir sonraki atanir böylelikle bir sonraki dugume gecilmis olunur.
            }
        } else {
            System.out.println("Liste bos.");
        }
    }

    public void printInDescendingOrder() {      // Tüm kayıtları azalan alfabetik sırada (Z’den A’ya) ekrana yazdıran method.
        Node position = tail;       // Postion adli bir degisken listeyi gezmemizi sağlar. Bu nedenler tail'den baslatililir.
        if (!isEmpty()) {           // Liste bos degilse ekrana yazdirma islemi gerceklestirilir.
            while (position != null) {  //Position degiskenimiz null olmadigi surece yani liste sonu olmadigi surece doner.
                System.out.println(position.data.toString());   // Musteri bilgileri ekrana yazdirilir.
                position = position.previous; // position'a bir onceki atanir ve boylelikle bir onceki dugume gecilir ve liste tersten yazdirilmis olur.
            }
        } else {
            System.out.println("Liste bos.");
        }
    }

} // DoublyLinkedList bitti...
