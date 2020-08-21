package demo.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import demo.model.Artist;
import demo.model.Artist.InnerArtist;
import demo.model.SearchItem;

@Component
public class SpotifyService {
	private RestTemplate restTemplate = new RestTemplate();

	//類似アーティスト
	public Artist getArtist(OAuth2Authentication oAuth2Authentication ,String id) {

		OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) oAuth2Authentication.getDetails();
		String token = details.getTokenValue();

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Authorization", "Bearer " + token);
		httpHeaders.add("Accept-Language", "ja;q=1");
		HttpEntity<Artist> httpEntity = new HttpEntity<>(httpHeaders);

		ResponseEntity<Artist> responseEntity = restTemplate.exchange(
				"https://api.spotify.com/v1/artists/"+ id +"/related-artists", HttpMethod.GET,
				httpEntity, Artist.class);
		return responseEntity.getBody();
	}

	//アーティスト情報
	public SearchItem search(OAuth2Authentication oAuth2Authentication, String q) {

		OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) oAuth2Authentication.getDetails();
		String token = details.getTokenValue();

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Authorization", "Bearer " + token);
		httpHeaders.add("Accept-Language", "ja;q=1");
		HttpEntity<SearchItem> httpEntity = new HttpEntity<>(httpHeaders);

		ResponseEntity<SearchItem> responseEntity = restTemplate.exchange(
				"https://api.spotify.com/v1/search?q=" + q + "&type=artist", HttpMethod.GET,
				httpEntity, SearchItem.class);
		return responseEntity.getBody();

	}

	//類似アーティストのID取得
	public InnerArtist getId(OAuth2Authentication oAuth2Authentication ,String id){
		OAuth2AuthenticationDetails details =(OAuth2AuthenticationDetails) oAuth2Authentication.getDetails();
		String token =details.getTokenValue();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Authorization", "Bearer " + token);
		httpHeaders.add("Accept-Language", "ja;q=1");
		HttpEntity<InnerArtist> httpEntity = new HttpEntity<>(httpHeaders);

		ResponseEntity<InnerArtist> responseEntity = restTemplate.exchange(
				"https://api.spotify.com/v1/artists/" + id , HttpMethod.GET, httpEntity, InnerArtist.class);
		return responseEntity.getBody();

	}

}
