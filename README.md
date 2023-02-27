# Ch-Job-Execution
Clickhouse' da 20 kolonlu bir tablo oluşturuldu.
Bu tabloya 1 milyon satırlık veri eklendi.


[ch-sentetkiveri.txt](https://github.com/ezgisariyildiz/Ch-Job-Execution/files/10841193/ch-sentetkiveri.txt)


Projeye quartz kütüphanesi eklendi. Qartz kütüphanesi zamanlama ve zamanlanmış görevlerin yönetimi için kullanılan açık kaynaklı bir kütüphanedir. Bu kütüphane sayesinde, Java uygulamalarında belirli aralıklarla tekrar eden görevlerin planlanması, zamanlama, yürütülmesi ve yönetilmesi amaçlanmaktadır. 
Oluşturduğumuz tablado çalışan 100 task çalıştırma işlemi yapıldı ve her task ın kullandığı CPU, RAM ve toplam çalışma zamanı tespit edildi. 

Tasklar çalışırrken diğer taraftan 500 Insert query si ekleme işlemi yapıldı.

Materialized View oluşturuldu ve bununla beraber performance improvement ölçüldü. 


[materializedview.txt](https://github.com/ezgisariyildiz/Ch-Job-Execution/files/10841255/materializedview.txt)


AveragePerformanceCalculator ile performance improvement işlemi karşılaştırıldı.
