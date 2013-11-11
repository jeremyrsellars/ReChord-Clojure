(defproject rechord "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :plugins [[lein-cljsbuild "0.3.4"]]
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-1934"]]
  :source-paths ["src"],
  :cljsbuild {
    :crossovers [rechord.core rechord.linereader rechord.htmlrenderer rechord.main],
    :builds [{
      :source-paths ["src" "target/cljsbuild-crossover"],
      :crossover-path "src/rechord",
      :compiler {
        :output-to "resources/public/index.js"
        :optimizations :simple
        :pretty-print true
        :target :nodejs
      }
    }]})
