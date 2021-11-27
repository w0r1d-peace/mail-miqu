package com.islet.support.elasticsearch.domain;

import com.islet.service.mail.handler.server.UniversalAttachment;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 邮件文档结构
 *
 * @author tangJM.
 * @date 2021/11/03
 */
@Data
@ToString
@Document(indexName = "emailx")
public class EmailEs implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private Long taskId;
    private String email;
    private String uid;
    private String fromer;
    private List<EsRecipient> receiver;
    private List<EsRecipient> bcc;
    private List<EsRecipient> cc;
    private String folder;
    private Boolean hasAttachment;
    private Boolean hasRead;
    private Date sendDate;
    private String title;
    private Date createTime;
    private Long userId;
    private String content;
    private String emlPath;
    private List<UniversalAttachment> attachments;
}
