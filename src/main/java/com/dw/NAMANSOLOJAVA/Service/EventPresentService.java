package com.dw.NAMANSOLOJAVA.Service;

import com.dw.NAMANSOLOJAVA.Config.SecurityConfig;
import com.dw.NAMANSOLOJAVA.DTO.EventPresentDTO;
import com.dw.NAMANSOLOJAVA.Exception.ResourceNotFoundException;
import com.dw.NAMANSOLOJAVA.Repository.EventPresentRepository;
import com.dw.NAMANSOLOJAVA.model.EventPresent;
import com.dw.NAMANSOLOJAVA.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class EventPresentService {
    String clientId = SecurityConfig.dotenv.get("NAVER_CLIENT_ID");

    private String cliId = clientId;

    String clientSecret = SecurityConfig.dotenv.get("NAVER_CLIENT_SECRET");

    private String apSec = clientSecret;

    @Autowired
    EventPresentRepository eventPlaceRepository;

    @Autowired
    UserService userService;

    private static final String NAVER_API_URL = "https://openapi.naver.com/v1/search/shop.json";

    public List<EventPresentDTO> getAllEventPresent() {
        User user = userService.getCurrentUser();
        LocalDate birthF = user.getBirthF();
        LocalDate birthM = user.getBirthM();
        int ageM = calculateExactAge(birthM);
        int ageF = calculateExactAge(birthF);
//        System.out.println("🎂 나이 계산 결과 (남자): " + ageM);
//        System.out.println("🎂 나이 계산 결과 (여자): " + ageF);
        List<EventPresentDTO> male = searchAndConvert(ageM + "대 남자 선물");
        List<EventPresentDTO> female = searchAndConvert(ageF + "대 여자 선물");

        List<EventPresentDTO> all = new ArrayList<>();
        all.addAll(male);
        all.addAll(female);
        return all;
    }

    public List<EventPresentDTO> getFemaleEventPresent() {
        User user = userService.getCurrentUser();
        LocalDate birthF = user.getBirthF();
        int ageF = calculateExactAge(birthF);
        System.out.println("남자 생일: " + user.getBirthM());
        System.out.println("여자 생일: " + user.getBirthF());
        return searchAndConvert(ageF+"대 여자 선물");
    }

    public List<EventPresentDTO> getMaleEventPresent() {
        User user = userService.getCurrentUser();
        LocalDate birthM = user.getBirthM();
        int ageM = calculateExactAge(birthM);
        System.out.println("🎂 나이 계산 결과 (남자): " + ageM);
        return searchAndConvert(ageM+"대 남자 선물");
    }

    private List<EventPresentDTO> searchAndConvert(String keyword) {
        try {
            String encodedQuery = URLEncoder.encode(keyword, StandardCharsets.UTF_8);
            String apiUrl = NAVER_API_URL + "?query=" + encodedQuery + "&display=10";

            URL url = new URL(apiUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            con.setRequestProperty("X-Naver-Client-Id", cliId);
            con.setRequestProperty("X-Naver-Client-Secret", apSec);

            int responseCode = con.getResponseCode();
            BufferedReader br = responseCode == 200 ?
                    new BufferedReader(new InputStreamReader(con.getInputStream())) :
                    new BufferedReader(new InputStreamReader(con.getErrorStream()));

            StringBuilder sb = new StringBuilder();
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }
            br.close();
            System.out.println("🧾 네이버 응답 JSON: " + sb.toString()); // 여기 출력해봐

            Map<String, Object> jsonMap = new ObjectMapper().readValue(sb.toString(), Map.class);
            List<Map<String, Object>> items = (List<Map<String, Object>>) jsonMap.get("items");

            System.out.println("아이디: "+clientId);
            System.out.println("비번: "+clientSecret);

            if (items == null) return Collections.emptyList();

            return items.stream().map(this::mapToDTO).toList();

        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException("네이버 검색 API 호출 실패: " + e.getMessage());
        }
    }

    private String stripHtml(String html) {
        return html.replaceAll("<[^>]*>", ""); // 모든 HTML 태그 제거
    }

    private EventPresentDTO mapToDTO(Map<String, Object> item) {
        return new EventPresentDTO(
                stripHtml ((String) item.getOrDefault("title", "")),
                stripHtml ((String) item.getOrDefault("description", "")),
                (String) item.getOrDefault("image", ""),
                (String) item.getOrDefault("link", ""),
                (String) item.getOrDefault("lprice", "")
        );
    }

    private int calculateExactAge(LocalDate birthDate) {
        LocalDate today = LocalDate.now();
        Period period = Period.between(birthDate, today);
        return (period.getYears() / 10) * 10;
    }
}
