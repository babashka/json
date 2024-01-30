(ns babashka.json.internal.jsonista
  (:require [jsonista.core :as json])
  (:refer-clojure :exclude [read])
  (:import))

(set! *warn-on-reflection* true)

(defn- -key-fn->object-mapper
  ^com.fasterxml.jackson.databind.ObjectMapper [key-fn]
  (if-not key-fn
    json/default-object-mapper
    (json/object-mapper {:decode-key-fn key-fn})))

(defn ->json-reader
  ([reader] (->json-reader reader nil))
  ([reader {:keys [key-fn] :or {key-fn keyword}}]
   (-> (-key-fn->object-mapper key-fn)
       .getFactory
       (.createParser ^java.io.Reader reader))))

(defn read
  ([s] (read s nil))
  ([s _ #_{:keys [key-fn] :or {key-fn keyword}}]
   (.readValueAs ^com.fasterxml.jackson.core.json.ReaderBasedJsonParser s Object)))

(defn read-str
  ([s] (read-str s nil))
  ([s {:keys [key-fn] :or {key-fn keyword}}]
   (json/read-value s (-key-fn->object-mapper key-fn))))

(defn write-str
  ([s] (write-str s nil))
  ([s {:keys [key-fn] :or {key-fn keyword}}] (json/write-value-as-string s (-key-fn->object-mapper key-fn))))

(def fns ['metosin/jsonista read-str write-str ->json-reader read])
