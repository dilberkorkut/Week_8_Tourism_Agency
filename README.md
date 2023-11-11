# Tourism Agency



Bu proje, Patika Turizm Acentesi'nin işlerini dijital ortamda yönetmesi için bir yazılım geliştirmeyi amaçlamaktadır. Acentenin çalışma mantığına göre otel yönetimi, dönem yönetimi, oda yönetimi, fiyatlandırma, oda arama ve rezervasyon işlemlerini içermektedir.

## Proje Tanımı

Otel Yönetimi:
Acentenin anlaşmalı olduğu otelleri, konum bilgileri ve diğer özellikleri ile sisteme ekleyebilme.
Oteller eklenirken, Otel Adı, Adres, E-posta, Telefon, Yıldız, Tesis Özellikleri, Pansiyon Tipleri gibi bilgilerin girilebilmesi.

Dönem Yönetimi:
Otellere ait tarihsel dönemlerin eklenmesi ve fiyatlandırmanın bu dönemlere göre yapılabilmesi.

Oda Yönetimi:
Acentenin rezerve ettiği odaların sisteme eklenmesi ve fiyatlandırmanın yapılabilmesi.
Oda tipleri (Single, Double, Suit vb.) ve oda özellikleri (Yatak Sayısı, Televizyon, Minibar vb.) girişleri.

Fiyatlandırma:
Odalara ait gecelik fiyatların dönem, pansiyon tipi ve konaklayanların yetişkin/çocuk sayısına göre belirlenmesi.
Fiyatlandırmalara acentenin komisyon bedelinin dahil edilmesi.

Oda Arama ve Rezervasyon İşlemleri:
Acentenin çalışanlarının tarih aralığı, bölge, otel ve misafir bilgisine göre odaları arayabilmesi.
Odaların liste halinde gösterilmesi ve fiyatlandırmanın yapılabilmesi.

Rezervasyon İşlemi:
Acenta çalışanının uygun oda tipini seçerek rezervasyon yapabilmesi.
Rezervasyon tamamlanınca ilgili odanın stok miktarının azaltılması.

Rezervasyon Listesi:
Acente çalışanlarının yapılan rezervasyonları listeleyebilmesi ve silme işlemi yapabilmesi.

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
-  Kullanilan mettotlar benzer oldugu icin hepsinde ayni sekilde detayli yorum satirlari eklenmedi.
- Ornegin bir cok ozellik User class'inda detayli aciklandi. Bu sebeple diger class'lardadaha az aciklamaya yer verildi.
- GUI.form boyutlari laptop boyutunda gorunmeyebilir. Buyuk ekranda bakiniz. 
- Proje degerlendirme sonrasi duzenlemeler yapilacak.
- Proje degerlendirme kriterleri karsilaniyor proje calisiyor fakat eklemek istedigim ozellikler icin daha sonra poje uzerinde calismaya devam edecegim. 


