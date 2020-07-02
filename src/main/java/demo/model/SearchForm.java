package demo.model;

import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class SearchForm {

	private String q;

	@Pattern(regexp="[a-zA-Z0-9]*")
	private String id;
}
