(ns babashka.json.internal.jsonista
  (:require [jsonista.core :as j]))

(defn- -key-fn->object-mapper
  [key-fn]
  (if-not key-fn
    j/default-object-mapper
    (j/object-mapper {:decode-key-fn key-fn})))

(defn read-str
  ([s] (read-str s nil))
  ([s {:keys [key-fn] :or {key-fn keyword}}]
   (j/read-value s (-key-fn->object-mapper key-fn))))

(defn write-str
  ([s] (write-str s nil))
  ([s _opts] (j/write-value-as-string s (-key-fn->object-mapper nil))))

(def fns ['metosin/jsonista read-str write-str])
