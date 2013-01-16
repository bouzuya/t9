(ns t9.core
  (:require [t9.reading :as r]
            [clojure.string :as str])
  (:import [twitter4j TwitterFactory Twitter])
  (:gen-class))

(defn help
  []
  (println "Usage: java -jar t9.jar help")
  (println "Usage: java -jar t9.jar tweet <status>")
  (println "Usage: java -jar t9.jar tweet-reading <status>")
  (println "Usage: java -jar t9.jar tweet-reverse <status>"))

(defn tweet
  [status]
  (let [^TwitterFactory factory (TwitterFactory.)
        ^Twitter twitter (.getInstance factory)]
    (.updateStatus twitter status)))

(defn tweet-reading
  [status]
  (tweet (r/reading status)))

(defn tweet-reverse
  [status]
  (tweet (str/reverse status)))

(defn -main
  [& args]
  (if (empty? args)
    (help)
    (let [[command & options] args]
      (case (keyword command)
        :help (help)
        :tweet (tweet (first options))
        :tweet-reading (tweet-reading (first options))
        :tweet-reverse (tweet-reverse (first options))))))

