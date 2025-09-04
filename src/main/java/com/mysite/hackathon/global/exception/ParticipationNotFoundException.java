package com.mysite.hackathon.global.exception;

/**
 * 참여 기록이 없을 때 발생하는 예외
 */
public class ParticipationNotFoundException extends RuntimeException {
    public ParticipationNotFoundException() {
        super("참여 기록이 없습니다.");
    }
}
