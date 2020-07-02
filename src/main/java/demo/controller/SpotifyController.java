package demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import demo.model.Artist;
import demo.model.SearchForm;
import demo.model.SearchItem;
import demo.service.SpotifyService;

@Controller
public class SpotifyController {

	@Autowired
	private SpotifyService spotifyClient;

	@GetMapping("/")
	public String form(@ModelAttribute SearchForm form, OAuth2Authentication oAuth2Authentication, Model model) {
		return "index";
	}

	//類似アーティスト検索
	@GetMapping("/related")
	public String index(@Validated SearchForm form, BindingResult result, Model model,
			@RequestParam String id, OAuth2Authentication oAuth2Authentication) {

		model.addAttribute(form);
		if (result.hasErrors()) {
			return "index";
		}
		Artist artist = spotifyClient.getArtist(oAuth2Authentication, id);
		model.addAttribute("artist", artist.getList());
		return "related";
	}

	//アーティスト情報検索
	@GetMapping("/search")
	public String getArtis(@Validated SearchForm form, BindingResult result, Model model,
			@RequestParam(name = "q") String q, OAuth2Authentication oAuth2Authentication) {

		model.addAttribute(form);
		if (result.hasErrors()) {
			return "index";
		}
		SearchItem searchItem = spotifyClient.search(oAuth2Authentication, q);
		model.addAttribute("searchItem", searchItem.getInnerItem().getItem());
		return "search";
	}

	//例外処理
	@ExceptionHandler(Exception.class)
	public String handleException(Exception e) {
		System.out.println(e.getClass().getName());
		e.printStackTrace();
		return "error";
	}
}
