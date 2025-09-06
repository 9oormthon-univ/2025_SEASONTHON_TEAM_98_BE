-- USERS 샘플
INSERT INTO users (email, password, name, username, dong, location_lat, location_lng) VALUES

                                                                                          ('ksh@hotmail.com', '$2a$10$51jpHpQ4NhmJwt3v2iG/KOK/rMzF86dROmXkHrXbGr0UMX/tdf1lG', '아무개');
                                                                                          ('ksh000@hotmail.com', '$2a$10$OzzJHF2MULQgXjlYSSK9Cuc6dBkDvnbxK5/j9u21.J.ZzDwfGaAoG', '아무나');



-- MEETINGS 샘플
INSERT INTO meetings (title, description, category, host_id, dong, start_time, end_time, max_participants, has_afterparty)
VALUES
    ('스터디 모임', '자바 스터디 함께해요', '스터디', 1, '흥덕구', '2025-09-10 10:00:00', '2025-09-10 12:00:00', 10, TRUE),
    ('축구 모임', '주말에 풋살 경기 합니다', '운동', 2, '서원구', '2025-09-12 15:00:00', '2025-09-12 17:00:00', 12, FALSE);

-- MEETING_PARTICIPANTS 샘플
INSERT INTO meeting_participants (meeting_id, user_id, joined_at)
VALUES
    (1, 2, '2025-09-08 09:00:00'),
    (2, 1, '2025-09-08 09:10:00');

-- SURVEY_QUESTION 샘플
INSERT INTO survey_question (id, question_text) VALUES
                                                    (1, '요즘 당신을 가장 힘들게 하는 것은 어떤 종류의 피로감인가요?'),
                                                    (2, '요즘 스트레스를 받을 때, 주로 어떻게 반응하나요?'),
                                                    (3, '새로운 사람과 만난다면, 어떤 방식의 소통이 가장 편안할까요?'),
                                                    (4, '운동을 통해 지금 당신이 가장 되찾고 싶은 감각은 무엇인가요?'),
                                                    (5, '요즘 당신을 가장 힘들게 하는 것은 어떤 종류의 피로감인가요? (다시)');

-- SURVEY_OPTION 샘플
-- Q1 보기
INSERT INTO survey_option (option_text, question_id) VALUES
                                                         ('정신적 소진: 뭘 해도 재미없고, 집중이 잘 안 돼요.', 1),
                                                         ('감정적 소진: 사소한 일에도 감정이 크게 요동쳐요.', 1),
                                                         ('신체적 소진: 충분히 자도 몸이 무겁고 게으름피우기만 해요.', 1),
                                                         ('관계적 소진: 사람들을 만나는 게 버겁고 혼자 있고 싶어요.', 1);

-- Q2 보기
INSERT INTO survey_option (option_text, question_id) VALUES
                                                         ('머리가 지끈거리고 생각이 많아져요.', 2),
                                                         ('어깨와 목 주변이 뻐근해졌어요.', 2),
                                                         ('가슴이 답답하고 숨쉬기 힘들 때가 있어요.', 2),
                                                         ('소화가 잘 안 되고 속이 더부룩해요.', 2);

-- Q3 보기
INSERT INTO survey_option (option_text, question_id) VALUES
                                                         ('묵묵한 동행: 많은 대화 없이 함께 있는 것만으로도 좋아요.', 3),
                                                         ('관심사 공유: 취미나 흥미로운 주제에 대해 이야기하고 싶어요.', 3),
                                                         ('공감적 교류: 서로의 고민을 풀어주고 공감을 나누고 싶어요.', 3),
                                                         ('가벼운 교류: 즐거운 농담이나 일상적인 대화를 나누고 싶어요.', 3);

-- Q4 보기
INSERT INTO survey_option (option_text, question_id) VALUES
                                                         ('개운함: 아무 생각 없이 땀 흘리고 싶어요.', 4),
                                                         ('평온함: 자연을 느끼며 마음을 사로잡히지 않고 싶어요.', 4),
                                                         ('성취감: 작은 목표라도 꾸준히 해내고 싶어요.', 4),
                                                         ('연결감: 누군가와 함께하고 있다는 느낌을 받고 싶어요.', 4);

-- Q5 보기
INSERT INTO survey_option (option_text, question_id) VALUES
                                                         ('정신적 소진: 뭘 해도 재미없고, 집중이 잘 안 돼요.', 5),
                                                         ('감정적 소진: 사소한 일에도 감정이 크게 요동쳐요.', 5),
                                                         ('신체적 소진: 충분히 자도 몸이 무겁고 게으름피우기만 해요.', 5),
                                                         ('관계적 소진: 사람들을 만나는 게 버겁고 혼자 있고 싶어요.', 5);

-- SURVEY_ANSWER 샘플 (임시 테스트용)
INSERT INTO survey_answer (user_id, question_id, selected_option)
VALUES
    (1, 1, '정신적 소진: 뭘 해도 재미없고, 집중이 잘 안 돼요.'),
    (2, 2, '어깨와 목 주변이 뻐근해졌어요.');
