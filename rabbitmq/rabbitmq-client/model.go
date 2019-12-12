package main

type user struct {
	ID   string `json:"id"`
	Name string `json:"name"`
}

type userCreationResponse struct {
	Status   int      `json:"status"`
	Summary  string   `json:"summary"`
	Messages []string `json:"messages"`
	User     user     `json:"user"`
}

const (
	userCreationStatusSuccess = 0
	userCreationStatusWarning = 1
	userCreationStatusFailure = 2
)
