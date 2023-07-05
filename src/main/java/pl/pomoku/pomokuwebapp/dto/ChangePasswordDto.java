package pl.pomoku.pomokuwebapp.dto;

public record ChangePasswordDto(String token, String changedPassword) {
}
