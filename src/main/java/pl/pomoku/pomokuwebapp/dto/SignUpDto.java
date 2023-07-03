package pl.pomoku.pomokuwebapp.dto;

public record SignUpDto(String firstName, String lastName, String login, char[] password) {
}
