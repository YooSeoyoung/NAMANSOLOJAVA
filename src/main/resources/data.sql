INSERT INTO authority(authority_name) VALUES ('ROLE_USER');
INSERT INTO authority(authority_name) VALUES ('ROLE_ADMIN');

INSERT INTO category (name) VALUES ('전체');
INSERT INTO category (name) VALUES ('맛집');
INSERT INTO category (name) VALUES ('호텔');
INSERT INTO category (name) VALUES ('관광지');
INSERT INTO category (name) VALUES ('포토존');
INSERT INTO category (name) VALUES ('카페');

INSERT INTO media (media_url, media_type) VALUES
('https://cdn.namansolo.com/profile/admin001.jpg', 0),
('https://cdn.namansolo.com/profile/couple001.jpg', 0),
('https://cdn.namansolo.com/profile/couple002.jpg', 0),
('https://cdn.namansolo.com/profile/couple003.jpg', 0);

INSERT INTO user (
    username, password, real_name_m, real_name_f,
    email_m, email_f, birth_m, birth_f,
    phone_number_m, phone_number_f, ROLE_authority,
    add_date, d_day, last_login,
    alarm_alert, comment_alert, follow_alert, great_alert,
    event_alert, recommend_alert, recomment_alert, todo_alert, media_id
) VALUES (
    'admin', '$2b$12$VOkWoMWTob2tHuXyyyh2hu14TPUH9iy6aB/NR1WkfqXvxxyAUnuW.', '관리자남', '관리자여',
    'adminm@example.com', 'adminf@example.com', '1990-01-01', '1991-02-02',
    '010-0000-0000', '010-1111-2222', 'ROLE_ADMIN',
    '2023-01-01', '2020-01-01', '2025-04-08',
    true, true, true, true,
    true, true, true, true, 1),
(
     'steve12', '$2b$12$VOkWoMWTob2tHuXyyyh2hu14TPUH9iy6aB/NR1WkfqXvxxyAUnuW.', '스티브남', '스티브여',
     'steve1@example.com', 'steve2@example.com', '1990-01-01', '1991-02-02',
     '010-0000-0000', '010-1111-2222', 'ROLE_USER',
     '2023-01-01', '2020-01-01', '2025-04-08',
     true, true, true, true,
     true, true, true, true, 1
);

INSERT INTO user (
    username, password, real_name_m, real_name_f,
    email_m, email_f, birth_m, birth_f,
    phone_number_m, phone_number_f, ROLE_authority,
    add_date, d_day, last_login,
    alarm_alert, comment_alert, follow_alert, great_alert,
    event_alert, recommend_alert, recomment_alert, todo_alert, media_id
) VALUES (
    'couple001', '$2b$12$VOkWoMWTob2tHuXyyyh2hu14TPUH9iy6aB/NR1WkfqXvxxyAUnuW.', '민수', '지민',
    'minsu@example.com', 'jimin@example.com', '1995-05-10', '1996-08-15',
    '010-1234-5678', '010-8765-4321', 'ROLE_USER',
    '2024-04-01', '2022-06-15', '2025-04-08',
    true, true, true, true,
    true, true, true, true, 2
);

INSERT INTO user (
    username, password, real_name_m, real_name_f,
    email_m, email_f, birth_m, birth_f,
    phone_number_m, phone_number_f, ROLE_authority,
    add_date, d_day, last_login,
    alarm_alert, comment_alert, follow_alert, great_alert,
    event_alert, recommend_alert, recomment_alert, todo_alert, media_id
) VALUES (
    'couple002', '$2b$12$VOkWoMWTob2tHuXyyyh2hu14TPUH9iy6aB/NR1WkfqXvxxyAUnuW.', '영호', '수아',
    'youngho@example.com', 'sua@example.com', '1994-07-12', '1995-10-30',
    '010-2345-6789', '010-9876-5432', 'ROLE_USER',
    '2024-03-01', '2022-05-20', '2025-04-08',
    true, true, true, true,
    true, true, true, true, 3
);

INSERT INTO user (
    username, password, real_name_m, real_name_f,
    email_m, email_f, birth_m, birth_f,
    phone_number_m, phone_number_f, ROLE_authority,
    add_date, d_day, last_login,
    alarm_alert, comment_alert, follow_alert, great_alert,
    event_alert, recommend_alert, recomment_alert, todo_alert, media_id
) VALUES (
    'couple003', '$2b$12$VOkWoMWTob2tHuXyyyh2hu14TPUH9iy6aB/NR1WkfqXvxxyAUnuW.', '준호', '예림',
    'junho@example.com', 'yerim@example.com', '1993-03-05', '1994-11-22',
    '010-3456-7890', '010-1122-3344', 'ROLE_USER',
    '2024-02-15', '2021-12-01', '2025-04-08',
    true, true, true, true,
    true, true, true, true, 4
);

INSERT INTO follow (follower_name, following_name) VALUES ('couple002', 'couple001');

INSERT INTO follow (follower_name, following_name) VALUES ('couple003', 'couple001');

INSERT INTO follow (follower_name, following_name) VALUES ('couple001', 'couple002');

INSERT INTO follow (follower_name, following_name) VALUES ('couple003', 'couple002');

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
    37.5284, 126.9326, '서울특별시 영등포구 여의동로 330', 'PUBLIC'
);

INSERT INTO album (
    title, add_date, username, latitude, longitude, location, visibility
) VALUES (
    '감성 카페 데이트', '2025-04-07 15:30:00', 'couple001',
    37.5551, 126.9258, '서울특별시 마포구 홍익로 25', 'PUBLIC'
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

INSERT INTO recomment (content, add_date, comment_id, username) VALUES
('감사해요! 꼭 가보세요', '2025-04-08 21:30:00', 1, 'couple001'),
('와~ 반갑네요! 👍', '2025-04-08 21:35:00', 2, 'couple001'),
('진짜 분위기 좋아요~!', '2025-04-07 16:30:00', 3, 'couple001'),
('디저트 강추입니다 🍰', '2025-04-07 16:32:00', 4, 'couple001');

INSERT INTO official_event (event_date, event_title, editable, offset_days)
VALUES ('2025-02-14', '발렌타인데이', false, 0),
(CURRENT_DATE, '100일', false, 100);

INSERT INTO official_event (event_date, event_title, editable, offset_days)
VALUES ('2025-03-14', '화이트데이', false, 0),
('2025-11-11', '빼빼로데이', false, 0),
('2025-12-25', '크리스마스', false, 0);

INSERT INTO todo (
    title, start_date, last_date, final_edit_date,
    type, username, editable
) VALUES (
    '첫 만남 💕', '2022-06-15', '2022-06-15', '2025-04-09',
    'ANNIVERSARY', 'couple001', true
);

INSERT INTO todo (
    title, start_date, last_date, final_edit_date,
    type, username, editable
) VALUES (
    '여름 제주도 여행 🌴', '2023-08-01', '2023-08-05', '2025-04-09',
    'TRAVEL', 'couple001', true
);

INSERT INTO todo (
    title, start_date, last_date, final_edit_date,
    type, username, editable
) VALUES (
    '사귄 날 💑', '2022-05-20', '2022-05-20', '2025-04-09',
    'ANNIVERSARY', 'couple002', true
);

INSERT INTO todo (
    title, start_date, last_date, final_edit_date,
    type, username, editable
) VALUES (
    '놀이공원 데이트 🎡', '2024-10-03', '2024-10-03', '2025-04-09',
    'TRAVEL', 'couple003', true
);

-- album 1 관련 media
INSERT INTO media (media_url, media_type)
VALUES
('https://cdn.namansolo.com/media/couple001/night_view_01.jpg', 0),
('https://cdn.namansolo.com/media/couple001/night_view_02.mp4', 1);

-- album 2 관련 media
INSERT INTO media (media_url, media_type)
VALUES
('https://cdn.namansolo.com/media/couple001/cafe_01.jpg', 0);

-- todo 2 관련 media
INSERT INTO media (media_url, media_type)
VALUES
('https://cdn.namansolo.com/media/couple001/jeju_trip_01.jpg', 0),
('https://cdn.namansolo.com/media/couple001/jeju_trip_02.jpg', 0),
('https://cdn.namansolo.com/media/couple001/jeju_trip_jeep.mp4', 1);

-- recommend place 관련 미디어
INSERT INTO media (media_url, media_type) VALUES
('https://cdn.namansolo.com/media/place/hanriver.jpg', 0),
('https://cdn.namansolo.com/media/place/cafe_dream.jpg', 0),
('https://cdn.namansolo.com/media/place/gangneung_beach.jpg', 0);

-- album_media 연결 (media_id: 1, 2 -> album_id: 1), (media_id: 3 -> album_id: 2)
INSERT INTO album_media (media_id, album_id) VALUES (5, 1);
INSERT INTO album_media (media_id, album_id) VALUES (6, 1);
INSERT INTO album_media (media_id, album_id) VALUES (7, 2);

-- todo_media 연결 (media_id: 4~6 -> todo_id: 2)
INSERT INTO todo_media (media_id, todo_id) VALUES (8, 2);
INSERT INTO todo_media (media_id, todo_id) VALUES (9, 2);
INSERT INTO todo_media (media_id, todo_id) VALUES (10, 2);

-- recommend_place_media 연결
INSERT INTO recommend_place_media (recommend_place_id, media_id) VALUES
(1, 11),
(2, 12),
(3, 13);

INSERT INTO alarm (username, type, message, add_date, is_read, weather_info)
VALUES (
    'couple001', 'COMMENT', 'couple002님이 앨범에 댓글을 남겼습니다.',
    '2025-04-09 10:15:00', false, null
);

INSERT INTO alarm (username, type, message, add_date, is_read, weather_info)
VALUES (
    'couple001', 'GREAT', 'couple003님이 앨범을 좋아합니다.',
    '2025-04-09 10:16:00', false, null
);

INSERT INTO alarm (username, type, message, add_date, is_read, weather_info)
VALUES (
    'couple001', 'TODO', '여름 제주도 여행의 날씨 정보가 도착했습니다.',
    '2025-04-09 09:00:00', false, '맑음, 최고기온 24°C, 강수 확률 10%'
);

INSERT INTO alarm (username, type, message, add_date, is_read, weather_info)
VALUES (
    'couple002', 'FOLLOW', 'couple003님이 당신을 팔로우했습니다.',
    '2025-04-09 11:00:00', false, null
);

-- 주의: 향후 관리 목적이나 차단 필요 시 저장한 예시 데이터
-- 실시간 검색과는 별도로 내부 캐싱/추천용으로 쓰일 수 있음
INSERT INTO event_present (
    title, description, image_url, shopping_url, price
) VALUES (
    '커플 무드등',
    '분위기 좋은 감성 커플 무드등입니다. 인테리어 효과도 뛰어납니다.',
    'https://cdn.example.com/images/moonlight.jpg',
    'https://shopping.naver.com/product/abc123',
    '32,900원'
);
-- ****** NOTE:
-- 이 데이터는 네이버 API 검색 결과와는 무관하게,
-- 서비스 내에서 수동 추천하거나, 특정 키워드 차단/우선 노출 용도로 사용될 수 있습니다.
-- 실시간 검색이 우선이며, 저장형 데이터를 사용자에게 보여줄 경우 반드시 표시 또는 분리 필요
INSERT INTO media (media_url, media_type)
VALUES ('https://cdn.example.com/video.mp4', 1);

