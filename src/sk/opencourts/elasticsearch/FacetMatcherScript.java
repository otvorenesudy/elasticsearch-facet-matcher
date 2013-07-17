package sk.opencourts.elasticsearch;

import java.util.Map;

import org.elasticsearch.common.Nullable;
import org.elasticsearch.script.AbstractSearchScript;

public final class FacetMatcherScript extends AbstractSearchScript
{
	private final String term;
	private final String query;

	public FacetMatcherScript(final @Nullable Map<String, Object> parameters)
	{
		this.query = (String) parameters.get("query");
	}

	private static final String analyze(final String s)
	{
		return Strings.unaccent(s).toLowerCase();
	}

	@Override
	public final Object run()
	{
		String value = analyze(this.term);

		if (value.contains(analyze(this.query)))
		{
			return this.term;
		}

		return null;
	}

	@Override
	public final void setNextVar(final String name, final Object value)
	{
		if (name.equals("term"))
		{
			this.term = value.toString();
		}
		else
		{
			super.setNextVar(name, value);
		}
	}
}
