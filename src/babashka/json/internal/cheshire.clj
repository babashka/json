(ns babashka.json.internal.cheshire
  (:require [cheshire.core :as cheshire]))

(defn read-str
  ([s] (read-str s nil))
  ([s {:keys [key-fn]}] (cheshire/parse-string s (or key-fn keyword))))

(defn write-str
  ([s] (write-str s nil))
  ([s _opts] (cheshire/generate-string s)))

(def fns [read-str write-str])
