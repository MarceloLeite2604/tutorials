package dbuser

import (
	"database/sql"
	"fmt"
	"strings"

	"github.com/google/uuid"
)

const (
	insertUserStatementPrefix    = "INSERT INTO users (id, first_name, last_name, username) VALUES "
	insertUserStatementValues    = "(?, ?, ?, ?)"
	countUsersStatement          = "SELECT count(*) FROM users"
	findUsersByIDStatementPrefix = "SELECT * FROM users WHERE id IN "
)

// User maps "users" table rows.
type User struct {
	ID        uuid.UUID
	FirstName string
	LastName  string
	UserName  string
}

// InsertUsers insert users into database.
func InsertUsers(db *sql.DB, usrs []User) (int64, error) {

	sqlCmds := insertUserStatementPrefix
	vals := []interface{}{}
	for _, usr := range usrs {
		sqlCmds += insertUserStatementValues + ","
		vals = append(vals, usr.ID.String(), usr.FirstName, usr.LastName, usr.UserName)
	}

	sqlCmds = strings.TrimSuffix(sqlCmds, ",")

	tx, err := db.Begin()
	if err != nil {
		err = fmt.Errorf("Error while opening a transaction with database: %w", err)
		return -1, err
	}

	stmt, err := tx.Prepare(sqlCmds)
	if err != nil {
		err = fmt.Errorf("Error while preparing insert statement: %w", err)
		return -1, err
	}
	defer stmt.Close()

	res, err := stmt.Exec(vals...)
	if err != nil {
		tx.Rollback()
		err = fmt.Errorf("Error while trying executing insert statements: %w", err)
		return -1, err
	}

	rows, err := res.RowsAffected()
	if err != nil {
		tx.Rollback()
		err = fmt.Errorf("Error while retrieving rows affected by user insertion: %w", err)
		return -1, err
	}

	tx.Commit()
	return rows, nil
}

// InsertUser insert a user into database.
func InsertUser(db *sql.DB, usr User) (int64, error) {

	usrs := make([]User, 1)
	usrs[0] = usr
	return InsertUsers(db, usrs)
}

// CountUsers counts the total of rows on "users" table.
func CountUsers(db *sql.DB) (int64, error) {

	stmt, err := db.Prepare(countUsersStatement)
	if err != nil {
		err = fmt.Errorf("Error while preparing count users statement: %w", err)
		return -1, err
	}

	var result int64
	err = stmt.QueryRow().Scan(&result)
	if err != nil {
		err = fmt.Errorf("Error while retrieving result from count users query: %w", err)
		return -1, err
	}

	return result, nil
}

// FindUsersByID find users on database by its IDs
func FindUsersByID(db *sql.DB, ids []uuid.UUID) ([]User, error) {

	sqlCmd := findUsersByIDStatementPrefix + "("
	vals := []interface{}{}
	for _, id := range ids {
		sqlCmd += "?, "
		vals = append(vals, id.String())
	}

	sqlCmd = strings.TrimSuffix(sqlCmd, ", ")
	sqlCmd += ")"

	stmt, err := db.Prepare(sqlCmd)
	if err != nil {
		err = fmt.Errorf("Error while preparing find users by id statement: %w", err)
		return nil, err
	}
	defer stmt.Close()

	rows, err := stmt.Query(vals...)
	if err != nil {
		err = fmt.Errorf("Error executing find users by id statement: %w", err)
		return nil, err
	}
	defer rows.Close()

	usrs := make([]User, 0)
	for rows.Next() {
		var usr User
		err := usr.scanRow(rows)
		if err != nil {
			err = fmt.Errorf("Error scanning row returned from database: %w", err)
			return nil, err
		}
		usrs = append(usrs, usr)
	}

	return usrs, nil
}

func (usr *User) scanRow(rows *sql.Rows) error {
	var strID string
	var err error

	rows.Scan(&strID, &usr.FirstName, &usr.LastName, &usr.UserName)
	usr.ID, err = uuid.Parse(strID)
	if err != nil {
		err = fmt.Errorf("Error parsing ID \"%v\" from row: %w", strID, err)
		return err
	}

	return nil
}

func (usr User) String() string {
	result := "ID: " + usr.ID.String()
	result += ", Name: " + usr.FirstName + " " + usr.LastName
	result += ", Username: " + usr.UserName
	return result
}
