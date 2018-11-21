package com.suchaos.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Message
 *
 * @author suchao
 * @date 2018/11/21
 */
@Data
public class Message {
    private Long id;
    private String text;
    private String summary;
    private LocalDateTime createAt = LocalDateTime.now();
}
