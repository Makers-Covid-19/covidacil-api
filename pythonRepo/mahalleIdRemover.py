import json


with open('./Tablolar_Ayrı/mahalle.json', encoding="utf8") as f:
    data = json.load(f)
    index = 0
    for i in data:
        print(index)
        i["id"] = index
        index += 1

with open('./Tablolar_Ayrı/mahalle.json',  'w') as fs:
    json.dump(data, fs)

# eg:
