import mysql.connector

mydb = mysql.connector.connect(
  
    user="rfb",
    passwd="Rfb.5388414585",
    database="schema"
)

mycursor = mydb.cursor()
