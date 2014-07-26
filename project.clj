(defproject vampire-web "0.0.1"
  :description "Extreme Vampire Name Generator"

  :dependencies [[org.clojure/clojure "1.5.1"]
                 [ring "1.3.0"]
                 [hiccup "1.0.5"]
                 [compojure "1.1.8"]]

  :plugins [[lein-ring "0.8.11"]]

  :ring {:handler vampire-web.core/app})