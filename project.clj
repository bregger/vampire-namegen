(defproject vampire-web "0.0.1"
  :description "Extreme Vampire Name Generator"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [ring "1.3.0"]
                 [hiccup "1.0.5"]
                 [compojure "1.1.8"]]

  :uberjar-name "vampire-web.jar"
				 
  :plugins [[lein-ring "0.8.11"]]

  :ring {:handler vampire-web.core/app})
