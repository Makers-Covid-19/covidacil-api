import mysql.connector

mydb = mysql.connector.connect(
   host="3.21.169.224",
    user="rfb",
    passwd="Rfb.5388414585",
    database="schema"
)

mycursor = mydb.cursor()
