package com.islet.support.elasticsearch.repository;

import com.islet.support.elasticsearch.domain.EmailEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author tangJM.
 * @date 2021/11/03
 */
public interface EmailEsRepository extends ElasticsearchRepository<EmailEs, String>, PagingAndSortingRepository<EmailEs, String> {
}
