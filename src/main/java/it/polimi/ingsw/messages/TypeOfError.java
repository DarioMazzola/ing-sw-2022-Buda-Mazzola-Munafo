package it.polimi.ingsw.messages;

public enum TypeOfError {
        ASSISTANT_CARD_NOT_AVAILABLE("This card is not present in your deck! Please select another one"),
        ASSISTANT_CARD_TAKEN("This assistant card has already been selected by another player! Please select an available assistant card"),
        TEAM_FULL ("This team is already full! Please select the other team"),
        INVALID_NUM_PLAYERS("The number of players chosen is not valid! Please select a number of player between 2 and 4"),
        INVALID_TEAM("The selected team doesn't exists! Please select an existing team"),
        WIZARD_TAKEN("The wizard selected has already been selected by another player! Please select an available wizard"),
        COLOR_TOWER_TAKEN("The tower color chosen has already been selected by another player! Please select an available tower color"),
        NICKNAME_TAKEN("The nickname selected has already been taken by another player! Please choose another nickname"),
        EMPTY_NICKNAME("Empty nickname given!"),
        LEADER_TAKEN ("The leader position for this team has already been taken by another player! You can apply to this same team as a team member"),
        TEAM_TAKEN ("The team member position for this team has already been taken by another player! You can apply to this same team as team leader"),
        MAX_STUDENTS_MOVED("You have already moved the maximum number of students! Please select another action"),
        STUDENTS_NOT_MOVED("You have not moved all your students! Please move your students from your entrance to your dining hall or to an island"),
        INVALID_STUDENT("The selected student is not available! Please select another one"),
        INVALID_STUDENT_OR_ISLAND("The selected island or student is not available! Please select other ones"),
        ALREADY_USED_CHARACTER_CARD("You have already used a character card! PLease choose another action"),
        INVALID_CLOUD("This cloud is not available! Please select another cloud"),
        GAME_ALREADY_STARTED("The game is already started. You cannot join now!");


        private final String description;

        TypeOfError(String errorString) {
            this.description = errorString;
        }

        @Override
        public String toString() {
            return description;
        }
}
