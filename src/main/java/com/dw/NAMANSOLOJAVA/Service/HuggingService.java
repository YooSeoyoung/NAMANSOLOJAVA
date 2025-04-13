package com.dw.NAMANSOLOJAVA.Service;

import com.dw.NAMANSOLOJAVA.Config.SecurityConfig;
import com.dw.NAMANSOLOJAVA.Repository.AlbumRepository;
import com.dw.NAMANSOLOJAVA.model.Album;
import com.dw.NAMANSOLOJAVA.model.User;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class HuggingService {

    // Hugging Face API Key 가져오기
    private final String apiKey = SecurityConfig.dotenv.get("HUGGINGFACE_API_KEY");

    // application.properties 또는 yml에서 설정
    @Value("${huggingface.model}")
    private String modelId;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private UserService userService;



    public String getPersonalizedRecommendation(int count) {
        User user = userService.getCurrentUser();
        List<Album> albums = albumRepository.findByUser_Username(user.getUsername());

        List<String> locations = albums.stream()
                .map(Album::getLocation)
                .distinct()
                .toList();

        if (locations.isEmpty()) {
            return "추천을 생성하려면 먼저 몇 개의 장소를 기록해야 해요!";
        }

        String prompt = buildPrompt(locations, count);
        String aiResponse = askHuggingFace(prompt);
        return extractRecommendedSpot(aiResponse);
    }

    private String buildPrompt(List<String> locations, int count) {
        String lastLocation = locations.get(locations.size() - 1);
        String locationList = String.join("\n- ", locations);

        return String.format("""
            다음은 커플이 다녀온 데이트 장소 목록입니다:

            - %s

            이 커플이 좋아할 만한 %s 데이트 장소를 하나 추천해 주세요.
            장소명과 이유를 포함해 주세요.
            장소가 음식점이라면 메뉴도 포함하면 좋습니다.
            """, locationList, count == 0 ? "분위기 비슷한" : "새로운 느낌의");
    }

    private String askHuggingFace(String prompt) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> body = Map.of("inputs", prompt);
        HttpEntity<?> request = new HttpEntity<>(body, headers);

        String url = "https://api-inference.huggingface.co/models/" + modelId;
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(response.getBody());
            return root.get(0).get("generated_text").asText();
        } catch (Exception e) {
            return "AI 응답 파싱 중 오류 발생: " + e.getMessage();
        }
    }

    public String extractRecommendedSpot(String generatedText) {
        if (generatedText == null || generatedText.isBlank()) {
            return "AI 응답이 비어있습니다.";
        }

        return "다음 데이트 추천 장소는 👇\n\n" + generatedText.trim();
    }
}