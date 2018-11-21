package com.suchaos.controller;

import com.suchaos.model.Message;
import com.suchaos.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * MessageController
 *
 * @author suchao
 * @date 2018/11/21
 */
@RestController
@RequestMapping("/")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping(value = "messages")
    public List<Message> list() {
        return this.messageRepository.findAll();
    }

    @PostMapping(value = "message")
    public Message create(Message message) {
        return this.messageRepository.save(message);
    }

    @PostMapping(value = "messageJson")
    public Message createWithJson(@RequestBody Message message) {
        return this.messageRepository.save(message);
    }

    @PutMapping(value = "message")
    public Message modify(Message message) {
        return this.messageRepository.update(message);
    }

    @PatchMapping(value = "/message/text")
    public Message patch(Message message) {
        return this.messageRepository.updateText(message);
    }

    @GetMapping(value = "message/{id}")
    public Message get(@PathVariable Long id) {
        return this.messageRepository.findMessage(id);
    }

    @DeleteMapping(value = "message/{id}")
    public void delete(@PathVariable("id") Long id) {
        this.messageRepository.deleteMessage(id);
    }
}
