Elasticsearch Facet Matcher
===========================

Native script for matching facet values against specified query.

It's maily supposed to be used as a validation tool for facet values when suggesting facet on array field, since you might get values that match your query along with other values in array field.
It's pretty dump right now, it check if facet term contains the query. We plan to include more advanced options with usage of Lucene query.

## Installation

1. Clone the repository in elasticsearch root

```
cd elasticsearch-0.90.2
git clone https://github.com/otvorenesudy/elasticsearch-facet-matcher.git
cd elasticsearch-facet-matcher
```

2. Open `Rakefile` and change required libraries verions of `elasticsearch`, `lucene-core` and `lucene-analyzer-common` according to one locates in your elasticsearch `lib` directory.
3. Run `rake build`
4. Build copies `elasticsearch-facet-matcher.jar` to your elasticsearch `lib` dir for autoloading.
5. Open `config/elasticsearch.yml` and add this to the end of file:

```
script.native:
  facet_matcher.type: sk.opencourts.elasticsearch.FacetMatcherFactory

```

You can change name of `facet_matcher` to any name you desire.

6. Restart elasticsearch and try it out.


## Example

This searches for any value in faceted field `judges.untouched` matching pattern 'peter*' by lucene query and validates each facet value by the same query in script.

```json


{
  "facets": {
    "judges": {
      "terms": {
        "field": "judges.untouched",
        "size": 10,
        "all_terms": false,
        "script": "facet_matcher",
        "params": {
          "query": "peter"
        },
        "lang": "native"
      },
      "global_facets": false,
      "facet_filter": {
        "query": {
          "filtered": {
            "query": {
              "bool": {
                "must": [
                  {
                    "query_string": {
                      "query": "peter* ",
                      "default_operator": "and",
                      "analyze_wildcard": true,
                      "fields": [
                        "judges.analyzed"
                      ]
                    }
                  }
                ]
              }
            }
          }
        }
      }
    }
  }
}

```

## Contributing

1. Fork it
2. Create your feature branch `git checkout -b new-feature`
3. Commit your changes `git commit -am 'Added some feature'`
4. Push to the branch `git push origin new-feature`
5. Create new Pull Request

## License

### This code is free to use under the terms of the MIT license.

Copyright (c) 2013 Samuel Molnár

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

