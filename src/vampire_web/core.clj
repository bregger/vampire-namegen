(ns vampire-web.core
  (:use compojure.core)
  (:use [org.httpkit.server :only [run-server]])
  (:use ring.middleware.params)
  (:require [compojure.route :as route])
  (:require [vampire-web.views :as views])
  (:require [vampire-web.namegen :as namegen])
  (:require [environ.core :refer [env]]))

(defroutes approutes
  (GET "/"   [] views/index)

  (POST "/bestow-name" [human-name]
    (views/show-name (namegen/vampire-name human-name)))

  (route/resources "/")
  (route/not-found "Page not found"))

(def app
  (-> approutes
      wrap-params))

(defn -main [& [port]]
  (let [port (Integer. (or port (env :port) 9000))]
    (run-server app {:port port})))