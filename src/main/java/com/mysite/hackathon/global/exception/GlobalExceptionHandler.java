package com.mysite.hackathon.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 모든 예외를 전역적으로 처리하는 핸들러
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<Object> buildResponse(HttpStatus status, String error, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", error);
        body.put("message", message);
        return new ResponseEntity<>(body, status);
    }

    @ExceptionHandler(MeetingNotFoundException.class)
    public ResponseEntity<Object> handleMeetingNotFound(MeetingNotFoundException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, "MeetingNotFound", ex.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFound(UserNotFoundException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, "UserNotFound", ex.getMessage());
    }

    @ExceptionHandler(AlreadyJoinedException.class)
    public ResponseEntity<Object> handleAlreadyJoined(AlreadyJoinedException ex) {
        return buildResponse(HttpStatus.CONFLICT, "AlreadyJoined", ex.getMessage());
    }

    @ExceptionHandler(ParticipationNotFoundException.class)
    public ResponseEntity<Object> handleParticipationNotFound(ParticipationNotFoundException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, "ParticipationNotFound", ex.getMessage());
    }

    @ExceptionHandler(MaxParticipantsExceededException.class)
    public ResponseEntity<Object> handleMaxParticipantsExceeded(MaxParticipantsExceededException ex) {
        return buildResponse(HttpStatus.CONFLICT, "MaxParticipantsExceeded", ex.getMessage());
    }

    @ExceptionHandler(Exception.class) // 예상치 못한 모든 예외 처리
    public ResponseEntity<Object> handleGeneral(Exception ex) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "InternalError", ex.getMessage());
    }
}
