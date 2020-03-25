import json
from database import mydb, mycursor


with open('./Tablolar_Ayrı/sehir.json', encoding="utf8") as f:
    data = json.load(f)
    index = 1
    for i in data:
        name = i["sehir_title"]
        postaKodu = i["sehir_key"]

        sql = "INSERT INTO province (id, name) VALUES (%s,%s);"
        val = (postaKodu, name)
        print(val)
        mycursor.execute(sql, val)

        mydb.commit()

        print(mycursor.rowcount, "record inserted.")
        index += 1
