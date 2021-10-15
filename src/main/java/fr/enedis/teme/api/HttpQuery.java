package fr.enedis.teme.api;

import static java.util.Objects.requireNonNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public final class HttpQuery extends HttpRequest {

	private boolean debug = false;
	private boolean disabled = false;
	private boolean strict = true;
	private boolean parallel = true;
	private String description = "";
	private HttpRequest expected;
	private HttpRequest actual;

	public HttpQuery build() {
		if(getUri() == null) {
			requireNonNull(expected);
			requireNonNull(actual);
		}
		else {
			expected = actual = this;
		}
		return this;
	}

	@Override
	public String toString() {
		return description + " : " + super.toString();
	}
	
}