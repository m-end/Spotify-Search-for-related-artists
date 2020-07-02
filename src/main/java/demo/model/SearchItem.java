package demo.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import demo.model.Artist.InnerArtist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchItem {

	@JsonProperty("artists")
	private InnerItem innerItem;

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class InnerItem{
		@JsonProperty("items")
		private List<InnerArtist> item = new ArrayList<>();

	}
}
