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


-- Step 1. 미디어 등록 (사진 있는 거로!) 50번부터
INSERT INTO media (media_url,media_type) VALUES
-- 제주
-- 맛집
 ('/api/recommend_place/download/Jeju/SeasonTable.jpg', 0),
 ('/api/recommend_place/download/Jeju/SeasonTable1.jpg', 0),
 ('/api/recommend_place/download/Jeju/SeasonTable2.jpg', 0),
 ('/api/recommend_place/download/Jeju/SeasonTable3.jpg', 0),
 ('/api/recommend_place/download/Jeju/FlatfishdaPyoseon.jpg', 0),
 ('/api/recommend_place/download/Jeju/FlatfishdaPyoseon1.jpg', 0),
 ('/api/recommend_place/download/Jeju/FlatfishdaPyoseon2.jpg', 0),
 ('/api/recommend_place/download/Jeju/Gwanghae.jpg', 0),
 ('/api/recommend_place/download/Jeju/Gwanghae1.jpg', 0),
 ('/api/recommend_place/download/Jeju/Gwanghae2.jpg', 0),
 ('/api/recommend_place/download/Jeju/Gwanghae3.jpg', 0),
 ('/api/recommend_place/download/Jeju/GimnyeongAlright.jpg', 0),
 ('/api/recommend_place/download/Jeju/GimnyeongAlright1.jpg', 0),
 ('/api/recommend_place/download/Jeju/GimnyeongAlright2.jpg', 0),
 ('/api/recommend_place/download/Jeju/RomanticShrimpAewol.jpg', 0),
 ('/api/recommend_place/download/Jeju/RomanticShrimpAewol1.jpg', 0),
 ('/api/recommend_place/download/Jeju/RomanticShrimpAewol2.jpg', 0),
 ('/api/recommend_place/download/Jeju/JejuHwadoBlackPork.jpg', 0),
 ('/api/recommend_place/download/Jeju/JejuHwadoBlackPork1.jpg', 0),
 ('/api/recommend_place/download/Jeju/JejuHwadoBlackPork2.jpg', 0),
 -- 카페
 ('/api/recommend_place/download/Jeju/TheCliff.jpg', 0),
 ('/api/recommend_place/download/Jeju/TheCliff1.jpg', 0),
 ('/api/recommend_place/download/Jeju/TheCliff2.jpg', 0),
 ('/api/recommend_place/download/Jeju/MonocleJeju.jpg', 0),
 ('/api/recommend_place/download/Jeju/MonocleJeju1.jpg', 0),
 ('/api/recommend_place/download/Jeju/MonocleJeju2.jpg', 0),
 ('/api/recommend_place/download/Jeju/Antoinette.jpg', 0),
 ('/api/recommend_place/download/Jeju/Antoinette1.jpg', 0),
 ('/api/recommend_place/download/Jeju/Antoinette2.jpg', 0),
 ('/api/recommend_place/download/Jeju/Oren.jpg', 0),
 ('/api/recommend_place/download/Jeju/Oren1.jpg', 0),
 ('/api/recommend_place/download/Jeju/Oren2.jpg', 0),
 ('/api/recommend_place/download/Jeju/WayviewHyeopjaeOcean.jpg', 0),
 ('/api/recommend_place/download/Jeju/WayviewHyeopjaeOcean1.jpg', 0),
 ('/api/recommend_place/download/Jeju/WayviewHyeopjaeOcean2.jpg', 0),
 ('/api/recommend_place/download/Jeju/TeamBlow.jpg', 0),
 ('/api/recommend_place/download/Jeju/TeamBlow1.jpg', 0),
 ('/api/recommend_place/download/Jeju/TeamBlow2.jpg', 0),
 -- 숙소
 ('/api/recommend_place/download/Jeju/646MetersPerSecond.jpg', 0),
 ('/api/recommend_place/download/Jeju/646MetersPerSecond1.jpg', 0),
 ('/api/recommend_place/download/Jeju/646MetersPerSecond2.jpg', 0),
 ('/api/recommend_place/download/Jeju/RaffiaHouse.jpg', 0),
 ('/api/recommend_place/download/Jeju/RaffiaHouse1.jpg', 0),
 ('/api/recommend_place/download/Jeju/RaffiaHouse2.jpg', 0),
 ('/api/recommend_place/download/Jeju/AtCorner.jpg', 0),
 ('/api/recommend_place/download/Jeju/AtCorner1.jpg', 0),
 ('/api/recommend_place/download/Jeju/AtCorner2.jpg', 0),
 ('/api/recommend_place/download/Jeju/JejuTimeTravelHealingPension.jpg', 0),
 ('/api/recommend_place/download/Jeju/JejuTimeTravelHealingPension1.jpg', 0),
 ('/api/recommend_place/download/Jeju/JejuTimeTravelHealingPension2.jpg', 0),
 ('/api/recommend_place/download/Jeju/ConstHyeopjae.jpg', 0),
 ('/api/recommend_place/download/Jeju/ConstHyeopjae1.jpg', 0),
 ('/api/recommend_place/download/Jeju/ConstHyeopjae2.jpg', 0),
 ('/api/recommend_place/download/Jeju/ConstHyeopjae3.jpg', 0),
 ('/api/recommend_place/download/Jeju/LogwoodPark.jpg', 0),
 ('/api/recommend_place/download/Jeju/LogwoodPark1.jpg', 0),
 ('/api/recommend_place/download/Jeju/LogwoodPark2.jpg', 0),
 -- 관광지
 ('/api/recommend_place/download/Jeju/Lunapole.jpg', 0),
 ('/api/recommend_place/download/Jeju/Lunapole1.jpg', 0),
 ('/api/recommend_place/download/Jeju/Lunapole2.jpg', 0),
 ('/api/recommend_place/download/Jeju/ManoirBlanc.jpg', 0),
 ('/api/recommend_place/download/Jeju/ManoirBlanc1.jpg', 0),
 ('/api/recommend_place/download/Jeju/ManoirBlanc2.jpg', 0),
 ('/api/recommend_place/download/Jeju/AivaGarden.jpg', 0),
 ('/api/recommend_place/download/Jeju/AivaGarden1.jpg', 0),
 ('/api/recommend_place/download/Jeju/AivaGarden2.jpg', 0),
 ('/api/recommend_place/download/Jeju/AquaPlanetJeju.jpg', 0),
 ('/api/recommend_place/download/Jeju/AquaPlanetJeju1.jpg', 0),
 ('/api/recommend_place/download/Jeju/AquaPlanetJeju2.jpg', 0),
 ('/api/recommend_place/download/Jeju/CamelliaHill.jpg', 0),
 ('/api/recommend_place/download/Jeju/CamelliaHill1.jpg', 0),
 ('/api/recommend_place/download/Jeju/CamelliaHill2.jpg', 0),
 ('/api/recommend_place/download/Jeju/FigureMuseum.jpg', 0),
 ('/api/recommend_place/download/Jeju/FigureMuseum1.jpg', 0),
 ('/api/recommend_place/download/Jeju/FigureMuseum2.jpg', 0),
 -- 포토존
 ('/api/recommend_place/download/Jeju/SagyeCoast.jpg', 0),
 ('/api/recommend_place/download/Jeju/SagyeCoast1.jpg', 0),
 ('/api/recommend_place/download/Jeju/SagyeCoast2.jpg', 0),
 ('/api/recommend_place/download/Jeju/SananCliffPoint.jpg', 0),
 ('/api/recommend_place/download/Jeju/SananCliffPoint1.jpg', 0),
 ('/api/recommend_place/download/Jeju/SananCliffPoint2.jpg', 0),
 ('/api/recommend_place/download/Jeju/IhoTewooBeach.jpg', 0),
 ('/api/recommend_place/download/Jeju/IhoTewooBeach1.jpg', 0),
 ('/api/recommend_place/download/Jeju/IhoTewooBeach2.jpg', 0),
 ('/api/recommend_place/download/Jeju/JeolmulForestPark.jpg', 0),
 ('/api/recommend_place/download/Jeju/JeolmulForestPark1.jpg', 0),
 ('/api/recommend_place/download/Jeju/JeolmulForestPark2.jpg', 0),
 ('/api/recommend_place/download/Jeju/JejuFolkVillage.jpg', 0),
 ('/api/recommend_place/download/Jeju/JejuFolkVillage1.jpg', 0),
 ('/api/recommend_place/download/Jeju/JejuFolkVillage2.jpg', 0),
