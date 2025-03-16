INSERT INTO authority(authority_name) VALUES ('USER');
INSERT INTO authority(authority_name) VALUES ('ADMIN');

INSERT INTO user(user_name, real_name, password, email, phone_number, gender, user_authority)
VALUES ('admin', 'administrator', '$2b$12$A0kgVpplgbH3ZZ1E89441eacUXljTTt7nP8I3RdLtW0P6/CXdEnCm', 'admin@gmail.com', '010-0000-0000',  'MALE', 'ADMIN'),
('user', '유저이름', '010-1111-1111', '$2a$10$AbLNyFNNFBekwJMX.833/ugdQLaB4Tv3DHGsGyQip1bJJrAFm9ufC', 'name@naver.com', 'MALE', 'USER'),
('user1', '유저이름', '010-1222-1222', '$2a$10$AbLNyFNNFBekwJMX.833/ugdQLaB4Tv3DHGsGyQip1bJJrAFm9ufC', 'name2@naver.com', 'MALE', 'USER'),
('user2', '유저이름', '010-1234-1432', '$2a$10$AbLNyFNNFBekwJMX.833/ugdQLaB4Tv3DHGsGyQip1bJJrAFm9ufC', 'name3@naver.com', 'FEMALE', 'USER');

INSERT INTO category(name) VALUES ('장비');
INSERT INTO category(name) VALUES ('기술');
INSERT INTO category(name) VALUES ('루틴');
INSERT INTO category(name) VALUES ('식단');
INSERT INTO category(name) VALUES ('질문');

INSERT INTO board(title, is_active, content, user_name, add_date, modified_date, category_name, count_like, count_dislike)
VALUES ('루틴 관리', true, '운동 루틴을 어떻게 관리하고 계신가요? 효과적인 루틴을 함께 나누고 피드백을 주고받으면 좋겠습니다.', 'user1', '2025-03-14', '2025-03-14', '질문', 0, 1),
('식단 팁', true, '체중 감량을 위한 식단 조절 방법에 대해 이야기해봅시다. 식단 관리의 꿀팁을 공유해주세요!', 'user', '2025-03-14', '2025-03-14', '질문', 1, 0);

INSERT INTO comment(content, add_date, board_id, user_name)
VALUES ('닭가슴살도 맛있는데, 맛과 브랜드가 다양하게 있죠', '2025-03-14', 2, 'user1');

INSERT INTO recomment(content, add_date, comment_id, user_name)
VALUES ('한 번 시켜봐야겠네요. 추천하시는 브랜드 있나요?', '2025-03-14', 1, 'user');

INSERT INTO schedule(start_date, last_date, title, content)
VALUES ('2025-01-01', '2025-01-12', '2025년 아마추어 청소년 대회', '2025년 아마추어 청소년 대회(체급별) 개인 참가 및 복싱장 참가 둘다 가능하니 많은 신청 바랍니다.'),
('2025-04-01', '2025-04-15', '2025년 단증 취득', '단증 취득 가능한 지역은 복싱 협회 일정에서 확인이 가능합니다.');

INSERT INTO great(board_id, user_name, reaction)
VALUES (2, 'user1', 'LIKE'), (1, 'user', 'DISLIKE');

INSERT INTO friend_request(receiver_name, sender_name, approved)
VALUES ('user2', 'user', false);

INSERT INTO friend_request(receiver_name, sender_name, approved)
VALUES ('user1', 'user', true);


INSERT INTO friendship(user_id, friend_id)
VALUES ('user', 'user1');