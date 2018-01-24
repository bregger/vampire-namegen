(ns vampire-web.core
  (:gen-class)
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

(defn wrap-logging [handler]
  (fn [request]
    (println (str ">>> REQUEST [" (:request-method request) "] " (:uri request)))
    (let [response (handler request)]
      (println "<<< RESPONSE" (:status response))
      response)))

(def app
  (-> approutes
      wrap-logging
      wrap-params))

(defn -main [& [port]]
  (let [port (Integer. (or port (env :port) 9000))]
    (println "Application starting. Listening on port" port)
    (run-server app {:port port})))