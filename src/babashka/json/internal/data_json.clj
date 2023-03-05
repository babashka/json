(ns babashka.json.internal.data-json
  (:require [clojure.data.json :as json]))

(defn read-str
  ([s] (read-str s nil))
  ([s {:keys [key-fn]}] (json/read-str s :key-fn (or key-fn keyword))))

(defn write-str
  ([s] (write-str s nil))
  ([s _opts] (json/write-str s)))

(def fns ['org.clojure/data.json read-str write-str])
