package com.simple.drawing.jms;

public interface SessionQueueSender {

  void sendMessageToQueue(String text);
  
}
