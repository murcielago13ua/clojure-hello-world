(ns clojure-docker.core)
(require '[compojure.core :refer :all]
         '[compojure.handler :as handler]
         '[compojure.route :as route]
         '[org.httpkit.server :as ohs])



(def messages (atom []))

(defrecord Person [name age])
(defn index [req]
  {
   :body   (->Person "Vitalii" 20)
   :status 200
   })

(defn put-message [name message]
  (swap! messages conj (str name ": " message))
  "OK"
  )

(defn read-messages [req]
  (str "<h1>Messages:</h1>"
       (apply str (map #(str "<h3>" % "</h3>") @messages)))
  )

(defroutes app-routes
           (GET "/" [] #'index)
           (POST "/write" [name message] (put-message name message))
           (GET "/read" [] #'read-messages))

(def app
  (handler/site app-routes))

(defn -main []
  (ohs/run-server app {:port 5000})
  (println "started at 5000"))
