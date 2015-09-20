package com.pep1.kitchenoffice.service;

import javax.annotation.PostConstruct;

import com.pep1.kitchenoffice.data.Recipe;
import com.pep1.kitchenoffice.repository.ArticleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.pep1.kitchenoffice.data.Article;

@Service
@Scope("singleton")
public class ArticleService {

	private static Logger log = LoggerFactory.getLogger(ArticleService.class);

	@Autowired
	ArticleRepository articleRepository;

	@PostConstruct
	public void initialize() {
		log.debug("initializing " + this.getClass().getSimpleName() + " instance ... ");
	}

	public Article save(Article article) {
		Assert.notNull(article, "Article to save may not be null");
		articleRepository.save(article);
		return article;
	}

	public Article refresh(Article article) {
		return articleRepository.findOne(article.getId());
	}

	public void addToRecipe(Article article, Recipe recipe, Integer amount) {

	}

}
