(ns babashka.json)

(defn try-require [sym]
  (try (deref (requiring-resolve sym))
       (catch Exception _e
         nil)))

(let [[lib read-fn write-fn]
      (or
       ;; this should always work in babashka
       (try-require 'babashka.json.internal.cheshire/fns)
       ;; this needs to be added to the classpath explicitly
       (try-require 'babashka.json.internal.charred/fns)
       ;; this one should always work as this project has a dependency on it.
       (try-require 'babashka.json.internal.data-json/fns))]
  (def ^:private lib lib)
  (def ^:private read-fn read-fn)
  (def ^:private write-fn write-fn))

(defn read-str
  ([s] (read-str s nil))
  ([s opts]
   (read-fn s opts)))

(defn write-str
  ([s] (write-str s nil))
  ([s opts]
   (write-fn s opts)))

(defn get-provider
  "Returns which library currently provides the JSON implementation."
  []
  lib)
