package store;

import static store.Exceptions.INVALID_INPUT;

public enum UserResponse {
    YES("Y"), NO("N");

    private final String message;

    UserResponse(String message) {
        this.message = message;
    }

    public static UserResponse of(String message) {
        for (UserResponse response : UserResponse.values()) {
            if (response.message.equals(message)) {
                return response;
            }
        }
        throw new IllegalArgumentException(INVALID_INPUT.getMessage());
    }
}
