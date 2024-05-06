/*
 * Copyright 2008-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.vn.osp.notarialservices.questionHelp.controller;

import com.vn.osp.notarialservices.questionHelp.dto.Question;
import com.vn.osp.notarialservices.questionHelp.service.QuestionService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DaiCQ on 08/02/2020.
 */
@Controller
@RequestMapping(value = "/question-help")
public class QuestionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionController.class);

    @Autowired
    QuestionService questionService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResponseEntity<Long> add(@RequestBody final Question item){
        Long id = questionService.add(item);
        return new ResponseEntity<>(id,HttpStatus.OK);

    }
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ResponseEntity<Long> update(@RequestBody final Question item){
        Long id = questionService.update(item);
        return new ResponseEntity<>(id,HttpStatus.OK);
    }
    // lay list va phan trang
    @RequestMapping(value = "/page",method = RequestMethod.GET)
    public ResponseEntity<List<Question>> getContractFee(@RequestParam (name = "page", required = false, defaultValue = "1") final int page,
                                                         @RequestParam (name = "search", required = false, defaultValue = "") final String search,
                                                         @RequestParam (name = "type", required = false, defaultValue = "") final String type){
        String query = "";
        if(!StringUtils.isBlank(type)){
            query += " and bo.type = "+type;
        }
        query += search;

        List<Question> list = new ArrayList<>();
        list = questionService.page(page,query);
        return new ResponseEntity<>(list,HttpStatus.OK);

    }

    // lay list va phan trang
    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public ResponseEntity<List<Question>> getContractFee(@RequestParam (name = "search", required = false, defaultValue = "") final String search,
                                                         @RequestParam (name = "type", required = false, defaultValue = "") final String type){
        String query = "";
        if(!StringUtils.isBlank(type)){
            query += " and bo.type = "+type;
        }
        query += search;

        List<Question> list = new ArrayList<>();
        list = questionService.search(query);
        return new ResponseEntity<>(list,HttpStatus.OK);

    }

    // lay list va phan trang
    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public ResponseEntity<List<Question>> getContractFee(@RequestBody final String search){
        List<Question> list = new ArrayList<>();
        list = questionService.search(search);
        return new ResponseEntity<>(list,HttpStatus.OK);

    }

    @RequestMapping(value = "/count",method = RequestMethod.GET)
    public ResponseEntity<Long> count(@RequestParam (name = "search", required = false, defaultValue = "") final String search,
                                      @RequestParam (name = "type", required = false, defaultValue = "") final String type){
        String query = "";
        if(!StringUtils.isBlank(type)){
            query += " and bo.type = "+type;
        }
        query += search;
        return new ResponseEntity<>(questionService.count(query),HttpStatus.OK);
    }

    @RequestMapping(value = "/count",method = RequestMethod.POST)
    public ResponseEntity<Long> count(@RequestBody final String search){
        return new ResponseEntity<>(questionService.count(search),HttpStatus.OK);
    }

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public ResponseEntity<Question> get(@RequestParam final Long id){
        Question item = new Question();
        item = questionService.get(id);
        return new ResponseEntity<>(item,HttpStatus.OK);

    }
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public ResponseEntity<Boolean> deleteContractFee(@RequestBody @Valid final Long id){

        Boolean check = questionService.delete(id);
        return new ResponseEntity<>(check,HttpStatus.OK);

    }

}
