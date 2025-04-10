package com.dw.NAMANSOLOJAVA.Service;

import com.dw.NAMANSOLOJAVA.Exception.ResourceNotFoundException;
import com.dw.NAMANSOLOJAVA.Repository.AlbumRepository;
import com.dw.NAMANSOLOJAVA.Repository.UserRepository;
import com.dw.NAMANSOLOJAVA.model.Album;
import com.dw.NAMANSOLOJAVA.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class GPTService {
    @Value("${openai.api.key}")
    private String apiKey;

    @Autowired
    private UserRepository userRepository;
    @Autowired private AlbumRepository albumRepository;
    @Autowired
    private UserService userService;

    public String getPersonalizedRecommendation() {
        User user = userService.getCurrentUser();
        //해당 유저의 장소를 가져오기!!!
        List<Album> albums = albumRepository.findByUser_Username(user.getUsername());
        List<String> locations = albums.stream()
                .map(Album::getLocation)
                .distinct()
                .toList();

        String prompt = buildPrompt(locations);
        return askGPT(prompt);
    }

    private String buildPrompt(List<String> locations) {
        String lastLocation = locations.get(locations.size() - 1); // 가장 최근 다녀온 장소
        return String.format("""
            다음은 한 커플이 데이트를 다녀온 장소 목록입니다:

            %s

            이 장소들을 통해 커플의 데이트 스타일을 추론해 주세요.
            그리고 마지막에 방문한 장소인 '%s'의 근처에서 갈 만한
            새로운 데이트 장소를 하나만 추천해 주세요.

            그 후, 이 커플이 아직 방문하지 않은 새로운 데이트 장소를 1곳 추천해 주세요.
            그 후, 음식을 파는 곳이면 메뉴도 1개 이상으로 추천해주세요.
            조건:
            - 분위기와 스타일이 기존과 유사하거나 살짝 새로운 느낌이면 좋습니다.
            - 장소명과 간단한 이유를 함께 알려주세요.
            """, String.join("\n- ", locations),lastLocation);
    }

    private String askGPT(String prompt) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> body = Map.of(
                "model", "gpt-4 turbo",
                "temperature", 0.8,
                "messages", List.of(
                        Map.of("role", "system", "content", "당신은 데이트 장소를 추천해주는 감성적인 챗봇입니다."),
                        Map.of("role", "user", "content", prompt)
                )
        );

        HttpEntity<?> request = new HttpEntity<>(body, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(
                "https://api.openai.com/v1/chat/completions", request, Map.class
        );

        List choices = (List) response.getBody().get("choices");
        Map message = (Map) ((Map) choices.get(0)).get("message");
        return message.get("content").toString();
    }

}
