package com.dw.NAMANSOLOJAVA.Config;


import com.dw.NAMANSOLOJAVA.chat.JwtChannelInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker  //WebSocket 메시징을 활성화하는 역할
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Autowired
    private JwtChannelInterceptor jwtChannelInterceptor;

    // stomp(Simple Text Oriented Messaging Protocol)의 앤드포인트 설정
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                //react의 서버 주소(이쪽에서날라온건 받아달라는 의미)
                .setAllowedOriginPatterns("http://localhost:5173", "http://192.168.0.77:82")
                .withSockJS();
    }

    //클라이언트에서 보내는 메시지와 서버에서 보내는 메시지를 구분하고 처리할 수 있는 브로커를 설정합니다.
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //브로커(서버) : 받은 내용을 구독을 한 모든 클라이언트에게 전달을 하라는 의미
        // /topic, /user 를 브로커의 목적지로 설정합니다(채널 주제)
        registry.enableSimpleBroker("/topic", "/user");
        // 클라이언트에서 /user 로 시작하는 목적지를 구독하면, 이를 내부적으로 처리할 수 있도록 함(개인에게 보내는 채팅에 사용)
        registry.setUserDestinationPrefix("/user");
        // 클라이언트가 보내는 메시지는 /app 으로 시작해야 함.
        registry.setApplicationDestinationPrefixes("/app");
    }

    // 클라이언트가 메시지를 보낼 때마다 이 인터셉터가 실행되어,
    // 해당 메시지가 유효한지 (즉, 인증된 사용자 요청인지) 확인합니다.
    // 이 인터셉터는 주로 메시지의 헤더에서 JWT 토큰을 검사하는 데 사용됩니다.
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(jwtChannelInterceptor); // 인터셉터 등록
    }
}