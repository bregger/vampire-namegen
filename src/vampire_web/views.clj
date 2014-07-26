(ns vampire-web.views
  (:use hiccup.page))

(defn layout
  [title & content]
  (html5 [:head
           [:title (str title " - Vampire Web")]
           (include-css "main.css")]
         [:body
           [:div#wrap
             [:h1 title]
             [:div#content content]]]))

(def index
  (layout "Vampire Name Generator"
          [:form {:method "POST" :action "/bestow-name"}
            [:input.name {:type "text" :placeholder "enter your human name" :required true :name "human-name"}]
            [:input {:type "submit" :value "Tell Me My Vampire Name"}]]))

(defn show-name
  [vampire-name]
  (layout "Your Vampire Name"
          [:p "HUMAN, I bestow upon thee the vampire name"]
          [:p.vampire-name vampire-name]))
