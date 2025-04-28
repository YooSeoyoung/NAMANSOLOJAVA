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


    // 사용자가에게 추천해주는 함수
    public String getPersonalizedRecommendation(int count) {
        User user = userService.getCurrentUser();  // 현재 로그인한 사용자를 가져옴
        List<Album> albums = albumRepository.findByUser_Username(user.getUsername()); // 그 사용자의 앨범 추출

        // 그 앨범에서 다녀온 LOCATION 추출
        List<String> locations = albums.stream()
                .map(Album::getLocation)
                .distinct()
                .toList();

        // LOCATION이 하나도 없으면 등록 후 이용이 가능하다고 안내하기
        if (locations.isEmpty()) {
            return "추천을 생성하려면 먼저 몇 개의 장소를 기록해야 해요!";
        }


        // 프롬프트로 만들어서 HUGGING FACE AI에게 요청을 보내고 추천 장소 받기
        String prompt = buildPrompt(locations, count);
        System.out.println("Generated Prompt: " + prompt);
        String aiResponse = askHuggingFace(prompt);
        System.out.println("AI Response: " + aiResponse);
        return extractRecommendedSpot(aiResponse);
    }
        // COUNT : 0이면 비슷한 분위기 1이며8ㄴ 새로운 느낌의 장소 추천
    private String buildPrompt(List<String> locations, int count) {
        String locationList = String.join("\n- ", locations);

        return String.format("""
                 커플이 과거에 방문한 데이트 장소는 다음과 같습니다:
                 %s.
                
                 위 장소들과 %s 데이트 장소를 1곳 추천해 주세요.
                 다음 형식으로 답변해 주세요:
                
                 장소명: [추천 장소명]
                 추천 사유: [한 줄 설명]
                 추천 음식: [대표 메뉴] (없으면 작성하지 마세요)
            """, locationList, count == 0 ? "비슷한 분위기의 " : "새로운 분위기의");
    }
    // 실제 HUGGING FACE에게 요청하는 것( 프롬프트를 매개변수로 받아서)
    private String askHuggingFace(String prompt) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 바디의 형태
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

        String place = "알 수 없는 장소";
        String reason = "추천 이유를 가져오지 못했습니다.";
        String food = null;

        try {
            String[] lines = generatedText.trim().split("\n");

            for (String line : lines) {
                line = line.trim();  // 각 줄의 앞뒤 공백 제거

                if (line.startsWith("장소명:")) {
                    place = line.replace("장소명:", "").trim();
                } else if (line.startsWith("추천 사유:")) {
                    reason = line.replace("추천 사유:", "").trim();
                } else if (line.startsWith("추천 음식:")) {
                    food = line.replace("추천 음식:", "").trim();
                }
            }
        } catch (Exception e) {
            return "AI 응답 파싱 중 오류 발생: " + e.getMessage();
        }

        StringBuilder result = new StringBuilder();
        result.append("다음 추천 장소는 \"").append(place).append("\"입니다.\n");
        result.append("추천 사유는 \"").append(reason).append("\"입니다.\n");
        if (food != null && !food.isBlank()) {
            result.append("추천 음식은 \"").append(food).append("\"입니다.");
        }

        return result.toString();
    }
}