# Covid Acil Api

Deployment için build klasörü içerisindeki jar dosyası ve üzerindeki lib klasörünün
aynı dizinde bulunmasına dikkat ettikten sonra run.bat içrisindeki
komut cmd den verilerek uygulama çalıştırılabilir.
Port ayarlamak için run.bat içerisindeki -Dserver.port=8090 değişkenindeki
port numarası ayarlanabilir.
çalıştırmak için komut ayrıca

java -Dserver.port=8090 -jar RestFullApi-0.0.1-SNAPSHOT.jar

serverde jre yüklü olmalıdır.

Bu proje Covid Acil uygulaması için veri sunmaktadır.

Endpointler:

*https://rocky-reef-05857.herokuapp.com/api/v0/provinces
Bu end point ile Türkiyedeki tüm illere ulaşım sağlanabilmektedir

*https://rocky-reef-05857.herokuapp.com/api/v0/districts/{il Plaka Kodu}
Bu endpoint ile verilen il plaka koduna göre ilçe isimlerine ulaşım sağlanabilmektedir.

*https://rocky-reef-05857.herokuapp.com/api/v0/neighborhoods/{ilçe id}
Bu endpoint ile verilen ilçe id sine göre mahalle isimleri listelenmektedir.

Telefon numaralarına ulaşmak için farklı yollar mevcut.

1.https://rocky-reef-05857.herokuapp.com/api/v0/phones
Bu endpoint sistemde kayıtlı olan bütün telefon numaralarını listeler


2.https://rocky-reef-05857.herokuapp.com/api/v0/phones/id
Bu endpointe url parametre olarak üç adet id göndererek gönderdiğiniz 
id 'de bulunan telefon numaraları geri döndürür.

province_id -> verildiği zaman ilde bulunan bütün numaralar

district_id -> verildiği zaman ilçede bulunan bütün numaralar geri döner

neighborhood_id -> verildiği zaman mahallede bulunan bütün telefon numaraları döner

hepsi birden verildiği zaman ilk olarak mahalle sonra ilçe en son il dikkate alınır.

* https://rocky-reef-05857.herokuapp.com/api/v0/phones/{mahalle id}
mahalle id gönderilerek mahalleye ait telefon numarasına ulaşabilirsiniz



