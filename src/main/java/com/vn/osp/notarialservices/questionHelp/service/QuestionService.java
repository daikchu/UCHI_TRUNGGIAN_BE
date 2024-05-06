package com.vn.osp.notarialservices.questionHelp.service;

import com.vn.osp.notarialservices.questionHelp.dto.Question;
import java.util.List;

/**
 * Created by manhtran on 20/10/2016.
 */
public interface QuestionService {

    Long count(String search);
    List<Question> page(int page, String search);
    List<Question> search(String search);
    Question get(Long id);
    Long add(Question item);
    Long update(Question item);
    Boolean delete(Long id);

}
