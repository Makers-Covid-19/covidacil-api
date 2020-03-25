import json
from database import mydb, mycursor


#  "mahalle_title": "\u00c7AYKI\u015eLA",
#  "mahalle_key": "6535",
#  "mahalle_ilcekey": "1363"

# id, name, distric_key


rows = []


with open('./Tablolar_Ayrı/mahalle.json', encoding="utf8") as f:
    data = json.load(f)
    index = 1
    for i in data:
        name = i["name"]
        key = i["id"]
        sehirkey = i["distric_id"]
        sql = "INSERT INTO neighborhood (id, name, distric_id) VALUES (%s,%s, %s);"

        val = (index, name, sehirkey)

        print(val)

        index += 1

        try:
            mycursor.execute(sql, val)
            mydb.commit()

            print(mycursor.rowcount, "record inserted.")
        except:
            print("Not Uploaded")
# eg:
