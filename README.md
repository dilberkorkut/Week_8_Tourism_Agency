# Turizm Acentesi


Bu proje bir Java uygulamasidir. 
Patika Turizm acentesinin islerini dijital ortamda yurutebilmesini saglamak uzere gelistirilmistir.

Acentenin calisma mantigina gore otel yonetimi, donem yonetimi, oda yonetimi, fiyatlandirma, oda arama ve rezervasyon islemlerini icermektedir.

## Proje Tanımı

### Users:
Admin ve Agency olarak sisteme giris yapilabiliyor.
![](/Users/dilberbilgin/IdeaProjects/Week_8_Tourism_Agency/src/secreenshot/users_add_search_delete.png)
![](/Users/dilberbilgin/IdeaProjects/Week_8_Tourism_Agency/src/secreenshot/database_users.png)

### Otel Yönetimi:
Acentenin anlaşmalı olduğu otelleri, konum bilgileri ve diğer özellikleri ile sisteme ekleyebilme.
Oteller eklenirken, Otel Adı, Adres, E-posta, Telefon, Yıldız, Tesis Özellikleri, Pansiyon Tipleri gibi bilgilerin girilebilmesi.
Burada otel arama ekleme ve secilen oteli silme ozellikleri calismaktadir.
![](/Users/dilberbilgin/IdeaProjects/Week_8_Tourism_Agency/src/secreenshot/hotel_add_features_delete.png)
![](/Users/dilberbilgin/IdeaProjects/Week_8_Tourism_Agency/src/secreenshot/database_hotels.png)

### Dönem Yönetimi:
Otellere ait tarihsel dönemlerin eklenmesi ve silinebilmesi. Bu iki ozellik de calisir durumda. Diledigimiz kadar donem araligi belirleyebiliyoruz.
![](/Users/dilberbilgin/IdeaProjects/Week_8_Tourism_Agency/src/secreenshot/season_add_delete.png)
![](/Users/dilberbilgin/IdeaProjects/Week_8_Tourism_Agency/src/secreenshot/database_hotel_seasons.png)

### Oda Yönetimi:
Acentenin rezerve ettiği odaların sisteme eklenmesi ve fiyatlandırmanın yapılabilmesi.
Oda tipleri (Single, Double, Suit vb.) ve oda özellikleri (Yatak Sayısı, Televizyon, Minibar vb.) girişleri.
Oda ekleme silme calisir durumda.
![](/Users/dilberbilgin/IdeaProjects/Week_8_Tourism_Agency/src/secreenshot/room_add_delete.png)
![](/Users/dilberbilgin/IdeaProjects/Week_8_Tourism_Agency/src/secreenshot/database_rooms.png)

### Fiyatlandırma:
Odalara ait gecelik fiyatların dönem, pansiyon tipi ve konaklayanların yetişkin/çocuk olma durumuna göre belirlenmesi.
Oda fiyati ekleme ve silme calisir durumda.
![](/Users/dilberbilgin/IdeaProjects/Week_8_Tourism_Agency/src/secreenshot/price_add_delete.png)
![](/Users/dilberbilgin/IdeaProjects/Week_8_Tourism_Agency/src/secreenshot/database_prices.png)

### Oda Arama ve Rezervasyon İşlemleri:
Acentenin çalışanlarının tarih aralığı, bölge, otel ve misafir bilgisine göre odaları arayabilmesi.
Odaların liste halinde gösterilmesi ve fiyatlandırmanın yapılabilmesi.
Kisi sayilari secilerek toplam tutar rezervasyon ektranina dusuyor.
Rezervasyon tamamlanınca ilgili odanın stok miktarının azaliyor.
![](/Users/dilberbilgin/IdeaProjects/Week_8_Tourism_Agency/src/secreenshot/search_reservation_operation.png)

Rezervasyon Listesi:
Acente çalışanlarının yapılan rezervasyonları listeleyebilmesi ve silme işlemi yapabilmesi.
![](/Users/dilberbilgin/IdeaProjects/Week_8_Tourism_Agency/src/secreenshot/season_add_delete.png)
![](/Users/dilberbilgin/IdeaProjects/Week_8_Tourism_Agency/src/secreenshot/database_reservations.png)


## Kullanılan Teknolojiler

- Java
- Swing (GUI)
- MySQL (veritabanı)
- JDBC (Java Database Connectivity)

## Proje Yapısı

- **com.tourismagency.Model:** Temel veri modelleme sınıfları.
- **com.tourismagency.View:** Swing kullanılarak oluşturulmuş kullanıcı arayüzü sınıfları.
- **com.tourismagency.Helper:** Yardımcı sınıflar ve yardımcı metodlar.

## Nasıl Çalıştırılır

1. Projeyi bilgisayarınıza klonlayın.
2. MySQL veritabanınızı oluşturun ve bağlantı bilgilerini `DBConnector` sınıfında güncelleyin.
3. Proje dosyalarını bir Java IDE'sinde açın.
4. `LoginGUI` sınıfını çalıştırarak uygulamayı başlatın.

## NOTLAR
- Kullanilan mettotlar benzer oldugu icin hepsinde ayni sekilde detayli yorum satirlari eklenmedi.
- Ornegin bir cok ozellik User class'inda detayli aciklandi. Bu sebeple diger class'lardadaha az aciklamaya yer verildi.
- GUI.form boyutlari laptop boyutunda gorunmeyebilir. Buyuk ekranda bakiniz. 
- Proje degerlendirme sonrasi duzenlemeler yapilacak.
- Proje degerlendirme kriterleri karsilaniyor proje calisiyor fakat eklemek istedigim ozellikler icin daha sonra poje uzerinde calismaya devam edecegim. 


