package com.jianma.xtdm;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jianma.xtdm.model.Article;
import com.jianma.xtdm.model.PageObject;
import com.jianma.xtdm.service.ArticleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml","file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ArticleTest {

	@Autowired
	@Qualifier("articleServiceImpl")
	private ArticleService articleServiceImpl;
	
	//@Test
	public void createArticle(){
		for (int i = 0; i < 10; i++){
			Article article = new Article();
			article.setCreateTime(new Date());
			article.setTitle("习近平接受八国新任驻华大使递交国书");
			article.setAbstractContent("习近平接受八国新任驻华大使递交国书习近平接受八国新任驻华大使递交国书习近平接受八国新任驻华大使递交国书习近平接受八国新任驻华大使递交国书习近平接受八国新任驻华大使递交国书");
			article.setCategoryId(1);
			article.setRecommand((byte)1);
			article.setLabel("java");
			article.setThumb("http://img2.cache.netease.com/photo/0005/2017-07-12/CP69TE9F4FFD0005.jpg");
			article.setType((byte)1);
			article.setContent("人民大会堂北门外台阶上，礼兵分列两侧。新任驻华使节们先后抵达，沿着台阶拾级而上，进入北京厅。在“北京鸟瞰”图前，使节们依次向习近平呈递国书，习近平同他们一一握手并合影。这8位新任驻华大使是：阿尔巴尼亚驻华大使贝洛尔塔亚、墨西哥驻华大使贝尔纳尔、吉尔吉斯斯坦驻华大使乌谢诺夫、博茨瓦纳驻华大使帕拉伊、加纳驻华大使博阿滕、美国驻华大使布兰斯塔德、法国驻华大使黎想、哥斯达黎加驻华大使罗德里格斯。");
			articleServiceImpl.createArticle(article);
		}
	}
	
	//@Test
	public void deleteArticle(){
		articleServiceImpl.deleteArticle(2);
	}
	
	//@Test
	public void getListArticleByPage(){
		PageObject pObject = articleServiceImpl.getArticleByPage(1, 0, 10);
		List<Article> list = (List<Article>)pObject.getList();
		System.out.println(list.size());
		
		list.stream().forEach(article->{
			
			System.out.println(article.getId()+" -- "+article.getTitle()+" -- "+article.getContent());
		});
	}
	
	@Test
	public void getTopArticle(){
		List<Article> list = articleServiceImpl.getRecommandArticle(10);

		System.out.println(list.size());
		
		list.stream().forEach(article->{
			
			System.out.println(article.getId()+" -- "+article.getTitle()+" -- "+article.getContent());
		});
	}
}
