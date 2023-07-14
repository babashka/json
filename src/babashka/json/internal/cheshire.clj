(ns babashka.json.internal.cheshire
  (:require [cheshire.core :as cheshire]
            [cheshire.factory :as factory]
            [cheshire.parse :as cheshire-parse])
  (:refer-clojure :exclude [read])
  (:import [com.fasterxml.jackson.core JsonFactory]
           [java.io Reader]))

(defn read-str
  ([s] (read-str s nil))
  ([s {:keys [key-fn]}] (cheshire/parse-string s (or key-fn keyword))))

(defn write-str
  ([s] (write-str s nil))
  ([s _opts] (cheshire/generate-string s)))

(defn ->json-reader
  ([rdr] (->json-reader rdr nil))
  ([rdr _opts]
   (.createParser ^JsonFactory (or factory/*json-factory*
                                   factory/json-factory)
                  ^Reader rdr)))

(defn read
  ([reader] (read reader nil))
  ([reader _opts]
   (cheshire-parse/parse-strict reader true nil nil)))

(def fns ['cheshire/cheshire read-str write-str ->json-reader read])
