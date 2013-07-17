package sk.opencourts.elasticsearch;

import java.util.Map;

import org.elasticsearch.common.Nullable;
import org.elasticsearch.script.ExecutableScript;
import org.elasticsearch.script.NativeScriptFactory;

public final class FacetMatcherFactory implements NativeScriptFactory
{
	@Override
	public final ExecutableScript newScript(final @Nullable Map<String, Object> parameters)
	{
		return new FacetMatcherScript(parameters);
	}

}
