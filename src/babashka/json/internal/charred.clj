(ns babashka.json.internal.charred
  (:require [charred.api :as json]))

(defn read-str
  ([s] (read-str s nil))
  ([s {:keys [key-fn]}] (json/read-json s :key-fn (or key-fn keyword))))

(defn write-str
  ([s] (write-str s nil))
  ([s _opts] (json/write-json-str s)))

(def fns [read-str write-str])
