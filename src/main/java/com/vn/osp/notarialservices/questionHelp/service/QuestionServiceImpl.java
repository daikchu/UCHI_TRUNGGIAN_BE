package com.vn.osp.notarialservices.questionHelp.service;

import com.vn.osp.notarialservices.questionHelp.domain.QuestionBO;
import com.vn.osp.notarialservices.questionHelp.dto.Question;
import com.vn.osp.notarialservices.questionHelp.repository.QuestionRepositoryCustom;
import com.vn.osp.notarialservices.questionHelp.repository.QuestionRepositoryImpl;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manhtran on 20/10/2016.
 */
@Component
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    QuestionRepositoryCustom questionRepositoryCustom;
    ModelMapper modelMapper = new ModelMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionServiceImpl.class);

    @Override
    public Long count(String search) {
        return questionRepositoryCustom.count(search);
    }

    @Override
    public List<Question> page(int page, String search) {
        List<QuestionBO> itemBOs = questionRepositoryCustom.page(page, search);
        List<Question> items = new ArrayList<>();
        for (QuestionBO itemBo: itemBOs) {
            items.add(modelMapper.map(itemBo, Question.class));
        }
        return items;
    }

    @Override
    public List<Question> search(String search) {
        List<QuestionBO> itemBOs = questionRepositoryCustom.search(search);
        List<Question> items = new ArrayList<>();
        for (QuestionBO itemBo: itemBOs) {
            items.add(modelMapper.map(itemBo, Question.class));
        }
        return items;
    }

    @Override
    public Question get(Long id) {
        QuestionBO itemBO = questionRepositoryCustom.get(id);
        Question item = modelMapper.map(itemBO, Question.class);
        return item;
    }

    @Override
    public Long add(Question item) {
        QuestionBO itemBO = modelMapper.map(item, QuestionBO.class);
        return questionRepositoryCustom.add(itemBO);
    }

    @Override
    public Long update(Question item) {
        QuestionBO itemBO = modelMapper.map(item, QuestionBO.class);
        return questionRepositoryCustom.update(itemBO);
    }

    @Override
    public Boolean delete(Long id) {
        return questionRepositoryCustom.delete(id);
    }

    /*private final AccessHistoryRepository accessHistoryRepository;
    private final QuestionConverter accessHistoryConverter;*/


}
