import csv

with open("./Tablolar_Ayri/csv/yerel-yönetim-listesi-teyitli.csv", newline='') as csvfile:
    csv_reader = csv.reader(csvfile, delimiter=' ', quotechar='|')
    line_count = 0
    for row in csv_reader:
    
        print("new row" , row)
        line_count += 1

    print(f'Processed {line_count} lines.')