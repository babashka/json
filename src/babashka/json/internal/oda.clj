(ns babashka.json.internal.oda
  (:require [s-exp.oda :as oda])
  (:refer-clojure :exclude [read]))

(set! *warn-on-reflection* true)

(defn ->json-reader
  ([x] (->json-reader x nil))
  ([x {:keys [key-fn]}]
   (oda/reader x {:key-fn (or key-fn keyword)})))

(defn read
  ([reader] (read reader nil))
  ([reader _]
   (oda/read reader)))

(defn read-str
  ([s] (read-str s nil))
  ([s {:keys [key-fn]}] (oda/parse s {:key-fn (or key-fn keyword)})))

(defn write-str
  ([s] (write-str s nil))
  ([s _opts] (oda/write-str s)))

(def fns ['com.s-exp/oda read-str write-str ->json-reader read])
