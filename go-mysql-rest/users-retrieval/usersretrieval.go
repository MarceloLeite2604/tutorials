package main

import (
	"database/sql"
	"flag"
	"fmt"

	"github.com/MarceloLeite2604/tutorials/go-mysql-rest/users-retrieval/dbuser"
	"github.com/MarceloLeite2604/tutorials/go-mysql-rest/users-retrieval/randomuser"

	_ "github.com/go-sql-driver/mysql"
	"github.com/google/uuid"
)

const (
	databaseType = "mysql"
)

var (
	usersAmount = flag.Int("users", 1, "Amount of users to retrieve from randomusers.me API.")
)

func checkError(err error, message string) {
	if err != nil {
		panic(fmt.Sprintf("%s: %s", message, err))

	}
}

func createDataSourceName(mySQLConf mySQLConfiguration) string {
	return fmt.Sprintf("%v:%v@%v(%v:%v)/%v", mySQLConf.Username, mySQLConf.Password, mySQLConf.Protocol, mySQLConf.Host, mySQLConf.Port, mySQLConf.Database)
}

func openDatabase(mySQLConf mySQLConfiguration) *sql.DB {
	mysqlDb, err := sql.Open("mysql", createDataSourceName(mySQLConf))
	checkError(err, "Error opening \"mysql\" database.")

	return mysqlDb
}

func retrieveConfiguration() configuration {
	var conf configuration
	conf.getConfiguration()
	return conf
}

func generateRandomUUID() uuid.UUID {
	id, err := uuid.NewRandom()
	checkError(err, "Error generating a new random UUID.")

	return id
}

func mapRandomUserToDbUser(rndUsr randomuser.User) dbuser.User {
	var usr dbuser.User

	id, err := uuid.Parse(rndUsr.Login.UUID)
	message := fmt.Sprintf("Error parsing UUID \"%v\".", rndUsr.Login.UUID)
	checkError(err, message)
	usr.ID = id
	usr.FirstName = rndUsr.Name.First
	usr.LastName = rndUsr.Name.Last
	usr.UserName = rndUsr.Login.Username

	return usr
}

func mapRandomUsersToDbUsers(rndUsrs []randomuser.User) []dbuser.User {
	dbUsrs := make([]dbuser.User, len(rndUsrs))
	for idx, rndUsr := range rndUsrs {
		dbUsrs[idx] = mapRandomUserToDbUser(rndUsr)
	}

	return dbUsrs
}

func retrieveUsersFromRandomUsersAPI(amount int) []randomuser.User {
	rndUsrs, err := randomuser.RequestUsers(*usersAmount)
	checkError(err, "Error requesting users from \"randomuser.me\" API")

	return rndUsrs
}

func insertUsersIntoDatabase(db *sql.DB, dbUsrs []dbuser.User) int64 {

	usersCreated, err := dbuser.InsertUsers(db, dbUsrs)
	checkError(err, "Error while requesting user(s) insertion.")

	return usersCreated
}

func countUsersOnDatabase(db *sql.DB) int64 {
	count, err := dbuser.CountUsers(db)
	checkError(err, "Error counting users on database.")
	return count
}

func retrieveIDsFromRandomUsers(rndUsrs []randomuser.User) []uuid.UUID {
	ids := make([]uuid.UUID, len(rndUsrs))
	for idx, rndUsr := range rndUsrs {
		id, err := uuid.Parse(rndUsr.Login.UUID)
		checkError(err, "Error parsing UUID.")
		ids[idx] = id
	}

	return ids
}

func findAndPrintUsersOnDatabase(db *sql.DB, ids []uuid.UUID) []dbuser.User {
	dbUsrs, err := dbuser.FindUsersByID(db, ids)
	checkError(err, "Error finding users by ID on database.")

	for _, dbUsr := range dbUsrs {
		fmt.Println(dbUsr)
	}

	return dbUsrs
}

func main() {

	flag.Parse()

	rndUsrs := retrieveUsersFromRandomUsersAPI(*usersAmount)

	dbUsrs := mapRandomUsersToDbUsers(rndUsrs)

	conf := retrieveConfiguration()

	db := openDatabase(conf.GoMySQL.MySQL)
	defer db.Close()

	insertCnt := insertUsersIntoDatabase(db, dbUsrs)

	totalCnt := countUsersOnDatabase(db)

	ids := retrieveIDsFromRandomUsers(rndUsrs)

	dbUsrs = findAndPrintUsersOnDatabase(db, ids)

	fmt.Printf("%v row(s) inserted into database. Total users on database %v.\n", insertCnt, totalCnt)
}
