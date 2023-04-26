package puzzle;

public enum GameState {
	// Before shape is finalized
	INVALID_NEW_INCOMPLETE,
	INVALID_NEW_COMPLETE,
	VALID_NEW_COMPLETE,
	FINALIZED,
	// after shape is finalized
	INVALID_TEST_POINT,
	VALID_TEST_POINT,
	GAME_END	
}
