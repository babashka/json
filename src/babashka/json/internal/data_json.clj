(ns babashka.json.internal.data-json
  (:require [clojure.data.json :as json])
  (:refer-clojure :exclude [read]))

(defn read
  ([reader] (read reader nil))
  ([reader _opts]
   (json/read reader)))

(defn read-str
  ([s] (read-str s nil))
  ([s {:keys [key-fn]}] (json/read-str s :key-fn (or key-fn keyword))))

(defn write-str
  ([s] (write-str s nil))
  ([s _opts] (json/write-str s)))

(def fns ['org.clojure/data.json read-str write-str read])
