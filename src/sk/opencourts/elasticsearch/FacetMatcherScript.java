package sk.opencourts.elasticsearch;

import java.util.Map;

import org.elasticsearch.common.Nullable;
import org.elasticsearch.script.AbstractSearchScript;

public class FacetMatcherScript extends AbstractSearchScript {

	private String   term;
	private String   query;
	
	public FacetMatcherScript(@Nullable Map<String, Object> params) {
		query = (String) params.get("query");
	}

	@Override
	public Object run() {
		String   value   = analyzeString(term);
		String[] queries = tokenizeString(analyzeString(query));
		
		for(String q : queries) {
			if (!value.contains(q)) {
				return null;
			}
		}
		
		return term;
	}
	
	@Override
	public void setNextVar(String name, Object value) {
		if (name.equals("term")) {
			term = (String) value;
		}
		else {
			super.setNextVar(name, value);
		}
	}
	
	private String analyzeString(String s) {
		return StringUtils.unaccent(s).toLowerCase();
	}
	
	private String[] tokenizeString(String s) {
		return StringUtils.tokenize(s);
	}
}