-- 전라남도
-- 맛집
 ('/api/recommend_place/download/Jeollanamdo/KkotdolSoyCrab.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/KkotdolSoyCrab1.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/KkotdolSoyCrab2.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/Nanulteo.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/Nanulteo1.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/Nanulteo2.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/RomanticPocha18.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/RomanticPocha181.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/RomanticPocha182.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/MadameSo.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/MadameSo1.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/MadameSo2.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/SuniKitchenTable.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/SuniKitchenTable1.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/SuniKitchenTable2.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/YeosuSoyCrab.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/YeosuSoyCrab1.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/YeosuSoyCrab2.jpg', 0),
 -- 카페
 ('/api/recommend_place/download/Jeollanamdo/GoodysPie.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/GoodysPie1.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/GoodysPie2.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/MoyfinOcean.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/MoyfinOcean1.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/MoyfinOcean2.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/BrewWorks.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/BrewWorks1.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/BrewWorks2.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/CLBBakery.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/CLBBakery1.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/CLBBakery2.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/ChogaoYeosuGeobukseonBranch.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/ChogaoYeosuGeobukseonBranch1.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/ChogaoYeosuGeobukseonBranch2.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/Colombang.png', 0),
 ('/api/recommend_place/download/Jeollanamdo/Colombang1.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/Colombang2.jpg', 0),
 -- 호텔
 ('/api/recommend_place/download/Jeollanamdo/TheArkResort.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/TheArkResort1.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/TheArkResort2.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/JeonnamShilla.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/JeonnamShilla1.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/JeonnamShilla2.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/SolBeachJindo.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/SolBeachJindo1.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/SolBeachJindo2.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/YeosuVenezia.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/YeosuVenezia1.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/YeosuVenezia2.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/UTopMarina.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/UTopMarina1.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/UTopMarina2.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/HiddenBay.png', 0),
 ('/api/recommend_place/download/Jeollanamdo/HiddenBay1.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/HiddenBay2.png', 0),
 -- 관광지
 ('/api/recommend_place/download/Jeollanamdo/DaehanTeaBoseong.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/DaehanTeaBoseong1.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/DaehanTeaBoseong2.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/MetasequoiaAve.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/MetasequoiaAve1.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/MetasequoiaAve2.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/MokpoSky.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/MokpoSky1.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/MokpoSky2.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/SuncheonDrama.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/SuncheonDrama1.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/SuncheonDrama2.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/AquaPlanet.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/AquaPlanet1.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/AquaPlanet2.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/UworldLuge.png', 0),
 ('/api/recommend_place/download/Jeollanamdo/UworldLuge1.png', 0),
 ('/api/recommend_place/download/Jeollanamdo/UworldLuge2.jpg', 0),
 -- 포토존
 ('/api/recommend_place/download/Jeollanamdo/NoktemaReMediaArt.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/NoktemaReMediaArt1.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/NoktemaReMediaArt2.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/MetaProvence.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/MetaProvence.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/MetaProvence.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/SuncheonmanWetland.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/SuncheonmanWetland1.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/SuncheonmanWetland2.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/SinanTulipPark.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/SinanTulipPark1.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/SinanTulipPark2.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/YeosuOceanRailBike.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/YeosuOceanRailBike1.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/YeosuOceanRailBike2.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/CheonggyeJuniperForest.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/CheonggyeJuniperForest1.jpg', 0),
 ('/api/recommend_place/download/Jeollanamdo/CheonggyeJuniperForest2.jpg', 0),
 -- 전북 맛집
 ('/api/recommend_place/download/Jeollabukdo/GeumseongJjukkumi.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/GeumseongJjukkumi1.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/GeumseongJjukkumi2.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/GeumamSoba.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/GeumamSoba1.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/GeumamSoba2.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/Dopamen.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/Dopamen1.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/Dopamen2.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/SigolGamasotJip.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/SigolGamasotJip1.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/SigolGamasotJip2.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/JibokJeom.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/JibokJeom1.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/JibokJeom2.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/HangukJeom.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/HangukJeom1.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/HangukJeom2.jpg', 0),
 -- 카페
 ('/api/recommend_place/download/Jeollabukdo/GeumjongBakery.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/GeumjongBakery1.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/GeumjongBakery2.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/Maraedang.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/Maraedang1.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/Maraedang2.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/Iseongdang.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/Iseongdang1.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/Iseongdang2.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/Iso.png', 0),
 ('/api/recommend_place/download/Jeollabukdo/Iso1.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/Iso2.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/Cafe1938Mansion.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/Cafe1938Mansion1.png', 0),
 ('/api/recommend_place/download/Jeollabukdo/Cafe1938Mansion2.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/PungnyeonBakery.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/PungnyeonBakery1.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/PungnyeonBakery2.jpg', 0),
 -- 호텔
 ('/api/recommend_place/download/Jeollabukdo/GunsanStayHotel.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/GunsanStayHotel1.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/GunsanStayHotel2.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/TheMayHotel.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/TheMayHotel1.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/TheMayHotel2.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/SonoBellByeonsan.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/SonoBellByeonsan1.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/SonoBellByeonsan2.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/SuiteHotelNamwon.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/SuiteHotelNamwon1.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/SuiteHotelNamwon2.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/ShillaStayJeonju.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/ShillaStayJeonju1.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/ShillaStayJeonju2.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/EosaHwaJeonjuHanokVillage.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/EosaHwaJeonjuHanokVillage1.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/EosaHwaJeonjuHanokVillage2.jpg', 0),
 -- 관광지
 ('/api/recommend_place/download/Jeollabukdo/GyeongamRailVillage.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/GyeongamRailVillage1.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/GyeongamRailVillage2.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/GwanghanRuPark.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/GwanghanRuPark1.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/GwanghanRuPark2.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/SanghaFarm.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/SanghaFarm1.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/SanghaFarm2.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/IksanPrisonSet.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/IksanPrisonSet1.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/IksanPrisonSet2.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/ImsilCheesePark.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/ImsilCheesePark1.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/ImsilCheesePark2.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/JeonjuHanokVillage.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/JeonjuHanokVillage1.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/JeonjuHanokVillage2.jpg', 0),
 -- 포토존
 ('/api/recommend_place/download/Jeollabukdo/GonggiVillageCypressForest.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/GonggiVillageCypressForest1.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/GonggiVillageCypressForest2.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/GuseodoStationFilmSet.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/GuseodoStationFilmSet1.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/GuseodoStationFilmSet2.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/MeoruWineCave.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/MeoruWineCave1.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/MeoruWineCave2.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/AgapeGarden.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/AgapeGarden1.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/AgapeGarden2.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/JinanFlowerGrassHill.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/JinanFlowerGrassHill1.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/JinanFlowerGrassHill2.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/ChaeseokGang.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/ChaeseokGang1.jpg', 0),
 ('/api/recommend_place/download/Jeollabukdo/ChaeseokGang2.jpg', 0),
 -- 경남 맛집
 ('/api/recommend_place/download/Gyeongsangnamdo/DongbuMeetingCenter.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/DongbuMeetingCenter1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/DongbuMeetingCenter2.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/MokminJeong.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/MokminJeong1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/MokminJeong2.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/BangkokSanJang.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/BangkokSanJang1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/BangkokSanJang2.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/SalonDeInsa.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/SalonDeInsa1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/SalonDeInsa2.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/ShabuHaeyeonChangwonSangnam.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/ShabuHaeyeonChangwonSangnam1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/ShabuHaeyeonChangwonSangnam2.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/SongGiWonJinjuNaengmyeon.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/SongGiWonJinjuNaengmyeon1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/SongGiWonJinjuNaengmyeon2.jpg', 0),
 -- 카페
 ('/api/recommend_place/download/Gyeongsangnamdo/Gosae.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/Gosae1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/Gosae2.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/Moa.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/Moa1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/Moa2.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/CityBay.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/CityBay1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/CityBay2.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/JjaekJjaekCoffeeMasan.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/JjaekJjaekCoffeeMasan1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/JjaekJjaekCoffeeMasan2.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/HaewoldangHoppo.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/HaewoldangHoppo1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/HaewoldangHoppo2.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/HongChaWangJaBonjeom.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/HongChaWangJaBonjeom1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/HongChaWangJaBonjeom2.jpg', 0),
 -- 호텔
 ('/api/recommend_place/download/Gyeongsangnamdo/LotteHotelResort.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/LotteHotelResort1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/LotteHotelResort2.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/BaramHillResort.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/BaramHillResort1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/BaramHillResort2.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/SacheonArtheResortVilla.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/SacheonArtheResortVilla1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/SacheonArtheResortVilla2.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/SonoCalmGeoje.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/SonoCalmGeoje1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/SonoCalmGeoje2.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/AnantiNamhae.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/AnantiNamhae1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/AnantiNamhae2.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/TongyeongHansanMarinaHotel.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/TongyeongHansanMarinaHotel1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/TongyeongHansanMarinaHotel2.jpg', 0),
 -- 관광지
 ('/api/recommend_place/download/Gyeongsangnamdo/NamhaeGermanVillage.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/NamhaeGermanVillage1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/NamhaeGermanVillage2.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/LotteWaterPark.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/LotteWaterPark1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/LotteWaterPark2.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/BaramHill.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/BaramHill1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/BaramHill2.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/SamsungGung.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/SamsungGung1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/SamsungGung2.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/SpaTheSpace.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/SpaTheSpace1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/SpaTheSpace2.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/HapcheonFilmThemePark.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/HapcheonFilmThemePark.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/HapcheonFilmThemePark.jpg', 0),
 -- 포토존
 ('/api/recommend_place/download/Gyeongsangnamdo/GeochangChangpoWon.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/GeochangChangpoWon1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/GeochangChangpoWon2.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/DarenVillage.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/DarenVillage1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/DarenVillage2.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/Dpira.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/Dpira1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/Dpira2.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/ForestSoundPark.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/ForestSoundPark1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/ForestSoundPark2.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/TwinTunnel.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/TwinTunnel1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/TwinTunnel2.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/HwangmaesanNationalPark.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/HwangmaesanNationalPark1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangnamdo/HwangmaesanNationalPark2.jpg', 0),
 -- 경북 맛집
 ('/api/recommend_place/download/Gyeongsangbukdo/MaradoHoesikDang.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/MaradoHoesikDang1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/MaradoHoesikDang2.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/SoOk.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/SoOk1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/SoOk2.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/YeongildaeMinamPocha.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/YeongildaeMinamPocha1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/YeongildaeMinamPocha2.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/YukYukGalbiJjim.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/YukYukGalbiJjim1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/YukYukGalbiJjim2.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/HyanghwaJeong.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/HyanghwaJeong1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/HyanghwaJeong2.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/HupoRiDaegaeAndCook.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/HupoRiDaegaeAndCook1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/HupoRiDaegaeAndCook2.jpg', 0),
 -- 카페
 ('/api/recommend_place/download/Gyeongsangbukdo/BagelBageler.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/BagelBageler1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/BagelBageler2.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/Obremen.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/Obremen1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/Obremen2.png', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/CheongSooDangGyeongju.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/CheongSooDangGyeongju1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/CheongSooDangGyeongju2.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/CafeAraeHeon.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/CafeAraeHeon1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/CafeAraeHeon2.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/HwasanJidae.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/HwasanJidae1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/HwasanJidae2.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/HwangNamBbang.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/HwangNamBbang1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/HwangNamBbang2.jpg', 0),
 -- 호텔
 ('/api/recommend_place/download/Gyeongsangbukdo/DeokguOncheonCondo.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/DeokguOncheonCondo1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/DeokguOncheonCondo2.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/LahanSelectGyeongju.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/LahanSelectGyeongju1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/LahanSelectGyeongju2.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/LahanHotelPohang.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/LahanHotelPohang1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/LahanHotelPohang2.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/YeongdeokSeaPortResort.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/YeongdeokSeaPortResort1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/YeongdeokSeaPortResort2.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/PanacOperatedBySono.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/PanacOperatedBySono1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/PanacOperatedBySono2.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/HiltonHotelGyeongju.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/HiltonHotelGyeongju1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/HiltonHotelGyeongju2.jpg', 0),
 -- 관광지
 ('/api/recommend_place/download/Gyeongsangbukdo/GyeongjuWorld.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/GyeongjuWorld1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/GyeongjuWorld2.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/DonggungWolji.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/DonggungWolji1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/DonggungWolji2.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/MungyeongEcoWorld.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/MungyeongEcoWorld1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/MungyeongEcoWorld2.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/JukbyeonSkyRail.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/JukbyeonSkyRail1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/JukbyeonSkyRail2.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/Cheomseongdae.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/Cheomseongdae1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/Cheomseongdae2.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/HomigotSunrisePlaza.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/HomigotSunrisePlaza1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/HomigotSunrisePlaza2.jpg', 0),
 -- 포토존
 ('/api/recommend_place/download/Gyeongsangbukdo/GyeongbukMillenniumForest.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/GyeongbukMillenniumForest1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/GyeongbukMillenniumForest2.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/GyeongjuDaereungwon.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/GyeongjuDaereungwon1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/GyeongjuDaereungwon2.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/Seongnyugul.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/Seongnyugul1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/Seongnyugul2.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/WineTunnel.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/WineTunnel1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/WineTunnel2.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/IgariAnchorObservatory.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/IgariAnchorObservatory1.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/IgariAnchorObservatory2.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/JungleMediaPark.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/JungleMediaPark.jpg', 0),
 ('/api/recommend_place/download/Gyeongsangbukdo/JungleMediaPark.jpg', 0),
 -- 충남 맛집
 ('/api/recommend_place/download/Chungcheongnamdo/KkongdangBoribap.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/KkongdangBoribap1.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/KkongdangBoribap2.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/WurungiDoctor.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/WurungiDoctor1.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/WurungiDoctor2.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/Uhwa.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/Uhwa1.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/Uhwa2.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/IlsongCrabSet.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/IlsongCrabSet1.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/IlsongCrabSet2.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/JjinbaeFlowerGalbiAsan.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/JjinbaeFlowerGalbiAsan1.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/JjinbaeFlowerGalbiAsan2.jpg', 0),
 -- 카페
 ('/api/recommend_place/download/Chungcheongnamdo/TtuttyuruBakery.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/TtuttyuruBakery1.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/TtuttyuruBakery2.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/MongsangGain.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/MongsangGain1.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/MongsangGain2.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/BaenamuForest.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/BaenamuForest1.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/BaenamuForest2.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/CafeStayInterviewDang.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/CafeStayInterviewDang1.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/CafeStayInterviewDang2.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/GrandmaWalnutCookies.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/GrandmaWalnutCookies1.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/GrandmaWalnutCookies2.jpg', 0),
 -- 호텔
 ('/api/recommend_place/download/Chungcheongnamdo/LotteResortBuyeo.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/LotteResortBuyeo1.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/LotteResortBuyeo2.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/SonoBelleCheonan.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/SonoBelleCheonan1.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/SonoBelleCheonan2.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/SplasResom.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/SplasResom1.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/SplasResom2.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/ShillaStay.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/ShillaStay1.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/ShillaStay2.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/SeaToIsland.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/SeaToIsland1.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/SeaToIsland2.jpg', 0),
 -- 관광지
 ('/api/recommend_place/download/Chungcheongnamdo/SapgyoLake.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/SapgyoLake1.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/SapgyoLake2.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/SeosanHaemiEupseong.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/SeosanHaemiEupseong1.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/SeosanHaemiEupseong2.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/SunshineStudio.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/SunshineStudio1.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/SunshineStudio2.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/AsanSpavis.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/AsanSpavis1.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/AsanSpavis2.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/ParadiseSpaDogo.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/ParadiseSpaDogo1.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/ParadiseSpaDogo2.jpg', 0),
 -- 포토존
 ('/api/recommend_place/download/Chungcheongnamdo/SeosanHanwooWellbeingTrail.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/SeosanHanwooWellbeingTrail1.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/SeosanHanwooWellbeingTrail2.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/CheonbukGreenBarleyField.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/CheonbukGreenBarleyField1.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/CheonbukGreenBarleyField2.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/TaeanLightFestival.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/TaeanLightFestival1.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/TaeanLightFestival2.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/FarmCamille.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/FarmCamille1.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/FarmCamille2.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/HongseongSkyTower.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/HongseongSkyTower1.jpg', 0),
 ('/api/recommend_place/download/Chungcheongnamdo/HongseongSkyTower2.jpg', 0),
 -- 충북 맛집
 ('/api/recommend_place/download/Chungcheongbukdo/GyodongMyeonok.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/GyodongMyeonok1.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/GyodongMyeonok2.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/HoneySamgyeop.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/HoneySamgyeop1.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/HoneySamgyeop2.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/BongyongBulgogi.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/BongyongBulgogi1.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/BongyongBulgogi2.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/FamousCoupleTteokbokki.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/FamousCoupleTteokbokki1.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/FamousCoupleTteokbokki2.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/JungangMemil.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/JungangMemil1.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/JungangMemil2.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/WindmillJjukkumi.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/WindmillJjukkumi1.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/WindmillJjukkumi2.jpg', 0),
 -- 카페
 ('/api/recommend_place/download/Chungcheongbukdo/Gyeomhadang.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/Gyeomhadang1.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/Gyeomhadang2.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/ThereThereBakery.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/ThereThereBakery1.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/ThereThereBakery2.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/DyureBakery.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/DyureBakery1.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/DyureBakery2.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/StayInterviewRouteur.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/StayInterviewRouteur1.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/StayInterviewRouteur2.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/Oji.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/Oji1.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/Oji2.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/ToseongVillage.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/ToseongVillage1.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/ToseongVillage2.jpg', 0),
 -- 호텔
 ('/api/recommend_place/download/Chungcheongbukdo/TheRiverSPoolvilla.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/TheRiverSPoolvilla1.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/TheRiverSPoolvilla2.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/RaonPoolvilla.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/RaonPoolvilla1.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/RaonPoolvilla2.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/SonoBelleDanyang.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/SonoBelleDanyang1.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/SonoBelleDanyang2.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/ETCastlePoolvilla.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/ETCastlePoolvilla1.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/ETCastlePoolvilla2.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/CountryVibe.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/CountryVibe1.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/CountryVibe2.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/ForestResom.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/ForestResom1.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/ForestResom2.jpg', 0),
 -- 관광지
 ('/api/recommend_place/download/Chungcheongbukdo/MancheonhaSkywalk.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/MancheonhaSkywalk1.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/MancheonhaSkywalk2.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/MunamEcoPark.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/MunamEcoPark1.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/MunamEcoPark2.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/JincheonNongdari.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/JincheonNongdari1.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/JincheonNongdari2.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/HeavenlyGardenAquaticCenter.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/HeavenlyGardenAquaticCenter1.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/HeavenlyGardenAquaticCenter2.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/Cheongnamdae.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/Cheongnamdae1.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/Cheongnamdae2.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/CheongjuLandZoo.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/CheongjuLandZoo1.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/CheongjuLandZoo2.jpg', 0),
 -- 포토존
 ('/api/recommend_place/download/Chungcheongbukdo/DanuriAquarium.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/DanuriAquarium1.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/DanuriAquarium2.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/MidongsanArboretum.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/MidongsanArboretum1.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/MidongsanArboretum2.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/SangdangSanseong.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/SangdangSanseong1.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/SangdangSanseong2.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/OchangLakePark.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/OchangLakePark1.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/OchangLakePark2.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/OkcheonGeumgangCanolaGarden.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/OkcheonGeumgangCanolaGarden1.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/OkcheonGeumgangCanolaGarden2.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/JeongbukdongEarthenFortress.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/JeongbukdongEarthenFortress1.jpg', 0),
 ('/api/recommend_place/download/Chungcheongbukdo/JeongbukdongEarthenFortress2.jpg', 0),
 -- 서울, 인천, 경기 맛집
 ('/api/recommend_place/download/Seoul/MongsangHouse.jpg', 0),
 ('/api/recommend_place/download/Seoul/MongsangHouse1.jpg', 0),
 ('/api/recommend_place/download/Seoul/MongsangHouse2.jpg', 0),
 ('/api/recommend_place/download/Seoul/Yeongyeong.jpg', 0),
 ('/api/recommend_place/download/Seoul/Yeongyeong1.jpg', 0),
 ('/api/recommend_place/download/Seoul/Yeongyeong2.jpg', 0),
 ('/api/recommend_place/download/Seoul/WaikikiMarket.jpg', 0),
 ('/api/recommend_place/download/Seoul/WaikikiMarket1.jpg', 0),
 ('/api/recommend_place/download/Seoul/WaikikiMarket2.jpg', 0),
 ('/api/recommend_place/download/Seoul/EelDream.jpg', 0),
 ('/api/recommend_place/download/Seoul/EelDream1.jpg', 0),
 ('/api/recommend_place/download/Seoul/EelDream2.jpg', 0),
 ('/api/recommend_place/download/Seoul/ChunhaChudong.jpg', 0),
 ('/api/recommend_place/download/Seoul/ChunhaChudong.jpg', 0),
 ('/api/recommend_place/download/Seoul/ChunhaChudong.jpg', 0),
 -- 카페
 ('/api/recommend_place/download/Seoul/LongBeachCoffeeAndBread.jpg', 0),
 ('/api/recommend_place/download/Seoul/LongBeachCoffeeAndBread1.jpg', 0),
 ('/api/recommend_place/download/Seoul/LongBeachCoffeeAndBread2.jpg', 0),
 ('/api/recommend_place/download/Seoul/MadeDream.jpg', 0),
 ('/api/recommend_place/download/Seoul/MadeDream1.jpg', 0),
 ('/api/recommend_place/download/Seoul/MadeDream2.jpg', 0),
 ('/api/recommend_place/download/Seoul/StandardBrandAnguk.jpg', 0),
 ('/api/recommend_place/download/Seoul/StandardBrandAnguk1.jpg', 0),
 ('/api/recommend_place/download/Seoul/StandardBrandAnguk2.jpg', 0),
 ('/api/recommend_place/download/Seoul/CafeDrawingSeokcheonLake.jpg', 0),
 ('/api/recommend_place/download/Seoul/CafeDrawingSeokcheonLake1.jpg', 0),
 ('/api/recommend_place/download/Seoul/CafeDrawingSeokcheonLake2.jpg', 0),
 ('/api/recommend_place/download/Seoul/KeepThatRoastery.jpg', 0),
 ('/api/recommend_place/download/Seoul/KeepThatRoastery1.jpg', 0),
 ('/api/recommend_place/download/Seoul/KeepThatRoastery2.jpg', 0),
 -- 호텔
 ('/api/recommend_place/download/Seoul/GrandHyattIncheonWestTower.jpg', 0),
 ('/api/recommend_place/download/Seoul/GrandHyattIncheonWestTower1.jpg', 0),
 ('/api/recommend_place/download/Seoul/GrandHyattIncheonWestTower2.jpg', 0),
 ('/api/recommend_place/download/Seoul/NestHotel.jpg', 0),
 ('/api/recommend_place/download/Seoul/NestHotel1.jpg', 0),
 ('/api/recommend_place/download/Seoul/NestHotel2.jpg', 0),
 ('/api/recommend_place/download/Seoul/DoubleTreeByHiltonSeoulPangyo.jpg', 0),
 ('/api/recommend_place/download/Seoul/DoubleTreeByHiltonSeoulPangyo1.jpg', 0),
 ('/api/recommend_place/download/Seoul/DoubleTreeByHiltonSeoulPangyo2.jpg', 0),
 ('/api/recommend_place/download/Seoul/RollingHillsHotel.jpg', 0),
 ('/api/recommend_place/download/Seoul/RollingHillsHotel1.jpg', 0),
 ('/api/recommend_place/download/Seoul/RollingHillsHotel2.jpg', 0),
 ('/api/recommend_place/download/Seoul/SeoulShillaHotel.jpg', 0),
 ('/api/recommend_place/download/Seoul/SeoulShillaHotel1.jpg', 0),
 ('/api/recommend_place/download/Seoul/SeoulShillaHotel2.jpg', 0),
 -- 관광지
 ('/api/recommend_place/download/Seoul/Gyeongbokgung.jpg', 0),
 ('/api/recommend_place/download/Seoul/Gyeongbokgung1.jpg', 0),
 ('/api/recommend_place/download/Seoul/Gyeongbokgung2.jpg', 0),
 ('/api/recommend_place/download/Seoul/NaksanPark.jpg', 0),
 ('/api/recommend_place/download/Seoul/NaksanPark1.jpg', 0),
 ('/api/recommend_place/download/Seoul/NaksanPark2.jpg', 0),
 ('/api/recommend_place/download/Seoul/Everland.jpg', 0),
 ('/api/recommend_place/download/Seoul/Everland1.jpg', 0),
 ('/api/recommend_place/download/Seoul/Everland2.jpg', 0),
 ('/api/recommend_place/download/Seoul/WolmiSeaTrain.jpg', 0),
 ('/api/recommend_place/download/Seoul/WolmiSeaTrain1.jpg', 0),
 ('/api/recommend_place/download/Seoul/WolmiSeaTrain2.jpg', 0),
 ('/api/recommend_place/download/Seoul/KoreanFolkVillage.jpg', 0),
 ('/api/recommend_place/download/Seoul/KoreanFolkVillage1.jpg', 0),
 ('/api/recommend_place/download/Seoul/KoreanFolkVillage2.jpg', 0),
 -- 포토존
 ('/api/recommend_place/download/Seoul/NSeoulTower.jpg', 0),
 ('/api/recommend_place/download/Seoul/NSeoulTower1.jpg', 0),
 ('/api/recommend_place/download/Seoul/NSeoulTower2.jpg', 0),
 ('/api/recommend_place/download/Seoul/IlwolArboretum.jpg', 0),
 ('/api/recommend_place/download/Seoul/IlwolArboretum1.jpg', 0),
 ('/api/recommend_place/download/Seoul/IlwolArboretum2.jpg', 0),
 ('/api/recommend_place/download/Seoul/FirstGarden.jpg', 0),
 ('/api/recommend_place/download/Seoul/FirstGarden1.jpg', 0),
 ('/api/recommend_place/download/Seoul/FirstGarden2.jpg', 0),
 ('/api/recommend_place/download/Seoul/PureunArboretum.jpg', 0),
 ('/api/recommend_place/download/Seoul/PureunArboretum1.jpg', 0),
 ('/api/recommend_place/download/Seoul/PureunArboretum2.jpg', 0),
 ('/api/recommend_place/download/Seoul/HerbIsland.jpg', 0),
 ('/api/recommend_place/download/Seoul/HerbIsland1.jpg', 0),
 ('/api/recommend_place/download/Seoul/HerbIsland2.jpg', 0),
 -- 강원도 맛집
 ('/api/recommend_place/download/Gangwondo/GoSunsaengHwadukGui.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/GoSunsaengHwadukGui1.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/GoSunsaengHwadukGui2.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/ManseokDakgangjeongMain.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/ManseokDakgangjeongMain1.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/ManseokDakgangjeongMain.2jpg', 0),
 ('/api/recommend_place/download/Gangwondo/BongpoMeoguriHouse.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/BongpoMeoguriHouse1.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/BongpoMeoguriHouse2.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/SugaseongSundubu.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/SugaseongSundubu1.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/SugaseongSundubu2.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/YujinCrabSteamed.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/YujinCrabSteamed1.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/YujinCrabSteamed2.jpg', 0),
 -- 카페
 ('/api/recommend_place/download/Gangwondo/CoffeeStory346.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/CoffeeStory3461.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/CoffeeStory3462.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/GamjaFarm.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/GamjaFarm1.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/GamjaFarm2.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/Grounie.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/Grounie1.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/Grounie2.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/BadaGarden.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/BadaGarden1.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/BadaGarden2.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/StayInterview.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/StayInterview1.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/StayInterview2.jpg', 0),
 -- 호텔
 ('/api/recommend_place/download/Gangwondo/LotteResortSokcho.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/LotteResortSokcho1.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/LotteResortSokcho2.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/StJohnsHotel.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/StJohnsHotel1.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/StJohnsHotel2.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/SkybayHotelGyeongpo.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/SkybayHotelGyeongpo1.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/SkybayHotelGyeongpo2.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/SolBeachSamcheok.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/SolBeachSamcheok1.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/SolBeachSamcheok2.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/CassiaSokchoHotelAndResort.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/CassiaSokchoHotelAndResort1.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/CassiaSokchoHotelAndResort2.jpg', 0),
 -- 관광지
 ('/api/recommend_place/download/Gangwondo/GyeongpoAquarium.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/GyeongpoAquarium1.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/GyeongpoAquarium2.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/MureungByeolyucheonji.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/MureungByeolyucheonji1.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/MureungByeolyucheonji2.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/YukbaekMajigi.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/YukbaekMajigi1.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/YukbaekMajigi2.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/JeongdongjinRailBike.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/JeongdongjinRailBike1.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/JeongdongjinRailBike2.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/JadenGarden.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/JadenGarden1.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/JadenGarden2.jpg', 0),
 -- 포토존
 ('/api/recommend_place/download/Gangwondo/GangneungSeongyojang.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/GangneungSeongyojang1.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/GangneungSeongyojang2.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/NamiIsland.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/NamiIsland1.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/NamiIsland2.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/DaegwallyeongSamyangRoundhill.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/DaegwallyeongSamyangRoundhill1.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/DaegwallyeongSamyangRoundhill2.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/WhisperingBirchForest.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/WhisperingBirchForest1.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/WhisperingBirchForest2.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/SokchoEyeFerrisWheel.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/SokchoEyeFerrisWheel1.jpg', 0),
 ('/api/recommend_place/download/Gangwondo/SokchoEyeFerrisWheel2.jpg', 0);
-- Step 2. 장소 등록
INSERT INTO recommend_place (id, address, city, description, detail, latitude, longitude, name) VALUES
(1, '제주특별자치도 제주시 조천읍 조함해안로 510, 2층', '제주도', ' 오션뷰를 즐기기 좋은 분위기 좋은 해산물 맛집', null ,33.5415, 126.6722, '계절식탁 함덕점'),
(2, '제주특별자치도 서귀포시 표선면 민속해안로 73, 광해수산 2층', '제주도', '바다 전망과 함께 신선한 광어회를 즐길 수 있는 맛집', NULL, 33.3250, 126.8340, '광어다 표선 본점'),
(3, '제주특별자치도 제주시 애월읍 애월해안로 867', '제주도', '애월 바다를 바라보며 갈치조림을 맛볼 수 있는 분위기 좋은 식당', NULL, 33.4622, 126.3097, '광해 애월점'),
(4, '제주특별자치도 제주시 구좌읍 김녕로21길 21', '제주도', '김녕해수욕장 근처에서 신선한 해산물을 즐길 수 있는 맛집', NULL, 33.5570, 126.7540, '김녕오라이'),
(5, '제주특별자치도 제주시 애월읍 애월해안로 872', '제주도', '애월 해안도로에서 로맨틱한 분위기와 함께 새우 요리를 즐길 수 있는 식당', NULL, 33.4625, 126.3099, '로맨틱새우애월'),
(6, '제주특별자치도 제주시 승천로 53, 1층', '제주도', '제주 흑돼지를 고급스럽게 즐길 수 있는 분위기 좋은 고깃집', NULL, 33.5008, 126.5311, '제주화도흑돼지'),
-- 카페
(7, '제주특별자치도 서귀포시 중문관광로 154-17', '제주도', '오션뷰와 함께 즐기는 감성 레스토랑', NULL, 33.2449, 126.4123, '더클리프'),
(8, '제주특별자치도 제주시 구좌읍 해맞이해안로 522', '제주도', '모던한 감성의 뷰 맛집 카페', NULL, 33.2465, 126.6730, '모노클제주'),
(9, '제주특별자치도 제주시 애월읍 애월해안로 25', '제주도', '분위기 있는 디저트 카페', NULL, 33.4550, 126.3095, '앙뚜아네트'),
(10, '제주특별자치도 제주시 한경면 판포로 56', '제주도', '조용한 해안가의 감성 식당', NULL, 33.2395, 126.5678, '오른'),
(11, '제주특별자치도 제주시 한림읍 협재리 2497-1', '제주도', '협재 바다를 바라보는 감성카페', NULL, 33.3942, 126.2367, '웨이뷰협재바다'),
(12, '제주특별자치도 제주시 구좌읍 김녕로 567', '제주도', '감각적인 인테리어의 트렌디 카페', NULL, 33.4590, 126.3090, '팀블로우'),
--숙소
(13, '제주특별자치도 제주시 애월읍 하귀1리 646', '제주도', '감각적인 네이밍이 돋보이는 감성 공간', null, 33.4910, 126.4440, '646미터퍼세크'),
(14, '제주특별자치도 제주시 애월읍 구엄길 51', '제주도', '자연 소재와 조용한 감성이 어우러진 숙소', null, 33.4875, 126.4225, '라피아하우스'),
(15, '제주특별자치도 제주시 구좌읍 해맞이해안로 2054', '제주도', '감성 가득한 포토존과 여유로운 카페 공간', null, 33.5231, 126.8503, '앳코너'),
(16, '제주특별자치도 서귀포시 표선면 가시로 101', '제주도', '제주 감성과 힐링을 담은 시간 여행형 펜션', null, 33.3082, 126.8379, '제주시간여행휴향펜션'),
(17, '제주특별자치도 제주시 한림읍 협재리 1234', '제주도', '세련된 감성 숙소 브랜드, 협재 바다 근처', null, 33.3922, 126.2405, '콘스트협재'),
(18, '제주특별자치도 제주시 애월읍 어음13길 77', '제주도', '자연 속에서 통나무 감성을 즐길 수 있는 체험 공간', null, 33.4511, 126.3666, '통나무파크'),
-- 관광지
(19, '제주특별자치도 서귀포시 안덕면 일주서로 1836', '제주도', '달빛 아래 분위기 좋은 감성 카페', null, 33.2394, 126.3103, '루나폴'),
(20, '제주특별자치도 서귀포시 안덕면 일주서로2100번길 46', '제주도', '하얀 저택을 닮은 고급스러운 정원 카페', null, 33.2504, 126.3731, '마노르블랑'),
(21, '제주특별자치도 제주시 애월읍 고성남서길 8', '제주도', '넓은 정원 속 자연을 즐길 수 있는 힐링 카페', null, 33.4497, 126.3086, '아이바가든'),
(22, '제주특별자치도 서귀포시 성산읍 섭지코지로 95', '제주도', '아이들과 함께 즐기기 좋은 대형 수족관', null, 33.4316, 126.9277, '아쿠아플라넷제주'),
(23, '제주특별자치도 서귀포시 안덕면 병악로 166', '제주도', '동백꽃으로 가득한 사계절 아름다운 정원', null, 33.2467, 126.3684, '카멜리아힐'),
(24, '제주특별자치도 서귀포시 안덕면 상창리 1839-1', '제주도', '다양한 캐릭터와 피규어 전시가 가득한 박물관', null, 33.2544, 126.3616, '피규어뮤지엄'),
-- 포토존
(25, '제주특별자치도 서귀포시 안덕면 사계리 112-1', '제주도', '드라이브 명소로 유명한 절경의 해안도로', null, 33.2198, 126.3102, '사계해안'),
(26, '제주특별자치도 서귀포시 남원읍 태위로 522', '제주도', '바위 절벽과 자연동굴이 어우러진 독특한 해안 지형', null, 33.2559, 126.6736, '산안큰엉곶'),
(27, '제주특별자치도 제주시 이호일동 1665-13', '제주도', '말등대가 있는 아름다운 해수욕장', null, 33.5145, 126.4813, '이호테우해수욕장'),
(28, '제주특별자치도 제주시 봉개동 산78-1', '제주도', '삼나무 숲 속에서 힐링할 수 있는 자연 휴양림', null, 33.4363, 126.6244, '절물자연휴양림'),
(29, '제주특별자치도 서귀포시 표선면 민속해안로 631-34', '제주도', '제주의 옛 생활문화를 체험할 수 있는 전통 테마 마을', null, 33.3254, 126.8346, '제주민속촌'),
-- 전남 맛집
(30, '전라남도 여수시 봉산2로 36', '전라남도', '간장게장을 메인으로 한 여수의 인기 맛집 거리', null, 34.7395, 127.7365, '꽃돌게장1번가'),
(31, '전라남도 순천시 연향3로 45', '전라남도', '따뜻한 정과 나눔을 실천하는 순천의 로컬 맛집', null, 34.9502, 127.5177, '나눌터'),
(32, '전라남도 여수시 수산시장2길 18', '전라남도', '이순신 장군과 해산물 삼합을 테마로 한 포차', null, 34.7390, 127.7403, '낭만포차18번이순신해산물삼합'),
(33, '전라남도 여수시 교동로 36', '전라남도', '고급스러운 감성을 더한 한식 포장마차 스타일', null, 34.7464, 127.7372, '소마담'),
(34, '전라남도 여수시 종화동 345-3', '전라남도', '푸짐한 밥상과 정겨운 분위기를 담은 식당', null, 34.7443, 127.7398, '순이네밥상'),
(35, '전라남도 여수시 여문1로 5', '전라남도', '두꺼비 캐릭터로 유명한 여수 대표 게장 전문점', null, 34.7549, 127.7156, '여수게장두꺼비게장'),
-- 카페
(36, '전라남도 여수시 학동 137-3', '전라남도', '정통 파이와 홈메이드 디저트를 즐길 수 있는 카페', null, 34.7597, 127.7431, '구디스파이'),
(37, '전라남도 여수시 돌산읍 무술목길 50', '전라남도', '바다 전망이 아름다운 대형 오션뷰 카페', null, 34.7150, 127.7364, '모이핀 오션점'),
(38, '전라남도 여수시 교동로 20', '전라남도', '수제 맥주와 분위기 있는 야외 좌석이 특징인 펍', null, 34.7458, 127.7382, '브루웍스'),
(39, '전라남도 목포시 영산로75번길 14', '전라남도', '크림치즈 바게트로 유명한 목포 로컬 베이커리', null, 34.7936, 126.3872, '씨엘비베이커리'),
(40, '전라남도 여수시 진남상가길 9-1', '전라남도', '크림 커피와 소금빵으로 인기 있는 감성 카페', null, 34.7353, 127.7421, '초가오 여수거북선점'),
(41, '전라남도 목포시 영산로75번길 7', '전라남도', '1949년부터 운영 중인 전통 제과점, 명물 팥빵 유명', null, 34.7934, 126.3870, '코롬방제과점'),
-- 호텔
(42, '전라남도 목포시 영산로75번길 7', '전라남도', '편안한 숙박과 다양한 시설을 갖춘 고급 리조트', null, 34.7934, 126.3870, '디아크리조트'),
(43, '전라남도 목포시 영산로75번길 7', '전라남도', '모던한 디자인과 편안한 서비스로 유명한 호텔', null, 34.7934, 126.3870, '전남신라스테이'),
(44, '전라남도 목포시 영산로75번길 7', '전라남도', '해변과 가까운 위치에 있어 아름다운 경치를 자랑하는 리조트', null, 34.7934, 126.3870, '쏠비치진도'),
(45, '전라남도 여수시 여문로 5', '전라남도', '여수 바다를 보며 편안한 시간을 보낼 수 있는 고급 호텔', null, 34.7549, 127.7156, '여수베네치아호텔'),
(46, '전라남도 목포시 영산로75번길 7', '전라남도', '수상한 위치와 멋진 오션뷰를 갖춘 현대적인 호텔', null, 34.7934, 126.3870, '유탑마리나호텔'),
(47, '전라남도 여수시 돌산로 10', '전라남도', '탁 트인 바다 전망과 품격 있는 서비스를 제공하는 호텔', null, 34.7353, 127.7421, '히든베이호텔'),
-- 관광지
(48, '전라남도 보성군 보성읍 녹차로 763-65', '전라남도', '보성의 대한다원에서 즐기는 청정 녹차밭 체험', null, 34.7642, 127.0736, '대한다원보성'),
(49, '전라남도 담양군 담양읍 메타세쿼이아로 12', '전라남도', '메타세쿼이아 나무가 가득한 아름다운 가로수길', null, 34.7475, 127.4991, '메타세쿼이아가로수길'),
(50, '전라남도 목포시 죽교동 465-151', '전라남도', '탁 트인 전망을 자랑하는 목포의 스카이워크', null, 34.7602, 126.3867, '목포스카이워크'),
(51, '전라남도 순천시 비례골길 24', '전라남도', '드라마의 주인공이 된 듯한 순천 드라마 촬영장', null, 34.9406, 127.4857, '순천드라마촬영장'),
(52, '전라남도 여수시 오동도로 61-11', '전라남도', '해양 생물과 만나는 아쿠아 플라넷', null, 34.4313, 126.9079, '아쿠아플라넷'),
(53, '전라남도 여수시 소라면 안심산길 155', '전라남도', '스릴 넘치는 루지 체험이 가능한 유월드 테마파크', null, 34.7280, 127.1327, '유월드루지테마파크'),
-- 포토존
(54, '전라남도 여수시 만성로 294', '전라남도', '미디어 아트와 자연이 만나는 녹색 테마파크', null, 34.9431, 127.4854, '녹테마레미디어아트'),
(55, '전라남도 담양군 담양읍 학동리 586-1', '전라남도', '지중해 풍경이 가득한 아름다운 마을, 메타프로방스', null, 34.9012, 127.4401, '메타프로방스'),
(56, '전라남도 순천시 순천만길 513-25', '전라남도', '다양한 습지 생물들이 서식하는 순천만 자연 보호 구역', null, 34.8954, 127.5286, '순천만습지'),
(57, '전라남도 신안군 임자면 대기리 산327-2', '전라남도', '신안의 아름다운 튤립 정원', null, 34.8950, 126.2101, '신안튤립공원'),
(58, '전라남도 여수시 만흥동 141-2', '전라남도', '바다를 따라 달리는 해양 레일바이크', null, 34.7468, 127.7416, '여수해양레일바이크'),
(59, '전라남도 구례군 광의면 천변길 12 KR', '전라남도', '천계의 향기로운 향나무 숲', null, 34.6614, 127.6681, '천계의향나무숲'),
 -- 전북 맛집
(60, '전라북도 전주시 완산구 봉산2로 36', '전라북도', '쭈꾸미 요리 전문의 매콤한 맛집', null, 35.8095, 127.1231, '금성쭈꾸미'),
(61, '전라북도 전주시 덕진구 기린대로 400-75', '전라북도', '전주 현지인들에게 사랑받는 메밀 소바 전문점', null, 35.8471, 127.1235, '금암소바'),
(62, '전라북도 전주시 완산구 전라감영2길 6', '전라북도', '객리단길의 인기 라멘 전문점', null, 35.8175, 127.1230, '도파멘'),
(63, '전라북도 전주시 덕진구 동가재미2길 28-20', '전라북도', '전통 가마솥 방식으로 조리한 한식집', null, 35.8412, 127.1427, '시골가마솥집'),
(64, '전라북도 전주시 완산구 풍남문4길 15-25', '전라북도', '숯불 함박스테이크로 유명한 맛집', null, 35.8140, 127.1500, '지복점'),
(65, '전라북도 전주시 완산구 한국도로 88', '전라북도', '정통 한식을 제공하는 한국식 식당', null, 35.8093, 127.1212, '한국점'),
 -- 카페
(66, '전라북도 전주시 덕진구 백제대로 649', '전라북도', '기차역 감성 인테리어의 베이커리 카페', null, 35.8502, 127.1284, '금종제과'),
(67, '전라북도 전주시 완산구 전주객사3길 46-12', '전라북도', '전통 당고와 모던 감성의 디저트 카페', null, 35.8153, 127.1498, '마래당'),
(68, '전라북도 군산시 중앙로 177', '전라북도', '1945년부터 이어온 군산의 대표 빵집', null, 35.9784, 126.7116, '이성당'),
(69, '전라북도 전주시 완산구 바우배기1길 31-13', '전라북도', '제주 감성의 대형 카페, 이국적인 분위기', null, 35.8120, 127.1145, '이소'),
(70, '전라북도 전주시 완산구 어진길 78', '전라북도', '한옥마을 인근의 앤티크 감성 디저트 카페', null, 35.8147, 127.1512, '카페1938맨션'),
(71, '전라북도 전주시 완산구 장승배기로 310-6', '전라북도', '전주 초코파이로 유명한 전통 제과점', null, 35.7998, 127.1279, '풍년제과'),
 -- 호텔
(72, '전라북도 군산시 성산면 철새로 56', '전라북도', '금강습지생태공원 인근에 위치한 감성 숙소', null, 35.9505, 126.7712, '군산스테이호텔'),
(73, '전라북도 전주시 덕진구 기린대로 800', '전라북도', '전주 최초의 컨벤션 호텔로 웅장하고 고급스러운 외관', null, 35.8571, 127.1195, '더메이호텔'),
(74, '전라북도 부안군 변산면 변산해변로 51', '전라북도', '서해의 노을과 채석강을 감상할 수 있는 해안 리조트', null, 35.6325, 126.5089, '소노벨변산'),
(75, '전라북도 남원시 주천면 원천로 217', '전라북도', '지리산 자락에 위치한 자연 친화적인 휴양 호텔', null, 35.4290, 127.4475, '스위트호텔남원'),
(76, '전라북도 전주시 완산구 현무1길 10', '전라북도', '전주 한옥마을 인근의 현대적인 비즈니스 호텔', null, 35.8145, 127.1478, '신라스테이전주'),
(77, '전라북도 전주시 완산구 한지길 106-1', '전라북도', '전통 한옥의 멋을 살린 전주 한옥마을 내 숙소', null, 35.8142, 127.1503, '어사화전주한옥마을'),
 -- 관광지
(78, '전라북도 군산시 경촌4길 14', '전라북도', '레트로 감성의 철길과 골목이 어우러진 마을', null, 35.9645, 126.7152, '경암동 철길마을'),
(79, '전라북도 남원시 요천로 1447', '전라북도', '춘향전의 배경이 된 전통 누각과 정원', null, 35.4129, 127.3905, '광한루원'),
(80, '전라북도 고창군 상하면 상하농원길 11-23', '전라북도', '자연과 교감하며 체험할 수 있는 농촌 테마파크', null, 35.4478, 126.4504, '상하농원'),
(81, '전라북도 익산시 성당면 함낭로 207', '전라북도', '영화와 드라마 촬영지로 유명한 교도소 세트장', null, 36.0697, 126.9311, '익산교도소세트장'),
(82, '전라북도 임실군 성수면 도인2길 50', '전라북도', '치즈 체험과 유럽풍 경관이 어우러진 테마파크', null, 35.6590, 127.2895, '임실치즈테마파크'),
(83, '전라북도 전주시 완산구 은행로 72-1', '전라북도', '전통 한옥이 모여 있는 역사와 문화의 거리', null, 35.8135, 127.1527, '전주한옥마을'),
 -- 포토존
(84, '전라북도 완주군 상관면 죽림리 산214-1', '전라북도', '10만 그루의 편백나무가 조성된 힐링 숲', null, 35.7325, 127.1510, '공기마을편백나무숲'),
(85, '전라북도 남원시 서도길 32', '전라북도', '드라마 촬영지로 유명한 폐역을 재현한 세트장', null, 35.4265, 127.3840, '구서도역영상촬영장'),
(86, '전라북도 무주군 적상면 산성로 359', '전라북도', '머루와인을 테마로 한 동굴형 와인 체험 공간', null, 35.9520, 127.6745, '머루와인동굴'),
(87, '전라북도 익산시 황등면 율촌길 9', '전라북도', '메타세쿼이아 숲과 사계절 정원이 아름다운 민간정원', null, 35.9925, 127.0020, '아가페정원'),
(88, '전라북도 진안군 진안읍 전진로 3071-31', '전라북도', '봄철 꽃잔디가 장관을 이루는 테마 동산', null, 35.7915, 127.4305, '진안꽃잔디동산'),
(89, '전라북도 부안군 변산면 변산리 301-1', '전라북도', '해안절벽이 아름다운 서해안의 대표 명소', null, 35.6305, 126.5090, '채석강'),
 -- 경남 맛집
(90, '경상남도 창원시 진해구 천자로 5', '경상남도', '신선한 활어회를 저렴하게 즐길 수 있는 회센터', null, 35.1463, 128.6591, '동부회센터'),
(91, '경상남도 창원시 의창구 소답로 123', '경상남도', '정갈한 한정식과 생선구이로 유명한 전통 맛집', null, 35.2345, 128.6812, '목민정'),
(92, '경상남도 창원시 진해구 경화로 100', '경상남도', '태국 분위기의 이국적인 정원과 요리를 제공하는 레스토랑', null, 35.1523, 128.6804, '방콕산장'),
(93, '경상남도 진주시 충무공동 123-4', '경상남도', '모던한 인테리어와 다양한 스테이크 요리를 제공하는 레스토랑', null, 35.1802, 128.1076, '살롱드인사'),
(94, '경상남도 창원시 성산구 중앙대로100번길 9 리베라컨벤션 3층', '경상남도', '소고기 무한리필 샤브샤브와 다양한 뷔페 메뉴를 제공하는 식당', null, 35.2245, 128.6810, '샤브해연창원상남점'),
(95, '경상남도 진주시 모덕로47번길 1-2', '경상남도', '진주냉면의 원조로 백년가게에 선정된 전통 냉면집', null, 35.1923, 128.0847, '송기원진주냉면'),
 -- 카페
(96, '경상남도 창원시 마산합포구 동서북10길 15', '경상남도', '감성적인 인테리어와 다양한 커피 메뉴를 제공하는 카페', null, 35.2067, 128.5765, '고새'),
(97, '경상남도 창원시 마산합포구 해안대로 266', '경상남도', '세련된 무채색 인테리어와 디저트가 인기인 카페', null, 35.2015, 128.5747, '모아'),
(98, '경상남도 창원시 마산회원구 무역로 111', '경상남도', '해안도로에 위치한 오션뷰 대형 베이커리 카페', null, 35.2065, 128.5764, '시티베이'),
(99, '경상남도 창원시 마산합포구 해안대로 123', '경상남도', '아늑한 분위기에서 다양한 디저트를 즐길 수 있는 카페', null, 35.2070, 128.5780, '짹짹커피마산점'),
(100, '경상남도 양산시 동면 양산대로 83', '경상남도', '다양한 베이커리와 음료를 제공하는 대형 베이커리 카페', null, 35.3350, 129.0290, '해월당호포점'),
(101, '경상남도 양산시 신명3길 144', '경상남도', '홍차와 디저트를 전문으로 하는 브런치 카페', null, 35.3500, 129.0300, '홍차왕자본점'),
 -- 호텔
(102, '부산광역시 중구 중구로 2', '경상남도', '해운대와 남포동 사이에 위치한 고급 호텔 리조트', null, 35.1008, 129.0351, '롯데호텔앤리조트'),
(103, '경상남도 거제시 남부면 갈곶리 34-3', '경상남도', '바다 전망과 언덕 위 자연 풍광이 아름다운 리조트', null, 34.6905, 128.0132, '바람의언덕리조트'),
(104, '경상남도 사천시 남일로 125', '경상남도', '프라이빗 풀빌라와 탁 트인 오션뷰를 자랑하는 리조트', null, 35.0032, 128.0846, '사천아르떼리조트풀빌라'),
(105, '경상남도 거제시 일운면 거제대로 2660', '경상남도', '소노호텔앤리조트의 프리미엄 해양 리조트', null, 34.8245, 128.6892, '소노캄거제'),
(106, '경상남도 남해군 남면 남서대로 1179-45', '경상남도', '럭셔리한 오션뷰와 자연 속 힐링 리조트', null, 34.8087, 127.8895, '아난티남해'),
(107, '경상남도 통영시 도남로 269-28', '경상남도', '요트 정박장이 있는 한산 마리나 내 해양 호텔', null, 34.8279, 128.4344, '통영한산마리나호텔'),
 -- 관광지
(108, '경상남도 남해군 삼동면 독일로 89', '경상남도', '1960년대 독일에 파견되었던 교포들이 조성한 독일풍 마을', null, 34.8009, 128.0385, '남해독일마을'),
(109, '경상남도 김해시 장유로 555', '경상남도', '국내 최대 규모의 워터파크로 다양한 실내외 시설 보유', null, 35.2056, 128.8020, '롯데워터파크'),
(110, '경상남도 거제시 남부면 갈곶리 산14-47', '경상남도', '탁 트인 바다 전망과 풍차가 어우러진 언덕', null, 34.7423, 128.6630, '바람의언덕'),
(111, '경상남도 하동군 청암면 삼성궁길 86-15', '경상남도', '고조선의 정기를 품은 전통문화 테마파크', null, 35.2245, 127.6812, '삼성궁'),
(112, '경상남도 창원시 마산합포구 구산면 유산군령로 250', '경상남도', '다양한 스파 시설과 휴식 공간을 제공하는 복합 스파 센터', null, 35.0676, 128.5629, '스파더스페이스'),
(113, '경상남도 합천군 용주면 합천호수로 757', '경상남도', '한국 근현대 영화 촬영지로 유명한 테마파크', null, 35.5661, 128.1656, '합천영상테마파크'),
 -- 포토존
(114, '경상남도 거창군 남상면 창포원길 21-1', '경상남도', '100만 송이 꽃창포가 피어나는 생태정원', null, 35.6886, 127.9273, '거창창포원'),
(115, '경상남도 남해군 남면 남면로 702', '경상남도', '계단식 논과 바다 풍경이 어우러진 전통 마을', null, 34.7281, 127.8910, '다랭이마을'),
(116, '경상남도 통영시 남망공원길 29', '경상남도', '빛과 미디어 아트가 어우러진 야간 테마파크', null, 34.8445, 128.4292, '디피랑'),
(117, '경상남도 거제시 거제면 서상리 산 13', '경상남도', '무장애 데크길이 조성된 자연 친화적 공원', null, 34.8912, 128.6225, '숲소리공원'),
(118, '경상남도 밀양시 삼랑진읍 삼랑진로 537-11', '경상남도', '빛의 터널로 유명한 이색 관광지', null, 35.5043, 128.7457, '트윈터널'),
(119, '경상남도 합천군 가회면 황매산공원길 4', '경상남도', '철쭉과 억새로 유명한 자연경관 명소', null, 35.5180, 128.0268, '황매산군립공원'),
 -- 경북 맛집
(120, '경상북도 포항시 북구 해안로 107', '경상북도', '포항 영일대해수욕장 인근의 신선한 해산물과 물회 전문점', null, 36.0658, 129.3783, '마라도회식당'),
(121, '경상북도 경주시 사정로57번길 17', '경상북도', '경주 황리단길에 위치한 전통 한옥 스타일의 한식당', null, 35.8321, 129.2105, '소옥'),
(122, '경상북도 포항시 북구 해안로 107', '경상북도', '영일대해수욕장 앞 해산물 포장마차 스타일의 포차', null, 36.0658, 129.3783, '영일대미남포차'),
(123, '경상북도 포항시 북구 학산로 58', '경상북도', '매콤하고 부드러운 갈비찜 전문 프랜차이즈 식당', null, 36.0363, 129.3698, '육육갈비찜'),
(124, '경상북도 경주시 사정로57번길 17', '경상북도', '꼬막비빔밥으로 유명한 황리단길 대표 한식당', null, 35.8321, 129.2105, '향화정'),
(125, '경상북도 울진군 후포면 후포로 178-3', '경상북도', '울진 후포항 인근의 신선한 대게 요리 전문점', null, 36.6782, 129.4603, '후포리대게앤쿡'),
 -- 카페
(126, '경상북도 포항시 남구 상공로 184', '경상북도', '직접 만든 베이글과 크림치즈로 유명한 포항 카페', null, 36.0163, 129.3682, '베이글베이글러'),
(127, '경상북도 포항시 북구 해안로 191-1', '경상북도', '영일대해수욕장 앞 대형 베이커리 카페', null, 36.0658, 129.3783, '오브레멘'),
(128, '경상북도 경주시 첨성로81번길 21-1', '경상북도', '정원과 연못이 어우러진 한옥 디저트 카페', null, 35.8313, 129.2105, '청수당경주'),
(129, '경상북도 경주시 보불로 181', '경상북도', '하동 저수지 풍경을 감상할 수 있는 한옥 카페', null, 35.7904, 129.2876, '카페아래헌'),
(130, '경상북도 영주시 영주동 148-14', '경상북도', '통유리창으로 시골풍경을 감상하는 모던한 감성 카페', null, 36.8270, 128.6236, '화산지대'),
(131, '경상북도 경주시 태종로 783', '경상북도', '1939년부터 이어온 경주 대표 전통 빵집', null, 35.8375, 129.2115, '황남빵'),
 -- 호텔
(132, '경상북도 울진군 북면 덕구온천로 924', '경상북도', '울진 산속에 위치한 자연 친화적 온천 리조트 콘도', null, 37.1167, 129.1743, '덕구온천콘도'),
(133, '경상북도 경주시 북군길 338', '경상북도', '보문단지에 위치한 감성 프리미엄 호텔', null, 35.8393, 129.2816, '라한셀렉트경주'),
(134, '경상북도 포항시 북구 삼호로265번길 1', '경상북도', '포스코 인근의 시티뷰 비즈니스 호텔', null, 36.0401, 129.3733, '라한호텔포항'),
(135, '경상북도 영덕군 강구면 삼사리 322-5', '경상북도', '동해 바다를 품은 오션뷰 휴양 리조트', null, 36.3536, 129.3854, '영덕시포트리조트'),
(136, '경상북도 포항시 남구 대잠동 1000', '경상북도', '자연과 조화를 이룬 프라이빗 휴양 콘도', null, 36.0192, 129.3575, '파나크오퍼레이티드바이소노'),
(137, '경상북도 경주시 북군동 484-7', '경상북도', '보문호수 옆 글로벌 체인 호텔', null, 35.8437, 129.2844, '힐튼호텔경주'),
 -- 관광지
(138, '경상북도 경주시 보문로 544', '경상북도', '다양한 놀이기구와 테마파크를 즐길 수 있는 종합 레저시설', null, 35.8493, 129.2840, '경주월드'),
(139, '경상북도 경주시 원화로 102', '경상북도', '신라 왕궁 터의 아름다운 연못과 정원을 복원한 유적지', null, 35.8341, 129.2327, '동궁과월지'),
(140, '경상북도 문경시 문경읍 온천2길 2', '경상북도', '자연 체험과 레포츠를 즐길 수 있는 문경의 생태 테마파크', null, 36.5838, 128.1932, '문경에코월드'),
(141, '경상북도 울진군 죽변면 해안로 357', '경상북도', '동해 바다를 따라 운행하는 해안 스카이 레일 체험', null, 37.0636, 129.4006, '죽변해안스카이레일'),
(142, '경상북도 경주시 인왕동 839-1', '경상북도', '신라 시대 천문 관측소로 알려진 대표 유적지', null, 35.8318, 129.2199, '첨성대'),
(143, '경상북도 포항시 남구 호미곶면 대보리 85', '경상북도', '새해 해돋이 명소로 유명한 광장', null, 36.0677, 129.5682, '호미곶해맞이광장'),
 -- 포토존
(144, '경상북도 울진군 북면 나곡리 산1-1', '경상북도', '천년 숲의 웅장함을 느낄 수 있는 힐링 산책 코스', null, 37.1220, 129.1448, '경북천년숲정원'),
(145, '경상북도 경주시 황남동 31-1', '경상북도', '신라시대 왕족들의 고분이 모여 있는 역사 유적지', null, 35.8353, 129.2101, '경주대릉원'),
(146, '경상북도 문경시 가은읍 왕능길 138', '경상북도', '아름다운 석회암 동굴로 알려진 천연 기념물', null, 36.6237, 128.0797, '성류굴'),
(147, '경상북도 문경시 문경읍 새재로 914', '경상북도', '옛 철도 터널을 활용한 독특한 와인 저장고', null, 36.6941, 128.1065, '와인터널'),
(148, '경상북도 포항시 북구 청하면 이가리 산91-13', '경상북도', '닻 모양 조형물이 인상적인 바다 전망대', null, 36.2075, 129.3652, '이가리닻전망대'),
(149, '경상북도 포항시 남구 대송면 봉좌로 409', '경상북도', '미디어 아트와 체험형 콘텐츠가 결합된 복합 문화공간', null, 35.9847, 129.3894, '정글미디어파크'),
-- 충남맛집
(150, '충청남도 아산시 음봉면 음봉로926번길 21', '충청남도', '전통 보리밥 정식을 맛볼 수 있는 한식당', null, 36.8218, 127.0412, '꽁당보리밥'),
(151, '충청남도 아산시 인주면 아산만로 1124', '충청남도', '우렁이 요리 전문 건강 식당', null, 36.8786, 126.9568, '우렁이박사'),
(152, '충청남도 아산시 탕정면 탕정면로 151', '충청남도', '모던한 분위기의 파인다이닝 한식 레스토랑', null, 36.8032, 127.0954, '우화'),
(153, '충청남도 아산시 염치읍 은행나무길 130-1', '충청남도', '꽃게장 백반 정식이 인기인 한정식집', null, 36.8391, 127.0105, '일송꽃게장백반'),
(154, '충청남도 아산시 탕정면 탕정면로 90', '충청남도', '숯불에 구운 꽃갈비로 유명한 고깃집', null, 36.8075, 127.0932, '찐배네꽃갈비아산본점'),
-- 카페
(155, '충청남도 아산시 배방읍 공수리 1070-6', '충청남도', '아기자기한 디저트와 케이크로 유명한 빵집', null, 36.7771, 127.0647, '뚜쮸루과자점'),
(156, '충청남도 아산시 음봉면 신수리 47-1', '충청남도', '한적한 자연 속에서 즐기는 디저트 카페', null, 36.8296, 127.0431, '몽상가인'),
(157, '충청남도 아산시 음봉면 산동리 265', '충청남도', '나무 숲길과 함께하는 힐링 카페', null, 36.8277, 127.0415, '배나무숲'),
(158, '충청남도 아산시 음봉면 신수리 102-1', '충청남도', '스테이형 감성 카페, 인터뷰의 당', null, 36.8285, 127.0462, '카페스테이인터뷰의당'),
(159, '충청남도 아산시 신창면 순천향로 22', '충청남도', '따뜻한 할머니 손맛의 호두과자 전문점', null, 36.7695, 127.0022, '할머니호도과자'),
-- 호텔
(160, '충청남도 부여군 규암면 백제문로 400', '충청남도', '부여 백제문화단지 인근 종합 리조트', null, 36.2796, 126.9117, '롯데리조트부여'),
(161, '충청남도 천안시 동남구 성남면 종합휴양지로 200', '충청남도', '자연 친화적 콘도형 리조트', null, 36.7323, 127.2412, '소노벨천안'),
(162, '충청남도 예산군 덕산면 온천단지2로 45', '충청남도', '온천과 워터파크를 즐길 수 있는 테마 리조트', null, 36.6770, 126.5991, '스플라스리솜'),
(163, '충청남도 천안시 서북구 불당동 1549', '충청남도', '비즈니스와 관광 모두를 만족시키는 호텔', null, 36.8173, 127.1036, '신라스테이'),
(164, '충청남도 태안군 안면읍 승언리 1201-21', '충청남도', '해양 체험과 휴양을 함께 즐길 수 있는 섬', null, 36.4691, 126.3628, '씨투아일랜드'),
-- 관광지
(165, '충청남도 당진시 신평면 삽교천3로 79', '충청남도', '서해안의 대표적인 인공호수', null, 36.8912, 126.7482, '삽교호'),
(166, '충청남도 서산시 해미면 남문2로 143', '충청남도', '조선시대 읍성의 모습을 간직한 문화재', null, 36.6352, 126.5838, '서산해미읍성'),
(167, '충청남도 논산시 연산면 선샤인로 22', '충청남도', '드라마 세트장으로 꾸며진 복합 문화 공간', null, 36.2234, 127.0812, '선샤인스튜디오'),
(168, '충청남도 아산시 음봉면 음봉로 816', '충청남도', '워터파크와 온천을 함께 즐길 수 있는 스파', null, 36.8261, 127.0425, '아산스파비스'),
(169, '충청남도 아산시 도고면 도고온천로 176', '충청남도', '온천과 스파 테마파크를 갖춘 리조트', null, 36.7779, 127.0083, '파라다이스스파도고'),
-- 포토존
(170, '충청남도 서산시 운산면 원평리 산11', '충청남도', '한우 방목지와 건강 산책로가 있는 목장', null, 36.6827, 126.6155, '서산한우목장웰빙산책로'),
(171, '충청남도 보령시 천북면 장은리 57', '충청남도', '푸른 청보리가 물결치는 광활한 들판', null, 36.3236, 126.5045, '천북청보리밭'),
(172, '충청남도 태안군 남면 마검포길 200', '충청남도', '밤을 환하게 밝히는 대규모 빛 축제', null, 36.6633, 126.2784, '태안빛축제'),
(173, '충청남도 태안군 남면 신온리 168-5', '충청남도', '허브와 꽃으로 꾸며진 아름다운 농원', null, 36.6691, 126.2772, '팜카밀레'),
(174, '충청남도 홍성군 홍북읍 청사로 160', '충청남도', '홍성 시내를 한눈에 조망할 수 있는 전망대', null, 36.6014, 126.6671, '홍성스카이타워'),
-- 충북 맛집
(175, '충청북도 청주시 상당구 교동로 18', '충청북도', '전통 평양냉면으로 유명한 노포 맛집', null, 36.6373, 127.4915, '교동면옥'),
(176, '충청북도 청주시 서원구 구룡산로 376', '충청북도', '꿀처럼 달콤한 삼겹살 맛집', null, 36.6115, 127.4692, '꿀삼겹'),
(177, '충청북도 청주시 청원구 오창읍 중심상업로 7', '충청북도', '정성 가득한 불고기 정식 전문점', null, 36.7132, 127.4259, '봉용불고기'),
(178, '충청북도 청주시 상당구 북문로1가 123', '충청북도', '오랜 세월 사랑받은 부부 떡볶이 맛집', null, 36.6377, 127.4898, '소문난부부떡볶이'),
(179, '충청북도 청주시 상당구 남사로 118', '충청북도', '진한 메밀 향이 살아있는 전통 메밀국수집', null, 36.6382, 127.4930, '중앙모밀'),
(180, '충청북도 청주시 서원구 사직대로 126', '충청북도', '매콤달콤한 쭈꾸미 요리 전문', null, 36.6359, 127.4787, '풍차쭈꾸미'),
-- 카페
(181, '충청북도 청주시 상당구 북문로2가 58-1', '충청북도', '한옥 감성의 전통 찻집', null, 36.6374, 127.4925, '겸하당'),
(182, '충청북도 청주시 흥덕구 사운로 410', '충청북도', '아늑한 감성 디저트 베이커리 카페', null, 36.6484, 127.4298, '데어데어베이커리'),
(183, '충청북도 청주시 흥덕구 복대동 2890', '충청북도', '정성 가득 수제빵으로 유명한 베이커리', null, 36.6370, 127.4327, '듀레베이커리'),
(184, '충청북도 청주시 흥덕구 송절로 28', '충청북도', '감성 스테이형 디저트 카페', null, 36.6489, 127.4497, '스테이인터뷰르투어'),
(185, '충청북도 청주시 청원구 오창읍 중심상업로 45', '충청북도', '숨은 로컬 감성 카페', null, 36.7140, 127.4276, '오지'),
(186, '충청북도 단양군 대강면 용부원리 841', '충청북도', '전원 풍경이 아름다운 전통 마을', null, 36.9721, 128.3277, '토성마을'),
-- 호텔
(187, '충청북도 단양군 가곡면 두산리 396-8', '충청북도', '계곡 옆 프라이빗한 수영장이 있는 풀빌라', null, 36.9615, 128.3964, '더리버에스풀빌라'),
(188, '충청북도 단양군 가곡면 두산리 459', '충청북도', '자연 속 독채 풀빌라 숙소', null, 36.9655, 128.3926, '라온풀빌라'),
(189, '충청북도 단양군 단양읍 삼봉로 187', '충청북도', '단양 대표 가족형 리조트', null, 36.9851, 128.3697, '소노벨단양'),
(190, '충청북도 단양군 가곡면 사평리 148-2', '충청북도', '성을 테마로 한 독특한 풀빌라', null, 36.9812, 128.3878, '이티성풀빌라'),
(191, '충청북도 단양군 대강면 장림리 304-1', '충청북도', '자연 감성 가득한 컨트리 감성 숙소', null, 36.9314, 128.3190, '컨추리바이브'),
(192, '충청북도 제천시 백운면 금봉로 365', '충청북도', '산림욕과 온천을 함께 즐길 수 있는 휴양 리조트', null, 37.1467, 128.1817, '포레스트리솜'),
-- 관광지
(193, '충청북도 단양군 적성면 애곡리 94-4', '충청북도', '남한강을 내려다보는 짜릿한 하늘 산책로', null, 36.9766, 128.3628, '만천하스카이워크'),
(194, '충청북도 제천시 신백동 122', '충청북도', '자연 생태를 체험할 수 있는 대규모 생태공원', null, 37.1338, 128.2097, '문암생태공원'),
(195, '충청북도 진천군 초평면 용정리 260', '충청북도', '1000년 된 돌다리, 진천 명물', null, 36.9925, 127.4281, '진천농다리'),
(196, '충청북도 충주시 살미면 월악로 681', '충청북도', '물과 식물의 조화를 이룬 천상의 정원', null, 36.8965, 127.9456, '천상의정원수생물학습원'),
(197, '충청북도 청주시 상당구 문의면 남계리 산2-1', '충청북도', '대통령 별장으로 유명한 청남대', null, 36.5158, 127.4517, '청남대'),
(198, '충청북도 청주시 상당구 명암로 216', '충청북도', '어린이들을 위한 동물원과 놀이터', null, 36.6541, 127.5083, '청주랜드동물원'),
-- 포토존
(199, '충청북도 단양군 단양읍 별곡리 94-4', '충청북도', '남한강 물속 생태계를 만날 수 있는 아쿠아리움', null, 36.9914, 128.3698, '다누리아쿠아리움'),
(200, '충청북도 청주시 상당구 미원면 미원초교길 50', '충청북도', '희귀 식물과 나무를 만나는 자연 학습원', null, 36.6527, 127.7267, '미동산수목원'),
(201, '충청북도 청주시 상당구 산성로 960', '충청북도', '조선시대 산성과 청주 명소', null, 36.6589, 127.5301, '상당산성'),
(202, '충청북도 청주시 청원구 오창읍 양청길 30', '충청북도', '도심 속 자연과 휴식을 즐길 수 있는 호수공원', null, 36.7147, 127.4315, '오창호수공원'),
(203, '충청북도 옥천군 옥천읍 금구리 1-1', '충청북도', '금강변 유채꽃과 자연을 즐기는 정원', null, 36.3096, 127.5713, '옥천금강유채꽃정원'),
(204, '충청북도 청주시 상당구 정북동 191', '충청북도', '삼국시대 토성 유적지', null, 36.6709, 127.5167, '정북동토성'),
-- 서울, 인천, 경기 맛집
(205, '서울특별시 종로구 북촌로5길 24', '인천,서울,경기', '북촌한옥마을 인근 감성 숙소', null, 37.5805, 126.9845, '몽상가옥'),
(206, '서울특별시 강남구 도산대로 318', '인천,서울,경기', '세련된 한식 다이닝 레스토랑', null, 37.5192, 127.0233, '연경'),
(207, '서울특별시 송파구 석촌호수로12길 38', '인천,서울,경기', '하와이 감성 라이프스타일 카페', null, 37.5060, 127.0948, '와이키키마켓'),
(208, '경기도 하남시 서하남로 460', '인천,서울,경기', '신선한 장어구이 전문점', null, 37.5379, 127.2085, '장어의꿈'),
(209, '경기도 성남시 분당구 정자일로 95', '인천,서울,경기', '사계절 냉면과 전통 한식 맛집', null, 37.3662, 127.1089, '춘하추동'),
-- 카페
(210, '경기도 용인시 기흥구 언남로79번길 6', '인천,서울,경기', '넓은 잔디밭과 함께 즐기는 브런치 카페', null, 37.2811, 127.1258, '롱비치커피앤브래드'),
(211, '경기도 성남시 수정구 산성대로 90', '인천,서울,경기', '휴식과 디저트를 함께 즐길 수 있는 감성 카페', null, 37.4557, 127.1493, '메이드림'),
(212, '서울특별시 종로구 윤보선길 19', '인천,서울,경기', '북촌 감성의 편집숍 카페', null, 37.5750, 126.9848, '스탠다드브랜드안국'),
(213, '서울특별시 송파구 석촌호수로 176', '인천,서울,경기', '호수 뷰 감성 드로잉 카페', null, 37.5076, 127.0944, '카페드로잉석천호수정'),
(214, '경기도 광주시 곤지암읍 광여로 374', '인천,서울,경기', '커피 로스팅 전문 감성 카페', null, 37.3543, 127.3446, '킵댓로스터리'),
-- 호텔
(215, '인천광역시 중구 영종해안남로321번길 208', '인천,서울,경기', '럭셔리 호텔, 인천공항 인근 웨스트 타워', null, 37.4470, 126.4522, '그랜드하얏트인천웨스트타워'),
(216, '인천광역시 중구 영종해안남로 19-5', '인천,서울,경기', '자연 속 힐링을 느낄 수 있는 라이프스타일 호텔', null, 37.4772, 126.4870, '네스트호텔'),
(217, '경기도 성남시 분당구 백현로 230', '인천,서울,경기', '힐튼 계열의 고급 비즈니스 호텔', null, 37.3922, 127.1114, '더블트리바이힐튼서울판교'),
(218, '경기도 화성시 동탄면 동탄기흥로 660', '인천,서울,경기', '가족과 함께 즐기기 좋은 풀빌라형 호텔', null, 37.2021, 127.0924, '롤링힐스호텔'),
(219, '서울특별시 중구 동호로 249', '인천,서울,경기', '서울 중심에 위치한 5성급 럭셔리 호텔', null, 37.5605, 127.0064, '서울신라호텔'),
-- 관광지
(220, '서울특별시 종로구 사직로 161', '인천,서울,경기', '조선시대 궁궐 중 가장 큰 규모를 자랑하는 고궁', null, 37.5796, 126.9770, '경복궁'),
(221, '서울특별시 종로구 동숭길 41', '인천,서울,경기', '서울 도심 속 자연과 어우러진 언덕 공원', null, 37.5827, 127.0075, '낙산공원'),
(222, '경기도 용인시 처인구 포곡읍 에버랜드로 199', '인천,서울,경기', '한국을 대표하는 대형 테마파크', null, 37.2933, 127.2021, '에버랜드'),
(223, '인천광역시 중구 북성동1가 월미문화로 85', '인천,서울,경기', '바다를 따라 운행하는 인천 명물 열차', null, 37.4760, 126.6044, '월미바다열차'),
(224, '경기도 용인시 기흥구 민속촌로 90', '인천,서울,경기', '한국 전통문화를 체험할 수 있는 민속촌', null, 37.2605, 127.1522, '한국민속촌'),
-- 포토존
(225, '서울특별시 용산구 남산공원길 105', '인천,서울,경기', '서울의 중심 남산에 위치한 랜드마크 타워', null, 37.5512, 126.9882, 'N서울타워'),
(226, '경기도 시흥시 능곡동 산3-3', '인천,서울,경기', '자연과 함께하는 도시 속 수목원', null, 37.3885, 126.8021, '일월수목원'),
(227, '경기도 파주시 탄현면 새오리로 217', '인천,서울,경기', '유럽풍 정원이 펼쳐진 테마파크', null, 37.7746, 126.6852, '퍼스트가든'),
(228, '서울특별시 구로구 연동로 240', '인천,서울,경기', '도심 속 푸르름을 간직한 수목원', null, 37.4877, 126.8247, '푸른수목원'),
(229, '경기도 포천시 신북면 청신로947번길 35', '인천,서울,경기', '향기로운 허브와 자연을 테마로 한 테마파크', null, 37.9160, 127.2262, '허브아일랜드'),
-- 강원도 맛집
(230, '강원특별자치도 강릉시 성산면 보광리 349-5', '강원도', '화덕에서 구운 고기 전문 맛집', null, 37.6735, 128.8541, '고선생화덕구이'),
(231, '강원특별자치도 속초시 중앙로 147번길 16', '강원도', '속초 명물, 달콤짭짤한 닭강정 본점', null, 38.1976, 128.5912, '만석닭강정본점'),
(232, '강원특별자치도 속초시 영랑해안길 223', '강원도', '싱싱한 해산물을 맛볼 수 있는 바다 앞 식당', null, 38.2103, 128.5916, '봉포머구리집'),
(233, '강원특별자치도 강릉시 주문진읍 신리천로 117', '강원도', '전통 순두부 요리가 인기인 식당', null, 37.8912, 128.8278, '수가성순두부'),
(234, '강원특별자치도 속초시 중앙로 117', '강원도', '살이 꽉 찬 대게찜 전문점', null, 38.2045, 128.5911, '유진게찜'),
-- 카페
(235, '강원특별자치도 강릉시 경강로 3466', '강원도', '작은 로스터리 감성 카페', null, 37.7525, 128.8953, '346커피스토리'),
(236, '강원특별자치도 평창군 봉평면 기풍로 116', '강원도', '감자 테마 체험형 카페', null, 37.6475, 128.3775, '감자밭'),
(237, '강원특별자치도 강릉시 난설헌로 131', '강원도', '넓은 잔디 정원과 카페', null, 37.7528, 128.9169, '그라우니'),
(238, '강원특별자치도 고성군 토성면 청간정리 334', '강원도', '푸른 바다를 조망할 수 있는 레스토랑', null, 38.2852, 128.5501, '바다정원'),
(239, '강원특별자치도 속초시 설악산로 461-5', '강원도', '소소한 인터뷰 감성 카페', null, 38.1886, 128.5319, '스테이인터뷰'),
-- 호텔
(240, '강원특별자치도 속초시 대포항길 186', '강원도', '바다와 산을 품은 고급 리조트', null, 38.1844, 128.6053, '롯데리조트속초'),
(241, '강원특별자치도 강릉시 창해로 307', '강원도', '경포해변 앞 대형 리조트 호텔', null, 37.7976, 128.9178, '세인트존스호텔'),
(242, '강원특별자치도 강릉시 해안로 476', '강원도', '오션뷰와 야경이 매력적인 리조트', null, 37.7972, 128.9274, '스카이베이호텔경포'),
(243, '강원특별자치도 삼척시 수로부인길 453', '강원도', '해변과 맞닿은 프리미엄 리조트', null, 37.4456, 129.1658, '쏠비치삼척'),
(244, '강원특별자치도 속초시 청초호반로 191', '강원도', '신축 오션뷰 호텔 & 리조트', null, 38.1945, 128.5889, '카시아속초호텔앤리조트'),
-- 관광지
(245, '강원특별자치도 강릉시 강문동 1', '강원도', '경포대 앞 대형 아쿠아리움', null, 37.7979, 128.9015, '경포아쿠아리움'),
(246, '강원특별자치도 동해시 삼화동 산1-1', '강원도', '계곡과 폭포가 어우러진 동해 대표 자연 관광지', null, 37.5041, 129.1046, '무릉별유천지'),
(247, '강원특별자치도 평창군 대관령면 횡계리 산60', '강원도', '끝없이 펼쳐진 고원 목장과 풍경', null, 37.6937, 128.7088, '육백마지기'),
(248, '강원특별자치도 강릉시 강동면 정동진리 50-2', '강원도', '정동진을 달리는 바닷가 레일바이크', null, 37.6902, 129.0386, '정동진레일바이크'),
(249, '강원특별자치도 춘천시 남산면 광판리 730-10', '강원도', '대형 온실과 정원이 아름다운 수목원', null, 37.7638, 127.6323, '제이든가든'),
-- 포토존
(250, '강원특별자치도 강릉시 운정동 459번지', '강원도', '전통 고택과 정원이 어우러진 문화재', null, 37.7687, 128.8972, '강릉선교장'),
(251, '강원특별자치도 춘천시 남산면 남이섬길 1', '강원도', '사계절이 아름다운 인기 섬 관광지', null, 37.7903, 127.5259, '남이섬'),
(252, '강원특별자치도 평창군 대관령면 횡계리 산1-15', '강원도', '삼양목장의 드넓은 고원 목장', null, 37.6809, 128.7027, '대관령삼양라운드힐'),
(253, '강원특별자치도 인제군 인제읍 원대리 산29-1', '강원도', '하얀 자작나무가 펼쳐진 아름다운 숲길', null, 38.0735, 128.2133, '속삭이는자작나무숲'),
(254, '강원특별자치도 속초시 엑스포로 85', '강원도', '청초호 앞에 위치한 대형 관람차', null, 38.1918, 128.5906, '속초아이대관람차');

-- Step 3. 장소 <-> 미디어 연결
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (50, 1),(51, 1),(52, 1),(53, 1);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (54, 2),(55, 2), (56, 2);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (57, 3),(58, 3),(59, 3),(60, 3);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (61, 4),(62, 4),(63, 4);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (64, 5),(65, 5),(66, 5);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (67, 6),(68, 6),(69, 6);
-- 카페
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (70, 7),(71, 7),(72, 7);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (73, 8),(74, 8),(75, 8);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (76, 9),(77, 9),(78, 9);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (79, 10),(80, 10),(81, 10);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (82, 11),(83, 11),(84, 11);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (85, 12),(86, 12),(87, 12);
-- 숙소
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (88, 13),(89, 13),(90, 13);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (91, 14),(92, 14),(93, 14);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (94, 15),(95, 15),(96, 15);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (97, 16),(98, 16),(99, 16);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (100, 17),(101, 17),(102, 17),(103, 17);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (104, 18),(105, 18),(106, 18);
-- 관광지
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (107, 19),(108, 19),(109, 19);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (110, 20),(111, 20),(112, 20);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (113, 21),(114, 21),(115, 21);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (116, 22),(117, 22),(118, 22);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (119, 23),(120, 23),(121, 23);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (122, 24),(123, 24),(124, 24);
-- 포토존
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (125, 25),(126, 25),(127, 25);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (128, 26),(129, 26),(130, 26);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (131, 27),(132, 27),(133, 27);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (134, 28),(135, 28),(136, 28);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (137, 29),(138, 29),(139, 29);
-- 전남 맛집
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (140, 30),(141, 30),(142, 30);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (143, 31),(144, 31),(145, 31);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (146, 32),(147, 32),(148, 32);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (149, 33),(150, 33),(151, 33);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (152, 34),(153, 34),(154, 34);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (155, 35),(156, 35),(157, 35);
-- 카페
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (158, 36),(159, 36),(160, 36);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (161, 37),(162, 37),(163, 37);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (164, 38),(165, 38),(166, 38);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (167, 39),(168, 39),(169, 39);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (170, 40),(171, 40),(172, 40);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (173, 41),(174, 41),(175, 41);
-- 호텔
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (176, 42),(177, 42),(178, 42);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (179, 43),(180, 43),(181, 43);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (182, 44),(183, 44),(184, 44);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (185, 45),(186, 45),(187, 45);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (188, 46),(189, 46),(190, 46);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (191, 47),(192, 47),(193, 47);
-- 관광지
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (194, 48),(195, 48),(196, 48);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (197, 49),(198, 49),(199, 49);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (200, 50),(201, 50),(202, 50);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (203, 51),(204, 51),(205, 51);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (206, 52),(207, 52),(208, 52);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (209, 53),(210, 53),(211, 53);
-- 포토존
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (212, 54),(213, 54),(214, 54);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (215, 55),(216, 55),(217, 55);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (218, 56),(219, 56),(220, 56);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (221, 57),(222, 57),(223, 57);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (224, 58),(225, 58),(226, 58);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (227, 59),(228, 59),(229, 59);
 -- 전북 맛집
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (230, 60),(231, 60),(232, 60);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (233, 61),(234, 61),(235, 61);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (236, 62),(237, 62),(238, 62);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (239, 63),(240, 63),(241, 63);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (242, 64),(243, 64),(244, 64);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (245, 65),(246, 65),(247, 65);
 -- 카페
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (248, 66),(249, 66),(250, 66);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (251, 67),(252, 67),(253, 67);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (254, 68),(255, 68),(256, 68);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (257, 69),(258, 69),(259, 69);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (260, 70),(261, 70),(262, 70);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (263, 71),(264, 71),(265, 71);
 -- 호텔
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (266, 72),(267, 72),(268, 72);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (269, 73),(270, 73),(271, 73);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (272, 74),(273, 74),(274, 74);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (275, 75),(276, 75),(277, 75);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (278, 76),(279, 76),(280, 76);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (281, 77),(282, 77),(283, 77);
 -- 관광지
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (284, 78),(285, 78),(286, 78);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (287, 79),(288, 79),(289, 79);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (290, 80),(291, 80),(292, 80);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (293, 81),(294, 81),(295, 81);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (296, 82),(297, 82),(298, 82);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (299, 83),(300, 83),(301, 83);
 -- 포토존
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (302, 84),(303, 84),(304, 84);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (305, 85),(306, 85),(307, 85);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (308, 86),(309, 86),(310, 86);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (311, 87),(312, 87),(313, 87);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (314, 88),(315, 88),(316, 88);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (317, 89),(318, 89),(319, 89);
 -- 경남 맛집
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (320, 90),(321, 90),(322, 90);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (323, 91),(324, 91),(325, 91);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (326, 92),(327, 92),(328, 92);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (329, 93),(330, 93),(331, 93);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (332, 94),(333, 94),(334, 94);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (335, 95),(336, 95),(337, 95);
 -- 카페
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (338, 96),(339, 96),(340, 96);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (341, 97),(342, 97),(343, 97);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (344, 98),(345, 98),(346, 98);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (347, 99),(348, 99),(349, 99);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (350, 100),(351, 100),(352, 100);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (353, 101),(354, 101),(355, 101);
 -- 호텔
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (356, 102),(357, 102),(358, 102);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (359, 103),(360, 103),(361, 103);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (362, 104),(363, 104),(364, 104);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (365, 105),(366, 105),(367, 105);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (368, 106),(369, 106),(370, 106);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (371, 107),(372, 107),(373, 107);
 -- 관광지
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (374, 108),(375, 108),(376, 108);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (377, 109),(378, 109),(379, 109);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (380, 110),(381, 110),(382, 110);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (383, 111),(384, 111),(385, 111);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (386, 112),(387, 112),(388, 112);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (389, 113),(390, 113),(391, 113);
 -- 포토존
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (392, 114),(393, 114),(394, 114);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (395, 115),(396, 115),(397, 115);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (398, 116),(399, 116),(400, 116);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (401, 117),(402, 117),(403, 117);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (404, 118),(405, 118),(406, 118);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (407, 119),(408, 119),(409, 119);
 -- 경북 맛집
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (410, 120),(411, 120),(412, 120);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (413, 121),(414, 121),(415, 121);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (416, 122),(417, 122),(418, 122);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (419, 123),(420, 123),(421, 123);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (422, 124),(423, 124),(424, 124);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (425, 125),(426, 125),(427, 125);
 -- 카페
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (428, 126),(429, 126),(430, 126);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (431, 127),(432, 127),(433, 127);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (434, 128),(435, 128),(436, 128);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (437, 129),(438,129 ),(439, 129);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (440, 130),(441, 130),(442, 130);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (443, 131),(444, 131),(445, 131);
 -- 호텔
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (446, 132),(447, 132),(448, 132);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (449, 133),(450, 133),(451, 133);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (452, 134),(453, 134),(454, 134);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (455, 135),(456, 135),(457, 135);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (458, 136),(459, 136),(460, 136);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (461, 137),(462, 137),(463, 137);
 -- 관광지
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (464, 138),(465, 138),(466, 138);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (467, 139),(468, 139),(469, 139);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (470, 140),(471, 140),(472, 140);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (473, 141),(474, 141),(475, 141);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (476, 142),(477, 142),(478, 142);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (479, 143),(480, 143),(481, 143);
 -- 포토존
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (482, 144),(483, 144),(484, 144);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (485, 145),(486, 145),(487, 145);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (488, 146),(489, 146),(490, 146);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (491, 147),(492, 147),(493, 147);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (494, 148),(495, 148),(496, 148);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (497, 149),(498, 149),(499, 149);
-- 충남 맛집
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (500, 150),(501, 150),(502, 150);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (503, 151),(504, 151),(505, 151);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (506, 152),(507, 152),(508, 152);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (509, 153),(510, 153),(511, 153);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (512, 154),(513, 154),(514, 154);
-- 카페
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (515, 155),(516, 155),(517, 155);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (518, 156),(519, 156),(520, 156);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (521, 157),(522, 157),(523, 157);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (524, 158),(525, 158),(526, 158);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (527, 159),(528, 159),(529, 159);
-- 호텔
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (530, 160),(531, 160),(532, 160);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (533, 161),(534, 161),(535, 161);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (536, 162),(537, 162),(538, 162);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (539, 163),(540, 163),(541, 163);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (542, 164),(543, 164),(544, 164);
-- 관광지
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (545, 165),(546, 165),(547, 165);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (548, 166),(549, 166),(550, 166);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (551, 167),(552, 167),(553, 167);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (554, 168),(555, 168),(556, 168);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (557, 169),(558, 169),(559, 169);
-- 포토존
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (560, 170),(561, 170),(562, 170);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (563, 171),(564, 171),(565, 171);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (566, 172),(567, 172),(568, 172);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (569, 173),(570, 173),(571, 173);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (572, 174),(573, 174),(574, 174);
-- 충북 맛집
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (575, 175),(576, 175),(577, 175);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (578, 176),(579, 176),(580, 176);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (581, 177),(582, 177),(583, 177);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (584, 178),(585, 178),(586, 178);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (587, 179),(588, 179),(589, 179);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (590, 180),(591, 180),(592, 180);
-- 카페
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (593, 181),(594, 181),(595, 181);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (596, 182),(597, 182),(598, 182);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (599, 183),(600, 183),(601, 183);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (602, 184),(603, 184),(604, 184);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (605, 185),(606, 185),(607, 185);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (608, 186),(609, 186),(610, 186);
-- 호텔
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (611, 187),(612, 187),(613, 187);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (614, 188),(615, 188),(616, 188);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (617, 189),(618, 189),(619, 189);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (620, 190),(621, 190),(622, 190);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (623, 191),(624, 191),(625, 191);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (626, 192),(627, 192),(628, 192);
-- 관광지
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (629, 193),(630, 193),(631, 193);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (632, 194),(633, 194),(634, 194);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (635, 195),(636, 195),(637, 195);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (638, 196),(639, 196),(640, 196);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (641, 197),(642, 197),(643, 197);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (644, 198),(645, 198),(646, 198);
-- 포토존
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (647, 199),(648, 199),(649, 199);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (650, 200),(651, 200),(652, 200);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (653, 201),(654, 201),(655, 201);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (656, 202),(657, 202),(658, 202);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (659, 203),(660, 203),(661, 203);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (662, 204),(663, 204),(664, 204);
-- 서울, 인천, 경기 맛집
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (665, 205),(666, 205),(667, 205);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (668, 206),(669, 206),(670, 206);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (671, 207),(672, 207),(673, 207);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (674, 208),(675, 208),(676, 208);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (677, 209),(678, 209),(679, 209);
-- 카페
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (680, 210),(681, 210),(682, 210);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (683, 211),(684, 211),(685, 211);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (686, 212),(687, 212),(688, 212);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (689, 213),(690, 213),(691, 213);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (692, 214),(693, 214),(694, 214);
-- 호텔
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (695, 215),(696, 215),(697, 215);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (698, 216),(699, 216),(700, 216);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (701, 217),(702, 217),(703, 217);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (704, 218),(705, 218),(706, 218);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (707, 219),(708, 219),(709, 219);
-- 관광지
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (710, 220),(711, 220),(712, 220);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (713, 221),(714, 221),(715, 221);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (716, 222),(717, 222),(718, 222);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (719, 223),(720, 223),(721, 223);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (722, 224),(723, 224),(724, 224);
-- 포토존
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (725, 225),(726, 225),(727, 225);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (728, 226),(729, 226),(730, 226);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (731, 227),(732, 227),(733, 227);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (734, 228),(735, 228),(736, 228);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (737, 229),(738, 229),(739, 229);
-- 강원도 맛집
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (740, 230),(741, 230),(742, 230);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (743, 231),(744, 231),(745, 231);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (746, 232),(747, 232),(748, 232);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (749, 233),(750, 233),(751, 233);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (752, 234),(753, 234),(754, 234);
-- 카페
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (755, 235),(756, 235),(757, 235);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (758, 236),(759, 236),(760, 236);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (761, 237),(762, 237),(763, 237);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (764, 238),(765, 238),(766, 238);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (767, 239),(768, 239),(769, 239);
-- 호텔
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (770, 240),(771, 240),(772, 240);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (773, 241),(774, 241),(775, 241);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (776, 242),(777, 242),(778, 242);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (779, 243),(780, 243),(781, 243);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (782, 244),(783, 244),(784, 244);
-- 관광지
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (785, 245),(786, 245),(787, 245);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (788, 246),(789, 246),(790, 246);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (791, 247),(792, 247),(793, 247);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (794, 248),(795, 248),(796, 248);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (797, 249),(798, 249),(799, 249);
-- 포토존
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (800, 250),(801, 250),(802, 250);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (803, 251),(804, 251),(805, 251);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (806, 252),(807, 252),(808, 252);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (809, 253),(810, 253),(811, 253);
INSERT INTO recommend_place_media (media_id, recommend_place_id) VALUES (812, 254),(813, 254),(814, 254);

-- Step 4. 장소 <-> 카테고리 연결
INSERT INTO category_place (id, category_name, recommend_place_id)VALUES
-- 제주
(50, '맛집', 1),
(51, '맛집', 2),
(52, '맛집', 3),
(53, '맛집', 4),
(54, '맛집', 5),
(55, '맛집', 6),
(56, '카페', 7),
(57, '카페', 8),
(58, '카페', 9),
(59, '카페', 10),
(60, '카페', 11),
(61, '카페', 12),
(62, '호텔', 13),
(63, '호텔', 14),
(64, '호텔', 15),
(65, '호텔', 16),
(66, '호텔', 17),
(67, '호텔', 18),
(68, '관광지', 19),
(69, '관광지', 20),
(70, '관광지', 21),
(71, '관광지', 22),
(72, '관광지', 23),
(73, '관광지', 24),
(74, '포토존', 25),
(75, '포토존', 26),
(76, '포토존', 27),
(77, '포토존', 28),
(78, '포토존', 29),
-- 전남
(79, '맛집', 30),
(80, '맛집', 31),
(81, '맛집', 32),
(82, '맛집', 33),
(83, '맛집', 34),
(84, '맛집', 35),
(85, '카페', 36),
(86, '카페', 37),
(87, '카페', 38),
(88, '카페', 39),
(89, '카페', 40),
(90, '카페', 41),
(91, '호텔', 42),
(92, '호텔', 43),
(93, '호텔', 44),
(94, '호텔', 45),
(95, '호텔', 46),
(96, '호텔', 47),
(97, '관광지', 48),
(98, '관광지', 49),
(99, '관광지', 50),
(100, '관광지', 51),
(101, '관광지', 52),
(102, '관광지', 53),
(103, '포토존', 54),
(104, '포토존', 55),
(105, '포토존', 56),
(106, '포토존', 57),
(107, '포토존', 58),
(108, '포토존', 59),
-- 전북
(109, '맛집', 60),
(110, '맛집', 61),
(111, '맛집', 62),
(112, '맛집', 63),
(113, '맛집', 64),
(114, '맛집', 65),
(115, '카페', 66),
(116, '카페', 67),
(117, '카페', 68),
(118, '카페', 69),
(119, '카페', 70),
(120, '카페', 71),
(121, '호텔', 72),
(122, '호텔', 73),
(123, '호텔', 74),
(124, '호텔', 75),
(125, '호텔', 76),
(126, '호텔', 77),
(127, '관광지', 78),
(128, '관광지', 79),
(129, '관광지', 80),
(130, '관광지', 81),
(131, '관광지', 82),
(132, '관광지', 83),
(133, '포토존', 84),
(134, '포토존', 85),
(135, '포토존', 86),
(136, '포토존', 87),
(137, '포토존', 88),
(138, '포토존', 89),
-- 경남
(139, '맛집', 90),
(140, '맛집', 91),
(141, '맛집', 92),
(142, '맛집', 93),
(143, '맛집', 94),
(144, '맛집', 95),
(145, '카페', 96),
(146, '카페', 97),
(147, '카페', 98),
(148, '카페', 99),
(149, '카페', 100),
(150, '카페', 101),
(151, '호텔', 102),
(152, '호텔', 103),
(153, '호텔', 104),
(154, '호텔', 105),
(155, '호텔', 106),
(156, '호텔', 107),
(157, '관광지', 108),
(158, '관광지', 109),
(159, '관광지', 110),
(160, '관광지', 111),
(161, '관광지', 112),
(162, '관광지', 113),
(163, '포토존', 114),
(164, '포토존', 115),
(165, '포토존', 116),
(166, '포토존', 117),
(167, '포토존', 118),
(168, '포토존', 119),
-- 경북
(169, '맛집', 120),
(170, '맛집', 121),
(171, '맛집', 122),
(172, '맛집', 123),
(173, '맛집', 124),
(174, '맛집', 125),
(175, '카페', 126),
(176, '카페', 127),
(177, '카페', 128),
(178, '카페', 129),
(179, '카페', 130),
(180, '카페', 131),
(181, '호텔', 132),
(182, '호텔', 133),
(183, '호텔', 134),
(184, '호텔', 135),
(185, '호텔', 136),
(186, '호텔', 137),
(187, '관광지', 138),
(188, '관광지', 139),
(189, '관광지', 140),
(190, '관광지', 141),
(191, '관광지', 142),
(192, '관광지', 143),
(193, '포토존', 144),
(194, '포토존', 145),
(195, '포토존', 146),
(196, '포토존', 147),
(197, '포토존', 148),
(198, '포토존', 149),
-- 충남
(199, '맛집', 150),
(200, '맛집', 151),
(201, '맛집', 152),
(202, '맛집', 153),
(203, '맛집', 154),
(204, '카페', 155),
(205, '카페', 156),
(206, '카페', 157),
(207, '카페', 158),
(208, '카페', 159),
(209, '호텔', 160),
(210, '호텔', 161),
(211, '호텔', 162),
(212, '호텔', 163),
(213, '호텔', 164),
(214, '관광지', 165),
(215, '관광지', 166),
(216, '관광지', 167),
(217, '관광지', 168),
(218, '관광지', 169),
(219, '포토존', 170),
(220, '포토존', 171),
(221, '포토존', 172),
(222, '포토존', 173),
(223, '포토존', 174),
-- 충북
(224, '맛집', 175),
(225, '맛집', 176),
(226, '맛집', 177),
(227, '맛집', 178),
(228, '맛집', 179),
(229, '맛집', 180),
(230, '카페', 181),
(231, '카페', 182),
(232, '카페', 183),
(233, '카페', 184),
(234, '카페', 185),
(235, '카페', 186),
(236, '호텔', 187),
(237, '호텔', 188),
(238, '호텔', 189),
(239, '호텔', 190),
(240, '호텔', 191),
(241, '호텔', 192),
(242, '관광지', 193),
(243, '관광지', 194),
(244, '관광지', 195),
(245, '관광지', 196),
(246, '관광지', 197),
(247, '관광지', 198),
(248, '포토존', 199),
(249, '포토존', 200),
(250, '포토존', 201),
(251, '포토존', 202),
(252, '포토존', 203),
(253, '포토존', 204),
-- 서울, 인천, 경기
(254, '맛집', 205),
(255, '맛집', 206),
(256, '맛집', 207),
(257, '맛집', 208),
(258, '맛집', 209),
(259, '카페', 210),
(260, '카페', 211),
(261, '카페', 212),
(262, '카페', 213),
(263, '카페', 214),
(264, '호텔', 215),
(265, '호텔', 216),
(266, '호텔', 217),
(267, '호텔', 218),
(268, '호텔', 219),
(269, '관광지', 220),
(270, '관광지', 221),
(271, '관광지', 222),
(272, '관광지', 223),
(273, '관광지', 224),
(274, '포토존', 225),
(275, '포토존', 226),
(276, '포토존', 227),
(277, '포토존', 228),
(278, '포토존', 229),
-- 강원도
(279, '맛집', 230),
(280, '맛집', 231),
(281, '맛집', 232),
(282, '맛집', 233),
(283, '맛집', 234),
(284, '카페', 235),
(285, '카페', 236),
(286, '카페', 237),
(287, '카페', 238),
(288, '카페', 239),
(289, '호텔', 240),
(290, '호텔', 241),
(291, '호텔', 242),
(292, '호텔', 243),
(293, '호텔', 244),
(294, '관광지', 245),
(295, '관광지', 246),
(296, '관광지', 247),
(297, '관광지', 248),
(298, '관광지', 249),
(299, '포토존', 250),
(300, '포토존', 251),
(301, '포토존', 252),
(302, '포토존', 253),
(303, '포토존', 254);