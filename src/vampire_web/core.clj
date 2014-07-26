(ns vampire-web.core
  (:use compojure.core)
  (:use ring.middleware.params)
  (:require [compojure.route :as route])
  (:require [vampire-web.views :as views])
  (:require [vampire-web.namegen :as namegen]))

(defroutes approutes
  (GET "/"   [] views/index)

  (POST "/bestow-name" [human-name]
    (views/show-name (namegen/vampire-name human-name)))

  (route/resources "/")
  (route/not-found "Page not found"))

(def app
  (-> approutes
      wrap-params))
