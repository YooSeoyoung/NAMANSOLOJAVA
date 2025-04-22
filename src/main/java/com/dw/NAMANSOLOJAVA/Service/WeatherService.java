package com.dw.NAMANSOLOJAVA.Service;

import com.dw.NAMANSOLOJAVA.Config.SecurityConfig;
import com.dw.NAMANSOLOJAVA.DTO.WeatherDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WeatherService {
    @Value("${openweather.api.key}")
    private String apiKey;
    private static final Map<String, String> cityNameMap = Map.ofEntries(
            Map.entry("Seoul", "서울"),
            Map.entry("Busan", "부산"),
            Map.entry("Daegu", "대구"),
            Map.entry("Incheon", "인천"),
            Map.entry("Gwangju", "광주"),
            Map.entry("Daejeon", "대전"),
            Map.entry("Ulsan", "울산"),
            Map.entry("Sejong", "세종"),
            Map.entry("Suwon", "수원시"),
            Map.entry("Yongin", "용인시"),
            Map.entry("Seongnam", "성남시"),
            Map.entry("Goyang", "고양시"),
            Map.entry("Bucheon", "부천시"),
            Map.entry("Ansan", "안산시"),
            Map.entry("Anyang", "안양시"),
            Map.entry("Namyangju", "남양주시"),
            Map.entry("Hwaseong", "화성시"),
            Map.entry("Pyeongtaek", "평택시"),
            Map.entry("Uijeongbu", "의정부시"),
            Map.entry("Siheung", "시흥시"),
            Map.entry("Gwangmyeong", "광명시"),
            Map.entry("Gwangju-si", "광주시"),
            Map.entry("Hanam", "하남시"),
            Map.entry("Gunpo", "군포시"),
            Map.entry("Icheon", "이천시"),
            Map.entry("Anseong", "안성시"),
            Map.entry("Osan", "오산시"),
            Map.entry("Yangju", "양주시"),
            Map.entry("Paju", "파주시"),
            Map.entry("Dongducheon", "동두천시"),
            Map.entry("Guri", "구리시"),
            Map.entry("Gapyeong", "가평군"),
            Map.entry("Yangpyeong", "양평군"),
            Map.entry("Yeoncheon", "연천군"),
            Map.entry("Pocheon", "포천시"),
            Map.entry("Chuncheon", "춘천시"),
            Map.entry("Wonju", "원주시"),
            Map.entry("Gangneung", "강릉시"),
            Map.entry("Donghae", "동해시"),
            Map.entry("Samcheok", "삼척시"),
            Map.entry("Taebaek", "태백시"),
            Map.entry("Sokcho", "속초시"),
            Map.entry("Hongcheon", "홍천군"),
            Map.entry("Hoengseong", "횡성군"),
            Map.entry("Pyeongchang", "평창군"),
            Map.entry("Jeongseon", "정선군"),
            Map.entry("Cheorwon", "철원군"),
            Map.entry("Hwacheon", "화천군"),
            Map.entry("Yanggu", "양구군"),
            Map.entry("Inje", "인제군"),
            Map.entry("Goseong_Gangwon", "고성군"),
            Map.entry("Yangyang", "양양"),
            Map.entry("Cheongju", "청주시"),
            Map.entry("Chungju", "충주시"),
            Map.entry("Jecheon", "제천시"),
            Map.entry("Boeun", "보은군"),
            Map.entry("Okcheon", "옥천군"),
            Map.entry("Yeongdong", "영동군"),
            Map.entry("Jincheon", "진천군"),
            Map.entry("Goesan", "괴산군"),
            Map.entry("Eumseong", "음성군"),
            Map.entry("Danyang", "단양군"),
            Map.entry("Jeungpyeong", "증평군"),
            Map.entry("Cheonan", "천안시"),
            Map.entry("Asan", "아산시"),
            Map.entry("Gongju", "공주시"),
            Map.entry("Boryeong", "보령시"),
            Map.entry("Seosan", "서산시"),
            Map.entry("Nonsan", "논산시"),
            Map.entry("Gyeryong", "계룡시"),
            Map.entry("Dangjin", "당진시"),
            Map.entry("Geumsan", "금산군"),
            Map.entry("Buyeo", "부여군"),
            Map.entry("Seocheon", "서천군"),
            Map.entry("Cheongyang", "청양군"),
            Map.entry("Hongseong", "홍성군"),
            Map.entry("Yesan", "예산군"),
            Map.entry("Taean", "태안군"),
            Map.entry("Jeonju", "전주시"),
            Map.entry("Gunsan", "군산시"),
            Map.entry("Iksan", "익산시"),
            Map.entry("Namwon", "남원시"),
            Map.entry("Gimje", "김제시"),
            Map.entry("Jeongeup", "정읍시"),
            Map.entry("Wanju", "완주군"),
            Map.entry("Jinan", "진안군"),
            Map.entry("Muju", "무주군"),
            Map.entry("Jangsu", "장수군"),
            Map.entry("Imsil", "임실군"),
            Map.entry("Sunchang", "순창군"),
            Map.entry("Gochang", "고창군"),
            Map.entry("Buan", "부안군"),
            Map.entry("Mokpo", "목포시"),
            Map.entry("Suncheon", "순천시"),
            Map.entry("Yeosu", "여수시"),
            Map.entry("Naju", "나주시"),
            Map.entry("Gwangyang", "광양시"),
            Map.entry("Damyang", "담양군"),
            Map.entry("Gokseong", "곡성군"),
            Map.entry("Gurye", "구례군"),
            Map.entry("Goheung", "고흥군"),
            Map.entry("Boseong", "보성군"),
            Map.entry("Hwasun", "화순군"),
            Map.entry("Jangheung", "장흥군"),
            Map.entry("Gangjin", "강진군"),
            Map.entry("Haenam", "해남군"),
            Map.entry("Yeongam", "영암군"),
            Map.entry("Muan", "무안군"),
            Map.entry("Hampyeong", "함평군"),
            Map.entry("Yeonggwang", "영광군"),
            Map.entry("Jangseong", "장성군"),
            Map.entry("Wando", "완도군"),
            Map.entry("Jindo", "진도군"),
            Map.entry("Sinan", "신안군"),
            Map.entry("Pohang", "포항시"),
            Map.entry("Gyeongju", "경주시"),
            Map.entry("Gimcheon", "김천시"),
            Map.entry("Andong", "안동시"),
            Map.entry("Gumi", "구미시"),
            Map.entry("Yeongju", "영주시"),
            Map.entry("Yeongcheon", "영천시"),
            Map.entry("Sangju", "상주시"),
            Map.entry("Mungyeong", "문경시"),
            Map.entry("Gyeongsan", "경산시"),
            Map.entry("Uljin", "울진군"),
            Map.entry("Uiseong", "의성군"),
            Map.entry("Yeongyang", "영양군"),
            Map.entry("Yeongdeok", "영덕군"),
            Map.entry("Bonghwa", "봉화군"),
            Map.entry("Cheongsong", "청송군"),
            Map.entry("Seongju", "성주군"),
            Map.entry("Chilgok", "칠곡군"),
            Map.entry("Gunwi", "군위군"),
            Map.entry("Cheongdo", "청도군"),
            Map.entry("Changwon", "창원시"),
            Map.entry("Jinju", "진주시"),
            Map.entry("Tongyeong", "통영시"),
            Map.entry("Sacheon", "사천시"),
            Map.entry("Gimhae", "김해시"),
            Map.entry("Miryang", "밀양시"),
            Map.entry("Geoje", "거제시"),
            Map.entry("Yangsan", "양산시"),
            Map.entry("Uiryeong", "의령군"),
            Map.entry("Haman", "함안군"),
            Map.entry("Changnyeong", "창녕군"),
            Map.entry("Namhae", "남해군"),
            Map.entry("Hadong", "하동군"),
            Map.entry("Sancheong", "산청군"),
            Map.entry("Hamyang", "함양군"),
            Map.entry("Geochang", "거창군"),
            Map.entry("Hapcheon", "합천군"),
            Map.entry("Jeju", "제주시"),
            Map.entry("Seogwipo", "서귀포시")
    );

    public String getCurrentWeather(String city) {
        try {
            String url = String.format(
                    "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric&lang=kr",
                    URLEncoder.encode(city, StandardCharsets.UTF_8), apiKey
            );

            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() == 200) {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode json = mapper.readTree(connection.getInputStream());

                String description = json.get("weather").get(0).get("description").asText();
                double temp = json.get("main").get("temp").asDouble();

                String koreanCity = cityNameMap.getOrDefault(city, city);
                return String.format("%s의 날씨: %s, %.1f도", koreanCity, description, temp);
            } else {
                return "날씨 정보를 가져올 수 없습니다 (API 응답 오류)";
            }
        } catch (Exception e) {
            return "날씨 API 호출 실패: " + e.getMessage();
        }
    }
    public WeatherDTO getWeatherDetail(String city) {
        try {
            String url = String.format(
                    "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric&lang=kr",
                    URLEncoder.encode(city, StandardCharsets.UTF_8), apiKey
            );

            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");


            if (connection.getResponseCode() == 200) {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode json = mapper.readTree(connection.getInputStream());

                String description = json.get("weather").get(0).get("description").asText();
                double temp = json.get("main").get("temp").asDouble();
                String icon = json.get("weather").get(0).get("icon").asText();

                return new WeatherDTO(description, temp, icon);
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public WeatherDTO getWeatherForecast(String city, int dayOffset) {
        try {
            String normalizedCity = normalizeCityName(city);
            System.out.println("🧭 도시명 정제 결과: " + city + " → " + normalizedCity);

            String forecastUrl = String.format(
                    "https://api.openweathermap.org/data/2.5/forecast?q=%s&units=metric&lang=kr&appid=%s",
                    URLEncoder.encode(normalizedCity, StandardCharsets.UTF_8), apiKey
            );
            System.out.println("📡 Forecast URL: " + forecastUrl);

            HttpURLConnection conn = (HttpURLConnection) new URL(forecastUrl).openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            System.out.println("📡 Forecast 응답 코드: " + responseCode);
            if (responseCode != 200) return null;

            String body = new String(conn.getInputStream().readAllBytes());
            System.out.println("📡 Forecast 응답 본문:\n" + body);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(body);
            JsonNode list = root.get("list");

            if (list == null || list.isEmpty()) return null;

            // 3시간 간격, 하루는 8개 → dayOffset * 8
            int targetIndex = Math.min(dayOffset * 8, list.size() - 1);
            if (list.size() <= targetIndex) return null;

            JsonNode target = list.get(targetIndex);
            String description = target.get("weather").get(0).get("description").asText();
            double temp = target.get("main").get("temp").asDouble();
            String icon = target.get("weather").get(0).get("icon").asText();

            return new WeatherDTO(description, temp, icon);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static final Map<String, String> korToEngMap = cityNameMap.entrySet().stream()
            .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));

    private static String normalizeCityName(String rawCity) {
        if (rawCity == null || rawCity.isBlank()) return "Seoul";

        String cleaned = rawCity.replace("시", "")
                .replace("군", "")
                .replace("구", "")
                .trim();

        // 1️⃣ 한글로 들어오는 경우 직접 변환
        switch (cleaned) {
            case "서울": return "Seoul";
            case "부산": return "Busan";
            case "대구": return "Daegu";
            case "인천": return "Incheon";
            case "광주": return "Gwangju";
            case "대전": return "Daejeon";
            case "울산": return "Ulsan";
            case "세종": return "Sejong";
            case "수원": return "Suwon";
            case "청주": return "Cheongju";
            case "전주": return "Jeonju";
            case "제주": return "Jeju";
            case "창원": return "Changwon";
            // 🧩 필요하면 더 추가
            default: break;
        }

        // 2️⃣ 영어로 저장된 경우 (cityNameMap에 있는 경우)
        for (Map.Entry<String, String> entry : cityNameMap.entrySet()) {
            String mappedKor = entry.getValue().replace("시", "").replace("군", "").replace("구", "").trim();
            if (mappedKor.equals(cleaned)) {
                return entry.getKey();
            }
        }

        return "Seoul"; // fallback
    }

    public WeatherDTO getWeatherForecast(String city, LocalDate targetDate) {
        LocalDate today = LocalDate.now();
        int offset = (int) ChronoUnit.DAYS.between(today, targetDate);

        if (offset < 0 || offset > 4) { // openweathermap forecast는 5일까지
            System.out.println("5일 초과 forecast는 제공되지 않음: offset=" + offset);
            return null;
        }

        return getWeatherForecast(city, offset);
    }

}
