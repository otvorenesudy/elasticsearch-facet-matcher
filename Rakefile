require 'fileutils'

task :compile do
  source = FileList['src/**/*.java']
  sh "javac -d ./bin -cp \"../lib/lucene-analyzers-common-4.3.1.jar:../lib/lucene-core-4.3.1.jar:../lib/elasticsearch-0.90.2.jar:.\" #{source.collect {|s| "'#{s}'" }.join(' ')}"
end

task :clean do
  FileUtils::Verbose.rm Dir.glob("**/*.class")
  sh "rm -rf ../lib/elasticsearch-facet-matcher.jar ./elasticsearch-facet-matcher.jar"
end

task :package do
  cd './bin'

  objects = FileList['**/*.class']

  sh "jar cf elasticsearch-facet-matcher.jar #{objects.collect {|s| "'#{s}'" }.join(' ')}"

  sh "cp elasticsearch-facet-matcher.jar ../../lib/"
end

task :build => [:clean, :compile, :package]

