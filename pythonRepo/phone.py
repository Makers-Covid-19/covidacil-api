import json
from database import mydb, mycursor


#  "mahalle_title": "\u00c7AYKI\u015eLA",
#  "mahalle_key": "6535",
#  "mahalle_ilcekey": "1363"

# id, name, distric_key


rows = []


with open('./Tablolar_Ayri/phones.json', encoding="utf8") as f:
    data = json.load(f)
    index = 1
    for i in data:
        id = i["id"]
        name = i["name"]
        no = i["no"]
        category_id = i["category_id"]
        neighborhood_id = i["neighborhood_id"]
        distric_id = i["distric_id"]
        province_id = i["province_id"]
        sql = "INSERT INTO neighborhood (id,name, no, category_id, neighborhood_id, distric_id, province_id) VALUES (%s,%s,%s, %s,%s,%s, %s);"

        val = (id, name, no,category_id,neighborhood_id,distric_id,province_id)

        print(val)

        index += 1

        try:
            mycursor.execute(sql, val)
            mydb.commit()

            print(mycursor.rowcount, "record inserted.")
        except :
            
            print("Not Uploaded")
# eg:
