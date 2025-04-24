INSERT INTO authority(authority_name) VALUES ('ROLE_USER');
INSERT INTO authority(authority_name) VALUES ('ROLE_ADMIN');

INSERT INTO category (name) VALUES ('전체');
INSERT INTO category (name) VALUES ('맛집');
INSERT INTO category (name) VALUES ('호텔');
INSERT INTO category (name) VALUES ('관광지');
INSERT INTO category (name) VALUES ('포토존');
INSERT INTO category (name) VALUES ('카페');

INSERT INTO media (media_url, media_type) VALUES
('https://play-lh.googleusercontent.com/38AGKCqmbjZ9OuWx4YjssAz3Y0DTWbiM5HB0ove1pNBq_o9mtWfGszjZNxZdwt_vgHo=w240-h480-rw', 0),
('https://play-lh.googleusercontent.com/38AGKCqmbjZ9OuWx4YjssAz3Y0DTWbiM5HB0ove1pNBq_o9mtWfGszjZNxZdwt_vgHo=w240-h480-rw', 0),
('https://play-lh.googleusercontent.com/38AGKCqmbjZ9OuWx4YjssAz3Y0DTWbiM5HB0ove1pNBq_o9mtWfGszjZNxZdwt_vgHo=w240-h480-rw', 0),
('https://play-lh.googleusercontent.com/38AGKCqmbjZ9OuWx4YjssAz3Y0DTWbiM5HB0ove1pNBq_o9mtWfGszjZNxZdwt_vgHo=w240-h480-rw', 0);

INSERT INTO user (
    username, password, real_name_m, real_name_f,
    email_m, email_f, birth_m, birth_f,
    phone_number_m, phone_number_f, ROLE_authority,
    city, add_date, d_day, last_login,
    alarm_alert, comment_alert, follow_alert, great_alert,
    event_alert, recommend_alert, recomment_alert,
    todo_alert, media_id
) VALUES (
    'admin', '$2b$12$VOkWoMWTob2tHuXyyyh2hu14TPUH9iy6aB/NR1WkfqXvxxyAUnuW.', '관리자남', '관리자여',
    'adminm@example.com', 'adminf@example.com', '1990-01-01', '1991-02-02',
    '010-0000-0000', '010-0000-0001', 'ROLE_ADMIN', '서울',
    '2023-01-01', '2020-01-01', '2025-04-08',
    true, true, true, true,
    true, true, true, true, 1),
(
     'steve12', '$2b$12$VOkWoMWTob2tHuXyyyh2hu14TPUH9iy6aB/NR1WkfqXvxxyAUnuW.', '스티브남', '스티브여',
     'steve1@example.com', 'steve2@example.com', '1990-01-01', '1991-02-02',
     '010-0000-0003', '010-0000-0002', 'ROLE_USER', '울산',
     '2023-01-01', '2020-01-01', '2025-04-08',
     true, true, true, true,
     true, true, true, true, 1);

INSERT INTO user (
    username, password, real_name_m, real_name_f,
    email_m, email_f, birth_m, birth_f,
    phone_number_m, phone_number_f, ROLE_authority, city,
    add_date, d_day, last_login,
    alarm_alert, comment_alert, follow_alert, great_alert,
    event_alert, recommend_alert, recomment_alert,
    todo_alert, media_id
) VALUES (
    'couple001', '$2b$12$VOkWoMWTob2tHuXyyyh2hu14TPUH9iy6aB/NR1WkfqXvxxyAUnuW.', '민수', '지민',
    'minsu@example.com', 'jimin@example.com', '1995-05-10', '1996-08-15',
    '010-0000-0004', '010-0000-0005', 'ROLE_USER', '대전',
    '2024-04-01', '2022-06-15', '2025-04-08',
    true, true, true, true,
    true, true, true, true, 2);

INSERT INTO user (
    username, password, real_name_m, real_name_f,
    email_m, email_f, birth_m, birth_f,
    phone_number_m, phone_number_f, ROLE_authority,city,
    add_date, d_day, last_login,
    alarm_alert, comment_alert, follow_alert, great_alert,
    event_alert, recommend_alert, recomment_alert,
    todo_alert, media_id
) VALUES (
    'couple002', '$2b$12$VOkWoMWTob2tHuXyyyh2hu14TPUH9iy6aB/NR1WkfqXvxxyAUnuW.', '영호', '수아',
    'youngho@example.com', 'sua@example.com', '1994-07-12', '1995-10-30',
    '010-0000-0006', '010-0000-0007', 'ROLE_USER', '부산',
    '2024-03-01', '2022-05-20', '2025-04-08',
    true, true, true, true,
    true, true, true, true, 3);

INSERT INTO user (
    username, password, real_name_m, real_name_f,
    email_m, email_f, birth_m, birth_f,
    phone_number_m, phone_number_f, ROLE_authority, city,
    add_date, d_day, last_login,
    alarm_alert, comment_alert, follow_alert, great_alert,
    event_alert, recommend_alert, recomment_alert,
    todo_alert, media_id
) VALUES (
    'couple003', '$2b$12$VOkWoMWTob2tHuXyyyh2hu14TPUH9iy6aB/NR1WkfqXvxxyAUnuW.', '준호', '예림',
    'junho@example.com', 'yerim@example.com', '1993-03-05', '1994-11-22',
    '010-0000-0008', '010-0000-0009', 'ROLE_USER', '제주',
    '2024-02-15', '2021-12-01', '2025-04-08',
    true, true, true, true,
    true, true, true, true, 4
);

INSERT INTO follow (follower_name, following_name) VALUES ('couple002', 'couple001');

INSERT INTO follow (follower_name, following_name) VALUES ('couple003', 'couple001');

INSERT INTO follow (follower_name, following_name) VALUES ('couple001', 'couple002');

INSERT INTO follow (follower_name, following_name) VALUES ('couple003', 'couple002');

INSERT INTO official_event (event_date, event_title, editable, offset_days)
VALUES ('2025-02-14', '발렌타인데이', false, 0),
(CURRENT_DATE, '100일', false, 100),
(CURRENT_DATE, '1주년', false, 365);

INSERT INTO official_event (event_date, event_title, editable, offset_days)
VALUES ('2025-03-14', '화이트데이', false, 0),
('2025-11-11', '빼빼로데이', false, 0),
('2025-12-25', '크리스마스', false, 0);

INSERT INTO todo (
    title, start_date, last_date, final_edit_date,
    type, username, color, editable
) VALUES (
    '첫 만남 💕', '2022-06-15', '2022-06-15', '2025-04-09',
    'ANNIVERSARY', 'couple001', '#ffc0cb', true
);

INSERT INTO todo (
    title, start_date, last_date, final_edit_date,
    type, username, color, editable
) VALUES (
    '여름 제주도 여행 🌴', '2023-08-01', '2023-08-05', '2025-04-09',
    'TRAVEL', 'couple001', '#ffc0cb', true
);

INSERT INTO todo (
    title, start_date, last_date, final_edit_date,
    type, username, color, editable
) VALUES (
    '사귄 날 💑', '2022-05-20', '2022-05-20', '2025-04-09',
    'ANNIVERSARY', 'couple002', '#ffc0cb', true
);

INSERT INTO todo (
    title, start_date, last_date, final_edit_date,
    type, username, color, editable
) VALUES (
    '놀이공원 데이트 🎡', '2024-10-03', '2024-10-03', '2025-04-09',
    'TRAVEL', 'couple003', '#ffc0cb', true
);

-- todo 2 관련 media
INSERT INTO media (media_url, media_type)
VALUES
('https://mblogthumb-phinf.pstatic.net/MjAyNTAxMTZfMjk4/MDAxNzM2OTg5MTcwNjQw.Qc7XPSWMa9aLOjHiKFuMnk0ROZRpppDT8NDKz2kgu5Qg.GTBcURVGZgeps1mhk0OfgrgyWr1O3TJj_ypoGtyzR8kg.PNG/image.png?type=w800', 0),
('https://mblogthumb-phinf.pstatic.net/MjAyMTEyMTNfMTQ3/MDAxNjM5MzkxMjM3NTUw.aaekK8dYRosCgU1jT-RxnPe3LvokCB55dXhf3YMbA54g.QDSbyG8Fo47YSLw-s8wxGkkNXzYS0HbsiAPeVL8mBwog.JPEG.ichufs/DSC_3609.jpg?type=w800', 0),
('https://cdn.pixabay.com/video/2019/08/29/26356-357839112_large.mp4', 1);

-- todo_media 연결 (media_id: 4~6 -> todo_id: 2)
INSERT INTO todo_media (media_id, todo_id) VALUES (5, 2);
INSERT INTO todo_media (media_id, todo_id) VALUES (6, 2);

-- 알람
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



INSERT INTO tag (name) VALUES
 ('#데이트'),('#커플'),('#프로필'),('#바다'),('#뷰맛집'), ('#인생샷'),('#봄 소풍'), ('#산책'), ('#사랑'), ('#행복'),
 ('#돗자리'),('#노을'),('#떡볶이'), ('#대기중'),('#롯데월드'), ('#파전'),('#스쿠버다이빙'), ('#수목원'), ('#인생네컷'), ('#파크'),
 ('#캠핑'),('#1박2일'),('#더워'), ('#열기구'),('#무서워'), ('#사랑해'),('#폭싹'), ('#속았수다'), ('#패러디'), ('#등산'),
  ('#힘들다'),('#5분만더..'),('#스케이트'), ('#엉덩방아'),('#근육통'), ('#영화관'),('#먹방중'), ('#눈물'), ('#PC방'), ('#여친'),
   ('#라면'), ('#롤');

INSERT INTO album (title,add_date, username, latitude, longitude, location, visibility) VALUES
('커플 프로필 찰칵♥','2025-04-08 20:00:00','couple001',36.326319, 127.422978, '대전 중구 중앙로112번길 24', 'PUBLIC'),
('둘이 하는 바다 산책','2025-04-08 10:30:00', 'couple002', 37.7950, 128.9070, '강원특별자치도 강릉시 강문동 산1', 'PUBLIC'),
('떡볶이 먹고 같이 롯데월드★','2025-04-10 12:06:00', 'couple003',37.5115,127.0967, '서울 송파구 올림픽로 240', 'PUBLIC'),
('봄 나들이','2025-04-11 20:30:00','steve12',36.3510,127.3848, '대전 서구 둔산대로 169', 'PUBLIC'),
('비 오는 날에는 파전','2025-04-12 20:11:00', 'couple001', 36.773123,126.319272, '충남 태안군 태안읍 시장1길 34', 'PUBLIC'),
('베어트리파크 데이트','2025-04-13 07:50:00', 'couple002', 36.521,127.2873, '세종 전동면 신송로 217', 'PUBLIC'),
('노을을 보면서 즐기는 중','2025-04-14 23:11:00', 'couple003', 37.67861,129.05167, '강원 강릉시 강동면 정동진리', 'PUBLIC'),
('맛집 기달리는 중..','2025-04-15 21:09:00', 'steve12',37.5141939,127.1040148, '서울특별시 송파구 올림픽로 300 롯데월드몰 1층', 'PUBLIC'),
('캠핑(노숙 라이프)','2025-04-11 11:40:00', 'couple001',37.9275,127.0567, '경기 동두천시 안흥로 78', 'PUBLIC'),
('생애 첫 열기구','2025-04-16 19:10:00', 'couple002',35.8430,128.6263, '대구 수성구 들안로 285-6 (수성동2가)', 'PUBLIC'),
('난 애순이 넌 관식이','2025-04-23 16:20:00', 'couple003',36.3926,127.3120, '대전광역시 유성구 노은2동 771-51', 'PUBLIC'),
('등산은 너무 힘들어','2025-04-20 18:03:00', 'couple003',35.1461,126.9995, '광주 북구 무등산천왕봉길 792', 'PUBLIC'),
('콰당잼','2025-04-05 22:40:00', 'steve12',37.5111,127.0982, '서울 송파구 올림픽로 240', 'PUBLIC'),
('화장 안하고 오길 잘했다ㅠㅠ','2025-04-17 23:15:00', 'couple001',36.8155,127.1130, '충남 천안시 서북구 공원로 196', 'PUBLIC'),
('여친 이기니까 좋아??','2025-04-10 15:30:00', 'couple002',37.6485,127.0331, '서울 도봉구 도봉로 468 홍일빌딩 206호', 'PUBLIC');



INSERT INTO album_tag (album_id, tag_id) VALUES (1, 1),(1, 2),(1, 2),(1,19);
INSERT INTO album_tag (album_id, tag_id) VALUES (2, 4),(2, 5),(2, 1),(2,17);
INSERT INTO album_tag (album_id, tag_id) VALUES (3, 13),(3, 15),(3, 2);
INSERT INTO album_tag (album_id, tag_id) VALUES (4, 6),(4, 7),(4, 8);
INSERT INTO album_tag (album_id, tag_id) VALUES (5, 1),(5, 6),(5, 8);
INSERT INTO album_tag (album_id, tag_id) VALUES (6, 1),(6,20),(6, 8);
INSERT INTO album_tag (album_id, tag_id) VALUES (7, 2),(7,6),(7,12);
INSERT INTO album_tag (album_id, tag_id) VALUES (8,1),(8, 2),(8,14);

