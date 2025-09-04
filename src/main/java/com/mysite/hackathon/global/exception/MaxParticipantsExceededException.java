package com.mysite.hackathon.global.exception;

public class MaxParticipantsExceededException extends RuntimeException {
    public MaxParticipantsExceededException() {
        super("모임의 최대 참가 인원을 초과했습니다.");
    }
}
