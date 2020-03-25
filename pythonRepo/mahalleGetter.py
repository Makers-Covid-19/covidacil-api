import mysql.connector

from database import mydb as connection, mycursor as cursor

try:

    sql_select_Query = "select * from neighborhood"
    cursor = connection.cursor()
    cursor.execute(sql_select_Query)
    records = cursor.fetchall()
    print("Total number of rows in Neighborhood is: ", cursor.rowcount)

    print("\nPrinting each laptop record")
    for row in records:
        print("Id = ", row[0], )
        print("Name = ", row[1])
        print("District id  = ", row[2], "\n")


except Error as e:
    print("Error reading data from MySQL table", e)
finally:
    if (connection.is_connected()):
        connection.close()
        cursor.close()
        print("MySQL connection is closed")
