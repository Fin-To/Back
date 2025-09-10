package FinTo.global.config.chat;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class SignalingChatHandler extends TextWebSocketHandler {

    // 현재는 사용자 연결에 대한 정보를 (A,B가 화상채팅 중임) 서버 메모리 세션상에서 관리하고있음.
    // websocket CORS 설정해야함
    // websocket은 HTTP 헤더에 Authentication 객체 못담아보내는 상황
    private final Set<WebSocketSession> sessions = ConcurrentHashMap.newKeySet();
    private final ObjectMapper objectMapper = new ObjectMapper();

    // 연결 되면
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        log.info("Client connected: {}", session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();

        try {
            // JSON 파싱
            JsonNode data = objectMapper.readTree(payload);
            String messageType = data.get("type").asText();

            log.info("Received message type: {} from session: {}", messageType, session.getId());

            // 메시지 타입에 따른 처리 (필요시 확장 가능)
            switch (messageType) {
                // WebRTC 연결 제안, 응답, ICE 처리입니다.
                case "offer":
                case "answer":
                case "ice":
                // websocket 채팅 메시지 처리입니다.
                case "chat":
                    broadcastToOthers(session, payload);
                    break;
                default:
                    log.warn("Unknown message type: {}", messageType);
                    broadcastToOthers(session, payload);
                    break;
            }

        } catch (Exception e) {
            log.error("Invalid JSON or processing error: {}", e.getMessage());
        }
    }

    private void broadcastToOthers(WebSocketSession sender, String message) {
        sessions.stream()
                .filter(session -> !session.equals(sender) && session.isOpen())
                .forEach(session -> {
                    try {
                        session.sendMessage(new TextMessage(message));
                    } catch (IOException e) {
                        log.error("Failed to send message to session {}: {}",
                                session.getId(), e.getMessage());
                        // 연결이 끊어진 세션은 제거
                        sessions.remove(session);
                    }
                });
    }

    // 연결 끊기면
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
        log.info("Client disconnected: {}, status: {}", session.getId(), status);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        sessions.remove(session);
        log.error("Transport error for session {}: {}", session.getId(), exception.getMessage());
    }
}
