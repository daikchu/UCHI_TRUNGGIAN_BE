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
package com.vn.osp.notarialservices.questionHelp.repository;
import com.vn.osp.notarialservices.questionHelp.domain.QuestionBO;
import java.util.List;

/**
 * Simple interface for custom methods on the repository for {@code Question}s.
 *
 * @author DaiCQ on 08/02/2020.
 */
public interface QuestionRepositoryCustom {

    Long count(String search);
    List<QuestionBO> page(int page, String search);
    List<QuestionBO> search(String search);
    QuestionBO get(Long id);
    Long add(QuestionBO item);
    Long update(QuestionBO item);
    Boolean delete(Long id);

}
