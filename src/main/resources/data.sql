INSERT INTO authority(authority_name) VALUES ('ROLE_USER');
INSERT INTO authority(authority_name) VALUES ('ROLE_ADMIN');

INSERT INTO category (name) VALUES ('전체');
INSERT INTO category (name) VALUES ('맛집');
INSERT INTO category (name) VALUES ('호텔');
INSERT INTO category (name) VALUES ('관광지');
INSERT INTO category (name) VALUES ('포토존');
INSERT INTO category (name) VALUES ('카페');

INSERT INTO user (
    username, password, real_name_m, real_name_f,
    email_m, email_f, birth_m, birth_f,
    phone_number_m, phone_number_f, ROLE_authority,
    add_date, d_day, last_login,
    alarm_alert, comment_alert, follow_alert, great_alert,
    event_alert, recommend_alert, recomment_alert, todo_alert
) VALUES (
    'admin001', '$2b$12$VOkWoMWTob2tHuXyyyh2hu14TPUH9iy6aB/NR1WkfqXvxxyAUnuW.', '관리자남', '관리자여',
    'adminm@example.com', 'adminf@example.com', '1990-01-01', '1991-02-02',
    '010-0000-0000', '010-1111-2222', 'ROLE_ADMIN',
    '2023-01-01', '2020-01-01', '2025-04-08',
    true, true, true, true,
    true, true, true, true
);

INSERT INTO user (
    username, password, real_name_m, real_name_f,
    email_m, email_f, birth_m, birth_f,
    phone_number_m, phone_number_f, ROLE_authority,
    add_date, d_day, last_login,
    alarm_alert, comment_alert, follow_alert, great_alert,
    event_alert, recommend_alert, recomment_alert, todo_alert
) VALUES (
    'couple001', '$2b$12$VOkWoMWTob2tHuXyyyh2hu14TPUH9iy6aB/NR1WkfqXvxxyAUnuW.', '민수', '지민',
    'minsu@example.com', 'jimin@example.com', '1995-05-10', '1996-08-15',
    '010-1234-5678', '010-8765-4321', 'ROLE_USER',
    '2024-04-01', '2022-06-15', '2025-04-08',
    true, true, true, true,
    true, true, true, true
);

INSERT INTO user (
    username, password, real_name_m, real_name_f,
    email_m, email_f, birth_m, birth_f,
    phone_number_m, phone_number_f, ROLE_authority,
    add_date, d_day, last_login,
    alarm_alert, comment_alert, follow_alert, great_alert,
    event_alert, recommend_alert, recomment_alert, todo_alert
) VALUES (
    'couple002', '$2b$12$VOkWoMWTob2tHuXyyyh2hu14TPUH9iy6aB/NR1WkfqXvxxyAUnuW.', '영호', '수아',
    'youngho@example.com', 'sua@example.com', '1994-07-12', '1995-10-30',
    '010-2345-6789', '010-9876-5432', 'ROLE_USER',
    '2024-03-01', '2022-05-20', '2025-04-08',
    true, true, true, true,
    true, true, true, true
);

INSERT INTO user (
    username, password, real_name_m, real_name_f,
    email_m, email_f, birth_m, birth_f,
    phone_number_m, phone_number_f, ROLE_authority,
    add_date, d_day, last_login,
    alarm_alert, comment_alert, follow_alert, great_alert,
    event_alert, recommend_alert, recomment_alert, todo_alert
) VALUES (
    'couple003', '$2b$12$VOkWoMWTob2tHuXyyyh2hu14TPUH9iy6aB/NR1WkfqXvxxyAUnuW.', '준호', '예림',
    'junho@example.com', 'yerim@example.com', '1993-03-05', '1994-11-22',
    '010-3456-7890', '010-1122-3344', 'ROLE_USER',
    '2024-02-15', '2021-12-01', '2025-04-08',
    true, true, true, true,
    true, true, true, true
);

INSERT INTO recommend_place (
    name, address, city, latitude, longitude, description, detail
) VALUES -- detail은 프론트에서 작성하여 보여줄 예정,
-- sql의 제한 글자수때문에 detail을 sql에서 작성X
(
    '한강공원', '서울특별시 영등포구 여의동로 330', '서울', 37.5284, 126.9326,
    '서울 대표 데이트 코스인 한강공원은 산책, 피크닉, 야경 감상 등 다양한 활동이 가능한 명소입니다.',
    NULL
),
(
    '카페 드림', '서울특별시 마포구 홍익로 25', '서울', 37.5551, 126.9258,
    '감성적인 인테리어와 다양한 디저트로 유명한 홍대 대표 카페입니다.',
    NULL
),
(
    '강릉 안목해변', '강원특별자치도 강릉시 창해로14번길 20', '강원', 37.7696, 128.9516,
    '바다와 커피 거리로 유명한 강릉 안목해변은 데이트 명소로 제격입니다.',
    NULL
);

INSERT INTO category_place (category_name, recommend_place_id) VALUES ('관광지', 1);
INSERT INTO category_place (category_name, recommend_place_id) VALUES ('포토존', 1);
INSERT INTO category_place (category_name, recommend_place_id) VALUES ('카페', 2);
INSERT INTO category_place (category_name, recommend_place_id) VALUES ('포토존', 3);

INSERT INTO tag (name) VALUES ('데이트'), ('야경'),
('감성'), ('바다'),
('뷰맛집'), ('인생샷'),
('드라이브'), ('산책'),
('실내'), ('더블데이트');

INSERT INTO album (
    title, add_date, username, latitude, longitude, location, visibility
) VALUES (
    '서울 야경 데이트', '2025-04-08 20:00:00', 'couple001',
    37.5284, 126.9326, '서울특별시 영등포구 여의동로 330', 'PRIVATE'
);

INSERT INTO album (
    title, add_date, username, latitude, longitude, location, visibility
) VALUES (
    '감성 카페 데이트', '2025-04-07 15:30:00', 'couple001',
    37.5551, 126.9258, '서울특별시 마포구 홍익로 25', 'PRIVATE'
);

INSERT INTO album_tag (album_id, tag_id) VALUES (1, 1);
INSERT INTO album_tag (album_id, tag_id) VALUES (1, 2);
INSERT INTO album_tag (album_id, tag_id) VALUES (1, 5);

INSERT INTO album_tag (album_id, tag_id) VALUES (2, 1);
INSERT INTO album_tag (album_id, tag_id) VALUES (2, 3);
INSERT INTO album_tag (album_id, tag_id) VALUES (2, 6);

INSERT INTO great (album_id, username) VALUES (1, 'couple002'), (1, 'couple003'),
(2, 'couple003');

INSERT INTO comment (content, add_date, album_id, username) VALUES
('야경 너무 예뻐요! 다음에 가봐야겠어요 ☺️', '2025-04-08 21:00:00', 1, 'couple002'),
('진짜 좋아요~ 저도 예전에 갔었어요!', '2025-04-08 21:15:00', 1, 'couple003');

INSERT INTO comment (content, add_date, album_id, username) VALUES
('와 여기 인테리어가 대박인데요?', '2025-04-07 16:00:00', 2, 'couple002'),
('디저트도 맛있어 보이네요 😋', '2025-04-07 16:05:00', 2, 'couple003');

