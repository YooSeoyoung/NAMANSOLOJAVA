INSERT INTO authority(authority_name) VALUES ('ROLE_USER');
INSERT INTO authority(authority_name) VALUES ('ROLE_ADMIN');

INSERT INTO category (name) VALUES ('전체');
INSERT INTO category (name) VALUES ('맛집');
INSERT INTO category (name) VALUES ('호텔');
INSERT INTO category (name) VALUES ('관광지');
INSERT INTO category (name) VALUES ('포토존');
INSERT INTO category (name) VALUES ('카페');

INSERT INTO user (user_name, add_date, email_f, email_m, password, phone_number_f, phone_number_m, real_name_f, real_name_m, user_authority)
VALUES ('couple1', NOW(), 'f1@example.com', 'm1@example.com', 'pass1', '01011112222', '01033334444', '여자1', '남자1', 'USER');

INSERT INTO user (user_name, add_date, email_f, email_m, password, phone_number_f, phone_number_m, real_name_f, real_name_m, user_authority)
VALUES ('couple2', NOW(), 'f2@example.com', 'm2@example.com', 'pass2', '01055556666', '01077778888', '여자2', '남자2', 'USER');

INSERT INTO recommend_place (name, address, description, latitude, longitude, category_name)
VALUES ('서울맛집1', '서울 강남구 어딘가', '서울 맛집 추천 설명', 37.4979, 127.0276, '맛집'),
('부산호텔1', '부산 해운대구', '부산 호텔 추천 설명', 35.1587, 129.1604, '호텔');

INSERT INTO event_place (title, description, event_date, category_name)
VALUES ('100일 기념 여행지', '100일 추천 여행지 설명', '2025-05-01', '관광지');

INSERT INTO feed (add_time, content, visibility, user_name)
VALUES (NOW(), '우리의 첫 데이트 기록!', 'PUBLIC', 'couple1');

INSERT INTO tag (name) VALUES ('데이트');
INSERT INTO tag (name) VALUES ('여행');

INSERT INTO follow (add_time, follower_name, following_name) VALUES (NOW(), 'couple1', 'couple2');