INSERT INTO album_tag (album_id, tag_id) VALUES (9,21),(9, 22),(9,23);
INSERT INTO album_tag (album_id, tag_id) VALUES (10,24),(10, 25),(10,26);
INSERT INTO album_tag (album_id, tag_id) VALUES (11,27),(11, 28),(11,29);
INSERT INTO album_tag (album_id, tag_id) VALUES (12,30),(12,31),(12,32);
INSERT INTO album_tag (album_id, tag_id) VALUES (13,33),(13, 34),(13,35);
INSERT INTO album_tag (album_id, tag_id) VALUES (14,36),(14, 37),(14,38);
INSERT INTO album_tag (album_id, tag_id) VALUES (15,39),(15, 40),(15,41),(15,42);


--앨범 시연용 이미지들
INSERT INTO media (media_url, media_type) VALUES
('/api/album/download/1.jpg',0),('/api/album/download/7.jpg',0),('/api/album/download/8.jpg',0),
('/api/album/download/9.jpg',0),('/api/album/download/10.jpg',0),('/api/album/download/11.jpg',0),
('/api/album/download/12.jpg',0),('/api/album/download/13.jpg',0),('/api/album/download/14.jpg',0),
('/api/album/download/15.jpg',0),('/api/album/download/16.jpg',0),('/api/album/download/17.jpg',0),
('/api/album/download/18.jpg',0),('/api/album/download/19.jpg',0),('/api/album/download/20.jpg',0),
('/api/album/download/21.jpg',0),('/api/album/download/22.jpg',0),('/api/album/download/23.jpg',0),
('/api/album/download/2.mp4',1),('/api/album/download/3.mp4',1),('/api/album/download/4.mp4',1),
('/api/album/download/5.mp4',1),('/api/album/download/6.mp4',1),
('/api/album/download/24.mp4',1),('/api/album/download/28.jpg',0),('/api/album/download/29.jpg',0),
('/api/album/download/25.mp4',1),('/api/album/download/30.jpg',0),('/api/album/download/31.jpg',0),
('/api/album/download/26.mp4',1),('/api/album/download/32.jpg',0),('/api/album/download/33.jpg',0),
('/api/album/download/27.mp4',1),('/api/album/download/34.jpg',0),('/api/album/download/35.jpg',0),
('/api/album/download/36.jpg',0),('/api/album/download/37.mp4',1),('/api/album/download/38.jpg',0),
('/api/album/download/39.jpg',0),('/api/album/download/40.jpg',0),
('/api/album/download/41.jpg',0),('/api/album/download/42.jpg',0);


---- album_media 연결 (media_id: 1, 2 -> album_id: 1), (media_id: 3 -> album_id: 2)
INSERT INTO album_media (media_id, album_id) VALUES
  (8, 1), (11, 1), (20, 1),
  (26, 2), (9, 2),
  (14, 3), (18, 3),
  (28, 4), (16, 4), (12, 4),
  (15, 5),
  (22, 6), (24, 6),
  (29, 7), (21, 7),
  (23, 8), (13, 8),
  (31, 9), (32, 9), (33, 9),
  (34, 10), (35, 10), (36, 10),
  (37, 11), (38, 11), (39, 11),
  (40, 12), (41, 12), (42, 12),
  (43, 13), (44, 13), (45, 13),
  (46, 14), (47, 14),
  (48, 15),(49, 15);


-- Step 1. 미디어 등록 (사진 있는 거로!)
INSERT INTO media (id, media_type, media_url)
VALUES (3001, 0, '/api/recommend_place/download/0a369d24-610c-4287-b169-f5a399a778b4_baby2.jpg');

-- Step 2. 장소 등록
INSERT INTO recommend_place (
    id, address, city, description, detail, latitude, longitude, name
) VALUES (
    30001, '충남 천안시 신방동 200', '충청남도', '연인과 함께 걷기 좋은 분위기 좋은 골목', '포토존 많음',
    36.8123, 127.1155, '골목데이트'
);

-- Step 3. 장소 <-> 미디어 연결
INSERT INTO recommend_place_media (media_id, recommend_place_id)
VALUES (3001, 30001);

-- Step 4. 장소 <-> 카테고리 연결
INSERT INTO category_place (id, category_name, recommend_place_id)
VALUES (3001, '포토존', 30001);



