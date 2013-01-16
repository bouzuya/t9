(ns t9.reading
  (:import [org.atilika.kuromoji Token Tokenizer]))

(def ^:dynamic ^Tokenizer *tokenizer* nil)

(defmacro with-tokenizer
  [& body]
  `(if (nil? *tokenizer*)
     (binding [*tokenizer* (.build (Tokenizer/builder))]
       ~@body)
     ~@body))

(defn tokenize
  [s]
  (seq (.tokenize *tokenizer* s)))

(defn reading
  [s]
  (with-tokenizer
    (apply str
           (map #(or (.getReading %)
                     (.getSurfaceForm %))
                (tokenize s)))))
