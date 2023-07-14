(ns babashka.json.internal.cheshire
  (:require [cheshire.core :as cheshire])
  (:refer-clojure :exclude [read]))

(defn read-str
  ([s] (read-str s nil))
  ([s {:keys [key-fn]}] (cheshire/parse-string s (or key-fn keyword))))

(defn write-str
  ([s] (write-str s nil))
  ([s _opts] (cheshire/generate-string s)))

(defn read
  ([reader] (read reader nil))
  ([reader _opts]
   (cheshire/parse-stream-strict reader true)))

(def fns ['cheshire/cheshire read-str write-str read])
