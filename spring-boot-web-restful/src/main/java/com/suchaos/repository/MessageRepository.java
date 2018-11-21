package com.suchaos.repository;

import com.suchaos.model.Message;

import java.util.List;

/**
 * MessageRepository
 *
 * @author suchao
 * @date 2018/11/21
 */
public interface MessageRepository {

    List<Message> findAll();

    Message save(Message message);

    Message update(Message message);

    Message updateText(Message message);

    Message findMessage(Long id);

    void deleteMessage(Long id);
}
