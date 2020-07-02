package demo.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Artist {
	@JsonProperty("artists")
	private List<InnerArtist>list=new ArrayList<>();


@Data
@NoArgsConstructor
@AllArgsConstructor
	public static class InnerArtist{
	@JsonProperty("external_urls")
	private ExternalUrls externalUrls;

	@JsonProperty("followers")
	private Followers followers;

	@JsonProperty("genres")
	private List<String> genres = new ArrayList<>();

	@JsonProperty("name")
	private String name;

	@JsonProperty("popularity")
	private Integer popularity;

	@JsonProperty("uri")
	private String uri;

	@JsonProperty("id")
	private String id;}

}
