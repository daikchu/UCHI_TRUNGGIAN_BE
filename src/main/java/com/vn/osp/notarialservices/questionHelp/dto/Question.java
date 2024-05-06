package com.vn.osp.notarialservices.questionHelp.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.vn.osp.notarialservices.common.dto.BaseEntityBeans;
import lombok.Data;

import java.sql.Timestamp;

@Data
@XStreamAlias("Question")
public class Question{
  private Long id;
  private String question;
  private String answer;
  private Integer type;
  private Long entry_user_id;
  private Long update_user_id;
  private Timestamp entry_date_time;
  private Timestamp update_date_time;


/*  @JsonCreator
  public Question(@JsonProperty(value = "id", required = true) final Long id,
                  @JsonProperty(value = "question", required = true) final String question,
                  @JsonProperty(value = "answer", required = false) final String answer,
                  @JsonProperty(value = "client_info", required = false) final String client_info,
                  @JsonProperty(value = "execute_date_time", required = false) final String execute_date_time,
                  @JsonProperty(value = "access_type", required = false) final Integer access_type) {
    this.hid = hid;
    this.usid = usid;
    this.execute_person = execute_person;
    this.client_info = client_info;
    this.execute_date_time = execute_date_time;
    this.access_type = access_type;
  }*/

  public Question() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public Long getEntry_user_id() {
    return entry_user_id;
  }

  public void setEntry_user_id(Long entry_user_id) {
    this.entry_user_id = entry_user_id;
  }

  public Long getUpdate_user_id() {
    return update_user_id;
  }

  public void setUpdate_user_id(Long update_user_id) {
    this.update_user_id = update_user_id;
  }

  public Timestamp getEntry_date_time() {
    return entry_date_time;
  }

  public void setEntry_date_time(Timestamp entry_date_time) {
    this.entry_date_time = entry_date_time;
  }

  public Timestamp getUpdate_date_time() {
    return update_date_time;
  }

  public void setUpdate_date_time(Timestamp update_date_time) {
    this.update_date_time = update_date_time;
  }
}
