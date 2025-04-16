package com.dw.NAMANSOLOJAVA.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class WeatherService {

    @Value("${openweather.api.key}")
    private String apiKey;

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

                return String.format("%s의 날씨: %s, %.1f도", city, description, temp);
            } else {
                return "날씨 정보를 가져올 수 없습니다 (API 응답 오류)";
            }
        } catch (Exception e) {
            return "날씨 API 호출 실패: " + e.getMessage();
        }
    }
}
