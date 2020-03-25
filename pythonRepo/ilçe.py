import json
from database import mydb, mycursor


# id, name, province_id

with open('./Tablolar_Ayrı/ilce.json', encoding="utf8") as f:
    data = json.load(f)
    index = 1
    for i in data:
        id = i["id"]
        name = i["name"]
        sehirkey = i["province_id"]

        sql = "INSERT INTO district (id, name, province_id) VALUES (%s,%s, %s);"
        val = (id, name, sehirkey)
        print(val)
        try:
            mycursor.execute(sql, val)

            mydb.commit()

            print(mycursor.rowcount, "record inserted.")
        except:
            print("Not Addes")

        index += 1
print(index)
