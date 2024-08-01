package com.anticoder.DemoWs.controller;

import com.anticoder.DemoWs.entity.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ChatController {
  private final SimpMessageSendingOperations messagingTemplate;

  @GetMapping
  public String hello() {
    return "Hello World";
  }

  @MessageMapping("app/hello") // Đường dẫn tới enpoint của ws trong server
  // @SendTo("/topic/public") // Đường dẫn tới destination cụ thể
  public void hello(ChatMessage message) throws InterruptedException {
    log.info("Received message: {}", message.getContent());
    System.out.println(message.getContent());
    messagingTemplate.convertAndSend("/topic/public", message);
    log.info("Message sent to /topic/public");
  }

}
